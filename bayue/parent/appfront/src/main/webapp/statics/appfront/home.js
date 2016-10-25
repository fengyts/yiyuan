$(function() {
	
	$.init();
	$("#more").on('click',function(){
		
		alert(123);
	});
	
	$(document).on('click','.alert-text',function () {
        $.alert('Here goes alert text');
     });

    $(document).on('click','.alert-text-title', function () {
        $.alert('Here goes alert text', 'Custom Title!');
    });

    $(document).on('click', '.alert-text-title-callback',function () {
        $.alert('Here goes alert text', 'Custom Title!', function () {
            $.alert('Button clicked!')
        });
    });

    $(document).on('click', '.alert-text-callback',function () {
        $.alert('Here goes alert text', function () {
            $.alert('Button clicked!')
        });
    });
});