import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.StaticApplicationContext;
import staticapplicationcontextdemo.Foo;

class StaticApplicationContextDemoTest {

	private StaticApplicationContext context;
	private BeanFactory beanFactory;

	@BeforeEach
	public void init() {
		context = new StaticApplicationContext();
		beanFactory = context;
	}

	@AfterEach
	public void closeContext() {
		context.close();
	}

	@Test
	void testDefaultScope() {
		context.registerBean("foo", Foo.class);

		Assertions.assertTrue(beanFactory.isSingleton("foo"));

		var o1 = beanFactory.getBean("foo");
        var o2 = beanFactory.getBean("foo");

		Assertions.assertNotNull(o1);
		Assertions.assertNotNull(o2);

		Assertions.assertSame(o1, o2);
	}

	@Test
	void testSingletonScope() {
		context.registerSingleton("foo", Foo.class);

		Assertions.assertTrue(beanFactory.isSingleton("foo"));

        var o1 = beanFactory.getBean("foo");
        var o2 = beanFactory.getBean("foo");

		Assertions.assertNotNull(o1);
		Assertions.assertNotNull(o2);

		Assertions.assertSame(o1, o2);
	}

	@Test
	void testPrototypeScope() {
		context.registerPrototype("foo", Foo.class);

		Assertions.assertTrue(beanFactory.isPrototype("foo"));

        var o1 = beanFactory.getBean("foo");
        var o2 = beanFactory.getBean("foo");

		Assertions.assertNotNull(o1);
		Assertions.assertNotNull(o2);

		Assertions.assertNotSame(o1, o2);
	}
}
