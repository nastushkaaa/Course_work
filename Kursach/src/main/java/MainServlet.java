import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.Vector;

public class MainServlet extends HttpServlet{
    Phones book = new Phones();
    Names names = new Names();
    MainPage pg = new MainPage();

    public void init(ServletConfig config){
        book.loadFromTextFile();
        names.loadFromTextFile();
    }

    public void doGet(HttpServletRequest request,HttpServletResponse response)
    throws ServletException, IOException {
    response.setContentType("text/html");
    String uri = request.getRequestURI();
    PrintWriter out = response.getWriter();
    out.println("<html>");
    out.println("<head>");
    out.println("<title>Course work</title>");
        out.println("<style>");
        out.println("body {");
        out.println("  background-color: #F0F0F0;");
        out.println("}");
        out.println("</style>");

    out.println("<link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css\" rel=\"stylesheet\"integrity=\"sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3\" crossorigin=\"anonymous\">");
    out.println("</head>");
    out.println("<body style=\"background-color: snow;\">");

    out.println("<div class=\"container-xxl\">");
    out.println("<h1>Bug tracker " + book.size() + "</h1>");
    book.loadFromTextFile();
    request.getRequestDispatcher("links.html").include(request, response);
    HttpSession sess1 = request.getSession();
    if(sess1.getAttribute("name") != null) out.println("User: " + sess1.getAttribute("name") + "\n");
    out.println(pg.getMainPage());

    out.println("</div>");
    out.println("</body>");
    out.println("</html>");
  }

  public String getMainPage(){
    StringBuilder sb = new StringBuilder();
      sb.append("<b><h3 style=\"color: black;\">Opened tickets</h3></b><br>\n");

    sb.append("<hr>\n");
    for (Map.Entry<String, Vector<String>> pair : book.book.entrySet()) {
        if(pair.getValue().get(3).equals("open")){
            sb.append("<fieldset>");
            sb.append("<div class=\"row g-3\">");
            sb.append("<div class=\"col-md-4\">");
            sb.append(pair.getKey() + " ");
            sb.append("</div>");
            sb.append("<div class=\"col-md-1\">");
            sb.append("<form method=\"post\" action=\"DeleteServlet\">\n");
            sb.append("<input type=\"hidden\" name=\"num\" value=\"" + pair.getKey() + "\">\n") ;
            sb.append("<input class=\"btn btn-primary\" type=\"submit\" value=\"delete\">\n");
            sb.append("</form>");
            sb.append("</div>");
            sb.append("<div class=\"col-md-7\">");
            sb.append("<form method=\"post\" action=\"EditServlet\">\n");
            sb.append("<input type=\"hidden\" name=\"num\" value=\"" + pair.getKey() + "\">\n") ;
            sb.append("<input class=\"btn btn-primary\" type=\"submit\" value=\"edit\">\n");
            sb.append("</form>");
            sb.append("</div>");
            sb.append("</div>");
            sb.append("</fieldset>\n");
            sb.append("<hr>\n");

        }
    }
      sb.append("<b><h3 style=\"color: black;\">Closed tickets</h3></b><br>\n");

    sb.append("<hr>\n");
    for (Map.Entry<String, Vector<String>> pair : book.book.entrySet()) {
        if(pair.getValue().get(3).equals("close")){
          sb.append("<fieldset>");
          sb.append("<div class=\"row g-3\">");
          sb.append("<div class=\"col-md-4\">");
          sb.append(pair.getKey() + " ");
          sb.append("</div>");
          sb.append("<div class=\"col-md-1\">");
          sb.append("<form method=\"post\" action=\"DeleteServlet\">\n");  
          sb.append("<input type=\"hidden\" name=\"num\" value=\"" + pair.getKey() + "\">\n") ;
          sb.append("<input class=\"btn btn-primary\" type=\"submit\" value=\"delete\">\n");
          sb.append("</form>");
          sb.append("</div>");
          sb.append("<div class=\"col-md-7\">");
          sb.append("<form method=\"post\" action=\"EditServlet\">\n");
          sb.append("<input type=\"hidden\" name=\"num\" value=\"" + pair.getKey() + "\">\n") ;
          sb.append("<input class=\"btn btn-primary\" type=\"submit\" value=\"edit\">\n");
          sb.append("</form>");
          sb.append("</div>");
          sb.append("</div>");
          sb.append("</fieldset>\n");
          sb.append("<hr>\n");

        }
    }

    return sb.toString();
  }
}

