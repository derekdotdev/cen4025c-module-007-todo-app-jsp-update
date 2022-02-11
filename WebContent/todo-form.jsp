<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%-- <%@ taglib uri="http://java.sun.com/jsp/jstl/core_1_1" prefix="c"%> --%>
<%-- <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> --%>
<%@taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<!-- <!DOCTYPE html> -->
<html>
<head>
<!-- <meta charset="UTF-8"> -->
<meta charset="UTF-8">
<title>To Do List Application</title>
</head>
<body>
	<div align="center">
		<h1>To Do List</h1>
		<h2>
			<a href="new">Add New Item</a> &nbsp;&nbsp;&nbsp; <a
				href="list">List All Items</a>
		</h2>
	</div>
	<div align="center">
		<c:if test="${todoItem != null}">
			<form action="update" method="post"></form>
		</c:if>
		<c:if test="${todoItem == null}">
			<form action="insert" method="post"></form>
		</c:if>
				<table style="border-collapse: separate; border-spacing: 10px;">
					<caption>
						<c:if test="${todoItem != null}">
            			Edit To Do Item
            		</c:if>
						<c:if test="${todoItem == null}">
            			Add New To Do Item
            		</c:if>
					</caption>
					<c:if test="${todoItem != null}">
						<input type="hidden" name="id"
							value="<c:out value='${todoItem.id}' />" />
					</c:if>
					<tr>
						<th>Item Description:</th>
						<td><input type="text" name="description" size="45"
							value="<c:out value='${todoItem.description}' />" />
						</td>
					</tr>
					<tr>
						<td colspan="2" align="center"><input type="submit"
							value="Save" /></td>
					</tr>
				</table>
			<!-- </form> -->
	</div>
</body>
</html>