import com.example.BankingSystem.Dao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "UserServlet", value = "/UserServlet")
public class UserServlet extends HttpServlet {

    private Dao dao;

    public UserServlet(){
        this.dao=new Dao();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action= request.getServletPath();
        switch (action){
            case "/new":
                showNewForm(request, response);
                break;
            case "/insert":
                break;
            case "/update":
                break;
            case "/edit":
                break;
            case "/delete":
                break;
            default:
                break;

        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request,response);

    }

    public void showNewForm(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher=request.getRequestDispatcher("userForm.jsp");
        dispatcher.forward(request, response);
    }
}
