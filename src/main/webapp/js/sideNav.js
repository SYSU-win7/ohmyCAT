;(function($) {
	$('.L-sideNavList').css('height', screen.availHeight+'px');

	$('.M-option li > a').click(function() {
		if ($(this).next().get(0)) {
			$(this).next().slideToggle();
			var icon = $(this).find('.listIcon');
			console.log(icon.attr('class'));
			if (icon.attr('class').indexOf('M-active') == -1) {
				icon.addClass('M-active');
			} else {
				icon.removeClass('M-active');
			}
		}
	});
})(jQuery);