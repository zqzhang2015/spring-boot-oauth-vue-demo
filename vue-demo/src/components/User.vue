<template>
  <div>
    <el-form ref="queryForm" :inline="true" :model="queryData">
      <el-form-item label="用户名" prop="username">
        <el-input v-model="queryData.username" auto-complete="off"></el-input>
      </el-form-item>
      <el-form-item label="昵称" prop="nickname">
        <el-input v-model="queryData.nickname" auto-complete="off"></el-input>
      </el-form-item>
    </el-form>
    <el-button type="primary" size="small" @click="find">查询</el-button>
    <el-button type="warning" size="small" @click="resetForm('queryForm')">重置</el-button>
    <el-button type="success" size="small" @click="add">新增</el-button>
    <el-table :data="grid.data"
              highlight-current-row
              v-loading="grid.loading">
      <el-table-column prop="id" label="ID" width="60"></el-table-column>
      <el-table-column prop="username" label="用户名"></el-table-column>
      <el-table-column prop="nickname" label="昵称"></el-table-column>
      <el-table-column prop="state" label="状态" :formatter="formatState"></el-table-column>
      <el-table-column prop="mobile" label="手机号码"></el-table-column>
      <el-table-column prop="email" label="邮箱"></el-table-column>
      <el-table-column prop="createTime" label="创建时间"></el-table-column>
      <el-table-column label="操作" width="150" fixed="right">
        <template slot-scope="scope">
          <el-button type="primary" size="small" @click="edit(scope.$index, scope.row)">编辑</el-button>
          <el-button type="danger" size="small" @click="del(scope.$index, scope.row)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination
      background
      @size-change="onPageSizeChange"
      @current-change="onPageNumChange"
      :current-page="grid.pageNum"
      :page-sizes="[10, 25, 50]"
      :page-size="grid.pageSize"
      layout="total, sizes, prev, pager, next, jumper"
      :total="grid.total">
    </el-pagination>
    <el-dialog :title="editDialog.title" :visible.sync="editDialog.visible" @close="resetForm('editForm')">
      <el-form ref="editForm" :model="editData" label-width="80px">
        <el-form-item label="用户名" prop="username">
          <el-input v-model="editData.username" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="昵称" prop="nickname">
          <el-input v-model="editData.nickname" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="状态" prop="state">
          <el-radio-group v-model="editData.state">
            <el-radio-button :key="index"
                             :label="item.value"
                             v-for="(item, index) in userStates">{{item.name}}
            </el-radio-button>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="手机号码" prop="mobile">
          <el-input v-model="editData.mobile" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="邮箱" prop="email">
          <el-input v-model="editData.email" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="创建日期" prop="createTime">
          <el-input v-model="editData.createTime" auto-complete="off"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="editDialog.visible = false">取 消</el-button>
        <el-button type="primary" @click="submit">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
  import axios from 'axios'

  export default {
    data() {
      return {
        grid:{
          loading:false,
          data:[],
          pageNum:1,
          pageSize:10,
          total:0
        },
        queryData: {
          username: '',
          nickname: ''
        },
        userStates: [],
        editDialog:{
          visible:false,
          title:''
        },
        editUrl: '',
        editData: {
          id: '',
          username: '',
          nickname: '',
          state: 0,
          mobile: '',
          email: '',
          createTime: ''
        }
      }
    },
    methods: {
      onPageSizeChange(val) {
        this.grid.pageSize = val;
        this.find();
      },
      onPageNumChange(val) {
        this.grid.pageNum = val;
        this.find();
      },
      resetForm(formName) {
        this.$refs[formName].resetFields();
      },
      formatState(row, column, cellValue, index) {
        for (let i = 0, len = this.userStates.length; i < len; i++) {
          if (parseInt(this.userStates[i].value) === parseInt(cellValue)) {
            return this.userStates[i].name;
          }
        }
      },
      find() {
        axios.get('/api/user/listPage', {
          params: {
            pageNum: this.grid.pageNum,
            pageSize: this.grid.pageSize,
            name: this.queryData.name,
            description:this.queryData.description
          }
        }).then(response => {
          let result = response.data;
          this.grid.data = result.data.rows;
          this.grid.total = result.data.total;
        }).catch(error => {
          this.$message.error(error.msg);
        })
      },
      add() {
        this.editDialog.title = '新增';
        this.editDialog.visible = true;
        this.editUrl = '/api/user/save';
      },
      edit(index, row) {
        this.editDialog.title = '编辑';
        this.editDialog.visible = true;
        this.editData = Object.assign({}, this.editData, JSON.parse(JSON.stringify(row)));
        this.editUrl = '/api/user/update';
      },
      submit() {
        axios.post(this.editUrl, this.editData)
          .then(response => {
            this.editDialog.visible = false;
            this.$message.success(response.data.msg);
            this.find();
          }).catch(error => {
          this.$message.error(error.msg);
        })
      },
      del(index, row) {
        this.$confirm('确定删除？', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          return axios.post('/api/user/delete', {}, {
            params: {
              id: row.id
            }
          })
        }).then(response => {
          this.$message.success(response.data.msg);
          this.find();
        }).catch(error => {
          this.$message.error(error.msg);
        });
      }
    },
    created() {
      this.find();
      axios.get('/api/user/listState')
        .then(response => {
          this.userStates = response.data.data;
        }).catch(error => {
        this.$message.error('用户状态列表拉取失败：' + error.msg);
      });
    }
  }
</script>
