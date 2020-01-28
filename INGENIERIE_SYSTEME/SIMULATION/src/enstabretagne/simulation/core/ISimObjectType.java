/*
 * 
 */
package enstabretagne.simulation.core;

import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Interface ISimObjectType.
 */
public interface ISimObjectType {
	
	/**
	 * Gets the object instances.
	 *
	 * @return the object instances
	 */
    List<ISimObject> getObjectInstances();
	
	/**
	 * Terminate.
	 *
	 * @param restart the restart
	 * @return the int
	 */
    int terminate(boolean restart);
}
