package org.springframework.extended.section03

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.Configuration
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.TestExecutionListeners
import spock.lang.Specification

import static org.springframework.test.context.TestExecutionListeners.*

@ContextConfiguration(classes = TestConfiguration)
@TestExecutionListeners(mergeMode = MergeMode.REPLACE_DEFAULTS)
class TestExecutionListenerTest extends Specification {

    @Autowired
    private ApplicationContext applicationContext;

    def "DependencyTestExecutionListener test"() {
        expect:
            applicationContext != null;
    }

    @Configuration
    public static class TestConfiguration {

    }

}
