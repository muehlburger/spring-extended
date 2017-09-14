package org.springframework.extended.section08

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer
import org.springframework.core.env.Environment
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.TestContext
import spock.lang.Specification

@ContextConfiguration(classes = TestConfiguration, initializers = CustomApplicationContextInitializer)
class EnvironmentTest extends Specification {

    @Value('${propertyA}')
    private String propertyA

    @Autowired
    private Environment environment

    def "environment test"() {
        expect:
            propertyA == 'valueA'
            environment.getProperty("propertyB") == 'valueB'
    }

    @Configuration
    public static class TestConfiguration {

        @Bean
        public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
            return new PropertySourcesPlaceholderConfigurer()
        }

    }

}
