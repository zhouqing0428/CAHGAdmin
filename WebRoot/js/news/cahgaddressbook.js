$(function () {
    $("#jqGrid").jqGrid({
        url: '../cahgaddressbook/list',
        datatype: "json",
        colModel: [			
			{ label: 'addressLookId', name: 'addressLookId', width: 50, key: true,hidden: true },
			{ label: '姓名', name: 'name', width: 50 }, 			
			{ label: '手机号码', name: 'mobile', width: 80 }, 			
			{ label: '办公内线', name: 'interior', width: 50 }, 			
			{ label: '办公外线', name: 'external', width: 80 }, 			
			{ label: '工号', name: 'jobNumber', width: 50 }, 			
			{ label: '职务', name: 'duty', width: 80 }, 	
			{ label: '科室', name: 'deptName', width: 140 }
        ],
		viewrecords: true,
        height: 385,
        rowNum: 10,
		rowList : [10,30,50],
        rownumbers: true, 
        rownumWidth: 30, 
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
        	//隐藏grid底部滚动条
        	$("#jqGrid").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" }); 
        }
    });
    
    vm.getDeptList();
    
    jQuery.download = function(url, data, method){
        // 获得url和data
        if( url && data ){ 
            // data 是 string 或者 array/object
            data = typeof data == 'string' ? data : jQuery.param(data);
            // 把参数组装成 form的  input
            var inputs = '';
            jQuery.each(data.split('&'), function(){ 
                var pair = this.split('=');
                console.log("pair           ",pair);
                inputs+='<input type="hidden" name="'+ pair[0] +'" value="'+ pair[1] +'" />';
            });
            // request发送请求
            jQuery('<form action="'+ url +'" method="'+ (method||'post') +'">'+inputs+'</form>')
            .appendTo('body').submit().remove();
        };
    };
});

var vm = new Vue({
	el:'#rrapp',
	data:{
		showList: true,
		title: null,
		deptList:[],
		q:{
			name: ""
		},
		cahgAddressBook: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.cahgAddressBook = {};
			this.getDeptList();
		},
		update: function (event) {
			var addressLookId = getSelectedRow();
			if(addressLookId == null){
				return ;
			}
			$("#selectedDept").attr("selected","selected");
			vm.showList = false;
            vm.title = "修改";
            
            //获取部门信息
			this.getDeptList();
            vm.getInfo(addressLookId)
		},
		saveOrUpdate: function (event) {
			var url = vm.cahgAddressBook.addressLookId == null ? "../cahgaddressbook/save" : "../cahgaddressbook/update";
			
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.cahgAddressBook),
			    success: function(r){
			    	if(r.code === 0){
						alert('操作成功', function(index){
							vm.reload();
						});
					}else{
						alert(r.msg);
					}
				},
				complete : function(XMLHttpRequest, textStatus) { 
					if(textStatus!="success"){
						location.reload(true);
					};
				}
			})
		},
		del: function (event) {
			var addressLookIds = getSelectedRows();
			if(addressLookIds == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: "../cahgaddressbook/delete",
				    data: JSON.stringify(addressLookIds),
				    success: function(r){
						if(r.code == 0){
							alert('操作成功', function(index){
								$("#jqGrid").trigger("reloadGrid");
							});
						}else{
							alert(r.msg);
						}
					},
					complete : function(XMLHttpRequest, textStatus) { 
						if(textStatus!="success"){
							location.reload(true);
						};
					}
				});
			});
		},
		//导入
		imports: function (event) {
			if($("#excle").val()=="" || document.getElementById("excle").files[0] =='请选择xls格式的文件'){
				alert("请先选择xls或者xlsx格式文件");
				return false;
			}
			$.ajaxFileUpload({
				type: "POST",
				fileElementId: 'excle', //文件上传域的ID
			    url: "../cahgaddressbook/imports",
			    success: function(r){
			    	alert(r.msg, function(index){
						$("#jqGrid").trigger("reloadGrid");
					});
				}
			});
		},
		//导出
		exports:function(){
			window.location.href="../cahgaddressbook/exports?deptId="+$("#dept_id").val()+"&name="+vm.q.name
		},
		//下载模板
		download:function(){
			$.download('../cahgaddressbook/downloadTemplate','find=commoncode','post');
//			window.location.href="../cahgaddressbook/downloadTemplate";
		},
		getInfo: function(addressLookId){
			$.get("../cahgaddressbook/info/"+addressLookId, function(r){
                vm.cahgAddressBook = r.cahgAddressBook;
            });
		},
		//部门列表
		getDeptList: function(){
			$.get("../sysdept/selectList", function(r){
				vm.deptList = r.list;
			});
		},
		reload: function (event) {
			$("#selectedDept").removeAttr("selected");
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{ 
				postData : {
					'deptId' : $("#dept_id").val(),
					'name' : $("#name").val()
				},
                page:page
            }).trigger("reloadGrid");
		}
	}
});