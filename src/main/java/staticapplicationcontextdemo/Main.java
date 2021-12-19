package staticapplicationcontextdemo;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.StaticApplicationContext;

public class Main {

    public static void main(String[] args) {
        // see StaticApplicationContextDemoTest for a JUnit version
        try (StaticApplicationContext context = new StaticApplicationContext()) {
            var beanFactory = (BeanFactory) context;

            final var defaultScopeId = "Foo in default scope";
            context.registerBean(defaultScopeId, Foo.class);
            System.out.println(beanFactory.getBean(defaultScopeId));
            System.out.println(context.isSingleton(defaultScopeId));

            final var singletonId = "Foo as singletonId";
            context.registerSingleton(singletonId, Foo.class);
            System.out.println(beanFactory.getBean(singletonId));
            System.out.println(context.isSingleton(singletonId));

            final var prototypeId = "Foo as prototypeId";
            context.registerPrototype(prototypeId, Foo.class);
            System.out.println(beanFactory.getBean(prototypeId));
            System.out.println(context.isPrototype(prototypeId));
        }
    }

}
