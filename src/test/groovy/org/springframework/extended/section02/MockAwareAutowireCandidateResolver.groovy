package org.springframework.extended.section02

import org.easymock.EasyMock
import org.springframework.beans.BeansException
import org.springframework.beans.factory.NoSuchBeanDefinitionException
import org.springframework.beans.factory.config.BeanFactoryPostProcessor
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory
import org.springframework.beans.factory.config.DependencyDescriptor
import org.springframework.beans.factory.support.DefaultListableBeanFactory
import org.springframework.beans.factory.support.SimpleAutowireCandidateResolver
import org.springframework.context.ApplicationContext
import org.springframework.context.ApplicationContextAware

class MockAwareAutowireCandidateResolver extends SimpleAutowireCandidateResolver implements BeanFactoryPostProcessor, ApplicationContextAware {

    private ApplicationContext applicationContext

    @Override
    Object getSuggestedValue(DependencyDescriptor descriptor) {
        Class<?> dependencyType = descriptor.dependencyType;

        Map<String, ?> beans = applicationContext.getBeansOfType(dependencyType)
        if (beans == null || beans.isEmpty()) {
            return EasyMock.createMock(dependencyType)
        }
        return applicationContext.getBean(dependencyType)

        // return a mock if the bean type is not present within the context (EasyMock.createMock())
    }

    @Override
    void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        ((DefaultListableBeanFactory)beanFactory).setAutowireCandidateResolver(this)
    }

    @Override
    void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext
    }
}
