package com.example.practisejdbc;
import java.sql.*;

public class DBConnection {

	public static void main(String[] args) throws Exception {
		
	
	Connection c=DriverManager.getConnection("jdbc:mysql://localhost:3306/JDBC","root","Mani230252@#");
		String s="Select name from mani where id=1";
		String s2="INSERT INTO mani VALUES ('Sani',2,22)";
		Statement st=c.createStatement();
		st.executeQuery(s);
		st.executeUpdate(s2);

		
			PreparedStatement p=c.prepareStatement("INSERT INTO mani VALUES (?,?,?)");
			p.setString(1, "Tanish");
			p.setInt(2, 3);
			p.setInt(3,21);
			p.execute();

	}

}
