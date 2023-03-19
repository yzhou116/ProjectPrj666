<template>
  <div class="part" >
    <div class="box" ref="box"></div>
    <div v-if="isNull">
      <span>There is no result for this exam</span>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      isNull: false, 
      name: null,
      category: { 
        'A+': 0,
        'A': 0,
        'B': 0,
        'C': 0,
        'F': 0,
      }
    }
  },
  created() {
    this.getScoreInfo()

  },
  methods: {
    getScoreInfo() {
      let examCode = this.$route.query.examCode
      this.name = this.$route.query.source
      ///api/scores/${examCode}
      let tokenStr = this.$session.get('jwt')
      const headers = 
      {
      
       'Authorization' : 'Bearer ' + tokenStr
      }
      this.$axios(`http://localhost:8080/scores/${examCode}`,{headers}).then(res => {
        debugger
        let data = res.data.data
        if(data.length > 0) {
          let box = this.$refs['box']
          let charts = this.$echarts.init(box)
          //Code Comment:loop the data to find out what value need to assign
          data.forEach(element => {
          /*  switch(element.etScore / 10) {
          
              case 10:
              case 9:
                this.category["A+"]++
                break
              case 8:
                this.category['A']++
                break
              case 7:
                this.category["B"]++
                break
              case 6:
                this.category['C']++
                break
              default:
                this.category['F']++
            } */
            if(element.etScore / element.score >= 0.9){
              this.category["A+"]++
            }else if(element.etScore / element.score >= 0.8){
              this.category["A"]++
            }else if(element.etScore / element.score >= 0.7){
              this.category["B"]++
            }else if(element.etScore / element.score >= 0.7){
              this.category['C']++
            }else{
              this.category['F']++
            }
          });
          let option = {
              title : {
                  text: `${this.name}Score Chart`,
                  subtext: 'Score Pie chart',
                  x:'center'
              },
              tooltip : {
                  trigger: 'item',
                  formatter: "{a}: {b} <br/> {c}Person ({d}%)"
              },
              legend: {
                  orient: 'vertical',
                  left: 'left',
                  data: ['A+','A','B','C','F']
              },
              series : [
                  {
                      name: 'Score',
                      type: 'pie',
                      radius : '35%',
                      center: ['50%', '35%'],
                      data:[
                          {value:this.category['A+'], name:'A+'},
                          {value:this.category['A'], name:'A'},
                          {value:this.category['B'], name:'B'},
                          {value:this.category['C'], name:'C'},
                          {value:this.category['F'], name:'F'}
                      ],
                      itemStyle: {
                          emphasis: {
                              shadowBlur: 10,
                              shadowOffsetX: 0,
                              shadowColor: 'rgba(0, 0, 0, 0.5)'
                          }
                      }
                  }
              ]
          };
          charts.setOption(option)
        }else {
          this.isNull = true
        }
      })
    }
  },

}
</script>

<style lang="less" scoped>
.part {
  .box {
    width: 800px;
    height: 800px;
    margin-left: 40px;
  }
}
</style>


