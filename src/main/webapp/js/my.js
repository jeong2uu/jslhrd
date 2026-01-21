//로그인 check() 함수 시작
	function check() {
		if(!my.userid.value){
			alert("아이디를 입력하세요");
			my.userid.focus();
			return false;
		}
		if(!my.password.value){
			alert("비밀번호를 입력하세요");
			my.password.focus();
			return false;
		}
		return true;
	}
//jQuery 시작문
$(function() {	
	$(".gnb_group > .gnb >.nav_1depth > li").hover(function() {
		$(this).children(".nav_2depth").stop().slideDown();
	},function() {
		$(".nav_2depth").stop().slideUp();
	})
	
	//회원가입 페이지에서만 writer 유효성 검사 실행
	if($("#writer").length > 0) {
		$("#writer").focus();
		$("#writer").blur(function() {
			//아이디가 writer인 태그에서 다른곳으로 커서가 이동하면 (blur) 아래를 실행
			if(!$("#writer").val()) {
				//만약에 아이디(#)가 writer인 태그의 값(val)이 없으면(!) 아래를 실행
				//$(".writer-msg").text("사용자명을 입력하세요");
				$(".writer-msg").html("<span style='color:#f00'>사용자명을 입력하세요</span>");
				//클래스(.)이름이 writer-msg인 태그의 내용(html)을 변경
				$("#writer").focus();
				//커서를 다시 아이디(#)가 writer인 태그로 이동(focus)시킨다
			}else{
				$(".writer-msg").text("");
				//클래스(.)이름이 writer-msg인 택드의 내용을 ""로 변경(초기화)한다
			}
		});
	}
		
	//회원가입 페이지에서만 userid 중복체크 실행
	if($("#userid").length > 0) {
		$("#userid").blur(function() {
			if(!$("#userid").val()) {
				$(".userid-msg").html("<span style='color:#f00'>아이디는 필수 입력사항 입니다. 아이디를 입력하세요.</span>");
				$("#userid").focus();
				return;
			}else{
				$(".userid-msg").text("");
			}
			
			//ajax 통신 시작 : userid 중복체크
			$.ajax({
				type:"POST", //전송방식 안보이게
				url :"/mem/useridCheck.do" , //요청경로
				data : {userid:$("#userid").val()}, // 요청파라미터
				success:function(result) {//응답데이터받는 곳.
					//alert("result : "+result);
					if(result==1) {//중복아이디 있음
						$(".userid-msg").html("<span style='color:#f00'>이미 사용중인 아이디 입니다.</span>");
					}else{
						$(".userid-msg").html("<span style='color:#f00'>사용가능한 아이디 입니다.</span>");
					}
				},error:function() { //통신실패
					alert("에러발생");
				}
			});
		});  //end of userid blur
	}
	
	//회원가입 페이지에서만 실행되는 유효성 검사
	if($("#btn-submit").length > 0) {
		$("#password").blur(function() {
			if(!$("#password").val()) {
				$(".password-msg").html("<span style='color:#f00'>비밀번호는 필수 입력 사항입니다.</span>");
				$("#password").focus();
			}
		})
		
		$("#pw2").blur(function() {
			if(!$("#pw2").val()) {
				$(".pw2-msg").html("<span style='color:#f00'>비밀번호 확인 필수 입력 사항입니다.</span>");
				$("#pw2").focus();
			}
		})
		
		$("#pw2").blur(function() {
			let pw1 = $("#password").val(); //let 은 재선언 불가능, 값 재할당 가능
			let pw2 = $("#pw2").val();
			if(pw1 != pw2) {
				$(".pw2-msg").html("<span style='color:#f00'>비밀번호가 일치하지 않습니다.</span>");
			}else {
				$(".pw2-msg").text("");
			}
		})
		$("#email").blur(function() {
			if(!$("#email").val()) {
				$(".email-msg").html("<span style='color:#f00'>이메일을 입력하세요.</span>");
				$("#email").focus();
			}
		})
		$("#phone").blur(function() {
			if(!$("#phone").val()) {
				$(".phone-msg").html("<span style='color:#f00'>전화번호를 입력하세요.</span>");
				$("#phone").focus();
			}
		});
		
		$("#btn-submit").click(function() {
		//아이디가 btn-submit인 태그를 클릭하면 아래를 실행
		var isvalid = true;
		$("#writer, #userid, #password, #pw2, #email, #phone").each(function() {
			//아이디가 writer, userid, password, pw2, email, phone 인 태그를 각각(each) 돌면서 아래를 실행
			if(!$(this).val()){
				//만약에 각각의 태그($(this))의 값(val)이 없으면 (!) 아래를 실행
				isvalid = false;
				$(this).focus();
				//커서를 각각의 태그로 이동(focus)시킨다
				return false; //each 탈출
			}
		});
		if(isvalid) {
			//만약에 isvalid가 true이면 아래를 실행
			$("#myform").attr("method","post");
			//아이디가 myform인 태그의 속성(attr) method를 post로 설정
			$("#myform").attr("action", "/mem/membersave.do");
			//아이디가 myform인 태그의 속성(attr) action을 /mem/membersave.do로 설정
			$("#myform").submit();		//아이디가 myform인 태그를 전송(submit)
		}
		})
	}
	
	//로그인 페이지에서만 쿠키 처리 실행
	if($("#loginUserid").length > 0 && $("#saveid").length > 0) {
		if($.cookie("saveid")) {
			//만약에 saveid라는 이름의 크키가 있으면 아래를 실행
			$("#loginUserid").val($.cookie("saveid"));
			//아이디 입력칸에 쿠키의 값을 넣음
			$("#saveid").prop("checked", true);
			//체크박스를 체크된 상태로 만들어 준다
		}
	}
	
	//login 유효성 검사 (로그인 페이지에서만 실행)
	if($("#btn-login").length > 0) {
		$("#btn-login").click(function() {
		let userid = $("#loginUserid").val();
		let password = $("#password").val();
		if(!userid) {
			$("#errmsg").html("<span style='color:#f00'>아이디를 입력하세요</span>");
			return;
		}
		if(!password) {
			$("#errmsg").html("<span style='color:#f00'>비밀번호를 입력하세요</span>");
			return;
		}
		$.ajax({
			type:"POST",
			url:"/mem/loginpro.do",
			data:{userid:userid, password:password},
			success:function(result) {
				if(result === "success") {//로그인성공
					if($("#saveid").is(":checked")) {
						//만약에 아이디 저장(체크박스)가 체크되어 있으면 아래를 실행
						$.cookie("saveid",userid,{expires:7, path:"/"});
						//쿠키이름,쿠키값,옵션(7일간유지,웹사이트 전체경로)
					}else {
						$.removeCookie("saveid", {path:"/"});
						//쿠키이름, 옵션(웹사이트 전체경로)
					}
					//메인페이지 이동=
					location.href='/';
				}else {
					$("#errmsg").html("<span style='color:#f00'>아이디또는 비밀번호를 확인하세요</span>");
				}
			},error:function() {
				$("#errmsg").html("<span style='color:#f00'>서버에러 발생</span>");
			}
			
		})
		})
	}

	
	

}) //end of $(function()