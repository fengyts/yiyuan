$.extend({
	
	syncAjax : function( url, data ,dataType, type){
		if(dataType === null || '' === dataType){
			dataType = "text";
		}
		if(type === null || '' === type){
			type = "post";
		}
		var res;
		$.ajax({
			url : url,
			dataType : dataType,
			data : data,
			type : type,
			cache : false,
			error : function(request){
				alert("Server Connection Failure...");
			},
			success : function(response) {
				res = response;
			}
		});
		return res;
	}
	
	
	
	
});