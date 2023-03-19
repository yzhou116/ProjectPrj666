
<template>
  <div id="myExam">
    <div class="title">My Exam</div>
    <div class="wrapper">
      <ul class="top">
        <li class="order">Exam List</li>
        <li class="search-li"><div class="icon"><input type="text" placeholder="Exam Name" class="search" v-model="key"><i class="el-icon-search"></i></div></li>
        <li><el-button type="primary" @click="search()">Search Exam</el-button></li>
      </ul>
      <ul class="paper" v-loading="loading">
        <li class="item" v-for="(item,index) in pagination.records" :key="index">
          <h4 @click="toExamMsg(item.examCode)">{{item.source}}</h4>
          <p class="name">{{item.source}}-{{item.description}}</p>
          <div class="info">
            <i class="el-icon-loading"></i><span>{{item.examDate.substr(0,10)}}</span>
            <i class="iconfont icon-icon-time"></i><span v-if="item.totalTime != null">Limit {{item.totalTime}}Mins</span>
            <i class="iconfont icon-fenshu"></i><span>Total{{item.totalScore}}Score</span>
          </div>
        </li>
      </ul>
      <div class="pagination">
        <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="pagination.current"
          :page-sizes="[6, 10, 20, 40]"
          :page-size="pagination.size"
          layout="total, sizes, prev, pager, next, jumper"
          :total="pagination.total">
        </el-pagination>
      </div>
    </div>
  </div>
</template>

<script>
import myFooter from "@/components/student/myFooter"
import VueResource from 'vue-resource'
import Vuex from 'vuex'
import  Vue from 'vue'
Vue.use(VueResource);
Vue.use(Vuex);
export default {
  // name: 'myExam'
  data() {
    return {
      loading: false,
      key: null, 
      allExam: null, 
      pagination: { 
        current: 1, 
        total: null, 
        size: 6 
      }
    }
  },
  created() {
    this.getExamInfo()
    this.loading = true
  },
  // watch: {
    
  // },
  methods: {
  
 async   getExamInfo() {
   
    console.log("exam token " + this.$session.get('jwt'))
  let tokenStr = this.$session.get('jwt')
  const headers = 
      {
      
       'Authorization' : 'Bearer ' + tokenStr
  }
; 
//  debugger;
  //TODO: import axios again in this file and try to add header
    /*  
     ,
      {
      headers: {
        'Authorization': `Bearer ${tokenStr}`
      }
      } */
 /*  axios(
    { method: 'get', url: api + '/api/user/getUserInfo?UserId=1', headers: { 'Authorization': 'Bearer ' + accessToken } }) */
      ///api/exams/${this.pagination.current}/${this.pagination.size}
/*   this.$axios(  
        `http://localhost:8080/exams/${this.pagination.current}/${this.pagination.size}`,
        {headers}
      ).then(res => {
        this.pagination = res.data.data
        this.loading = false
        console.log(this.pagination)
      }).catch(error => {
        console.log(error)
      })  */
      var username = this.$cookies.get("cname")
/*    this.$axios(  
        `http://localhost:8080/exams/${this.pagination.current}/${this.pagination.size}/${username}`,
        {headers}
      ).then(res => {
        this.pagination = res.data.data
        this.loading = false
        console.log(this.pagination)
      }).catch(error => {
        console.log(error)
      }) */
      
      
      this.$axios(`http://localhost:8080/exams/${this.pagination.current}/${this.pagination.size}/${username}`,
        {headers}).then(res => {
        this.pagination = res.data.data
        this.loading = false
        console.log(this.pagination)
      }).catch(error => {
        console.log(error)
      })

/* const data = { 
            name: "abc",
            age : "123124"
          };
      this.$axios.post(`http://localhost:8000/testPost.php`, data,{ headers }).then(res => {
        console.log("tespotst" + JSON.stringify(res));
             
            }) */
   /*  let res = await this.$axios( {
        method: 'get',
        url: `http://localhost:8080/exams/${this.pagination.current}/${this.pagination.size}`,
        headers: { 'Authorization': 'Bearer ' + tokenStr }
       }
      ) */
    //  debugger;
      var i = 0;
    },

    handleSizeChange(val) {
      this.pagination.size = val
      this.getExamInfo()
    },
 
    handleCurrentChange(val) {
      this.pagination.current = val
      this.getExamInfo()
    },

    search() {
      ///api/exams
      this.$axios('http://localhost:8080/exams',
        {headers}).then(res => {
        if(res.data.code == 200) {
          let allExam = res.data.data
          let newPage = allExam.filter(item => {
            return item.source.includes(this.key)
          })
          this.pagination.records = newPage
        }
      })
    },

    toExamMsg(examCode) {
      this.$router.push({path: '/examPublic', query: {examCode: examCode, isSub : false}})
      console.log(examCode)
    }
  }
}
</script>

<style lang="less" scoped>
.pagination {
  padding: 20px 0px 30px 0px;
  .el-pagination {
    display: flex;
    justify-content: center;
  }
}
.paper {
  h4 {
    cursor: pointer;
  }
}
.paper .item a {
  color: #000;
}
.wrapper .top .order {
  cursor: pointer;
}
.wrapper .top .order:hover {
  color: #0195ff;
  border-bottom: 2px solid #0195ff;
}
.wrapper .top .order:visited {
  color: #0195ff;
  border-bottom: 2px solid #0195ff;
}
.item .info i {
  margin-right: 5px;
  color: #0195ff;
}
.item .info span {
  margin-right: 14px;
}
.paper .item {
  width: 380px;
  border-radius: 4px;
  padding: 20px 30px;
  border: 1px solid #eee;
  box-shadow: 0 0 4px 2px rgba(217,222,234,0.3);
  transition: all 0.6s ease;
}
.paper .item:hover {
  box-shadow: 0 0 4px 2px rgba(140, 193, 248, 0.45);
  transform: scale(1.03);
}
.paper .item .info {
  font-size: 14px;
  color: #88949b;
}
.paper .item .name {
  font-size: 14px;
  color: #88949b;
}
.paper * {
  margin: 20px 0;
}
.wrapper .paper {
  display: flex;
  justify-content: space-around;
  flex-wrap: wrap;
}
.top .el-icon-search {
  position: absolute;
  right: 10px;
  top: 10px;
}
.top .icon {
  position: relative;
}
.wrapper .top {
  border-bottom: 1px solid #eee;
  margin-bottom: 20px;
}
#myExam .search-li {
  margin-left: auto;
}
.top .search-li {
  margin-left: auto;
}
.top li {
  display: flex;
  align-items: center;
}
.top .search {
  margin-left: auto;
  padding: 10px;
  border-radius: 4px;
  border: 1px solid #eee;
  box-shadow: inset 0 1px 1px rgba(0,0,0,.075);
  transition: border-color ease-in-out .15s,box-shadow ease-in-out .15s;
}
.top .search:hover {
  color: #0195ff;
  border-color: #0195ff;
}
.wrapper .top {
  display: flex;
}
.wrapper .top li {
  margin: 20px;
}
#myExam {
  width: 980px;
  margin: 0 auto;
}
#myExam .title {
  margin: 20px;
}
#myExam .wrapper {
  background-color: #fff;
}
</style>
