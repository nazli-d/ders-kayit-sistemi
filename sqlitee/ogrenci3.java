package sqlitee;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;

public class ogrenci3 extends kullanici3 {
	
    public ogrenci3(int id, String name) {
        super(id, name);
    }
    
    public void registerCourse(int courseId) throws SQLException {
        Connection conn = connector3.getConnection();
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO registrations(student_id, course_id) VALUES (?, ?)");
        
        stmt.setInt(1, this.id);
        stmt.setInt(2, courseId);
        stmt.executeUpdate();
        System.out.println("Ders başarıyla kaydedildi.");        
    }
 
    public void unregisterCourse(int courseId) throws SQLException {
        Connection conn = connector3.getConnection();
        PreparedStatement checkStmt = conn.prepareStatement("SELECT * FROM registrations WHERE student_id = ? AND course_id = ?");
        checkStmt.setInt(1, this.id);
        checkStmt.setInt(2, courseId);
        ResultSet rs = checkStmt.executeQuery();
        
        if (!rs.next()) {
            System.out.println("Kayıt bulunamadı.");
            return;
        }
        
        PreparedStatement deleteStmt = conn.prepareStatement("DELETE FROM registrations WHERE student_id = ? AND course_id = ?");
        deleteStmt.setInt(1, this.id);
        deleteStmt.setInt(2, courseId);
        int affectedRows = deleteStmt.executeUpdate();
        
        if (affectedRows == 0) {
            System.out.println("Kayıt silinemedi.");
        } else {
            System.out.println("Kayıt silme işlemi tamamlandı.");
        }
    }
   
    public int getCourseCount() throws SQLException {
    	Connection conn = connector3.getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM registrations WHERE student_id = " + id);
        rs.next();
        return rs.getInt(1);
    }
    
    public void sendRequest() throws SQLException {
        Connection conn = connector3.getConnection();
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO requests(student_id) VALUES (?)");
        stmt.setInt(1, this.id);
        stmt.executeUpdate();     
    }
    
    public boolean isRegistrationPending() throws SQLException {
        Connection conn = connector3.getConnection();
        PreparedStatement stmt = conn.prepareStatement(
                "SELECT * FROM requests WHERE student_id = ? AND status = 'approved'"
        );
        
        stmt.setInt(1, this.id);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            return true;
        } else {
            return false;
        }
    }
}