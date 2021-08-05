<template>
  <el-upload
      class="upload-demo"
      name="image"
      action="https://127.0.0.1:8443/post"
      :on-success="handle_success"
      multiple
      :limit="3"
      :file-list="fileList">
    <el-button size="small" type="primary">点击上传</el-button>
  </el-upload>
</template>
<script>
export default {
  methods: {
    handle_success(res) {
      alert(res)
    }
  }
}
</script>
