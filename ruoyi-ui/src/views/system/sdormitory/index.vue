<template>
  <div class="app-container">


    <el-table v-loading="loading" :data="studentList" @selection-change="handleSelectionChange">

      <el-table-column label="序号" align="center" prop="id"/>
      <el-table-column label="学号" align="center" prop="studentNumber"/>
      <el-table-column label="姓名" align="center" prop="name"/>
      <el-table-column label="性别" align="center" prop="gender"/>
      <el-table-column label="年龄" align="center" prop="age"/>
      <el-table-column label="专业" align="center" prop="major"/>
      <el-table-column label="班级名称" align="center" prop="className"/>
      <el-table-column label="联系电话" align="center" prop="phone"/>
      <el-table-column label="宿舍楼ID" align="center" prop="residenceHallId">
        <template #default="scope">
          {{ getResidenceHallName(scope.row.residenceHallId) }}
        </template>
      </el-table-column>
      <el-table-column label="宿舍房间号" align="center" prop="dormitoryRoomNumber"/>
    </el-table>


  </div>
</template>

<script>
import {listStudent, getStudent, delStudent, addStudent, updateStudent} from "@/api/system/sdormitory";

export default {
  name: "Student",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // student表格数据
      studentList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        studentNumber: null,
        name: null,
        gender: null,
        age: null,
        major: null,
        className: null,
        phone: null,
        residenceHallId: null,
        dormitoryRoomNumber: null,
        moveInDate: null,
        moveOutDate: null,
        isDeleted: null
      },
      // 表单参数
      form: {},
    }

  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询student列表 */
    getList() {
      this.loading = true;
      listStudent(1).then(response => {
        this.studentList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        id: null,
        studentNumber: null,
        name: null,
        gender: null,
        age: null,
        major: null,
        className: null,
        phone: null,
        residenceHallId: null,
        dormitoryRoomNumber: null,
        moveInDate: null,
        moveOutDate: null,
        createTime: null,
        updateTime: null,
        isDeleted: null
      };
      this.resetForm("form");
    },
    getResidenceHallName(residenceHallId) {
      const residenceHalls = {
        1: '宿舍楼1',
        2: '宿舍楼2',
        3: '宿舍楼3',
        4: '宿舍楼4'
      };
      return residenceHalls[residenceHallId];
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加student";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getStudent(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改student";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateStudent(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addStudent(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$modal.confirm('是否确认删除student编号为"' + ids + '"的数据项？').then(function () {
        return delStudent(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {
      });
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/student/export', {
        ...this.queryParams
      }, `student_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
