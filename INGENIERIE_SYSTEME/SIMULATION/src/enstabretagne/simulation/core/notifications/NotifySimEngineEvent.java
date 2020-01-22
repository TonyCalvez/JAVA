/**
* Classe NotifySimEngineEvent.java
*@author Olivier VERRON
*@version 1.0.
*/
package enstabretagne.simulation.core.notifications;

import enstabretagne.simulation.core.ISimTimeEvent;

// TODO: Auto-generated Javadoc
/**
 * The Interface NotifySimEngineEvent.
 */
@FunctionalInterface
public interface NotifySimEngineEvent {
    void notifySimEngineEvent(ISimTimeEvent ev);
}

