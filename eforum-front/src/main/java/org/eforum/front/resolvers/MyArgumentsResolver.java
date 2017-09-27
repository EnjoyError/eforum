package org.eforum.front.resolvers;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.eforum.entity.BaseEntity;
import org.eforum.exception.ServiceException;
import org.eforum.repository.CommonDao;
import org.eforum.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.alibaba.fastjson.JSON;

/**
 * 参数解析器，用来解析参数并包装成使用了AutoLoad注解的方法入口参数
 * 
 * @author Sackr
 *
 */
@Component
public class MyArgumentsResolver implements HandlerMethodArgumentResolver {
	private Logger logger = LoggerFactory.getLogger(MyArgumentsResolver.class);
	@Autowired
	private CommonDao dao;

	public void setDao(CommonDao dao) {
		this.dao = dao;
	}

	public boolean supportsParameter(MethodParameter parameter) {
		return parameter.hasParameterAnnotation(AutoLoad.class);
	}

	/**
	 * 解析参数
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
			NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
		System.out.println(webRequest);
		String json = getRequestJsonString((HttpServletRequest) webRequest.getNativeRequest());
		Map<String, Object> map = (Map) JSON.parse(json);
		Class paramClass = (Class) parameter.getGenericParameterType();
		Object param = getValue(paramClass, map);
		return param;
	}

	/**
	 * 获取字段的值
	 * 
	 * @param paramClass
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private Object getValue(Class paramClass, Map<String, Object> map) throws Exception {
		Object param = paramClass.newInstance();
		if (param instanceof BaseEntity) {
			String idStr = String.valueOf(map.get("id"));
			if (!StringUtils.isNullOrEmpty(idStr) && !"null".equals(idStr)) {
				Long id = Long.valueOf(idStr);
				param = dao.get(paramClass, id);
				if (null == param) {
					throw new ServiceException("找不到id为【" + id + "】的Entity");
				}
			}
		}
		for (Map.Entry<String, Object> entry : map.entrySet()) {
			String fieldName = entry.getKey();
			Object fieldValue = entry.getValue();
			Field field = getField(paramClass, fieldName);
			if (null == field) {
				continue;
			}
			Class fieldClass = field.getType();
			if (fieldClass == Long.class || fieldClass == long.class) {
				fieldValue = Long.valueOf(String.valueOf(fieldValue));
			} else if (fieldClass == Integer.class || fieldClass == int.class) {
				fieldValue = Integer.valueOf(String.valueOf(fieldValue));
			} else if (fieldClass == Double.class || fieldClass == double.class) {
				fieldValue = Double.valueOf(String.valueOf(fieldValue));
			} else if (fieldClass == Boolean.class || fieldClass == boolean.class) {
				fieldValue = Boolean.valueOf(String.valueOf(fieldValue));
			} else if (fieldClass == String.class) {
				fieldValue = String.valueOf(fieldValue);
			} else {
				fieldValue = getValue(fieldClass, (Map) fieldValue);
			}
			Method setMethod = getMethod(paramClass, getFieldSetMethodName(fieldName), fieldClass);
			if (null == setMethod) {
				continue;
			}
			setMethod.invoke(param, fieldValue);
		}
		return param;
	}

	/**
	 * 获取方法
	 * 
	 * @param clazz
	 * @param methodName
	 * @param paramClasses
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private Method getMethod(Class clazz, String methodName, Class... paramClasses) {
		Method method = null;
		try {
			method = clazz.getMethod(methodName, paramClasses);
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
		return method;
	}

	/**
	 * 获取字段
	 * 
	 * @param clazz
	 * @param fieldName
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	private Field getField(Class clazz, String fieldName) {
		Field field = null;
		try {
			field = clazz.getDeclaredField(fieldName);
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
		return field;
	}

	/**
	 * 根据字段名，获取set方法名
	 * 
	 * @param fieldName
	 * @return
	 */
	private String getFieldSetMethodName(String fieldName) {
		String first = fieldName.substring(0, 1);
		String last = fieldName.substring(1);
		return "set" + first.toUpperCase() + last;
	}

	/***
	 * 获取 request 中 json 字符串的内容
	 * 
	 * @param request
	 * @return : <code>byte[]</code>
	 * @throws IOException
	 */
	public static String getRequestJsonString(HttpServletRequest request) throws IOException {
		String submitMehtod = request.getMethod();
		// GET
		if (submitMehtod.equals("GET")) {
			return new String(request.getQueryString().getBytes("iso-8859-1"), "utf-8").replaceAll("%22", "\"");
			// POST
		} else {
			return getRequestPostStr(request);
		}
	}

	/**
	 * 描述:获取 post 请求的 byte[] 数组
	 * 
	 * <pre>
	 * 举例：
	 * </pre>
	 * 
	 * @param request
	 * @return
	 * @throws IOException
	 */
	public static byte[] getRequestPostBytes(HttpServletRequest request) throws IOException {
		int contentLength = request.getContentLength();
		if (contentLength < 0) {
			return null;
		}
		byte buffer[] = new byte[contentLength];
		for (int i = 0; i < contentLength;) {

			int readlen = request.getInputStream().read(buffer, i, contentLength - i);
			if (readlen == -1) {
				break;
			}
			i += readlen;
		}
		return buffer;
	}

	/**
	 * 描述:获取 post 请求内容
	 * 
	 * <pre>
	 * 举例：
	 * </pre>
	 * 
	 * @param request
	 * @return
	 * @throws IOException
	 */
	public static String getRequestPostStr(HttpServletRequest request) throws IOException {
		byte buffer[] = getRequestPostBytes(request);
		String charEncoding = request.getCharacterEncoding();
		if (charEncoding == null) {
			charEncoding = "UTF-8";
		}
		return new String(buffer, charEncoding);
	}

}
