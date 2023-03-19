
<template>
  <div id="login">
    <div class="bg"></div>
    <el-row class="main-container">
      <el-col :lg="8" :xs="16" :md="10" :span="10">
        <div class="top">
          <i class="iconfont icon-kaoshi"></i><span class="title">Online Exam</span>
        </div>
        <div class="bottom">
          <div class="container">
            <p class="title">Account login</p>
            <el-form :label-position="labelPosition" label-width="80px" :model="formLabelAlign">
              <el-form-item label="Account">
                <el-input v-model.number="formLabelAlign.username" placeholder="Please enter your password"></el-input>
              </el-form-item>
              <el-form-item label="Password">
                <el-input v-model="formLabelAlign.password" placeholder="Please enter your password" type='password'></el-input>
              </el-form-item>
              
              <el-form-item label="Type">
                <el-radio-group v-model="formLabelAlign.type"> 
                  <el-radio v-model="formLabelAlign.subcriber" label="Subscriber" />
                <el-radio v-model="formLabelAlign.publisher" label="Publisher" />
                <el-radio v-model="formLabelAlign.admin" label="Admin" />
                </el-radio-group>
               
              </el-form-item>
              
              <div class="submit">
                <el-button type="primary" class="row-login" @click="login()">Login</el-button>
                <el-button type="primary" @click="RegisterUser()">Register</el-button>
              
              </div>
        
              <el-dialog
             title="Register Form"
             :visible.sync="displayRegister"
      width="30%"
      >
      <section class="update">
        <el-form ref="form" :model="registerForm" label-width="100px">
          <el-form-item label="Username">
            <el-input  placeholder="Please enter your Email" v-model="registerForm.username"></el-input>
          </el-form-item>
          <el-form-item label="Password">
            <el-input type='password'  placeholder="Please enter your password" v-model="registerForm.password"></el-input>
          </el-form-item>
          <el-form-item label="Confirm Password">
            <el-input type='password'  placeholder="Please enter your again" v-model="registerForm.passwordConfirm"></el-input>
          </el-form-item>

          <el-form-item label="Name">
            <el-input  placeholder="Please enter your name" v-model="registerForm.name"></el-input>
          </el-form-item>
       <!--    <el-form-item label="Phone"> :before-close="handleClose"
            <el-input v-model="form.tel"></el-input>
          </el-form-item>
          <el-form-item label="Password">
            <el-input v-model="form.pwd"></el-input>
          </el-form-item>
          <el-form-item label="ID">
            <el-input v-model="form.cardId"></el-input>
          </el-form-item>
          <el-form-item label="Position">
            <el-input v-model="form.type"></el-input>
          </el-form-item> -->
        </el-form>
      </section>
      <span slot="footer" class="dialog-footer">
        <el-button @click="displayRegister = false">Cancel</el-button>
        <el-button type="primary" @click="register()">Confirm</el-button>
      </span>
    </el-dialog>
            </el-form>
           

          </div>
        </div>
      </el-col>
    </el-row>
    <el-row class="footer">
      <el-col>
        <p class="msg2">Some text in here<a href="" target="_blank">Some text in here</a></p>
      </el-col>
    </el-row>
    <section class="remind">
      <span>Account number: yzhou120@gmail.com</span>
      <span>Account number: yzhou116@gmail.com</span>
      <span>All password: Sd!4888202</span>
    </section>
  </div>
</template>

<script>
import  Vue from 'vue'
import {mapState} from 'vuex'
import VueSession from 'vue-session'
Vue.use(VueSession)
export default {
  name: "login",
  data() {
    return {
      role: 2,
      labelPosition: 'left',
      formLabelAlign: {
       /*  username: '20154084',
        password: '123456', */
        username: 'yzhou120@gmail.com',
        password: 'Sd!4888202',
        type :""
      },
      displayRegister: false,
      registerForm : {
        username : "",
        password : "",
        passwordConfirm : "",
        name : ""
      }
    }
  },
  methods: {
  
    //Change every rul to http://localhost:8080/ without api, don't just delete it, need to deploy it after
    login() {
      console.log("Login -------");
   
      this.$axios({
        url: 'http://localhost:8080/authenticatet',
        method: 'post',
        data: {
          ...this.formLabelAlign
        }
      }).then(res=>{
        //let resData = res.data.data
        let resData = res.data.token
        console.log(JSON.stringify(res));
        console.log(resData);
    
        if(resData != null) {
          this.$session.start()
          this.$session.set('jwt', resData)
          console.log(JSON.stringify(resData));
        //  debugger
       //   let instance = this.$axios.create();
       //   instance.defaults.headers.common['Authorization'] =  `Bearer ${this.$session.get('jwt')}`;
        //  this.$axios.defaults.headers.common['Authorization'] = `Bearer ${this.$session.get('jwt')}`;
          console.log("this is session token " + this.$session.get('jwt'));
          console.log("User name" + res.data.username)
          // //Code Comment:find out the type of the user and assign user to the right page.
   
          switch(this.formLabelAlign.type){
            case "Subscriber":
          /*   this.$cookies.set("cname", resData.studentName)
              this.$cookies.set("cid", resData.studentId) */
              this.$cookies.set("cname", this.formLabelAlign.username)
              this.$cookies.set("cid", this.formLabelAlign.username)
             this.$router.push({path: '/student'})
          
           
              break
            case "Admin": 
              this.$cookies.set("cname", this.formLabelAlign.username)
              this.$cookies.set("cid", this.formLabelAlign.username)
              this.$cookies.set("role", 0)
              this.$router.push({path: '/index' }) 
         //   this.$router.push({path: '/adminindex'})
              break
            case "Publisher": 
              this.$cookies.set("cname", this.formLabelAlign.username)
              this.$cookies.set("cid", this.formLabelAlign.username)
              this.$cookies.set("role", 1)
              this.$router.push({path: '/index' }) 
              break

          }

       /*    switch(resData.role) {
            case "0": 
              this.$cookies.set("cname", resData.adminName)
              this.$cookies.set("cid", resData.adminId)
              this.$cookies.set("role", 0)
              this.$router.push({path: '/index' }) //跳转到首页
              break
            case "1":
              this.$cookies.set("cname", resData.teacherName)
              this.$cookies.set("cid", resData.teacherId)
              this.$cookies.set("role", 1)
              this.$router.push({path: '/index' }) //跳转到教师用户
              break
            case "2": 
              this.$cookies.set("cname", resData.studentName)
              this.$cookies.set("cid", resData.studentId)
              this.$router.push({path: '/student'})
              break
          } */
        }
        if(resData == null || res.data.code==401) {
          this.$message({
          
            type: 'error',
            message: 'Account number or password wrong'
          })
        }
      }).catch(() => {
        this.$message({
          type: 'error',
          message: 'Account number or password wrong'
        })
})
    },
    clickTag(key) {
      this.role = key
    },
    RegisterUser(){
      if(this.displayRegister == true){
        this.displayRegister = false
      }else{
        this.displayRegister = true
      }
    
    },
    register(){
      this.$axios({
        url: 'http://localhost:8080/register',
        method: 'post',
        data: {
        ...this.registerForm
        }
      }).then(res=>{
        if(res.data.code == 200){
          this.registerForm = {
            username : "",
            password : "",
            passwordConfirm : "",
            name : ""
      }
      this.$message({
            message: 'Registeration Successful',
            type: 'success'
          })

           console.log("Rsgiter res ->" + JSON.stringify(res))
        }
      
           this.displayRegister = false

      }).catch(error => {
        this.registerForm = {
        username : "",
        password : "",
        passwordConfirm : "",
        name : ""
      }
      this.$message({
            message: 'Opps, something is wrong',
            type: 'Error'
          })
    
      console.log("Something is wrong with register" + error)
      this.displayRegister = false
      })
    }
  },
  computed: mapState(["userInfo"]),
  mounted() {

  }
}
</script>

<style lang="less" scoped>
.remind {
  border-radius: 4px;
  padding: 10px 20px;
  display: flex;
  position: fixed;
  right: 20px;
  bottom: 50%;
  flex-direction: column;
  color: #606266;
  background-color: #fff;
  border-left: 4px solid #409eff;
  box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19)
}
.container {
  margin-bottom: 32px;
}
.container .el-radio-group {
  margin: 30px 0px;
}
a:link {
  color:#ff962a;
  text-decoration:none;
}
#login {
  font-size: 14px;
  color: #000;
  background-color: #fff;
}
#login .bg {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  overflow-y: auto;
  height: 100%;
  background: url('../../assets/img/logbg.jpg')center top / cover no-repeat;
  background-color: #b6bccdd1 !important;
}
#login .main-container {
  display: flex;
  justify-content: center;
  align-items: center;
}
#login .main-container .top {
  margin-top: 100px;
  font-size: 30px;
  color: #ff962a;
  display: flex;
  justify-content: center;
}
#login .top .icon-kaoshi {
  font-size: 80px;
}
#login .top .title {
  margin-top: 20px;
}
#login .bottom {
  display:flex;
  justify-content: center;
  background-color:#fff;
  border-radius: 5px;
  box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);
}
#login .bottom .title {
  text-align: center;
  font-size: 30px;
}
.bottom .container .title {
  margin: 30px 0px;;
}
.bottom .submit .row-login {
  width: 100%;
  background-color: #04468b;
  border-color: #04468b;
  margin: 20px 0px 10px 0px;
  padding: 15px 20px;
}
.bottom .submit {
  display: flex;
  justify-content: center;
}
.footer {
  margin-top: 50px;
  text-align: center;
}
.footer .msg1 {
  font-size: 18px;
  color: #fff;
  margin-bottom: 15px;
}
.footer .msg2 {
  font-size: 14px;
  color: #e3e3e3;
  margin-top: 70px;
}
.bottom .options {
  margin-bottom: 40px;
  color: #ff962a;
  display: flex;
  justify-content: space-between;
}
.bottom .options > a {
  color: #ff962a;
}
.bottom .options .register span:nth-child(1) {
  color: #8C8C8C;
}
</style>
