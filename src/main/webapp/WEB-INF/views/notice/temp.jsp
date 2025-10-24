<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<title>공지사항</title>
	
	<c:import url="/WEB-INF/views/common/header.jsp"></c:import>

	<link href="/css/notice/list.css" rel="stylesheet">
</head>

<body class="g-sidenav-show bg-gray-100">
	<c:import url="/WEB-INF/views/common/sidebar.jsp"></c:import>
  
  <main class="main-content position-relative max-height-vh-100 h-100 border-radius-lg">
    <c:import url="/WEB-INF/views/common/nav.jsp"></c:import>
    <section class="border-radius-xl bg-white ms-2 mt-2 me-3 p-4" style="height: 90vh; overflow: hidden scroll;">
    	<div class="col-10 offset-1 mt-3">
    	
    	<!-- 검색창 -->
    	<form action="/notice/temp" class="mt-4 mb-4">
		    <div class="d-flex justify-content-end">
		        <div class="d-flex">
		            <input type="text" class="form-control" placeholder="제목 또는 작성자로 검색" name="keyword" value="${ pager.keyword }" style="width: 200px; height: 30px; border-radius: 0.375rem 0 0 0.375rem !important;">
		            <button class="btn btn-dark py-0" type="submit" style="width: 60px; height: 30px; border-radius: 0 6px 6px 0;">검색</button>
		        </div>
		    </div>
		</form>
    
    	<!-- 공지 존재 -->
    	<c:if test="${ totalNotice gt 0 }">
    	<div class="mt-3" style="min-height: 500px;">
		<table class="table table-hover align-middle text-center">
			<thead>
				<tr>
					<th scope="col">No</th>
					<th scope="col">부서</th>
					<th scope="col">제목</th>
					<th scope="col">작성자</th>
					<th scope="col">작성일</th>
					<th scope="col">조회수</th>
				</tr>
			</thead>
			<tbody>
				<!-- 일반공지 -->
				<c:forEach items="${ notice.content }" var="n">
					<tr>
						<th scope="row">${ n.noticeNum }</th>
						<td>${ n.staffDTO.deptDTO.deptDetail }팀</td>
						<td class="text-start"><a class="text-decoration-none" href="/notice/${ n.noticeNum }">${ n.noticeTitle }</a></td>
						<td>${ n.staffDTO.staffName }</td>
						<td>${ n.noticeDate }</td>
						<td>${ n.noticeHits }</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		</div>

		<!-- 페이지네이션 -->
		<c:if test="${ notice.content.size() gt 0 }">
		<nav>
			<ul class="pagination justify-content-center">
				<c:if test="${ notice.hasPrevious() and pager.startPage gt 1 }">
					<li class="page-item">
						<a class="page-link" href="?page=${ pager.startPage - 1 }&keyword=${ pager.keyword }">&lt;</a>
					</li>
				</c:if>
				
				<c:forEach var="i" begin="${ pager.startPage }" end="${ pager.endPage }">
					<li class="page-item ${ i == notice.number ? 'active' : '' }">
						<a class="page-link" href="?page=${i}&keyword=${ pager.keyword }">${i + 1}</a>
					</li>
				</c:forEach>
				
				<c:if test="${ notice.hasNext() and pager.endPage + 1 ne notice.totalPages }">
					<li class="page-item">
						<a class="page-link" href="?page=${ pager.endPage + 1 }&keyword=${ pager.keyword }">&gt;</a>
					</li>
				</c:if>
			</ul>
		</nav>
    	</c:if>
    	</c:if>
    	<!-- 공지 존재 -->
    	
    	<!-- 공지 없음 -->
		<c:if test="${ totalNotice eq 0 }">
			<div class="d-flex flex-column justify-content-center align-items-center mt-8">
	  	<img width="150" height="180" src="/images/nothing.png" />
	  	<h4 class="mt-5">검색 결과가 없습니다.</h4>
	  </div>
		</c:if>    	
    	<!-- 공지 없음 -->
    	
    	</div>
    </section>
  </main>
	<c:import url="/WEB-INF/views/common/footer.jsp"></c:import>
	<script>
		document.querySelector("i[data-content='공지사항']").parentElement.classList.add("bg-gradient-dark", "text-white")
		document.querySelector("#navTitle").textContent = "임시보관함"
	</script>
</body>

</html>