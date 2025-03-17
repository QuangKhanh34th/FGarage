package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class updateServiceTicket_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_forEach_varStatus_var_items;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_if_test;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_fmt_formatNumber_var_value_pattern_nobody;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _jspx_tagPool_c_forEach_varStatus_var_items = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_c_if_test = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_fmt_formatNumber_var_value_pattern_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
  }

  public void _jspDestroy() {
    _jspx_tagPool_c_forEach_varStatus_var_items.release();
    _jspx_tagPool_c_if_test.release();
    _jspx_tagPool_fmt_formatNumber_var_value_pattern_nobody.release();
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write(" \n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html lang=\"en\">\n");
      out.write("<head>\n");
      out.write("    <meta charset=\"UTF-8\">\n");
      out.write("    <title>Update Service Ticket</title>\n");
      out.write("    <style>\n");
      out.write("        body {\n");
      out.write("            font-family: Arial, sans-serif;\n");
      out.write("            background-color: #f8f9fa;\n");
      out.write("            display: flex;\n");
      out.write("            justify-content: center;\n");
      out.write("            align-items: center;\n");
      out.write("            height: 100vh;\n");
      out.write("            margin: 0;\n");
      out.write("        }\n");
      out.write("        .container {\n");
      out.write("            background: white;\n");
      out.write("            padding: 20px;\n");
      out.write("            border-radius: 8px;\n");
      out.write("            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);\n");
      out.write("            text-align: center;\n");
      out.write("            width: 700px;\n");
      out.write("        }\n");
      out.write("        h2 {\n");
      out.write("            color: #343a40;\n");
      out.write("        }\n");
      out.write("        table {\n");
      out.write("            width: 100%;\n");
      out.write("            border-collapse: collapse;\n");
      out.write("            margin-top: 10px;\n");
      out.write("        }\n");
      out.write("        th, td {\n");
      out.write("            border: 1px solid #ddd;\n");
      out.write("            padding: 8px;\n");
      out.write("            text-align: left;\n");
      out.write("        }\n");
      out.write("        th {\n");
      out.write("            background-color: #007bff;\n");
      out.write("            color: white;\n");
      out.write("        }\n");
      out.write("        tr:nth-child(even) {\n");
      out.write("            background-color: #f2f2f2;\n");
      out.write("        }\n");
      out.write("        input, button {\n");
      out.write("            padding: 8px;\n");
      out.write("            margin: 5px;\n");
      out.write("            border-radius: 5px;\n");
      out.write("            border: 1px solid #ddd;\n");
      out.write("        }\n");
      out.write("        button {\n");
      out.write("            background-color: #007bff;\n");
      out.write("            color: white;\n");
      out.write("            border: none;\n");
      out.write("            cursor: pointer;\n");
      out.write("        }\n");
      out.write("        button:hover {\n");
      out.write("            background-color: #0056b3;\n");
      out.write("        }\n");
      out.write("    </style>\n");
      out.write("</head>\n");
      out.write("<body>\n");
      out.write("    <div class=\"container\">\n");
      out.write("        <h2>Update Service Ticket</h2>\n");
      out.write("        ");
      if (_jspx_meth_c_if_0(_jspx_page_context))
        return;
      out.write("\n");
      out.write("        ");
      if (_jspx_meth_c_if_1(_jspx_page_context))
        return;
      out.write("\n");
      out.write("\n");
      out.write("        <br>\n");
      out.write("        <a href=\"MainController?action=MechanicDashboard\"><button type=\"button\">Back to Dashboard</button></a>\n");
      out.write("        <a href=\"MainController?action=ViewAllTickets\"><button type=\"button\">View Service Tickets</button></a>\n");
      out.write("    </div>\n");
      out.write("</body>\n");
      out.write("</html>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }

  private boolean _jspx_meth_c_if_0(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:if
    org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_if_0 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _jspx_tagPool_c_if_test.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
    _jspx_th_c_if_0.setPageContext(_jspx_page_context);
    _jspx_th_c_if_0.setParent(null);
    _jspx_th_c_if_0.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${not empty MESSAGE}", java.lang.Boolean.class, (PageContext)_jspx_page_context, null)).booleanValue());
    int _jspx_eval_c_if_0 = _jspx_th_c_if_0.doStartTag();
    if (_jspx_eval_c_if_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\n");
        out.write("            <div style=\"color: ");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${MESSAGE eq 'Update Successful!' ? 'green' : 'red'}", java.lang.String.class, (PageContext)_jspx_page_context, null));
        out.write("; font-weight: bold;\">\n");
        out.write("                ");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${MESSAGE}", java.lang.String.class, (PageContext)_jspx_page_context, null));
        out.write("\n");
        out.write("            </div>\n");
        out.write("        ");
        int evalDoAfterBody = _jspx_th_c_if_0.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_if_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_0);
      return true;
    }
    _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_0);
    return false;
  }

  private boolean _jspx_meth_c_if_1(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:if
    org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_if_1 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _jspx_tagPool_c_if_test.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
    _jspx_th_c_if_1.setPageContext(_jspx_page_context);
    _jspx_th_c_if_1.setParent(null);
    _jspx_th_c_if_1.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${not empty SERVICE_TICKETS}", java.lang.Boolean.class, (PageContext)_jspx_page_context, null)).booleanValue());
    int _jspx_eval_c_if_1 = _jspx_th_c_if_1.doStartTag();
    if (_jspx_eval_c_if_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\n");
        out.write("            <form action=\"MainController?action=UpdateServiceTicket\" method=\"POST\">\n");
        out.write("                <table>\n");
        out.write("                    <tr>\n");
        out.write("                        <th>#</th>\n");
        out.write("                        <th>Service Ticket ID</th>\n");
        out.write("                        <th>Service ID</th>\n");
        out.write("                        <th>Hours</th>\n");
        out.write("                        <th>Comment</th>\n");
        out.write("                        <th>Rate</th>\n");
        out.write("                        <th>Action</th>\n");
        out.write("                    </tr>\n");
        out.write("                    ");
        if (_jspx_meth_c_forEach_0((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_if_1, _jspx_page_context))
          return true;
        out.write("\n");
        out.write("                </table>\n");
        out.write("            </form>\n");
        out.write("        ");
        int evalDoAfterBody = _jspx_th_c_if_1.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_if_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_1);
      return true;
    }
    _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_1);
    return false;
  }

  private boolean _jspx_meth_c_forEach_0(javax.servlet.jsp.tagext.JspTag _jspx_th_c_if_1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:forEach
    org.apache.taglibs.standard.tag.rt.core.ForEachTag _jspx_th_c_forEach_0 = (org.apache.taglibs.standard.tag.rt.core.ForEachTag) _jspx_tagPool_c_forEach_varStatus_var_items.get(org.apache.taglibs.standard.tag.rt.core.ForEachTag.class);
    _jspx_th_c_forEach_0.setPageContext(_jspx_page_context);
    _jspx_th_c_forEach_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_1);
    _jspx_th_c_forEach_0.setVar("ticket");
    _jspx_th_c_forEach_0.setItems((java.lang.Object) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${SERVICE_TICKETS}", java.lang.Object.class, (PageContext)_jspx_page_context, null));
    _jspx_th_c_forEach_0.setVarStatus("status");
    int[] _jspx_push_body_count_c_forEach_0 = new int[] { 0 };
    try {
      int _jspx_eval_c_forEach_0 = _jspx_th_c_forEach_0.doStartTag();
      if (_jspx_eval_c_forEach_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        do {
          out.write("\n");
          out.write("                        <tr>\n");
          out.write("                            <td>");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${status.count}", java.lang.String.class, (PageContext)_jspx_page_context, null));
          out.write("</td>\n");
          out.write("                            <td>");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${ticket.serviceTicketID}", java.lang.String.class, (PageContext)_jspx_page_context, null));
          out.write("</td>\n");
          out.write("                            <td>");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${ticket.serviceID}", java.lang.String.class, (PageContext)_jspx_page_context, null));
          out.write("</td>\n");
          out.write("                            <td>\n");
          out.write("                                <input type=\"number\" name=\"hours_");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${ticket.serviceTicketID}", java.lang.String.class, (PageContext)_jspx_page_context, null));
          out.write('_');
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${ticket.serviceID}", java.lang.String.class, (PageContext)_jspx_page_context, null));
          out.write("\" \n");
          out.write("                                       value=\"");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${ticket.hours}", java.lang.String.class, (PageContext)_jspx_page_context, null));
          out.write("\" required>\n");
          out.write("                            </td>\n");
          out.write("                            <td>\n");
          out.write("                                <input type=\"text\" name=\"comment_");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${ticket.serviceTicketID}", java.lang.String.class, (PageContext)_jspx_page_context, null));
          out.write('_');
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${ticket.serviceID}", java.lang.String.class, (PageContext)_jspx_page_context, null));
          out.write("\" \n");
          out.write("                                       value=\"");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${ticket.comment}", java.lang.String.class, (PageContext)_jspx_page_context, null));
          out.write("\">\n");
          out.write("                            </td>\n");
          out.write("                            ");
          if (_jspx_meth_fmt_formatNumber_0((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_forEach_0, _jspx_page_context, _jspx_push_body_count_c_forEach_0))
            return true;
          out.write("\n");
          out.write("                            <td>\n");
          out.write("                                <input type=\"number\" step=\"0.01\" name=\"rate_");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${ticket.serviceTicketID}", java.lang.String.class, (PageContext)_jspx_page_context, null));
          out.write('_');
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${ticket.serviceID}", java.lang.String.class, (PageContext)_jspx_page_context, null));
          out.write("\" \n");
          out.write("                                       value=\"");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${formattedRate}", java.lang.String.class, (PageContext)_jspx_page_context, null));
          out.write("\" required>\n");
          out.write("                            </td>\n");
          out.write("                            <td>\n");
          out.write("                                <button type=\"submit\" name=\"update\" \n");
          out.write("                                        value=\"");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${ticket.serviceTicketID}", java.lang.String.class, (PageContext)_jspx_page_context, null));
          out.write('_');
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${ticket.serviceID}", java.lang.String.class, (PageContext)_jspx_page_context, null));
          out.write("\">Update</button>\n");
          out.write("                            </td>\n");
          out.write("                        </tr>\n");
          out.write("                    ");
          int evalDoAfterBody = _jspx_th_c_forEach_0.doAfterBody();
          if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
            break;
        } while (true);
      }
      if (_jspx_th_c_forEach_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        return true;
      }
    } catch (Throwable _jspx_exception) {
      while (_jspx_push_body_count_c_forEach_0[0]-- > 0)
        out = _jspx_page_context.popBody();
      _jspx_th_c_forEach_0.doCatch(_jspx_exception);
    } finally {
      _jspx_th_c_forEach_0.doFinally();
      _jspx_tagPool_c_forEach_varStatus_var_items.reuse(_jspx_th_c_forEach_0);
    }
    return false;
  }

  private boolean _jspx_meth_fmt_formatNumber_0(javax.servlet.jsp.tagext.JspTag _jspx_th_c_forEach_0, PageContext _jspx_page_context, int[] _jspx_push_body_count_c_forEach_0)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  fmt:formatNumber
    org.apache.taglibs.standard.tag.rt.fmt.FormatNumberTag _jspx_th_fmt_formatNumber_0 = (org.apache.taglibs.standard.tag.rt.fmt.FormatNumberTag) _jspx_tagPool_fmt_formatNumber_var_value_pattern_nobody.get(org.apache.taglibs.standard.tag.rt.fmt.FormatNumberTag.class);
    _jspx_th_fmt_formatNumber_0.setPageContext(_jspx_page_context);
    _jspx_th_fmt_formatNumber_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_forEach_0);
    _jspx_th_fmt_formatNumber_0.setVar("formattedRate");
    _jspx_th_fmt_formatNumber_0.setValue((java.lang.Object) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${ticket.rate}", java.lang.Object.class, (PageContext)_jspx_page_context, null));
    _jspx_th_fmt_formatNumber_0.setPattern("#.00");
    int _jspx_eval_fmt_formatNumber_0 = _jspx_th_fmt_formatNumber_0.doStartTag();
    if (_jspx_th_fmt_formatNumber_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_fmt_formatNumber_var_value_pattern_nobody.reuse(_jspx_th_fmt_formatNumber_0);
      return true;
    }
    _jspx_tagPool_fmt_formatNumber_var_value_pattern_nobody.reuse(_jspx_th_fmt_formatNumber_0);
    return false;
  }
}
