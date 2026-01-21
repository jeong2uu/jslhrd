package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import util.DBManager;

public class MemberDao {
	
	//아이디 중복체크
	public int userIdCheck(String userid) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select * from member where userid = ?";
		int result = 0;
		try {
			conn = DBManager.getInstance();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,userid);
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				result= 1; //아이디존재
			}else {
				result= -1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return result;
	}
	
	//회원가입 저장 메서드
	public void memberSave(MemberDto dto) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql = "insert into member (writer,userid, password, phone,email) values (?,?,?,?,?)";
		int result = 0;
		try {
			conn = DBManager.getInstance();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getWriter());
			pstmt.setString(2,  dto.getUserid());
			// password가 null일 수 있음 (구글 로그인)
			if (dto.getPassword() == null || dto.getPassword().isEmpty()) {
				pstmt.setNull(3, java.sql.Types.VARCHAR);
			} else {
				pstmt.setString(3, dto.getPassword());
			}
			pstmt.setString(4, dto.getPhone());
			pstmt.setString(5, dto.getEmail());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

	//아이디존재여부
	public MemberDto searchUserId(String userid) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select * from member where userid = ?";
		MemberDto dto = null;
		try {
			conn = DBManager.getInstance();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,userid);
			rs=pstmt.executeQuery();
			
		if(rs.next()) {
			dto = new MemberDto();
			dto.setWriter(rs.getString("writer"));
			dto.setUserid(rs.getString("userid"));
			dto.setPassword(rs.getString("password"));
			dto.setPhone(rs.getString("phone"));
			dto.setEmail(rs.getString("email"));
		}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return dto;
	}

	// 이메일로 회원 찾기 (구글 로그인용) - 추가
	public MemberDto searchUserByEmail(String email) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select * from member where email = ?";
		MemberDto dto = null;
		try {
			conn = DBManager.getInstance();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, email);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				dto = new MemberDto();
				dto.setWriter(rs.getString("writer"));
				dto.setUserid(rs.getString("userid"));
				dto.setPassword(rs.getString("password"));
				dto.setPhone(rs.getString("phone"));
				dto.setEmail(rs.getString("email"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return dto;
	}
}