<template>
  <a-card :bordered="false" class="card-area">
    <div :class="advanced ? 'search' : null">
      <!-- 搜索区域 -->
      <a-form layout="horizontal">
        <a-row :gutter="15">
          <div :class="advanced ? null: 'fold'">
            <a-col :md="6" :sm="24">
              <a-form-item
                label="订单编号"
                :labelCol="{span: 5}"
                :wrapperCol="{span: 18, offset: 1}">
                <a-input v-model="queryParams.code"/>
              </a-form-item>
            </a-col>
            <a-col :md="6" :sm="24">
              <a-form-item
                label="客户名称"
                :labelCol="{span: 5}"
                :wrapperCol="{span: 18, offset: 1}">
                <a-input v-model="queryParams.userName"/>
              </a-form-item>
            </a-col>
            <a-col :md="6" :sm="24">
              <a-form-item
                label="收货地址"
                :labelCol="{span: 5}"
                :wrapperCol="{span: 18, offset: 1}">
                <a-input v-model="queryParams.address"/>
              </a-form-item>
            </a-col>
            <a-col :md="6" :sm="24">
              <a-form-item
                label="联系人"
                :labelCol="{span: 5}"
                :wrapperCol="{span: 18, offset: 1}">
                <a-input v-model="queryParams.contact"/>
              </a-form-item>
            </a-col>
          </div>
          <span style="float: right; margin-top: 3px;">
            <a-button type="primary" @click="search">查询</a-button>
            <a-button style="margin-left: 8px" @click="reset">重置</a-button>
          </span>
        </a-row>
      </a-form>
    </div>
    <div>
      <div class="operator">
<!--        <a-button type="primary" ghost @click="add">添加订单</a-button>-->
<!--        <a-button @click="batchDelete">删除</a-button>-->
      </div>
      <!-- 表格区域 -->
      <a-table ref="TableInfo"
               :columns="columns"
               :rowKey="record => record.id"
               :dataSource="dataSource"
               :pagination="pagination"
               :loading="loading"
               :rowSelection="{selectedRowKeys: selectedRowKeys, onChange: onSelectChange}"
               :scroll="{ x: 900 }"
               @change="handleTableChange">
        <template slot="titleShow" slot-scope="text, record">
          <template>
            <a-tooltip>
              <template slot="title">
                {{ record.title }}
              </template>
              {{ record.title.slice(0, 8) }} ...
            </a-tooltip>
          </template>
        </template>
        <template slot="operation" slot-scope="text, record">
          <a-icon v-if="(record.type == 0 && record.status == 0 )|| (record.type == 1 && record.oweDate == null)" type="alipay" @click="pay(record)" title="支 付"></a-icon>
          <a-icon type="file-search" @click="orderViewOpen(record)" title="详 情" style="margin-left: 15px"></a-icon>
          <a-icon type="check" v-if="record.status == 4" @click="orderAuditOpen(record)" title="签 收" style="margin-left: 15px"></a-icon>
        </template>
      </a-table>
    </div>
    <order-audit
      @close="handleorderAuditViewClose"
      @success="handleorderAuditViewSuccess"
      :orderAuditShow="orderAuditView.visiable"
      :orderAuditData="orderAuditView.data">
    </order-audit>
    <order-view
      @close="handleorderViewClose"
      :orderShow="orderView.visiable"
      :orderData="orderView.data">
    </order-view>
  </a-card>
</template>

<script>
import RangeDate from '@/components/datetime/RangeDate'
import {mapState} from 'vuex'
import moment from 'moment'
import OrderAudit from './OrderAudit'
import OrderView from './OrderView'
moment.locale('zh-cn')

export default {
  name: 'order',
  components: {OrderView, OrderAudit, RangeDate},
  data () {
    return {
      advanced: false,
      orderAdd: {
        visiable: false
      },
      orderEdit: {
        visiable: false
      },
      orderView: {
        visiable: false,
        data: null
      },
      orderStatusView: {
        visiable: false,
        data: null
      },
      queryParams: {},
      filteredInfo: null,
      sortedInfo: null,
      paginationInfo: null,
      dataSource: [],
      selectedRowKeys: [],
      loading: false,
      pagination: {
        pageSizeOptions: ['10', '20', '30', '40', '100'],
        defaultCurrent: 1,
        defaultPageSize: 10,
        showQuickJumper: true,
        showSizeChanger: true,
        showTotal: (total, range) => `显示 ${range[0]} ~ ${range[1]} 条记录，共 ${total} 条记录`
      },
      orderAuditView: {
        visiable: false,
        data: null
      },
      userList: []
    }
  },
  computed: {
    ...mapState({
      currentUser: state => state.account.user
    }),
    columns () {
      return [{
        title: '订单编号',
        dataIndex: 'code',
        ellipsis: true
      }, {
        title: '客户名称',
        dataIndex: 'userName',
        customRender: (text, row, index) => {
          if (text !== null) {
            return text
          } else {
            return '- -'
          }
        }
      }, {
        title: '用户头像',
        dataIndex: 'images',
        customRender: (text, record, index) => {
          if (!record.images) return <a-avatar shape="square" icon="user" />
          return <a-popover>
            <template slot="content">
              <a-avatar shape="square" size={132} icon="user" src={ 'http://127.0.0.1:9527/imagesWeb/' + record.images.split(',')[0] } />
            </template>
            <a-avatar shape="square" icon="user" src={ 'http://127.0.0.1:9527/imagesWeb/' + record.images.split(',')[0] } />
          </a-popover>
        }
      }, {
        title: '订单类型',
        dataIndex: 'type',
        customRender: (text, row, index) => {
          switch (text) {
            case '0':
              return <a-tag>正常订单</a-tag>
            case '1':
              return <a-tag color="pink">预付款</a-tag>
            default:
              return '- -'
          }
        }
      }, {
        title: '联系方式',
        dataIndex: 'phone',
        customRender: (text, row, index) => {
          if (text !== null) {
            return text
          } else {
            return '- -'
          }
        }
      }, {
        title: '订单总额',
        dataIndex: 'totalPrice',
        customRender: (text, row, index) => {
          if (text !== null) {
            return text + '元'
          } else {
            return '- -'
          }
        }
      }, {
        title: '收货地址',
        dataIndex: 'address',
        customRender: (text, row, index) => {
          if (text !== null) {
            return text
          } else {
            return '- -'
          }
        },
        ellipsis: true
      }, {
        title: '订单状态',
        dataIndex: 'status',
        customRender: (text, row, index) => {
          switch (text) {
            case '0':
              return <a-tag>待支付</a-tag>
            case '1':
              return <a-tag>预付款已缴</a-tag>
            case '2':
              return <a-tag>尾款已缴</a-tag>
            case '3':
              return <a-tag>已支付</a-tag>
            case '4':
              return <a-tag>已出库</a-tag>
            case '5':
              return <a-tag>已签收</a-tag>
            default:
              return '- -'
          }
        }
      }, {
        title: '下单时间',
        dataIndex: 'createDate',
        customRender: (text, row, index) => {
          if (text !== null) {
            return text
          } else {
            return '- -'
          }
        },
        ellipsis: true
      }, {
        title: '操作',
        dataIndex: 'operation',
        scopedSlots: {customRender: 'operation'}
      }]
    }
  },
  mounted () {
    this.fetch()
  },
  methods: {
    pay (row) {
      if (row.type === '0' && row.status === '0') {
        let data = { outTradeNo: row.code, subject: `${row.code}`, totalAmount: row.totalPrice, body: '' }
        this.$post('/cos/pay/alipay', data).then((r) => {
          // 添加之前先删除一下，如果单页面，页面不刷新，添加进去的内容会一直保留在页面中，二次调用form表单会出错
          const divForm = document.getElementsByTagName('div')
          if (divForm.length) {
            document.body.removeChild(divForm[0])
          }
          const div = document.createElement('div')
          div.innerHTML = r.data.msg // data就是接口返回的form 表单字符串
          // console.log(div.innerHTML)
          document.body.appendChild(div)
          document.forms[0].setAttribute('target', '_self') // 新开窗口跳转
          document.forms[0].submit()
        })
      }
      if (row.type === '1' && row.payDate == null) {
        let data = { outTradeNo: row.code, subject: `${row.code}`, totalAmount: row.subsistPrice, body: '' }
        this.$post('/cos/pay/alipay', data).then((r) => {
          // 添加之前先删除一下，如果单页面，页面不刷新，添加进去的内容会一直保留在页面中，二次调用form表单会出错
          const divForm = document.getElementsByTagName('div')
          if (divForm.length) {
            document.body.removeChild(divForm[0])
          }
          const div = document.createElement('div')
          div.innerHTML = r.data.msg // data就是接口返回的form 表单字符串
          // console.log(div.innerHTML)
          document.body.appendChild(div)
          document.forms[0].setAttribute('target', '_self') // 新开窗口跳转
          document.forms[0].submit()
        })
      } else if (row.type === '1' && row.oweDate == null) {
        let data = { outTradeNo: row.oweCode, subject: `${row.oweCode}`, totalAmount: row.owePrice, body: '' }
        this.$post('/cos/pay/alipay', data).then((r) => {
          // 添加之前先删除一下，如果单页面，页面不刷新，添加进去的内容会一直保留在页面中，二次调用form表单会出错
          const divForm = document.getElementsByTagName('div')
          if (divForm.length) {
            document.body.removeChild(divForm[0])
          }
          const div = document.createElement('div')
          div.innerHTML = r.data.msg // data就是接口返回的form 表单字符串
          // console.log(div.innerHTML)
          document.body.appendChild(div)
          document.forms[0].setAttribute('target', '_self') // 新开窗口跳转
          document.forms[0].submit()
        })
      }
    },
    orderStatusOpen (row) {
      this.orderStatusView.data = row
      this.orderStatusView.visiable = true
    },
    orderAuditOpen (row) {
      this.$get(`/cos/order-info/orderReceiving`, {
        'orderId': row.id
      }).then((r) => {
        this.$message.success('收货成功')
        this.search()
      })
    },
    orderViewOpen (row) {
      this.orderView.data = row
      this.orderView.visiable = true
    },
    handleorderViewClose () {
      this.orderView.visiable = false
    },
    handleorderStatusViewClose () {
      this.orderStatusView.visiable = false
    },
    handleorderStatusViewSuccess () {
      this.orderStatusView.visiable = false
      this.$message.success('修改成功')
      this.fetch()
    },
    handleorderAuditViewClose () {
      this.orderAuditView.visiable = false
    },
    handleorderAuditViewSuccess () {
      this.orderAuditView.visiable = false
      this.$message.success('审核成功')
      this.fetch()
    },
    onSelectChange (selectedRowKeys) {
      this.selectedRowKeys = selectedRowKeys
    },
    toggleAdvanced () {
      this.advanced = !this.advanced
    },
    add () {
      this.orderAdd.visiable = true
    },
    handleorderAddClose () {
      this.orderAdd.visiable = false
    },
    handleorderAddSuccess () {
      this.orderAdd.visiable = false
      this.$message.success('添加平台订单成功')
      this.search()
    },
    edit (record) {
      this.$refs.orderEdit.setFormValues(record)
      this.orderEdit.visiable = true
    },
    handleorderEditClose () {
      this.orderEdit.visiable = false
    },
    handleorderEditSuccess () {
      this.orderEdit.visiable = false
      this.$message.success('修改产品成功')
      this.search()
    },
    handleDeptChange (value) {
      this.queryParams.deptId = value || ''
    },
    batchDelete () {
      if (!this.selectedRowKeys.length) {
        this.$message.warning('请选择需要删除的记录')
        return
      }
      let that = this
      this.$confirm({
        title: '确定删除所选中的记录?',
        content: '当您点击确定按钮后，这些记录将会被彻底删除',
        centered: true,
        onOk () {
          let ids = that.selectedRowKeys.join(',')
          that.$delete('/cos/order-info/' + ids).then(() => {
            that.$message.success('删除成功')
            that.selectedRowKeys = []
            that.search()
          })
        },
        onCancel () {
          that.selectedRowKeys = []
        }
      })
    },
    search () {
      let {sortedInfo, filteredInfo} = this
      let sortField, sortOrder
      // 获取当前列的排序和列的过滤规则
      if (sortedInfo) {
        sortField = sortedInfo.field
        sortOrder = sortedInfo.order
      }
      this.fetch({
        sortField: sortField,
        sortOrder: sortOrder,
        ...this.queryParams,
        ...filteredInfo
      })
    },
    reset () {
      // 取消选中
      this.selectedRowKeys = []
      // 重置分页
      this.$refs.TableInfo.pagination.current = this.pagination.defaultCurrent
      if (this.paginationInfo) {
        this.paginationInfo.current = this.pagination.defaultCurrent
        this.paginationInfo.pageSize = this.pagination.defaultPageSize
      }
      // 重置列过滤器规则
      this.filteredInfo = null
      // 重置列排序规则
      this.sortedInfo = null
      // 重置查询参数
      this.queryParams = {}
      this.fetch()
    },
    handleTableChange (pagination, filters, sorter) {
      // 将这三个参数赋值给Vue data，用于后续使用
      this.paginationInfo = pagination
      this.filteredInfo = filters
      this.sortedInfo = sorter

      this.fetch({
        sortField: sorter.field,
        sortOrder: sorter.order,
        ...this.queryParams,
        ...filters
      })
    },
    fetch (params = {}) {
      // 显示loading
      this.loading = true
      if (this.paginationInfo) {
        // 如果分页信息不为空，则设置表格当前第几页，每页条数，并设置查询分页参数
        this.$refs.TableInfo.pagination.current = this.paginationInfo.current
        this.$refs.TableInfo.pagination.pageSize = this.paginationInfo.pageSize
        params.size = this.paginationInfo.pageSize
        params.current = this.paginationInfo.current
      } else {
        // 如果分页信息为空，则设置为默认值
        params.size = this.pagination.defaultPageSize
        params.current = this.pagination.defaultCurrent
      }
      if (params.status === undefined) {
        delete params.status
      }
      params.userId = this.currentUser.userId
      this.$get('/cos/order-info/page', {
        ...params
      }).then((r) => {
        let data = r.data.data
        const pagination = {...this.pagination}
        pagination.total = data.total
        this.dataSource = data.records
        this.pagination = pagination
        // 数据加载完毕，关闭loading
        this.loading = false
      })
    }
  },
  watch: {}
}
</script>
<style lang="less" scoped>
@import "../../../../static/less/Common";
</style>
