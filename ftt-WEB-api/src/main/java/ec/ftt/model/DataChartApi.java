package ec.ftt.model;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import ec.ftt.beans.Chart;
import ec.ftt.beans.User;
import ec.ftt.model.*;

/**
 * Servlet implementation class chartApi
 * 
 * CRUD - 
 * 
 */

// TODO: PROJETO: CRIAR CRUD WEB + GRÁFICO PARA MAIS UMA TABELA COM MAIS CAMPOS PARA N1 2B
// TODO: PROJETO: PROJETO INDIVIDUAL OU NO MÁXIMO EM DUPLAS (EM DUPLAS 2 TABELAS)
// TODO: PROJETO: JavaScript Valina - CRUD em uma página - chart "fetch"
// TODO: PROJETO: Gerar gráfico com "Chart.js" https://www.chartjs.org/
// TODO: PROJETO: Trabalhar bem mensagens de erro da WEB API com try catch


@WebServlet("/chart")
public class DataChartApi extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DataChartApi() {
        super();
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {

	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setStatus(418); //200 - OK - Padrão (Default)

		String chartId = request.getParameter("chart-id");
		
	    if(chartId != null) {
	    	long id = Long.valueOf(chartId);
	    	
	    	DataChartDao chartDao = new DataChartDao();
	    	
	        Chart chart = chartDao.getchartById(id);
	     	Gson gson = new Gson();
	    	response.getWriter().append(gson.toJson(chart));
	    	
	    } else {
	    	
	    	DataChartDao chartDao = new DataChartDao();
	    	
	    	List<Chart> charts = chartDao.getAllchart();
	        
	    	Gson gson = new Gson();
	    	
	    	response.getWriter().append(gson.toJson(charts));
	    	/*
	    	 for (chart u : charts)
	    	 
	    		response.getWriter().append(u.toString());
	    	*/
	    } //if
		
	} //doGet

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Chart u = new Chart(
				request.getParameter("chart-id"),
				request.getParameter("chart-happy"),
				request.getParameter("chart-sad"),
				request.getParameter("chart-conf")
				);
		
		DataChartDao charDao = new DataChartDao();
		
		charDao.addchart(u);
		
		System.out.println(u);
		
		//response.getWriter().append(u.toString());
		response.sendRedirect("/ftt-WEB-api/grafico.html");
    	
    	
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("application/json"); //mimeType - https://developer.mozilla.org/pt-BR/docs/Web/HTTP/Basics_of_HTTP/MIME_types/Common_types
		Chart u = new Chart(
				request.getParameter("chart-id"),
				request.getParameter("chart-happy"),
				request.getParameter("chart-sad"),
				request.getParameter("chart-conf")
				);
		DataChartDao chartDao = new DataChartDao();
		
		chartDao.updatechart(u);
		
		System.out.println(u);
		
		response.getWriter().append(u.toString());


	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setStatus(418); //200 - OK - Padrão (Default)

		if (request.getParameter("chartId") == null) {
			//System.out.print("bbbbbbbbbbbb");
			response.sendError(407, "Informe o ID do usuário a ser retornado!!!" );
		}
		else {
		Long chartId = Long.valueOf(request.getParameter("chartId"));
		//System.out.print("ccccccccccccc");
		
		
		DataChartDao ud = new DataChartDao();
		
		ud.deletechart(chartId);
		
		//response.getWriter().append(request.getParameter("chartId") + " chart removido");
		response.sendRedirect("ftt-WEB-api/lista.html");
		}
	}

}