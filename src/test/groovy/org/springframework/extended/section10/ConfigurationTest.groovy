package org.springframework.extended.section10

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationContext
import org.springframework.context.MessageSource
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.support.ReloadableResourceBundleMessageSource
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification

@ContextConfiguration(classes = TestConfiguration)
class ConfigurationTest extends Specification {

    @Autowired
    private ApplicationContext applicationContext

    def "configuration class test"() {

    }

    @Configuration
    public static class TestConfiguration {



    }

}
