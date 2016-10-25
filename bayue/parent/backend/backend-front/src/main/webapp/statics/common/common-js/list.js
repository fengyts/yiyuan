$(function() {
	
	//隔行变色
	$("#listUser tr:even").css("background", "#FFE1FF");
	$("#listUser tr:even").attr("bg", "#FFE1FF");//#EED5D2  #DBDBDB  FFE1FF
	$("#listUser tr:odd").attr("bg", "#ffffff");

	$("#listUser tr").mouseover(function() {
		$(this).css("background", "#FFAEB9");//#FFAEB9 #A6A6A6  #bcd4ec
	}).mouseout(function() {
		var bgcolor = $(this).attr("bg");
		$(this).css("background",bgcolor);
	});

});
