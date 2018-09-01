package am.ak.dao.jdbc.connections;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MainClass {
    public static void main(String[] args) {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = Pool.getConnection();
            // Do work with connection
            statement = connection.createStatement();
            String selectEmployeesSQL = "select * from students";
            resultSet = statement.executeQuery(selectEmployeesSQL);

            while (resultSet.next()) {
                printTestTable(resultSet);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                } // nothing we can do
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                } // nothing we can do
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                } // nothing we can do
            }
        }
    }

    private static void printTestTable(ResultSet resultSet) throws SQLException {
        System.out.print(resultSet.getString("first_name") + ", ");
        System.out.print(resultSet.getString("last_name"));
        System.out.println(" ");
    }

}