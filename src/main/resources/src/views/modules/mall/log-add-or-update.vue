<template>
  <el-dialog
    :title="!dataForm.logId ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="用户id" prop="userId">
      <el-input v-model="dataForm.userId" placeholder="用户id"></el-input>
    </el-form-item>
    <el-form-item label="支出" prop="pay">
      <el-input v-model="dataForm.pay" placeholder="支出"></el-input>
    </el-form-item>
    <el-form-item label="日期" prop="creatTime">
      <el-input v-model="dataForm.creatTime" placeholder="日期"></el-input>
    </el-form-item>
    <el-form-item label="收入" prop="money">
      <el-input v-model="dataForm.money" placeholder="收入"></el-input>
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
          logId: 0,
          userId: '',
          pay: '',
          creatTime: '',
          money: ''
        },
        dataRule: {
          userId: [
            { required: true, message: '用户id不能为空', trigger: 'blur' }
          ],
          pay: [
            { required: true, message: '支出不能为空', trigger: 'blur' }
          ],
          creatTime: [
            { required: true, message: '日期不能为空', trigger: 'blur' }
          ],
          money: [
            { required: true, message: '收入不能为空', trigger: 'blur' }
          ]
        }
      }
    },
    methods: {
      init (id) {
        this.dataForm.logId = id || 0
        this.visible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].resetFields()
          if (this.dataForm.logId) {
            this.$http({
              url: this.$http.adornUrl(`/mall/log/info/${this.dataForm.logId}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.userId = data.log.userId
                this.dataForm.pay = data.log.pay
                this.dataForm.creatTime = data.log.creatTime
                this.dataForm.money = data.log.money
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
              url: this.$http.adornUrl(`/mall/log/${!this.dataForm.logId ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'logId': this.dataForm.logId || undefined,
                'userId': this.dataForm.userId,
                'pay': this.dataForm.pay,
                'creatTime': this.dataForm.creatTime,
                'money': this.dataForm.money
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
