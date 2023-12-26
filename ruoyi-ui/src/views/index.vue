<template>
  <div v-if="this.username !== 'admin'">
    <el-form ref="elForm" :model="formData" :rules="rules" size="medium" label-width="100px">
      <el-form-item label="学号" prop="studentNumber" clearable>
        <el-input v-model="formData.studentNumber" placeholder="请输入学号" :style="{width: '100%'}"></el-input>
      </el-form-item>
      <el-form-item label="姓名" prop="name" clearable>
        <el-input v-model="formData.name" placeholder="请输入姓名" clearable :style="{width: '100%'}"></el-input>
      </el-form-item>
      <el-form-item label="性别" prop="gender" clearable>
        <el-input v-model="formData.gender" placeholder="请输入性别" clearable :style="{width: '100%'}"></el-input>
      </el-form-item>
      <el-form-item label="性别" prop="age" clearable>
        <el-input v-model="formData.age" placeholder="请输入性别" clearable :style="{width: '100%'}"></el-input>
      </el-form-item>
      <el-form-item label="班级" prop="className" clearable>
        <el-input v-model="formData.className" placeholder="请输入班级" clearable :style="{width: '100%'}">
        </el-input>
      </el-form-item>
      <el-form-item label="专业" prop="major" clearable>
        <el-input v-model="formData.major" placeholder="请输入专业" clearable :style="{width: '100%'}"></el-input>
      </el-form-item>
      <el-form-item label="联系电话" prop="phone" clearable>
        <el-input v-model="formData.phone" placeholder="请输入联系电话" clearable :style="{width: '100%'}">
        </el-input>
      </el-form-item>
      <el-form-item label="宿舍楼" prop="residenceHallId" clearable>
        <el-input v-model="formData.residenceHallId" placeholder="请输入宿舍楼" clearable
                  :style="{width: '100%'}"></el-input>
      </el-form-item>
      <el-form-item label="房间号" prop="dormitoryRoomNumber" clearable>
        <el-input v-model="formData.dormitoryRoomNumber" placeholder="请输入房间号" clearable
                  :style="{width: '100%'}"></el-input>
      </el-form-item>
    </el-form>
  </div>
  <div v-else>

    <img src="../../src/assets/images/1.jpg" alt="" style="width: 100%;">
  </div>

</template>
<script>
import {getStudent} from "@/api/system/student";

export default {
  components: {},
  props: [],
  data() {
    return {
      formData: {
        studentNumber: undefined,
        name: undefined,
        gender: undefined,
        age: undefined,
        className: '',
        major: undefined,
        phone: '',
        residenceHallId: undefined,
        dormitoryRoomNumber: undefined,
      },
      rules: {
        studentNumber: [],
        name: [{
          required: true,
          message: '请输入姓名',
          trigger: 'blur'
        }],
        gender: [{
          required: true,
          message: '请输入性别',
          trigger: 'blur'
        }],
        age: [{
          required: true,
          message: '请输入性别',
          trigger: 'blur'
        }],
        className: [{
          required: true,
          message: '请输入班级',
          trigger: 'blur'
        }],
        major: [{
          required: true,
          message: '请输入专业',
          trigger: 'blur'
        }],
        phone: [{
          required: true,
          message: '请输入联系电话',
          trigger: 'blur'
        }],
        residenceHallId: [{
          required: true,
          message: '请输入宿舍楼',
          trigger: 'blur'
        }],
        dormitoryRoomNumber: [{
          required: true,
          message: '请输入房间号',
          trigger: 'blur'
        }],
      },
      username: "",
    }
  },
  computed: {},
  watch: {},
  created() {
    this.getList();
    this.username = this.$store.state.user.name;
  },
  mounted() {
  },
  methods: {
    getList() {
      this.loading = true;
      getStudent(1).then(response => {
        // 转换成 1 宿舍楼1 的规律
        response.data.residenceHallId = '宿舍楼' + response.data.residenceHallId;
        this.formData = response.data;
      });
    },
    submitForm() {
      this.$refs['elForm'].validate(valid => {
        if (!valid) return
        // TODO 提交表单
      })
    },
    resetForm() {
      this.$refs['elForm'].resetFields()
    },
  }
}

</script>
<style>
</style>
