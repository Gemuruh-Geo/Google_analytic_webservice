function sendAjax() {
 
	// get inputs
	/*var article = new Object();
	article.title = $('#title').val();
	article.url = $('#url').val();
	article.categories = $('#categories').val().split(";");
	article.tags = $('#tags').val().split(";");*/
	var article = new Object();
	
	article.trackingCode="WT2073";
	article.gaproperties = {"startDate":"2014-04-07","endDate":"2014-04-21","metrics":"ga:visits,ga:pageviews","dimension":"ga:source,ga:keyword,ga:pageTitle","sortBy":"-ga:visits,ga:source"};
	$.ajax({
		url: "pull",
		type: 'POST',
		dataType: 'json',
		data: JSON.stringify(article),
		contentType: 'application/json',
		mimeType: 'application/json',
		
		success: function () {
			 window.location.href = "http://localhost:8080/jet-filter-ws/pull";
        },
		error:function(data,status,er) {
			alert("error: "+data+" status: "+status+" er:"+er);
		}
	});
}