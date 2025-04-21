package Database_Connectivity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class Updation {
	private static final String driver = "com.mysql.cj.jdbc.Driver";
	private static final String url = "jdbc:mysql://localhost:3306/project";
	private static final String username = "root";
	private static final String password = "root";
	private static Connection conn;
	private static PreparedStatement pmst;

	public static void main(String[] args) {
		try {
		Scanner sc = new Scanner(System.in);
		Class.forName(driver);
		conn = DriverManager.getConnection(url, username, password);
		String sql = "update login set login_email=?, login_password=?  where login_id=?";
		pmst = conn.prepareStatement(sql);
		System.out.println("Enter login email:");
		pmst.setString(1, sc.next());
		System.out.println("Enter login password:");
		pmst.setString(2, sc.next());
		System.out.println("Enter login id:");
		pmst.setString(3, sc.next());

		int i = pmst.executeUpdate();
		if (i > 0) {
			System.out.println("Data updated");
		} else {
			System.out.println("Data not updated");
		}
		conn.close();
		pmst.close();
		sc.close();
	}
		catch (Exception e) {
			e.printStackTrace();
		}
}
}