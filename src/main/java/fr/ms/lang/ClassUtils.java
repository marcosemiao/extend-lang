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
package fr.ms.lang;

import java.util.HashSet;
import java.util.Set;

/**
 * <p>
 * This class {@code ClassUtils} provides commonly used class functions such as
 * present, find interfaces method.
 * 
 * @see <a href="http://marcosemiao4j.wordpress.com">Marco4J</a>
 *
 *
 * @author Marco Semiao
 *
 */
public final class ClassUtils {

	private ClassUtils() {
	}

	public final static boolean classPresent(final String className) {
		try {
			Class.forName(className);
			return true;
		} catch (final ClassNotFoundException e) {
			return false;
		}
	}

	public final static Class[] findInterfaces(final Class cls) {
		if (cls == null) {
			return null;
		}

		final Set interfacesFound = new HashSet();
		getAllInterfaces(cls, interfacesFound);

		final Class[] interfaces = (Class[]) interfacesFound.toArray(new Class[interfacesFound.size()]);

		return interfaces;
	}

	private final static void getAllInterfaces(Class clazz, final Set interfacesFound) {
		while (clazz != null) {
			final Class[] interfaces = clazz.getInterfaces();

			for (int i = 0; i < interfaces.length; i++) {
				final Class inter = interfaces[i];
				if (interfacesFound.add(inter)) {
					getAllInterfaces(inter, interfacesFound);
				}
			}

			clazz = clazz.getSuperclass();
		}
	}
}
