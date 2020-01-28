/*
 * 
 */
package enstabretagne.simulation.core;

import enstabretagne.base.time.LogicalDateTime;
import enstabretagne.simulation.core.ISimObject;

// TODO: Auto-generated Javadoc
/**
 * The Interface ISimEvent.
 */
public interface ISimEvent extends Comparable<ISimEvent>{
	
	/**
	 * Process.
	 */
    void Process();
	
	/**
	 * Schedule date.
	 *
	 * @return the logical date time
	 */
    LogicalDateTime ScheduleDate();
	
	/**
	 * Owner.
	 *
	 * @return the i sim object
	 */
    ISimObject Owner();
	
	/**
	 * Initialize.
	 *
	 * @param simObject the sim object
	 * @param t the t
	 */
    void initialize(ISimObject simObject, LogicalDateTime t);
	
	/**
	 * Terminate.
	 */
    void terminate();
	
	/**
	 * Reset process date.
	 *
	 * @param simulationDate the simulation date
	 */
    void resetProcessDate(LogicalDateTime simulationDate);
	
	/**
	 * Time event line.
	 *
	 * @param r the r
	 * @return the string
	 */
    String TimeEventLine(int r);
}
