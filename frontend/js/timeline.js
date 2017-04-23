$(document).ready(function() {
    $('.icon-container i').click(function(){
        $(this).parents('.left-container').find('.left-inside').toggleClass("hide");
        $(this).parents('.left-container').find('.hided-inside').toggleClass("show");
        $(this).parents('.left-container').toggleClass("shrink");
        $(this).parents('.main-container').find('.right-container').toggleClass("expand");
        $(this).toggleClass("rotate");
    });
});