layui.config({
  base : "js/"
}).use(['form','layer'],function(){
  var form = layui.form(),
      layer = parent.layer === undefined ? layui.layer : parent.layer,
      $ = layui.jquery;
  //video
  $(window).resize(function(){
    if($(".video-player").width() > $(window).width()){
      $(".video-player").css({"height":$(window).height(),"width":"auto","left":-($(".video-player").width()-$(window).width())/2});
    }else{
      $(".video-player").css({"width":$(window).width(),"height":"auto","left":-($(".video-player").width()-$(window).width())/2});
    }
  }).resize();

  //
  form.on("submit(login)",function(data){
    window.location.href = "/main.jsp";
    return false;
  })
})