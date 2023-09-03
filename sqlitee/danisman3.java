package sqlitee;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class danisman3 extends kullanici3 {

    public danisman3(int id, String name) {
        super(id, name);
    }
    
    public void viewPendingRequests() throws SQLException {
        Connection conn = connector3.getConnection();
        PreparedStatement stmt = conn.prepareStatement("SELECT id, student_id FROM requests WHERE status='pending'");
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            int requestId = rs.getInt("id");
            int studentId = rs.getInt("student_id");
            System.out.println(" Student ID: " + studentId);
        }
    }
    
    public void approveRegistrations(int studentId) throws SQLException {
        Connection conn = connector3.getConnection();
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM registrations WHERE student_id = ? AND status = 'pending'");
        
        stmt.setInt(1, studentId);
        ResultSet rs = stmt.executeQuery();
        System.out.println( "----------DERS LİSTESİ---------- ");
        while (rs.next()) {
            int registrationId = rs.getInt("id");
            int courseId = rs.getInt("course_id");
            
            System.out.println("Course ID: "+ courseId );
            PreparedStatement updateStmt = conn.prepareStatement("UPDATE registrations SET status = 'approved' WHERE id = ?");
            updateStmt.setInt(1, registrationId);
            updateStmt.executeUpdate();
         
        }
        
        stmt = conn.prepareStatement("UPDATE requests SET status = 'approved' WHERE student_id = ?");
        stmt.setInt(1, studentId);
        stmt.executeUpdate();
        System.out.println( "\n Kayıt olunan tüm dersler onaylandı.\n");
    }

    public void exportApprovedRegistrations() throws SQLException, IOException {
        Connection conn = connector3.getConnection();
        PreparedStatement stmt = conn.prepareStatement(
                "SELECT r.student_id, r.course_id " +
                "FROM registrations r " +
                "JOIN requests req ON r.student_id = req.student_id " +
                "WHERE req.status = 'approved'"
        );
        ResultSet rs = stmt.executeQuery();

        FileWriter writer = new FileWriter("onaylanmis_kayit.csv");
        writer.append("student_id,course_id\n");

        while (rs.next()) {
            int studentId = rs.getInt("student_id");
            int courseId = rs.getInt("course_id");
            writer.append(String.valueOf(studentId));
            writer.append("         ,   ");
            writer.append(String.valueOf(courseId));
            writer.append("\n");    
        }
        
        System.out.print("Lütfen csv dosyasını kontrol edin. \n");
        writer.flush();
        writer.close();
    }
}
