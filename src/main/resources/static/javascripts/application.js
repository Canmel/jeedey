$(function() {
	$(document).pjax('a:not([data-remote]):not([data-behavior]):not([data-skip-pjax]):not([data-method="delete"])', '[data-pjax-container]',{timeout: 50000});

	$(document).on('submit', '.pjax-form', function(event) {
        $.pjax.submit(event, '[data-pjax-container]',{timeout: 50000});
    });
	
	$(document).on('pjax:end', function() {
		$(".edit").click(function() {
		});
	});
});