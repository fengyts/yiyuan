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
	
	/**
	 * formId:表单id
	 * rad: 是否存在radio元素，存在则为true,否则可以不传，因为如果存在radio元素，则radio属性序列化为字符串时值是on，而不是boolean类型的
	 * */
	formDataJson : function( formId , rad){
		var _o = "{";
		$.each( $( formId ).serializeArray(), function( i, v ){
//			var _to = "'" + v.name +"':'" + v.value + "'";
			var _to;
			if(rad && v.value == 'on'){
				_to = "'" + v.name +"':'" + true + "'";
			}else{
				_to = "'" + v.name +"':'" + v.value + "'";
			}
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