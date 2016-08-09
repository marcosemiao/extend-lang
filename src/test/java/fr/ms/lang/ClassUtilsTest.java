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

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @see <a href="http://marcosemiao4j.wordpress.com">Marco4J</a>
 *
 *
 * @author Marco Semiao
 *
 */
public class ClassUtilsTest {

	@Test
	public void classPresentTest() {
		boolean classPresent = ClassUtils.classPresent(String.class.getName());

		Assert.assertTrue(classPresent);

		classPresent = ClassUtils.classPresent("java.util.FakeClass");

		Assert.assertFalse(classPresent);
	}

	@Test
	public void findInterfacesTest() {
		ArrayList<Class> checkInterfaces = new ArrayList<Class>();
		checkInterfaces.add(java.io.Serializable.class);
		checkInterfaces.add(java.util.Collection.class);
		checkInterfaces.add(java.lang.Iterable.class);
		checkInterfaces.add(java.util.List.class);
		checkInterfaces.add(java.lang.Cloneable.class);
		checkInterfaces.add(java.util.RandomAccess.class);

		Class[] interfaces = ClassUtils.findInterfaces(ArrayList.class);

		for (final Class clazz : interfaces) {
			Assert.assertTrue(checkInterfaces.contains(clazz));
		}

		checkInterfaces = new ArrayList<Class>();
		checkInterfaces.add(java.lang.Runnable.class);

		interfaces = ClassUtils.findInterfaces(ArrayList.class);

		for (final Class clazz : interfaces) {
			Assert.assertFalse(checkInterfaces.contains(clazz));
		}

		interfaces = ClassUtils.findInterfaces(Object.class);

		Assert.assertEquals(0, interfaces.length);
	}
}
