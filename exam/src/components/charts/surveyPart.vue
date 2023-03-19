<template>
  <div class="part">
    <select class="niceselect" v-model="selected.text" @change="onChange($event)">
      <option disabled value>Please select one</option>
      <option
        v-for="(value, key) in myObject"
        v-bind:value="{ thevalue: value, text: key }"
      >{{ key }}</option>
    </select>
    <div v-show="isPie" class="box" ref="box"></div>
    <div v-if="!isPie">
      <span style="white-space: pre-wrap;" v-for="item in this.fillAnswers">
        {{ item }}
        <br />
      </span>
    </div>
  </div>
</template>
  <script>
//import { json } from 'stream/consumers'

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
      },
      myObject: {},
      selected: {
        thevalue: "",
        text: ""
      },
      resData: [],
      multiQ: {
        'A': 0,
        'B': 0,
        'C': 0,
        'D': 0
      },
      TrueAndFalse: {
        'Correct': 0,
        'Wrong': 0,
      },
      isPie: false,
      fillAnswers: []
    }
  },
  created() {
    this.getScoreInfo()

  },
  methods: {
    onChange(event) {
      this.multiQ = {
        'A': 0,
        'B': 0,
        'C': 0,
        'D': 0
      }
      this.TrueAndFalse = {
        'Correct': 0,
        'Wrong': 0,

      }
      this.fillAnswers = []
      this.isPie = false
      console.log(event.target.value["text"])
      console.log(JSON.stringify(this.selected.text));
      console.log(this.selected.text.text);
      console.log(this.selected.text.thevalue);
      console.log(this.selected.text);
      if (this.selected.text.thevalue === 'A' || this.selected.text.thevalue === 'B' || this.selected.text.thevalue === 'C' || this.selected.text.thevalue === 'D') {
        this.isPie = true
        let box = this.$refs['box']
        let charts = this.$echarts.init(box)
        console.log(this.resData);
         //Code Comment:loop the data to find out what value need to assign
        this.resData.forEach(element => {
          console.log(this.selected.text);
          console.log('This is element1 ->' + JSON.stringify(element));
          var key = Object.keys(element)
          console.log("this is keys ->" + key);



          console.log('This is element ->' + element[this.selected.text.text]);

          switch (element[this.selected.text.text]) {
            case 'A':
              this.multiQ["A"]++
              break
            case 'B':
              this.multiQ['B']++
              break
            case 'C':
              this.multiQ["C"]++
              break
            case 'D':
              this.multiQ['D']++

          }
        })
        console.log("this is value " + JSON.stringify(this.multiQ));
        let option = {
          title: {
            text: `${this.selected.text.text}`,
            subtext: 'Pie chart',
            x: 'center'
          },
          tooltip: {
            trigger: 'item',
            //  formatter: "{a}: {b} <br/> {c}Person ({d}%)"
          },
          legend: {
            orient: 'vertical',
            left: 'left',
            data: ['A', 'B', 'C', 'D']
          },
          series: [
            {
              name: 'Score',
              type: 'pie',
              radius: '35%',
              center: ['50%', '35%'],
              data: [
                { value: this.multiQ["A"], name: 'A' },
                { value: this.multiQ['B'], name: 'B' },
                { value: this.multiQ['C'], name: 'C' },
                { value: this.multiQ['D'], name: 'D' },
                //    {value:this.category['F'], name:'Below 60'}
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
      } else if (this.selected.text.thevalue === 'Correct' || this.selected.text.thevalue === 'Wrong') {
        this.isPie = true
        let box = this.$refs['box']
        let charts = this.$echarts.init(box)

        this.resData.forEach(element => {
          switch (element[this.selected.text.text]) {
            case 'Correct':
              this.TrueAndFalse["Correct"]++
              break
            case 'Wrong':
              this.TrueAndFalse['Wrong']++
          }
        })
        console.log("this is value " + JSON.stringify(this.multiQ));
        let option = {
          title: {
            text: `${this.selected.text.text} Chart`,
            subtext: 'Pie chart',
            x: 'center'
          },
          tooltip: {
            trigger: 'item',
            //  formatter: "{a}: {b} <br/> {c}Person ({d}%)"
          },
          legend: {
            orient: 'vertical',
            left: 'left',
            data: ['Correct', 'Wrong']
          },
          series: [
            {
              name: 'Score',
              type: 'pie',
              radius: '35%',
              center: ['50%', '35%'],
              data: [
                { value: this.TrueAndFalse["Correct"], name: 'Correct' },
                { value: this.TrueAndFalse['Wrong'], name: 'Wrong' }

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
      } else {
        this.isPie = false
        this.resData.forEach(element => {
          this.fillAnswers.push(element[this.selected.text.text])
        })

      }
    },


    getScoreInfo() {
      let examCode = this.$route.query.examCode
      this.name = this.$route.query.source
      ///api/scores/${examCode}
      let tokenStr = this.$session.get('jwt')
      const headers =
      {

        'Authorization': 'Bearer ' + tokenStr
      }
      this.$axios(`http://localhost:8080/surveyRes/${examCode}`, { headers }).then(res => {
        debugger;
        let data = res.data.data
        this.resData = res.data.data
        console.log("back data -> " + JSON.stringify(data));
        console.log("First data -> " + JSON.stringify(data[1]))
        this.myObject = data[0];
        if (data.length > 0) {
          let box = this.$refs['box']
          let charts = this.$echarts.init(box)

          /*       data.forEach(element => {
                  switch(element.etScore / 10) {
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
                  }
                }); */
          /*  let option = {
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
                           {value:this.category['A+'], name:'Above 90'},
                           {value:this.category['A'], name:'80-89'},
                           {value:this.category['B'], name:'70-79'},
                           {value:this.category['C'], name:'60-69'},
                           {value:this.category['F'], name:'Below 60'}
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
           }; */
          //   charts.setOption(option)
        } else {
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

.niceselect {
  width: 100%;
  padding: 8px 16px;
  border: 1px solid #ccc;
  border-radius: 4px;
  box-sizing: border-box;
  font-size: 16px;
  font-weight: bold;
  -webkit-appearance: none;
  -moz-appearance: none;
  appearance: none;
  background-color: #fff;
  background-image: url('data:image/svg+xml;utf8,<svg fill="#000" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg"><path d="M7 10l5 5 5-5z"/></svg>');
  background-repeat: no-repeat;
  background-position: right 8px center;
  cursor: pointer;
}

.niceselect:focus {
  outline: none;
  box-shadow: 0 0 0 2px rgba(0, 123, 255, 0.5);
  border-color: rgba(0, 123, 255, 0.5);
}
</style>
  