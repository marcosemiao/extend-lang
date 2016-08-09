/*
 * Copyright 2016 Marco Semiao
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package fr.ms.lang.reflect;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import fr.ms.lang.ClassUtils;

/**
 * <p>
 * {@code ProxyUtils} provides static methods to create proxy class with origin
 * implementation.
 * </p>
 * 
 * @see <a href="http://marcosemiao4j.wordpress.com">Marco4J</a>
 *
 *
 * @author Marco Semiao
 *
 */
public final class ProxyUtils {

	public static final Object newProxyInstance(final Object implementation, final InvocationHandler h) {
		final ClassLoader classLoader = implementation.getClass().getClassLoader();
		final Class[] interfaces = ClassUtils.findInterfaces(implementation.getClass());

		final Object instance = Proxy.newProxyInstance(classLoader, interfaces, h);

		return instance;
	}

	public static final Object newProxyInstance(final Object implementation, final InvocationHandler h,
			final Class inter) {

		final ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		Class[] interfaces = ClassUtils.findInterfaces(implementation.getClass());

		if (inter != null) {
			final Set allInterfaces = new HashSet(Arrays.asList(interfaces));
			allInterfaces.add(inter);

			interfaces = (Class[]) allInterfaces.toArray(new Class[allInterfaces.size()]);
		}

		final Object instance = Proxy.newProxyInstance(classLoader, interfaces, h);

		return instance;
	}
}
