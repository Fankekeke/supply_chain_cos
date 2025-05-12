<template>
  <a-modal v-model="show" title="新增供应商" @cancel="onClose" :width="800">
    <template slot="footer">
      <a-button key="back" @click="onClose">
        取消
      </a-button>
      <a-button key="submit" type="primary" :loading="loading" @click="handleSubmit">
        提交
      </a-button>
    </template>
    <a-form :form="form" layout="vertical">
      <a-row :gutter="20">
        <a-col :span="8">
          <a-form-item label='供应商名称' v-bind="formItemLayout">
            <a-input v-decorator="[
            'supplierName',
            { rules: [{ required: true, message: '请输入供应商名称!' }] }
            ]"/>
          </a-form-item>
        </a-col>
        <a-col :span="16">
          <a-form-item label='可供采购类型' v-bind="formItemLayout">
            <a-select v-decorator="[
              'purchaseType',
              { rules: [{ required: true, message: '请输入可供采购类型!' }] }
              ]">
              <a-select-option value="1">电容</a-select-option>
              <a-select-option value="2">微控制器</a-select-option>
              <a-select-option value="3">滤波器</a-select-option>
              <a-select-option value="4">连接器</a-select-option>
            </a-select>
          </a-form-item>
        </a-col>
        <a-col :span="12">
          <a-form-item label='联系人' v-bind="formItemLayout">
            <a-input v-decorator="[
            'contact',
            { rules: [{ required: true, message: '请输入联系人!' }] }
            ]"/>
          </a-form-item>
        </a-col>
        <a-col :span="12">
          <a-form-item label='联系方式' v-bind="formItemLayout">
            <a-input v-decorator="[
            'phone',
            { rules: [{ required: true, message: '请输入联系方式!' }] }
            ]"/>
          </a-form-item>
        </a-col>
        <a-col :span="12">
          <a-form-item label='邮箱地址' v-bind="formItemLayout">
            <a-input v-decorator="[
            'email',
            { rules: [{ required: true, message: '请输入邮箱地址!' }] }
            ]"/>
          </a-form-item>
        </a-col>
        <a-col :span="24">
          <a-form-item label="备注">
            <a-textarea placeholder="Basic usage" :rows="4" v-decorator="['remark']"/>
          </a-form-item>
        </a-col>
        <a-col :span="24">
          <a-form-item label='图片' v-bind="formItemLayout">
            <a-upload
              name="avatar"
              action="http://127.0.0.1:9527/file/fileUpload/"
              list-type="picture-card"
              :file-list="fileList"
              @preview="handlePreview"
              @change="picHandleChange"
            >
              <div v-if="fileList.length < 2">
                <a-icon type="plus" />
                <div class="ant-upload-text">
                  Upload
                </div>
              </div>
            </a-upload>
            <a-modal :visible="previewVisible" :footer="null" @cancel="handleCancel">
              <img alt="example" style="width: 100%" :src="previewImage" />
            </a-modal>
          </a-form-item>
        </a-col>
      </a-row>
    </a-form>
  </a-modal>
</template>

<script>
import {mapState} from 'vuex'
function getBase64 (file) {
  return new Promise((resolve, reject) => {
    const reader = new FileReader()
    reader.readAsDataURL(file)
    reader.onload = () => resolve(reader.result)
    reader.onerror = error => reject(error)
  })
}
const formItemLayout = {
  labelCol: { span: 24 },
  wrapperCol: { span: 24 }
}
export default {
  name: 'supplierAdd',
  props: {
    supplierAddVisiable: {
      default: false
    }
  },
  computed: {
    ...mapState({
      currentUser: state => state.account.user
    }),
    show: {
      get: function () {
        return this.supplierAddVisiable
      },
      set: function () {
      }
    }
  },
  data () {
    return {
      formItemLayout,
      form: this.$form.createForm(this),
      loading: false,
      fileList: [],
      previewVisible: false,
      previewImage: ''
    }
  },
  methods: {
    handleCancel () {
      this.previewVisible = false
    },
    async handlePreview (file) {
      if (!file.url && !file.preview) {
        file.preview = await getBase64(file.originFileObj)
      }
      this.previewImage = file.url || file.preview
      this.previewVisible = true
    },
    picHandleChange ({ fileList }) {
      this.fileList = fileList
    },
    reset () {
      this.loading = false
      this.form.resetFields()
    },
    onClose () {
      this.reset()
      this.$emit('close')
    },
    handleSubmit () {
      // 获取图片List
      let images = []
      this.fileList.forEach(image => {
        images.push(image.response)
      })
      this.form.validateFields((err, values) => {
        if (!err) {
          values.images = images.length > 0 ? images.join(',') : null
          this.loading = true
          this.$post('/cos/supplier-info', {
            ...values
          }).then((r) => {
            this.reset()
            this.$emit('success')
          }).catch(() => {
            this.loading = false
          })
        }
      })
    }
  }
}
</script>

<style scoped>

</style>
