/*
 * Copyright 2011 the original author or authors.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.jayway.jaxrs.hateoas.support;

import java.lang.reflect.Field;

import com.jayway.jaxrs.hateoas.HateoasInjectException;

/**
 * General reflection utilities. Not intended for external use.
 * @author Mattias Hellborg Arthursson
 * @author Kalle Stenflo
 */
public class ReflectionUtils {
	private ReflectionUtils() {

	}

	public static void setFieldAccessible(Field field) {
		if (!field.isAccessible()) {
			field.setAccessible(true);
		}
	}

	public static Object getFieldValue(Object entity, String fieldName) {
		try {
			Field field = getField(entity, fieldName);
			return field.get(entity);
		} catch (Exception e) {
			throw new HateoasInjectException(e);
		}

	}

	public static Field getField(Object entity, String fieldName)
			throws NoSuchFieldException {
		Field field = entity.getClass().getDeclaredField(fieldName);
		setFieldAccessible(field);
		return field;
	}

	public static void setField(Object entity, String fieldName, Object value) {
		try {
			Field field = getField(entity, fieldName);
			field.set(entity, value);
		} catch (Exception e) {
			throw new HateoasInjectException(e);
		}
	}
}
