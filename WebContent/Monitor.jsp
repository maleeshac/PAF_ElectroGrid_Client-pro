<%@page import="com.Monitor"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>

 
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Monitor Management</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.6.0.min.js"></script>
<script src="Components/monitor.js"></script>

</head>
<body>

<div class="container"><div class="row"><div class="col-6">
<h1>Monitor Management</h1>
<form id="formMonitor" name="formMonitor">
 Monitor NO:
 <input id="moniter_no" name="moniter_no" type="text"
 class="form-control form-control-sm">
 <br> Customer ID:
 <input id="customer_id" name="customer_id" type="text"
 class="form-control form-control-sm">
 <br> Unit Amount:
 <input id="unit_amount" name="unit_amount" type="text"
 class="form-control form-control-sm">
 <br> Previous Unit:
 <input id="previous_unit" name="previous_unit" type="text"
 class="form-control form-control-sm">
 <br> current Unit:
 <input id="current_unit" name="current_unit" type="text"
 class="form-control form-control-sm">
 <br> Monthly Amount:
 <input id="monthly_amount" name="monthly_amount" type="text"
 class="form-control form-control-sm">
 <br>
 <input id="btnSave" name="btnSave" type="button" value="Submit"
 class="btn btn-primary">
 <input type="hidden" id="hidMonitorIDSave"
 name="hidMonitorIDSave" value="">
 

</form>
<div id="alertSuccess" class="alert alert-success"></div>
<div id="alertError" class="alert alert-danger"></div>
<br>
<div id="divMonitorsGrid">
 <%
 	Monitor monitorObj = new Monitor();
  out.print(monitorObj.readMonitors());
 %>
 
</div>
</div> </div> </div>
<br><br>


</body>
</html>