
<template>
  <footer id="footer">
    <ul>
      <li><a href="javascript:;">About us</a></li>
      <li><a href="javascript:;">Some Text</a></li>
      <li><a href="javascript:;">User Aggreement</a></li>
      <li><a href="" target="_blank">Some text here</a></li>
      <li>@Copyright xxxx-xxxx. ALL Rights Reserved</li>
    </ul>
  </footer>
</template>

<script>
export default {
  name: "myFooter"
}
</script>

<style scoped>
/* #footer a {
  color: #919698;
  font-size: 14px;
}
#footer {
  background-color: #eee;
}
#footer ul {
  margin-top: 40px;
  border-top: 1px solid #d5d5d5;
  display: flex;
  justify-content: center;
  height: 80px;
  line-height: 80px;
}
#footer ul li {
  color: #919698;
  font-size: 14px;
  margin-right: 20px;
} */
#footer a {
  color: #919698;
  font-size: 14px;
}

#footer {
  background-color: #eee;
  position: absolute;

  width: 100%;
  height: 80px;
}

#footer ul {

  border-top: 1px solid #d5d5d5;
  display: flex;
  justify-content: center;
  height: 80px;
  line-height: 80px;
}

#footer ul li {
  color: #919698;
  font-size: 14px;
  margin-right: 20px;
}
</style>
