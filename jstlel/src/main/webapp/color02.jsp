<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<c:choose>
		<c:when test='${param.color == "red" }'>
			<h1 style='color: #FF0000'>Hello World</h1>
		</c:when>
		<c:when test='${param.color == "blue" }'>
			<h1 style='color: #0000FF'>Hello World</h1>
		</c:when>
		<c:when test='${param.color == "green" }'>
			<h1 style='color: #00FF00'>Hello World</h1>	
		</c:when>
		<c:otherwise>
			<h1 style='color: #000000'>Hello World</h1>	
		</c:otherwise>
	</c:choose>

</body>
</html>