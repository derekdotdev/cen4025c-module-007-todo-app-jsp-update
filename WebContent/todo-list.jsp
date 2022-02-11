<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
	<meta charset="UTF-8">
	<title>To Do List Application</title>
</head>
<body>
	<div align="center">
		<h1>To Do List</h1>
			<h2>
				<a href="new">Add New Item</a>
				&nbsp;&nbsp;&nbsp;
				<a href="list">List All Items</a>
			
			</h2>
	</div>
	<div align="center">
	
		<table style="border-collapse: separate; border-spacing: 10px;">
		
			<tr>
				<th>ID</th>
				<th>Description</th>
				<th>Actions</th>
			
			</tr>
			<c:forEach var="todoItem" items="${listOfTodoItems}">
			
				<tr>
				
					<td><c:out value="${todoItem.id}" /></td>
					<td><c:out value="${todoItem.description}" /></td>
					<td>
						<a href="edit?id=<c:out value='${todoItem.id}' />">Edit</a>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<a href="delete?id=<c:out value='${todoItem.id}' />">Delete</a>
					
					</td>
				</tr>
			</c:forEach> 
		</table>
	</div>	
</body>
</html>