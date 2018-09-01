package am.ak.dao.jdbc.connections;

import org.apache.commons.dbcp2.cpdsadapter.DriverAdapterCPDS;
import org.apache.commons.dbcp2.datasources.SharedPoolDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author Sergii.Zagriichuk
 */
public class Pool {
    private static DataSource ds;

    static {
        DriverAdapterCPDS cpds = new DriverAdapterCPDS();
        try {
            cpds.setDriver("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        cpds.setUrl("jdbc:mysql://localhost:3306/collage");
        cpds.setUser("root");
        cpds.setPassword("r00t");

        SharedPoolDataSource tds = new SharedPoolDataSource();
        tds.setConnectionPoolDataSource(cpds);
        tds.setMaxTotal(10);
        /*tds.setMaxWait(50);
        tds.setMaxTotal();*/

        tds.setMaxTotal(50);

        ds = tds;
    }

    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }
}