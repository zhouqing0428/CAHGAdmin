<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>问卷问题</title>
    
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
  
<body>
<input id="surveyId" value="${surveyId }" style="display: none">
<div id="rrapp" v-cloak>
	<div v-show="showList">
		<div class="grid-btn">
		<!-- 	#if($shiro.hasPermission("cahgsurveyquestion:save")) -->
			<a class="btn btn-primary" @click="add"><i class="fa fa-plus"></i>&nbsp;新增</a>
		<!-- 	#end
			#if($shiro.hasPermission("cahgsurveyquestion:update")) -->
			<a class="btn btn-primary" @click="update"><i class="fa fa-pencil-square-o"></i>&nbsp;修改</a>
		<!-- 	#end
			#if($shiro.hasPermission("cahgsurveyquestion:delete")) -->
			<a class="btn btn-primary" @click="del"><i class="fa fa-trash-o"></i>&nbsp;删除</a>
			<input type="button" class="btn btn-primary"  onclick="javascript :history.back(-1);" value="返回">
		
		<!-- 	#end -->
		</div>
	    <table id="jqGrid"></table>
	    <div id="jqGridPager" style="height: 41px;display: none"></div>
    </div>
    
    <div v-show="!showList" class="panel panel-default" >
		<div class="panel-heading">{{title}}</div>
		<form class="form-horizontal">
											<div class="form-group">
			   	<div class="col-sm-2 control-label">问题</div>
			   	<div class="col-sm-10">
			      <input type="text" class="form-control" v-model="cahgSurveyQuestion.question" placeholder="问题"/>
			    </div>
			</div>
									<div class="form-group">
			   	<div class="col-sm-2 control-label">类型</div>
			   	<div class="col-sm-10">
			     <!--  <input type="text" class="form-control" v-model="cahgSurveyQuestion.answerType" placeholder=""/> -->
			     <label class="radio-inline">
					  <input type="radio" name="status" value="0" v-model="cahgSurveyQuestion.answerType"/> 单选
					</label>
					<label class="radio-inline">
					  <input type="radio" name="status" value="1" v-model="cahgSurveyQuestion.answerType"/> 多选
					</label>
			    </div>
			</div>
								
								<!-- 	<div class="form-group">
			   	<div class="col-sm-2 control-label">卷名id</div>
			   	<div class="col-sm-10">
			      <input type="text" class="form-control" v-model="cahgSurveyQuestion.surveyId" placeholder="卷名id"/>
			    </div>
			</div>  -->
							<div class="form-group">
				<div class="col-sm-2 control-label"></div> 
				<input type="button" class="btn btn-primary" @click="saveOrUpdate" value="确定"/>
				&nbsp;&nbsp;<input type="button" class="btn btn-warning" @click="reload" value="返回"/>
			</div>
		</form>
	</div>
</div>

<script src="<%=basePath%>/js/web/cahgsurveyquestion.js?_${date.systemTime}"></script>
</body>
</html>
