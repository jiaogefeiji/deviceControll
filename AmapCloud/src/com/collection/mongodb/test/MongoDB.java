/**   
 * 用一句话描述该文件做什么.
 * @title MongoDB.java
 * @package com.collection.mongodb.test
 * @author 王帅   
 * @update 2015-11-12 下午2:03:58  
 */
package com.collection.mongodb.test;


/**
 * 这里用一句话描述这个类的作用.
 * @author 王帅
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
