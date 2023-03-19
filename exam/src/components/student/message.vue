
<template>

  <div class="chatbot-container">
    <div v-for="(message, index) in messages" :key="index">
      <div v-if="message.isBot" class="bot-message">{{ message.text }}</div>
      <div v-else class="user-message">{{ message.text }}</div>
    </div>
    <form @submit.prevent="handleSubmit">
      <input type="text" v-model="currentMessage" placeholder="Type your message here" />
      <button type="submit">Send</button>
    </form>
  </div>
</template>

<script>
/* import  Vue from 'vue'
import VueSocketIO from 'vue-socket.io' */
export default {
  // name: 'message'
  data() {
    return {
      flag: false,
      current: 0,
      title: "",
      content: "",
      pagination: { 
        current: 1, 
        total: null, 
        size: 4 
      },
      openai: {
        model: 'text-davinci-002',
        prompt: 'What is seneca college',
        max_tokens: 100,
        temperature: 0.5,
        top_p: 1,
        frequency_penalty: 0,
        presence_penalty: 0,

      },
      API_KEY: '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',
      msg: [],
      message: '',
      isConnected: false,
      socketMessage: '',
      messages: [],
      currentMessage: '',
    }
  },
  created() {
    this.getMsg()


  },


  /*   sockets: {
     
      connect: function () {
        this.isConnected = true;
              console.log('socket connected')
          },
  
      disconnect() {
        this.isConnected = false;
      },
      customEmit: function (data) {
              console.log('this method was fired by the socket server. eg: io.emit("customEmit", )' + data)
          },
  
      // Fired when the server sends something on the "messageChannel" channel.
      messageChannel(data) {
        this.socketMessage = data
        console.log(this.socketMessage)
      }
    },
   */
  methods: {
    getMsg() {
      let tokenStr = this.$session.get('jwt')
      const headers =
      {

        'Authorization': 'Bearer ' + tokenStr
      }
      ///api/messages/${this.pagination.current}/${this.pagination.size}
      this.$axios(`http://localhost:8080/messages/${this.pagination.current}/${this.pagination.size}`, { headers }).then(res => {
        let status = res.data.code
        if (status == 200) {
          this.msg = res.data.data.records
          this.pagination = res.data.data
        }
      })
    },
    handleSubmit() {
      this.messages.push({ text: this.currentMessage, isBot: false });
  
      this.openai.prompt =  this.currentMessage
      const mheaders =
      {

        'Authorization': 'Bearer ' + this.API_KEY
      }
      this.$axios({
        url: 'https://api.openai.com/v1/completions',
        headers: mheaders,
        method: 'post',
        data: this.openai

      }).then(res => {
  
        console.log(JSON.stringify(res))
        console.log(res.data.choices[0].text)
        this.currentMessage = res.data.choices[0].text
        this.messages.push({ text: this.currentMessage, isBot: true });
        this.currentMessage = '';
      this.openai.prompt = ''
      })
    
    },
    /*  connect() {
          debugger
          var socket =   new VueSocketIO({
      debug: true,
      connection: 'http://localhost:8080/gs-guide-websocket'
    })
       //new SockJS('http://localhost:8080/gs-guide-websocket');
        this.stompClient = Stomp.over(socket);
        this.stompClient.connect({}, function (frame) {
            setConnected(true);
            console.log('Connected: ' + frame);
            stompClient.subscribe('/topic/greetings', function (greeting) {
                showGreeting(JSON.parse(greeting.body).content);
            });
             stompClient.subscribe('/topic/yigreetings', function (greeting) {
                        showGreeting(JSON.parse(greeting.body).content);
                 });
        });
    }, */


  

    handleSizeChange(val) {
      this.pagination.size = val
      this.getMsg()
    },
  
    handleCurrentChange(val) {
      this.pagination.current = val
      this.getMsg()
    },

    submit() {
      let date = new Date()
      if (this.title.length == 0 || this.content.length == 0) { 
        this.$message({
          type: 'error',
          message: 'Comment content or title can not be empty',
        })
      } else {
        this.$axios({
          /*   url: "/api/message", */
          url: `http://localhost:8080/message`,
          method: "post",
          data: {
            title: this.title,
            content: this.content,
            time: date
          }
        }).then(res => {
          let code = res.data.code
          if (code == 200) {
            this.$message({
              type: "success",
              message: "Leave the message"
            })
          }
          this.getMsg()
        })
      }
      this.title = ""
      this.content = ""
      this.getMsg()
    },
     //Code Comment:Not in use
    replay(messageId) { 
      this.$prompt('Reply Comment', 'hint', {
        confirmButtonText: 'Confirm',
        cancelButtonText: 'Cancel',
        inputPattern: /^[\s\S]*.*[^\s][\s\S]*$/,
        inputErrorMessage: 'Reply can not be empty'
      }).then(({ value }) => {
        let date = new Date()
        console.log(messageId)
        this.$axios({
          /*   url: '/api/replay', */
          url: `http://localhost:8080/replay`,
          method: 'post',
          data: {
            replay: value,
            replayTime: date,
            messageId: messageId
          }
        }).then(res => {
          this.getMsg()
        })
        this.$message({
          type: 'success',
          message: 'Reply confirmed'
        });
      }).catch(() => {
        this.$message({
          type: 'info',
          message: 'Cancel input'
        });
      });
    },
    enter(index) {
      this.flag = true
      this.current = index
    },
    leave(index) {
      this.flag = false;
      this.current = index;
    }
  },
  /*  mixins: [VueSocketIO({
     debug: true,
     connection: 'http://localhost:8080/gs-guide-websocket'
   })] */
}
</script>

<style lang="less" scoped>
.pagination {
  display: flex;
  justify-content: center;
}
#message {
  width: 980px;
  margin: 0 auto;
}
.title {
  margin: 20px;
}
.content {
  padding: 20px 0px;
}
#message {
  .btn {
    padding-bottom: 20px;
  }
  .all {
    .date {
      color: rgb(80, 157, 202);
      line-height: 45px;
      font-size: 13px;
    }
    .list {
      background-color: #eee;
      padding: 10px;
      border-radius: 4px;
      margin: 10px 0px;
      position: relative;
      transition: all 0.3s ease;
      .title {
        color: #5f5f5f;
        margin: 0px;
        font-size: 13px;
        line-height: 30px;
      }
      .content {
        padding: 0px;
      }
      .icon-untitled33 {
        font-size: 13px;
        margin-right: 4px;
      }
      .icon-date {
        font-size: 13px;
        margin-right: 4px;
        color: rgb(80, 157, 202);
      }
      .replay {
        position: absolute;
        right: 30px;
        bottom: 15px;
        color: tomato;
        cursor: pointer;
        transition: all 0.3s ease;
      }
      .comment {
        margin: -7px 0px;
        padding-bottom: 12px;
        font-size: 13px;
        color: #28b2b4;
        i {
          margin-right: 4px;
        }
      }
    }
  }
}
#message .wrapper {
  background-color: #fff;
  padding: 20px;
}
.chatbot-container {
  display: flex;
  flex-direction: column;
  height: 400px;
  border: 1px solid #ccc;
  padding: 10px;
}

.bot-message {
  background-color: #eee;
  padding: 10px;
  margin: 5px;
  border-radius: 5px;
  align-self: flex-start;
}

.user-message {
  background-color: #0084ff;
  color: #fff;
  padding: 10px;
  margin: 5px;
  border-radius: 5px;
  align-self: flex-end;
}

input {
  width: 100%;
  padding: 10px;
  margin-top: 10px;
  border-radius: 5px;
  border: 1px solid #ccc;
  font-size: 16px;
}

button {
  padding: 10px;
  margin-top: 10px;
  border-radius: 5px;
  border: none;
  background-color: #0084ff;
  color: #fff;
  font-size: 16px;
}
</style>
