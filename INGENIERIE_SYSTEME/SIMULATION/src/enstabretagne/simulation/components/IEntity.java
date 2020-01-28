/**
* Classe IEntity.java
*@author Olivier VERRON
*@version 1.0.
*/
package enstabretagne.simulation.components;
import java.util.List;

import enstabretagne.base.math.MoreRandom;
import enstabretagne.simulation.components.implementation.SimEntity;
import enstabretagne.simulation.components.notifications.ActivationNotification;
import enstabretagne.simulation.components.notifications.CreationNotification;
import enstabretagne.simulation.components.notifications.DeactivationNotification;
import enstabretagne.simulation.components.notifications.InitializationNotification;
import enstabretagne.simulation.components.notifications.TerminatingNotification;
import enstabretagne.simulation.core.ISimEngine;

// TODO: Auto-generated Javadoc
/**
 * Cette interface compl?te l'interface {@link ISimObject} des fonctions donnant un cycle de vie ? l'entit?.
 * Elle rajoute de plus la gestion de la filiation d'une entit?.
 */
public interface IEntity extends enstabretagne.simulation.core.ISimObject{

		/**
		 * Liste de callbacks permettant ? l'entit? de pr?venir de 
		 * son passage en phase de cr?ation.
		 *
		 * @return the list
		 */
	    List<CreationNotification> OnCreating();

		/**
		 * Liste de callbacks permettant ? l'entit? de pr?venir de 
		 * son passage ? l'?tat cr??.
		 *
		 * @return the list
		 */
	    List<CreationNotification> OnCreated();

	    /**
    	 * Liste de callbacks permettant ? l'entit? de pr?venir de 
		 * son passage en phase d'initialisation.
    	 *
    	 * @return the list
    	 */
	    List<InitializationNotification> OnInitializing();

		/**
		 * Liste de callbacks permettant ? l'entit? de pr?venir de 
		 * son passage en phase initialis?e.
		 *
		 * @return the list
		 */
	    List<InitializationNotification> OnInitialized();

	    /**
    	 * Liste de callbacks permettant ? l'entit? de pr?venir de 
		 * son passage en phase d'activation.
    	 *
    	 * @return the list
    	 */
    	List<ActivationNotification> OnActivating();

	    /**
    	 * Liste de callbacks permettant ? l'entit? de pr?venir de 
		 * son passage en phase activ?.
    	 *
    	 * @return the list
    	 */
	    List<ActivationNotification> OnActivated();

	    /**
    	 * Liste de callbacks permettant ? l'entit? de pr?venir de 
		 * avant de rentrer dans le processus de terminaison.
    	 *
    	 * @return the list
    	 */
	    List<TerminatingNotification> OnTerminating();

	    /**
    	 * Liste de callbacks permettant ? l'entit? de pr?venir de 
		 * avant de rentrer dans le processus de d?sactivation.
    	 *
    	 * @return the list
    	 */
	    List<DeactivationNotification> OnDeactivating();

	    /**
    	 *Liste de callbacks permettant ? l'entit? de pr?venir de 
		 * qu'elle est d?sactiv?e.
    	 *
    	 * @return the list
    	 */
	    List<DeactivationNotification> OnDeactivated();

	    /**
    	 * Checks if is alive.
    	 *
    	 * @return true, if successful
    	 */
	    boolean IsAlive();

	    /**
    	 * Checks if is active.
    	 *
    	 * @return true, if successful
    	 */
	    boolean IsActive ();


	    /**
    	 * Gets the full name.
    	 *
    	 * @return the full name
    	 */
	    String getFullName();

	    /**
    	 * Permet de r?cup?rer le g?n?rateur de nombres al?atoires.
    	 *
    	 * @return the more random
    	 */
	    MoreRandom RandomGenerator();
	    
	    /**
    	 * Cette fonction permet d'activer l'entit?. 
    	 * Il est de la responsabilit? de l'entit? parente d'activer ses filles.
    	 */
    	void activate();
	    
    	/**
    	 * Cette fonction permet d'activer l'entit?. 
    	 * Il est de la responsabilit? de l'entit? parente de d?sactiver ses filles.
    	 */
    	void deactivate();
	    
    	/**
    	 * Gets the children.
    	 *
    	 * @return the children
    	 */
    	List<SimEntity> getChildren() ;
	    
    	/**
    	 * Gets the parent.
    	 * renvoie Null le cas o? il n'y a pas de parents.
    	 *
    	 * @return the parent
    	 */
    	IEntity getParent() ;

		/**
		 * Sets the engine.
		 * Cette m?thode n'est pas ? utiliser en temps normal.
		 * Plut?t ? usage du framework de simulation plus que des applications de simulation
		 *
		 * @param value the new engine
		 */
		void setEngine(ISimEngine value);

}

