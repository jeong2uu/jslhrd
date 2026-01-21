<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../header.jsp" %>
    
    <div class="sub-container">
        <div class="sub-left">
            <h3 class="subtit">포트폴리오</h3>
            <div class="btn-sns">
                <a href="">블로그</a>
                <a href="">페이스북</a>
                <a href="">인스타그램</a>
            </div>
        </div>
        <div class="board-view">
            <div class="board-header">
                <span class="date">Regdate : ${viewdto.regdate.substring(0,10) }</span>
                <strong>${viewdto.title }</strong>
                <span class="icon-view">views : ${viewdto.views }</span>
            </div>
            <div class="board-body">
                <p>즐거운 여행 다녀왔습니다.</p>
                <p><img src="../images/image_1.jpg" alt=""></p>
                <p>${viewdto.content }</p>
            </div>
            <div class="board-controller">
                <a href="" class="prev">
                    <strong>Previous</strong>
                    <span>유럽여행</span>
                </a>
                <a href="" class="next">
                    <strong>Next</strong>
                    <span>에베레스트 여행</span>
                </a>
            </div>
            <div class="btn-board">
                <button type="button" class="submit" onclick="location.href='${pageContext.request.contextPath}/port/list.do'">목록</button>
                <button type="button" class="reset" onclick="updateGet(${viewdto.bno})">수정</button>
                <button type="button" class="list" onclick="deleteGet(${viewdto.bno})">삭제</button>
            </div>
        </div>
        <!-- <div class="side">
            <h2>JSL커뮤니티</h2>
            <ul class="menu">
                <li><a href="" class="active">공지사항</a></li>
                <li><a href="">일본취업정보</a></li>
                <li><a href="">질문답변</a></li>
                <li><a href="">인터뷰</a></li>
            </ul>
        </div> -->
    </div>
    
<script>
	function deleteGet(bno) {
		if(confirm("삭제할래요?")) {
			location.href="/port/delete.do?bno="+bno;
		}
	}
	function updateGet(bno) {
		if(confirm("수정할래요?")) {
			location.href="/port/update.do?bno="+bno;
		}
	}
</script>
<%@ include file="../footer.jsp" %>