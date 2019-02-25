package me.yahyaismail.controller;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Analytics
 */
@WebServlet({"/admin", "/admin/*"})
public class Analytics extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Analytics() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public void init(ServletConfig config) throws ServletException {
    	super.init(config);
    	if (this.getServletConfig().getServletContext().getAttribute("maxPrincipal") == null)
    		this.getServletConfig().getServletContext().setAttribute("maxPrincipal", 0);
    	if (this.getServletConfig().getServletContext().getAttribute("maxPeriod") == null)
    		this.getServletConfig().getServletContext().setAttribute("maxPeriod", 0);
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getPathInfo() == null) {	
			if (request.getSession().getAttribute("extended") == null)
				request.getSession().removeAttribute("showPeriod");
			else
				request.getSession().removeAttribute("extended");
			String target = "MaxPrincipal.jsp";
			request.getRequestDispatcher(target).forward(request, response);
		} else if (request.getPathInfo().equals("/Extended")) {
			request.getSession().setAttribute("showPeriod", "true");
			request.getSession().setAttribute("extended", "true");
			String url = this.getServletContext().getContextPath() + "/admin";
			response.sendRedirect(url);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
