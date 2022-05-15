package com;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UserAPI
 */
@WebServlet("/MonitorAPI")
public class MonitorAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	Monitor monitorObj = new Monitor();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MonitorAPI() {
        super();
        // TODO Auto-generated constructor stub
    }
 // Convert request parameters to a Map
    private static Map getParasMap(HttpServletRequest request)
    {
     Map<String, String> map = new HashMap<String, String>();
    try
     {
     Scanner scanner = new Scanner(request.getInputStream(), "UTF-8");
     String queryString = scanner.hasNext() ?
     scanner.useDelimiter("\\A").next() : "";
     scanner.close();
     String[] params = queryString.split("&");
     for (String param : params)
     { 
    	 String[] p = param.split("=");
    	 map.put(p[0], p[1]);
    	 }
    	 }
    	catch (Exception e)
    	 {
    	 }
    	return map;
    	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			 throws ServletException, IOException
			{
			 String output = monitorObj.insertMonitor(request.getParameter("moniter_no"),
			 request.getParameter("customer_id"),
			request.getParameter("unit_amount"),
			request.getParameter("previous_unit"),
			request.getParameter("current_unit"),
			request.getParameter("monthly_amount"));
			response.getWriter().write(output);
			}
	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			 throws ServletException, IOException
			{
			 Map paras = getParasMap(request);
			 String output = monitorObj.updateMonitor(paras.get("hidMonitorIDSave").toString(),
			 paras.get("moniter_no").toString(),
			 paras.get("customer_id").toString(),
			 paras.get("unit_amount").toString(),
			 paras.get("previous_unit").toString(),
			 paras.get("current_unit").toString(),
			 paras.get("monthly_amount").toString());
			 response.getWriter().write(output);
			}
			protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			 throws ServletException, IOException
			{
			 Map paras = getParasMap(request);
			 String output = monitorObj.deleteMonitor(paras.get("bill_no").toString());
			response.getWriter().write(output);
			}

}
