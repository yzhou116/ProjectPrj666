<template>
    <div>
      <button @click="login">Log in</button>
    </div>
  </template>
  <script>
import { useAuth0 } from '../../../node_modules/@auth0/auth0-vue';
import {mapState} from 'vuex'
    export default {
      name: "authlogin",
     
      methods: {
       
        login() {
          const  loginWithRedirect  = useAuth0();
        
           loginWithRedirect();
          
        }
  /* setup() {
        const { loginWithRedirect } = useAuth0();
  
        return {
          login: () => {
            loginWithRedirect();
          }
        };
      } */
      }
    
    };
  </script>