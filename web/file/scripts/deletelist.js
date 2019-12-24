	var ids=[];
	Array.prototype.indexOf = function(val) { 
		for (var i = 0; i < this.length; i++) { 
			if (ids[i] == val) return i; 
		} 
		return -1; 
	};
	//定义数组的删除方法
	Array.prototype.remove = function(val) { 
		var index = ids.indexOf(val); 
		if (index > -1) { 
			ids.splice(index, 1); 
		} 
	};

	//批量删除
	function delids(mapping,s){
		if(!s.disabled){
			if(!s.disabled){
				s.title="已启用";
			}else{
				s.title="已禁用";
			}
			s.disabled=!confirm("您要禁用批量删除功能吗?");
			
		}
		
		var obj = document.fom.elements;
		for (var i=0;i<obj.length;i++){
			if (obj[i].name == "delid"){
				if (obj[i].checked==true){
					ids.push(obj[i].value);
				}
				
			}
		}


		if(ids.length>0&&confirm("您确定全部删除吗?")){
			location.href=mapping+"_ids?ids="+ids;
		}else{
			alert("至少选择一个！");
			
		}
		   
	}
	
	
	//全选
	function selectAll(){
		var obj = document.fom.elements;
		for (var i=0;i<obj.length;i++){
			if (obj[i].name == "delid"){
				obj[i].checked = true;
			}
		}
		
	}
	
	//反选
	function unselectAll(){
		
		var obj = document.fom.elements;
		for (var i=0;i<obj.length;i++){
			if (obj[i].name == "delid"){
				if (obj[i].checked==true){
					 obj[i].checked = false;
					 ids.remove(obj[i].value);
				}
				else {
					obj[i].checked = true;
				}
			}
		}
	}