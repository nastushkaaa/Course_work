import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

public class LogoutServlet extends HttpServlet{
    MainPage pg = new MainPage();
    public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
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
        HttpSession session = request.getSession();
        session.invalidate();
        request.getRequestDispatcher("links.html").include(request, response);
        System.out.println("Pri");
        out.println(pg.getMainPage());
        out.println("</div>");
        out.println("</body>");
        out.println("</html>");
    }
}
