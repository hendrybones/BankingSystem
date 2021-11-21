import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "Login", value = "/Login")
public class Login extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name=request.getParameter("username");
        String pass=request.getParameter("pass");

        if (name.equals("hendry" )&& pass.equals("hendry56")){
            HttpSession session=request.getSession();
            session.setAttribute("username",name);
            response.sendRedirect("customerReport.jsp");
        }
        else {
            response.sendRedirect("Login.jsp");
        }
    }
}
