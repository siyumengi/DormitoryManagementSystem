<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="维修单号" prop="repairRequestId">
        <el-input
          v-model="queryParams.repairRequestId"
          placeholder="请输入维修单号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="宿管" prop="dormitoryKeeperId">
        <el-input
          v-model="queryParams.dormitoryKeeperId"
          placeholder="请输入宿管"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>

      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">

      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['system:assignment:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['system:assignment:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:assignment:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="assignmentList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="序号" align="center" prop="id" />
      <el-table-column label="维修单号" align="center" prop="repairRequestId" />
      <el-table-column label="宿管" align="center" prop="dormitoryKeeperId" />
      <el-table-column label="派工时间" align="center" prop="assignmentTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.assignmentTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="开始维修时间" align="center" prop="startRepairTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.startRepairTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="完成维修时间" align="center" prop="finishRepairTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.finishRepairTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="派工描述" align="center" prop="description" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:assignment:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:assignment:remove']"
          >删除</el-button>
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

    <!-- 添加或修改assignment对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="维修请求ID" prop="repairRequestId">
          <el-input v-model="form.repairRequestId" placeholder="请输入维修请求ID" />
        </el-form-item>
        <el-form-item label="宿管ID" prop="dormitoryKeeperId">
          <el-input v-model="form.dormitoryKeeperId" placeholder="请输入宿管ID" />
        </el-form-item>
        <el-form-item label="派工时间" prop="assignmentTime">
          <el-date-picker clearable
            v-model="form.assignmentTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择派工时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="开始维修时间" prop="startRepairTime">
          <el-date-picker clearable
            v-model="form.startRepairTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择开始维修时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="完成维修时间" prop="finishRepairTime">
          <el-date-picker clearable
            v-model="form.finishRepairTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择完成维修时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="派工描述" prop="description">
          <el-input v-model="form.description" placeholder="请输入派工描述" />
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
import { listAssignment, getAssignment, delAssignment, addAssignment, updateAssignment } from "@/api/system/assignment";

export default {
  name: "Assignment",
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
      // assignment表格数据
      assignmentList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        repairRequestId: null,
        dormitoryKeeperId: null,
        assignmentTime: null,
        startRepairTime: null,
        finishRepairTime: null,
        description: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        repairRequestId: [
          { required: true, message: "维修请求ID不能为空", trigger: "blur" }
        ],
        dormitoryKeeperId: [
          { required: true, message: "宿管ID不能为空", trigger: "blur" }
        ],
        createTime: [
          { required: true, message: "创建时间不能为空", trigger: "blur" }
        ],
        updateTime: [
          { required: true, message: "更新时间不能为空", trigger: "blur" }
        ]
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询assignment列表 */
    getList() {
      this.loading = true;
      listAssignment(this.queryParams).then(response => {
        this.assignmentList = response.rows;
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
        repairRequestId: null,
        dormitoryKeeperId: null,
        assignmentTime: null,
        startRepairTime: null,
        finishRepairTime: null,
        description: null,
        createTime: null,
        updateTime: null
      };
      this.resetForm("form");
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
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加assignment";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getAssignment(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改assignment";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateAssignment(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addAssignment(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除assignment编号为"' + ids + '"的数据项？').then(function() {
        return delAssignment(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/assignment/export', {
        ...this.queryParams
      }, `assignment_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
