$(document).ready(function()
		{
	if ($("#alertSuccess").text().trim() == "")
	{
		$("#alertSuccess").hide();
	}
	$("#alertError").hide();
		});


//UPDATE==========================================
//$(document).on("click", ".btnUpdate", function(event)
//{
// $("#hidItemIDSave").val($(this).closest("tr").find('#hidItemIDUpdate').val());
// $("#itemCode").val($(this).closest("tr").find('td:eq(0)').text());
// $("#itemName").val($(this).closest("tr").find('td:eq(1)').text());
// $("#itemPrice").val($(this).closest("tr").find('td:eq(2)').text());
// $("#itemDesc").val($(this).closest("tr").find('td:eq(3)').text());
//});

$(document).on("click", ".btnUpdate", function(event)
		{
		 $("#hidMonitorIDSave").val($(this).data("bill_no"));
		 $("#moniter_no").val($(this).closest("tr").find('td:eq(0)').text());
         $("#customer_id").val($(this).closest("tr").find('td:eq(1)').text());
		 $("#unit_amount").val($(this).closest("tr").find('td:eq(2)').text());
		 $("#previous_unit").val($(this).closest("tr").find('td:eq(3)').text());
		 $("#current_unit").val($(this).closest("tr").find('td:eq(4)').text());
		 $("#monthly_amount").val($(this).closest("tr").find('td:eq(5)').text());
		});
////CLIENT-MODEL================================================================
function validateUserForm()
{
//	NAME--------------------------------
	if ($("#moniter_no").val().trim() == "")
	{
		return "Insert User Full moniter_no.";
	}
//	ADDRESS--------------------------------
	if ($("#customer_id").val().trim() == "")
	{
		return "Insert User customer_id.";
	}
	9
//	PRICE-------------------------------
//	if ($("#itemPrice").val().trim() == "")
//	{
//		return "Insert Item Price.";
//	}
//	is numerical value
//	var tmpPrice = $("#itemPrice").val().trim();
//	if (!$.isNumeric(tmpPrice))
//	{
//		return "Insert a numerical value for Item Price.";
//	}
//	convert to decimal price
//	$("#itemPrice").val(parseFloat(tmpPrice).toFixed(2));
	
//  EMAIL-------------------------------
	if ($("#unit_amount").val().trim() == "")
	{
		return "Insert unit_amount.";
	}
	
//  CONTACT NUMBER-------------------------------
	if ($("#previous_unit").val().trim() == "")
	{
		return "Insert Contact previous_unit.";
	}
	
//  USERNAME-------------------------------
	if ($("#current_unit").val().trim() == "")
	{
		return "Insert current_unit.";
	}
	
//	Password------------------------
	if ($("#monthly_amount").val().trim() == "")
	{
		return "Insert monthly_amount.";
	}
	return true;
}

$(document).on("click", "#btnSave", function(event)
		{
		// Clear alerts---------------------
		 $("#alertSuccess").text("");
		 $("#alertSuccess").hide();
		 $("#alertError").text("");
		 $("#alertError").hide();
		// Form validation-------------------
		var status = validateUserForm();
		if (status != true)
		 {
		 $("#alertError").text(status);
		 $("#alertError").show();
		 return;
		 }
		// If valid------------------------
		var type = ($("#hidMonitorIDSave").val() == "") ? "POST" : "PUT";
		 $.ajax(
		 {
		 url : "MonitorAPI",
		 type : type,
		 data : $("#formMonitor").serialize(),
		 dataType : "text",
		 complete : function(response, status)
		 {
		 onUserSaveComplete(response.responseText, status);
		 }
		 });
		});

function onMonitorSaveComplete(response, status)
{
if (status == "success")
 {
 var resultSet = JSON.parse(response);
 if (resultSet.status.trim() == "success")
 {
 $("#alertSuccess").text("Successfully saved.");
 $("#alertSuccess").show();
 $("#divMonitorsGrid").html(resultSet.data);
 } else if (resultSet.status.trim() == "error")
 {
 $("#alertError").text(resultSet.data);
 $("#alertError").show();
 }
 } else if (status == "error")
 {
 $("#alertError").text("Error while saving.");
 $("#alertError").show();
 } else
 {
 $("#alertError").text("Unknown error while saving..");
 $("#alertError").show();
 }

 $("#hidMonitorIDSave").val("");
 $("#formMonitor")[0].reset();
}


$(document).on("click", ".btnRemove", function(event)
		{
		 $.ajax(
		 {
		 url : "MonitorAPI",
		 type : "DELETE",
		 data : "bill_no=" + $(this).data("itemid"),
		 dataType : "text",
		 complete : function(response, status)
		 {
		 onUserDeleteComplete(response.responseText, status);
		 }
		 });
		});

function onMonitorDeleteComplete(response, status)
{
if (status == "success")
 {
 var resultSet = JSON.parse(response);
 if (resultSet.status.trim() == "success")
 {
 $("#alertSuccess").text("Successfully deleted.");
 $("#alertSuccess").show();
 $("#divMonitorsGrid").html(resultSet.data);
 } else if (resultSet.status.trim() == "error")
 {
 $("#alertError").text(resultSet.data);
 $("#alertError").show();
 }
 } else if (status == "error")
 {
 $("#alertError").text("Error while deleting.");
 $("#alertError").show();
 } else
 {
 $("#alertError").text("Unknown error while deleting..");
 $("#alertError").show();
 }
}


