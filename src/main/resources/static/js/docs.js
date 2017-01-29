/*
 * Documentation JS script
 */
$(function () {
    $('.list-group-item').on('click', function() {
        $('.glyphicon-chevron-right ,.glyphicon-chevron-down', this)
            .toggleClass('glyphicon-chevron-right')
            .toggleClass('glyphicon-chevron-down');
    });
    var slideToTop = $("<div />");
    slideToTop.html('<i class="fa fa-chevron-up"></i>');
    slideToTop.css({
        position: 'fixed',
        bottom: '20px',
        right: '25px',
        width: '40px',
        height: '40px',
        color: '#eee',
        'font-size': '',
        'line-height': '40px',
        'text-align': 'center',
        'background-color': '#222d32',
        cursor: 'pointer',
        'border-radius': '5px',
        'z-index': '99999',
        opacity: '.7',
        'display': 'none'
    });
    slideToTop.on('mouseenter', function () {
        $(this).css('opacity', '1');
    });
    slideToTop.on('mouseout', function () {
        $(this).css('opacity', '.7');
    });
    $('.wrapper').append(slideToTop);
    $(window).scroll(function () {
        if ($(window).scrollTop() >= 150) {
            if (!$(slideToTop).is(':visible')) {
                $(slideToTop).fadeIn(500);
            }
        } else {
            $(slideToTop).fadeOut(500);
        }
    });
    $(slideToTop).click(function () {
        $("body").animate({
            scrollTop: 0
        }, 500);
    });
    $(".sidebar-menu li:not(.treeview) a").click(function () {
        var $this = $(this);
        var target = $this.attr("href");
        if (typeof target === 'string') {
            $("body").animate({
                scrollTop: ($(target).offset().top) + "px"
            }, 500);
        }
    });
    //Skin switcher
    var current_skin = "skin-blue";
    $('#layout-skins-list [data-skin]').click(function (e) {
        e.preventDefault();
        var skinName = $(this).data('skin');
        $('body').removeClass(current_skin);
        $('body').addClass(skinName);
        current_skin = skinName;
    });

    /**Function to hide/show forgot password panel **/
    $('#forgrt-password-link').click(function (e) {
        toggelForgotPasswordPannel();
    });
    $('#login-link').click(function (e) {
        toggelForgotPasswordPannel();
    });

    function toggelForgotPasswordPannel() {
        var $forgotPasswordPanel = $('#forgot-password-panel');
        var $loginPanel = $('#login-panel');

        if ($forgotPasswordPanel.is(':visible')) {
            $forgotPasswordPanel.hide();
            $loginPanel.show();

        } else {
            $forgotPasswordPanel.show();
            $loginPanel.hide();
        }
    }

    /**END Function to hide/show forgot password panel **/

    /*function to show hide client info form in user regestration*/
    $('#client-info').hide();
    $('#client-admin').click(function(e) {
        $('#client-admin').is(':checked') ?
            toggleClientInfoPanel(true) :
            toggleClientInfoPanel(false);

        function   toggleClientInfoPanel(display){
            $('#client-admin').prop('checked', display);
            display ? $('#client-info').show() : $('#client-info').hide();
        }
    });
    function sohowClientPage() {



    }

    /* end function to show hide client info form in user regestration*/



});
