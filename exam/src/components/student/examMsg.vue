
<template>
  <div id="msg">
    <div class="title">
      <span>Exam List</span>
      <span>/  {{examData.source}}</span>
    </div>
    <div class="wrapper">
      <ul class="top">
        <li class="example">{{examData.source}}</li>
        <li><i class="iconfont icon-pen-"></i></li>
        <li><i class="iconfont icon-share"></i></li>
        <li class="right">
          <div>
            <span class="count">Total Score</span>
            <span class="score">{{score[0]+score[1]+score[2]}}</span>
          </div>
        </li>
      </ul>
      <ul class="bottom">
        <li>Update {{examData.examDate}}</li>
        <li>From {{examData.institute}}</li>
        <li class="btn">{{examData.type}}</li>
        <li class="right"><el-button @click="toAnswer(examData.examCode)">Start Answer</el-button></li>
       
      </ul>
 <!--      <div v-show="visible" class="right"><el-button  @click="Subscribe(examData.examCode)">Subscribe</el-button></div> -->
      <ul class="info">
        <li @click="dialogVisible = true"><a href="javascript:;"><i class="iconfont icon-info"></i>Student Needs to Know</a></li>
      </ul>
    </div>
    <div class="content">
      <el-collapse v-model="activeName" >
        <el-collapse-item class="header" name="0">
          <template slot="title" class="stitle" >
            <div class="title">
              <span>{{examData.source}}</span><i class="header-icon el-icon-info"></i>
              <span class="time">{{examData.totalScore}}Mins / {{examData.totalTime}}Mins</span>
              <el-button type="primary" size="small">Please see the details</el-button>
            </div>
          </template>
          <el-collapse class="inner">
            <el-collapse-item>
              <template slot="title" name="1">
                <div class="titlei">MultiQuestion (Total{{topicCount[0]}}Questions Total{{score[0]}}Score)</div>
              </template>
              <div class="contenti">
                <ul class="question" v-for="(list, index) in topic[1]" :key="index">
                  <li>{{index+1}}. {{list.question}} {{list.score}}Score</li>
                </ul>
              </div>
            </el-collapse-item>
            <el-collapse-item>
              <template slot="title" name="2">
                <div class="titlei">Fill Blank (Total{{topicCount[1]}}Questions  Total{{score[1]}}Score)</div>
              </template>
              <div class="contenti">
                <ul class="question" v-for="(list, index) in topic[2]" :key="index">
                  <li>{{topicCount[0]+index+1}}.{{list.question}}  {{list.score}}Score</li>
                </ul>
              </div>
            </el-collapse-item>
            <el-collapse-item>
              <template slot="title" name="3">
                <div class="titlei">True Or False (Total{{topicCount[2]}}Questions Total{{score[2]}}Score)</div>
              </template>
              <div class="contenti">
                <ul class="question" v-for="(list, index) in topic[3]" :key="index">
                  <li>{{topicCount[0]+topicCount[1]+index+1}}. {{list.question}} {{list.score}}Score</li>
                </ul>
              </div>
            </el-collapse-item>
          </el-collapse>
        </el-collapse-item>
        
      </el-collapse>
    </div>

    <el-dialog
      title="Need to know"
      :visible.sync="dialogVisible"
      width="30%">
      <span>{{examData.tips}}</span>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">Get it!</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
export default {
  data() {
    return {
      dialogVisible: false, 
      activeName: '0',  
      topicCount: [],
      score: [],  
      examData: { 
        // source: null,
        // totalScore: null,
      },
      topic: {  

      },
      visible : true,
      issurvey : false
      
    }
  },
  mounted() {
    this.init()
  },
  methods: {

    init() {
      let examCode = this.$route.query.examCode
    //  this.visible = this.$route.query.isSub 
     // console.log("the value from previous page " + this.$route.query.isSub )
     // console.log("the value from previous page " +  this.isSuber )
      
      // /api/exam/${examCode}
      let tokenStr = this.$session.get('jwt')
      const headers = 
      {
      
       'Authorization' : 'Bearer ' + tokenStr
      }
     var username = this.$cookies.get("cname")
      this.$axios(`http://localhost:8080/exam/${examCode}/${username}`,{headers}).then(res => { 
  
        res.data.data.examDate = res.data.data.examDate.substr(0,10)
        this.issurvey = res.data.data.issurvey
        this.examData = { ...res.data.data}
        let paperId = this.examData.paperId
        ///api/paper/${paperId}
        this.$axios(`http://localhost:8080/paper/${paperId}`,{headers}).then(res => {  
          this.topic = {...res.data}
          let keys = Object.keys(this.topic) 
          keys.forEach(e => {
            let data = this.topic[e]
            this.topicCount.push(data.length)
            let currentScore = 0
            for(let i = 0; i< data.length; i++) { 
              currentScore += data[i].score
            }
            this.score.push(currentScore) 
          })
        })
      })
    },
    toAnswer(id) {
      if(!this.issurvey){
        this.$router.push({path:"/answer",query:{examCode: id}})
      }else{
        var username = this.$cookies.get("cname")
        this.$router.push({path:"/anonymousanswer",query:{examCode: id, theuseremaill : username}})
      }
      
    },



  }
}
</script>

<style lang="less" scoped>
.bottom {
  .right{
    .el-button{
      color: #409EFF;
      border-color: #c6e2ff;
      background-color: #ecf5ff;
    }
  }
}
.right {
  margin-left: auto;
}
.inner .contenti .question {
  margin-left: 40px;
  color: #9a9a9a;
  font-size: 14px;
}
.content .inner .titlei {
  margin-left: 20px;
  font-size: 16px;
  color: #88949b;
  font-weight: bold;
}
.content .title .time {
  font-size: 16px;
  margin-left: 420px;
  color: #999;
}
.content .stitle {
  background-color: #0195ff;
}
.content .title span {
  margin-right: 10px;
}
#msg .content .title {
  font-size: 20px;
  margin: 0px;
  display: flex;
  align-items: center;
}
.content {
  margin-top: 20px;
  background-color: #fff;
}
.content .header {
  padding: 10px 30px;
}
.wrapper .info {
  margin: 20px 0px 0px 20px;
  border-top: 1px solid #eee;
  padding: 20px 0px 10px 0px;
}
.wrapper .info a {
  color: #88949b;
  font-size: 14px;
}
.wrapper .info a:hover {
  color: #0195ff;
}
.wrapper .bottom .btn {
  cursor: pointer;
  padding: 5px 10px;
  border: 1px solid #88949b;
  border-radius: 4px;
} 
.wrapper .bottom {
  display: flex;
  margin-left: 20px;
  color: #999;
  font-size: 14px;
  align-items: center;
}
.wrapper .bottom li {
  margin-right: 14px;
}
#msg {
  background-color: #eee;
  width: 980px;
  margin: 0 auto;
}
#msg .title {
  margin: 20px;
}
#msg .wrapper {
  background-color: #fff;
  padding: 10px;
}
.wrapper .top {
  display: flex;
  margin: 20px;
  align-items: center;
}
.wrapper .top .right {
  margin-left: auto;
}
.wrapper .top .example {
  color: #333;
  font-size: 22px;
  font-weight: 700;
}
.wrapper .top li i {
  margin-left: 20px;
  color: #88949b;
}
.wrapper .right .count {
  margin-right: 60px;
  color: #fff;
  padding: 4px 10px;
  background-color: #88949b;
  border-top-left-radius: 4px;
  border-bottom-left-radius: 4px;
  border: 1px solid #88949b;
}
.wrapper .right .score {
  position: absolute;
  left: 103px;
  top: -5px;
  padding: 1px 12px;
  font-size: 20px;
  color: #88949b;
  border: 1px dashed #88949b;
  border-left: none;
  border-top-right-radius: 4px;
  border-bottom-right-radius: 4px;
  font-weight: bold;
}
.wrapper .right div {
  position: relative;
}
</style>
