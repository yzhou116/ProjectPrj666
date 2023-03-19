
<template>
  <div id="answer">

     <div class="top">
       <ul class="item">
         <li><i class="iconfont icon-menufold icon20" ref="toggle" @click="slider_flag = !slider_flag"></i></li>
         <li>{{examData.type}}-{{examData.source}}</li>
         <li @click="flag = !flag">
           <i class="iconfont icon-user icon20"></i>
           <div class="msg"  v-if="flag" @click="flag = !flag">
             <p>Name: {{userInfo.name}}</p>
             <p>User ID {{userInfo.id}}</p>
           </div>
         </li>
         <li><i class="iconfont icon-arrLeft icon20"></i></li>
       </ul>
     </div>
     <div class="flexarea">

        <transition name="slider-fade">
          <div class="left" v-if="slider_flag">
            <ul class="l-top">
              <li>
                <a href="javascript:;"></a>
                <span>Current</span>
              </li>
              <li>
                <a href="javascript:;"></a>
                <span>Not</span>
              </li>
              <li>
                <a href="javascript:;"></a>
                <span>Answerd</span>
              </li>
              <li>
                <a href="javascript:;"></a>
                <span>Mark</span>
              </li>
            </ul>
            <div class="l-bottom">
              <div class="item">
                <p>MultiQuestion</p>
                <ul>
                  <li v-for="(list, index1) in topic[1]" :key="index1">
                    <a href="javascript:;" 
                      @click="change(index1)"
                      :class="{'border': index == index1 && currentType == 1,'bg': bg_flag && topic[1][index1].isClick == true}">
                      <span :class="{'mark': topic[1][index1].isMark == true}"></span>
                      {{index1+1}}
                    </a>
                  </li>
                </ul>
              </div>
              <div class="item">
                <p>Fill Blank</p>
                <ul>
                  <li v-for="(list, index2) in topic[2]" :key="index2">
                    <a href="javascript:;" @click="fill(index2)" :class="{'border': index == index2 && currentType == 2,'bg': fillAnswer[index2][3] == true}"><span :class="{'mark': topic[2][index2].isMark == true}"></span>{{topicCount[0]+index2+1}}</a>
                  </li>
                </ul>
              </div>
              <div class="item">
                <p>True False</p>
                <ul>
                  <li v-for="(list, index3) in topic[3]" :key="index3">
                    <a href="javascript:;" @click="judge(index3)" :class="{'border': index == index3 && currentType == 3,'bg': bg_flag && topic[3][index3].isClick == true}"><span :class="{'mark': topic[3][index3].isMark == true}"></span>{{topicCount[0]+topicCount[1]+index3+1}}</a>
                  </li>
                </ul>
              </div>
              <div class="final" @click="commit()">End Test</div>
            </div>
          </div>
        </transition>  

        <transition name="slider-fade">
        <div class="right">
          <div class="title">
            <p>{{title}}</p>
            <i class="iconfont icon-right auto-right"></i>
            <span>Total Questions {{topicCount[0] + topicCount[1] + topicCount[2]}}  <i class="iconfont icon-time"></i>Count Down: <b>{{time}}</b>Mins</span>
          </div>
          <div class="content">
            <p class="topic"><span class="number">{{number}}</span>{{showQuestion}}</p>
            <div v-if="currentType == 1">
              <el-radio-group v-model="radio[index]" @change="getChangeLabel" >
                <el-radio :label="1">{{showAnswer.answerA}}</el-radio>
                <el-radio :label="2">{{showAnswer.answerB}}</el-radio>
                <el-radio :label="3">{{showAnswer.answerC}}</el-radio>
                <el-radio :label="4">{{showAnswer.answerD}}</el-radio>
              </el-radio-group>
              <div class="analysis" v-if="isPractice">
                <ul>
                  <li> <el-tag type="success">Right Answer</el-tag><span class="right">{{reduceAnswer.rightAnswer}}</span></li>
                  <li><el-tag>Answer Explanation</el-tag></li>
                  <li>{{reduceAnswer.analysis == null ? 'No Explanation': reduceAnswer.analysis}}</li>
                </ul>
              </div>
            </div>
            <div class="fill" v-if="currentType == 2">
              <div v-for="(item,currentIndex) in part" :key="currentIndex">
                <el-input placeholder="Please write it here"
                  v-model="fillAnswer[index][currentIndex]"
                  clearable
                  @blur="fillBG">
                </el-input>
              </div>
              <div class="analysis" v-if="isPractice">
                <ul>
                  <li> <el-tag type="success">Right Answer </el-tag><span class="right">{{topic[2][index].answer}}</span></li>
                  <li><el-tag>Question Analysis</el-tag></li>
                  <li>{{topic[2][index].analysis == null ? 'No Analysis': topic[2][index].analysis}}</li>
                </ul>
              </div>
            </div>
            <div class="judge" v-if="currentType == 3">
              <el-radio-group v-model="judgeAnswer[index]" @change="getJudgeLabel" v-if="currentType == 3">
                <el-radio :label="1">Correct</el-radio>
                <el-radio :label="2">Wrong</el-radio>
              </el-radio-group>
              <div class="analysis" v-if="isPractice">
                <ul>
                  <li> <el-tag type="success">Right Answer</el-tag><span class="right">{{topic[3][index].answer}}</span></li>
                  <li><el-tag>Question Analysis</el-tag></li>
                  <li>{{topic[3][index].analysis == null ? 'No Analysis': topic[3][index].analysis}}</li>
                </ul>
              </div>
            </div>
          </div>
          <div class="operation">
            <ul class="end">
              <li @click="previous()"><i class="iconfont icon-previous"></i><span>Previous</span></li>
              <li @click="mark()"><i class="iconfont icon-mark-o"></i><span>Mark</span></li>
              <li @click="next()"><span>Next</span><i class="iconfont icon-next"></i></li>
            </ul>
          </div>
        </div>
        </transition>
     </div> 
  </div>
</template>

<script>
import {mapState} from 'vuex'
export default {
  data() {
    return {
      startTime: null, 
      endTime: null,
      time: null, 
      reduceAnswer:[], 
      answerScore: 0, 
      bg_flag: false, 
      isFillClick: false, 
      slider_flag: true, 
      flag: false, 
      currentType: 1,
      radio: [], 
      title: "Please make the right choice",
      index: 0, 
      userInfo: { 
        name: null,
        id: null
      },
      topicCount: [],
      score: [],  
      examData: { 
        // source: null,
        // totalScore: null,
      },
      topic: {  

      },
      showQuestion: [], 
      showAnswer: {},
      number: 1, 
      part: null, 
      fillAnswer: [[]], 
      judgeAnswer: [], 
      topic1Answer: [],  
      rightAnswer: '',
      isPractice : false
    }
  },
  created() {
    this.getCookies()
    this.getExamData()
    this.showTime()
  },
  methods: {
    getTime(date) { 
      let year = date.getFullYear()
      let month= date.getMonth()+ 1 < 10 ? "0" + (date.getMonth() + 1) : date.getMonth() + 1;
      let day=date.getDate() < 10 ? "0" + date.getDate() : date.getDate();
      let hours=date.getHours() < 10 ? "0" + date.getHours() : date.getHours();
      let minutes=date.getMinutes() < 10 ? "0" + date.getMinutes() : date.getMinutes();
      let seconds=date.getSeconds() < 10 ? "0" + date.getSeconds() : date.getSeconds();
     
      return year+"-"+month+"-"+day+" "+hours+":"+minutes+":"+seconds;
    },
    getCookies() {  
      this.userInfo.name = this.$cookies.get("cname")
      this.userInfo.id = this.$cookies.get("cid")
    },
    calcuScore() { 
      
    },
    getExamData() { 
      let date = new Date()
      this.startTime = this.getTime(date)
      let examCode = this.$route.query.examCode 
     // /api/exam/${examCode}
     let tokenStr = this.$session.get('jwt')
      const headers = 
      {
        
       'Authorization' : 'Bearer ' + tokenStr
      }
 

      var username = this.$cookies.get("cname")
      this.$axios(`http://localhost:8080/exam/${examCode}/${username}`,{headers}).then(res => {  
        this.examData = { ...res.data.data} 
        this.index = 0
        this.time = this.examData.totalScore 
        let paperId = this.examData.paperId
        this.isPractice = this.examData.ispractice
        debugger;
        ///api/paper/${paperId}
        this.$axios(`http://localhost:8080/paper/${paperId}`,{headers}).then(res => {  
          this.topic = {...res.data}
          console.log("this is topic -> " +JSON.stringify(this.topic))
          let reduceAnswer = this.topic[1][this.index]
          this.reduceAnswer = reduceAnswer
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
          let len = this.topicCount[1]
          let father = []
          for(let i = 0; i < len; i++) { 
            let children = [null,null,null,null]
            father.push(children)
          }
          this.fillAnswer = father
          let dataInit = this.topic[1]
          this.number = 1
          this.showQuestion = dataInit[0].question
          this.showAnswer = dataInit[0]
        })
      })
    },
    change(index) { 
      this.index = index
      let reduceAnswer = this.topic[1][this.index]
      this.reduceAnswer = reduceAnswer
      this.isFillClick = true
      this.currentType = 1
      let len = this.topic[1].length
      if(this.index < len) {
        if(this.index <= 0){
          this.index = 0
        }
        console.log(`Total length${len}`)
        console.log(`Current index:${index}`)
        this.title = "Please make right choice"
        let Data = this.topic[1]
        // console.log(Data)
        this.showQuestion = Data[this.index].question 
        this.showAnswer = Data[this.index]
        this.number = this.index + 1
      }else if(this.index >= len) {
        this.index = 0
        this.fill(this.index)
      }
    },
    fillBG() { 
      if(this.fillAnswer[this.index][0] != null) {
        this.fillAnswer[this.index][3] = true
      }
    },
    fill(index) { 
      let len = this.topic[2].length
      this.currentType = 2
      this.index = index
      if(index < len) {
        if(index < 0) {
          index = this.topic[1].length -1
          this.change(index)
        }else {
          console.log(`Total length${len}`)
          console.log(`Current index:${index}`)
          this.title = "Please write your answer on the underscores"
          let Data = this.topic[2]
          console.log(Data)
          this.showQuestion = Data[index].question 
          let part= this.showQuestion.split("()").length -1 
          this.part = part
          this.number = this.topicCount[0] + index + 1
        } 
      }else if(index >= len) {
        this.index = 0
        this.judge(this.index)
      }
    },
    judge(index) { 
      let len = this.topic[3].length
      this.currentType = 3
      this.index = index
      if(this.index < len) {
        if(this.index < 0){
          this.index = this.topic[2].length - 1
          this.fill(this.index)
        }else {
          console.log(`Total length${len}`)
          console.log(`Current index:${this.index}`)
          this.title = "Please make the right choice"
          let Data = this.topic[3]
          console.log(Data)
          this.showQuestion = Data[index].question 
          this.number = this.topicCount[0] + this.topicCount[1] + index + 1
        }
      }else if (this.index >= len) {
        this.index = 0
        this.change(this.index)
      }
    },
    getChangeLabel(val) { 
      this.radio[this.index] = val 
      if(val) {
        let data = this.topic[1]
        this.bg_flag = true
        data[this.index]["isClick"] = true
      }
 
      this.topic1Answer[this.index] = val 
    },
    getJudgeLabel(val) {  
      this.judgeAnswer[this.index] = val
      if(val) {
        let data = this.topic[3]
        this.bg_flag = true
        data[this.index]["isClick"] = true
      }
    },
    previous() { 
      this.index --
      switch(this.currentType) {
        case 1: 
          this.change(this.index)
          break
        case 2: 
          this.fill(this.index)
          break
        case 3:
          this.judge(this.index)
          break
      }
    },
    next() { 
      this.index ++
      switch(this.currentType) {
        case 1: 
          this.change(this.index)
          break
        case 2: 
          this.fill(this.index)
          break
        case 3:
          this.judge(this.index)
          break
      }
    },
    mark() { 
      switch(this.currentType) {
        case 1:
          this.topic[1][this.index]["isMark"] = true 
          break
        case 2:
          this.topic[2][this.index]["isMark"] = true 
          break
        case 3:
          this.topic[3][this.index]["isMark"] = true 
      }
    },
    commit() { 
    
      let topic1Answer = this.topic1Answer
      let finalScore = 0
      topic1Answer.forEach((element,index) => { 
        let right = null
        if(element != null) {
          switch(element) { 
            case 1:
              right = "A"
              break
            case 2:
              right = "B"
              break
            case 3:
              right = "C"
              break
            case 4:
              right = "D"
          }
          if(right == this.topic[1][index].rightAnswer) { 
            finalScore += this.topic[1][index].score 
          }
          console.log(right,this.topic[1][index].rightAnswer)
        }
        // console.log(topic1Answer)
      })
     
      let fillAnswer = this.fillAnswer

      for(var i = 0; i < fillAnswer.length; i++){
        console.log("Fill q -> " + this.topic[2][i].answer)
        console.log("Fill q2 -> " + fillAnswer[i])
        if(this.topic[2][i].answer.includes(fillAnswer[i][0])){
          finalScore += this.topic[2][i].score
        }
      }
 
      let topic3Answer = this.judgeAnswer
      topic3Answer.forEach((element,index) => {
        let right = null
        switch(element) {
          case 1:
            right = "T"
            break
          case 2:
            right = "F"
        }
        if(right == this.topic[3][index].answer) { 
            finalScore += this.topic[3][index].score 
          }
      })
      console.log(`Current score ${finalScore}`)
      if(this.time != 0) {
        this.$confirm("The time is not reach to the end","Friendly Reminder",{
          confirmButtonText: 'Submit it',
          cancelButtonText: 'Review my Exam',
          type: 'warning'
        }).then(() => {
          console.log("Submit")
          let date = new Date()
          this.endTime = this.getTime(date)
          let answerDate = this.endTime.substr(0,10)
          let tokenStr = this.$session.get('jwt')
      const headers = 
      {
      
       'Authorization' : 'Bearer ' + tokenStr
      }
         
          this.$axios({
         /*    url: '/api/score', */
            url: `http://localhost:8080/score`,
            method: 'post',
            data: {
              examCode: this.examData.examCode,
              studentId: this.userInfo.id, 
              subject: this.examData.source, 
              etScore: finalScore, 
              answerDate: answerDate, 
            },
            headers
          }).then(res => {
            debugger;
            if(res.data.code == 200) {
              this.$router.push({path:'/studentScore',query: {
                score: finalScore, 
                startTime: this.startTime,
                endTime: this.endTime,
                mdata : res.data.data
              }})
            }  
          })
        }).catch(() => {
          console.log("Continue")
        })
      }
    },
    showTime() { 
      setInterval(() => {
        this.time -= 1
        if(this.time == 10) {
          this.$message({
            showClose: true,
            type: 'error',
            message: 'Student, you have only 10 mins left'
          })
          if(this.time == 0) {
            console.log("TIme is up !!!!")
          }
        }
      },1000 * 60)
    }
  },
//  computed:mapState(["isPractice"])
}
</script>

<style lang="less">
.iconfont.icon-time {
  color: #2776df;
  margin: 0px 6px 0px 20px;
}
.analysis {
  margin-top: 20px;
  .right {
    color: #2776df;
    font-size: 18px;
    border: 1px solid #2776df;
    padding: 0px 6px;
    border-radius: 4px;
    margin-left: 20px;
  }
  ul li:nth-child(2) {
    margin: 20px 0px;
  }
  ul li:nth-child(3) {
    padding: 10px;
    background-color: #d3c6c9;
    border-radius: 4px;
  }
}
.analysis span:nth-child(1) {
  font-size: 18px;
}
.mark {
  position: absolute;
  width: 4px;
  height: 4px;
  content: "";
  background-color: red;
  border-radius: 50%;
  top: 0px;
  left: 22px;
}
.border {
  position: relative;
  border: 1px solid #FF90AA !important;
}
.bg {
  background-color: #5188b8 !important;
}
.fill .el-input {
  display: inline-flex;
  width: 150px;
  margin-left: 20px;
  .el-input__inner {
    border: 1px solid transparent;
    border-bottom: 1px solid #eee;
    padding-left: 20px;
  }
}

.slider-fade-enter-active {
  transition: all .3s ease;
}
.slider-fade-leave-active {
  transition: all .3s cubic-bezier(1.0, 0.5, 0.8, 1.0);
}
.slider-fade-enter, .slider-fade-leave-to {
  transform: translateX(-100px);
  opacity: 0;
}

.operation .end li:nth-child(2) {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  background-color: rgb(39, 118, 223);
  border-radius: 50%;
  width: 50px;
  height: 50px;
  color: #fff;
}
.operation .end li {
  cursor: pointer;
  margin: 0 100px;
}
.operation {
  background-color: #fff;
  border-radius: 4px;
  padding: 10px 0px;
  margin-right: 10px;
}
.operation .end {
  display: flex;
  justify-content: center;
  align-items: center;
  color: rgb(39, 118, 223);
}
.content .number {
  display: inline-flex;
  justify-content: center;
  align-items: center;
  width: 20px;
  height: 20px;
  background-color: rgb(39, 118, 223);
  border-radius: 4px;
  margin-right: 4px;
}
.content {
  padding: 0px 20px;
}
.content .topic {
  padding: 20px 0px;
  padding-top: 30px; 
}
.right .content {
  background-color: #fff;
  margin: 10px;
  margin-left: 0px;
  height: 470px;
}
.content .el-radio-group label {
  color: #000;
  margin: 5px 0px;
}
.content .el-radio-group {
  display: flex;
  flex-direction:column;
}
.right .title p {
  margin-left: 20px;
}
.flexarea {
  display: flex;
}
.flexarea .right {
  flex: 1;
}
.auto-right {
  margin-left: auto;
  color: #2776df;
  margin-right: 10px;
}
.right .title {
  margin-right: 10px;
  padding-right: 30px;
  display: flex;
  margin-top: 10px;
  background-color: #fff;
  height: 50px;
  line-height: 50px;
}
.clearfix {
  clear: both;
}
.l-bottom .final {
  cursor: pointer;
  display: inline-block;
  text-align: center;
 background-color: rgb(39, 118, 223);
  width: 240px;
  margin: 20px 0px 20px 10px;
  border-radius: 4px;
  height: 30px;
  line-height: 30px;
  color: #fff;
  margin-top: 22px;
}
#answer .left .item {
  padding: 0px;
  font-size: 16px;
}
.l-bottom {
  border-radius: 4px;
  background-color: #fff;
}
.l-bottom .item p {
  margin-bottom: 15px;
  margin-top: 10px;
  color: #000;
  margin-left: 10px;
  letter-spacing: 2px;
}
.l-bottom .item li {
  width: 15%;
  margin-left: 5px;
  margin-bottom: 10px;
}
.l-bottom .item {
  display: flex;
  flex-direction: column;
}
.l-bottom .item ul {
  width: 100%;
  margin-bottom: -8px;
  display: flex;
  justify-content: space-around;
  flex-wrap: wrap;
}
.l-bottom .item ul li a { 
  position: relative;
  justify-content: center;
  display: inline-flex;
  align-items: center;
  width: 30px;
  height: 30px;
  border-radius: 50%;
  background-color: #fff;
  border: 1px solid #eee;
  text-align: center;
  color: #000;
  font-size: 16px;
}
.left .l-top {
  display: flex;
  justify-content: space-around;
  padding: 16px 0px;
  border: 1px solid #eee;
  border-radius: 4px;
  margin-bottom: 10px;
  background-color: #fff;
}
.left {
  width: 260px;
  height: 100%;
  margin: 10px 10px 0px 10px;
}
.left .l-top li:nth-child(2) a {
  border: 1px solid #eee;
}
.left .l-top li:nth-child(3) a {
  background-color: #5188b8;
  border: none;
}
.left .l-top li:nth-child(4) a {
  position: relative;
  border: 1px solid #eee;
}
.left .l-top li:nth-child(4) a::before {
  width: 4px;
  height: 4px;
  content: " ";
  position: absolute;
  background-color: red;
  border-radius: 50%;
  top: 0px;
  left: 16px;
}
.left .l-top li {
  display: flex;
  justify-content: center;
  align-items: center;
  flex-direction: column;
}
.left .l-top li a {
  display: inline-block;
  padding: 10px;
  border-radius: 50%;
  background-color: #fff;
  border: 1px solid #FF90AA;
}
#answer .top {
  background-color: rgb(39, 118, 223);
}
#answer .item {
  color: #fff;
  display: flex;
  padding: 20px;
  font-size: 20px;
}
#answer .top .item li:nth-child(1) {
  margin-right: 10px;
}
#answer .top .item li:nth-child(3) {
  position: relative;
  margin-left: auto;
}
#answer {
  padding-bottom: 30px;
}
.icon20 {
  font-size: 20px;
  font-weight: bold;
}
.item .msg {
  padding: 10px 15px;
  border-radius: 4px;
  top: 47px;
  right: -30px;
  color: #6c757d;
  position: absolute;
  border: 1px solid rgba(0,0,0,.15);
  background-color: #fff;
}
.item .msg p {
  font-size: 16px;
  width: 200px;
  text-align: left;
}
</style>
