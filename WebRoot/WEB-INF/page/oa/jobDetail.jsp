<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>工作跟进信息</title>
    
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
<%-- 	<table class="table table-bordered">
	<thead>
		<tr>
			<th>工作标题</th>
			<td> ${job.title }</td>
		</tr>
	</thead>
	<tbody>
	    	<tr>
			<th>发起人</th>
			<th>${job.createUser }</th>
		</tr>
		<tr>
			<th>流转至</th>
			<th>${user.name }</th>
		</tr>
		<tr>
			<th>紧急程度</th>
			<td> <c:if test="${job.urgentStatus eq 0 }">低</c:if><c:if test="${job.urgentStatus eq 1 }">中</c:if><c:if test="${job.urgentStatus eq 2 }">高</c:if></td>
		</tr>
		<tr>
			<th>发起时间</th>
			<td><fmt:formatDate value="${job.createUserDate }" pattern="yyyy-MM-dd hh:mm:ss"/></td>
		</tr>
		<tr>
			<th>计划完成时间</th>
			<td><fmt:formatDate value="${job.endTime }" pattern="yyyy-MM-dd hh:mm:ss"/></td>
		</tr>
		<tr>
			<th>工作内容</th>
			<td>${job.content }</td>
		</tr>
	</tbody>
</table> --%>
<table class="table table-bordered">
<%-- 	<caption>边框表格布局</caption> --%>
	<thead>
		<tr>
			<th>标题</th>
			<th>发起人</th>
			<th>流转至</th>
			<th>紧急程度</th>
			<th>发起时间</th>
			<th>计划完成时间</th>
		</tr>
	</thead>
	<tbody>
		<tr class="success">
			<td>${job.title }</td>
			<td>${job.createUser }</td>
			<td>${user.name }</td>
			<td><c:if test="${job.urgentStatus eq 0 }">低</c:if><c:if test="${job.urgentStatus eq 1 }">中</c:if><c:if test="${job.urgentStatus eq 2 }">高</c:if></td>
			<td><fmt:formatDate value="${job.createUserDate }" pattern="yyyy-MM-dd hh:mm:ss"/></td>
			<td><fmt:formatDate value="${job.endTime }" pattern="yyyy-MM-dd hh:mm:ss"/></td>
		</tr>
	</tbody>
</table>

<table class="table">
	<tbody>
		<tr  class="danger">
			<td>工作内容</td>
			<td>${job.content }</td>
		</tr>
	</tbody>
</table>
<input id="jobId" style="display: none" value="${job.jobId }">
<!-- <div style="height:1px;width:100%; background: red"></div> -->
<table class="table table-bordered">
	<h3>跟进消息</h3>
	<thead>
		<tr>
			<th style="width: 18%">跟进人</th>
			<th >跟进内容</th>
			<th style="width: 18%">跟进时间</th>
			<th style="width: 18%">流转至</th>
		</tr>
	</thead>
	<tbody>
	 <c:forEach items="${list }" var="jobb">
		<tr class="warning">
			<td>${jobb.flowUserName }</td>
			<td>${jobb.content }</td>
			<td><fmt:formatDate value="${jobb.createUserDate }" pattern="yyyy-MM-dd hh:mm:ss"/></td>
			<td>${jobb.nextUserName }</td>
		</tr>
		</c:forEach>
	</tbody>
</table>
 
 <c:if test="${job.status != 2}">
     流转未结束
     <c:if test="${mp.status eq 0 }">
       <form id="rrapp" class="form-horizontal">
			 	<div class="form-group">
			   	<div class="col-sm-2 control-label">科室</div>
			   	<div class="col-sm-10">
			       <select id="deptId"  class="form-control">
				         <option>--请选择科室--</option>
				         <option v-for="item in deptList" :value="item.deptId "  >
				         	 {{item.name }}
				         </option>
					</select>
			    </div>
			</div>
			<div class="form-group">
			   	<div class="col-sm-2 control-label">工作流转</div>
			   	<div class="col-sm-10">
			       <select id="flowUserId"  class="form-control" >
				      <option value="">--请选择流转人--</option>
				 </select>
			    </div>
			</div>
									<div class="form-group">
			   	<div class="col-sm-2 control-label">跟进内容</div>
			   	<div class="col-sm-10">
			      <!-- <input type="text" class="form-control" v-model="cahgJob.content" placeholder="工作内容"/> -->
			        <textarea class="form-control" id="content" rows="4" cols="30"></textarea> 
			    </div>
			</div>
			<div class="form-group">
			   	<div class="col-sm-2 control-label">流程结束</div>
			   	<div class="col-sm-10">
					<label class="radio-inline">
					   <input type="radio" name="status" checked="checked" v-model="jobDetail.status" value="1"/> 否
					</label>
					<label class="radio-inline">
					   <input type="radio" name="status" v-model="jobDetail.status" value="2"/> 是
					</label>
			    </div>
			</div>
								
									<!-- <div class="form-group">
			   	<div class="col-sm-2 control-label">附件名称</div>
			   	<div class="col-sm-10">
			      <input type="text" class="form-control" v-model="cahgJob.fileName" placeholder="附件名称"/>
			    </div>
			</div>
									<div class="form-group">
			   	<div class="col-sm-2 control-label">附件原名</div>
			   	<div class="col-sm-10">
			      <input type="text" class="form-control" v-model="cahgJob.fileOldName" placeholder="附件原名"/>
			    </div>
			</div> -->
							<div class="form-group">
				<div class="col-sm-2 control-label"></div> 
				<input type="button" class="btn btn-primary" @click="saveJobDetail" value="确定"/>
				&nbsp;&nbsp;<input type="button" class="btn btn-warning" onclick="history.go(-1)" value="返回"/>
			</div>
		</form>
     </c:if>
 </c:if>
 <c:if test="${job.status eq 2}">
 <h4>此工作流转结束</h4>
 </c:if>
 
 <script type="text/javascript">
   var vm=new Vue({
       el:'#rrapp',
       data:{
		deptList:[],
		jobDetail: {status:1},
		cahgJob: {}
	},
	methods: {
		getUserList: function (deptId){
			vm.cahgJob.deptId=deptId;
		    $.ajax({
				type: "POST",
			    url: "<%=basePath%>/sys/user/selectList",
			    data: JSON.stringify(vm.cahgJob),
			    //dataType: "json",
			    success: function(data){
				  	var size = data.length;
					console.log(size);
					$("#flowUserId").empty();//设置之前加载数据为空
					//$("#userId").append("<option>"+"--请选择流转人--"+"<option>");
				  	for(var i=0;i<size;i++){
				  		var user = data[i];
				  		var $option = $("<option value="+user.userId+">"+user.name+"</option>");
				  		$("#flowUserId").append($option);
				  	}
				}
			});
		},
			//部门列表
		getDeptList: function(){
			$.get("<%=basePath%>/sysdept/selectList", function(r){
				vm.deptList = r.list;
			});
		},
		saveJobDetail: function (event) {
			var flowUserId=$("#flowUserId").val();
			if(flowUserId == null|| flowUserId == ""){
				alert("请选择流转人");
				return ;
			}
			var url = "<%=basePath%>/cahgjob/saveJobDetail" ;
			var content=$("#content").val();
			content=content.replace(/\n|\r\n/g,"<br>");
			vm.jobDetail.content=content;
			vm.jobDetail.flowUserId=flowUserId;
			vm.jobDetail.jobId=$("#jobId").val();
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.jobDetail),
			    success: function(r){
			    	if(r.code === 0){
						alert('操作成功', function(index){
							window.history.go(-1);
						});
					}else{
						alert(r.msg);
					}
				}
			});
		},
		}
   })
   
    $(function(){
   		 vm.getDeptList();
    });
    
     $("#deptId").change(function(){
     // vm.cahgJob.deptId="";
      if(jQuery.isNumeric($("#deptId").val())){
   	     vm.getUserList($("#deptId").val());
      }
   });
 </script>
  </body>
</html>
