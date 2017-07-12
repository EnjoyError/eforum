package org.eforum.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.eforum.entity.BaseEntity;
import org.eforum.exception.ServiceException;
import org.eforum.repository.CommonDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@SuppressWarnings({ "unchecked", "rawtypes" })
@Repository
public class CommonDaoImpl implements CommonDao {
	@Autowired
	private EntityManager entityManager;

	@Override
	public BaseEntity get(Class clazz, Long id) {
		return (BaseEntity) entityManager.find(clazz, id);
	}

	@Override
	public void save(BaseEntity entity) {
		entityManager.persist(entity);
	}

	@Override
	public List<BaseEntity> findByHql(String hql, String paramKey, Object paramValue) {
		Query query = entityManager.createQuery(hql);
		query.setParameter(paramKey, paramValue);
		return query.getResultList();
	}

	@Override
	public BaseEntity findUniqueByHql(String hql, String paramKey, Object paramValue) {
		List<BaseEntity> entitys = findByHql(hql, paramKey, paramValue);
		if (null == entitys || entitys.size() < 1) {
			throw new ServiceException("查询结果为空");
		}
		if (entitys.size() > 1) {
			throw new ServiceException("查询结果不唯一");
		}
		return entitys.get(0);
	}

}
