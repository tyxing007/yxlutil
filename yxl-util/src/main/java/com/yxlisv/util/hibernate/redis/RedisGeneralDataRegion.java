package com.yxlisv.util.hibernate.redis;

import org.hibernate.cache.CacheException;
import org.hibernate.cache.spi.GeneralDataRegion;
import org.hibernate.engine.spi.SessionImplementor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yxlisv.util.hibernate.redis.util.KeyUtil;
import com.yxlisv.util.redis.JedisUtil;

/**
 * <p>Hibernate Redis 通用数据区域包装类</p>
 * @author 杨雪令
 * @time 2016年3月22日上午10:02:40
 * @version 1.0
 */
public class RedisGeneralDataRegion extends RedisDataRegion implements GeneralDataRegion {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public Object get(SessionImplementor session, Object key) throws CacheException {
		logger.debug("Redis DataRegion get[key="+ key +"]");
		return JedisUtil.get(KeyUtil.formatKey(key));
	}

	@Override
	public void put(SessionImplementor session, Object key, Object value) throws CacheException {
		logger.debug("Redis DataRegion put[key="+ key +", value="+ value +"]");
		JedisUtil.set(KeyUtil.formatKey(key), value);
	}

	@Override
	public void evict(Object key) throws CacheException {
		logger.debug("Redis DataRegion evict[key="+ key +"]");
		JedisUtil.delete(KeyUtil.formatKey(key));
	}

	@Override
	public void evictAll() throws CacheException {
		logger.warn("Redis DataRegion evictAll");
		JedisUtil.delete(KeyUtil.keyPrefix + "*");
	}
}