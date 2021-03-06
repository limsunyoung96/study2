package com.study.code.dao;

import java.sql.Connection;
import java.util.List;

import com.study.code.vo.CodeVO;
import com.study.exception.DaoException;

public interface ICommonCodeDao {
	/**
	 * <b>공통코드에 조회목록을 리턴한다.</b>
	 *  
	 * @param conn
	 * @param code
	 * @return 코드목록 List<CodeVO>
	 * @throws DaoException
	 */
	public List<CodeVO> getCodeListByParent(Connection conn, String parentCode);
	
}
