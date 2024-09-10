<template>
  <div>
    <el-row :gutter="10" style="margin-bottom: 40px">
      <el-col :span="6">
        <el-card style="color: #409EFF">
          <div><i class="el-icon-user-solid"/>用户总数</div>
          <div style="padding: 10px 0; text-align: center; font-weight: bold">
            20
          </div>
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card style="color:#F56C6C">
          <div><i class="el-icon-receiving"/>设备总数</div>
          <div style="padding: 10px 0; text-align: center; font-weight: bold">
            8
          </div>
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card style="color: #67C23A">
          <div><i class="el-icon-house"/>勘察面积</div>
          <div style="padding: 10px 0; text-align: center; font-weight: bold">
            10000㎡
          </div>
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card style="color: #E6A23C">
          <div><i class="el-icon-cpu"/>系统版本</div>
          <div style="padding: 10px 0; text-align: center; font-weight: bold">
            2.6.1
          </div>
        </el-card>
      </el-col>
    </el-row>

    <el-row >
      <el-col :span="8">
        <div id="main" style="width:400px; height: 400px"></div>
      </el-col>
      <el-col :span="8">
        <div id="main2" style="width:400px; height: 400px"></div>
      </el-col>
      <el-col :span="8">
        <div id="main3" style="width:400px; height: 400px"></div>
      </el-col>
    </el-row>

  </div>
</template>

<script>
import * as echarts from 'echarts';

export default {
  name: "Home",
  data(){
    return{

    }
  },
  mounted() {//页面元素渲染之后再触发
    var option = {
      title:{
        text:'形变监测▲x偏移量',
        subtext:'趋势图',
        left:'center'
      },
      xAxis: {
        type: 'category',
        data: []
      },
      yAxis: {
        type: 'value'
      },
      series: [
        {
          data: [],
          type: 'line'
        },
      ]
    };
    var chartDom = document.getElementById('main');
    var myChart = echarts.init(chartDom);


    this.request.get("/echarts").then(res =>{
      const name = res.map(v =>v.name);
      console.log(name)
      option.xAxis.data = name

      const dx = res.map(v => v.dx);
      console.log(dx)
      option.series[0].data = dx
      myChart.setOption(option);
    })

    // this.request.get("/echarts/members").then(res =>{
    //   //填空
    //   // option.xAxis.data = res.data.x
    //   option.series[0].data = res.data
    //   option.series[1].data = res.data
    //   myChart.setOption(option);
    // })



    var option2 = {
      title:{
        text:'形变监测▲y偏移量',
        subtext:'趋势图',
        left:'center'
      },
      xAxis: {
        type: 'category',
        data: []
      },
      yAxis: {
        type: 'value'
      },
      series: [
        {
          data: [],
          type: 'line'
        },
        {
          data: [],
          type: 'bar'
        }
      ]
    };
    var chartDom2 = document.getElementById('main2');
    var myChart2 = echarts.init(chartDom2);

    this.request.get("/echarts").then(res =>{
      const name = res.map(v =>v.name);
      console.log(name)
      option2.xAxis.data = name

      const dy = res.map(v => v.dy);
      console.log(dy)
      option2.series[0].data = dy
      myChart2.setOption(option2);
    })


    var option3 = {
      title:{
        text:'形变监测▲z偏移量',
        subtext:'趋势图',
        left:'center'
      },
      xAxis: {
        type: 'category',
        data: []
      },
      yAxis: {
        type: 'value'
      },
      series: [
        {
          data: [],
          type: 'line'
        },
        {
          data: [],
          type: 'bar'
        }
      ]
    };
    var chartDom3 = document.getElementById('main3');
    var myChart3 = echarts.init(chartDom3);

    this.request.get("/echarts").then(res =>{
      const name = res.map(v =>v.name);
      console.log(name)
      option3.xAxis.data = name

      const dz = res.map(v => v.dz);
      console.log(dz)
      option3.series[0].data = dz
      myChart3.setOption(option3);
    })

    // 饼图
    //  var pieOption = {
    //    title: {
    //      text: 'Referer of a Website',
    //      subtext: 'Fake Data',
    //      left: 'center'
    //    },
    //    tooltip: {
    //      trigger: 'item'
    //    },
    //    legend: {
    //      orient: 'vertical',
    //      left: 'left'
    //    },
    //    series: [
    //      {
    //        name: 'Access From',
    //        type: 'pie',
    //        radius: '50%',
    //        data: [
    //          { value: 1048, name: 'Search Engine' },
    //          { value: 735, name: 'Direct' },
    //          { value: 580, name: 'Email' },
    //          { value: 484, name: 'Union Ads' },
    //          { value: 300, name: 'Video Ads' }
    //        ],
    //        emphasis: {
    //          itemStyle: {
    //            shadowBlur: 10,
    //            shadowOffsetX: 0,
    //            shadowColor: 'rgba(0, 0, 0, 0.5)'
    //          }
    //        }
    //      }
    //    ]
    //  };
    //  var pieDom = document.getElementById('pie');
    //  var pieChart = echarts.init(pieDom);
    //  pieChart.setOption(pieOption)

  }
}
</script>

<style scoped>

</style>