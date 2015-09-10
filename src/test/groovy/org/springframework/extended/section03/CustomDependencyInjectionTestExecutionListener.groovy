package org.springframework.extended.section03

import org.springframework.beans.factory.config.AutowireCapableBeanFactory
import org.springframework.test.context.TestContext
import org.springframework.test.context.TestExecutionListener

class CustomDependencyInjectionTestExecutionListener implements TestExecutionListener {

    @Override
    void beforeTestClass(TestContext testContext) throws Exception {
        // do nothing
    }

    @Override
    void prepareTestInstance(TestContext testContext) throws Exception {
        // do nothing
    }

    @Override
    void beforeTestMethod(TestContext testContext) throws Exception {
        injectDependencies(testContext)
    }

    @Override
    void afterTestMethod(TestContext testContext) throws Exception {
        // do nothing
    }

    @Override
    void afterTestClass(TestContext testContext) throws Exception {
        // do nothing
    }

    private static void injectDependencies(final TestContext testContext) throws Exception {
        Object bean = testContext.getTestInstance();
        AutowireCapableBeanFactory beanFactory = testContext.getApplicationContext().getAutowireCapableBeanFactory();
        // injects all properties with @Autowired annotation
        beanFactory.autowireBeanProperties(bean, AutowireCapableBeanFactory.AUTOWIRE_NO, false);
        // processes the spring bean lifecycle for the given bean
        beanFactory.initializeBean(bean, testContext.getTestClass().getName());
    }

}
