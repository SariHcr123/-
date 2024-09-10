<template>
  <div>
    <div style="padding:10px 0">
      <el-input style="width: 200px" placeholder="请输入设备编号" suffix-icon="el-icon-search" v-model="num" ></el-input>
      <el-input style="width:200px" placeholder="请输入设备名称" suffix-icon="el-icon-message" class="ml-5" v-model="name"></el-input>
      <el-input style="width:200px" placeholder="请输入设备地址" suffix-icon="el-icon-position" class="ml-5" v-model="address"></el-input>
      <el-button class="ml-5" type="primary" @click="load">搜索</el-button>
      <el-button  type="warning" @click="reset">重置</el-button>
    </div>

    <el-button type="primary" @click="handleAdd">新增设备 <i class="el-icon-circle-plus-outline"></i></el-button>
    <el-popconfirm
        class="ml-5"
        confirm-button-text='确定'
        cancel-button-text='哒咩'
        icon="el-icon-info"
        icon-color="red"
        title="您真的忍心删除我吗？^-^"
        @confirm="delBatch"
    >
      <el-button type="danger" slot="reference">批量删除 <i class="el-icon-remove-outline"></i></el-button>
    </el-popconfirm>

    <el-table :data="tableData" border stripe :header-cell-class-name="'headerBg'"  @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55"></el-table-column>
      <el-table-column prop="id" label="ID" width="80"></el-table-column>
      <el-table-column prop="num" label="设备编号" width="140"></el-table-column>
      <el-table-column prop="name" label="设备名称" width="120"></el-table-column>
      <el-table-column prop="address" label="设备地址"></el-table-column>
      <el-table-column prop="dx" label="x偏移量"></el-table-column>
      <el-table-column prop="dy" label="y偏移量"></el-table-column>
      <el-table-column prop="dz" label="z偏移量"></el-table-column>
      <el-table-column label="操作">
        <template slot-scope="scope">
          <el-popconfirm
              class="ml-5"
              confirm-button-text='确定'
              cancel-button-text='哒咩'
              icon="el-icon-info"
              icon-color="red"
              title="您真的忍心删除我吗？^-^"
              @confirm="del(scope.row.id)"
          >
            <el-button type="danger" slot="reference">删除 <i class="el-icon-remove-outline" ></i></el-button>
          </el-popconfirm>
        </template>
      </el-table-column>
    </el-table>

    <div style="padding:10px 0">
      <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="pageNum"
          :page-sizes="[10, 20, 30, 40]"
          :page-size="pageSize"
          layout="total, sizes, prev, pager, next, jumper"
          :total="total">
      </el-pagination>
    </div>

    <el-dialog title="设备信息" :visible.sync="dialogFormVisible" width="30%">
      <el-form label-width="80px" size="small">
        <el-form-item label="设备编号" size="small">
          <el-input v-model="form.num" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="设备名称" size="small">
          <el-input v-model="form.name" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="设备地址" size="small">
          <el-input v-model="form.address" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="x偏移量" size="small">
          <el-input v-model="form.dx" autocomplete="off"></el-input>
        </el-form-item>
<!--        <el-form-item label="y偏移量" size="small">-->
<!--          <el-input v-model="form.dy" autocomplete="off"></el-input>-->
<!--        </el-form-item>-->
<!--        <el-form-item label="z偏移量" size="small">-->
<!--          <el-input v-model="form.dz" autocomplete="off"></el-input>-->
<!--        </el-form-item>-->
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="save">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
export default {
  name: "Shuju",
  data(){
    return{
      tableData:[],
      name:'',
      multipleSelection:[],
      form:{},
      pageNum:1,
      pageSize:10,
      total:0,
      dialogFormVisible:false,
      num:"",
      email:"",
      address:"",
    }
  },
  created() {
    this.load()
  },
  methods:{
    load(){
      this.request.get("/shuju/page",{
        params:{
          pageNum:this.pageNum,
          pageSize:this.pageSize,
          num:this.num,
          name:this.name,
          address:this.address,
        }
      }).then(res =>{

        this.tableData = res.data.records
        this.total = res.data.total
      })
    },
    save(){
      this.request.post("/shuju",this.form).then(res =>{
        if(!res.data){
          this.$message.success("保存成功")
          this.dialogFormVisible=false
          this.load()
        }else{
          this.$message.error("保存失败")
        }
      })
    },
    del(id){
      this.request.delete("/shuju/"+id).then(res =>{
        if(!res.data){
          this.$message.success("删除成功")
          this.load()
        }else{
          this.$message.error("删除失败")
          this.load()

        }
      })
    },
    handleSelectionChange(val){
      console.log(val)
      this.multipleSelection = val
    },
    delBatch(){
      let ids =this.multipleSelection.map(v => v.id)
      this.request.post("/shuju/del/batch",ids).then(res =>{
        if(!res.data){
          this.$message.success("批量删除成功")
          this.load()
        }else{
          this.$message.error("删除失败")
          this.load()
        }
      })
    },
    reset(){
      this.num=""
      this.name=""
      this.address=""
      this.load()
    },
    handleSizeChange(pageSize){
      console.log(pageSize)
      this.pageSize=pageSize
      this.load()
    },
    handleCurrentChange(pageNum){
      console.log(pageNum)
      this.pageNum=pageNum
      this.load()
    },
    handleAdd(){
      this.dialogFormVisible=true
      this.form={}
    },

  }

}
</script>

<style >
.headerBg{
  background: #cccccc!important;
}
</style>