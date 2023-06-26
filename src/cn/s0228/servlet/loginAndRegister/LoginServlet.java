package cn.s0228.servlet.loginAndRegister;

import cn.s0228.jdbc.dao.UserDAO;
import cn.s0228.jdbc.utils.C3p0Utils;
import org.apache.commons.dbutils.QueryRunner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
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
                request.getSession().setAttribute("name",name);
//                发送cookie
                String autoLogin=request.getParameter("autoLogin");
                if (autoLogin!=null){
                    Cookie cookie=new Cookie("name",name+"-"+password);
                    cookie.setPath(request.getContextPath());
                    response.addCookie(cookie);
                }
                response.sendRedirect("home.html");
                PrintWriter out=response.getWriter();
                out.print(name);
                out.flush();
                out.close();
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
