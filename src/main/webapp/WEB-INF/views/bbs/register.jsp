<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<!-- 위 3개의 메타 태그는 *반드시* head 태그의 처음에 와야합니다; 어떤 다른 콘텐츠들은 반드시 이 태그들 *다음에* 와야 합니다 -->
	<title></title>
	<link rel="stylesheet" href="/resources/css/bootstrap.css" />
	<style>
		.fileDrop {
			width: 100%;
			height:200px;
			border: 1px dotted blue;
		}
	</style>
</head>
<body>
<div class="container">
	<jsp:include page="../header.jsp" />

	<br />

	<div class="well">

		<form role="form" method="post" action="/bbs/register">
			<input type="hidden" name="page" value="${criteria.page}">
			<input type="hidden" name="perPageNum" value="${criteria.perPageNum}">
			<input type="hidden" name="searchType" value="${criteria.searchType}">
			<input type="hidden" name="keyword" value="${criteria.keyword}">

			<div class="form-group">
				<label for="title">Title</label>
				<input type="text" class="form-control" name="title" id="title">
			</div>

			<div class="form-group">
				<label for="content">Content</label>
				<textarea class="form-control" name="content" id="content" rows="5"></textarea>
			</div>

			<div class="form-group">
				<label for="writer">Writer</label>
				<input type="text" class="form-control" name="writer" id="writer" value="${login.id}" readonly>
			</div>

			<div class="fileDrop form-group">
			</div>

			<div class="uploadedList row">
			</div>
		</form>

		<div align="right" class="list-group">
			<button class="btn btn-primary" type="submit" id="btnRegister">등록</button>
			<button class="btn btn-default" type="submit" id="btnList">List</button>
		</div>
	</div>
</div>

<script src="/resources/js/jquery-3.2.1.js" ></script>
<script src="/resources/js/bootstrap.js" ></script>
<script src="/static/common/common-js.js"></script>
<script src="/static/bbs/register.js" ></script>
</body>
</html>