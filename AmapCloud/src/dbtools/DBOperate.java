package dbtools;

import java.lang.reflect.InvocationTargetException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

/**
 * 
 * @author ws
 * 
 */
public class DBOperate {

	/**
	 * jdbc鏌ヨ灏佽鍑芥暟
	 * 
	 * @param selectSql
	 *            鏌ヨ璇彞
	 * @param list
	 *            杞箟瀛楃鍙傛暟闆嗗悎
	 * @return 杩斿洖涓簂ist闆嗗悎
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public List select(String selectSql, List list) throws SQLException,
			ClassNotFoundException {
		List tolist = new ArrayList();
		PreparedStatement ps = GetConnection.getConnection().prepareStatement(
				selectSql);
		// /鍒ゆ柇锛燂紵
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				ps.setObject(i + 1, list.get(i));
			}
		}
		ResultSet set = ps.executeQuery();
		while (set.next()) {
			// /鑾峰緱鍒楁暟
			int colCount = set.getMetaData().getColumnCount();
			HashMap rowMap = new HashMap();
			for (int i = 0; i < colCount; i++) {
				String colName = set.getMetaData().getColumnName(i + 1);
				String colValue = set.getObject(i + 1) + "";
				rowMap.put(colName.toLowerCase(), colValue);
			}
			tolist.add(rowMap);
		}
		set.close();
		ps.close();
		return tolist;
	}

	/**
	 * jdbc鏌ヨ鍑芥暟
	 * 
	 * @param selectSql
	 *            鏌ヨsql
	 * @param list
	 *            杞箟瀛楃鍙傛暟闆嗗悎
	 * @param firstSize
	 *            缈婚〉鐨勮捣濮嬫潯鐩�
	 * @param maxSize
	 *            缈婚〉鐨勬�鏉＄洰
	 * @return 杩斿洖list闆嗗悎
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public List select(String selectSql, List list, int firstSize, int maxSize)
			throws SQLException, ClassNotFoundException {
		List tolist = new ArrayList();
		PreparedStatement ps = GetConnection.getConnection().prepareStatement(
				selectSql);
		// 鏈�ぇ鏉℃暟
		ps.setMaxRows(maxSize);
		// /鍒ゆ柇锛燂紵
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				ps.setObject(i + 1, list.get(i));
			}
		}
		ResultSet set = ps.executeQuery();
		// 浠庣鍑犳潯寮�
		set.relative(firstSize);
		while (set.next()) {
			// /鑾峰緱鍒楁暟
			int colCount = set.getMetaData().getColumnCount();
			HashMap rowMap = new HashMap();
			for (int i = 0; i < colCount; i++) {
				String colName = set.getMetaData().getColumnName(i + 1);
				String colValue = set.getObject(i + 1) + "";
				rowMap.put(colName.toLowerCase(), colValue);
			}
			tolist.add(rowMap);
		}
		set.close();
		ps.close();
		return tolist;
	}

	/**
	 * jdbc鏌ヨ灏佽鍑芥暟
	 * 
	 * @param selectSql
	 *            鏌ヨsql
	 * @param list
	 *            杞箟瀛楃鍙傛暟闆嗗悎
	 * @param obj
	 *            娉涘瀷闆嗗悎绫诲瀷
	 * @return 杩斿洖娉涘瀷list闆嗗悎
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws InstantiationException 
	 */
	public List<Object> selectListObj(String selectSql, List list, Object obj)
			throws SQLException, ClassNotFoundException,
			IllegalAccessException, InvocationTargetException, InstantiationException {
		List<Object> tolist = new ArrayList<Object>();
		PreparedStatement ps = GetConnection.getConnection().prepareStatement(
				selectSql);
		// /鍒ゆ柇锛燂紵
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				ps.setObject(i + 1, list.get(i));
			}
		}
		ResultSet set = ps.executeQuery();
		while (set.next()) {
			obj = obj.getClass().newInstance();
			// /鑾峰緱鍒楁暟
			int colCount = set.getMetaData().getColumnCount();
			for (int i = 0; i < colCount; i++) {
				String colName = set.getMetaData().getColumnName(i + 1);
				String colValue = set.getObject(i + 1) + "";
				BeanUtils.setProperty(obj, colName.toLowerCase(), colValue);
			}
			tolist.add(obj);
		}
		set.close();
		ps.close();
		return tolist;
	}

	/**
	 * jdbc鏌ヨ灏佽鍑芥暟
	 * 
	 * @param selectSql
	 *            鏌ヨsql
	 * @param list
	 *            杞箟瀛楃鍙傛暟闆嗗悎
	 * @param obj
	 *            娉涘瀷闆嗗悎绫诲瀷
	 * @param firstSize
	 *            缈婚〉鐨勮捣濮嬫潯鐩�
	 * @param maxSize
	 *            缈婚〉鐨勬�鏉＄洰
	 * @return 杩斿洖娉涘瀷list闆嗗悎
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	public List<Object> selectListObj(String selectSql, List list, Object obj,
			int firstSize, int maxSize) throws SQLException,
			ClassNotFoundException, IllegalAccessException,
			InvocationTargetException {
		List<Object> tolist = new ArrayList<Object>();
		PreparedStatement ps = GetConnection.getConnection().prepareStatement(
				selectSql);
		// 鏈�ぇ鏉℃暟
		ps.setMaxRows(maxSize);
		// /鍒ゆ柇锛燂紵
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				ps.setObject(i + 1, list.get(i));
			}
		}
		ResultSet set = ps.executeQuery();
		// 浠庣鍑犳潯寮�
		set.relative(firstSize);
		while (set.next()) {
			// /鑾峰緱鍒楁暟
			int colCount = set.getMetaData().getColumnCount();
			for (int i = 0; i < colCount; i++) {
				String colName = set.getMetaData().getColumnName(i + 1);
				String colValue = set.getObject(i + 1) + "";
				BeanUtils.setProperty(obj, colName.toLowerCase(), colValue);
			}
			tolist.add(obj);
		}
		set.close();
		ps.close();
		return tolist;
	}

	/**
	 * jdbc鏇存柊鍑芥暟
	 * 
	 * @param updateSql
	 *            鏇存柊sql
	 * @param list
	 *            杞箟瀛楃鍙傛暟闆嗗悎
	 * @return 杩斿洖鎵ц缁撴灉1涓烘垚鍔�涓哄け璐�
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public int update(String updateSql, List list) throws SQLException,
			ClassNotFoundException {
		List tolist = new ArrayList();
		PreparedStatement ps = GetConnection.getConnection().prepareStatement(
				updateSql);
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				ps.setObject(i + 1, list.get(i));
			}
		}
		int count = ps.executeUpdate(updateSql);
		ps.getConnection().commit();
		ps.close();
		return count;
	}

	/**
	 * jdbc鎻掑叆鍑芥暟
	 * 
	 * @param selectSQL
	 *            鎻掑叆sql
	 * @param paramList
	 *            鍙傛暟闆嗗悎
	 * @return 杩斿洖鎵ц缁撴灉1涓烘垚鍔�涓哄け璐�
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public int Insert(String selectSQL, ArrayList paramList)
			throws SQLException, ClassNotFoundException {
		int count = 0;
		List returnList = new ArrayList();

		PreparedStatement ps = GetConnection.getConnection().prepareStatement(
				selectSQL);
		if (paramList != null && paramList.size() > 0) {
			for (int i = 0; i < paramList.size(); i++) {
				ps.setObject(i + 1, paramList.get(i));
			}
		}
		count = ps.executeUpdate();
		GetConnection.getConnection().commit();
		ps.close();
		return count;

	}
}
