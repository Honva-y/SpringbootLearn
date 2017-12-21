package com.honva.common;

import redis.clients.jedis.Jedis;

public class RedisTools {
	private static final String LOCK_SUCCESS = "OK";
	private static final String SET_IF_NOT_EXIST = "NX";
	private static final String SET_WITH_EXPIRE_TIME = "PX";
	private static final Long RELEASE_SUCCESS = 1l;

	/**
	 * @Title:redis分布式锁
	 * @param jedis
	 * @param locakKey
	 *            锁，作为key
	 * @param requestId
	 *            请求标志，表示哪个请求加的锁
	 * @param expireTime
	 *            过期时间
	 * @return boolean 是否成功
	 */
	public static boolean tryGetDistributedLock(Jedis jedis, String locakKey, String requestId, int expireTime) {
		String result = jedis.set(locakKey, requestId, SET_IF_NOT_EXIST, SET_WITH_EXPIRE_TIME, expireTime);
		if (LOCK_SUCCESS.equals(result)) {
			return true;
		}
		return false;

	}
	/**
	 * @Title:
	 * @param jedis
	 * @param lockKey
	 * @param requestId
	 * @return
	 */
	public static boolean releaseDistributeLock(Jedis jedis,String lockKey,String requestId){
		if(requestId.equals(jedis.get(lockKey))){
			jedis.del(lockKey);
			return true;
		}
		return false;
	}
	
}
