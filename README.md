# Extend Java Lang API

## Fonctionnalités générales
Librairie utilitaire permettant d'avoir des méthodes supplémentaires à l'api standard Java Lang :
- Compatible à partir de la version Java 3.
- Disponible sur le repository central de Maven.

## Utilisation rapide

- Ajouter la dépendance :
````xml
<dependency>
	<groupId>com.github.marcosemiao</groupId>
	<artifactId>extend-lang</artifactId>
	<version>0.0.1</version>
</dependency>
````
- Utiliser les méthodes statiques disponibles

Retrouver toutes les interfaces d'une classe :
````java
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
````

Verifier qu'une classe est disponible :
````java
	@Test
	public void classPresentTest() {
		boolean classPresent = ClassUtils.classPresent(String.class.getName());

		Assert.assertTrue(classPresent);

		classPresent = ClassUtils.classPresent("java.util.FakeClass");

		Assert.assertFalse(classPresent);
	}
````

Créer une proxy en utilisant toutes les interfaces d'une instance deja existante :
````java
	Statement statement;
	final Statement instance = (Statement) ProxyUtils.newProxyInstance(statement, handler);
````
