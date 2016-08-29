<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>AD Manager Home</title>
    </head>
    <body>
    	<div align="center">
	        <h1>AD List</h1>
	        <h3><a href="newContact">New Contact</a></h3>
	        <table border="1">
	        	<tr>
		        	<th>No</th>
		        	<th>AD Code</th>
		        	<th>Branch Name</th>
		        	<th>Address</th>
		        	<th>RIT Code</th>
		        	<th>BankCode</th>
		        	<th>Entry User</th>
		        	<th>Action</th>
		        </tr>
	        	
				<c:forEach var="adscode" items="${listAdscode}" varStatus="status">
	        	<tr>
	        	
	        		<td>${status.index + 1}</td>
					<td>${adscode.adscode}</td>
					<td>${adscode.adsAddr1}</td>
					<td>${adscode.adsAddr2}</td>
					<td>${adscode.ewdRitCode}</td>
					<td>${adscode.bankCode}</td>
					<td>${adscode.entUser}</td>
					
					
					<td>
						<a href="editContact?adscode=${adscode.adscode}">Edit</a>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<a href="deleteContact?adscode=${adscode.adscode}">Delete</a>
					</td>
							
	        	</tr>
				</c:forEach>	        	
			</table>
    	</div>
    </body>
</html>
