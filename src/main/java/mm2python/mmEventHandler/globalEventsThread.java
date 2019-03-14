/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mm2python.mmEventHandler;

import mm2python.DataStructures.constants;
import mm2python.UI.reporter;
import org.micromanager.Studio;
import org.micromanager.display.DisplayWindow;

/**
 *
 * @author bryant.chhun
 */
public class globalEventsThread implements Runnable {
    private final Studio mm;
    private final DisplayWindow dw;

    /**
     * Every micro-manager 'DisplayAboutToShowEvent' creates this thread,
     *  which subsequenty registers that DisplayWindow's 'Display' events and 'Datastore' events
     *
     * @param mm_: parent micro-manager Studio object
     * @param dw_: DisplayWindow whose construction triggered this class's construction
     */
    public globalEventsThread(Studio mm_, DisplayWindow dw_) {
        mm = mm_;
        dw = dw_;
        reporter.set_report_area(true, false, "global events thread filename = "+constants.RAMDiskName);
    }

    /**
     * Creates two new event objects and registers them to the parent display
     */
    @Override
    public void run() {
        // Currently, displayEvents are not used.
//        displayEvents display_events = new displayEvents(mm, dw, reporter);
//        display_events.registerThisDisplay();

        reporter.set_report_area(true, true, "registering datastore: "+dw.getName());

        datastoreEvents datastore_events = new datastoreEvents(mm, dw.getDatastore(), dw.getName());
        datastore_events.registerThisDatastore();
    }

    
}
