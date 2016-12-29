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
	},

	formDataJson : function( formId ){
		var _o = "{";
		$.each( $( formId ).serializeArray(), function( i, v ){
			var _to = "'" + v.name +"':'" + v.value + "'";
//			var _to = v.name +":" + v.value;
			_o +=  _to + ",";
		} );
		_o = _o.substring(0, _o.length-1)+"}";
		return _o;
	},
	
	
//	var json = {};
//    $.each(_data, function () {
//        if (json[this.name] != undefined) {
//            if (!json[this.name].push) {
//                json[this.name] = [json[this.name]];
//            }
//            json[this.name].push(this.value || '');
//        } else {
//            json[this.name] = this.value || '';
//        }
//    });
	
	
	
	
});