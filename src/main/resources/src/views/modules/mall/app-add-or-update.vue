<template>
  <el-dialog
    :title="!dataForm.appId ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="用户id" prop="userId">
      <el-input v-model="dataForm.userId" placeholder="用户id"></el-input>
    </el-form-item>
    <el-form-item label="钱包余额" prop="appAccount">
      <el-input v-model="dataForm.appAccount" placeholder="钱包余额"></el-input>
    </el-form-item>
    <el-form-item label="时间" prop="createdTime">
      <el-input v-model="dataForm.createdTime" placeholder="时间"></el-input>
    </el-form-item>
    </el-form>
    <span slot="footer" class="dialog-footer">
      <el-button @click="visible = false">取消</el-button>
      <el-button type="primary" @click="dataFormSubmit()">确定</el-button>
    </span>
  </el-dialog>
</template>

<script>
  export default {
    data () {
      return {
        visible: false,
        dataForm: {
          appId: 0,
          userId: '',
          appAccount: '',
          createdTime: ''
        },
        dataRule: {
          userId: [
            { required: true, message: '用户id不能为空', trigger: 'blur' }
          ],
          appAccount: [
            { required: true, message: '钱包余额不能为空', trigger: 'blur' }
          ],
          createdTime: [
            { required: true, message: '时间不能为空', trigger: 'blur' }
          ]
        }
      }
    },
    methods: {
      init (id) {
        this.dataForm.appId = id || 0
        this.visible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].resetFields()
          if (this.dataForm.appId) {
            this.$http({
              url: this.$http.adornUrl(`/mall/app/info/${this.dataForm.appId}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.userId = data.app.userId
                this.dataForm.appAccount = data.app.appAccount
                this.dataForm.createdTime = data.app.createdTime
              }
            })
          }
        })
      },
      // 表单提交
      dataFormSubmit () {
        this.$refs['dataForm'].validate((valid) => {
          if (valid) {
            this.$http({
              url: this.$http.adornUrl(`/mall/app/${!this.dataForm.appId ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'appId': this.dataForm.appId || undefined,
                'userId': this.dataForm.userId,
                'appAccount': this.dataForm.appAccount,
                'createdTime': this.dataForm.createdTime
              })
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.$message({
                  message: '操作成功',
                  type: 'success',
                  duration: 1500,
                  onClose: () => {
                    this.visible = false
                    this.$emit('refreshDataList')
                  }
                })
              } else {
                this.$message.error(data.msg)
              }
            })
          }
        })
      }
    }
  }
</script>
