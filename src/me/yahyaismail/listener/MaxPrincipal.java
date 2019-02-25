package me.yahyaismail.listener;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

import me.yahyaismail.model.Loan;

/**
 * Application Lifecycle Listener implementation class MaxPrincipal
 *
 */
@WebListener
public class MaxPrincipal implements HttpSessionAttributeListener {

    /**
     * Default constructor. 
     */
    public MaxPrincipal() {
    	
    }

	/**
     * @see HttpSessionAttributeListener#attributeAdded(HttpSessionBindingEvent)
     */
    public void attributeAdded(HttpSessionBindingEvent arg0)  {
    	handleChange(arg0, arg0.getValue().toString());
    }

	/**
     * @see HttpSessionAttributeListener#attributeRemoved(HttpSessionBindingEvent)
     */
    public void attributeRemoved(HttpSessionBindingEvent arg0)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see HttpSessionAttributeListener#attributeReplaced(HttpSessionBindingEvent)
     */
    public void attributeReplaced(HttpSessionBindingEvent arg0)  {
    	Object val = arg0.getSession().getAttribute(arg0.getName());
    	
    	if (val.getClass().equals(Double.class)) {
        	handleChange(arg0, Double.toString((double) val));
        	return;
    	}
    	handleChange(arg0, (String) val);
    }
	
    private void handleChange(HttpSessionBindingEvent event, String changedValue) {
    	ServletContext context = event.getSession().getServletContext();
    	Loan loan = (Loan) context.getAttribute("mLoan"); 
    	String maxP = changedValue;
    	if (event.getName().equals("principal")) {
	    	if (context.getAttribute("maxPrincipal") != null)
	    		maxP = loan.maxPrincipal(context.getAttribute("maxPrincipal").toString(), changedValue);
	    	context.setAttribute("maxPrincipal", maxP);
    	} else if (event.getName().equals("period")) {
    		if (context.getAttribute("maxPeriod") != null)
	    		maxP = loan.maxPrincipal(context.getAttribute("maxPeriod").toString(), changedValue);
	    	context.setAttribute("maxPeriod", maxP);
    	}
    }    
}
