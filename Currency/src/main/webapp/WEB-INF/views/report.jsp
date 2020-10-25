<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>

<head>
<title>Exchange Currency</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
</head>
<body>
	<c:choose>
		<c:when test="${not empty reportList}">
			<h2>Six month's Report</h2>
			<table class="table table-bordered">
				<thead class="thead-light">
					<tr>
						<th scope="col">Currency</th>
						<th scope="col">Month1</th>
						<th scope="col">Month2</th>
						<th scope="col">Month3</th>
						<th scope="col">Month4</th>
						<th scope="col">Month5</th>
						<th scope="col">Month6</th>
					</tr>
				</thead>
				<tr>
					<td>UK</td>
					<c:forEach var="currency" items="${reportList}">
						<c:choose>
							<c:when test="${not empty currency.rates.gbp}">
								<td>${currency.rates.gbp}</td>
							</c:when>
							<c:otherwise>
								<td>-</td>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</tr>
				<tr>
					<td>USA</td>
					<c:forEach var="currency" items="${reportList}">
						<c:choose>
							<c:when test="${not empty currency.rates.usd}">
								<td>${currency.rates.usd}</td>
							</c:when>
							<c:otherwise>
								<td>-</td>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</tr>
				<tr>
					<td>HK</td>
					<c:forEach var="currency" items="${reportList}">
						<c:choose>
							<c:when test="${not empty currency.rates.hkd}">
								<td>${currency.rates.hkd}</td>
							</c:when>
							<c:otherwise>
								<td>-</td>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</tr>
			</table>
		</c:when>
		<c:otherwise>
			<%@ include file="error.jsp"%>
		</c:otherwise>
	</c:choose>

</body>

</html>