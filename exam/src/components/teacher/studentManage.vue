
<template>
  <div class="all">
    <el-table :data="pagination.records" border>
      <el-table-column fixed="left" prop="studentName" label="Name" width="180"></el-table-column>
      <el-table-column prop="institute" label="College" width="200"></el-table-column>
      <el-table-column prop="major" label="Course" width="200"></el-table-column>
      <el-table-column prop="grade" label="Year" width="200"></el-table-column>
      <el-table-column prop="clazz" label="Class" width="100"></el-table-column>
      <el-table-column prop="sex" label="Sex" width="120"></el-table-column>
      <el-table-column prop="tel" label="Contact" width="120"></el-table-column>
      <el-table-column fixed="right" label="Edit" width="150">
        <template slot-scope="scope">
          <el-button @click="checkGrade(scope.row.studentId)" type="primary" size="small">Edit</el-button>
          <el-button @click="deleteById(scope.row.studentId)" type="danger" size="small">Delete</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
      :current-page="pagination.current"
      :page-sizes="[6, 10]"
      :page-size="pagination.size"
      layout="total, sizes, prev, pager, next, jumper"
      :total="pagination.total"
      class="page">
    </el-pagination>

    <el-dialog
      title="Edit Exam"
      :visible.sync="dialogVisible"
      width="30%"
      :before-close="handleClose">
      <section class="update">
        <el-form ref="form" :model="form" label-width="80px">
          <el-form-item label="Name">
            <el-input v-model="form.studentName"></el-input>
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
          <el-form-item label="Sex">
            <el-input v-model="form.sex"></el-input>
          </el-form-item>
          <el-form-item label="Phone">
            <el-input v-model="form.tel"></el-input>
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
      pagination: {

        current: 1,
        total: null, 
        size: 6, 
      },
      dialogVisible: false, 
      form: {}, 
    };
  },
  created() {
    this.getStudentInfo();
  },
  methods: {
    getStudentInfo() {
     
      ///api/students/${this.pagination.current}/${this.pagination.size}
      let tokenStr = this.$session.get('jwt')
      const headers = 
      {
      
       'Authorization' : 'Bearer ' + tokenStr
      }
      this.$axios(`http://localhost:8080/students/${this.pagination.current}/${this.pagination.size}`,{headers}).then(res => {
        this.pagination = res.data.data;
      }).catch(error => {});
    },
   
    handleSizeChange(val) {
      this.pagination.size = val;
      this.getStudentInfo();
    },
  
    handleCurrentChange(val) {
      this.pagination.current = val;
      this.getStudentInfo();
    },
    checkGrade(studentId) { 
      this.dialogVisible = true
      ///api/student/${studentId}
      this.$axios(`http://localhost:8080/students/${studentId}`).then(res => {
        this.form = res.data.data
      })
    },
    deleteById(studentId) { 
      let tokenStr = this.$session.get('jwt')
      const headers = 
      {
      
       'Authorization' : 'Bearer ' + tokenStr
      }
      this.$confirm("Do you want to delete it, you can not get it back","Warning",{
        confirmButtonText: 'Yes',
        cancelButtonText: 'No',
        type: 'danger'
      }).then(()=> { 
        this.$axios({
         /*  url: `/api/student/${studentId}`, */
         url: `http://localhost:8080/student/${studentId}`,
          method: 'delete',
          headers
        }).then(res => {
          this.getStudentInfo()
        })
      }).catch(() => {

      })
    },
    submit() { 
      let tokenStr = this.$session.get('jwt')
      const headers = {
        headers:  {
      
      'Authorization' : 'Bearer ' + tokenStr
     }
    }
    
      this.dialogVisible = false
      this.$axios({
       /*  url: '/api/student', */
       url: `http://localhost:8080/student`,
        method: 'put',
        data: {
          ...this.form
        },
        headers
      }).then(res => {
        console.log(res)
        if(res.data.code ==200) {
          this.$message({
            message: 'Updated',
            type: 'success'
          })
        }
        this.getStudentInfo()
      })
    },
    handleClose(done) { 
      this.$confirm('Do you want to close?')
        .then(_ => {
          done();
        }).catch(_ => {});
    },
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
