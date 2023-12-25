<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="宿舍楼" prop="residenceHallId">
        <el-input
          v-model="queryParams.residenceHallId"
          placeholder="请输入宿舍楼"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="宿舍房间号" prop="roomNumber">
        <el-input
          v-model="queryParams.roomNumber"
          placeholder="请输入宿舍房间号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="宿舍面积" prop="size">
        <el-input
          v-model="queryParams.size"
          placeholder="请输入宿舍面积或大小"
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
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['system:dormitory:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['system:dormitory:edit']"
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
          v-hasPermi="['system:dormitory:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:dormitory:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="dormitoryList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="序号" align="center" prop="id" />
      <el-table-column label="宿舍楼" align="center" prop="residenceHallId" >
        <template #default="scope">
          {{ getResidenceHallName(scope.row.residenceHallId) }}
        </template>
      </el-table-column>
      <el-table-column label="宿舍房间号" align="center" prop="roomNumber" />
      <el-table-column label="宿舍面积" align="center" prop="size" />
      <el-table-column label="实际入住数量" align="center" prop="occupancyCount" />
      <el-table-column label="宿舍状态" align="center" prop="status" />
      <el-table-column label="总床位数量" align="center" prop="totalBedCount" />
      <el-table-column label="剩余床位数量" align="center" prop="remainingBedCount" />
      <el-table-column label="宿舍描述" align="center" prop="description" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:dormitory:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:dormitory:remove']"
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

    <!-- 添加或修改dormitory对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="宿舍楼ID" prop="residenceHallId">
          <el-input v-model="form.residenceHallId" placeholder="请输入宿舍楼ID" />
        </el-form-item>
        <el-form-item label="宿舍房间号" prop="roomNumber">
          <el-input v-model="form.roomNumber" placeholder="请输入宿舍房间号" />
        </el-form-item>
        <el-form-item label="宿舍面积" prop="size">
          <el-input v-model="form.size" placeholder="请输入宿舍面积或大小" />
        </el-form-item>
        <el-form-item label="总床位数量" prop="totalBedCount">
          <el-input v-model="form.totalBedCount" placeholder="请输入总床位数量" />
        </el-form-item>
        <el-form-item label="宿舍描述" prop="description">
          <el-input v-model="form.description" placeholder="请输入宿舍描述" />
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
import { listDormitory, getDormitory, delDormitory, addDormitory, updateDormitory } from "@/api/system/dormitory";

export default {
  name: "Dormitory",
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
      // dormitory表格数据
      dormitoryList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        residenceHallId: null,
        roomNumber: null,
        size: null,
        occupancyCount: null,
        status: null,
        totalBedCount: null,
        remainingBedCount: null,
        description: null,
        isDeleted: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        residenceHallId: [
          { required: true, message: "宿舍楼ID不能为空", trigger: "blur" }
        ],
        roomNumber: [
          { required: true, message: "宿舍房间号不能为空", trigger: "blur" }
        ],
        status: [
          { required: true, message: "宿舍状态不能为空", trigger: "change" }
        ],
        totalBedCount: [
          { required: true, message: "总床位数量不能为空", trigger: "blur" }
        ],
        remainingBedCount: [
          { required: true, message: "剩余床位数量不能为空", trigger: "blur" }
        ],
        createTime: [
          { required: true, message: "创建时间不能为空", trigger: "blur" }
        ],
        updateTime: [
          { required: true, message: "更新时间不能为空", trigger: "blur" }
        ],
        isDeleted: [
          { required: true, message: "是否删除标记不能为空", trigger: "blur" }
        ]
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    getResidenceHallName(residenceHallId) {
      const residenceHalls = {
        1: '宿舍楼1',
        2: '宿舍楼2',
        3: '宿舍楼3',
        4: '宿舍楼4'
      };
      return residenceHalls[residenceHallId];
    },
    /** 查询dormitory列表 */
    getList() {
      this.loading = true;
      listDormitory(this.queryParams).then(response => {
        this.dormitoryList = response.rows;
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
        residenceHallId: null,
        roomNumber: null,
        size: null,
        occupancyCount: null,
        status: null,
        totalBedCount: null,
        remainingBedCount: null,
        description: null,
        createTime: null,
        updateTime: null,
        isDeleted: null
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
      this.title = "添加dormitory";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getDormitory(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改dormitory";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateDormitory(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addDormitory(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除dormitory编号为"' + ids + '"的数据项？').then(function() {
        return delDormitory(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/dormitory/export', {
        ...this.queryParams
      }, `dormitory_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
