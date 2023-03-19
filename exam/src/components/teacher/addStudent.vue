
<template>
  <section class="add">
    <el-form ref="form" :model="form" label-width="80px">
      <el-form-item label="Name">
        <el-input v-model="form.studentName"></el-input>
      </el-form-item>
      <el-form-item label="Sex">
        <el-input v-model="form.sex"></el-input>
      </el-form-item>
      <el-form-item label="College">
        <el-input v-model="form.institute"></el-input>
      </el-form-item>
      <el-form-item label="Course">
        <el-input v-model="form.major"></el-input>
      </el-form-item>
      <el-form-item label="Year">
        <el-input v-model="form.grade"></el-input>
      </el-form-item>
      <el-form-item label="Class">
        <el-input v-model="form.clazz"></el-input>
      </el-form-item>
      <el-form-item label="Phone">
        <el-input v-model="form.tel"></el-input>
      </el-form-item>
      <el-form-item label="ID">
        <el-input v-model="form.cardId"></el-input>
      </el-form-item>
      <el-form-item label="Email">
        <el-input v-model="form.email"></el-input>
      </el-form-item>
      <el-form-item label="Password">
        <el-input v-model="form.pwd"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="onSubmit()">Create it</el-button>
        <el-button type="text" @click="cancel()">Cancel</el-button>
      </el-form-item>
    </el-form>
  </section>
</template>

<script>
export default {
  data() {
    return {
      form: { 
        studentName: null,
        grade: null,
        major: null,
        clazz: null,
        institute: null,
        tel: null,
        email: null,
        pwd: null,
        cardId: null,
        sex: null,
        role: 2
      }
    };
  },
  methods: {
    onSubmit() {
      let tokenStr = this.$session.get('jwt')
      const headers = 
      {
      
       'Authorization' : 'Bearer ' + tokenStr
      }
      this.$axios({
       /*  url: '/api/student', */
       url: `http://localhost:8080/student`,
        method: 'post',
        data: {
          ...this.form
        },
        headers
      }).then(res => {
        if(res.data.code == 200) {
          this.$message({
            message: 'Added to database',
            type: 'success'
          })
          this.$router.push({path: '/studentManage'})
        }
      })
    },
    cancel() {
      this.form = {}
    },
    
  }
};
</script>
<style lang="less" scoped>
.add {
  padding: 0px 40px;
  width: 400px;
}
</style>

