package org.eforum.repository.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.eforum.constant.Constants;
import org.eforum.entity.BaseEntity;
import org.eforum.entity.User;
import org.eforum.exception.ServiceException;
import org.eforum.produces.PageVo;
import org.eforum.repository.CommonDao;
import org.eforum.util.ConvertUtil;
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
		Subject subject = SecurityUtils.getSubject();
		User user = (User) subject.getSession().getAttribute(Constants.CURRENT_USER_IN_SESSION);
		save(entity, user);
	}

	@Override
	public List<? extends Object> findByHql(String hql, String paramKey, Object paramValue) {
		Query query = entityManager.createQuery(hql);
		if(null != paramKey){
			query.setParameter(paramKey, paramValue);
		}
		return query.getResultList();
	}

	@Override
	public Object findUniqueByHql(String hql, String paramKey, Object paramValue) {
		List<Object> entitys = (List<Object>) findByHql(hql, paramKey, paramValue);
		if (entitys.size() > 1) {
			throw new ServiceException("查询结果不唯一");
		}
		if (entitys.size() == 0) {
			return null;
		}
		return entitys.get(0);
	}

	@Override
	public List pagingQuery(String hql, int pageNumber, int pageSize) {
		Query query = entityManager.createQuery(hql);
		query.setFirstResult((pageNumber - 1) * pageSize);
		query.setMaxResults(pageSize);
		query.getResultList();
		return query.getResultList();
	}

	@Override
	public List pagingQuery(Class clazz, int pageNumber, int pageSize) {
		String hql = "FROM " + clazz.getSimpleName() + " obj WHERE 1=1";
		return pagingQuery(hql, pageNumber, pageSize);
	}

	@Override
	public <T extends BaseEntity> T findUniqueByHql(Class<T> clazz, String whereSub, String paramKey,
			Object paramValue) {
		String hql = "FROM " + clazz.getSimpleName() + " obj WHERE " + whereSub;

		return (T) findUniqueByHql(hql, paramKey, paramValue);
	}

	@Override
	public BaseEntity findUniqueByHql(String hql, Map<String, Object> condition) {
		List<? extends BaseEntity> entitys = findByHql(hql, condition);
		if (entitys.size() > 1) {
			throw new ServiceException("查询结果不唯一");
		}
		if (entitys.size() == 0) {
			return null;
		}
		return entitys.get(0);
	}

	@Override
	public List<? extends BaseEntity> findByHql(String hql, Map<String, Object> condition) {
		Query query = entityManager.createQuery(hql);
		for (Entry<String, Object> entry : condition.entrySet()) {
			query.setParameter(entry.getKey(), entry.getValue());
		}
		return query.getResultList();
	}

	@Override
	public List<? extends BaseEntity> findByHql(Class<?> clazz, String whereSub, Map<String, Object> condition) {
		String hql = "FROM " + clazz.getSimpleName() + " obj WHERE " + whereSub;
		return findByHql(hql, condition);
	}

	@Override
	public <T extends BaseEntity> T findUniqueByHql(Class<T> clazz, String whereSub, Map<String, Object> condition) {
		String hql = "FROM " + clazz.getSimpleName() + " obj WHERE " + whereSub;
		return (T) findUniqueByHql(hql, condition);
	}

	@Override
	public <T extends BaseEntity> void save(T entity, User user) {
		Date date = new Date();
		if (entity.isNew()) {
			entity.setCreateTime(date);
			entity.setCreateUserId(user.getId());
			entity.setCreateUserName(user.getName());
		}
		entity.setLastUpdateTime(date);
		entity.setLastUpdateUserId(user.getId());
		entity.setLastUpdateUserName(user.getName());
		entityManager.merge(entity);
	}

	@Override
	public void execute(String hql, Map<String, Object> map) {
		Query query = entityManager.createQuery(hql);
		if (null != map) {
			for (Entry<String, Object> entry : map.entrySet()) {
				query.setParameter(entry.getKey(), entry.getValue());
			}
		}
		query.executeUpdate();
	}

	@Override
	public <T> PageVo<T> pagingQueryAndPackage(Class<T> clazz, int pageNumber, int pageSize) {
		// TODO 待实现
		return null;
	}

	@Override
	public <T> PageVo<T> pagingQueryAndPackage(String hql, int pageNumber, int pageSize, Class<T> voClass) {
		List list = pagingQuery(hql, pageNumber, pageSize);
		String countHql = "SELECT COUNT(*) " + hql;
		Long count = (Long) findUniqueByHql(countHql, null, null);
		long pageCount = (count / pageSize) + ((count % pageSize) == 0 ? 0 : 1);
		PageVo<T> pageVo = new PageVo<T>();
		List<T> vos = ConvertUtil.convertEntityToVo(list, voClass);
		pageVo.setData(vos);
		pageVo.setPageIndex(pageNumber);
		pageVo.setPageSize(pageSize);
		pageVo.setDataCount(count);
		pageVo.setPageCount(pageCount);
		return pageVo;
	}

	public List<? extends BaseEntity> findByHql(Class<?> clazz, String whereSub, String paramKey, Object paramValue) {
		String hql = "FROM " + clazz.getSimpleName() + " obj WHERE " + whereSub;
		List<BaseEntity> entitys = (List<BaseEntity>) findByHql(hql, paramKey, paramValue);
		return entitys;
	}

	@Override
	public <T extends BaseEntity> void delete(T entity) {
		entityManager.remove(entity);
	}
}
