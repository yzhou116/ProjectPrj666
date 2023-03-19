
<template>
  <div class="all">
    <el-table :data="pagination.records" border>
 <!--     // width="180"width="200"width="200" -->
      <el-table-column fixed="left" prop="source" label="Survey" ></el-table-column>
      <el-table-column prop="description" label="Description" ></el-table-column>
      <el-table-column prop="major" label="Major" ></el-table-column>
 
      <el-table-column  label="Get Link" width="150"> 
        <template slot-scope="scope">
          <el-button @click="checklink(scope.row.examCode)" type="primary" size="small">Get Link</el-button>
        </template>
      </el-table-column>
    </el-table>

    <el-dialog
      title="Survry URL"
      :visible.sync="dialogVisible"
      width="30%"
      :before-close="handleClose">
      <section class="update">
        <el-form ref="form" :model="form" label-width="80px">
          <el-form-item label="URL">
            <el-input disabled  v-model="mUrl"></el-input>
          </el-form-item>
        </el-form>
      </section>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">Cancel</el-button>
        <el-button type="primary" @click="mCopy()">Copy</el-button>
      </span>
    </el-dialog>
    <a href="http://ec2-18-215-174-101.compute-1.amazonaws.com/johnEmail/">Go to Email Blast</a>
  </div>
</template>

<script>
export default {
  data() {
    return {
      pagination: {
 
        current: 1, 
        total: null, 
        size: 6 ,
        records : [],
        mUrl : ""
      },
      dialogVisible: false,
      mExamCode : ""
    };
  },
  created() {
    this.getAnswerInfo();
  },
  methods: {
    getAnswerInfo() {
 
      ///api/students/${this.pagination.current}/${this.pagination.size}
      let tokenStr = this.$session.get('jwt')
      const headers = 
      {
      
       'Authorization' : 'Bearer ' + tokenStr
      }
    /*   this.$axios(`http://localhost:8080/students/${this.pagination.current}/${this.pagination.size}`,{headers}).then(res => {
        this.pagination = res.data.data;
      }).catch(error => {}); */
      var username = this.$cookies.get("cname")
      this.$axios(`http://localhost:8080/teacherSuveryexamsAll/${username}`,{headers}).then(res => {
        debugger
        this.pagination.records = res.data.data;
        console.log(JSON.stringify( this.pagination.records))
      }).catch(error => {});
    },

    handleSizeChange(val) {
      this.pagination.size = val;
      this.getAnswerInfo();
    },

    handleCurrentChange(val) {
      this.pagination.current = val;
      this.getAnswerInfo();
    },
    checklink(examCode) {

    console.log("examCode ->" + examCode);
    debugger
    this.dialogVisible = true
    this.mExamCode = examCode
    this.mUrl = `http://localhost:8088/#/anonymousanswer?examCode=${this.mExamCode}&theuseremaill=yzhou111%40gmail.com`
    var i = 0


    },
    mCopy(){
      const el = document.createElement('textarea')
     el.value = this.mUrl
  el.setAttribute('readonly', '')
  el.style.position = 'absolute'
  el.style.left = '-9999px'
  document.body.appendChild(el)
  el.select()
  document.execCommand('copy')
  document.body.removeChild(el)
      this.dialogVisible = false
      this.mExamCode = ""
    }
  }
};
</script>
<style lang="less" scoped>
.all {
  padding: 0px 40px;
  .page {
    margin-top: 20px;
    display: flex;
    justify-content: center;
    align-items: center;
  }
  .edit {
    margin-left: 20px;
  }
  .el-table tr {
    background-color: #dd5862 !important;
  }
}
.el-table .warning-row {
  background: #000 !important;
}

.el-table .success-row {
  background: #dd5862;
}
</style>
