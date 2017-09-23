package ng.bayue.validate;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Deque;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import ng.bayue.annotation.GenerValidate;
import ng.bayue.annotation.ValidateType;
import ng.bayue.common.ErrorMessage;
import ng.bayue.test.TestModel;

/**
 * <pre>
 * controller 入参实体类有效性校验
 * </pre>
 *
 * @author lenovopc
 * @version $Id: GenerateValidator.java, v 0.1 2017年1月11日 下午5:20:38 lenovopc Exp $
 */
@SuppressWarnings({ "unused" })
public class GenerateValidator {

	private static final Class<GenerValidate> annotationClass = GenerValidate.class;

	private static final String CONSTANT_INTEGER = "class java.lang.Integer";
	private static final String CONSTANT_BOOLEAN = "class java.lang.Boolean";
	private static final String CONSTANT_LONG = "class java.lang.Long";
	private static final String CONSTANT_FLOAT = "class java.lang.Float";
	private static final String CONSTANT_DOUBLE = "class java.lang.Double";

	private static final String CONSTANT_STRING = "class java.lang.String";
	private static final String CONSTANT_DATE = "class java.util.Date";
	private static final String CONSTANT_MAP = "class java.util.Map";
	private static final String CONSTANT_List = "class java.util.List";

	public static <T> ErrorMessage validate(T t) {
		Class<? extends Object> modelClass = t.getClass();
		Field[] fields = modelClass.getDeclaredFields();
		for (Field field : fields) {
			GenerValidate generValidate = field.getAnnotation(annotationClass);// 获取字段上的注解
			if (null == generValidate) {
				continue;
			}
			try {
				// 获取字段的值
				String fieldName = field.getName();
				PropertyDescriptor pd = new PropertyDescriptor(fieldName, modelClass);
				Method method = pd.getReadMethod();
				Object fieldValue = method.invoke(t);

				String message = generValidate.message();
				ValidateType[] type = generValidate.type();
				String regex = generValidate.regex();

				Class<? extends Object> fieldType = field.getType();
				for (ValidateType vt : type) {
					switch (vt) {
					case NOTBLANK:
						if (null == fieldValue || StringUtils.isBlank(fieldValue.toString())) {
							return new ErrorMessage(true, message);
						}
						break;
					case NOTEMPTY:
						if (CONSTANT_STRING.equals(fieldType)
								&& (null == fieldValue || StringUtils.isBlank(fieldValue.toString()))) {
							return new ErrorMessage(true, message);
						}
						if (Map.class.equals(fieldType)) {
							Map<?, ?> map = (Map<?, ?>) fieldValue;
							if (null == map || map.isEmpty()) {
								return new ErrorMessage(true, message);
							}
						}
						if (List.class.equals(fieldType) || Set.class.equals(fieldValue)
								|| Queue.class.equals(fieldType) || Deque.class.equals(fieldType)) {
							if (CollectionUtils.isEmpty((Collection<?>) fieldValue)) {
								return new ErrorMessage(true, message);
							}
						}
						/*
						 * if(fieldValue instanceof List || fieldValue
						 * instanceof Set || fieldValue instanceof Deque ||
						 * fieldValue instanceof Queue){ if
						 * (CollectionUtils.isEmpty((Collection<?>) fieldValue))
						 * { return new ErrorMessage(true, message); } }
						 */
						break;
					case NUMERIC:
						if (null == fieldValue || StringUtils.isBlank(fieldValue.toString())
								|| !isNumeric(fieldValue)) {
							return new ErrorMessage(true, message);
						}
						break;
					case EMAIL:
						// 暂未实现
						break;
					default:
						;
					}

					// if (vt.equals(ValidateType.NOTBLANK) &&
					// StringUtils.isBlank((String) fieldValue)) {
					// return new ErrorMessage(true, message);
					// }
					// if (vt.equals(ValidateType.NOTEMPTY)) {
					// // if(String.class.equals(fieldType)){
					// if (CONSTANT_STRING.equals(fieldType) &&
					// StringUtils.isBlank((String) fieldValue)) {
					// return new ErrorMessage(true, message);
					// }
					// if (Map.class.equals(fieldType)) {
					// Map<?, ?> map = (Map<?, ?>) fieldValue;
					// if (null == map || map.isEmpty()) {
					// return new ErrorMessage(true, message);
					// }
					// }
					// if (List.class.equals(fieldType) ||
					// Set.class.equals(fieldValue)
					// || Queue.class.equals(fieldType) ||
					// Deque.class.equals(fieldType)) {
					// if (CollectionUtils.isEmpty((Collection<?>) fieldValue))
					// {
					// return new ErrorMessage(true, message);
					// }
					// }
					// /*
					// * if(fieldValue instanceof List || fieldValue
					// * instanceof Set || fieldValue instanceof Deque ||
					// * fieldValue instanceof Queue){ if
					// * (CollectionUtils.isEmpty((Collection<?>) fieldValue))
					// * { return new ErrorMessage(true, message); } }
					// */
					//
					// }

				}

			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
					| IntrospectionException e) {
			}
		}

		return new ErrorMessage();
	}

	/**
	 * <pre>
	 * 判断包含小数点的正负数字
	 * </pre>
	 *
	 * @param o
	 * @return
	 */
	private static boolean isNumeric(Object o) {
		if (null == o) {
			return false;
		}
		String regex = "^-?[1-9][0-9]*.?[0-9]{1,}$";
		return o.toString().matches(regex);
	}

	public static void main(String[] args) {
		TestModel model = new TestModel();
		model.setName("33.5");
		model.setAge(20);
		// model.setCreateTime(new Date());
		ErrorMessage message = GenerateValidator.validate(model);
		System.out.println(message);
	}

}
