package org.springframework.extended.section01

import org.springframework.beans.MutablePropertyValues
import org.springframework.beans.factory.config.BeanDefinition
import org.springframework.beans.factory.config.RuntimeBeanReference
import org.springframework.beans.factory.support.BeanDefinitionBuilder
import org.springframework.beans.factory.support.BeanDefinitionRegistry
import org.springframework.beans.factory.support.GenericBeanDefinition
import org.springframework.context.support.StaticApplicationContext
import org.springframework.extended.section01.beans.IRepository
import org.springframework.extended.section01.beans.IService
import org.springframework.extended.section01.beans.impl.RepositoryImpl
import org.springframework.extended.section01.beans.impl.ServiceImpl
import spock.lang.Specification

class BeanDefinitionTest extends Specification {

    def "create a bean definition programmatically"() {
        expect:
            StaticApplicationContext context = new StaticApplicationContext();
            BeanDefinitionRegistry registry = (BeanDefinitionRegistry) context.beanFactory;

            // create the repository bean
            BeanDefinitionBuilder repositoryBeanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(RepositoryImpl)
            registry.registerBeanDefinition("repositoryImpl", repositoryBeanDefinitionBuilder.getBeanDefinition());

            // create the service bean
            BeanDefinitionBuilder serviceBeanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(ServiceImpl)
            serviceBeanDefinitionBuilder.addPropertyReference("repository", "repositoryImpl")

            registry.registerBeanDefinition("serviceImpl", serviceBeanDefinitionBuilder.getBeanDefinition());

            context.getBean(IService.class) != null
            context.getBean(IRepository.class) != null
            context.getBean(IService.class).repository != null
    }

    /**
     * GenericBeanDefinition beanDef = new GenericBeanDefinition();
     * beanDef.setBeanClass(ServiceImpl.class);
     *
     * MutablePropertyValues values = new MutablePropertyValues();
     * values.addPropertyValue("repository", new RuntimeBeanReference("repositoryImpl"));
     *
     * beanDef.setPropertyValues(values);
     *
     * registry.registerBeanDefinition("serviceImpl", beanDef);
     *
     * context.getBean(IService.class) != null
     * context.getBean(IRepository.class) != null
     */

}
