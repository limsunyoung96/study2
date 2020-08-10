package com.study.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.study.exception.DaoException;
import com.study.member.vo.MemberVO;

public class MemberDaoOracle implements IMemberDao {

	@Override
	public int insertMember(Connection conn, MemberVO member) {
		return 0;
	}

	@Override
	public int updateMember(Connection conn, MemberVO member) {
		return 0;
	}

	@Override
	public int deleteMember(Connection conn, MemberVO member) {
		return 0;
	}

	@Override
	public MemberVO getMember(Connection conn, String memId) {
		return null;
	}

	@Override
	public List<MemberVO> getMemberList(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuffer sb = new StringBuffer();
		List<MemberVO> list = new ArrayList<MemberVO>();

		try {
			sb.append("SELECT *");
			sb.append("  FROM member");

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
				member.setMemJob(rs.getString("mem_job"));
				member.setMemLike(rs.getString("mem_like"));
				member.setMemMileage(rs.getInt("mem_mileage"));
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
