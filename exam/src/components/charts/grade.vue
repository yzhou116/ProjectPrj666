
<template>
  <div id="grade">
    <div ref="box" class="box"></div>
    <div class="notFound" v-if="isNull">
      <i class="iconfont icon-LC_icon_tips_fill"></i><span>This student does not attend the test</span>
    </div>
  </div>
</template>

<script>
export default {
  name: "grade",
  data() {
    return {
      isNull: false, 
      tableDataX: [], 
      tableDataY: [], 
    }
  },
  mounted() {
    this.score();
  },
  methods: {
    score() {
      let studentId = this.$route.query.studentId
      let tokenStr = this.$session.get('jwt')
      const headers = 
      {
      
       'Authorization' : 'Bearer ' + tokenStr
      }
      ///api/score/${studentId}
      this.$axios(`http://localhost:8080/score/${studentId}`,{headers}).then(res => { 
        console.log(res)
        if(res.data.code == 200) {
          let rootData = res.data.data
          rootData.forEach((element,index) => {
            this.tableDataX.push(`${index + 1}Times`)
            this.tableDataY.push(element.etScore)
          });
          let boxDom = this.$refs["box"];
          let scoreCharts = this.$echarts.init(boxDom);
          let option = {
            xAxis: {
              type: "category",
              data: this.tableDataX
            },
            yAxis: {
              type: "value"
            },
            series: [
              {
                data: this.tableDataY,
                type: "line",
                itemStyle: { normal: { label: { show: true } } }
              }
            ]
          };
          scoreCharts.setOption(option);
          scoreCharts.on("mouseover", params => {
            console.log(params.value);
          });
        }else {
          this.isNull = true
        }
      })
    }
  }
};
</script>

<style lang="less" scoped>
#grade {
  position: relative;
  .box{
    width: 600px;
    height: 400px;
  }
  .notFound {
    position: absolute;
    top: 0px;
    left: 0px;
  }
}
</style>
