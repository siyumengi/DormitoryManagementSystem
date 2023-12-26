<template>
  <div class="app-container">

    <el-form ref="elForm" :model="formData" :rules="rules" size="medium" label-width="100px">
      <el-form-item label="宿舍楼" prop="dormitoryId">
        <el-input v-model="formData.dormitoryId" placeholder="请输入宿舍楼" :maxlength="11" show-word-limit
                  clearable prefix-icon='el-icon-mobile' :style="{width: '100%'}"></el-input>
      </el-form-item>
      <el-form-item label="房间号" prop="dormitoryRoomNumber">
        <el-input v-model="formData.dormitoryRoomNumber" placeholder="请输入房间号" :maxlength="11" show-word-limit
                  clearable prefix-icon='el-icon-mobile' :style="{width: '100%'}"></el-input>
      </el-form-item>
      <el-form-item label="维修描述" prop="repairDescription">
        <el-input v-model="formData.repairDescription" placeholder="请输入维修描述" :maxlength="11" show-word-limit
                  clearable prefix-icon='el-icon-mobile' :style="{width: '100%'}"></el-input>
      </el-form-item>
      <el-form-item size="large">
        <el-button type="primary" @click="submitForm">提交</el-button>
        <el-button @click="resetForm">重置</el-button>
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
        >导出
        </el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="requestList" >
      <el-table-column label="序号" align="center" prop="id"/>
      <el-table-column label="宿舍" align="center" prop="dormitoryId">
        <template #default="scope">
          {{ getResidenceHallName(scope.row.dormitoryId) }}
        </template>
      </el-table-column>
      <el-table-column label="宿舍房间号" align="center" prop="dormitoryRoomNumber"/>
      <el-table-column label="维修描述" align="center" prop="repairDescription"/>
      <el-table-column label="维修状态" align="center" prop="status">
      </el-table-column>

    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

  </div>
</template>

<script>
import {listRequest, getRequest, delRequest, addRequest, updateRequest} from "@/api/system/srequest";
import {resetForm} from "@/utils/ruoyi";

export default {
  name: "Request",
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
      // request表格数据
      requestList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        studentId: null,
        dormitoryId: null,
        dormitoryRoomNumber: null,
        repairDescription: null,
        status: null,
      },
      // 维修状态
      statusList: [
        {label: "待处理", value: 0},
        {label: "已派工", value: 1},
        {label: "维修中", value: 2},
        {label: "已完成", value: 3},
      ],
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        dormitoryId: [{
          required: true,
          message: '请输入宿舍楼',
          trigger: 'blur'
        }, {
          message: '手机号格式错误',
          trigger: 'blur'
        }],
        dormitoryRoomNumber: [{
          required: true,
          message: '请输入房间号',
          trigger: 'blur'
        }, {
          message: '手机号格式错误',
          trigger: 'blur'
        }],
        repairDescription: [{
          required: true,
          message: '请输入维修描述',
          trigger: 'blur'
        }, {
          message: '手机号格式错误',
          trigger: 'blur'
        }], trigger: "blur"
      },
      formData: {
        dormitoryId: '',
        dormitoryRoomNumber: '',
        repairDescription: '',
      },
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
    /** 查询request列表 */
    getList() {
      this.loading = true;
      listRequest(this.queryParams).then(response => {
        this.requestList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    resetForm() {
      this.$refs['elForm'].resetFields()
    },


    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },

    /** 提交按钮 */
    submitForm() {
      this.$refs["elForm"].validate(valid => {
        console.log(this.formData)


          addRequest(this.formData).then(response => {

            this.$modal.msgSuccess("修改成功");
            this.open = false;
            this.getList();
          });

      });
    },


    /** 导出按钮操作 */
    handleExport() {
      this.download('system/request/export', {
        ...this.queryParams
      }, `request_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
