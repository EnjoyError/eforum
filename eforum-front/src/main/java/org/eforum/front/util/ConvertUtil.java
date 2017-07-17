package org.eforum.front.util;

import java.util.ArrayList;
import java.util.List;

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
		return (T) entity;
	}

	public static final <T> T convertEntityToVo(BaseEntity entity, Class<T> clazz) {
		T vo = null;
		try {
			vo = clazz.newInstance();
			BeanUtils.copyProperties(vo, entity);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return vo;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static final List convertEntityToVo(List entitys, Class clazz) {
		List list = new ArrayList();
		for (BaseEntity entity : (List<BaseEntity>) entitys) {
			list.add(convertEntityToVo(entity, clazz));
		}
		return list;
	}
}
