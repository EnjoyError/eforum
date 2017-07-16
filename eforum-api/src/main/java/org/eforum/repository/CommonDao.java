package org.eforum.repository;

import java.util.List;

import org.eforum.entity.BaseEntity;
import org.springframework.transaction.annotation.Transactional;

/**
 * 一些公用操作的dao
 * 
 * @author huxiansheng
 *
 */
public interface CommonDao {
	/**
	 * 根据ID查找entity
	 * 
	 * @param clazz
	 * @param id
	 * @return
	 */
	public <T extends BaseEntity> T get(Class<T> clazz, Long id);

	/**
	 * 保存entity
	 * 
	 * @param entity
	 */
	@Transactional
	public <T extends BaseEntity> void save(T entity);

	/**
	 * 根据hql查找
	 * 
	 * @param hql
	 * @param paramKey
	 * @param paramValue
	 */
	public List<? extends Object> findByHql(String hql, String paramKey, Object paramValue);

	/**
	 * 根据hql查找唯一的结果，如果为空或者多余一条结果，则抛出异常
	 * 
	 * @param hql
	 * @param paramKey
	 * @param paramValue
	 */
	public Object findUniqueByHql(String hql, String paramKey, Object paramValue);
}
