
<template>
  <div class="add">
    <el-tabs v-model="activeName">
    <el-tab-pane name="first">
      <span slot="label"><i class="el-icon-circle-plus"></i>Add Question Repo</span>
      <section class="append">
        <ul>
          <li>
            <span>Repo Type:</span>
            <el-select v-model="optionValue" placeholder="Please choose Type" class="w150">
              <el-option
                v-for="item in options"
                :key="item.value"
                :label="item.label"
                :value="item.value">
              </el-option>
            </el-select>
          </li>
          <li v-if="optionValue == 'Multi Choice'">
            <span>Chapter:</span>
            <el-input
              placeholder="Please enter chapter"
              v-model="postChange.section"
              class="w150"
              clearable>
            </el-input>
          </li>
          <li v-if="optionValue == 'Fill Blank'">
            <span>Chapter:</span>
            <el-input
              placeholder="Please enter chapter"
              v-model="postFill.section"
              class="w150"
              clearable>
            </el-input>
          </li>
          <li v-if="optionValue == 'True False'">
            <span>Chapter:</span>
            <el-input
              placeholder="Please enter chapter"
              v-model="postJudge.section"
              class="w150"
              clearable>
            </el-input>
          </li>
          <li v-if="optionValue == 'Multi Choice'">
            <span>Level:</span>
            <el-select v-model="postChange.level" placeholder="Choose Level" class="w150">
              <el-option
                v-for="item in levels"
                :key="item.value"
                :label="item.label"
                :value="item.value">
              </el-option>
            </el-select>
          </li>
          <li v-if="optionValue == 'Fill Blank'">
            <span>Level:</span>
            <el-select v-model="postFill.level" placeholder="Choose Level" class="w150">
              <el-option
                v-for="item in levels"
                :key="item.value"
                :label="item.label"
                :value="item.value">
              </el-option>
            </el-select>
          </li>
          <li v-if="optionValue == 'True False'">
            <span>Level:</span>
            <el-select v-model="postJudge.level" placeholder="Choose Level" class="w150">
              <el-option
                v-for="item in levels"
                :key="item.value"
                :label="item.label"
                :value="item.value">
              </el-option>
            </el-select>
          </li>
          <li v-if="optionValue == 'Multi Choice'">
            <span>Correct:</span>
            <el-select v-model="postChange.rightAnswer" placeholder="Choose Correct One" class="w150">
              <el-option
                v-for="item in rights"
                :key="item.value"
                :label="item.label"
                :value="item.value">
              </el-option>
            </el-select>
          </li>
        </ul>

        <div class="change" v-if="optionValue == 'Multi Choice'">
          <div class="title">
            <el-tag>Question: </el-tag><span>Enter the title in the input box below, in the form of --DNS server and DHCP server function is ()</span>
            <el-input
              type="textarea"
              rows="4"
              v-model="postChange.question"
              placeholder="Please enter the question"
              resize="none"
              class="answer">
            </el-input>
          </div>
          <div class="options">
            <ul>
              <li>
                <el-tag type="success">A</el-tag>
                <el-input
                  placeholder="Please enter the choice A"
                  v-model="postChange.answerA"
                  clearable="">
                </el-input>
              </li>
              <li>
                <el-tag type="success">B</el-tag>
                <el-input
                  placeholder="Please enter the choice B"
                  v-model="postChange.answerB"
                  clearable="">
                </el-input>
              </li>
              <li>
                <el-tag type="success">C</el-tag>
                <el-input
                  placeholder="Please enter the choice C"
                  v-model="postChange.answerC"
                  clearable="">
                </el-input>
              </li>
              <li>
                <el-tag type="success">D</el-tag>
                <el-input
                  placeholder="Please enter the choice D"
                  v-model="postChange.answerD"
                  clearable="">
                </el-input>
              </li>
            </ul>
          </div>
          <div class="title">
            <el-tag>Analysis:</el-tag><span>Please enter the analysis of question</span>
            <el-input
              type="textarea"
              rows="4"
              v-model="postChange.analysis"
              placeholder="Please enter the analysis of question"
              resize="none"
              class="answer">
            </el-input>
          </div>
          <div class="submit">
            <el-button type="primary" @click="changeSubmit()">Add</el-button>
          </div>
        </div>

        <div class="change fill" v-if="optionValue == 'Fill Blank'">
          <div class="title">
            <el-tag>Question: </el-tag><span>Enter the title, in the form of -- From the perspective of computer network system composition, computer networks can be divided into () and (). Note that candidates must use parentheses (English half-width) to answer the questions.</span>
            <el-input
              type="textarea"
              rows="4"
              v-model="postFill.question"
              placeholder="Please enter the question"
              resize="none"
              class="answer">
            </el-input>
          </div>
          <div class="fillAnswer">
            <el-tag>Answer:</el-tag>
            <el-input v-model="postFill.answer"></el-input>
          </div>
          <div class="title analysis">
            <el-tag type="success">Analysis:</el-tag><span>Please enter the analysis of question</span>
            <el-input
              type="textarea"
              rows="4"
              v-model="postFill.analysis"
              placeholder="Please enter the analysis of question"
              resize="none"
              class="answer">
            </el-input>
          </div>
          <div class="submit">
            <el-button type="primary" @click="fillSubmit()">Add</el-button>
          </div>
        </div>

        <div class="change judge" v-if="optionValue == 'True False'">
          <div class="title">
            <el-tag>Question:</el-tag><span>Please enter the question</span>
            <el-input
              type="textarea"
              rows="4"
              v-model="postJudge.question"
              placeholder="Please enter the question"
              resize="none"
              class="answer">
            </el-input>
          </div>
          <div class="judgeAnswer">
            <el-radio v-model="postJudge.answer" label="T">True</el-radio>
            <el-radio v-model="postJudge.answer" label="F">False</el-radio>
          </div>
          <div class="title">
            <el-tag>Analysis:</el-tag><span>Please enter the analysis of question</span>
            <el-input
              type="textarea"
              rows="4"
              v-model="postJudge.analysis"
              placeholder="Please enter answer analysis"
              resize="none"
              class="answer">
            </el-input>
          </div>
          <div class="submit">
            <el-button type="primary" @click="judgeSubmit()">Add</el-button>
          </div>
        </div>
      </section>
    </el-tab-pane>
    <el-tab-pane name="second">
      <span slot="label"><i class="iconfont icon-daoru-tianchong"></i>Online Exam</span>
      <div class="box">
        <ul>
          <li>
            <span>Level:</span>
            <el-select v-model="difficultyValue" placeholder="Question Level" class="w150">
              <el-option
                v-for="item in difficulty"
                :key="item.value"
                :label="item.label"
                :value="item.value">
              </el-option>
            </el-select>
          </li>
          <li>
            <span>Number of choice</span> <el-input type="text" v-model="changeNumber"></el-input>
          </li>
          <li>
            <span>Number of fill the blank</span> <el-input type="text" v-model="fillNumber"></el-input>
          </li>
          <li>
            <span>Number of true or false</span> <el-input type="text" v-model="judgeNumber"></el-input>
          </li>
          <li>
            <el-button type="primary" @click="create()">Add exam bundle</el-button>
          </li>
        </ul>
      </div>
    </el-tab-pane>
  </el-tabs>
  </div>
</template>

<script>
export default {
  data() {
    return {
      changeNumber: null, 
      fillNumber: null, 
      judgeNumber: null, 
      activeName: 'first',  
      options: [ 
        {
          value: 'Multi Choice',
          label: 'Multi Choice'
        },
        {
          value: 'Fill Blank',
          label: 'Fill Blank'
        },
        {
          value: 'True False',
          label: 'True False'
        },
      ],
      difficulty: [ 
        {
          value: 'Easy',
          label: 'Easy'
        },
        {
          value: 'Normal',
          label: 'Normal'
        },
        {
          value: 'Hard',
          label: 'Hard'
        }
      ],
      difficultyValue: 'Easy',
      levels: [ 
        {
          value: '1',
          label: '1'
        },
        {
          value: '2',
          label: '2'
        },
        {
          value: '3',
          label: '3'
        },
        {
          value: '4',
          label: '4'
        },
        {
          value: '5',
          label: '5'
        },
      ],
      rights: [ 
        {
          value: 'A',
          label: 'A'
        },
        {
          value: 'B',
          label: 'B'
        },
        {
          value: 'C',
          label: 'C'
        },
        {
          value: 'D',
          label: 'D'
        },
      ],
      paperId: null,
      optionValue: 'Multi Choice', 
      subject: '', 
      postChange: { 
        subject: '', 
        level: '', 
        rightAnswer: '', 
        section: '', 
        question: '', 
        analysis: '', 
        answerA: '',
        answerB: '',
        answerC: '',
        answerD: '',
      },
      postFill: { 
        subject: '', 
        level: '', 
        answer: '', 
        section: '', 
        question: '', 
        analysis: '', 
      },
      postJudge: { 
        subject: '', 
        level: '', 
        answer: '', 
        section: '', 
        question: '', 
        analysis: '', 
      },
      postPaper: { 
        paperId: null,
        questionType: null, 
        questionId: null,
      }
    };
  },
  created() {
    this.getParams()
  },
  methods: {
    // handleClick(tab, event) {
    //   console.log(tab, event);
    // },
    create() {
      let tokenStr = this.$session.get('jwt')
      const headers = 
      {
      
       'Authorization' : 'Bearer ' + tokenStr
      }
      this.$axios({
      /*   url: '/api/item', */
      url: `http://localhost:8080/item`,
        method: 'post',
        data: {
          changeNumber: this.changeNumber,
          fillNumber: this.fillNumber,
          judgeNumber: this.judgeNumber,
          paperId: this.paperId,
          subject: 'Computer Network' 
        },
        headers
      }).then(res => {
        console.log(res)
        let data = res.data
        if(data.code==200){
          setTimeout(() => {
            this.$router.push({path: '/selectAnswer'})
          },1000)
           this.$message({
            message: data.message,
            type: 'success'
          })
        }else if(data.code==400){
            this.$message({
            message: data.message,
            type: 'error'
          })
        }

      })
    },
    getParams() {
      let subject = this.$route.query.subject 
      let paperId = this.$route.query.paperId 
      this.paperId = paperId
      this.subject = subject
      this.postPaper.paperId = paperId
    },
    changeSubmit() { 
      this.postChange.subject = this.subject
      let tokenStr = this.$session.get('jwt')
      const headers = 
      {
      
       'Authorization' : 'Bearer ' + tokenStr
      }
      var username = this.$cookies.get("cname")
      this.$axios({ 
      /*   url: '/api/MultiQuestion', */
      url: `http://localhost:8080/MultiQuestion`,
        method: 'post',
        data: {
          ...this.postChange,
          teacher : username      
        },
        headers
      }).then(res => { 
        let status = res.data.code
        if(status == 200) {
          this.$message({
            message: 'Added to Question Repo',
            type: 'success'
          })
          this.postChange = {}
        }
      }).then(() => {
        ///api/multiQuestionId
        this.$axios(`http://localhost:8080/multiQuestionId`,{headers}).then(res => {
          let questionId = res.data.data.questionId
          this.postPaper.questionId = questionId
          this.postPaper.questionType = 1
          headers
          this.$axios({
           /*  url: '/api/paperManage', */
           url: `http://localhost:8080/paperManage`,
            method: 'Post',
            data: {
              ...this.postPaper
            },
            headers
          })
        })
      })
    },
    fillSubmit() { 
      let tokenStr = this.$session.get('jwt')
      const headers = 
      {
      
       'Authorization' : 'Bearer ' + tokenStr
      }
      var username = this.$cookies.get("cname")
      this.postFill.subject = this.subject
      this.$axios({
       /*  url: '/api/fillQuestion', */
       url: `http://localhost:8080/fillQuestion`,
        method: 'post',
        data: {
          ...this.postFill,
          teacher : username  
        },
        headers
      }).then(res => {
        let status = res.data.code
        if(status == 200) {
          this.$message({
            message: 'Add to exam',
            type: 'success'
          })
          this.postFill = {}
        }
      }).then(() => {
        ///api/fillQuestionId
        this.$axios(`http://localhost:8080/fillQuestionId`,{headers}).then(res => { 
          let questionId = res.data.data.questionId
          this.postPaper.questionId = questionId
          this.postPaper.questionType = 2
          headers
          this.$axios({
          /*   url: '/api/paperManage', */
          url: `http://localhost:8080/paperManage`,
            method: 'Post',
            data: {
              ...this.postPaper
            },
            headers
          })
        })
      })
    },
    judgeSubmit() { 
      let tokenStr = this.$session.get('jwt')
      const headers = 
      {
      
       'Authorization' : 'Bearer ' + tokenStr
      }
      var username = this.$cookies.get("cname")
      this.postJudge.subject = this.subject
      this.$axios({
      /*   url: '/api/judgeQuestion', */
      url: `http://localhost:8080/judgeQuestion`,
        method: 'post',
        data: {
          ...this.postJudge,
          teacher : username  
        },
        headers
      }).then(res => {
        let status = res.data.code
        if(status == 200) {
          this.$message({
            message: 'Add to Question Repo',
            type: 'success'
          })
          this.postJudge = {}
        }
      }).then(() => {
        ///api/judgeQuestionId
        this.$axios(`http://localhost:8080/judgeQuestionId`,{headers}).then(res => { 
          let questionId = res.data.data.questionId
          this.postPaper.questionId = questionId
          this.postPaper.questionType = 3
          headers
          this.$axios({
        /*     url: '/api/paperManage', */
        url: `http://localhost:8080/paperManage`,
            method: 'Post',
            data: {
              ...this.postPaper
            },
            headers
          })
        })
      })
    }
  },
};
</script>

<style lang="less" scoped>
.add {
  margin: 0px 40px;
  .box {
    padding: 0px 20px;
    ul li {
      margin: 10px 0px;
      display: flex;
      align-items: center;
      .el-input {
        width: 6%;
      }
      .w150 {
        margin-left: 20px;
        width: 7%;
      }
    }
  }
  .el-icon-circle-plus {
    margin-right: 10px;
  }
  .icon-daoru-tianchong {
    margin-right: 10px;
  }
  .append {
    margin: 0px 20px;
    ul {
      display: flex;
      align-items: center;
      li {
        margin-right: 20px;
      }
    }
    .change {
      margin-top: 20px;
      padding: 20px 16px;
      background-color: #E7F6F6;
      border-radius: 4px;
      .title {
        padding-left: 6px;
        color: #2f4f4f;
        span:nth-child(1) {
          margin-right: 6px;
        }
        .answer {
          margin: 20px 0px 20px 8px;
        }
        .el-textarea {
          width: 98% !important;
        }
      }
      .options {
        ul {
          display: flex;
          flex-direction: column;
        }
        ul li {
          display: flex;
          justify-content: center;
          align-items: center;
          width: 98%;
          margin: 10px 0px;
          span {
            margin-right: 20px;
          }
        }
      }
      .submit {
        display: flex;
        justify-content: center;
        align-items: center;
      }        
    }
    .fill {
      .fillAnswer {
        display: flex;
        justify-content: center;
        align-items: center;
        span {
          margin-right: 6px;
        }
        .el-input {
          width: 91% !important;
        }
      }
      .analysis {
        margin-top: 20px;
        margin-left: 5px;
      }
    }
    .judge {
      .judgeAnswer {
        margin-left: 20px;
        margin-bottom: 20px;
      }
    }
    .w150 {
      width: 150px;
    }
    li:nth-child(2) {
      display: flex;
      align-items: center;
      justify-content: center;
    }
  }
}
</style>


