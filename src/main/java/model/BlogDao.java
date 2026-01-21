package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import oracle.jdbc.driver.DBConversion;
import util.DBManager;

public class BlogDao {
	
	public void blogInsert(BlogDto dto) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql ="insert into blog (bno,name,title,content,imgfile) "
	            + " values (blog_seq.nextval,?,?,?,?)";
		
		try {
			conn = DBManager.getInstance();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getName());
			pstmt.setString(2, dto.getTitle());
			pstmt.setString(3, dto.getContent());
			pstmt.setString(4, dto.getImgfile());
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
	
	//검색
	public List<BlogDto> getAll() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select * from blog order by bno desc";
		
		//검색된 결과 여러개 리턴 하려면 dto저장하는 가변배열 만든다.
		List<BlogDto> list = new ArrayList<BlogDto>();
		try {
			conn= DBManager.getInstance();
			pstmt=conn.prepareStatement(sql);
			rs= pstmt.executeQuery();
			while(rs.next()) {
				BlogDto dto = new BlogDto();
				dto.setBno(rs.getInt("bno"));
				dto.setName(rs.getString("name"));
				dto.setTitle(rs.getString("title"));
				dto.setContent(rs.getString("content"));
				dto.setImgfile(rs.getString("imgfile"));
				dto.setViews(rs.getInt("views"));
				dto.setRegdate(rs.getString("regdate").substring(0,10));
				list.add(dto);
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
		return list;
	}

	//view(검색)
	public BlogDto getSelectByBno(int bno) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select * from blog where bno=?";
		BlogDto dto = new BlogDto();
		
		try {
			conn= DBManager.getInstance();
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, bno);
			rs= pstmt.executeQuery();
			while(rs.next()) {
				dto.setBno(rs.getInt("bno"));
				dto.setName(rs.getString("name"));
				dto.setTitle(rs.getString("title"));
				dto.setContent(rs.getString("content"));
				dto.setImgfile(rs.getString("imgfile"));
				dto.setViews(rs.getInt("views"));
				dto.setRegdate(rs.getString("regdate").substring(0,10));
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
	
	//조회수 증가
	public void viewCount(int bno) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql = "update blog set views = views + 1 where bno = ?";
		
		try {
			conn= DBManager.getInstance();
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, bno);
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
	

		//삭제
		public void blogDelete(int bno) {
			
			Connection conn = null;
			PreparedStatement pstmt = null;
			
			String sql = "delete blog where bno = ?";
			
			try {
				conn= DBManager.getInstance();
				pstmt=conn.prepareStatement(sql);
				pstmt.setInt(1, bno);
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
		
		
		//수정
//		public void blogUpdate(BlogDto dto) {
//			Connection conn = null;
//			PreparedStatement pstmt = null;
//			String sql = null;
//			
//			if(dto.getImgfile() != null && dto.getImgfile().isEmpty()) {
//				sql = "update blog set title = ?, content=? , imgfile=? "
//						+ "where bno =?";
//			}else {
//				sql = "update blog set title = ?, content=? "
//						+ "where bno =?";
//			}
//			
//			try {
//				conn= DBManager.getInstance();
//				pstmt=conn.prepareStatement(sql);
//				if(dto.getImgfile() != null&& !dto.getImgfile().isEmpty()) {
//					pstmt.setString(1, dto.getTitle());
//					pstmt.setString(2, dto.getContent());
//					pstmt.setString(3, dto.getImgfile());
//					pstmt.setInt(4, dto.getBno());
//				}else {
//				pstmt.setString(1, dto.getTitle());
//				pstmt.setString(2, dto.getContent());
//				pstmt.setInt(3, dto.getBno());
//				}
//				pstmt.executeUpdate();
//			} catch (Exception e) {
//				e.printStackTrace();
//			}finally {
//				try {
//					if(pstmt != null) pstmt.close();
//					if(conn != null) conn.close();
//				} catch (Exception e2) {
//					e2.printStackTrace();
//				}
//			}
//		
//		} //수정
//		
		
		public void blogUpdate(BlogDto dto) {
			Connection conn = null;
			PreparedStatement pstmt = null;
			String sql = null;
			
			sql = "update blog set title = ?, content=? ,imgfile=COALESCE(?,imgfile) "
						+ "where bno = ?";
			//COALESCE(값1, 값2)
			//값1이 null이면 값2 사용, 값1이 있으면 값1사용.
			try {
					conn= DBManager.getInstance();
					pstmt=conn.prepareStatement(sql);
					pstmt.setString(1, dto.getTitle());
					pstmt.setString(2, dto.getContent());
					
					if(dto.getImgfile() != null && !dto.getImgfile().isEmpty()) {
						pstmt.setString(3, dto.getImgfile());  
					} else {
						pstmt.setString(3, null);               
					}
					pstmt.setInt(4, dto.getBno());
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
		
		} //수정



}
