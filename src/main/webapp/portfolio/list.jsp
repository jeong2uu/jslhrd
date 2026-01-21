<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="../header.jsp" %>
    <div class="subvisual">
        <div class="sub-inner">
            <div class="sub-title">
                <h2>PORTFOLIO</h2>
                <p>Goooooooooood Man</p>
            </div>
        </div>
    </div>
    
    <div class="sub-container">
        <div class="search-group">
            <div class="count">
                <span class="count">총게시글 : 120</span>
            </div>
            <div class="search">
                <form name="my" method="post" action="" onsubmit="return check()">
                    <select name="type">
                        <option value="">선택</option>
                        <option value="title">제목</option>
                        <option value="content">내용</option>
                    </select>
                    <input type="text" name="keyword">
                </form>
            </div>
        </div>
        <c:forEach var="item" items="${list}">
	        <div class="search-content">
	            <div class="date">
	                <span>${item.regdate.substring(7,10) }</span>
	                <em>${item.regdate.substring(0,7) }</em>
	            </div>
	            <div class="search-img">
	                <img src="${pageContext.request.contextPath}/img/${item.imgfile}" alt="단품이미지">
	            </div>
	            <div class="search-text">
	                <div>
	                    <span>No.${item.bno }</span> | <span>${item.views }</span>
	                </div>
	                <div>
	                    <a href="/port/view.do?bno=${item.bno }"><h3>${item.title }</h3></a>
	                </div>
	                <div class="txt">
	                	<div style="height:72px; overflow:hidden;">
	                    ${item.content }
	                    </div>
	                    <p><a href="/port/view.do?bno=${item.bno }" class="more">MORE</a></p>
	                </div>
	            </div>
	        </div>
        </c:forEach>
        </div>
        
        <div class="page">
            <div class="number">
	            <a href="">&lt;&lt;</a>
	            <a href="" class="active">1</a>
	            <a href="">2</a>
	            <a href="">3</a>
	            <a href="">4</a>
	            <a href="">5</a>
	            <a href="">&gt;&gt;</a>
            </div>
            <div class="writer">
                <a href="/port/write.do">글쓰기</a>
            </div>
        </div>
   
    
    
<%@ include file="../footer.jsp" %>