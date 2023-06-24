package cn.s0228.servlet.loginAndRegister;

import cn.s0228.bean.User;
import cn.s0228.jdbc.dao.UserDAO;
import cn.s0228.jdbc.utils.C3p0Utils;
import org.apache.commons.dbutils.QueryRunner;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "loginServlet", value = "/loginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        设置编码格式
        response.setContentType("text/html;charset=UTF-8");
//        创建对象
        QueryRunner runner=new QueryRunner(C3p0Utils.getDataSource());
        UserDAO userDAO = new UserDAO(runner);
//        获取登录数据
        String name=request.getParameter("name");
        String password=request.getParameter("password");
//        检查
        try {
            boolean rowsAffected=userDAO.checkDao(name,password);
            if(rowsAffected){
                User user=new User();
                user.setName(name);
                user.setPassword(password);
                request.getSession().setAttribute("user",user);
//                发送cookie
                String autoLogin=request.getParameter("autoLogin");
                if (autoLogin!=null){
                    Cookie cookie=new Cookie("name",name+"-"+password);
                    cookie.setPath(request.getContextPath());
                    response.addCookie(cookie);
                }
                response.sendRedirect("home.html");
            }else {
                response.sendRedirect("login.html");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
