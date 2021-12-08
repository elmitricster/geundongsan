package parser;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class HouseDealDao {

	public void insert(HouseDeal houseDeal) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBConnection.getConnection();
			String sql = "insert into housedeal values(?,?,?,?,?, ?,?,?,?,?, ?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, houseDeal.getNo());
			pstmt.setString(2, houseDeal.getDong());
			pstmt.setString(3, houseDeal.getAptName());
			pstmt.setString(4, houseDeal.getCode());
			pstmt.setString(5, houseDeal.getDealAmount());
			pstmt.setString(6, houseDeal.getBuildYear());
			pstmt.setString(7, houseDeal.getDealYear());
			pstmt.setString(8, houseDeal.getDealMonth());
			pstmt.setString(9, houseDeal.getDealDay());
			pstmt.setString(10, houseDeal.getArea());
			pstmt.setString(11, houseDeal.getFloor());
			pstmt.setString(12, houseDeal.getJibun());
			pstmt.setString(13, houseDeal.getType());
			pstmt.setString(14, houseDeal.getRentMoney());
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
			}
		}
	}
}
