package org.springframework.extended.section07

import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component

@Component
class AsynchronousApplicationListener {

    List<Long> startEvents = new ArrayList<>()
    List<Long> stopEvents = new ArrayList<>()

    @EventListener(condition = "event.start == true")
    public void handleStartEvent(CustomApplicationEvent event) {
        this.startEvents.add(Thread.currentThread().id)
    }

    @EventListener(condition = "event.stop == true")
    public void handleStopEvent(CustomApplicationEvent event) {
        this.stopEvents.add(Thread.currentThread().id)
    }

}
