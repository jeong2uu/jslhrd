<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../header.jsp" %>
    
    <div class="subvisual">
        <div class="sub-inner">
            <div class="sub-title">
                <h2>Join Us</h2>
                <p>Goooooooooood Man</p>
            </div>
        </div>
    </div>
    
    <div class="sub-container">
        <div class="writebox">
            <form name="my" id="myform">
                <table class="border-table">
                    <tr>
                        <th>사용자명</th>
                        <td>
                        	<input type="text" name="writer" id="writer">
                        	<p class="writer-msg"></p>
                        </td>
                    </tr>
                    <tr>
                        <th>아이디</th>
                        <td>
                        	<input type="text" name="userid" id="userid">
                        	<p class="userid-msg"></p>
                        </td>
                    </tr>
                    <tr>
                        <th>패스워드</th>
                        <td>
                        	<input type="password" name="password" id="password">
                        	<p class="password-msg"></p>
                        </td>
                    </tr>
                    <tr>
                        <th>패스워드확인</th>
                        <td>
                        	<input type="password" name="passwordcheck" id="pw2">
                        	<p class="pw2-msg"></p>
                        </td>
                    </tr>
                    <tr>
                        <th>이메일</th>
                        <td>
                        	<input type="email" name="email" id="email">
                        	<p class="email-msg"></p>
                        </td>
                    </tr>
                    <tr>
                        <th>전화번호</th>
                        <td>
                        	<input type="text" name="phone" id="phone">
                        	<p class="phone-msg"></p>
                        </td>
                    </tr>
                    <tr>
                        <th>첨부파일</th>
                        <td>
                            <input type="file" name="imgFile">
                            <img src="../images/image_1.jpg" alt="" style="width: 36px; height: 36px;">
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <button type="button" class="submit" id="btn-submit">회원가입</button>
                            <button type="button" class="reset" id="btn-reset">다시쓰기</button>
                            <button type="button" class="list" onclick = "javascript:history.go(-1)">되돌아가기</button>
                        </td>
                    </tr>
                </table>
            </form>
        </div>
    </div>
    
    
    
    
<%@ include file="../footer.jsp" %>