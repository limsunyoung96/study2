package com.study.member.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import com.study.exception.BizDuplicateKeyException;
import com.study.exception.BizNotEffectedException;
import com.study.exception.BizNotFoundException;
import com.study.exception.BizPasswordNotMatchedException;
import com.study.exception.DaoDuplicateKeyException;
import com.study.exception.DaoException;
import com.study.free.dao.FreeBoardDaoOracle;
import com.study.free.dao.IFreeBoardDao;
import com.study.free.service.IFreeBoardService;
import com.study.free.vo.FreeBoardVO;
import com.study.member.vo.MemberVO;

public class FreeBoardServiceImpl implements IFreeBoardService  {
	
	private IFreeBoardDao freeBoardDao = new FreeBoardDaoOracle();
	
	@Override
	public List<FreeBoardVO> getBoardList() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection("jdbc:apache:commons:dbcp:study");
			List<FreeBoardVO> list = freeBoardDao.getBoardList(conn);
			return list;
		} catch (SQLException e) {
			throw new DaoException("조회시", e);
		} finally {
			// 자원 종료
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
				}
		}
	}

	@Override
	public FreeBoardVO getBoard(int boNo) throws BizNotFoundException {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection("jdbc:apache:commons:dbcp:study");
			FreeBoardVO vo = freeBoardDao.getBoard(conn, boNo);
			if (vo == null) {
				throw new BizNotFoundException("[" + boNo + "] 조회 실패");
			}
//			System.out.println(vo);
			return vo;
		} catch (SQLException e) {
			throw new DaoException("조회시", e);
		} finally {
			// 자원 종료
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
				}
		}
	}

	@Override
	public void registBoard(FreeBoardVO board) {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection("jdbc:apache:commons:dbcp:study");
			freeBoardDao.insertBoard(conn, board);
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			// 자원 종료
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
				}
		}
	}

	@Override
	public void modifyBoard(FreeBoardVO board)
			throws BizNotFoundException, BizPasswordNotMatchedException, BizNotEffectedException {
		
	}

	@Override
	public void removeBoard(FreeBoardVO board)
			throws BizNotFoundException, BizPasswordNotMatchedException, BizNotEffectedException {
		
	}

	@Override
	public void increaseHit(int boNo) {
		
	}

	

}
