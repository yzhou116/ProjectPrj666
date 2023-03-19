
<template>
  <div class="table">
    <p class="title">My Score</p>
    <section class="content-el">
      <el-table ref="filterTable" :data="score" v-loading="loading">
        <el-table-column
          prop="answerDate"
          label="Date of Exam"
          sortable
          width="300"
          column-key="answerDate"
          :filters="filter"
          :filter-method="filterHandler">
        </el-table-column>
        <el-table-column
          prop="subject"
          label="Exam Name"
          width="300"
          filter-placement="bottom-end">
          <template slot-scope="scope">
            <el-tag>{{scope.row.subject}}</el-tag>
          </template>
        </el-table-column>
        <el-table-column prop="etScore" label="Exam Score" width="200"></el-table-column>
        <el-table-column label="Result" width="100">
          <template slot-scope="scope">
            <el-tag :type="scope.row.etScore/scope.row.score >= 0.6 ? 'success' : 'danger'">{{scope.row.etScore/scope.row.score >= 0.6 ? "Pass" : "Fail"}}</el-tag>
          </template>
        </el-table-column>
      </el-table>
      <el-row type="flex" justify="center" align="middle" class="pagination">
        <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="pagination.current"
          :page-sizes="[4,6,8,10]"
          :page-size="pagination.size"
          layout="total, sizes, prev, pager, next, jumper"
          :total="pagination.total">
        </el-pagination>
      </el-row>
    </section>
  </div>
</template>

<script>
export default {
  data() {
    return {
      pagination: { 
        current: 1, 
        total: null, 
        size: 10 
      },
      loading: false, 
      score: [], 
      filter: null 
    }
  },
  created() {
    this.getScore()
    this.loading = true 
  },
  methods: {
    getScore() {
      let studentId = this.$cookies.get("cid")
      ///api/score/${this.pagination.current}/${this.pagination.size}/${studentId}
      let tokenStr = this.$session.get('jwt')
      const headers = 
      {
      
       'Authorization' : 'Bearer ' + tokenStr
      }
      this.$axios(`http://localhost:8080/score/${this.pagination.current}/${this.pagination.size}/${studentId}`, {headers}).then(res => {
        if(res.data.code == 200) {
          this.loading = false 
          debugger
          this.score = res.data.data.records
          this.pagination = {...res.data.data}
          let mapVal = this.score.map((element,index) => { 
            let newVal = {}
            newVal.text = element.answerDate
            newVal.value = element.answerDate
            return newVal
          })
          let hash = []
          const newArr = mapVal.reduce((item, next) => { 
            hash[next.text] ? '' : hash[next.text] = true && item.push(next);
            return item
          }, []);
          this.filter = newArr
        }
      })
    },

    handleSizeChange(val) {
      this.pagination.size = val
      this.getScore()
    },

    handleCurrentChange(val) {
      this.pagination.current = val
      this.getScore()
    },
    formatter(row, column) {
      return row.address;
    },
    filterTag(value, row) {
      return row.tag === value;
    },
    filterHandler(value, row, column) {
      const property = column["property"];
      return row[property] === value;
    }
  }
};
</script>

<style lang="less" scoped>
.pagination {
  padding-top: 20px;
}
.table {
  width: 980px;
  margin: 0 auto;
  .title {
    margin: 20px;
  }
  .content-el {
    background-color: #fff;
    padding: 20px;
  }
}
</style>
