$(function () {
    $("#jqGrid").jqGrid({
        url: '../sysactivity/list',
        datatype: "json",
        colModel: [			
			{ label: 'activityId', name: 'activityId', width: 50, key: true,hidden:true },
			{ label: '标题', name: 'title', width: 80 }, 			
			{ label: '活动人数', name: 'number', width: 80 }, 			
			{ label: '费用', name: 'expense', width: 80 }, 			
			{ label: '开始时间', name: 'strartDate', width: 80 }, 			
			{ label: '结束时间', name: 'endDate', width: 80 }, 			
			{ label: '活动内容', name: 'content', width: 80,hidden:true}, 			
			{ label: '活动效果作用', name: 'effect', width: 80,hidden:true }, 			
			{ label: '备注', name: 'note', width: 80 }, 	
			{ label: '标题图片', name: 'actiImg', width: 80,formatter:function(value, options, row){
					return '<img class="img-responsive" src="../upImg/activity/'+value+'">';}
			}, 		
			{ label: '类别id', name: 'actiCateId', width: 80,hidden:true }			
        ],
		viewrecords: true,
        height: 520,
        rowNum: 10,
		rowList : [10,30,50],
        rownumbers: true, 
        rownumWidth: 25, 
        autowidth:true,
        multiselect: true,
        pager: "#jqGridPager",
        jsonReader : {
            root: "page.list",
            page: "page.currPage",
            total: "page.totalPage",
            records: "page.totalCount"
        },
        prmNames : {
            page:"page", 
            rows:"limit", 
            order: "order"
        },
        gridComplete:function(){
        	// 隐藏grid底部滚动条
        	$("#jqGrid").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" }); 
        }
    });
});


var vm = new Vue({
	el:'#rrapp',
	data:{
		showList: true,
		title: null,
		sysActivity: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.sysActivity = {};
			UE.getEditor('editor').setContent('');  //编辑内容为空
		},
		update: function (event) {
			var activityId = getSelectedRow();
			if(activityId == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";
            
            vm.getInfo(activityId);
          /*  var content=vm.sysActivity.content;   //活动内容
            console.log("content="+content);
            vm.setContent(content)*/
		},
		
		/*setContent:function (content) {
	        var arr = [];
	       // arr.push("使用editor.setContent('欢迎使用ueditor')方法可以设置编辑器的内容");
	        UE.getEditor('editor').setContent(content);
	       // alert(arr.join("\n"));
	    },*/
		
		saveOrUpdate: function (event) {
		    var title=$("#title").val();
		    var number=$("#number").val();
		    var expense=$("#expense").val();
		    var strartDate=$("#strartDate").val();
		    var endDate=$("#endDate").val();
		    var effect=$("#effect").val();
		    var endDate=$("#note").val();
		    var effect=$("#actiCateId").val();
		    var content=UE.getEditor('editor').getContent();
		   // alert("title="+title);
		 //   console.log("title="+title+"  number="+number);
		  //  return;
			var url = vm.sysActivity.activityId == null ? "../sysactivity/save" : "../sysactivity/update";
			$.ajax({
				//type: "POST",
			    url: url,
			    type:'post',
	            async:false,
	            cache:false,
			 /*  data: JSON.stringify(vm.sysActivity),*/
			    data: {title : title,
			    	number  : number,
			    	expense : expense,
			    	strartDate : title,
			    	endDate : endDate,
			    	effect : effect,
			    	content : content
			    },
			    dataType : 'json',
			    success: function(r){
			    	if(r.code === 0){
						alert('操作成功', function(index){
							vm.reload();
						});
					}else{
						alert(r.msg);
					}
				}
			});
		},
		del: function (event) {
			var activityIds = getSelectedRows();
			if(activityIds == null){
				return ;
			}
			//alert("activityIds="+activityIds);
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
					contentType: "application/json",
				    url: "../sysactivity/delete",
				    data: JSON.stringify(activityIds),
				    success: function(r){
						if(r.code == 0){
							alert('操作成功', function(index){
								$("#jqGrid").trigger("reloadGrid");
							});
						}else{
							alert(r.msg);
						}
					}
				});
			});
		},
		getInfo: function(activityId){
			$.get("../sysactivity/info/"+activityId, function(r){
                vm.sysActivity = r.sysActivity;
                UE.getEditor('editor').setContent(r.sysActivity.content);  //回显编辑内容
            	var path="http://localhost:8080/CAHGAdmin/upImg/activity/"+r.sysActivity.actiImg;
	            $("#showImg").attr('src',path); 
            });
		},
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{ 
                page:page
            }).trigger("reloadGrid");
		}
	}
});