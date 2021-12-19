import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.context.support.StaticApplicationContext;
import staticapplicationcontextdemo.Foo;

class StaticApplicationContextDemoTest {

	private static StaticApplicationContext context;

	@BeforeAll
	static void init() {
		context = new StaticApplicationContext();
	}

	@AfterAll
	static void closeContext() {
		context.close();
	}

	@Test
	void testDefaultScope() {
		// arrange
		context.registerBean("foo", Foo.class);

		// act
		var o1 = context.getBean("foo");
		var o2 = context.getBean("foo");

		// assert
		Assertions.assertTrue(context.isSingleton("foo"));
		Assertions.assertNotNull(o1);
		Assertions.assertNotNull(o2);
		Assertions.assertSame(o1, o2);
	}

	@Test
	void testSingletonScope() {
		// arrange
		context.registerSingleton("foo", Foo.class);

		// act
		var o1 = context.getBean("foo");
		var o2 = context.getBean("foo");

		// assert
		Assertions.assertTrue(context.isSingleton("foo"));
		Assertions.assertNotNull(o1);
		Assertions.assertNotNull(o2);
		Assertions.assertSame(o1, o2);
	}

	@Test
	void testPrototypeScope() {
		// arrange
		context.registerPrototype("foo", Foo.class);

		// act
		var o1 = context.getBean("foo");
		var o2 = context.getBean("foo");

		// assert
		Assertions.assertTrue(context.isPrototype("foo"));
		Assertions.assertNotNull(o1);
		Assertions.assertNotNull(o2);
		Assertions.assertNotSame(o1, o2);
	}
}
