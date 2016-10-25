var Namespace = new Object();
Namespace.register = function(path) {
	var arr = path.split(".");
	var ns = "";
	for (var i = 0; i < arr.length; i++) {
		if (i > 0)
			ns += ".";
		ns += arr[i];
		eval("if(typeof(" + ns + ") == 'undefined') " + ns + " = new Object();");
	}
};
Namespace.register("ng.bayue.form");

ng.bayue.form.Form = function() {

};

/**
 * ResponseObject对象
 * 
 * @param data
 */
ng.bayue.form.ResultMessage = function(data) {
	{
		this.data = eval('(' + data + ')');
	}

	/**
	 * 操作是否成功
	 * 
	 * @returns {Boolean}
	 */
	this.isSuccess = function() {
		return this.data['result'] == 1;
	};

	/**
	 * 获取响应信息
	 * 
	 * @returns
	 */
	this.getMessage = function() {
		return this.data['message'];
	};
};
