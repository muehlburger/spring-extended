package org.springframework.extended.section06

import org.springframework.context.ApplicationEvent

class CustomApplicationEvent extends ApplicationEvent {

    boolean start
    boolean stop

    /**
     * Create a new ApplicationEvent.
     * @param source the object on which the event initially occurred (never {@code null})
     */
    CustomApplicationEvent() {
        super("Synchronous application event")
    }

}
