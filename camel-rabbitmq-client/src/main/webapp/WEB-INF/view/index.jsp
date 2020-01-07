<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<title>ET-BOT</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</head>
<body>
	<div class="container">
		<h1>Apache Camel + RabbitMQ + Spring boot</h1>
		<div class="jumbotron col-md-6">
			<c:if test="${RES_MSG == null}">
				<h5>Send some message to the server!</h5>
			</c:if>
			<c:if test="${RES_MSG != null}">
				<p id="chat"><img alt="bot" src="images/bot3.png" width="50px" height="50px" /><span> ${RES_MSG}</span></p>	
			</c:if>
			<form id="msg-form" action="send" method="GET">
				<div class="row">
					<div class="col-md-10">
						<input required value="${REQ_MSG}" id="req_msg" name="req_msg" class="form-control">
					</div>
					<div class="col-md-2">
						<input type="submit" id="btnSend" onclick="send()" value="Send" class="btn btn-primary" />
					</div>
				</div>
			</form>
		 		
		</div>
	</div>
</body>
</html>