package Database_Connectivity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class Dynamic_application {
	private static final String driver = "com.mysql.cj.jdbc.Driver";
	private static final String username = "root";
	private static final String password = "root";
	private static PreparedStatement pmst;
	private static Connection conn;

	public static void main(String[] args) {

		int choice;
		do {
			Scanner sc = new Scanner(System.in);
			System.out.println("Choose your choice:");
			displaymenu();
			choice = Integer.parseInt(sc.next());
			switch (choice) {
			case 1:
				CreateDatabase();
				break;
			case 2:
				DropDatabase();
				break;
			case 3:
				DataInsertion();
				break;
			case 4:
				DeletebyEmail();
				break;
			case 5:
				UpdateData();
				break;
			case 6:
				GetbyEmail();
				break;
			case 7:
				Getall();
				break;
			case 8:
				login();
				break;
			case 9:
				System.exit(0);
				break;
			default:
				System.out.println("Invalid option!!");
				break;
			}
		} while (choice > 0);
	}

	private static void login() {
		try {
			Scanner sc = new Scanner(System.in);
			Class.forName(driver);
			System.out.println("enter database name:");
			String url = "jdbc:mysql://localhost:3306/" + sc.next();
			conn = DriverManager.getConnection(url, username, password);
			System.out.println("enter table name:");
			String sql = "select * from " + sc.next()+" where login_id=? and login_password=?";
			pmst = conn.prepareStatement(sql);
			System.out.println("Enter Login ID:");
			pmst.setLong(1, sc.nextLong());
			System.out.println("Enter login Password:");
			pmst.setString(2, sc.next());
			ResultSet rs = pmst.executeQuery();
			if(rs.next()) {
				System.out.println("Login successful");
			} else {
				System.out.println("login failed");
			}
			pmst.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	private static void Getall() {
		try {
			Scanner sc = new Scanner(System.in);
			Class.forName(driver);
			System.out.println("enter database name:");
			String url = "jdbc:mysql://localhost:3306/" + sc.next();
			conn = DriverManager.getConnection(url, username, password);
			System.out.println("enter table name:");
			String sql = "select * from " + sc.next();
			pmst = conn.prepareStatement(sql);
			ResultSet rs = pmst.executeQuery();
			while (rs.next()) {
				System.out.println("Order ID: " + rs.getLong("order_id"));
				System.out.println("Order name: " + rs.getString("order_name"));
				System.out.println("Order pincode: " + rs.getInt("order_pincode"));
				System.out.println("Order Address" + rs.getString("order_address"));

			}
			pmst.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static void GetbyEmail() {
		try {
		Scanner sc = new Scanner(System.in);
		Class.forName(driver);
		System.out.println("Enter database name");
		String url = "jdbc:mysql://localjost:3306/"+sc.next();
		conn = DriverManager.getConnection(url, username, password);
		System.out.println("Enter table name:");
		String sql = "select * from "+sc.next() + "where order_id = ?";
		pmst = conn.prepareStatement(sql);
		System.out.println("Enter Order ID:");
		pmst.setLong(1, sc.nextLong());
		ResultSet rs = pmst.executeQuery();
		while(rs.next()) {
			System.out.println("Order ID: " + rs.getLong("order_id"));
			System.out.println("Order name: " + rs.getString("order_name"));
			System.out.println("Order pincode: " + rs.getInt("order_pincode"));
			System.out.println("Order Address" + rs.getString("order_address"));
		}
		conn.close();
		pmst.close();
		} catch (Exception e) {
	
		}

	}

	private static void UpdateData() {
		try {
			Scanner sc = new Scanner(System.in);
			Class.forName(driver);
			System.out.println("enter database name:");
			String url = "jdbc:mysql://localhost:3306/" + sc.next();
			conn = DriverManager.getConnection(url, username, password);
			System.out.println("Enter table name:");
			String sql = "update " + sc.next()
					+ "set order_name = ?, order_pincode = ?, order_address = ? where order_id = ?";
			pmst = conn.prepareStatement(sql);
			System.out.println("Enter Order Name:");
			pmst.setString(1, sc.next());
			System.out.println("Enter Order Pincode:");
			pmst.setInt(2, sc.nextInt());
			System.out.println("enter Order Address:");
			pmst.setString(3, sc.next());
			System.out.println("Enter Order ID:");
			pmst.setLong(4, sc.nextLong());

			int i = pmst.executeUpdate();
			if (i > 0) {
				System.out.println("Data updated successfully");
			} else {
				System.out.println("Data is not updated");
			}
			conn.close();
			pmst.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static void DeletebyEmail() {
		try {
			Scanner sc = new Scanner(System.in);
			Class.forName(driver);
			System.out.println("enter database name:");
			String url = "jdbc:mysql://localhost:3306/" + sc.next();
			conn = DriverManager.getConnection(url, username, password);
			System.out.println("Enter table name:");
			String sql = "delete from " + sc.next() + "where order_id = ?";
			pmst = conn.prepareStatement(sql);
			System.out.println("Enter Order ID:");
			pmst.setLong(1, sc.nextLong());
			int i = pmst.executeUpdate();
			if (i > 0) {
				System.out.println("Data inserted successfully");
			} else {
				System.out.println("Data is not inserted");
			}
			conn.close();
			pmst.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static void DataInsertion() {
		try {
			Scanner sc = new Scanner(System.in);
			Class.forName(driver);
			System.out.println("enter database name:");
			String url = "jdbc:mysql://localhost:3306/" + sc.next();
			conn = DriverManager.getConnection(url, username, password);
			System.out.println("Enter table name:");
			String sql = "insert into " + sc.next()
					+ "(order_id,order_name,order_pincode,order_address) values(?,?,?,?)";
			pmst = conn.prepareStatement(sql);
			System.out.println("Enter Order ID:");
			pmst.setLong(1, sc.nextLong());
			System.out.println("Enter Order Name:");
			pmst.setString(2, sc.next());
			System.out.println("Enter Order Pincode:");
			pmst.setInt(3, sc.nextInt());
			System.out.println("enter Order Address:");
			pmst.setString(4, sc.next());
			int i = pmst.executeUpdate();
			if (i > 0) {
				System.out.println("Data inserted successfully");
			} else {
				System.out.println("Data is not inserted");
			}
			conn.close();
			pmst.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static void DropDatabase() {
		try {
			Class.forName(driver);
			String url = "jdbc:mysql://localhost:3306/";
			conn = DriverManager.getConnection(url, username, password);
			System.out.println("Enter database name to drop:");
			Scanner sc = new Scanner(System.in);
			String sql = "drop database " + sc.next();
			pmst = conn.prepareStatement(sql);
			int i = pmst.executeUpdate();
			if (i == 0) {
				System.out.println("Database dropped successfully");
			} else {
				System.out.println("Database is not dropped");
			}
			conn.close();
			pmst.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static void CreateDatabase() {
		try {
			Class.forName(driver);
			String url = "jdbc:mysql://localhost:3306/";
			conn = DriverManager.getConnection(url, username, password);
			System.out.println("Enter database name to create:");
			Scanner sc = new Scanner(System.in);
			String sql = "create database " + sc.next();
			pmst = conn.prepareStatement(sql);
			int i = pmst.executeUpdate();
			if (i > 0) {
				System.out.println("Database created successfully");
			} else {
				System.out.println("Database is not created");
			}
			conn.close();
			pmst.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static void displaymenu() {
		System.out.println("\t1.Create database");
		System.out.println("\t2.Drop database");
		System.out.println("\t3.Data insertion");
		System.out.println("\t4.Delete by email");
		System.out.println("\t5.Update data");
		System.out.println("\t6.Get by email");
		System.out.println("\t7.Get all");
		System.out.println("\t8.Login");
		System.out.println("\t9.Exit");
	}
}
