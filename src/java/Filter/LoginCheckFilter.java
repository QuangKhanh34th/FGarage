/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Filter.java to edit this template
 */
package Filter;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;
import model.Customer;
import model.Mechanic;
import model.SalesPerson;

/**
 *
 * @author ASUS
 */
public class LoginCheckFilter implements Filter {
    
    private static final boolean debug = true;
    
    // Define URL patterns for different roles
    private static final String SALES_URL_PATTERN = "/SalesDashboard/";
    private static final String MECHANIC_URL_PATTERN = "/MechanicDashboard/";
    private static final String CUSTOMER_URL_PATTERN = "/CustomerDashboard/";
    // The filter configuration object we are associated with.  If
    // this value is null, this filter instance is not currently
    // configured. 
    private FilterConfig filterConfig = null;
    
    public LoginCheckFilter() {
    }    
    
    private void doBeforeProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
        if (debug) {
            log("LoginCheckFilter:DoBeforeProcessing");
        }

        // Write code here to process the request and/or response before
        // the rest of the filter chain is invoked.
        // For example, a logging filter might log items on the request object,
        // such as the parameters.
        /*
	for (Enumeration en = request.getParameterNames(); en.hasMoreElements(); ) {
	    String name = (String)en.nextElement();
	    String values[] = request.getParameterValues(name);
	    int n = values.length;
	    StringBuffer buf = new StringBuffer();
	    buf.append(name);
	    buf.append("=");
	    for(int i=0; i < n; i++) {
	        buf.append(values[i]);
	        if (i < n-1)
	            buf.append(",");
	    }
	    log(buf.toString());
	}
         */
    }    
    
    private void doAfterProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
        if (debug) {
            log("LoginCheckFilter:DoAfterProcessing");
        }

        // Write code here to process the request and/or response after
        // the rest of the filter chain is invoked.
        // For example, a logging filter might log the attributes on the
        // request object after the request has been processed. 
        /*
	for (Enumeration en = request.getAttributeNames(); en.hasMoreElements(); ) {
	    String name = (String)en.nextElement();
	    Object value = request.getAttribute(name);
	    log("attribute: " + name + "=" + value.toString());

	}
         */
        // For example, a filter might append something to the response.
        /*
	PrintWriter respOut = new PrintWriter(response.getWriter());
	respOut.println("<P><B>This has been appended by an intrusive filter.</B>");
         */
    }

    /**
     *
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     * @param chain The filter chain we are processing
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet error occurs
     */
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        if (debug) {
            log("LoginCheckFilter:doFilter()");
        }
        
        doBeforeProcessing(request, response);
        
        Throwable problem = null;
        try {
       HttpServletRequest httpRequest = (HttpServletRequest) request;
            HttpServletResponse httpResponse = (HttpServletResponse) response;
            //only check for login and not creating new session for resource saving
            HttpSession session = httpRequest.getSession(false);
            
            //debug console to track web access flow
            String requestURI = httpRequest.getRequestURI();
            System.out.println("[LoginCheckFilter.java] heading to: " +requestURI);
            
            
            //If the resource the user want to access is:
            /*
                MainServlet (with no parameter entered)
                Login-related Servlets (loginCust, loginSales,...)
                associated style, javascript files
            */
            //then continue as normal
            if (requestURI.endsWith("/MainServlet") || 
                    requestURI.contains("/Login") ||
                    requestURI.contains("SalesDashboard/css/") ||
                    requestURI.contains("SalesDashboard/js/") ||
                    requestURI.endsWith("/loginCustServlet") || 
                    requestURI.endsWith("/loginMechanicServlet") ||
                    requestURI.endsWith("/loginSalesServlet")) {
                chain.doFilter(request, response);
                return;
            }
            
            //check for user object in session 
            boolean user = false; // Initialize to false
            if (session != null) {
                SalesPerson sales = (SalesPerson) session.getAttribute("sales");
                Mechanic mechanic = (Mechanic) session.getAttribute("mechanic");
                Customer customer = (Customer) session.getAttribute("customer");
                user = sales != null || mechanic != null || customer != null;
            }
            //session not found or user object is not found then redirect to login page
            if (session == null || !user) {
                System.out.println("[LoginCheckFilter.java] login state is false (logged out),"
                        + " redirecting the user to: " + httpRequest.getContextPath() + "/MainServlet");
                httpResponse.sendRedirect(httpRequest.getContextPath() + "/MainServlet");
            }
            
            // Role-based access control
            /*
                If the user access pages directly through url, check for url pattern and associated user object
            */
            if (requestURI.contains(SALES_URL_PATTERN)) {
                if (session.getAttribute("sales") == null) {
                    System.out.println("[LoginCheckFilter.java] Unauthorized access to Sales's functions,"
                            + " redirecting the user to: " + httpRequest.getContextPath() + "/MainServlet");
                    session.setAttribute("error", "Unauthorized access found, please log in to proceed");
                    httpResponse.sendRedirect(httpRequest.getContextPath() + "/MainServlet");
                    return;
                }
            } else if (requestURI.contains(MECHANIC_URL_PATTERN)) {
                if (session.getAttribute("mechanic") == null) {
                    System.out.println("[LoginCheckFilter.java] Unauthorized");
                    System.out.println("[LoginCheckFilter.java] Unauthorized access to Mechanic's functions,"
                            + " redirecting the user to: " + httpRequest.getContextPath() + "/MainServlet");
                    session.setAttribute("error", "Unauthorized access found, please log in to proceed");
                    httpResponse.sendRedirect(httpRequest.getContextPath() + "/MainServlet");
                    return;
                }
            } else if (requestURI.contains(CUSTOMER_URL_PATTERN)) {
                if (session.getAttribute("customer") == null) {
                    System.out.println("[LoginCheckFilter.java] Unauthorized access to Customer's functions,"
                            + " redirecting the user to: " + httpRequest.getContextPath() + "/MainServlet");
                    session.setAttribute("error", "Unauthorized access found, please log in to proceed");
                    httpResponse.sendRedirect(httpRequest.getContextPath() + "/MainServlet");
                    return;
                }
            }
            
            chain.doFilter(request, response);
            
            
        } catch (Throwable t) {
            // If an exception is thrown somewhere down the filter chain,
            // we still want to execute our after processing, and then
            // rethrow the problem after that.
            problem = t;
            t.printStackTrace();
        }
        
        doAfterProcessing(request, response);

        // If there was a problem, we want to rethrow it if it is
        // a known type, otherwise log it.
        if (problem != null) {
            if (problem instanceof ServletException) {
                throw (ServletException) problem;
            }
            if (problem instanceof IOException) {
                throw (IOException) problem;
            }
            sendProcessingError(problem, response);
        }
    }

    /**
     * Return the filter configuration object for this filter.
     */
    public FilterConfig getFilterConfig() {
        return (this.filterConfig);
    }

    /**
     * Set the filter configuration object for this filter.
     *
     * @param filterConfig The filter configuration object
     */
    public void setFilterConfig(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }

    /**
     * Destroy method for this filter
     */
    public void destroy() {        
    }

    /**
     * Init method for this filter
     */
    public void init(FilterConfig filterConfig) {        
        this.filterConfig = filterConfig;
        if (filterConfig != null) {
            if (debug) {                
                log("LoginCheckFilter:Initializing filter");
            }
        }
    }

    /**
     * Return a String representation of this object.
     */
    @Override
    public String toString() {
        if (filterConfig == null) {
            return ("LoginCheckFilter()");
        }
        StringBuffer sb = new StringBuffer("LoginCheckFilter(");
        sb.append(filterConfig);
        sb.append(")");
        return (sb.toString());
    }
    
    private void sendProcessingError(Throwable t, ServletResponse response) {
        String stackTrace = getStackTrace(t);        
        
        if (stackTrace != null && !stackTrace.equals("")) {
            try {
                response.setContentType("text/html");
                PrintStream ps = new PrintStream(response.getOutputStream());
                PrintWriter pw = new PrintWriter(ps);                
                pw.print("<html>\n<head>\n<title>Error</title>\n</head>\n<body>\n"); //NOI18N

                // PENDING! Localize this for next official release
                pw.print("<h1>The resource did not process correctly</h1>\n<pre>\n");                
                pw.print(stackTrace);                
                pw.print("</pre></body>\n</html>"); //NOI18N
                pw.close();
                ps.close();
                response.getOutputStream().close();
            } catch (Exception ex) {
            }
        } else {
            try {
                PrintStream ps = new PrintStream(response.getOutputStream());
                t.printStackTrace(ps);
                ps.close();
                response.getOutputStream().close();
            } catch (Exception ex) {
            }
        }
    }
    
    public static String getStackTrace(Throwable t) {
        String stackTrace = null;
        try {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            t.printStackTrace(pw);
            pw.close();
            sw.close();
            stackTrace = sw.getBuffer().toString();
        } catch (Exception ex) {
        }
        return stackTrace;
    }
    
    public void log(String msg) {
        filterConfig.getServletContext().log(msg);        
    }
    
}
