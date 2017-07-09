package org.eforum.front.util;

import org.apache.commons.beanutils.BeanUtils;
import org.eforum.entity.BaseEntity;

/**
 * 将vo转换成entity
 * 
 * @author Sackr
 *
 */
public class ConvertUtil {
	@SuppressWarnings("unchecked")
	public static final <T extends BaseEntity> T convertVoToEntity(Object vo, Class<T> clazz) {
		BaseEntity entity = null;
		try {
			entity = clazz.newInstance();
			BeanUtils.copyProperties(entity, vo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (T)entity;
	}

	public static final <T> T convertEntityToVo(BaseEntity entity, Class<T> clazz) {
		// TODO 待实现
		return null;
	}
}
