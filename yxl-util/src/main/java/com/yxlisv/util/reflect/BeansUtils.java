package com.yxlisv.util.reflect;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>JavaBean 反射工具类</p>
 * @author 杨雪令
 * @time 2016年3月17日下午1:29:40
 * @version 1.0
 */
public class BeansUtils {

	// 定义一个全局的记录器，通过LoggerFactory获取
	protected static Logger logger = LoggerFactory.getLogger(BeansUtils.class);

	/**
	 * <p>获取基本类型的属性字段，及其引用类型的基本属性</p>
	 * @param targetClass 目标类
	 * @return String[] 属性字段名称数组
	 * @author 杨雪令
	 * @time 2016年3月9日下午12:59:24
	 * @version 1.0
	 */
	@SuppressWarnings("rawtypes")
	public static List<String> getFieldsAndRefer(Class targetClass) {
		return getFieldsAndRefer(targetClass, null);
	}

	/**
	 * <p>获取基本类型的属性字段，及其引用类型的基本属性</p>
	 * @param targetClass 目标类
	 * @param rootClass 原始类，根节点的类
	 * @return String[] 属性字段名称数组
	 * @author 杨雪令
	 * @time 2016年3月9日下午12:59:24
	 * @version 1.0
	 */
	@SuppressWarnings("rawtypes")
	public static List<String> getFieldsAndRefer(Class targetClass, List<Class> refClass) {
		Field[] fields = targetClass.getDeclaredFields();
		if (fields.length <= 0) return null;
		List<String> fieldNames = new ArrayList<String>();
		if (refClass == null) {
			refClass = new ArrayList<Class>();
			refClass.add(targetClass);
		}
		for (Field field : fields) {
			String fieldTypeName = field.getType().getName();
			if (fieldTypeName.indexOf(".") > 0) fieldTypeName = fieldTypeName.substring(fieldTypeName.lastIndexOf(".") + 1);
			if (fieldTypeName.equals("List") || fieldTypeName.equals("Set")){
				continue;
			}
			
			if (ReflectionUtils.isJDKType(field.getType())) {
				fieldNames.add(field.getName());
			} else if (!refClass.contains(field.getType())) {// 不能循环引用
				refClass.add(field.getType());
				List<String> referFields = getFieldsAndRefer(field.getType(), refClass);
				if (referFields == null) continue;
				for (String referfieldName : referFields) {
					fieldNames.add(field.getName() + "." + referfieldName);
				}
			}
		}
		return fieldNames;
	}
}