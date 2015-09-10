package org.springframework.extended.section03

import org.springframework.beans.factory.config.AutowireCapableBeanFactory
import org.springframework.test.context.TestContext
import org.springframework.test.context.TestExecutionListener
import org.springframework.test.context.support.AbstractTestExecutionListener

class CustomDependencyInjectionTestExecutionListener {

    private static void injectDependencies(final TestContext testContext) throws Exception {
        Object bean = testContext.getTestInstance();
        AutowireCapableBeanFactory beanFactory = testContext.getApplicationContext().getAutowireCapableBeanFactory();
        // injects all properties with @Autowired annotation
        beanFactory.autowireBeanProperties(bean, AutowireCapableBeanFactory.AUTOWIRE_NO, false);
        // processes the spring bean lifecycle for the given bean
        beanFactory.initializeBean(bean, testContext.getTestClass().getName());
    }

}
