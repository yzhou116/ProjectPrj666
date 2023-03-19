
<template>
  <div class="all">
    <el-table :data="pagination.records" border>
      <el-table-column fixed="left" prop="teacherName" label="Name" width="180"></el-table-column>
      <el-table-column prop="institute" label="College" width="200"></el-table-column>
      <el-table-column prop="sex" label="Sex" width="120"></el-table-column>
      <el-table-column prop="tel" label="Contact" width="120"></el-table-column>
  <!--     <el-table-column prop="email" label="Password" width="120"></el-table-column> -->
      <el-table-column prop="email" label="Email" width="120"></el-table-column>
      <el-table-column prop="cardId" label="ID" width="120"></el-table-column>
      <el-table-column prop="type" label="Position" width="120"></el-table-column>
      <el-table-column fixed="right" label="Edit" width="150">
        <template slot-scope="scope">
        <!--   <el-button @click="checkGrade(scope.row.teacherId)" type="primary" size="small">Edit</el-button>
          <el-button @click="deleteById(scope.row.teacherId)" type="danger" size="small">Delete</el-button> -->
          <el-button @click="checkGrade(scope.row.email)" type="primary" size="small">Edit</el-button>
          <el-button @click="deleteById(scope.row.email)" type="danger" size="small">Delete</el-button>
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
      title="Edit User Info"
      :visible.sync="dialogVisible"
      width="30%"
      :before-close="handleClose">
      <section class="update">
        <el-form ref="form" :model="form" label-width="80px">
          <el-form-item label="Name">
            <el-input v-model="form.teacherName"></el-input>
          </el-form-item>
          <el-form-item label="College">
            <el-input v-model="form.institute"></el-input>
          </el-form-item>
          <el-form-item label="Sex">
            <el-input v-model="form.sex"></el-input>
          </el-form-item>
          <el-form-item label="Phone">
            <el-input v-model="form.tel"></el-input>
          </el-form-item>
          <el-form-item label="Password">
            <el-input v-model="form.pwd"></el-input>
          </el-form-item>
          <el-form-item label="Email">
            <el-input v-model="form.email"></el-input>
          </el-form-item>
          <el-form-item label="ID">
            <el-input v-model="form.cardId"></el-input>
          </el-form-item>
          <el-form-item label="Position">
            <el-input v-model="form.type"></el-input>
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
      orginalEmail : "",
      dialogVisible: false, 
    /*   form: {

      },  */
      form: {
        teacherName : "",
        institute : "",
        sex : "",
        tel : "",
        cardId : "",
        type : "",
        pwd : ""
},
     
    };
  },
  created() {
    this.getTeacherInfo();
  },
  methods: {
    getTeacherInfo() {
      let tokenStr = this.$session.get('jwt')
      const headers = 
      {
      
       'Authorization' : 'Bearer ' + tokenStr
      }
///api/teachers/${this.pagination.current}/${this.pagination.size}
      this.$axios(`http://localhost:8080/teachers/${this.pagination.current}/${this.pagination.size}`,{headers}).then(res => {
        debugger
        this.pagination = res.data.data;
      }).catch(error => {});
    },
   
    handleSizeChange(val) {
      this.pagination.size = val;
      this.getTeacherInfo();
    },
   
    handleCurrentChange(val) {
      this.pagination.current = val;
      this.getTeacherInfo();
    },
    checkGrade(orginalEmail) { 
      this.dialogVisible = true
      this.orginalEmail = orginalEmail
     /*  this.$axios(`/api/teacher/${teacherId}`).then(res => {
        this.form = res.data.data
      }) */
    },
    deleteById(username) { 
      let tokenStr = this.$session.get('jwt')
      const headers = 
      {
      
       'Authorization' : 'Bearer ' + tokenStr
      }
      this.$axios({
          /* url: `/api/teacher/${teacherId}`, */
          url: `http://localhost:8080/teacher/${username}`,
          method: 'delete',
          headers
        }).then(res => {
          this.getTeacherInfo()
        })

    /*   this.$confirm("Do you really want to delete teacher? You can not do backup","Warning",{
        confirmButtonText: 'Delete it !',
        cancelButtonText: 'Leave it! ',
        type: 'danger'
      }).then(()=> { 
        debugger
      
      }).catch(() => {

      }) */
    },
    submit() { 
      this.dialogVisible = false
      let tokenStr = this.$session.get('jwt')
      const headers = 
      {
      
       'Authorization' : 'Bearer ' + tokenStr
      }
      debugger
      this.$axios({
       /*  url: '/api/teacher', */
       url: `http://localhost:8080/teacher/${this.orginalEmail}`,
        method: 'put',
        data: {
          ...this.form
        },
        headers
      }).then(res => {
        console.log(res)
        if(res.data.code ==200) {
          this.$message({
            message: 'Update',
            type: 'success'
          })
        }
        this.getTeacherInfo()
      })
    },
    handleClose(done) { 
      this.$confirm('Confirm Close')
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
