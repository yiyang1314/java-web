$(function(){
		function areaload(){
			//加載房屋類型
			$("#type_id").load("style_findAll",function(data,status){
				  if(status=='success'){
					  $("#type_id").append("<OPTION selected value='1'>不限</OPTION>");
					  var options=eval('('+data+')');//JSON.parse(data);
					  console.log(options);
					  for(var i=0;i<options.length;i++){
						  var op=new Option(options[i].styleName,options[i].styleid);
						  $("#type_id").append(op);
					  }
				  }
				  
			   });
			//加載位置區域
		   $("#street").load("region_findAll",function(data,status){
			  if(status=='success'){
				  $("#street").html("");
				  $("#street").append("<OPTION selected value='1101'>不限</OPTION>");
				  var options=eval('('+data+')');//JSON.parse(data);
				  for(var i=0;i<options.length;i++){
					  var op=new Option(options[i].regionNameC,options[i].code);
					  $("#street").append(op);
				  }
				  changeStreet();
			  }
		   });
	
		  
		}
	
		areaload();
		var count=0;
		$("#street").change(function(){
			$("#address").html(""); 
			$("#address").append("<OPTION selected value='110101'>不限</OPTION>");
			changeStreet();
		});
		function changeStreet(){
		   $("#address").load("region_findBySupperId","area="+$("#street").val(),function(data,status){
			   if(status=='success'){
					  var options=eval('('+data+')');//JSON.parse(data);
					  console.log(options);
					  for(var i=0;i<options.length;i++){
						  var op=new Option(options[i].regionNameC,options[i].code);
						  $("#address").append(op);
					  }
				  }
			});
		}

		$("#sform").submit(function(){
			//var form=$("#sform").serialize();
		  // console.log(form);
		   $("myarea").val($("#address").val());

		});
	});
	