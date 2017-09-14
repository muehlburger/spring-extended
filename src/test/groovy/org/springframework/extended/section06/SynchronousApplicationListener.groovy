package org.springframework.extended.section06

import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component

@Component
class SynchronousApplicationListener {

    List<Long> startEvents = new ArrayList<>()
    List<Long> stopEvents = new ArrayList<>()

    @EventListener(condition = "event.start")
    public void handleStartEvent(CustomApplicationEvent event) {
        this.startEvents.add(Thread.currentThread().id)
    }

    @EventListener(condition = "event.start")
    public void handleStopEvent(CustomApplicationEvent event) {
        this.stopEvents.add(Thread.currentThread().id)
    }

}
