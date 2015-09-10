package org.springframework.extended.section02

import org.springframework.beans.factory.BeanFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.support.DefaultListableBeanFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.core.type.filter.AnnotationTypeFilter
import org.springframework.extended.section02.beans.IRepository
import org.springframework.extended.section02.beans.IService
import org.springframework.stereotype.Service
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification

@ContextConfiguration(classes = TestConfiguration)
class AutowireCandidateResolverTest extends Specification {

    @Autowired
    private IService service
    @Autowired
    private IRepository repository

    def "mock autowire candidate resolver test"() {
        expect:
            service != null
            repository != null
    }

    @Configuration
    @ComponentScan("org.springframework.extended.section02")
    public static class TestConfiguration {

        @Bean
        public static MockAwareAutowireCandidateResolver mockAwareAutowireCandidateResolver() {
            return new MockAwareAutowireCandidateResolver();
        }

    }

}
