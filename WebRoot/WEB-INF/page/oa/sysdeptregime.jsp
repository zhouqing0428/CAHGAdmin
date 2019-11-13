<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>科室管理制度</title>
    
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

  </head>
  <input id="deptId" value="${deptId }" style="display: none">
  <body>
   		 <div id="rrapp" v-cloak>
	<div v-show="showList">
	<h4 class="text-primary" align="center">科室管理制度:${deptName }</h4>
		<div class="grid-btn">
			<!-- #if($shiro.hasPermission("sysdeptregime:save")) -->
			<a class="btn btn-primary" @click="add"><i class="fa fa-plus"></i>&nbsp;新增</a>
		<!-- 	#end
			#if($shiro.hasPermission("sysdeptregime:update")) -->
			<a class="btn btn-primary" @click="update"><i class="fa fa-pencil-square-o"></i>&nbsp;修改</a>
			<!-- #end
			#if($shiro.hasPermission("sysdeptregime:delete")) -->
			<a class="btn btn-primary" @click="del"><i class="fa fa-trash-o"></i>&nbsp;删除</a>
			<input type="button" class="btn btn-primary"  onclick="javascript :history.back(-1);" value="返回">
			<!-- #end -->
		</div>
	    <table id="jqGrid"></table>
	    <div id="jqGridPager" style="height: 35px;"></div>
    </div>
    
    <div v-show="!showList" class="panel panel-default">
		<div class="panel-heading">{{title}}</div>
		<form class="form-horizontal">
											<div class="form-group">
			   	<div class="col-sm-2 control-label">标题</div>
			   	<div class="col-sm-10">
			      <input type="text" class="form-control" v-model="sysDeptRegime.title" placeholder="标题"/>
			    </div>
			</div>
								<!-- 	<div class="form-group">
			   	<div class="col-sm-2 control-label"></div>
			   	<div class="col-sm-10">
			      <input type="text" class="form-control" v-model="sysDeptRegime.fileName" placeholder=""/>
			    </div>
			</div> -->
								<!-- 	<div class="form-group">
			   	<div class="col-sm-2 control-label">附件名称</div>
			   	<div class="col-sm-10">
			      <input type="text" class="form-control" v-model="sysDeptRegime.fileOldName" placeholder="附件名称"/>
			    </div>
			</div> -->
			<div class="form-group">
			   	<div class="col-sm-2 control-label">附件</div>
			   	<div class="col-sm-10">
			       <input type="file" style="float: left;" id="file"  name="file"  multiple="" > 
			   	      <input type="button" class="btn btn-default" @click="upfile" value="上传">
			   	      <code v-show="tips" style="margin-top:4px;display: block;">文件已上传</code>
			          <input type="text" style="display: none;" class="form-control" id="fileName" placeholder=""/>
			          <input type="text"  style="display: none;" class="form-control" id="fileOldName" placeholder=""/>
			    </div>
			</div>
								<!-- 	<div class="form-group">
			   	<div class="col-sm-2 control-label"></div>
			   	<div class="col-sm-10">
			      <input type="text" class="form-control" v-model="sysDeptRegime.deptId" placeholder=""/>
			    </div>
			</div> -->
							<div class="form-group">
				<div class="col-sm-2 control-label"></div> 
				<input type="button" class="btn btn-primary" @click="saveOrUpdate" value="确定"/>
				&nbsp;&nbsp;<input type="button" class="btn btn-warning" @click="reload" value="返回"/>
			</div>
		</form>
	</div>
</div>

<script src="<%=basePath%>/js/oa/sysdeptregime.js?_${date.systemTime}"></script>
  </body>
</html>
