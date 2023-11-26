import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.Vector;

public class TestingServ extends HttpServlet {

  Phones book = new Phones();
  Names names = new Names();

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
    out.println("<link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css\" rel=\"stylesheet\"integrity=\"sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3\" crossorigin=\"anonymous\">");
    out.println("</head>");
    out.println("<body>");
    out.println("<div class=\"container-xxl\">");
    out.println("<h1>Bug tracker" + "</h1>");
    HttpSession sess1 = request.getSession();
    if(sess1.getAttribute("name") != null) out.println("User: " + sess1.getAttribute("name") + "\n");

    if(uri.equals("com/example/kursach/TestingServ/button")){
      out.println(getAddPage1());
    }
    else if(uri.equals("com/example/kursach/TestingServ/buttonL")){
      out.println(getLoginPage());
    }
    else if(uri.equals("com/example/kursach/TestingServ/buttonS")){
      out.println(getSignPage());
    }
    else if(uri.equals("com/example/kursach/TestingServ/buttonOut")){
      HttpSession session = request.getSession();
      session.invalidate();
      out.println(getMainPage());
    }

    else{
      out.println(getMainPage());
    }
    out.println("<p>" + uri + "</p>");
    out.println("</div>");
    out.println("</body>");
    out.println("</html>");
  }

  public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
    response.setContentType("text/html");
    String uri = request.getRequestURI();
    PrintWriter out = response.getWriter();
    out.println("<html>");
    out.println("<head>");
    out.println("<title>Course work</title>");
    out.println("<link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css\" rel=\"stylesheet\"integrity=\"sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3\" crossorigin=\"anonymous\">");
    out.println("</head>");
    out.println("<body>");
    out.println("<div class=\"container-xxl\">");
    out.println("<h1>Bug tracker" + "</h1>");
    HttpSession sess = request.getSession();
    if(sess.getAttribute("name") != null) out.println("User: " + sess.getAttribute("name") + "\n");
    if(uri.equals("com/example/kursach/TestingServ/add")){
      if( (request.getParameter("name") == "")){
        out.println("Enter all parameters!!!<br>\n");
        out.println(getEditPage(request.getParameter("name")));
      }
      else{
        book.add(request.getParameter("name"), request.getParameter("type"), request.getParameter("desc"), request.getParameter("person"), "open");
        book.saveToTextFile();
        System.out.println("Pri");
        out.println(getMainPage());
      }
    }
    else if(uri.equals("com/example/kursach/TestingServ/edited")){
      if( (request.getParameter("name") == "") || (request.getParameter("status") == null)){
        out.println("Enter all parameters!!!<br>\n");
        out.println(getEditPage(request.getParameter("name")));
      }
      else{
        book.add(request.getParameter("name"), request.getParameter("type"), request.getParameter("desc"), request.getParameter("person"), request.getParameter("status"));
        book.saveToTextFile();
        System.out.println("Pri");
        out.println(getMainPage());
      }
    }
    else if( (uri.equals("com/example/kursach/TestingServ/delete"))){
      book.delete(request.getParameter("num"));
      book.saveToTextFile();
      System.out.println("Pri");
      out.println(getMainPage());

    }
    else if( (uri.equals("com/example/kursach/TestingServ/edit"))){

      System.out.println("Pri");
      out.println(getEditPage(request.getParameter("num")));

    }
    else if( (uri.equals("com/example/kursach/TestingServ/sign"))){

      System.out.println("Pri");
      String name = request.getParameter("name");
      String password = request.getParameter("password");
      if (! (names.check_name(name)) ){
        HttpSession session = request.getSession();
        session.setAttribute("name", name);
        names.add(name, password);
        names.saveToTextFile();
        out.println(getMainPage());
      }
      else{
        out.println("This user is already signed up!");
        out.println(getMainPage());
      }
    }
    else if( (uri.equals("com/example/kursach/TestingServ/login"))){

      System.out.println("Pri");
      String name = request.getParameter("name");
      String password = request.getParameter("password");
      if (names.check_password(name, password)){
        HttpSession session = request.getSession();
        session.setAttribute("name", name);
        out.println(getMainPage());
      }
      else{
        out.println("Wrong login or password!");
        out.println(getLoginPage());
      }
    }

    else{
      out.println(getMainPage());
    }
    out.println("<p>" + uri + "</p>");
    out.println("</div>");
    out.println("</body>");
    out.println("</html>");
  }

  public String getMainPage(){
    StringBuilder sb = new StringBuilder();
    sb.append("<form method=\"GET\" action=\"com/example/kursach/TestingServ/buttonL\">\n");
    sb.append("<input class=\"btn btn-primary\" type=\"submit\" value=\"Login\">\n");
    sb.append("</form>");
    sb.append("<form method=\"GET\" action=\"com/example/kursach/TestingServ/buttonS\">\n");
    sb.append("<input class=\"btn btn-primary\" type=\"submit\" value=\"Sign up\">\n");
    sb.append("</form>");
    sb.append("<form method=\"GET\" action=\"com/example/kursach/TestingServ/buttonOut\">\n");
    sb.append("<input class=\"btn btn-primary\" type=\"submit\" value=\"Logout\">\n");
    sb.append("</form>");
    sb.append("<form method=\"GET\" action=\"com/example/kursach/TestingServ/button\">\n");
    sb.append("<input class=\"btn btn-primary\" type=\"submit\" value=\"add Ticket\">\n");
    sb.append("</form>");
    sb.append("<b><h3>Opened tickets</h3></b><br>\n");
    sb.append("<hr>\n");
    for (Map.Entry<String, Vector<String>> pair : book.book.entrySet()) {
        if(pair.getValue().get(3).equals("open")){
            sb.append("<fieldset>");
            sb.append("<div class=\"row g-3\">");
            sb.append("<div class=\"col-md-4\">");
            sb.append(pair.getKey() + " ");
            sb.append("</div>");
            sb.append("<div class=\"col-md-1\">");
            sb.append("<form method=\"post\" action=\"com/example/kursach/TestingServ/delete\">\n");
            sb.append("<input type=\"hidden\" name=\"num\" value=\"" + pair.getKey() + "\">\n") ;
            sb.append("<input class=\"btn btn-primary\" type=\"submit\" value=\"delete\">\n");
            sb.append("</form>");
            sb.append("</div>");
            sb.append("<div class=\"col-md-7\">");
            sb.append("<form method=\"post\" action=\"com/example/kursach/TestingServ/edit\">\n");
            sb.append("<input type=\"hidden\" name=\"num\" value=\"" + pair.getKey() + "\">\n") ;
            sb.append("<input class=\"btn btn-primary\" type=\"submit\" value=\"edit\">\n");
            sb.append("</form>");
            sb.append("</div>");
            sb.append("</div>");
            sb.append("</fieldset>\n");
            sb.append("<hr>\n");

        }
    }
    sb.append("<b><h3>Closed tickets</h3></b><br>\n");
    sb.append("<hr>\n");
    for (Map.Entry<String, Vector<String>> pair : book.book.entrySet()) {
        if(pair.getValue().get(3).equals("close")){
          sb.append("<fieldset>");
          sb.append("<div class=\"row g-3\">");
          sb.append("<div class=\"col-md-4\">");
          sb.append(pair.getKey() + " ");
          sb.append("</div>");
          sb.append("<div class=\"col-md-1\">");
          sb.append("<form method=\"post\" action=\"com/example/kursach/TestingServ/delete\">\n");
          sb.append("<input type=\"hidden\" name=\"num\" value=\"" + pair.getKey() + "\">\n") ;
          sb.append("<input class=\"btn btn-primary\" type=\"submit\" value=\"delete\">\n");
          sb.append("</form>");
          sb.append("</div>");
          sb.append("<div class=\"col-md-7\">");
          sb.append("<form method=\"post\" action=\"/com/example/kursach/TestingServ/edit\">\n");
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
  

  public String getAddPage1(){
    
    StringBuilder sb = new StringBuilder();
    sb.append("<form method=\"post\" action=\"com/example/kursach/TestingServ/add\">\n");
    sb.append("Name: <input type=\"text\" name=\"name\" value=\"Bug #\">\n"); 
    sb.append("<br>Type:<br>\n<input type=\"radio\" name=\"type\" value=\"bug\">Bug\n"); 
    sb.append("<input type=\"radio\" name=\"type\" value=\"feature\">Feature\n"); ;
    sb.append("<br>Description: <input type=\"text\" name=\"desc\">\n"); 
    sb.append("<br>Person: <input type=\"text\" name=\"person\" value=\"Vasya Petrov\">\n");
    sb.append("<br><input class=\"btn btn-primary\" type=\"submit\" value=\"add\">\n");
    sb.append("</form>");
    return sb.toString();
  }
  public String getEditPage(String key){
    
    StringBuilder sb = new StringBuilder();
    sb.append("<form method=\"post\" action=\"com/example/kursach/TestingServ/edited\">\n");
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

  public String getLoginPage(){
    
    StringBuilder sb = new StringBuilder();
    sb.append("<form method=\"post\" action=\"com/example/kursach/TestingServ/login\">\n");
    sb.append("Name: <input type=\"text\" name=\"name\">\n"); 
    sb.append("Password: <input type=\"password\" name=\"password\">\n"); 
    sb.append("<br><input class=\"btn btn-primary\" type=\"submit\" value=\"Login\">\n");
    sb.append("</form>");
    return sb.toString();
  }

  public String getSignPage(){
    
    StringBuilder sb = new StringBuilder();
    sb.append("<form method=\"post\" action=\"com/example/kursach/TestingServ/sign\">\n");
    sb.append("Name: <input type=\"text\" name=\"name\">\n"); 
    sb.append("Password: <input type=\"password\" name=\"password\">\n"); 
    sb.append("<br><input class=\"btn btn-primary\" type=\"submit\" value=\"Sign up\">\n");
    sb.append("</form>");
    return sb.toString();
  }
}

