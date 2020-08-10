package com.study.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.study.exception.DaoDuplicateKeyException;
import com.study.exception.DaoException;
import com.study.member.vo.MemberVO;

public class MemberDaoOracle implements IMemberDao {

	@Override
	public int insertMember(Connection conn, MemberVO member) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuffer sb = new StringBuffer();
		/* List<MemberVO> list = new ArrayList<MemberVO>(); */

		try {
			sb.append("INSERT INTO member (                           ");
			sb.append("	      mem_id     , mem_pass   , mem_name      ");
			sb.append("	    , mem_bir    , mem_zip    , mem_add1      ");
			sb.append("	    , mem_add2   , mem_hp     , mem_mail      ");
			sb.append("	    , mem_job    , mem_like   , mem_mileage   ");
			sb.append("	    , mem_delete                              ");
			sb.append("	) VALUES (                                    ");
			sb.append(" ?, ?, ?");
			sb.append(" ,?, ?, ?");
			sb.append(" ,?, ?, ?");
			sb.append(" ,?, ?, 0");
			sb.append(" ,'N' ");
			sb.append("	)		                                          ");

			pstmt = conn.prepareStatement(sb.toString());
			// 구문 실행 전에 파라미터 설정
			int i = 1;
			pstmt.setString(i++, member.getMemId());
			pstmt.setString(i++, member.getMemPass());
			pstmt.setString(i++, member.getMemName());
			pstmt.setString(i++, member.getMemBir());
			pstmt.setString(i++, member.getMemZip());
			pstmt.setString(i++, member.getMemAdd1());
			pstmt.setString(i++, member.getMemAdd2());
			pstmt.setString(i++, member.getMemHp());
			pstmt.setString(i++, member.getMemMail());
			pstmt.setString(i++, member.getMemJob());
			pstmt.setString(i++, member.getMemLike());
			
			
			int cnt = pstmt.executeUpdate();
			return cnt;
			
		} catch (SQLException e) {
			if(e.getErrorCode()==1) { //unique 에러일 경우
				throw new DaoDuplicateKeyException("등록된 코드 발생 = ["+member.getMemId()+"]");
			}
			throw new DaoException(e.getMessage(), e);
			
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException e) {
				} // catch
		} // funally
	}

	@Override
	public int updateMember(Connection conn, MemberVO member) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuffer sb = new StringBuffer();
		/* List<MemberVO> list = new ArrayList<MemberVO>(); */

		try {
			sb.append("UPDATE member ");
			sb.append("	SET mem_pass = ?        ");
			sb.append("	  , mem_name = ?        ");
			sb.append("	  , mem_zip = ?        ");
			sb.append("	  , mem_add1 = ?        ");
			sb.append("	  , mem_add2 = ?        ");
			sb.append("	  , mem_bir = ?        ");
			sb.append("	  , mem_mail = ?        ");
			sb.append("	  , mem_hp = ?        ");
			sb.append("	  , mem_job = ?        ");
			sb.append("	  , mem_like = ?        ");
			sb.append(" WHERE mem_id =?           ");
			
			System.out.println(sb.toString().replace("\\s{2,}", ""));
			
			pstmt = conn.prepareStatement(sb.toString());
			// 바인드 변수 설정
			int i = 1;
			pstmt.setString(i++, member.getMemPass());
			pstmt.setString(i++, member.getMemName());
			pstmt.setString(i++, member.getMemZip());
			pstmt.setString(i++, member.getMemAdd1());
			pstmt.setString(i++, member.getMemAdd2());
			pstmt.setString(i++, member.getMemBir());
			pstmt.setString(i++, member.getMemMail());
			pstmt.setString(i++, member.getMemHp());
			pstmt.setString(i++, member.getMemJob());
			pstmt.setString(i++, member.getMemLike());
			pstmt.setString(i++, member.getMemId());
			
			int cnt = pstmt.executeUpdate();
			return cnt;
			
		} catch (SQLException e) {
			throw new DaoException(e.getMessage(), e);
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException e) {
				} // catch
		} // funally
	
	}

	@Override
	public int deleteMember(Connection conn, MemberVO member) {
		return 0;
	}

	@Override
	public MemberVO getMember(Connection conn, String memId) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuffer sb = new StringBuffer();
		/* List<MemberVO> list = new ArrayList<MemberVO>(); */

		try {
			sb.append("SELECT mem_id    , mem_pass  , mem_name         ");
			sb.append("	  , TO_CHAR(mem_bir,'YYYY-MM-DD') AS mem_bir ");
		 	sb.append("	  , mem_zip     , mem_add1                  ");
			sb.append("     , mem_add2  , mem_hp    , mem_mail        ");
			sb.append("     , mem_job                                 ");
			sb.append("     , (select comm_nm FROM comm_code WHERE comm_cd = mem_job) AS mem_job_nm ");
			sb.append("     , mem_like                                                              ");
			sb.append("     , (select comm_nm FROM comm_code WHERE comm_cd = mem_like) AS mem_like_nm ");
			sb.append("     , mem_mileage                                                           ");
			sb.append("     , mem_delete                                                             ");
			sb.append("  FROM member                                                                ");
			sb.append(" WHERE mem_id = ?                                                            ");
			
			System.out.println(sb.toString().replace("\\s{2,}", ""));
			pstmt = conn.prepareStatement(sb.toString());
			
			// 바인드 변수 설정
			pstmt.setString(1,memId );
			rs = pstmt.executeQuery();
			MemberVO member = null; // new MemberVO(); 나중에 문제가 되는 코드(주소지가 같아서 한 사람만 목록에 나옴)
			if (rs.next()) {
				member = new MemberVO(); // 여기에 new MemberVO를 해야 여러사람이 나옴
				member.setMemId(rs.getString("mem_id"));
				member.setMemName(rs.getString("mem_name"));
				member.setMemPass(rs.getString("mem_pass"));
				member.setMemBir(rs.getString("mem_bir"));
				member.setMemZip(rs.getString("mem_zip"));
				member.setMemAdd1(rs.getString("mem_add1"));
				member.setMemAdd2(rs.getString("mem_add2"));
				member.setMemHp(rs.getString("mem_hp"));
				member.setMemMail(rs.getString("mem_Mail"));
				member.setMemJob(rs.getString("mem_job"));
				member.setMemJobNm(rs.getString("mem_job_nm"));
				member.setMemLike(rs.getString("mem_like"));
				member.setMemLikeNm(rs.getString("mem_like_nm"));
				member.setMemMileage(rs.getInt("mem_mileage"));
				member.setMemDelete(rs.getString("mem_delete"));

			} // if
			return member;
		} catch (SQLException e) {
			throw new DaoException(e.getMessage(), e);
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException e) {
				} // catch
		} // funally
	}

	@Override
	public MemberVO getMemberList(Connection conn, String memId) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuffer sb = new StringBuffer();
		/* List<MemberVO> list = new ArrayList<MemberVO>(); */

		try {
			sb.append("SELECT mem_id    , mem_pass  , mem_name         ");
			sb.append("	  , TO_CHAR(mem_bir,'YYYY-MM-DD') AS mem_bir ");
		 	sb.append("	  , mem_zip     , mem_add1                  ");
			sb.append("     , mem_add2  , mem_hp    , mem_mail        ");
			sb.append("     , mem_job                                 ");
			sb.append("     , (select comm_nm FROM comm_code WHERE comm_cd = mem_job) AS mem_job_nm ");
			sb.append("     , mem_like                                                              ");
			sb.append("     , (select comm_nm FROM comm_code WHERE comm_cd = mem_like) AS mem_like_nm ");
			sb.append("     , mem_mileage                                                           ");
			sb.append("     , mem_delete                                                             ");
			sb.append("  FROM member                                                                ");
			sb.append(" WHERE mem_id = ?                                                            ");
			
			System.out.println(sb.toString().replace("\\s{2,}", ""));
			pstmt = conn.prepareStatement(sb.toString());
			
			// 바인드 변수 설정
			pstmt.setString(1,memId );
			rs = pstmt.executeQuery();
			MemberVO member = null; // new MemberVO(); 나중에 문제가 되는 코드(주소지가 같아서 한 사람만 목록에 나옴)
			if (rs.next()) {
				member = new MemberVO(); // 여기에 new MemberVO를 해야 여러사람이 나옴
				member.setMemId(rs.getString("mem_id"));
				member.setMemName(rs.getString("mem_name"));
				member.setMemPass(rs.getString("mem_pass"));
				member.setMemBir(rs.getString("mem_bir"));
				member.setMemZip(rs.getString("mem_zip"));
				member.setMemAdd1(rs.getString("mem_add1"));
				member.setMemAdd2(rs.getString("mem_add2"));
				member.setMemHp(rs.getString("mem_hp"));
				member.setMemMail(rs.getString("mem_Mail"));
				member.setMemJob(rs.getString("mem_job"));
				member.setMemJobNm(rs.getString("mem_job_nm"));
				member.setMemLike(rs.getString("mem_like"));
				member.setMemLikeNm(rs.getString("mem_like_nm"));
				member.setMemMileage(rs.getInt("mem_mileage"));
				member.setMemDelete(rs.getString("mem_delete"));

			} // if
			return member;
		} catch (SQLException e) {
			throw new DaoException(e.getMessage(), e);
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException e) {
				} // catch
		} // funally
	}

	@Override
	public List<MemberVO> getMemberList(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuffer sb = new StringBuffer();
		List<MemberVO> list = new ArrayList<MemberVO>();

		try {
			sb.append("SELECT mem_id    , mem_pass  , mem_name         ");
			sb.append("	  , TO_CHAR(mem_bir,'YYYY-MM-DD') AS mem_bir ");
		 	sb.append("	  , mem_zip     , mem_add1                   ");
			sb.append("     , mem_add2  , mem_hp    , mem_mail         ");
			sb.append("     , mem_job                                  ");
			sb.append("     , b.comm_nm AS mem_job_nm				      ");
			sb.append("     , mem_like               		             ");
			sb.append("     , c.comm_nm AS mem_like_nm					   ");
			sb.append("     , mem_mileage                              ");
			sb.append("     , mem_delete                               ");
			sb.append("  FROM member , comm_code b, comm_code c        ");
			sb.append(" WHERE mem_job = b.comm_cd                    ");
			sb.append("   AND mem_like = c.comm_cd                   ");

			pstmt = conn.prepareStatement(sb.toString());
			rs = pstmt.executeQuery();
			MemberVO member = null; // new MemberVO(); 나중에 문제가 되는 코드(주소지가 같아서 한 사람만 목록에 나옴)
			while (rs.next()) {
				member = new MemberVO(); // 여기에 new MemberVO를 해야 여러사람이 나옴
				member.setMemId(rs.getString("mem_id"));
				member.setMemName(rs.getString("mem_name"));
				member.setMemPass(rs.getString("mem_pass"));
				member.setMemBir(rs.getString("mem_bir"));
				member.setMemZip(rs.getString("mem_zip"));
				member.setMemAdd1(rs.getString("mem_add1"));
				member.setMemAdd2(rs.getString("mem_add2"));
				member.setMemHp(rs.getString("mem_hp"));
				//member.setMemMail(rs.getString("mem_Mail"));
				member.setMemJob(rs.getString("mem_job"));
				member.setMemJobNm(rs.getString("mem_job_nm"));
				member.setMemLike(rs.getString("mem_like"));
				member.setMemLikeNm(rs.getString("mem_like_nm"));
				member.setMemMileage(rs.getInt("mem_mileage"));
				//member.setMemDelete(rs.getInt("mem_delete"));
				list.add(member);
			} // while
			return list;
		} catch (SQLException e) {
			throw new DaoException(e.getMessage(), e);
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException e) {
				} // catch
		} // funally
	}// getMemberList
}// class
