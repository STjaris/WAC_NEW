package nl.hu.v1wac.firstapp.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserPostgresdaoImpl extends PostgresBaseDao implements UserDao {

	@Override
	public String findRoleForUser(String name, String pass) {

		String naam = "";
		String role = "";

		try (Connection conn = super.getConnection()) {

			String query = "SELECT role FROM useraccount WHERE username = ? AND password = ?";

			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1, name);
			pstmt.setString(2, pass);

			System.out.println(query);
			ResultSet rs = pstmt.executeQuery();
			System.out.println(pstmt);
			System.out.println(name);
			System.out.println(pass);
			while(rs.next()) {
				
				role = rs.getString("role");
				System.out.println(">>" + role);
			}
			System.out.println(">>>"+role);

		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return role;
	}

}
