package me.yahyaismail.controller;

import java.io.IOException;
import java.io.Writer;
import java.text.NumberFormat;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import me.yahyaismail.model.Loan;

/**
 * Servlet implementation class Start
 */
@WebServlet({"/Startup/*", "/Start"})
public class Start extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private String fixedInterest;
	private String gracePeriod;
	private String interest;
	private String period;
	private String principal;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Start() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public void init(ServletConfig config) throws ServletException {
    	super.init(config);
		this.getServletContext().setAttribute("mLoan", new Loan());
		fixedInterest = this.getServletContext().getInitParameter("fixedInterest");
		gracePeriod = this.getServletContext().getInitParameter("gracePeriod");
		interest = this.getServletContext().getInitParameter("interest");
		principal = this.getServletContext().getInitParameter("principal");
		period = this.getServletContext().getInitParameter("period");
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if (request.getParameter("calculate") != null) {
			Loan loan = (Loan) this.getServletContext().getAttribute("mLoan");
			
			getRequestInputs(request); 
			boolean rememberNum = request.getParameter("rememberNum") != null;
			boolean isGrace = request.getParameter("grace") != null;
			
			double totalInterest = 0;

			if (request.getParameter("studentNum") != null) {
				request.getSession().setAttribute("recomputeWithStudentNum", "true");
				try {
					loan.validateStudentNum(request.getParameter("studentNum"));
				} catch (Exception e) {
					request.setAttribute("invalidEntry", "true");
					String target ="UI.jsp";
					request.getRequestDispatcher(target).forward(request, response);
					e.printStackTrace();
					return;
				}
			}
			
			if (rememberNum) {
				request.getSession().setAttribute("studentNum", request.getParameter("studentNum"));
				request.getSession().setAttribute("rememberNum", "true");
			}
			else  {
				request.getSession().removeAttribute("studentNum");
				request.getSession().removeAttribute("rememberNum");
			}
			
			try {
				totalInterest = loan.getTotalInterest(interest, fixedInterest);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			request.getSession().setAttribute("principal", principal);//persisted in the session
			request.getSession().setAttribute("interest", totalInterest);//persisted in the session
			request.getSession().setAttribute("period", period);//persisted in the session
			request.getSession().setAttribute("grace", (isGrace ? "true" : "false"));

			double graceInterest = 0;
			double payments = 0;

			try {
				payments = loan.computePayment(principal, period, interest, fixedInterest);
				if (isGrace)
					graceInterest = loan.computeGrace(principal, gracePeriod, interest, fixedInterest);
			} catch (Exception e) {
				request.setAttribute("invalidEntry", "true");
				String target ="UI.jsp";
				request.getRequestDispatcher(target).forward(request, response);
				e.printStackTrace();
				return;
			}
				
			request.getSession().setAttribute("gracePeriod", gracePeriod);//persisted in the session
			request.getSession().setAttribute("graceInterest", graceInterest);//persisted in the session
			request.getSession().setAttribute("payments", payments);//persisted in the session
			
			String target = "/Results.jsp";
					
			request.getRequestDispatcher(target).forward(request, response);//dispatch the control to a jsp for display..";
		} else if (request.getParameter("ajaxCalc") != null) {
			Writer wr = response.getWriter();
			Loan loan = (Loan) this.getServletContext().getAttribute("mLoan");
			
			getRequestInputs(request); 
			boolean isGrace = request.getParameter("grace") != null;
			double graceInterest = 0;
			double payments = 0;
			double totalInterest = 0;

			try {
				totalInterest = loan.getTotalInterest(interest, fixedInterest);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			request.getSession().setAttribute("principal", principal);//persisted in the session
			request.getSession().setAttribute("interest", totalInterest);//persisted in the session
			request.getSession().setAttribute("period", period);//persisted in the session
			request.getSession().setAttribute("grace", (isGrace ? "true" : "false"));
						
			try {
				payments = loan.computePayment(principal, period, interest, fixedInterest);
				if (isGrace)
					graceInterest = loan.computeGrace(principal, gracePeriod, interest, fixedInterest);
			} catch (Exception e) {
				request.setAttribute("invalidEntry", "true");
				String target ="UI.jsp";
				request.getRequestDispatcher(target).forward(request, response);
				e.printStackTrace();
				return;
			}

			NumberFormat formatter = NumberFormat.getCurrencyInstance();
			
			wr.write("Monthly Payment: " + formatter.format(payments) + " <br/>");
			if (isGrace)
				wr.write("Grace Period Interest: " + formatter.format(graceInterest));
			
			wr.flush();
		} else {
			if (request.getPathInfo() != null)
				response.sendRedirect(this.getServletContext().getContextPath() + "/Start");
			else {
				if (request.getSession().getAttribute("recomputeWithStudentNum") == null) {
					String type = request.getParameter("type");
					if (type == null) {
						type = "";
						request.getSession().setAttribute("advanced", "false");
					} else if (type.equals("Advanced")) {
						request.getSession().setAttribute("advanced", "true");
					}
				} else {
					request.getSession().removeAttribute("recomputeWithStudentNum");
				}
				String target ="UI.jsp";
				request.getRequestDispatcher(target).forward(request, response);
			}
		}
		
	}

	private void getRequestInputs(HttpServletRequest request) {
		if (request.getParameter("interest") != null && !request.getParameter("interest").equals(""))
			interest = request.getParameter("interest");
		if (request.getParameter("principal") != null && !request.getParameter("principal").equals(""))
			principal = request.getParameter("principal");
		if (request.getParameter("period") != null && !request.getParameter("period").equals(""))
			period = request.getParameter("period");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
