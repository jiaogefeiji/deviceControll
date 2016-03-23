/**   
 * ��һ�仰�������ļ���ʲô.
 * @title MongoDB.java
 * @package com.collection.mongodb.test
 * @author ��˧   
 * @update 2015-11-12 ����2:03:58  
 */
package com.collection.mongodb.test;


/**
 * ������һ�仰��������������.
 * @author ��˧
 */

public class MongoDB {
	private static ThreadLocal tl = new ThreadLocal();

	public static MongoDB instance() {
		MongoDB mongoDB = null;
		if (tl.get() == null) {
			mongoDB = new MongoDB();
			tl.set(mongoDB);
		} else {
			Object object = tl.get();
			mongoDB = (MongoDB) object;
		}
		return mongoDB;
	}
}
