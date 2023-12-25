<template>
  <div class="app-container">
    <el-form :model="queryRecord" ref="queryRecord" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="电费余额" prop="studentId">
        <el-input
          v-model="queryRecord.electricityFee"
          placeholder="电费余额"
          clearable
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleUpdate">缴费</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:record:export']"
        >导出
        </el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="recordList" @selection-change="handleSelectionChange">
      <el-table-column label="学号" align="center" prop="studentId"/>
      <el-table-column label="学生姓名" align="center" prop="studentName"/>
      <el-table-column label="宿舍楼" align="center" prop="residenceHallId">
        <template #default="scope">
          {{ getResidenceHallName(scope.row.residenceHallId) }}
        </template>
      </el-table-column>
      <el-table-column label="房间号" align="center" prop="roomNumber"/>
      <el-table-column label="缴费金额" align="center" prop="amount"/>
      <el-table-column label="缴费状态" align="center" prop="paymentStatus"/>
      <el-table-column label="缴费时间" align="center" prop="paymentTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.paymentTime, '{y}-{m}-{d}') }}</span>
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

    <!--     添加或修改record对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="缴费金额" prop="amount">
          <el-input v-model="form.amount" placeholder="请输入缴费金额"/>
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
import {listBalance, getRecord, updateBalance} from "@/api/system/sbalance";

export default {
  name: "Record",
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
      // record表格数据
      recordList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        studentId: null,
        studentName: null,
        studentContact: null,
        residenceHallId: null,
        roomNumber: null,
        amount: null,
        paymentStatus: null,
        paymentTime: null,
        isDeleted: null
      },
      queryRecord: {
        waterFee: null,
      },
      // 表单参数
      form: {},
      num: 0,
      // 表单校验
      rules: {
        select: [{
          required: true,
          message: '请选择缴费类型',
          trigger: 'change'
        }],
        studentId: [
          {required: true, message: "学生ID不能为空", trigger: "blur"}
        ],
        studentName: [
          {required: true, message: "学生姓名不能为空", trigger: "blur"}
        ],
        studentContact: [
          {required: true, message: "学生联系方式不能为空", trigger: "blur"}
        ],
        residenceHallId: [
          {required: true, message: "宿舍楼ID不能为空", trigger: "blur"}
        ],
        roomNumber: [
          {required: true, message: "宿舍房间号不能为空", trigger: "blur"}
        ],
        amount: [
          {required: true, message: "缴费金额不能为空", trigger: "blur"}
        ],
        paymentStatus: [
          {required: true, message: "缴费状态不能为空", trigger: "change"}
        ],
        createTime: [
          {required: true, message: "创建时间不能为空", trigger: "blur"}
        ],
        updateTime: [
          {required: true, message: "更新时间不能为空", trigger: "blur"}
        ],
        isDeleted: [
          {required: true, message: "是否删除标记不能为空", trigger: "blur"}
        ]
      },
      selectOptions: [{
        "label": "水费",
        "value": 1
      }, {
        "label": "电费",
        "value": 2
      }],
    };
  },
  created() {
    this.getList();
    this.getRecord();
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

    /** 查询record列表 */
    getList() {
      this.loading = true;
      listBalance(this.queryParams).then(response => {
        this.recordList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    /** 查询record */
    getRecord() {
      getRecord(1).then(response => {
        this.queryRecord = response.data;
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
        studentId: null,
        studentName: null,
        studentContact: null,
        residenceHallId: null,
        roomNumber: null,
        amount: null,
        paymentStatus: null,
        paymentTime: null,
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
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "缴费";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getRecord(1).then(response => {
        this.queryRecord = response.data;
        this.open = true;
        this.title = "缴费";
      });
    },
    /** 提交按钮 */
    submitForm() {
      updateBalance(this.form.amount).then(response => {
        this.$modal.msgSuccess("修改成功");
        this.open = false;
        this.getList();
        this.getRecord();
      });


    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$modal.confirm('是否确认删除record编号为"' + ids + '"的数据项？').then(function () {
        return delRecord(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {
      });
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/record/export', {
        ...this.queryParams
      }, `record_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
