package org.springframework.extended.section07

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationEventPublisher
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.event.ApplicationEventMulticaster
import org.springframework.context.event.SimpleApplicationEventMulticaster
import org.springframework.core.task.SimpleAsyncTaskExecutor
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification

@ContextConfiguration(classes = TestConfiguration)
class AsynchronousApplicationEventTest extends Specification {

    @Autowired
    private ApplicationEventPublisher publisher

    @Autowired
    private AsynchronousApplicationListener listener

    def "asynchronous application event"() {
        expect:
            publisher.publishEvent(new CustomApplicationEvent(start: true))
            publisher.publishEvent(new CustomApplicationEvent(stop: true))

            Thread.sleep(1000)

            listener.startEvents.size() == 1
            listener.stopEvents.size() == 1

            listener.startEvents[0] != listener.stopEvents[0]
    }

    @Configuration
    @ComponentScan("org.springframework.extended.section07")
    public static class TestConfiguration {

    }

}
