<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
	"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>New/Edit Contact</title>
</head>
<body>
	<div align="center">
		<h1>New/Edit Contact</h1>
		<form:form action="abc" method="post" modelAttribute="adscode">
		<table>
			<form:hidden path="adscode"/>
			<tr>
				<td>adscode:</td>
				<td><form:input path="adscode" /></td>
			</tr>
			<tr>
				<td>Branch Name</td>
				<td><form:input path="adsAddr1" /></td>
			</tr>
			<tr>
				<td>Address:</td>
				<td><form:input path="adsAddr2" /></td>
			</tr>
			<tr>
				<td>ewdRitCode:</td>
				<td><form:input path="ewdRitCode" /></td>
			</tr>
			<tr>
				<td colspan="2" align="center"><input type="submit" value="Save"></td>
			</tr>
		</table>
		</form:form>
	</div>
</body>
</html>