package test;

import am.ak.dao.jdbc.connections.Pool;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet("/Demo.do")
public class DemoController extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ResultSet rsObj = null;
        Connection connObj = null;
        PreparedStatement pstmtObj = null;


        try {

            connObj = Pool.getConnection();
            //DataSource dataSource = jdbcObj.setUpPool();
            //jdbcObj.printDbStatus();

            // Performing Database Operation!
            System.out.println("\n=====Making A New Connection Object For Db Transaction=====\n");
            //connObj = dataSource.getConnection();
            //jdbcObj.printDbStatus();

            pstmtObj = connObj.prepareStatement("SELECT * FROM students");
            rsObj = pstmtObj.executeQuery();

            while (rsObj.next()) {
                System.out.println("Username: " + rsObj.getString("first_name"));
            }
            System.out.println("\n=====Releasing Connection Object To Pool=====\n");
        } catch (SQLException e) {
            e.printStackTrace();
        }


        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/demo.jsp");
        dispatcher.forward(request, response);
    }
}
