package com;
import java.sql.*;
public class Monitor
{
	private Connection connect()
	{
		Connection con = null;
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			con =DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/electrogrid", "root", "123456789");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return con;
	}
	
	public String readMonitors()
	{
	String output = "";
	try
	{
	Connection con = connect();
	if (con == null)
	{
	return "Error while connecting to the database for reading.";
	}
	
	// Prepare the html table to be displayed
	output = "<table border='1' class=\"table table-bordered\"><tr><th>moniter_no</th>"
			 +"<th>customer_id</th><th>unit_amount</th>"
			 + "<th>previous_unit</th>"
			 + "<th>current_unit</th>"
			 + "<th>monthly_amount</th>";
	String query = "select * from moniter_mgnt";
	Statement stmt = con.createStatement();
	ResultSet rs = stmt.executeQuery(query);
	
	// iterate through the rows in the result set
	while (rs.next())
	{
		 String bill_no = Integer.toString(rs.getInt("bill_no"));
		 String moniter_no = rs.getString("moniter_no");
		 String customer_id = rs.getString("customer_id");
		 String unit_amount = rs.getString("unit_amount");
		 String previous_unit = rs.getString("previous_unit");
		 String current_unit = rs.getString("current_unit");
		 String monthly_amount = rs.getString("monthly_amount");
	
	// Add into the html table
		output += "<tr><td class='table-warning'><input id='hidMonitorIDUpdate' name='hidMonitorIDUpdate' type='hidden' value='" + bill_no
		+ "'>" + moniter_no + "</td>";
		output += "<td class='table-warning'>" + customer_id + "</td>";
		output += "<td class='table-warning'>" + unit_amount + "</td>";
		output += "<td class='table-warning'>" + previous_unit + "</td>";
		output += "<td class='table-warning'>" + current_unit + "</td>";
		output += "<td class='table-warning'>" + monthly_amount + "</td>";
	// buttons
	output += "<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary' data-itemid='"+ bill_no + "'>" + "</td>"
	+ "<td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-itemid='"+ bill_no + "'>" + "</td></tr>";
	}
	con.close();
	// Complete the html table
	output += "</table>";
	}
	catch (Exception e)
	{
	output = "Error while reading the items.";
	System.err.println(e.getMessage());
	}
	return output;
	}
	

	public String insertMonitor(String moniter_no, String customer_id, String unit_amount, String previous_unit, String current_unit, String monthly_amount)
	{
		String output = "";
		try
		{
			Connection con = connect();
			if (con == null)
			{
				return "Error while connecting to the database for inserting.";
			}
			// create a prepared statement
			String query = " insert into moniter_mgnt(`bill_no`,`moniter_no`,`customer_id`,`unit_amount`,`previous_unit`,`current_unit`,`monthly_amount`)" 
			+" values (?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, moniter_no);
			preparedStmt.setString(3, customer_id);
			preparedStmt.setString(4, unit_amount);
			preparedStmt.setString(5, previous_unit);
			preparedStmt.setString(6, current_unit);
			preparedStmt.setString(7, monthly_amount);
			// execute the statement
			preparedStmt.execute();
			con.close();
			String newMonitors = readMonitors();
			output = "{\"status\":\"success\", \"data\": \"" +
					newMonitors + "\"}";
		}
		catch (Exception e)
		{
			output = "{\"status\":\"error\", \"data\": \"Error while inserting the monitor.\"}";
			System.err.println(e.getMessage());
		}
		return output;
	}
	
	public String updateMonitor(String bill_no, String moniter_no, String customer_id, String unit_amount, String previous_unit, String current_unit, String monthly_amount)
	{
		String output = "";
		try
		{
			Connection con = connect();
			if (con == null)
			{
				return "Error while connecting to the database for updating.";
			}
			// create a prepared statement
			String query = "UPDATE moniter_mgnt SET moniter_no=?,customer_id=?,unit_amount=?,previous_unit=?,current_unit=?,monthly_amount=?WHERE bill_no=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setString(1, moniter_no);
			preparedStmt.setString(2, customer_id);
			preparedStmt.setString(3, unit_amount);
			preparedStmt.setString(4, previous_unit);
			preparedStmt.setString(5, current_unit);
			preparedStmt.setString(6, monthly_amount);
			preparedStmt.setInt(7, Integer.parseInt(bill_no));

			//execute the statement
			preparedStmt.execute();
			con.close();
			String newMonitors = readMonitors();
			output = "{\"status\":\"success\", \"data\": \"" +
					newMonitors + "\"}";
		}
		catch (Exception e)
		{
			output = "{\"status\":\"error\", \"data\":\"Error while updating the monitor.\"}";
			System.err.println(e.getMessage());
		}
		return output;
	}
	
	public String deleteMonitor(String bill_no)
	{
		String output = "";
		try
		{
			Connection con = connect();
			if (con == null)
			{
				return "Error while connecting to the database for deleting.";
			}
			// create a prepared statement
			String query = "delete from moniter_mgnt where bill_no=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(bill_no));
			// execute the statement
			preparedStmt.execute();
			con.close();
			String newMonitors = readMonitors();
			output = "{\"status\":\"success\", \"data\": \"" +
					newMonitors + "\"}";
		}
		catch (Exception e)
		{
			output = "{\"status\":\"error\", \"data\":\"Error while deleting the monitor.\"}";
			System.err.println(e.getMessage());
		}
		return output;
	}
}
