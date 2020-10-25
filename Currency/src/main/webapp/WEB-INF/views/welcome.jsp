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
</style>
<body class="d-flex justify-content-center">
	<c:choose>
		<c:when test="${not empty rateModel}">
			<h4>Today's Rate Exchange for UK, USA and HK against EUR</h4>
			<table class="table table-bordered" >
				<thead class="thead-light">
					<tr>
						<th scope="col">Currency</th>
						<th scope="col">Rate</th>
					</tr>
				</thead>
				<tr>
					<td>UK</td>
					<td>${rateModel.rates.gbp}</td>
				</tr>
				<tr>
					<td>USA</td>
					<td>${rateModel.rates.usd}</td>
				</tr>
				<tr>

					<td>HK</td>
					<td>${rateModel.rates.hkd}</td>
				</tr>
			</table>
			<button class="btn btn-lg btn-primary" id="submit"
				onclick="location.href ='/currency/generate-report'">Generate Report</button>
		</c:when>
		<c:otherwise>
			<%@ include file="error.jsp"%>
		</c:otherwise>
	</c:choose>
</body>

</html>