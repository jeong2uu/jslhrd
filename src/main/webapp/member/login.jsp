<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../header.jsp" %>
    
    <div class="subvisual">
        <div class="sub-inner">
            <div class="sub-title">
                <h2>Sign In</h2>
                <p>Goooooooooood Man</p>
            </div>
        </div>
    </div>
    
    <div class="sub-container">
        <div class="loginbox">
            <h2>LOGIN</h2>
            <form id="myform">
                <table class="border-table">
                    <tr>
                        <td>
                            <input type="checkbox" name="useridcheck" id="saveid">&nbsp;&nbsp;아이디저장
                        </td>
                    </tr>
                    <tr>
                        <td><input type="text" name="userid" id="loginUserid" placeholder="아이디"></td>
                    </tr>
                    <tr>
                        <td><input type="password" name="password" id="password" placeholder="비밀번호"></td>
                    </tr>
                    <tr>
                        <td>
                            <button type="button" class="submit" id="btn-login" style="width: 100%; cursor:pointer">로그인</button>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <a href="">아이디.비밀번호 찾기</a> 
                            <a href="">회원가입</a>
                        </td>
                    </tr>
                    <tr>
                        <td style="text-align: center; padding-top: 15px;">
                            <div id="google-signin-button"></div>
                        </td>
                    </tr>
                </table>
                <p id="errmsg"></p>
            </form>
        </div>
    </div>

<!-- Google Identity Services 라이브러리 -->
<script src="https://accounts.google.com/gsi/client" async defer></script>
<script type="text/javascript">
    // Google Identity Services 초기화
    window.onload = function() {
        google.accounts.id.initialize({
            client_id: "888481044220-hncob4mhcr6t027h49p85vdd2u1aamoe.apps.googleusercontent.com",
            callback: handleCredentialResponse
        });
        
        // 구글 로그인 버튼 렌더링
        google.accounts.id.renderButton(
            document.getElementById("google-signin-button"),
            {
                type: "standard",
                size: "large",
                theme: "outline",
                text: "signin_with",
                shape: "rectangular",
                logo_alignment: "left"
            }
        );
        
        // 자동 로그인 프롬프트 비활성화
        google.accounts.id.prompt();
    };
    
    // 구글 로그인 성공 콜백
    function handleCredentialResponse(response) {
        // ID 토큰을 서버로 전송
        $.ajax({
            url: '/mem/googleLogin.do',
            type: 'POST',
            data: {
                credential: response.credential
            },
            success: function(result) {
                if(result === 'success') {
                    alert('구글 로그인 성공!');
                    location.href = '/';
                } else {
                    alert('구글 로그인 실패: ' + result);
                }
            },
            error: function(xhr, status, error) {
                alert('구글 로그인 중 오류가 발생했습니다.');
                console.error(error);
            }
        });
    }
</script>
<%@ include file="../footer.jsp" %>