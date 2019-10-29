package view.event;
/*
Last updated 10/10/2019

This class is used as the event fired when a user selects a content pane in the
content selection region of the interface.

Contributors:
Eva Moniz
 */

import javafx.event.Event;
import javafx.event.EventTarget;
import javafx.event.EventType;

/**
 * This represents the event of the selection of a content display in the GUI.
 *
 * @author Eva Moniz
 */
public class ContentSelectionEvent extends Event {

    public static final EventType<ContentSelectionEvent> CONTENT_SELECTION = new EventType<>("CONTENT_SELECTION");

    public ContentSelectionEvent(EventType<? extends ContentSelectionEvent> _eventType) {
        super(_eventType);
    }

    public ContentSelectionEvent(Object _source, EventTarget _target, EventType<? extends ContentSelectionEvent> _eventType) {
        super(_source, _target, _eventType);
    }

}
