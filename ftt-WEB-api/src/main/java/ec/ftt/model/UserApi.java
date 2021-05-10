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

import ec.ftt.beans.User;
import ec.ftt.model.*;

/**
 * Servlet implementation class UserApi
 * 
 * CRUD - 
 * 
 */

// TODO: PROJETO: CRIAR CRUD WEB + GRÁFICO PARA MAIS UMA TABELA COM MAIS CAMPOS PARA N1 2B
// TODO: PROJETO: PROJETO INDIVIDUAL OU NO MÁXIMO EM DUPLAS (EM DUPLAS 2 TABELAS)
// TODO: PROJETO: JavaScript Valina - CRUD em uma página - User "fetch"
// TODO: PROJETO: Gerar gráfico com "Chart.js" https://www.chartjs.org/
// TODO: PROJETO: Trabalhar bem mensagens de erro da WEB API com try catch


@WebServlet("/user")
public class UserApi extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserApi() {
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

		String userId = request.getParameter("user-id");
		
	    if(userId != null) {
	    	long id = Long.valueOf(userId);
	    	
	    	UserDao userDao = new UserDao();
	    	
	        User user = userDao.getUserById(id);
	     	Gson gson = new Gson();
	    	response.getWriter().append(gson.toJson(user));
	    	
	    } else {
	    	
	    	UserDao userDao = new UserDao();
	    	
	    	List<User> users = userDao.getAllUser();
	        
	    	Gson gson = new Gson();
	    	
	    	response.getWriter().append(gson.toJson(users));
	    	/*
	    	 for (User u : users)
	    	 
	    		response.getWriter().append(u.toString());
	    	*/
	    } //if
		
	} //doGet

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		User u = new User(
				request.getParameter("user-id"),
				request.getParameter("user-name"),
				request.getParameter("user-email"),
				request.getParameter("user-color")
				);
		
		UserDao userDao = new UserDao();
		
		userDao.addUser(u);
		
		System.out.println(u);
		
		//response.getWriter().append(u.toString());
		response.sendRedirect("/ftt-WEB-api/lista.html");
    	
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		System.out.println("try111111");
		response.setContentType("application/json"); //mimeType - https://developer.mozilla.org/pt-BR/docs/Web/HTTP/Basics_of_HTTP/MIME_types/Common_types
		User u = new User(
				request.getParameter("user-id"),
				request.getParameter("user-name"),
				request.getParameter("user-email"),
				request.getParameter("user-color")
				);
		UserDao userDao = new UserDao();
		
		userDao.updateUser(u);
		
		System.out.println(u);
		
		response.getWriter().append(u.toString());

	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setStatus(418); //200 - OK - Padrão (Default)

		if (request.getParameter("userId") == null) {
			//System.out.print("bbbbbbbbbbbb");
			response.sendError(407, "Informe o ID do usuário a ser retornado!!!" );
		}
		else {
		Long userId = Long.valueOf(request.getParameter("userId"));
		//System.out.print("ccccccccccccc");
		
		
		UserDao ud = new UserDao();
		
		ud.deleteUser(userId);
		
		//response.getWriter().append(request.getParameter("userId") + " User removido");
		response.sendRedirect("ftt-WEB-api/user");
		}
	}

}