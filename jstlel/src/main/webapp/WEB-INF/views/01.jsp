<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h4>값출력</h4>
	${iVal } <br>
	${lVal } <br>
	${fVal } <br>
	${bVal } <br>
	${sVal } <br>
				
	<h4>객체 출력</h4>
	--${obj}--  <br>	
	${user.id } <br>
	${user.name } <br>
	
	<h4>Map 객체 출력</h4>
	${m.iVal } <br>
	${m.bVal } <br>
	${m.sVal } <br>
	
	<h4>Literal</h4>
	${1 } <br>
	${"Hello World" } <br>
	${true } <br>

	<h4>산술연산</h4>
	${1 + 2} <br>
	${iVal + 2} <br>
	
	<h4>관계 연산</h4>
	${1 < 2} <br>
	${iVal < 2} <br>
	${obj == null} <br>
	${empty obj } <br>
	${not empty obj } <br>

	<h4>논리 연산</h4>
	${iVal != 10 && lval >= 10} <br>
	${lval >= 10} <br>
	${lVal } <br>
	
	<h4>요청 파라미터</h4>
	${param.no } <br>
	${param.name } <br>
	
	<h4>Context Path</h4>
	${pageContext.request.contextPath } <br>
	
</body>
</html>