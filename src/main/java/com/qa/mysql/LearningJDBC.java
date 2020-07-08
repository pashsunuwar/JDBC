package com.qa.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LearningJDBC {

	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";

	// Local host on my machine
	static final String DB_URL = "jdbc:mysql://localhost:3306/game_database?serverTimezone=UTC";

	private String jdbcConnectionURL;
	private String username = "root";
	private String password = "root";

	public LearningJDBC(String username, String password) {
		jdbcConnectionURL = "jdbc:mysql://localhost:3306/game_database?serverTimezone=UTC";
		this.username = username;
		this.password = password;
	}

	public LearningJDBC(String jdbcConnectionURL, String username, String password) {
		this.jdbcConnectionURL = jdbcConnectionURL;
		this.username = username;
		this.password = password;
	}

	public void OpeningTheConnecton() {
		Connection conn = null;
		Statement statement = null;
		try {
			// STEP 2: Register JDBC driver
			Class.forName(JDBC_DRIVER);

			// Open connection
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(jdbcConnectionURL, username, password);

		} catch (SQLException e) {
			e.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}

	}

	public void Create() {
		try {

			Connection conn = DriverManager.getConnection(jdbcConnectionURL, username, password);
			Statement statement = conn.createStatement();

			// turns out your have to insert the whole row of CUST DETAILS
			// not just the first two (for e.g. name, address) ((WASTED 3HRS ON THIS...))
			statement.executeUpdate("INSERT INTO customers(name, address, email, password, city) "
					+ "VALUES('Funtz', '9 Ridge Road','funtz@gmail.com', 'funtu', 'New York')");

			conn.close();
			statement.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void Read() {
		try {

			Connection conn = DriverManager.getConnection(jdbcConnectionURL, username, password);
			Statement statement = conn.createStatement();
			System.out.println("Creating statement...");

			String sql;
			sql = "SELECT * FROM customers";
			ResultSet rs = statement.executeQuery(sql);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void Update() {
		try {

			Connection conn = DriverManager.getConnection(jdbcConnectionURL, username, password);
			Statement statement = conn.createStatement();
			System.out.println("Updating statement...");

			statement.executeUpdate("UPDATE customers SET name = 'JAVAisFUN' " + "WHERE customer_id = '7'");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void Delete() {
		try {
			Connection conn = DriverManager.getConnection(jdbcConnectionURL, username, password);
			Statement statement = conn.createStatement();
			System.out.println("Deleting statement...");

			String sql;
			statement.executeUpdate("Delete FROM customers WHERE customer_id = '26'");
			sql = "SELECT * FROM customers";
			ResultSet rs = statement.executeQuery(sql);

			// Extract data from result set
			while (rs.next()) {
				// Retrieve by column name
				int id = rs.getInt("customer_id");
				String name = rs.getString("name");
				String address = rs.getString("address");

				// Display the values
				// System.out.println("Fetching info...");
				System.out.println("Id = " + id + "  --  Name = " + name + "  --  Address = " + address);
			}

			rs.close();
			statement.close();
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		// end of CRUD

		System.out.println("Goodbye!");

	}

}
