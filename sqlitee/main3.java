package sqlitee;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class main3{
	
	public static void main(String[] args) throws IOException, SQLException {
    Scanner scanner = new Scanner(System.in);
    String userType;
    
    while(true) {
    	
        do {
            System.out.print("Lütfen seçim yapınız (ogrenci/danisman): ");
            userType = scanner.nextLine().toLowerCase();
        } 
        while (!userType.equals("ogrenci") && !userType.equals("danisman"));
        
        if (userType.equals("ogrenci")) {
            System.out.print("Lütfen öğrenci numaranızı giriniz:");
            int studentId = scanner.nextInt();
            scanner.nextLine(); 
            ogrenci3 student = new ogrenci3(studentId, "Student Name");
            
            if (student.isRegistrationPending()) {
                System.out.println("Kayıt olduğunuz dersler onaylandı.");
                continue;
            } 
            int choice;
            boolean exit = false;
            
            do {
                System.out.println("1. Derse kayıt ol");
                System.out.println("2. Kayıt olunan dersi sil");
                System.out.println("3. Danışmana gönder");
                System.out.println("0. ÇIKIŞ");
                System.out.print("Seçiminizi giriniz: ");
                choice = scanner.nextInt();
                scanner.nextLine(); 

                try {
                    switch (choice) {
                        case 1:
                            System.out.print("Ders ID'sini giriniz: ");
                            int courseId = scanner.nextInt();
                            scanner.nextLine(); 
                            student.registerCourse(courseId);
                            break;
                        case 2:
                            System.out.print("Silmek için Ders ID'sini giriniz: ");
                            courseId = scanner.nextInt();
                            scanner.nextLine(); 
                            student.unregisterCourse(courseId);
                            break;
                        case 3:
                            //öğrenci, danışmana göndermeden önce 4 derse kayıt yaptırmalı.
                            if (student.getCourseCount() < 4) {
                                System.out.println("Lütfen en az 4 derse kaydolun.");
                                break;
                            }
                            
                            student.sendRequest();
                            System.out.println("Danışmana gönderildi.");
                            break;
                        case 0:
                            exit = true;
                            break;
                        default:
                            System.out.println("Geçersiz seçim.");
                            break;
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } while (!exit);
        } else {
        	
            danisman3 advisor = new danisman3(1, "Advisor Name");
            int choice;
            boolean exit = false;
            
            do {
                System.out.println("1. Onay Bekleyen Öğrenciler");
                System.out.println("2. Kaydı onayla");
                System.out.println("3. Kaydı onaylanmış öğrencileri export et.  ");
                System.out.println("0. Çıkış");
                System.out.print("Seçiminizi giriniz: ");
                choice = scanner.nextInt();
                scanner.nextLine(); 

                try {
                    switch (choice) {
                        case 1:
                            advisor.viewPendingRequests();
                            break;
                        case 2:
                            System.out.print("Lütfen kaydının onaylanmasını istediğiniz öğrenci ID'sini giriniz: ");
                            int studentId = scanner.nextInt();
                            scanner.nextLine(); 
                            advisor.approveRegistrations(studentId);
                            break;
                        case 3:
                            advisor.exportApprovedRegistrations();
                            break;
                        case 0:
                            exit = true;
                            break;
                        default:
                            System.out.println("Geçersiz seçim.");
                            break;
                    }
             
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } while (!exit);
       }
      }
     }
	}