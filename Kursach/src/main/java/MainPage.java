import java.util.ArrayList;
import java.util.Map;
import java.util.Vector;

public class MainPage {
    Phones book = new Phones();
    public MainPage(){
        book.loadFromTextFile();
    }
    public String getMainPage() {
        StringBuilder sb = new StringBuilder();
        sb.append("<br>\n");
        sb.append("<br>\n");
        sb.append("<b><h3 style=\"color: black;\">Opened tickets</h3></b><br>\n");
        sb.append("<hr>\n");
        for (Map.Entry<String, Vector<String>> pair : book.book.entrySet()) {
            if (pair.getValue().get(3).equals("open")) {
                sb.append("<fieldset>");
                sb.append("<div class=\"row g-3\">");
                sb.append("<div class=\"col-md-4\">");
                sb.append(pair.getKey() + " ");
                sb.append("</div>");
                sb.append("<div class=\"col-md-4\">");
                sb.append("Type: " + pair.getValue().get(0) + " ");
                sb.append("</div>");
                sb.append("<div class=\"col-md-4\" style=\"word-wrap: break-word;\">");  // Добавили стиль word-wrap для разбивки слов
                sb.append("Description: " + pair.getValue().get(1) + " ");
                sb.append("</div>");
                sb.append("<div class=\"col-md-1\">");
                sb.append("<form method=\"post\" action=\"DeleteServlet\" class=\"d-inline-block\">");
                sb.append("<input type=\"hidden\" name=\"num\" value=\"" + pair.getKey() + "\">");
                sb.append("<input class=\"btn btn-primary\" type=\"submit\" value=\"delete\">");
                sb.append("</form>");
                sb.append("<form method=\"post\" action=\"EditServlet\" class=\"d-inline-block ml-2\">");
                sb.append("<input type=\"hidden\" name=\"num\" value=\"" + pair.getKey() + "\">");
                sb.append("<input class=\"btn btn-primary\" type=\"submit\" value=\"edit\">");
                sb.append("</form>");
                sb.append("</div>");
                sb.append("</div>");
                sb.append("</fieldset>");
                sb.append("<hr>");
            }
        }

        sb.append("<br>\n");
        sb.append("<br>\n");
        sb.append("<b><h3 style=\"color: black;\">Closed tickets</h3></b><br>\n");

        sb.append("<hr>\n");
        for (Map.Entry<String, Vector<String>> pair : book.book.entrySet()) {
            if (pair.getValue().get(3).equals("close")) {
                sb.append("<fieldset>");
                sb.append("<div class=\"row g-3\">");
                sb.append("<div class=\"col-md-4\">");
                sb.append(pair.getKey() + " ");
                sb.append("</div>");
                sb.append("<div class=\"col-md-4\">");
                sb.append("Type: " + pair.getValue().get(0) + " ");
                sb.append("</div>");
                sb.append("<div class=\"col-md-4\" style=\"word-wrap: break-word;\">");  // Добавили стиль word-wrap для разбивки слов
                sb.append("Description: " + pair.getValue().get(1) + " ");
                sb.append("</div>");
                sb.append("<div class=\"col-md-1\">");
                sb.append("<form method=\"post\" action=\"DeleteServlet\" class=\"d-inline-block\">");
                sb.append("<input type=\"hidden\" name=\"num\" value=\"" + pair.getKey() + "\">");
                sb.append("<input class=\"btn btn-primary\" type=\"submit\" value=\"delete\">");
                sb.append("</form>");
                sb.append("<form method=\"post\" action=\"EditServlet\" class=\"d-inline-block ml-2\">");
                sb.append("<input type=\"hidden\" name=\"num\" value=\"" + pair.getKey() + "\">");
                sb.append("<input class=\"btn btn-primary\" type=\"submit\" value=\"edit\">");
                sb.append("</form>");
                sb.append("</div>");
                sb.append("</div>");
                sb.append("</fieldset>");
                sb.append("<hr>");
            }
        }

        return sb.toString();
    }
}
