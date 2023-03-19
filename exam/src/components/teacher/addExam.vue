
<template>
  <section class="add">
    <el-form ref="form" :model="form" label-width="80px">
      <el-form-item label="Exam">
        <el-input v-model="form.source"></el-input>
      </el-form-item>
      <el-form-item label="Professor">
        <el-input v-model="form.description"></el-input>
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
      <el-form-item label="Date">
        <el-col :span="11">
          <el-date-picker placeholder="Choose Date" v-model="form.examDate" style="width: 100%;"></el-date-picker>
        </el-col>
      </el-form-item>
      <el-form-item label="Time">
        <el-input v-model="form.totalTime"></el-input>
      </el-form-item>
      <el-form-item label="Score">
        <el-input v-model="form.totalScore"></el-input>
      </el-form-item>
      <el-form-item label="Type">
        <el-input v-model="form.type"></el-input>
      </el-form-item>
      <el-form-item label="Hint">
        <el-input type="textarea" v-model="form.tips"></el-input>
      </el-form-item>
      <el-form-item label="Public">
        <el-checkbox v-model="form.ispublic"></el-checkbox >
      </el-form-item>
      <el-form-item label="Survey">
        <el-checkbox v-model="form.issurvey"></el-checkbox >
      </el-form-item>
      <el-form-item label="Practice">
        <el-checkbox v-model="form.ispractice"></el-checkbox >
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="onSubmit()">Create it</el-button>
        <el-button type="text" @click="cancel()">Cancel</el-button>
      </el-form-item>
    </el-form>
  </section>
</template>

<script>
import { init } from 'events';

export default {
  data() {
    return {
      form: {
        source: null,
        description: null,
        institute: null,
        major: null,
        grade: null,
        examDate: null,
        totalTime: null,
        totalScore: null,
        type: null,
        tips: null,
        paperId: null,
        ispublic : true,
        issurvey : true,
        ispractice : true
      }
    };
    
  },
  methods: {
    formatTime(date) { 
      let year = date.getFullYear()
      let month= date.getMonth()+ 1 < 10 ? "0" + (date.getMonth() + 1) : date.getMonth() + 1;
      let day=date.getDate() < 10 ? "0" + date.getDate() : date.getDate();
      let hours=date.getHours() < 10 ? "0" + date.getHours() : date.getHours();
      let minutes=date.getMinutes() < 10 ? "0" + date.getMinutes() : date.getMinutes();
      let seconds=date.getSeconds() < 10 ? "0" + date.getSeconds() : date.getSeconds();

      return year+"-"+month+"-"+day+" "+hours+":"+minutes+":"+seconds;
    },
    onSubmit() {
      let examDate = this.formatTime(this.form.examDate)
      this.form.examDate = examDate.substr(0,10)
      let tokenStr = this.$session.get('jwt')
      const headers = 
      {
      
       'Authorization' : 'Bearer ' + tokenStr
      }
      var username = this.$cookies.get("cname")
///api/examManagePaperId
      this.$axios(`http://localhost:8080/examManagePaperId`,{headers}).then(res => {
        this.form.paperId = res.data.data.paperId + 1 
        this.$axios({
        /*   url: '/api/exam', */
          url: `http://localhost:8080/exam`,
          method: 'post',
          data: {
            ...this.form,
            teacher:username,
       /*      ispublic : 1,
            isSurvey : 0 */
          },
          headers
        }).then(res => {
          if(res.data.code == 200) {
            this.$message({
              message: 'Added to database',
              type: 'success'
            })
            this.$router.push({path: '/selectExam'})
          }
        })
      })
    },
    cancel() { 
      this.form = {}
    },
    
  }
};
</script>
<style lang="less" scoped>
/* .add {
  padding: 0px 40px;
  width: 400px;
} */

.add {
  padding: 0px 40px;
  width: 400px;
}

.el-form-item__label {
  font-weight: bold;
}

.el-input {
  width: 100%;
}

.el-date-picker {
  width: 100%;
}

.el-checkbox__label {
  font-weight: normal;
  margin-left: 5px;
}
</style>

