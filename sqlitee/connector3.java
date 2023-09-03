package sqlitee;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class connector3 {
	
    private static Connection conn;
    public static Connection getConnection() throws SQLException {
        if (conn == null) {
            conn = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\asus\\Desktop\\kayit\\test4.db");         // Kendi dosya yolunuzu ekleyin. Bu yol benim bilgisayarıma ait olduğu için proje çalışmayabilir.
        }
        return conn;
    }
}
