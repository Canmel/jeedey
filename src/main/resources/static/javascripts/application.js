$(function() {
	$(document).pjax('a:not([data-remote]):not([data-behavior]):not([data-skip-pjax]):not([data-method="delete"])', '[data-pjax-container]',{timeout: 50000});

	$(document).on('submit', '.pjax-form', function(event) {
        $.pjax.submit(event, '[data-pjax-container]',{timeout: 50000});
    });
	
	$(document).on('pjax:end', function() {
		$(".edit").click(function() {
		});
		$(".wrapper a").click(function() {
			initLabelA($(this));
		});
	});
	
});

function initLabelA(node) {
	pget($(node).attr("data-method"), $(node).attr("value"), $(node).attr("confirm"));
}

function pget(met, url, conf) {
	if(met != null && !confirm(conf)){
		return false;
	}
	 $.pjax({
         url: url,
         method: met == null ? "get" : met,
         container: '.wrapper'
     });
}
