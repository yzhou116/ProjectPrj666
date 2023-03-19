import VUE from 'vue'
import VUEX from 'vuex'

VUE.use(VUEX)

const state = {
  isPractice: false, 
  flag: false, 
  userInfo: null,
  menu: [{
      index: '1',
      title: 'Exam Management',
      icon: 'icon-kechengbiao',
      content:[{item1:'Functions',path:'/examDescription'},{item2:'Find Exam',path:'selectExam'},{item3:'Add Exam',path:'/addExam'}],
    },
    {
      index: '2',
      title: 'Repo Management',
      icon: 'icon-tiku',
      content:[{item1:'Functions',path:'/answerDescription'},{item2:'All Repos',path:'/selectAnswer'},{item3:'Add Repos',path:'/addAnswer'},{path: '/addAnswerChildren'}],
    },
    {
      index: '3',
      title: 'Result',
      icon: 'icon-performance',
      content:[{item1:'Send Survey',path:'/allStudentsGrade'},{path: '/grade'},{item2: 'See Result',path: '/selectExamToPart'},{path: '/scorePart'}],
    },
   
  ],
}
const mutations = {
  practice(state,status) {
    state.isPractice = status
  },
  toggle(state) {
    state.flag = !state.flag
  },
  changeUserInfo(state,info) {
    state.userInfo = info
  }
}
const getters = {
 
}
const actions = {
  getUserInfo(context,info) {
    context.commit('changeUserInfo',info)
  },
  getPractice(context,status) {
    context.commit('practice',status)
  }
}
export default new VUEX.Store({
  state,
  mutations,
  getters,
  actions,
  // store
})
