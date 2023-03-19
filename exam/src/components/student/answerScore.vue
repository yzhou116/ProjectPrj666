<template>
  <div class="score">
    <div class="title">
      <p class="name">{{title}}</p>
      <p class="description">(Total Mark: {{totalmark}},Time limit: {{toTime}} Mins)</p>
      <p class="description">{{userName}}</p>
    </div>
    <div class="total">
      <div class="look">
        Current Test Score
      </div>
      <div class="show">
        <div class="img1" :class="{'img1Transform': imgShow}">
          <img :src="imgSrc.fail1" alt="Cry" v-if="score < 0.6">
          <img :src="imgSrc.pass1" alt="Pass" v-if="score >= 0.6">
        </div>
        <div class="number" :class="{'border': isTransition}">
          <span>{{finalRes}}</span>
         <!--  <span>Score</span> -->
        </div>
        <div class="img2" :class="{'img2Transform': imgShow}">
          <img :src="imgSrc.fail2" alt="Cry" v-if="score < 0.6">
          <img :src="imgSrc.pass2" alt="Pass" v-if="score >= 0.6">
        </div>
      </div>
      <ul class="time">
        <li class="start"><span>Start Time</span> <span>{{startTime}}</span></li>
        <li class="end"><span>End Time</span> <span>{{endTime}}</span></li>
      </ul>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      isTransition: false,
      score: 0,
      imgShow: false, 
      imgSrc: {
        fail1: require("@/assets/img/fail.gif"),
        fail2: require('@/assets/img/fail2.gif'),
        pass1: require('@/assets/img/execellent2.gif'),
        pass2: require('@/assets/img/excellent.gif')
      },
      startTime: null, 
      endTime: null, 
      title : "",
      totalmark : "",
      TimeLimited : "",
      toTime : "",
      userName : "",
      finalRes : ""

    }
  },
  created() {
    this.transiton()
    this.getScore()
  },
  methods: {
    transiton() {  
      setTimeout(() => {
        this.isTransition = true
        this.imgShow = true
      },1000)
    },
    getScore() {
       //Code Comment:calcualte the result of exam
      let score = this.$route.query.score
      let startTime = this.$route.query.startTime
      let endTime = this.$route.query.endTime
      this.score = score / this.$route.query.mdata.totalScore
      if(this.score <= 1 && this.score >= 0.9){
        this.finalRes = 'A+'
      }else if(this.score < 0.9 && this.score >= 0.8){
        this.finalRes = 'A'
      }else if(this.score < 0.8 && this.score >= 0.75){
        this.finalRes = 'B+'
      }else if(this.score < 0.75 && this.score >= 0.7){
        this.finalRes = 'B'
      }else if(this.score < 0.7 && this.score >= 0.65){
        this.finalRes = 'C+'
      }else if(this.score < 0.65 && this.score >= 0.6){
        this.finalRes = 'C'
      }else{
        this.finalRes = 'F'
      }
      this.startTime = startTime
      this.endTime = endTime
      this.totalmark = this.$route.query.mdata.totalScore,
      this.title = this.$route.query.mdata.source,
      this.toTime = this.$route.query.mdata.totalTime
      this.userName = this.$route.query.mdata.student
    }
  }
}
</script>

<style lang="less" scoped>
.show {
  display: flex;
  justify-content: center;
  align-items: center;
  img {
    width: 160px;
    height: 160px;
  }
  .img1Transform {
    opacity: 1 !important;
    transform: translateX(30px) !important;  
    transition: all 0.6s ease !important;
  }
  .img2Transform {
    opacity: 1 !important;
    transform: translateX(-30px) !important;  
    transition: all 0.6s ease !important;
  }
  .img1 {
    margin-top: 70px;
    opacity: 0;
    transform: translateX(0px);  
    transition: all 0.6s ease;
  }
  .img2 {
    margin-top: 30px;
    opacity: 0;
    transform: translateX(0px);  
    transition: all 0.6s ease;
  }
}
.time {
  padding: 0px 70px;
  li {
    display: flex;
    justify-content: space-around;
    padding: 10px;
    margin: 20px 0px;
  }
  li:nth-child(1) {
    background-color: #fcf8e3;
  }
  li:nth-child(2) {
    background-color: #e9f5e9;
  }
}
.border {
  border: 6px solid #36aafd !important;
  transition: all 2s ease;
  width: 160px !important;
  height: 160px !important;
  transform: rotate(360deg) !important;
  opacity: 1 !important;
}
.score {
  max-width: 800px;
  margin: 0 auto;
  .title {
    margin: 60px 0px 30px 0px;
    display: flex;
    justify-content: center;
    align-items: center;
    flex-direction: column;
    .name {
      font-size: 26px;
      color: inherit;
      font-weight: 500;
    }
    .description {
      font-size: 14px;
      color: #888;
    }
  }
  .total {
    border: 1px solid #dbdbdb;
    background-color: #fff;
    padding: 40px;
    .look {
      border-bottom: 1px solid #dbdbdb;
      padding: 0px 0px 14px 14px;
      color: #36aafd;
    }
    .number {
      opacity: 0;
      border: 6px solid #fff;
      transform: rotate(0deg);
      display: flex;
      justify-content: center;
      align-items: center;
      flex-direction: column;
      margin: 0 auto;
      width: 160px;
      height: 160px;
      border-radius: 50%;
      margin-top: 80px;
      margin-bottom: 20px;
      transition: all 1s ease;
      
      span:nth-child(1) {
        font-size: 36px;
        font-weight: 600;
      }
      span:nth-child(2) {
        font-size: 14px;
      }
    }
  }
}
</style>

