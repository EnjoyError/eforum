package org.eforum.front.util;

import org.eforum.entity.BaseEntity;

/**
 * 将vo转换成entity
 * 
 * @author Sackr
 *
 */
public class ConvertUtil {
	public static final <T extends BaseEntity> T convertVoToEntity(Object vo, Class<T> clazz) {
		// TODO 待实现
		return null;
	}

	public static final <T> T convertEntityToVo(BaseEntity entity,Class<T> clazz){
		// TODO 待实现
		return null;
	}
}
