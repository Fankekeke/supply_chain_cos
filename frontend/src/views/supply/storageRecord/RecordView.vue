<template>
  <a-modal v-model="show" title="供货详情" @cancel="onClose" :width="900">
    <template slot="footer">
      <a-button key="back" @click="onClose" type="danger">
        关闭
      </a-button>
      <a-button v-if="recordData.status == 1" key="back1" @click="checkStock(3)" type="danger">
        退货
      </a-button>
      <a-button v-if="recordData.status == 1" key="back2" @click="checkStock(2)" type="primary">
        入库
      </a-button>
    </template>
    <div style="font-size: 13px;font-family: SimHei" v-if="recordData !== null">
      <a-row style="padding-left: 24px;padding-right: 24px;">
        <a-col style="margin-bottom: 15px"><span style="font-size: 15px;font-weight: 650;color: #000c17">基础信息</span></a-col>
        <a-col :span="8"><b>供货单号：</b>
          {{ recordData.code !== null ? recordData.code : '- -' }}
        </a-col>
        <a-col :span="8"><b>价 格：</b>
          {{ recordData.totalPrice }} 元
        </a-col>
        <a-col :span="8"><b>保管人：</b>
          {{ recordData.custodianName }}
        </a-col>
      </a-row>
      <br/>
      <a-row style="padding-left: 24px;padding-right: 24px;">
        <a-col :span="8"><b>供货时间：</b>
          {{ recordData.createDate !== null ? recordData.createDate : '- -' }}
        </a-col>
        <a-col :span="8"><b>经手人：</b>
          {{ recordData.handlerName }}
        </a-col>
        <a-col :span="8"><b>备 注：</b>
          {{ recordData.remark }}
        </a-col>
      </a-row>
      <br/>
      <a-row style="padding-left: 24px;padding-right: 24px;">
        <a-col :span="8"><b>供应商：</b>
          {{ recordData.supplierName !== null ? recordData.supplierName : '- -' }}
        </a-col>
      </a-row>
      <br/>
      <br/>
      <a-row style="padding-left: 24px;padding-right: 24px;" :gutter="15">
        <a-col style="margin-bottom: 15px"><span style="font-size: 15px;font-weight: 650;color: #000c17">物品详情</span></a-col>
        <a-col :span="24">
          <a-table :columns="columns" :data-source="goodsList">
            <template slot="nameShow" slot-scope="text, record">
              <a-tooltip>
                <template slot="title">
                  {{ record.materialName }}
                </template>
                {{ record.materialName.slice(0, 18) }} ...
              </a-tooltip>
            </template>
            <template slot="modelShow" slot-scope="text, record">
              <a-tooltip>
                <template slot="title">
                  {{ record.model }}
                </template>
                {{ record.model.slice(0, 10) }} ...
              </a-tooltip>
            </template>
            <template slot="typeIdShow" slot-scope="text, record">
              <span v-if="record.materialType == 1">电容</span>
              <span v-if="record.materialType == 2">微控制器</span>
              <span v-if="record.materialType == 3">滤波器</span>
              <span v-if="record.materialType == 4">连接器</span>
            </template>
          </a-table>
        </a-col>
      </a-row>
    </div>
  </a-modal>
</template>

<script>
import moment from 'moment'
moment.locale('zh-cn')
export default {
  name: 'RecordView',
  props: {
    recordShow: {
      type: Boolean,
      default: false
    },
    recordData: {
      type: Object
    }
  },
  computed: {
    show: {
      get: function () {
        return this.recordShow
      },
      set: function () {
      }
    },
    columns () {
      return [{
        title: '物品名称',
        dataIndex: 'materialName',
        scopedSlots: {customRender: 'nameShow'}
      }, {
        title: '型号',
        dataIndex: 'model',
        scopedSlots: {customRender: 'modelShow'}
      }, {
        title: '数量',
        dataIndex: 'quantity'
      }, {
        title: '所属类型',
        dataIndex: 'materialType',
        scopedSlots: {customRender: 'typeIdShow'}
      }, {
        title: '单位',
        dataIndex: 'measurementUnit'
      }, {
        title: '单价',
        dataIndex: 'unitPrice',
        customRender: (text, row, index) => {
          if (text !== null) {
            return '￥' + text.toFixed(2)
          } else {
            return '- -'
          }
        }
      }]
    }
  },
  data () {
    return {
      loading: false,
      goodsList: []
    }
  },
  watch: {
    recordShow: function (value) {
      if (value) {
        this.getGoodsByNum(this.recordData.code)
      }
    }
  },
  methods: {
    checkStock (status) {
      this.$get(`/cos/storage-record/checkStock`, {id: this.recordData.id, status}).then((r) => {
        this.$emit('success')
      })
    },
    getGoodsByNum (num) {
      this.$get(`/cos/storage-record/${num}`).then((r) => {
        this.goodsList = r.data.data
        console.log(this.goodsList)
      })
    },
    onClose () {
      this.$emit('close')
    }
  }
}
</script>

<style scoped>
  /deep/ .ant-table-tbody {
    font-size: 12px;
  }
  /deep/ .ant-table-thead {
    font-size: 13px;
  }
</style>
