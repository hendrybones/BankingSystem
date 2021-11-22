import com.example.BankingSystem.Dao;
import com.example.BankingSystem.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
                try {
                    insertUser(request,response);
                }catch (SQLException e){
                    e.printStackTrace();
                }

                break;
            case "/update":

                break;
            case "/edit":
//                showEditForm(request,response);

                break;
            case "/delete":
                try {
                    deleteUser(request,response);
                }catch (SQLException e){
                    e.printStackTrace();
                }

                break;
            default:
                UsersList(request,response);
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
    public  void insertUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        String name=request.getParameter("name");
        String email=request.getParameter("email");
        String country=request.getParameter("country");
        User newUser=new User(name,email,country);
        dao.insertUser(newUser);
        response.sendRedirect("list");

    }
    public  void deleteUser(HttpServletRequest request,HttpServletResponse response) throws SQLException, IOException {
        int id=Integer.parseInt(request.getParameter("id"));
        dao.deleteUser();
        response.sendRedirect("list");

    }
//    public  void  showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        int id=Integer.parseInt(request.getParameter("id"));
//        User existingUser=dao.selectAllUsers(id);
//
//        RequestDispatcher dispatcher=request.getRequestDispatcher("userForm.jsp");
//        request.setAttribute("user",existingUser);
//        dispatcher.forward(request, response);
//
//    }
    public void updateUser(HttpServletRequest request,HttpServletResponse response) throws SQLException, IOException {
        int id=Integer.parseInt(request.getParameter("id"));
        String name=request.getParameter("name");
        String email=request.getParameter("email");
        String country=request.getParameter("country");

        User user=new User(name,email, country);
        dao.updateUser(user);
        response.sendRedirect("list");

    }
    public void UsersList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<User> list=dao.selectAllUsers();
        request.setAttribute("ListUser",list);
        RequestDispatcher dispatcher=request.getRequestDispatcher("userList.jsp");
        dispatcher.forward(request,response);

    }
}
