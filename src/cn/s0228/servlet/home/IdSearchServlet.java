package cn.s0228.servlet.home;

import cn.s0228.bean.Station;
import cn.s0228.jdbc.dao.StationDao;
import cn.s0228.jdbc.utils.C3p0Utils;
import net.sf.json.JSONArray;
import org.apache.commons.dbutils.QueryRunner;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ISS", value = "/ISS")
public class IdSearchServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //                设置请求编码,相应方式和编码方式
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
//        创建对象
        QueryRunner runner = new QueryRunner(C3p0Utils.getDataSource());
        StationDao stationDao = new StationDao(runner);
//      获取文本
        JSONArray jsonArray=new JSONArray();
        StringBuilder sb = new StringBuilder();
        BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream(), StandardCharsets.UTF_8));
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }
        String id = sb.toString();

        // 在控制台打印接收到的 JSON 数据
        System.out.println(id);
        System.out.println(id);
        System.out.println(id);
        List<Station> stations = stationDao.queryDao();
        List<Station> searchStations = new ArrayList<>();
        for (Station station:stations){
            if(Integer.toString(station.getId()).equals(id)){
                searchStations.add(station);
            }
        }
        if(!(searchStations == null)){
            jsonArray=JSONArray.fromObject(searchStations);
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.print(jsonArray);
            out.flush();
            out.close();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
