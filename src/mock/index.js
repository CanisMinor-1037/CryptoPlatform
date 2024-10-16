import Mock from 'mockjs'

/*
      <el-table-column type="selection" width="55" align="center" />
<!--      <el-table-column label="序号" align="center" prop="id" />-->
      <el-table-column label="溯源码" align="center" prop="traceno" width="162"/>
      <el-table-column label="文件名称" align="center" prop="filename" />
      <el-table-column label="文件大小" align="center" prop="filesize" />
<!--      <el-table-column label="作品ipfsp hash值" align="center" prop="ipfshash" />-->
      <el-table-column label="文件指纹" align="center" prop="hash" />
      <el-table-column label="属性集" align="center" prop="policy" />
      <el-table-column label="上传时间" align="center" prop="createtime" />
      <el-table-column label="上传人姓名" align="center" prop="createname" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
*/ 
//login
/*Mock.mock("/fileapi/login","post",{
  "success":true,
  "data":{}
})
Mock.mock("/fileapi/register","post",{
  "success":true
})*/
//page1
Mock.mock("/api/index/orderInfo","get",{data:{
  "order":{
    lastWeek: 1,
    thisYear: 2,
    total: 3,
    department: 4
  },
  "barData":[5, 20, 36, 10, 10, 20, 5, 20, 36, 10, 10, 20]
}})
Mock.mock("/api/index/applicationInfo","get",{data:[
  [
    {name:'湖北',value:49},
    {name: '北京',value: 22},
    {name: '上海',value: 14},
    {name: '河南',value: 10},
    {name: '广东',value: 5},
  ],
  [
    {name: '政务',value: 11},
    {name: '工业',value: 38},
    {name: '农业',value: 25},
    {name: '服务业',value: 26},
  ]
]
})
Mock.mock("/api/index/timesRanking","get",{data:[
  {name:'高峰期交通流量',value:29},
  {name:'绿化带建设规划',value:12},
  {name:'人口数据',value:120},
  {name:'农产品数据',value:78},
  {name:'医疗信息1',value:66},
  {name:'摄像头数据',value:50},
  {name:'城市规划数据',value:45},
]})
Mock.mock("/api/index/realTime","get",{data:[
  ["1","武汉市统计局","武汉市农业局","农产品数据1","2024/5/7 18:00:03","进行中"],
  ["2","武汉市统计局","武汉市农业局","农产品数据2","2024/5/7 18:02:03","已完成"],
  ["3","武汉市统计局","武汉市农业局","农产品数据3","2024/5/7 18:00:03","进行中"],
  ["4","武汉市数据局","湖北六七二医院","医疗信息1","2024/5/8 18:02:03","中止"],
  ["5","武汉市数据局","湖北六七二医院","医疗信息2","2024/5/9 13:00:03","进行中"],
  ["6","武汉市交通局","武汉市国土局","城市规划数据","2024/5/9 14:02:03","已完成"],
  ["7","武汉市数据局","武汉市交通局","高峰期交通流量","2024/5/13 18:00:03","进行中"],
  ["8","武汉市农业局","武汉市统计局","人口数据","2024/5/14 18:02:03","中止"]
]})
Mock.mock("/api/index/peerDistribution","get",{data:[
  ["华中1","对等","正常"],
  ["华中2","排序","正常"],
  ["华北1","对等","正常"],
  ["华东1","对等","正常"],
  ["西南1","对等","正常"],
  ["中南1","对等","正常"],
  ["东北1","排序","正常"]
]})
Mock.mock("/api/index/departmentRanking","get",{data:[
  {
    "department": ['武汉市交通局', '武汉市环保局', '武汉市数据局', '武汉市农业局', '武汉市统计局'],
    "times": [7, 9, 16, 17, 20]
   },
  {
    "department": ['武汉市环保局', '武汉市统计局', '武汉市农业局', '武汉市公安局', '武汉市数据局'] ,
    "times": [5, 7, 9, 13, 25]
  }
]})
Mock.mock("/api/index/storage","get",{data:[
  ["人口数据","武汉市统计局","2024/4/7 18:00:03"],
  ["武汉市农业局","农产品数据1","2024/4/7 18:02:03"],
  ["武汉市农业局","农产品数据2","2024/4/8 18:00:03"],
  ["武汉市农业局","农产品数据3","2024/4/8 18:02:03"],
  ["湖北六七二医院","医疗信息1","2024/4/9 13:00:03"],
  ["湖北六七二医院","医疗信息2","2024/4/9 14:02:03"],
  ["武汉市国土局","城市规划数据","2024/4/13 18:00:03"],
  ["武汉市交通局","高峰期交通流量","2024/4/14 18:02:03"],
]})


//page2
Mock.mock("/api/confirmation/applyAndApplied","get",{data:[
  [10,20,30,40,50,60,12,13,14,15,16,17],
  [40,50,60,20,20,30,21,45,35,45,16,33]
]})
Mock.mock("/api/confirmation/getAllAttributes","get",{data:{
  "data":[
    ["age"],["age:20","user:tom","department:my company"]
  ]
}})
Mock.mock("/api/confirmation/fileAvailable","get",{data:[
  ["武汉市农业局","湖北六七二医院","武汉市数据局","武汉市公安局","武汉市国土局","武汉市环保局","武汉市交通局","武汉市统计局"],
  [{"date":"2024-06-01","fileName":"农产品数据","department":"武汉市农业局","attributes":"user:yha 1of1"},
  {"date":"2024-06-01","fileName":"医疗信息","department":"湖北六七二医院","attributes":"user:yha 1of1"},
  {"date":"2024-06-01","fileName":"智慧城市数据信息","department":"武汉市数据局","attributes":"user:yha 1of1"},
  {"date":"2024-06-01","fileName":"摄像头数据","department":"武汉市公安局","attributes":"user:yha 1of1"},
  {"date":"2024-06-01","fileName":"城市规划数据","department":"武汉市国土局","attributes":"user:yha 1of1"},
  {"date":"2024-06-01","fileName":"绿化带建设规划","department":"武汉市环保局","attributes":"user:yha 1of1"},
  {"date":"2024-06-01","fileName":"高峰期交通流量","department":"武汉市交通局","attributes":"user:yha 1of1"},
  {"date":"2024-06-01","fileName":"人口数据","department":"武汉市统计局","attributes":"user:yha 1of1"}]
]})
Mock.mock("/api/confirmation/ownerFile","get",{data:[
  {"date":"2024-06-01","fileName":"农产品数据","attributes":"user:yha 1of1"},
  {"date":"2024-06-01","fileName":"医疗信息","attributes":"user:yha 1of1"},
  {"date":"2024-06-01","fileName":"智慧城市数据信息","attributes":"user:yha 1of1"},
  {"date":"2024-06-01","fileName":"摄像头数据","attributes":"user:yha 1of1"},
  {"date":"2024-06-01","fileName":"城市规划数据","attributes":"user:yha 1of1"},
  {"date":"2024-06-01","fileName":"绿化带建设规划","attributes":"user:yha 1of1"},
  {"date":"2024-06-01","fileName":"高峰期交通流量","attributes":"user:yha 1of1"},
  {"date":"2024-06-01","fileName":"人口数据","attributes":"user:yha 1of1"}
]})
//page3
Mock.mock("/api/order/fileApplyHistory","get",[
  {"orderNo":"0001-10221","fileName":"农产品数据","department":"武汉市农业局",status:2},
  {"orderNo":"0003-00709","fileName":"医疗信息","department":"湖北六七二医院",status:3},
  {"orderNo":"0010-27498","fileName":"智慧城市数据信息","department":"武汉市数据局",status:1},
  {"orderNo":"0014-10231","fileName":"摄像头数据","department":"武汉市公安局",status:1},
  {"orderNo":"0012-00447","fileName":"城市规划数据","department":"武汉市国土局",status:4},
  {"orderNo":"0008-00129","fileName":"绿化带建设规划","department":"武汉市环保局",status:1},
  {"orderNo":"0007-00328","fileName":"高峰期交通流量","department":"武汉市交通局",status:1},
  {"orderNo":"0005-00109","fileName":"人口数据","department":"武汉市统计局",status:4},
  /**
    {"orderNo":"0001-10221","fileName":"农产品数据","department":"武汉市农业局",status:2},
  {"orderNo":"0003-00709","fileName":"医疗信息","department":"湖北六七二医院",status:3},
  {"orderNo":"0010-27498","fileName":"智慧城市数据信息","department":"武汉市数据局",status:1},
  {"orderNo":"0014-10231","fileName":"摄像头数据","department":"武汉市公安局",status:1},
  {"orderNo":"0001-10221","fileName":"农产品数据","department":"武汉市农业局",status:4},
  {"orderNo":"0001-10221","fileName":"农产品数据","department":"武汉市农业局",status:3},
  {"orderNo":"0001-10221","fileName":"农产品数据","department":"武汉市农业局",status:1},
  {"orderNo":"0003-00709","fileName":"医疗信息","department":"湖北六七二医院",status:2},
  {"orderNo":"0003-00709","fileName":"医疗信息","department":"湖北六七二医院",status:1},
  {"orderNo":"0012-00447","fileName":"城市规划数据","department":"武汉市国土局",status:1},
  {"orderNo":"0008-00129","fileName":"绿化带建设规划","department":"武汉市环保局",status:1},
  {"orderNo":"0007-00328","fileName":"高峰期交通流量","department":"武汉市交通局",status:1},
  {"orderNo":"0005-00109","fileName":"人口数据","department":"武汉市统计局",status:1}, 
  
   */
]
)
Mock.mock("/api/order/fileAppliedHistory","get",[
  {"orderNo":"0001-10221","fileName":"农产品数据","department":"武汉市农业局",status:2},
  {"orderNo":"0003-00709","fileName":"医疗信息","department":"湖北六七二医院",status:3},
  {"orderNo":"0010-27498","fileName":"智慧城市数据信息","department":"武汉市数据局",status:1},
  {"orderNo":"0014-10231","fileName":"摄像头数据","department":"武汉市公安局",status:1},
  {"orderNo":"0012-00447","fileName":"城市规划数据","department":"武汉市国土局",status:4},
  {"orderNo":"0008-00129","fileName":"绿化带建设规划","department":"武汉市环保局",status:1},
  {"orderNo":"0007-00328","fileName":"高峰期交通流量","department":"武汉市交通局",status:1},
  {"orderNo":"0005-00109","fileName":"人口数据","department":"武汉市统计局",status:4},
  /**
    {"orderNo":"0001-10221","fileName":"农产品数据","department":"武汉市农业局",status:2},
  {"orderNo":"0003-00709","fileName":"医疗信息","department":"湖北六七二医院",status:3},
  {"orderNo":"0010-27498","fileName":"智慧城市数据信息","department":"武汉市数据局",status:1},
  {"orderNo":"0014-10231","fileName":"摄像头数据","department":"武汉市公安局",status:1},
  {"orderNo":"0001-10221","fileName":"农产品数据","department":"武汉市农业局",status:4},
  {"orderNo":"0001-10221","fileName":"农产品数据","department":"武汉市农业局",status:3},
  {"orderNo":"0001-10221","fileName":"农产品数据","department":"武汉市农业局",status:1},
  {"orderNo":"0003-00709","fileName":"医疗信息","department":"湖北六七二医院",status:2},
  {"orderNo":"0003-00709","fileName":"医疗信息","department":"湖北六七二医院",status:1},
  {"orderNo":"0012-00447","fileName":"城市规划数据","department":"武汉市国土局",status:1},
  {"orderNo":"0008-00129","fileName":"绿化带建设规划","department":"武汉市环保局",status:1},
  {"orderNo":"0007-00328","fileName":"高峰期交通流量","department":"武汉市交通局",status:1},
  {"orderNo":"0005-00109","fileName":"人口数据","department":"武汉市统计局",status:1}, 
  
   */
]
)
/*Mock.mock("/api/applicationHistory/fileApplyHistory","get",[{"time":"2024-06-01 12:12:34","num":"0001-10221","filename":"农产品数据","counterpart":"武汉市农业局","direction":"input","progress":100,status:2},
        {"time":"2024-06-02 11:23:41","num":"0003-00709","filename":"医疗信息","counterpart":"湖北六七二医院","progress":55,"direction":"input",status:3},
        {"time":"2024-06-02 12:02:04","num":"0010-27498","filename":"智慧城市数据信息","counterpart":"武汉市数据局","direction":"input","status":1,"progress":30},
        {"time":"2024-06-03 07:43:48","num":"0014-10231","filename":"摄像头数据","counterpart":"武汉市公安局","direction":"output","status":1,"progress":90},
        {"time":"2024-06-01 16:01:10","num":"0001-10221","filename":"农产品数据","counterpart":"武汉市农业局","direction":"input","progress":60,status:4},
        {"time":"2024-06-01 13:44:10","num":"0001-10221","filename":"农产品数据","counterpart":"武汉市农业局","direction":"input","progress":100,status:3},
        {"time":"2024-06-01 12:02:33","num":"0001-10221","filename":"农产品数据","counterpart":"武汉市农业局","direction":"input","progress":100,status:1},
        {"time":"2024-06-01 11:20:09","num":"0003-00709","filename":"医疗信息","counterpart":"湖北六七二医院","progress":100,"direction":"input",status:2},
        {"time":"2024-05-30 07:18:21","num":"0003-00709","filename":"医疗信息","counterpart":"湖北六七二医院","progress":100,"direction":"input",status:1},
        {"time":"2024-06-04 06:11:31","num":"0012-00447","filename":"城市规划数据","counterpart":"武汉市国土局","progress":100,"direction":"output",status:1},
        {"time":"2024-06-04 07:18:12","num":"0008-00129","filename":"绿化带建设规划","counterpart":"武汉市环保局","progress":100,"direction":"output",status:1},
        {"time":"2024-06-03 07:18:21","num":"0007-00328","filename":"高峰期交通流量","counterpart":"武汉市交通局","progress":100,"direction":"output",status:1},
        {"time":"2024-06-04 07:18:21","num":"0005-00109","filename":"人口数据","counterpart":"武汉市统计局","progress":100,"direction":"output",status:1},
      ]
)*/
/*Mock.mock("/api/sharingHistory/fileSharingHistory","get",[
  {"id":1,"date":"2024-06-01","filename":"农产品数据","owner":{"user_name":"武汉市农业局","certificate":"111"}},
  {"id":2,"date":"2024-06-02","filename":"医疗信息","owner":{"user_name":"湖北六七二医院","certificate":"222"}},
  {"id":3,"date":"2024-06-02","filename":"智慧城市数据信息","owner":{"user_name":"武汉市数据局","certificate":"333"}},
  {"id":4,"date":"2024-06-03","filename":"摄像头数据","owner":{"user_name":"武汉市公安局","certificate":"444"}},
  {"id":5,"date":"2024-06-01","filename":"农产品数据","owner":{"user_name":"武汉市农业局","certificate":"111"}},
  {"id":6,"date":"2024-06-01","filename":"农产品数据","owner":{"user_name":"武汉市农业局","certificate":"111"}},
  {"id":7,"date":"2024-06-01","filename":"农产品数据","owner":{"user_name":"武汉市农业局","certificate":"111"}},
  {"id":8,"date":"2024-06-01","filename":"医疗信息","owner":{"user_name":"湖北六七二医院","certificate":"222"}},
  {"id":9,"date":"2024-05-30","filename":"医疗信息","owner":{"user_name":"湖北六七二医院","certificate":"222"}},
  {"id":10,"date":"2024-06-04","filename":"城市规划数据","owner":{"user_name":"武汉市国土局","certificate":"555"}},
  {"id":11,"date":"2024-06-04","filename":"绿化带建设规划","owner":{"user_name":"武汉市环保局","certificate":"666"}},
  {"id":12,"date":"2024-06-03","filename":"高峰期交通流量","owner":{"user_name":"武汉市交通局","certificate":"777"}},
  {"id":13,"date":"2024-06-04","filename":"人口数据","owner":{"user_name":"武汉市统计局","certificate":"888"}},

]);
Mock.mock("/api/sharingHistory/fileinfo","get",[
  {"operator":"张三","date":"2024-05-30","operation":"查看","filename":"1.txt","status":1}
]);
Mock.mock("/api/sharingHistory/DBSharingHistory","get",[
  {"date":"2024-06-01","databasename":"农产品数据","owner":{"user_name":"1"}},
  {"date":"2024-06-02","databasename":"医疗信息","owner":{"user_name":"湖北六七二医院"}},
  {"date":"2024-06-02","databasename":"智慧城市数据信息","owner":{"user_name":"武汉市数据局"}},
  {"date":"2024-06-03","databasename":"摄像头数据","owner":{"user_name":"武汉市公安局"}},
  {"date":"2024-06-01","databasename":"农产品数据","owner":{"user_name":"武汉市农业局"}},
  {"date":"2024-06-01","databasename":"农产品数据","owner":{"user_name":"武汉市农业局"}},
  {"date":"2024-06-01","databasename":"农产品数据","owner":{"user_name":"武汉市农业局"}},
  {"date":"2024-06-01","databasename":"医疗信息","owner":{"user_name":"湖北六七二医院"}},
  {"date":"2024-05-30","databasename":"医疗信息","owner":{"user_name":"湖北六七二医院"}},
  {"date":"2024-06-04","databasename":"城市规划数据","owner":{"user_name":"武汉市国土局"}},
  {"date":"2024-06-04","databasename":"绿化带建设规划","owner":{"user_name":"武汉市环保局"}},
  {"date":"2024-06-03","databasename":"高峰期交通流量","owner":{"user_name":"武汉市交通局"}},
  {"date":"2024-06-04","databasename":"人口数据","owner":{"user_name":"武汉市统计局"}},
]);
Mock.mock("/api/sharingHistory/dbinfo","get",[
  {"operator":"张三","date":"2024-05-30","operation":"查看","dbname":"1.db","status":1}
]);
*/
//page4
Mock.mock("/api/sharingHistory/allFile","get",[
  /*{"traceno":"621732085056405504","filename":"操作手册.zip","filesize":"0.76KB","hash":"fc2ecdbd6ba20334cf451be5d861096c","policy":"user:yha 1of1",
    "createtime":"2024-01-24 09:02:12","createname":"管理员","isdel":2},
    {"traceno":"621732085056405504","filename":"P2-note.zip","filesize":"5.16KB","hash":"fc2ecdbd6ba20334cf451be5d861096c","policy":"user:yha 1of1",
      "createtime":"2024-01-24 09:02:12","createname":"123","isdel":0},*/
      {"time":"2024-06-01 12:12:34","id":"0001-10221","fileName":"农产品数据","applier":"华中农业大学","owner":"武汉市农业局",status:2,"policy":"user:yha 1of1"},
        {"time":"2024-06-02 11:23:41","id":"0003-00709","fileName":"医疗信息","applier":"协和医院","owner":"湖北六七二医院",status:3,"policy":"user:yha 1of1"},
        {"time":"2024-06-02 12:02:04","id":"0010-27498","fileName":"智慧城市数据信息","applier":"东西湖区数据局","owner":"武汉市数据局","status":1,"policy":"user:yha 1of1"},
        {"time":"2024-06-03 07:43:48","id":"0014-10231","fileName":"摄像头数据","applier":"武汉市公安局","owner":"东西湖区公安局","status":1,"policy":"user:yha 1of1"},
        {"time":"2024-06-01 16:01:10","id":"0001-10221","fileName":"农产品数据","applier":"华中农业大学","owner":"武汉市农业局",status:4,"policy":"user:yha 1of1"},
        {"time":"2024-06-01 13:44:10","id":"0001-10221","fileName":"农产品数据","applier":"华中农业大学","owner":"武汉市农业局",status:3,"policy":"user:yha 1of1"},
        {"time":"2024-06-01 12:02:33","id":"0001-10221","fileName":"农产品数据","applier":"华中农业大学","owner":"武汉市农业局",status:1,"policy":"user:yha 1of1"},
        {"time":"2024-06-01 11:20:09","id":"0003-00709","fileName":"医疗信息","applier":"协和医院","owner":"湖北六七二医院",status:2,"policy":"user:yha 1of1"},
        {"time":"2024-05-30 07:18:21","id":"0003-00709","fileName":"医疗信息","applier":"协和医院","owner":"湖北六七二医院",status:1,"policy":"user:yha 1of1"},
        {"time":"2024-06-04 06:11:31","id":"0012-00447","fileName":"城市规划数据","applier":"武汉市国土局","owner":"武汉市数据局",status:1,"policy":"user:yha 1of1"},
        {"time":"2024-06-04 07:18:12","id":"0008-00129","fileName":"绿化带建设规划","applier":"武汉市环保局","owner":"武汉市数据局",status:1,"policy":"user:yha 1of1"},
        {"time":"2024-06-03 07:18:21","id":"0007-00328","fileName":"高峰期交通流量","applier":"武汉市交通局","owner":"武汉市数据局",status:1,"policy":"user:yha 1of1"},
        {"time":"2024-06-04 07:18:21","id":"0005-00109","fileName":"人口数据","applier":"武汉市统计局","owner":"武汉市数据局",status:1,"policy":"user:yha 1of1"},
])

//page5
Mock.mock("/api/attributeAuthority/attributeList","get",[
  "user:tom,jerry,jack",
  "age:1-80",
  "company:my company,anither company"
])
Mock.mock("/api/attributeAuthority/departmentList","get",{data:[
  {"name":"武汉市数据局","attribute":"user:yha 1of1"},
  {"name":"武汉市农业局","attribute":"user:yha 1of1"},
  {"name":"武汉市交通局","attribute":"user:yha 1of1"}, 
]})
Mock.mock("/api/attributeAuthority/applicationUnprocessed","get",[
  {"date":"2024-06-01","department":"武汉市数据局","attributes":"user:yha 1of1"},
  {"date":"2024-06-01","department":"武汉市农业局","attributes":"user:yha 1of1"},
  {"date":"2024-06-01","department":"武汉市交通局","attributes":"user:yha 1of1"}, 
])
Mock.mock("/api/attributeAuthority/applicationProcessed","get",[
  {"date":"2024-06-01","department":"武汉市数据局","attributes":"user:yha 1of1","status":0},
  {"date":"2024-06-01","department":"武汉市农业局","attributes":"user:yha 1of1","status":0},
  {"date":"2024-06-01","department":"武汉市交通局","attributes":"user:yha 1of1","status":1}, 
])