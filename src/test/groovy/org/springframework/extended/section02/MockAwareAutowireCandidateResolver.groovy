package org.springframework.extended.section02

import org.easymock.EasyMock
import org.springframework.beans.BeansException
import org.springframework.beans.factory.config.BeanFactoryPostProcessor
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory
import org.springframework.beans.factory.config.DependencyDescriptor
import org.springframework.beans.factory.support.DefaultListableBeanFactory
import org.springframework.beans.factory.support.SimpleAutowireCandidateResolver
import org.springframework.context.ApplicationContext
import org.springframework.context.ApplicationContextAware

class MockAwareAutowireCandidateResolver extends SimpleAutowireCandidateResolver implements BeanFactoryPostProcessor, ApplicationContextAware {

    private ApplicationContext applicationContext

    private Map<String, Object> cache = [:]

    @Override
    Object getSuggestedValue(DependencyDescriptor descriptor) {
        Class<?> cls = descriptor.dependencyType;

        Map<String, ?> beans = applicationContext.getBeansOfType(cls);

        if (beans.isEmpty()) {
            if (!cache.containsKey(cls.name)) {
                cache.put(cls.name, EasyMock.createMock(cls))
            }
            return cache.get(cls.name)
        }
        return null
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
