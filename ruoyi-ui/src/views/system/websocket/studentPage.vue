<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="学生学号" prop="studentNumber">
        <el-input
          v-model="queryParams.studentNumber"
          placeholder="请输入学生学号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="学生姓名" prop="studentName">
        <el-input
          v-model="queryParams.studentName"
          placeholder="请输入学生姓名"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="状态" prop="studentStatus">
        <el-select v-model="queryParams.studentStatus" placeholder="学生状态" clearable>
          <el-option
            v-for="dict in dict.type.sys_normal_disable"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
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
          v-hasPermi="['system:student:add']"
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
          v-hasPermi="['system:post:edit']"
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
          v-hasPermi="['system:post:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:post:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="studentList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="学生编号" align="center" prop="studentId" />
      <el-table-column label="学生姓名" align="center" prop="studentName" />
      <el-table-column label="性别" align="center" prop="studentSex" />
      <el-table-column label="年龄" align="center" prop="studentAge" />
      <el-table-column label="班级" align="center" prop="studentClassroom" />
      <el-table-column label="学号" align="center" prop="studentNumber" />
      <el-table-column label="学院" align="center" prop="studentCollege" />
      <el-table-column label="专业" align="center" prop="studentMajor" />
<!--      <el-table-column label="人员图片" align="center" prop="studentPhoto" />-->
      <el-table-column label="入学时间" align="center" prop="enrollDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.enrollDate) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="爱好" align="center" prop="studentHobby" />
      <el-table-column label="状态" align="center" prop="studentStatus">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.sys_normal_disable" :value="scope.row.studentStatus"/>
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
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:student:remove']"
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

<!--    &lt;!&ndash; 添加或修改岗位对话框 &ndash;&gt;-->
<!--    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>-->
<!--      <el-form ref="form" :model="form" :rules="rules" label-width="80px">-->
<!--        <el-form-item label="学生名称" prop="studentName">-->
<!--          <el-input v-model="form.studentName" placeholder="请输入学生名称" />-->
<!--        </el-form-item>-->
<!--        <el-form-item label="学生性别" prop="studentSex">-->
<!--          <el-input v-model="form.studentSex" placeholder="请输入性别" />-->
<!--        </el-form-item>-->
<!--        <el-form-item label="学生年龄" prop="studentAge">-->
<!--          <el-input v-model="form.studentAge" placeholder="请输入年龄" />-->
<!--        </el-form-item>-->
<!--        <el-form-item label="学生班级" prop="studentClassroom">-->
<!--          <el-input v-model="form.studentClassroom" placeholder="请输入班级" />-->
<!--        </el-form-item>-->
<!--        <el-form-item label="学生学号" prop="studentNumber">-->
<!--          <el-input v-model="form.studentNumber" placeholder="请输入学号" />-->
<!--        </el-form-item>-->
<!--        <el-form-item label="学生学院" prop="studentCollege">-->
<!--          <el-input v-model="form.studentCollege" placeholder="请输入学院" />-->
<!--        </el-form-item>-->
<!--        <el-form-item label="学生专业" prop="studentMajor">-->
<!--          <el-input v-model="form.studentMajor" placeholder="请输入专业" />-->
<!--        </el-form-item>-->
<!--        <el-form-item label="入学时间" prop="enrollDate">-->
<!--          <el-date-picker type="date" placeholder="选择日期" v-model="form.enrollDate"-->
<!--                          value-format="yyyy-MM-dd HH:mm:ss" >-->
<!--          </el-date-picker>-->
<!--        </el-form-item>-->
<!--        <el-form-item label="人员图片" prop="studentPhoto">-->
<!--          <el-input v-model="form.studentPhoto" placeholder="请选择图片" />-->
<!--        </el-form-item>-->
<!--        <el-form-item label="爱好" prop="studentHobby">-->
<!--          <el-input v-model="form.studentHobby" placeholder="请输入爱好" />-->
<!--        </el-form-item>-->
<!--        <el-form-item label="学生状态" prop="studentStatus">-->
<!--          <el-radio-group v-model="form.studentStatus">-->
<!--            <el-radio-->
<!--              v-for="dict in dict.type.sys_normal_disable"-->
<!--              :key="dict.value"-->
<!--              :label="dict.value"-->
<!--            >{{dict.label}}</el-radio>-->
<!--          </el-radio-group>-->
<!--        </el-form-item>-->
<!--      </el-form>-->
<!--      <div slot="footer" class="dialog-footer">-->
<!--        <el-button type="primary" @click="submitForm">确 定</el-button>-->
<!--        <el-button @click="cancel">取 消</el-button>-->
<!--      </div>-->
<!--    </el-dialog>-->




    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">

        <el-row>
          <el-col :span="16">
            <el-form-item label="学生名称" prop="studentName">
              <el-input v-model="form.studentName" placeholder="请输入学生名称" />
            </el-form-item>
            <el-form-item label="学生性别" prop="studentSex">
              <el-input v-model="form.studentSex" placeholder="请输入性别" />
            </el-form-item>
            <el-form-item label="学生年龄" prop="studentAge">
              <el-input v-model="form.studentAge" placeholder="请输入年龄" />
            </el-form-item>
          </el-col>
        </el-row>

        <!--人脸图片选择-->
        <el-row>
          <el-form-item label="人员图片" prop="studentPhoto">  <!--faceImgPath -->
            <el-upload ref="upload"
                       :action="imgUpload.url"
                       :headers="imgUpload.headers"
                       :http-request="uploadFile"
                       list-type="picture-card"
                       accept=".jpg"
                       :limit="limit"
                       :auto-upload="true"
                       :on-exceed="handleExceed"
                       :on-success="handlePictureSuccess"
                       :before-upload="beforeAvatarUpload"
                       :on-preview="handlePictureCardPreview"
                       :on-remove="handleRemove"
                       :show-file-list="true"
                       :file-list="fileList"
            >

              <i class="el-icon-plus"></i>
            </el-upload>
            <el-dialog :visible.sync="dialogVisible">
              <img width="100%"  v-if="imageUrl" :src="imageUrl" v-model="form.studentPhoto"  alt="">
            </el-dialog>
          </el-form-item>
        </el-row>


        <el-form-item label="学生班级" prop="studentClassroom">
          <el-input v-model="form.studentClassroom" placeholder="请输入班级" />
        </el-form-item>
        <el-form-item label="学生学号" prop="studentNumber">
          <el-input v-model="form.studentNumber" placeholder="请输入学号" />
        </el-form-item>
        <el-form-item label="学生学院" prop="studentCollege">
          <el-input v-model="form.studentCollege" placeholder="请输入学院" />
        </el-form-item>
        <el-form-item label="学生专业" prop="studentMajor">
          <el-input v-model="form.studentMajor" placeholder="请输入专业" />
        </el-form-item>
        <el-form-item label="入学时间" prop="enrollDate">
          <el-date-picker type="date" placeholder="选择日期" v-model="form.enrollDate"
                          value-format="yyyy-MM-dd HH:mm:ss" >
          </el-date-picker>
        </el-form-item>
        <el-form-item label="爱好" prop="studentHobby">
          <el-input v-model="form.studentHobby" placeholder="请输入爱好" />
        </el-form-item>
        <el-form-item label="学生状态" prop="studentStatus">
          <el-radio-group v-model="form.studentStatus">
            <el-radio
              v-for="dict in dict.type.sys_normal_disable"
              :key="dict.value"
              :label="dict.value"
            >{{dict.label}}</el-radio>
          </el-radio-group>
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
  import { listStudent, getStudent, delStudent, addStudent, updateStudent } from "@/api/system/websocketStudent";
  import { delImgFile, uploadImgFile  } from "@/api/system/common";
  import { getToken } from "@/utils/auth";

export default {
  name: "Student",
  dicts: ['sys_normal_disable'],
  data: function () {
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
      // 岗位表格数据
      // postList: [],

      //图片地址
      imageUrl: "",
      //图片详情弹窗显示状态
      dialogVisible:false,
      imgUpload: {
        // 设置上传的请求头部
        headers: {
          Authorization: "Bearer " + getToken()
        },
        // 图片上传的方法地址:
        url: process.env.VUE_APP_BASE_API + "/common/articleImg",
      },
      //编辑时的图片路径
      // imagePath: undefined,
      //页面上存的暂时图片地址List
      fileList: [{url: ""}],
      //选中的图片文件
      imgFile: undefined,
      //允许上传的图片数量
      limit: 1,


      studentList: [],

      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        studentNumber: undefined,
        studentName:undefined,
        studentStatus: undefined
      },
      // 表单参数

      columns: [
        { key: 0, label: `入学图片`, visible: true },
      ],


      form: {},
      // 表单校验
      rules: {
        studentName: [
          {required: true, message: "学生名称不能为空", trigger: "blur"}
        ],
        studentSex: [
          {required: true, message: "学生性别不能为空", trigger: "blur"}
        ],
        studentAge: [
          {required: true, message: "学生年龄不能为空", trigger: "blur"}
        ],
        studentNumber: [
          { required: true, message: "学生学号不能为空", trigger: "blur" },
          {
            pattern: /^[0-9]/,
            message: "请输入数字",
            trigger: "blur"
          },
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {

    /** 头像上传 */
    handleAvatarSuccess(res, file) {
      this.imageUrl = URL.createObjectURL(file.raw);
    },
    beforeAvatarUpload(file) {
      const isJPG = file.type === 'image/jpeg';
      const isLt2M = file.size / 1024 / 1024 < 2;

      if (!isJPG) {
        this.$message.error('上传头像图片只能是 JPG 格式!');
      }
      if (!isLt2M) {
        this.$message.error('上传头像图片大小不能超过 2MB!');
      }
      return isJPG && isLt2M;
    },

    //图片预览
    handlePictureCardPreview(file) {
      console.log("图片预览",file);
      this.imageUrl = file.url;
      this.dialogVisible = true;
    },
    //图片上传成功后的回调
    handlePictureSuccess(res, file) {
      console.log("图片上传成功后的回调",file);
      //设置图片访问路径 （articleImg 后台传过来的的上传地址）
      this.imageUrl = file.response.articleImg;
    },
    // 文件个数超出
    handleExceed() {
      this.$modal.msgError(`上传图片数量不能超过 ${this.limit} 个!`);
    },
    //自定义上传实现
    uploadFile(item) {
      console.log("自定义上传实现", item);
      this.imgFile = item.file;
    },
    // 文件上传中处理
    handleFileUploadProgress(event, file, fileList) {
      this.upload.isUploading = true;
    },
    // 文件上传成功处理
    handleFileSuccess(response, file, fileList) {
      this.upload.open = false;
      this.upload.isUploading = false;
      this.$refs.upload.clearFiles();
      this.$alert("<div style='overflow: auto;overflow-x: hidden;max-height: 70vh;padding: 10px 20px 0;'>" + response.msg + "</div>", "导入结果", { dangerouslyUseHTMLString: true });
      this.getList();
    },
    // 提交上传文件
    submitFileForm() {
      this.$refs.upload.submit();
    },
    handleRemove(file, fileList) {
      console.log(file, fileList);
      this.imgFile = undefined;
      // 删除已上传的图片文件
      delImgFile({"path":file.response.articleImg}).then(response => {
        console.log("handleRemove",response)
      });

    },


    /** 查询学生列表 */
    getList() {
      this.loading = true;
      listStudent(this.queryParams).then(response => {
        console.log("=====");
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
        studentId:undefined,
        studentName: undefined,
        studentSex:undefined,
        studentAge:undefined,
        studentClassroom:undefined,
        studentNumber: undefined,
        studentCollege:undefined,
        studentMajor:undefined,
        enrollDate:"",
        studentPhoto:"",
        studentHobby:undefined,
        studentStatus: "0",
      };
      this.imgFile = undefined;
      this.fileList = [];
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
      this.ids = selection.map(item => item.studentId)
      this.single = selection.length!=1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      console.log("sdasdasdasdddddddddddddddddddddddddddddddddddd");
      this.open = true;
      this.title = "添加学生";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const studentId = row.studentId || this.ids;
      getStudent(studentId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改学生";

        //图片回显
        if(this.form.studentPhoto != null &&  this.form.studentPhoto !== ""){
          const path = process.env.VUE_APP_BASE_API + this.form.studentPhoto;
          this.fileList.push( {url: path, response : { articleImg : path } } )
        }
      });
    },

    // /** 提交按钮 */
    // submitForm: function() {
    //   this.$refs["form"].validate(valid => {
    //     if (valid) {
    //       if (this.form.studentId != undefined) {
    //         updateStudent(this.form).then(response => {
    //           this.$modal.msgSuccess("修改成功");
    //           this.open = false;
    //           this.getList();
    //           console.log("updateStudent");
    //         });
    //       } else {
    //         addStudent(this.form).then(response => {
    //           this.$modal.msgSuccess("新增成功");
    //           this.open = false;
    //           this.getList();
    //           console.log("addStudent");
    //         });
    //       }
    //     }
    //   });
    // },

    /** 提交按钮 */
    submitForm: function() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if(this.imgFile != undefined){
            // 开始上传文件 新建一个formData
            const formData = new FormData();
            // 通过append向form对象添加数据
            formData.append("file", this.imgFile);
            uploadImgFile(formData).then(response => {
              // console.info("uploadImgFile",response );
              if(response.code == 200){
                // this.form.studentFeature.photo = response.articleImg;
                this.form.studentPhoto=response.articleImg;
                console.log(this.form);
                this.submitData();
              }else{
                this.$modal.msgError(response.msg);
              }
            })
          }else{
            this.submitData();
          }
        }
      });
    },

    submitData(){
      if (this.form.studentId != undefined) {
        updateStudent(this.form).then(response => {
          this.$modal.msgSuccess("修改成功");
          this.open = false;
          this.getList();
        });
      } else {
        addStudent(this.form).then(response => {
          if (response.code == 200){
            this.$modal.msgSuccess("添加成功");
            this.open = false;
            this.getList();

            this.$refs.upload.clearFiles();
          }else{
            this.$message.error(response.msg);
          }
        });
      }
    },


    /** 删除按钮操作 */
    handleDelete(row) {
      const studentIds = row.studentId || this.ids;
      this.$modal.confirm('是否确认删除学生编号为"' + studentIds + '"的数据项？').then(function() {
        return delStudent(studentIds);
        console.log("deleteStudentVue");
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/student/export', {
        ...this.queryParams
      }, `post_${new Date().getTime()}.xlsx`)
    },

    getImgUrl(url){
      if(url == ''){
        return;
      }
      //return require( process.env.VUE_APP_IMG_PATH + url);
      return process.env.VUE_APP_BASE_API + url;
    },
  }
};
</script>

<style>
  .avatar-uploader .el-upload {
    border: 1px dashed #d9d9d9;
    border-radius: 5px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
  }
  .avatar-uploader .el-upload:hover {
    border-color: #409EFF;
  }
  .avatar-uploader-icon {
    font-size: 23px;
    color: #8c939d;
    width: 150px;
    height: 150px;
    line-height: 150px;
    text-align: center;
  }
  .avatar {
    width: 150px;
    height: 150px;
    display: block;
  }
</style>
