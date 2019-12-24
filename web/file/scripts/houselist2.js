$(function(){
	var code=$("#code").val().split("-");
	console.log($("#code").val());
		function areaload(){
			//加載位置區域
		   $("#street").load("region_findAll",function(data,status){
			  if(status=='success'){
				  $("#street").html("");
				  $("#street").append("<OPTION "+(code[0]=='1101'?'selected':'')+" value='1101'>不限</OPTION>");
				  var options=eval('('+data+')');//JSON.parse(data);
				  for(var i=0;i<options.length;i++){
					  var op=new Option(options[i].regionNameC,options[i].code);
					  if(op.value==code[0]){
						  op.selected=true;
					  }
					  $("#street").append(op);
				  }
				changeStreet();
			  }
		   });

		}
	
		areaload();
		function changeStreet(){
			$("#address").html("");
			$("#address").append("<OPTION selected value='"+$("#street").val()+"'>不限</OPTION>");
		   $("#address").load("region_findBySupperId","area="+$("#street").val(),function(data,status){
			   if(status=='success'){
					  var options=eval('('+data+')');//JSON.parse(data);
					  $("#address").append("<OPTION "+(code[1]==code[0]?'selected':'')+" value="+code[0]+">不限</OPTION>");
					  for(var i=0;i<options.length;i++){
						  var op=new Option(options[i].regionNameC,options[i].code);
						  console.log(op.selected);
						  if(op.value==code[1]){
							  op.selected=true;
						  }
						  $("#address").append(op);
					  }
				  }
			});
		   
		}
		$("#street").change(function(){
			changeStreet();
			$("#sform").submit();
		});
		
		$("#address").change(function(){
			$("#sform").submit();
		});
		$("#sform").submit(function(){
			var address=$("#address").val();
			var street=$("#street").val();
		});
	});
	