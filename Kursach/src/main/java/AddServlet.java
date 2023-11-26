import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

public class AddServlet extends HttpServlet{
    Phones book = new Phones();
    MainPage pg = new MainPage();
    
    public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
        book.loadFromTextFile();
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Course work</title>");
        out.println("<link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css\" rel=\"stylesheet\"integrity=\"sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3\" crossorigin=\"anonymous\">");
        out.println("</head>");
        out.println("<body style=\"background-color: snow;\">");
//        out.println("<body>");
        out.println("<div class=\"container-xxl\">");
        out.println("<h1>Bug tracker" + "</h1>");
        HttpSession sess1 = request.getSession();
        if(sess1.getAttribute("name") != null) out.println("User: " + sess1.getAttribute("name") + "\n");
        request.getRequestDispatcher("links.html").include(request, response);
        if( (request.getParameter("name") == "")){
            out.println("Enter all parameters!!!<br>\n");
            out.println(pg.getMainPage());
        }
        else{
            book.add(request.getParameter("name"), request.getParameter("type"), request.getParameter("desc"), request.getParameter("person"), "open");
            book.saveToTextFile();
            System.out.println("Pri");
            out.println(pg.getMainPage());
          
        }
        System.out.println("Pri");
        out.println("</div>");
        out.println("</body>");
        out.println("</html>");
    }
    public String getEditPage(String key){
    
        StringBuilder sb = new StringBuilder();
        sb.append("<form method=\"post\" action=\"EditedServlet\">\n");
        sb.append("Name: <input type=\"text\" name=\"name\" value=\"" + key +"\">\n");
        sb.append("<br>Type:<br>\n<input type=\"radio\" name=\"type\" value=\"bug\">Bug\n");
        sb.append("<input type=\"radio\" name=\"type\" value=\"feature\">Feature\n"); ;
        sb.append("<br>Description: <input type=\"text\" name=\"desc\" value=\"" + book.getValue(key).get(1) +"\">\n"); 
        sb.append("<br>Person: <input type=\"text\" name=\"person\" value=\"" + book.getValue(key).get(2) +"\">\n"); 
        sb.append("<br>Status:<br>\n<input type=\"radio\" name=\"status\" value=\"open\">Open\n"); 
        sb.append("<input type=\"radio\" name=\"status\" value=\"close\">Close\n"); 
        sb.append("<br><input class=\"btn btn-primary\" type=\"submit\" value=\"save\">\n");
        sb.append("</form>");
        return sb.toString();
    }
}
