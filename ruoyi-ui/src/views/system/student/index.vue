<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="学号" prop="studentNumber">
        <el-input
          v-model="queryParams.studentNumber"
          placeholder="请输入学号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="姓名" prop="name">
        <el-input
          v-model="queryParams.name"
          placeholder="请输入姓名"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="年龄" prop="age">
        <el-input
          v-model="queryParams.age"
          placeholder="请输入年龄"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="专业" prop="major">
        <el-input
          v-model="queryParams.major"
          placeholder="请输入专业"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="班级名称" prop="className">
        <el-input
          v-model="queryParams.className"
          placeholder="请输入班级名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="联系电话" prop="phone">
        <el-input
          v-model="queryParams.phone"
          placeholder="请输入联系电话"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="宿舍楼ID" prop="residenceHallId">
        <el-input
          v-model="queryParams.residenceHallId"
          placeholder="请输入宿舍楼"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="房间号" prop="dormitoryRoomNumber">
        <el-input
          v-model="queryParams.dormitoryRoomNumber"
          placeholder="请输入宿舍房间号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <!--      <el-form-item label="入住日期" prop="moveInDate">-->
      <!--        <el-date-picker clearable-->
      <!--          v-model="queryParams.moveInDate"-->
      <!--          type="date"-->
      <!--          value-format="yyyy-MM-dd"-->
      <!--          placeholder="请选择入住日期">-->
      <!--        </el-date-picker>-->
      <!--      </el-form-item>-->
      <!--      <el-form-item label="退宿日期" prop="moveOutDate">-->
      <!--        <el-date-picker clearable-->
      <!--          v-model="queryParams.moveOutDate"-->
      <!--          type="date"-->
      <!--          value-format="yyyy-MM-dd"-->
      <!--          placeholder="请选择退宿日期">-->
      <!--        </el-date-picker>-->
      <!--      </el-form-item>-->
      <!--      <el-form-item label="是否删除标记" prop="isDeleted">-->
      <!--        <el-input-->
      <!--          v-model="queryParams.isDeleted"-->
      <!--          placeholder="请输入是否删除标记"-->
      <!--          clearable-->
      <!--          @keyup.enter.native="handleQuery"-->
      <!--        />-->
      <!--      </el-form-item>-->
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['system:student:add']"
        >新增
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['system:student:edit']"
        >修改
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['system:student:remove']"
        >删除
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:student:export']"
        >导出
        </el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="studentList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center"/>
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
      <el-table-column label="入住日期" align="center" prop="moveInDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.moveInDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="退宿日期" align="center" prop="moveOutDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.moveOutDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:student:edit']"
          >修改
          </el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:student:remove']"
          >删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改student对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="学号" prop="studentNumber">
          <el-input v-model="form.studentNumber" placeholder="请输入学号"/>
        </el-form-item>
        <el-form-item label="姓名" prop="name">
          <el-input v-model="form.name" placeholder="请输入姓名"/>
        </el-form-item>
        <el-form-item label="年龄" prop="age">
          <el-input v-model="form.age" placeholder="请输入年龄"/>
        </el-form-item>
        <el-form-item label="专业" prop="major">
          <el-input v-model="form.major" placeholder="请输入专业"/>
        </el-form-item>
        <el-form-item label="班级名称" prop="className">
          <el-input v-model="form.className" placeholder="请输入班级名称"/>
        </el-form-item>
        <el-form-item label="联系电话" prop="phone">
          <el-input v-model="form.phone" placeholder="请输入联系电话"/>
        </el-form-item>
        <el-form-item label="宿舍楼ID" prop="residenceHallId">
          <el-input v-model="form.residenceHallId" placeholder="请输入宿舍楼ID"/>
        </el-form-item>
        <el-form-item label="宿舍房间号" prop="dormitoryRoomNumber">
          <el-input v-model="form.dormitoryRoomNumber" placeholder="请输入宿舍房间号"/>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import {listStudent, getStudent, delStudent, addStudent, updateStudent} from "@/api/system/student";

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
      // 表单校验
      rules: {
        studentNumber: [
          {required: true, message: "学号不能为空", trigger: "blur"}
        ],
        name: [
          {required: true, message: "姓名不能为空", trigger: "blur"}
        ],
        gender: [
          {required: true, message: "性别不能为空", trigger: "blur"}
        ],
        age: [
          {required: true, message: "年龄不能为空", trigger: "blur"}
        ],
        major: [
          {required: true, message: "专业不能为空", trigger: "blur"}
        ],
        className: [
          {required: true, message: "班级名称不能为空", trigger: "blur"}
        ],
        phone: [
          {required: true, message: "联系电话不能为空", trigger: "blur"}
        ],
        residenceHallId: [
          {required: true, message: "宿舍楼ID不能为空", trigger: "blur"}
        ],
        dormitoryRoomNumber: [
          {required: true, message: "宿舍房间号不能为空", trigger: "blur"}
        ],
        // moveInDate: [
        //   {required: true, message: "入住日期不能为空", trigger: "blur"}
        // ],
        // createTime: [
        //   {required: true, message: "创建时间不能为空", trigger: "blur"}
        // ],
        // updateTime: [
        //   {required: true, message: "更新时间不能为空", trigger: "blur"}
        // ],
        // isDeleted: [
        //   {required: true, message: "是否删除标记不能为空", trigger: "blur"}
        // ]
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询student列表 */
    getList() {
      this.loading = true;
      listStudent(this.queryParams).then(response => {
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
      console.log(this.queryParams)
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
