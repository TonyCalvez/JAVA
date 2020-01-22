/*
 * 
 */
package enstabretagne.simulation.core;

import enstabretagne.base.time.LogicalDateTime;
import enstabretagne.base.time.LogicalDuration;

// TODO: Auto-generated Javadoc
/**
 * Cette interface caract�rise l'objet de base manipul� par le moteur.
 * Elle contient les fonctions essentielles pour poster/d�poster des �v�nements
 * La seule fonction li�e � un cycle de vie est Terminate().<br>
 * Les autres fonctions li�es au cycle de vie d'une entit� sont port�es par {@link IEntity}
 * 
 * La fonction <code>getName()</code> est essentielle. Elle donne l'�l�ment qui identifie l'objet sans ambiguit�.
 */
public interface ISimObject {
	
	/**
	 * Current date.
	 *
	 * @return the logical date time
	 */
	LogicalDateTime CurrentDate();
	
	/**
	 * Cette fonction permet � un objet de demander l'arr�t de la simulation.
	 * Fonction plut�t � usage du framework plus que des applications de simulation.
	 */
	void interruptEngineByDate();
	
	/**
	 * Gets the sim obj ID.
	 *
	 * @return the sim obj ID
	 */
	int getSimObjID();
	
	/**
	 * Post.
	 *
	 * @param ev the ev
	 */
    void Post(ISimEvent ev);
	
	/**
	 * Post.
	 *
	 * @param ev the ev
	 * @param t the t
	 */
    void Post(ISimEvent ev, LogicalDateTime t);
	
	/**
	 * Post.
	 *
	 * @param ev the ev
	 * @param dt the dt
	 */
    void Post(ISimEvent ev, LogicalDuration dt);
	
	/**
	 * Un post.
	 *
	 * @param ev the ev
	 */
    void UnPost(ISimEvent ev);
	
	/**
	 * Un post all events.
	 */
    void UnPostAllEvents();
	
	/**
	 * Terminate.
	 *
	 * @param restart the restart
	 */
    void terminate(boolean restart);
	
	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
    String getName();
}
