$(document).ready(function() {
    $('.icon-container i').click(function(){
        $(this).parents('.left-container').find('.left-inside').toggleClass("hide");
        $(this).parents('.left-container').find('.hided-inside').toggleClass("show");
        $(this).parents('.left-container').toggleClass("shrink");
        $(this).parents('.main-container').find('.right-container').toggleClass("expand");
        $(this).toggleClass("rotate");
    });
    $('header .fa-search').click(function(){
        $(this).parent('header').find('.search-input').toggleClass("show");
         $(this).parent('header').find('.logo').hide();
    });
    $('.custom-notification').click(function(){
        $(this).find('.notification-dropdown').toggleClass("show");
    });
});