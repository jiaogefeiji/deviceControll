package dbtools;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * 数据库连接类
 * 
 * @author ws
 * 
 */
public class GetConnection {

	// 单线程对象
	private static ThreadLocal tl = new ThreadLocal();

	/**
	 * 获取数据库连接的函数
	 * 
	 * @return 返回数据库单线程连接
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static Connection getConnection() throws ClassNotFoundException,
			SQLException {

		Connection connection = null;

		if (tl.get() == null) {
			// 获取数据库连接，设置自动提交为false
			connection = GetConnectionType.getConnectionFromJDBC();
			connection.setAutoCommit(false);
			// 设置单线程对象值为数据库连接
			tl.set(connection);
			System.out.println("   当前数据库连接的hashcode=" + connection.hashCode());
		} else {
			// 当单线程对象不为null时，获取单线的值转换为数据库连接对象
			Object object = tl.get();
			connection = (Connection) object;
			connection.setAutoCommit(false);
			System.out.println("当前数据库连接的 hashcode=" + connection.hashCode());
		}
		// 返回数据库连接
		return connection;
	}

	/**
	 * 数据库提交函数
	 */
	public static void commit() {
		try {
			// 获取单线程数据库连接，提交并关闭
			((Connection) tl.get()).commit();
			((Connection) tl.get()).close();
			tl.set(null);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 数据库连接回滚函数
	 */
	public static void rollback() {
		try {
			((Connection) tl.get()).rollback();
			((Connection) tl.get()).close();
			tl.set(null);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
