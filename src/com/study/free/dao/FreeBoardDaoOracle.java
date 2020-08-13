package com.study.free.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.study.exception.DaoDuplicateKeyException;
import com.study.exception.DaoException;
import com.study.free.vo.FreeBoardVO;
import com.study.member.vo.MemberVO;

public class FreeBoardDaoOracle implements IFreeBoardDao {

	@Override
	public List<FreeBoardVO> getBoardList(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuffer sb = new StringBuffer();
		List<FreeBoardVO> list = new ArrayList<FreeBoardVO>();

		try {
			sb.append("SELECT    to_char(bo_no) as bo_no    , bo_title    , bo_category, (select comm_nm FROM comm_code WHERE comm_cd = bo_category) AS bo_category_nm   ");
			sb.append("        , bo_writer    , bo_pass    , bo_content ");
		 	sb.append("        , bo_ip    , to_char(bo_hit) as bo_hit    , to_char(bo_reg_date, 'YYYY-MM-DD') as bo_reg_date     ");
			sb.append("        , bo_mod_date    , bo_del_yn             ");
			sb.append("  FROM    free_board                             ");
			

			pstmt = conn.prepareStatement(sb.toString());
			rs = pstmt.executeQuery();
			FreeBoardVO freeboard = null;
			
			while (rs.next()) {
				freeboard = new FreeBoardVO(); // 여기에 new MemberVO를 해야 여러사람이 나옴
				freeboard.setBoNo(rs.getInt("bo_no"));
				freeboard.setBoTitle(rs.getString("bo_title"));
				freeboard.setBoCategory(rs.getString("bo_category"));
				freeboard.setBoCategoryNm(rs.getString("bo_category_nm"));
				freeboard.setBoWriter(rs.getString("bo_writer"));
				freeboard.setBoPass(rs.getString("bo_pass"));
				freeboard.setBoContent(rs.getString("bo_content"));
				freeboard.setBoIp(rs.getString("bo_ip"));
				freeboard.setBoHit(rs.getInt("bo_hit"));
				freeboard.setBoRegDate(rs.getString("bo_reg_date"));
				freeboard.setBoModDate(rs.getString("bo_mod_date"));
				freeboard.setBoDelYn(rs.getString("bo_del_yn"));

				list.add(freeboard);
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

	@Override
	public FreeBoardVO getBoard(Connection conn, int boNo) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuffer sb = new StringBuffer();
		/* List<MemberVO> list = new ArrayList<MemberVO>(); */

		try {
			sb.append("SELECT    bo_no    , bo_title    , bo_category, (select comm_nm FROM comm_code WHERE comm_cd = bo_category) AS bo_category_nm   ");
			sb.append("        , bo_writer    , bo_pass    , bo_content ");
		 	sb.append("        , bo_ip    , bo_hit    , to_char(bo_reg_date, 'YYYY-MM-DD') as bo_reg_date     ");
			sb.append("        , bo_mod_date    , bo_del_yn             ");
			sb.append("  FROM    free_board                             ");
			sb.append(" WHERE   bo_no = ?                                                            ");
			
			System.out.println(sb.toString().replace("\\s{2,}", ""));
			pstmt = conn.prepareStatement(sb.toString());
			
			// 바인드 변수 설정
			pstmt.setInt(1, boNo);
			rs = pstmt.executeQuery();
			FreeBoardVO freeboard = null; // new MemberVO(); 나중에 문제가 되는 코드(주소지가 같아서 한 사람만 목록에 나옴)
			if (rs.next()) {
				freeboard = new FreeBoardVO(); // 여기에 new MemberVO를 해야 여러사람이 나옴
				freeboard.setBoNo(rs.getInt("bo_no"));
				freeboard.setBoTitle(rs.getString("bo_title"));
				freeboard.setBoCategory(rs.getString("bo_category"));
				freeboard.setBoCategoryNm(rs.getString("bo_category_nm"));
				freeboard.setBoWriter(rs.getString("bo_writer"));
				freeboard.setBoPass(rs.getString("bo_pass"));
				freeboard.setBoContent(rs.getString("bo_content"));
				freeboard.setBoIp(rs.getString("bo_ip"));
				freeboard.setBoHit(rs.getInt("bo_hit"));
				freeboard.setBoRegDate(rs.getString("bo_reg_date"));
				freeboard.setBoModDate(rs.getString("bo_mod_date"));
				freeboard.setBoDelYn(rs.getString("bo_del_yn"));

			} // if
			return freeboard;
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
	public int insertBoard(Connection conn, FreeBoardVO board) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuffer sb = new StringBuffer();
		
		try {
			sb.append("INSERT INTO free_board (    bo_no      , bo_title  , bo_category     ");
			sb.append("                          , bo_writer  , bo_pass   , bo_content      ");
			sb.append("                          , bo_ip      , bo_hit    , bo_reg_date     ");
			sb.append("                          , bo_del_yn                                ");
			sb.append(") VALUES (    seq_free_board.nextval   ,?          ,?                ");
			sb.append("                          , ?          ,?          ,?                ");
			sb.append("                          , ?          ,0          ,sysdate          ");
			sb.append("                          , 'N' )                                    ");


			pstmt = conn.prepareStatement(sb.toString());
			// 구문 실행 전에 파라미터 설정
			int i = 1;
			pstmt.setString(i++, board.getBoTitle());
			pstmt.setString(i++, board.getBoCategory());
			pstmt.setString(i++, board.getBoWriter());
			pstmt.setString(i++, board.getBoPass());
			pstmt.setString(i++, board.getBoContent());
			pstmt.setString(i++, board.getBoIp());
			
			int cnt = pstmt.executeUpdate();
			return cnt;
			
		} catch (SQLException e) {
			if(e.getErrorCode()==1) { //unique 에러일 경우
				throw new DaoDuplicateKeyException("등록된 코드 발생 = ["+board.getBoNo()+"]");
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
	public int updateBoard(Connection conn, FreeBoardVO board) {
		return 0;
	}

	@Override
	public int deleteBoard(Connection conn, FreeBoardVO board) {
		return 0;
	}

	@Override
	public int increaseHit(Connection conn, int boNo) {
		return 0;
	}

}// class
