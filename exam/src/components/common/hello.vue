<template>
  <section class="index">
    <div class="hello">
      <i class="iconfont icon-xihuan"></i><span>Happy to see you again,{{user.userName}}.</span>
    </div>
    <div class="msg">
      <p class="title">Normal Instroduction</p>
      <ul>
        <li @click="openMsg()"><i class="iconfont icon-flag"></i>Some text</li>
        <li @click="openMsg()"><i class="iconfont icon-flag"></i>Some text</li>
        <li @click="openMsg()"><i class="iconfont icon-flag"></i>Some text</li>
      </ul>
    </div>
  </section>
</template>

<script>
export default {
  data() {
    return {
      user: { 
        userName: null,
        userId: null
      } 
    }
  },
  created() {
    this.getUserInfo()
  },
  methods: {
    getUserInfo() { 
      let userName = this.$cookies.get("cname")
      let userId = this.$cookies.get("cid")
      this.user.userName = userName
      this.user.userId = userId
    },
    openMsg() {
      this.$alert('Some text should put in here',{
        confirmButtonText: 'Conifrm'
      })
    }
  }
}
</script>


<style lang="less" scoped>
.index {
  margin-left: 70px;
  .hello {
    font-size: 20px;
    color: #726f70;
    .icon-xihuan {
      font-size: 30px;
      color: #dd6572;
    }
  }
  .msg {
    .title {
      font-size: 16px;
      color: #000;
      margin-top: 20px;
      margin-left: 10px;
    }
    ul {
      display: flex;
      flex-direction: column;
      width: 200px;
      overflow: hidden;
    }
    li {
      margin-top: 10px;
      font-size: 14px;
      color: lightcoral;
      cursor: pointer;
      display: inline-block;
    }
  }
}
</style>

