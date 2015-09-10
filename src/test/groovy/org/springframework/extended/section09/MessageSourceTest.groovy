package org.springframework.extended.section09

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.ApplicationContext
import org.springframework.context.MessageSource
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer
import org.springframework.context.support.ReloadableResourceBundleMessageSource
import org.springframework.core.env.Environment
import org.springframework.core.io.ClassPathResource
import org.springframework.extended.section08.CustomApplicationContextInitializer
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification

@ContextConfiguration(classes = TestConfiguration)
class MessageSourceTest extends Specification {

    @Autowired
    private ApplicationContext applicationContext

    def "message source test"() {
        expect:
            applicationContext.getMessage("key1", new Object[0], Locale.GERMAN) == 'value1_DE'
            applicationContext.getMessage("key1", new Object[0], Locale.ENGLISH) == 'value1_DEFAULT'

            applicationContext.getMessage("key2", ["ARGUMENT"] as Object[], Locale.GERMAN) == 'value2_ARGUMENT_DE'
            applicationContext.getMessage("key2", ["ARGUMENT"] as Object[], Locale.ENGLISH) == 'value2_ARGUMENT_DEFAULT'
    }

    @Configuration
    public static class TestConfiguration {

        @Bean
        public MessageSource messageSource() {
            ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource()

            messageSource.setDefaultEncoding("UTF-8")
            messageSource.setBasename("classpath:org/springframework/extended/section09/messages/messages")
            messageSource.setFallbackToSystemLocale(false)

            return messageSource
        }

    }

}
