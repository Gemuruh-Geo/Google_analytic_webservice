<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<title>REST GA</title>
<script src="resources/mytheme/js/jquery.1.9.1.min.js"></script>
<link href="resources/mytheme/bootstrap/css/bootstrap.css" type="text/css" rel="stylesheet" />
<script type="text/javascript">
function sendAjax() {
	
	var jsonS = $('#gaproperties').val();
	jsonS = jsonS.replace(/\r?\n/g, '');
	jsonS = jsonS.replace(/\r?\t/g, '');
	
	
	$.ajax({ 
	    url: "callback", 
	    type: 'POST', 
	    dataType: 'json', 
	    data: JSON.stringify(jQuery.parseJSON(jsonS)), 
	    contentType: 'application/json',
	    mimeType: 'application/json',
	    success:function(data){
	    	window.location.href = 'pull';
	    	alert("Success");
	    },
	    error:function(data){
			alert("fail");
	    }
	});
	}
</script>


</head>

<body>

	<h1 style="text-align:center">Simple GA REST CLIENT<br></h1> 
	
	<!-- 
	<div class="form-horizontal" style="margin:10px;">
		<div class="form-group">
			<label for="inputEmail3" class="col-sm-2 control-label">GA Properties</label>
			<div class="col-sm-10">
				<textarea class="form-control" rows="3" id="gaproperties" name="gaproperties"></textarea>
			</div>
		</div>
		<br/>
		<p>
			<button class="btn btn-primary" type="button" onclick="sendAjax()">Sent</button>
		</p>
	</div>
	 -->
	 
	 <table>
	 	<tr>
	 		<td>
	 			<label for="gaproperties" class="col-sm-2 control-label">GA Properties</label>		
	 		</td>
	 		<td>
	 			<div class="col-sm-10">
					<textarea class="form-control" rows="3" id="gaproperties" name="gaproperties"></textarea>
				</div>
	 		</td>
	 		
	 	</tr>
	 	<tr>
	 		<td></td>
	 		<td>
	 			<button class="btn btn-primary" type="button" onclick="sendAjax()">Sent</button>
	 		</td>
	 	</tr>
	 </table>	 
	 
	
</body> 
</html>
