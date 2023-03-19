import Vue from 'vue'
import Router from 'vue-router'
Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'login', 
      component: () => import('@/components/common/login')
    },
    {path: '/anonymousExam',
    name: 'anonymousExam',
    component: () => import('@/components/student/anonymousExam')
  },
    {
    path: '/anonymousanswer',
    name: 'anonymousanswer',
    component: () => import('@/components/student/anonymousanswer')
  },

    {
      path: '/index', 
      component: () => import('@/components/admin/index'),
 
      children: [
        {
          path: '/', 
          component: () => import('@/components/common/hello')
        },
        {
          path:'/grade', 
          component: () => import('@/components/charts/grade')
        },
        {
          path: '/selectExamToPart', 
          component: () => import('@/components/teacher/selectExamToPart')
        },
        {
          path: '/scorePart',
          component: () => import('@/components/charts/scorePart')
        },
        {
          path: '/surveyPart',
          component: () => import('@/components/charts/surveyPart')
        },
        {
          path: '/allStudentsGrade', 
          component: () => import('@/components/teacher/allStudentsGrade')
        },
        {
          path: '/examDescription', 
          component: () => import('@/components/teacher/examDescription')
        },
        {
          path: '/selectExam', 
          component: () => import('@/components/teacher/selectExam')
        },
        {
          path: '/addExam', 
          component: () => import('@/components/teacher/addExam')
        },
        {
          path: '/answerDescription', 
          component: ()=> import('@/components/teacher/answerDescription')
        },
        {
          path: '/selectAnswer',
          component: () => import('@/components/teacher/selectAnswer')
        },
        {
          path: '/addAnswer', 
          component: () => import('@/components/teacher/addAnswer')
        },
        {
          path: '/addAnswerChildren', 
          component: () => import('@/components/teacher/addAnswerChildren')
        },
        {
          path: '/studentManage', 
          component: () => import('@/components/teacher/studentManage')
        },
        {
          path: '/addStudent', 
          component: () => import('@/components/teacher/addStudent')
        },
        {
          path: '/teacherManage',
          component: () => import('@/components/admin/tacherManage')
        },
        {
          path: '/addTeacher',
          component: () => import ('@/components/admin/addTeacher')
        }

      ]
    },
    {
      path: '/student',
      component: () => import('@/components/student/index'),
      children: [
        {path:"/",component: ()=> import('@/components/student/myExam')},
        {path:'/startExam', component: () => import('@/components/student/startExam')},
        {path: '/manager', component: () => import('@/components/student/manager')},
        {path: '/examMsg', component: () => import('@/components/student/examMsg')},

        {path: '/examPublic', component: () => import('@/components/student/examPublic')},
        {path: '/message', component: () => import('@/components/student/message')},
        {path: '/studentScore', component: () => import("@/components/student/answerScore")},
       
        {    path: '/subscript',
             component: () => import("@/components/student/subscript")
        },
        {path: '/scoreTable', component: () => import("@/components/student/scoreTable")}
      ]
    },
    {path: '/answer',component: () => import('@/components/student/answer')},
   
  ]
})
