<template>
<div>
  <div id="container" style="width: 100%;height: calc(100vh - 100px)"></div>
</div>
</template>

<script>
var content=[
    "<div>设备名称:A1   " +
    "设备编号:55101   " +
    "设备地址:东平大桥</div>"
]



export default {
  name: "Map",
  data(){
    return{}
  },
  created() {
  },
  mounted() {
    // 创建地图实例
    var map = new AMap.Map("container", {
      zoom: 13,
      center:[113.129841,22.980749],
    })

    var infoWindow = new AMap.InfoWindow({
      content: content.join("<br>")
    });


    var clickHandler = function (e){
      console.log('您在['+e.lnglat.getLng()+','+e.lnglat.getLat()+']的位置点击了地图！')

      if(infoWindow.open(map,[113.123401,22.9747])){
        infoWindow.open(map,[113.123401,22.9747]);
      }

      if(infoWindow.open(map,[113.123347,22.975172])){
        infoWindow.open(map,[113.123347,22.975172]);
      }

    };

    //绑定事件
    // map.on('click',clickHandler);

    //创建点
    var marker1 = new AMap.Marker({
      position:new AMap.LngLat(113.123401,22.9747),
      title:'监测设备A1'
    })
    var marker2 = new AMap.Marker({
      position:new AMap.LngLat(113.123347,22.975172),
      title:'监测设备A2'
    })
    var marker3 = new AMap.Marker({
      position:new AMap.LngLat(113.123487,22.975557),
      title:'监测设备A3'
    })
    var marker4 = new AMap.Marker({
      position:new AMap.LngLat(113.123444,22.975859),
      title:'监测设备A4'
    })
    // var marker5 = new AMap.Marker({
    //   position:new AMap.LngLat(113.123401,22.9747),
    //   title:'监测设备A5'
    // })
    // var marker6 = new AMap.Marker({
    //   position:new AMap.LngLat(113.123401,22.9747),
    //   title:'监测设备A6'
    // })
    // var marker7 = new AMap.Marker({
    //   position:new AMap.LngLat(113.123401,22.9747),
    //   title:'监测设备A7'
    // })
    // var marker8 = new AMap.Marker({
    //   position:new AMap.LngLat(113.123401,22.9747),
    //   title:'监测设备A8'
    // })
    //



    marker1.on('click',clickHandler);
    marker2.on('click',clickHandler);


    //将创建的点添加到地图实例
    map.add(marker1);
    map.add(marker2);
    map.add(marker3);
    map.add(marker4);
  },
  methods:{}
}
</script>

<style scoped>

</style>