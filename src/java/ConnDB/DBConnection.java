package ConnDB;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    public static Connection getConnection() throws Exception {
        String url = "jdbc:sqlserver://localhost:1433;"
                + "databaseName=QLBX;"
                + "encrypt=true;"
                + "trustServerCertificate=true";
        String user = "sa";
        String pass = "sa";
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        System.out.println("Test: ConnDB");
        return DriverManager.getConnection(url, user, pass);
    }
}

/*

create


*/

/*
insert

*/
