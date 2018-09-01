package am.ak.dao.jdbc.connections;

import org.apache.commons.dbcp2.cpdsadapter.DriverAdapterCPDS;
import org.apache.commons.dbcp2.datasources.SharedPoolDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

import static am.ak.dao.jdbc.connections.Pool.getConnection;

public class Test {
    public static void main(String[] args) {


        Pool2 pool = new Pool2();
        try {
            Connection connection = getConnection();
            int x = (int) 10l;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static class Pool2 {
        private static DataSource ds;

        static {
            DriverAdapterCPDS cpds = new DriverAdapterCPDS();

            try {
                cpds.setDriver("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            cpds.setUrl("jdbc:mysql://localhost:3306/collage");
            cpds.setUser("root");
            cpds.setPassword("r00t");
            SharedPoolDataSource tds = new SharedPoolDataSource();
            tds.setConnectionPoolDataSource(cpds);
            tds.setMaxTotal(10);
            //tds.setMaxWaitMillis(50);
            ds = tds;
        }

        public static Connection getConnection() throws SQLException {

            return ds.getConnection();
        }
    }
}
