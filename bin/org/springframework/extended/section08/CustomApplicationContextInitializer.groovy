package org.springframework.extended.section08

import org.springframework.context.ApplicationContextInitializer
import org.springframework.context.ConfigurableApplicationContext
import org.springframework.core.env.Environment

class CustomApplicationContextInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    @Override
    void initialize(ConfigurableApplicationContext applicationContext) {
        Environment environment = applicationContext.getEnvironment()
    }

}
