
<template>
  <section class="description">
    <p class="title">Manage Repo introduction</p>
    <p class="content">The design of the question bank table is different from that of the common data table.
      It is divided into three tables, which are multiple-choice question bank table, fill-in-the-blank question bank table, judgment question question bank table,
      Each table saves the corresponding type of question bank, and associates the question bank with the test questions through an intermediate table.
      This constitutes a complete test paper.
    </p>
  </section>
</template>

<style lang="less" scoped>
.description {
  margin-left: 40px;
  .title {
    font-size: 22px;
    font-weight: 400;
    color: rgb(31, 47, 61);
  }
  .content {
    width: 600px;
    background-color: #FAF5F2;
    padding: 16px 32px;
    border-radius: 4px;
    border-left: 5px solid #FDC8C8;
    margin: 20px 0px;
  }
}
</style>
