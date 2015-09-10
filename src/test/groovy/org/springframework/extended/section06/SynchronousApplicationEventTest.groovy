package org.springframework.extended.section06

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationEventPublisher
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification

@ContextConfiguration(classes = TestConfiguration)
class SynchronousApplicationEventTest extends Specification {

    @Autowired
    private ApplicationEventPublisher publisher

    @Autowired
    private SynchronousApplicationListener listener

    def "synchronous application event"() {
        expect:
            publisher.publishEvent(new CustomApplicationEvent(start: true))
            publisher.publishEvent(new CustomApplicationEvent(stop: true))

            listener.startEvents.size() == 1
            listener.stopEvents.size() == 1

            listener.startEvents[0] == listener.stopEvents[0]
    }

    @Configuration
    @ComponentScan("org.springframework.extended.section06")
    public static class TestConfiguration {

    }

}
