$(function () {
    $("#jqGrid").jqGrid({
        url: '../picture/list',
        datatype: "json",
        colModel: [			
			{ label: 'imgId', name: 'imgId', width: 50, key: true,hidden : true },
			{ label: '文件名称', name: 'imgName', width: 80 },
			{ label: '显示状态', name: 'imgState', width: 80, formatter:function(value, options, row){
				if(value == '0'){
					return '<span class="label label-primary">隐藏</span>';  
				}
				if(value =='1'){
					return '<span class="label label-danger">显示</span>';  
				}
			}   }
			/*{ label: '最后修改人id', name: 'lastUpdateUserId', width: 80 },
			{ label: '最后修改时间', name: 'lastUpdateDate', width: 80 }*/
        ],
		viewrecords: true,
        height: 536,
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
        	//隐藏grid底部滚动条
//        	$("#jqGrid").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" }); 
        }
    });
    
});
var setting = {
		data: {
			simpleData: {
				enable: true,
				idKey: "imgId",
				pIdKey: "parentId",
				rootPId: -1
			},
			key: {
				url:"nourl"
			}
		}
};

var vm = new Vue({
	el:'#rrapp',
	data:{
		showList: true,
		title: null,
		pictureEntity: {
		}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		update: function (event) {
			debugger;
			var imgId = getSelectedRow();
			if(imgId == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";
            vm.getInfo(imgId)
		},
		saveOrUpdate: function (event) {
			debugger;
			var url = "../picture/update";
			//vm.sysDept.duty=($("#duty").val()).replace(/\n|\r\n/g,"<br>");
			//vm.sysDept.regime=($("#regime").val()).replace(/\n|\r\n/g,"<br>");
			//vm.sysDept.workStandard=($("#workStandard").val()).replace(/\n|\r\n/g,"<br>");
			
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.pictureEntity),
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
			});
		},
		getInfo: function(id){
			$.get("../picture/info/"+id, function(r){
                vm.pictureEntity = r.pictureEntity;
                /*$("#duty").val(vm.sysDept.duty);
                $("#regime").val(vm.sysDept.regime);
                $("#workStandard").val(vm.sysDept.workStandard);*/
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