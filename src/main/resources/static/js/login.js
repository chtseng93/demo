/**
 * 
 */
$( document ).ready(function() {
  // 在這撰寫javascript程式碼
  $(".message a").click(function () {
  $("#register-form").animate({
      height: "toggle", 
      opacity:"toggle"
  }, "slow");
  });
  $(".message a").click(function () {
  $("#login-form").animate({
      height: "toggle", 
      opacity:"toggle"
  }, "slow");
  });
  
  if(document.getElementById("errorRegisterMsg")){
	 console.log('STEP1');
	  if($("#errorRegisterMsg").length>0){
		  $("#register-form").show();
		  $("#login-form").hide();
		  console.log('STEP2');
		    
	  }
  }

});