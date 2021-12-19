package staticapplicationcontextdemo;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.StaticApplicationContext;

public class Main {

    public static void main(String[] args) {
        try (StaticApplicationContext context = new StaticApplicationContext()) {
            BeanFactory beanFactory = context;

            final String default0 = "default0";
            context.registerBean(default0, Foo.class);
            System.out.println(beanFactory.getBean(default0));
            System.out.println(context.isSingleton(default0));

            final String singleton = "singleton";
            context.registerSingleton(singleton, Foo.class);
            System.out.println(beanFactory.getBean(singleton));
            System.out.println(context.isSingleton(singleton));

            final String prototype = "prototype";
            context.registerPrototype(prototype, Foo.class);
            System.out.println(beanFactory.getBean(prototype));
            System.out.println(context.isPrototype(prototype));
        }
    }

}
