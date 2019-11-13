<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>选项</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  <link rel="stylesheet" href="<%=basePath%>/statics/css/bootstrap.min.css">
<link rel="stylesheet" href="<%=basePath%>/statics/css/font-awesome.min.css">
<link rel="stylesheet" href="<%=basePath%>/statics/plugins/jqgrid/ui.jqgrid-bootstrap.css">
<link rel="stylesheet" href="<%=basePath%>/statics/plugins/ztree/css/metroStyle/metroStyle.css">
<link rel="stylesheet" href="<%=basePath%>/statics/css/main.css">
<script src="<%=basePath%>/statics/libs/jquery.min.js"></script>
<script src="<%=basePath%>/statics/libs/ajaxfileupload.js"></script>  <!-- 异步文件上传插件 -->
<script src="<%=basePath%>/statics/libs/jquery.form.js"></script>  
<script src="<%=basePath%>/statics/plugins/layer/layer.js"></script>
<script src="<%=basePath%>/statics/libs/bootstrap.min.js"></script>
<script src="<%=basePath%>/statics/libs/vue.min.js"></script>
<script src="<%=basePath%>/statics/plugins/jqgrid/grid.locale-cn.js"></script>
<script src="<%=basePath%>/statics/plugins/jqgrid/jquery.jqGrid.min.js"></script>
<script src="<%=basePath%>/statics/plugins/ztree/jquery.ztree.all.min.js"></script>
<script src="<%=basePath%>/js/common.js"></script>

<script type="text/javascript" charset="utf-8" src="<%=basePath%>/ueditor/ueditor.all.min.js"> </script>
<script type="text/javascript" charset="utf-8" src="<%=basePath%>/ueditor/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=basePath%>/ueditor/lang/zh-cn/zh-cn.js"></script>
  </head>
  <body>
    
    <div id="rrapp" v-cloak>
<!-- 	<div v-show="showList">
		<div class="grid-btn">
			<a class="btn btn-primary" @click="update"><i class="fa fa-pencil-square-o"></i>&nbsp;修改</a>
		</div>
	 
	
    </div> -->
    
    <div v-show="showList" class="panel panel-default">
		<div class="panel-heading">{{title}}</div>
		<div><input id="deptID" type="text" value="${sysDept.deptId}"></div>
		
		<form class="form-horizontal">
									<div class="form-group">
			   	<div class="col-sm-2 control-label">科室名称</div>
			   	<div class="col-sm-10">
			      <input type="text" class="form-control" v-model="sysDept.name" placeholder="科室名称"/> 
			    </div>
			</div>
									<div class="form-group">
			   	<div class="col-sm-2 control-label">通讯录</div>
			   	<div class="col-sm-12">
			     <!--  <input type="text" class="form-control" v-model="sysDept.sysContent" placeholder="通讯录"/> -->
			       <!-- 编辑器 -->
			       <script id="editor" type="text/plain" style="width:1024px;height:500px;"></script>
			    </div>
			</div>
			
							<div class="form-group">
				<div class="col-sm-2 control-label"></div> 
				<input type="button" class="btn btn-primary" @click="saveOrUpdate" value="确定"/>
				&nbsp;&nbsp;<input type="button" class="btn btn-warning" @click="reload" value="返回"/>
			</div>
		</form>
	</div>
</div>
<script type="text/javascript">
  //实例化编辑器
    //建议使用工厂方法getEditor创建和引用编辑器实例，如果在某个闭包下引用该编辑器，直接调用UE.getEditor('editor')就能拿到相关的实例
    var ue = UE.getEditor('editor');
</script>

<script src="<%=basePath%>/js/sys/sysdeptjsp.js?_${date.systemTime}"></script>
</body>
</html>
