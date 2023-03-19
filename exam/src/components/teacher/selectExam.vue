
<template>
  <div class="exam">
    <el-table :data="pagination.records" border>
      <el-table-column fixed="left" prop="source" label="Exam Name" width="180"></el-table-column>
      <el-table-column prop="description" label="Info" width="200"></el-table-column>
      <el-table-column prop="institute" label="College" width="120"></el-table-column>
      <el-table-column prop="major" label="Course" width="200"></el-table-column>
      <el-table-column prop="grade" label="Year" width="100"></el-table-column>
      <el-table-column prop="examDate" label="Exam Date" width="120"></el-table-column>
      <el-table-column prop="totalTime" label="Time" width="120"></el-table-column>
      <el-table-column prop="totalScore" label="Total Score" width="120"></el-table-column>
      <el-table-column prop="type" label="Exam Type" width="120"></el-table-column>
      <el-table-column prop="tips" label="Student hint" width="400"></el-table-column>
      <el-table-column fixed="right" label="Edit" width="150">
        <template slot-scope="scope">
          <el-button id="edit" @click="edit(scope.row.examCode)" type="primary" size="small">Edit</el-button>
          <el-button id="delete" @click="deleteRecord(scope.row.examCode)" type="danger" size="small">Delete</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
      :current-page="pagination.current"
      :page-sizes="[4, 8, 10, 20]"
      :page-size="pagination.size"
      layout="total, sizes, prev, pager, next, jumper"
      :total="pagination.total" class="page">
    </el-pagination>
  <!--   :before-close="handleClose" -->
    <el-dialog
      title="Edit exam information"
      :visible.sync="dialogVisible"
      width="30%"
      >
      <section class="update">
        <el-form ref="form" :model="form" label-width="80px">
          <el-form-item label="Exam">
            <el-input v-model="form.source"></el-input>
          </el-form-item>
          <el-form-item label="Desc">
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
              <el-date-picker type="date" placeholder="Choose Date" v-model="form.examDate" style="width: 100%;"></el-date-picker>
            </el-col>
          </el-form-item>
          <el-form-item label="Time">
            <el-input v-model="form.totalTime"></el-input>
          </el-form-item>
          <el-form-item label="Mark">
            <el-input v-model="form.totalScore"></el-input>
          </el-form-item>
          <el-form-item label="Type">
            <el-input v-model="form.type"></el-input>
          </el-form-item>
          <el-form-item label="Hint">
            <el-input type="textarea" v-model="form.tips"></el-input>
          </el-form-item>
        </el-form>
      </section>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">Cancel</el-button>
        <el-button type="primary" @click="submit()">Confirm</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
export default {
  data() {
    return {
      form: {}, 
      pagination: { 
        current: 1, 
        total: null, 
        size: 4 
      },
      dialogVisible: false
    }
  },
  created() {
    this.getExamInfo()
  },
  methods: {
    edit(examCode) { 
      this.dialogVisible = true
      let tokenStr = this.$session.get('jwt')
      const headers = 
      {
      
       'Authorization' : 'Bearer ' + tokenStr
      }
      // /api/exam/${examCode}
    /*   this.$axios(`http://localhost:8080/exam/${examCode}`,{headers}).then(res => { 
        if(res.data.code == 200) {
          this.form = res.data.data
        }
      }) */
      var username = this.$cookies.get("cname")
      this.$axios(`http://localhost:8080/exam/${examCode}/${username}`,{headers}).then(res => {  
        if(res.data.code == 200) {
          this.form = res.data.data
        }
        ///api/paper/${paperId}
    
      })

    },
    handleClose(done) { 
     /*  this.$confirm('Close it? ')
        .then(_ => {
          done();
        }).catch(_ => {}); */
    },
    submit() { 
      this.dialogVisible = false
      let tokenStr = this.$session.get('jwt')
      const headers = 
      {
      
       'Authorization' : 'Bearer ' + tokenStr
      }
      this.$axios({
       /*  url: '/api/exam', */
       url: `http://localhost:8080/exam`,
        method: 'put',
        data: {
          ...this.form
        },
        headers
      }).then(res => {
        if(res.data.code == 200) {
          this.$message({ 
            message: 'Updated',
            type: 'success'
          })
        }
        this.getExamInfo()
      })
    },
    deleteRecord(examCode) {
      let tokenStr = this.$session.get('jwt')
      const headers = 
      {
      
       'Authorization' : 'Bearer ' + tokenStr
      }
      this.$confirm("Do you really want to delete this record !!!","Delete hint",{
        confirmButtonText: 'Yes',
        cancelButtonText: 'No Keep it',
        type: 'danger'
      }).then(()=> { 
        this.$axios({
        /*   url: `/api/exam/${examCode}`, */
        url: `http://localhost:8080/exam/${examCode}`,
          method: 'delete',
          headers
        }).then(res => {
          this.getExamInfo()
        })
      }).catch(() => {

      })
    },
    getExamInfo() { 
      ///api/exams/${this.pagination.current}/${this.pagination.size}
      let tokenStr = this.$session.get('jwt')
      const headers = 
      {
      
       'Authorization' : 'Bearer ' + tokenStr
      }

      console.log("select exam token-> " + tokenStr);
      var username = this.$cookies.get("cname")
      this.$axios( `http://localhost:8080/teacherexams/${this.pagination.current}/${this.pagination.size}/${username}`,{headers}).then(res => {
          this.pagination = res.data.data
      }).catch(error => {
        console.log(error)
      })
     /*  this.$axios(`http://localhost:8080/exams/${this.pagination.current}/${this.pagination.size}`,{headers}).then(res => {
        this.pagination = res.data.data
      }).catch(error => {
      }) */
    },

    handleSizeChange(val) {
      this.pagination.size = val
      this.getExamInfo()
    },
 
    handleCurrentChange(val) {
      this.pagination.current = val
      this.getExamInfo()
    },
  },
};
</script>
<style lang="less" scoped>
.exam {
  padding: 0px 40px;
  .page {
    margin-top: 20px;
    display: flex;
    justify-content: center;
    align-items: center;
  }
  .edit{
    margin-left: 20px;
  }
}
#edit{
  margin-left: 40px;
}

#delete{
  margin-left: 30px;
  margin-top: 10px;
}
</style>
