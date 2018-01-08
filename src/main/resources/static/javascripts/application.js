$(function() {
	$(document)
			.pjax(
					'a:not([data-remote]):not([data-behavior]):not([data-skip-pjax]):not([data-method="delete"])',
					'[data-pjax-container]', {
						timeout : 50000
					});

	$(document).on('submit', '.pjax-form', function(event) {
		$.pjax.submit(event, '[data-pjax-container]', {
			timeout : 50000
		});
	});

	$(document).on('pjax:end', function() {
		$(".edit").click(function() {
		});

		$('#export_btn').bind("click", function() {
			var h = window.location.href;
			var h0 = h.split("?")[0];
			var h1 = h.split("?")[1];
			if (h0.indexOf("#") > 0) {
				h0 = h0.split("#")[0]
			}
			h = h0 + '/export';
			if (h1 != 'undefined' && h1 != undefined) {
				h = h + '?' + h1
			}
			window.open(h, '_blank');
		});
		
//		treeBtnEnvent();
	});

});

function initLabelA(node) {
	pget($(node).attr("data-method"), $(node).attr("value"), $(node).attr(
			"confirm"));
}

function pget(met, url, conf) {
	if (met != null && !confirm(conf)) {
		return false;
	}
	$.pjax({
		url : url,
		method : met == null ? "get" : met,
		container : '.wrapper'
	});
}


function treeBtnEnvent(){
	// Expand/collapse/toggle nodes
    $('#input-disable-node').on('keyup', function (e) {
      disabledNodes = findDisabledNodes();
      $('.disable-node').prop('disabled', !(disabledNodes.length >= 1));
    });

    $('#btn-disable-node.disable-node').on('click', function (e) {
      $disabledTree.treeview('disableNode', [ disabledNodes, { silent: $('#chk-disable-silent').is(':checked') }]);
    });

    $('#btn-enable-node.disable-node').on('click', function (e) {
      $disabledTree.treeview('enableNode', [ disabledNodes, { silent: $('#chk-disable-silent').is(':checked') }]);
    });

    $('#btn-toggle-disabled.disable-node').on('click', function (e) {
      $disabledTree.treeview('toggleNodeDisabled', [ disabledNodes, { silent: $('#chk-disable-silent').is(':checked') }]);
    });

    // Expand/collapse all
    $('#btn-disable-all').on('click', function (e) {
      $disabledTree.treeview('disableAll', { silent: $('#chk-disable-silent').is(':checked') });
    });

    $('#btn-enable-all').on('click', function (e) {
      $disabledTree.treeview('enableAll', { silent: $('#chk-disable-silent').is(':checked') });
    });
}
