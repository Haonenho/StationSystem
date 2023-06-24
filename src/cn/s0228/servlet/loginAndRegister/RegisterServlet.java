package cn.s0228.servlet.loginAndRegister;

import cn.s0228.jdbc.dao.UserDAO;
import cn.s0228.jdbc.utils.C3p0Utils;
import org.apache.commons.dbutils.QueryRunner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;


@WebServlet(name = "registerServlet", value = "/registerServlet")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        设置编码格式
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
//       创建对象
        QueryRunner runner=new QueryRunner(C3p0Utils.getDataSource());
        boolean rowsAffected = false;
        UserDAO userDAO = new UserDAO(runner);
//        读取
        String name = request.getParameter("name");
        String password = request.getParameter("password");
//        存入
        if (name.isEmpty() || password.isEmpty()) {
            response.sendRedirect("register.html");
        } else {
            try {
                rowsAffected = userDAO.insertDao(name, password);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            response.sendRedirect("login.html");
        }

        System.out.println(rowsAffected);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
