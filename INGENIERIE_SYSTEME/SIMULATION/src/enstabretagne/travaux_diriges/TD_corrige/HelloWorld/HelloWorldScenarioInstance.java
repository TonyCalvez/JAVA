package enstabretagne.travaux_diriges.TD_corrige.HelloWorld;

import java.util.HashMap;

import enstabretagne.base.time.LogicalDateTime;
import enstabretagne.base.time.LogicalDuration;
import enstabretagne.simulation.components.ScenarioId;
import enstabretagne.simulation.core.IScenario;
import enstabretagne.simulation.core.IScenarioInstance;
import enstabretagne.travaux_diriges.TD_corrige.HelloWorld.Scenarios.StudentScenario;
import enstabretagne.travaux_diriges.TD_corrige.HelloWorld.Scenarios.StudentScenarioFeatures;
import enstabretagne.travaux_diriges.TD_corrige.HelloWorld.SimEntity.StudentFeatures;
import enstabretagne.travaux_diriges.TD_corrige.HelloWorld.SimEntity.StudentInit;
import enstabretagne.travaux_diriges.TD_corrige.HelloWorld.expertise.Film;

public class HelloWorldScenarioInstance implements IScenarioInstance {

	@Override
	public IScenario getScenarioInstance(long seed) {
		//pour l'exemple on se force ici � mettre un feature � ce niveau.
		//l'id�e est que le sc�nario aie toujours toutes les donn�es pour cr�er le sc�nario.
		//cette fa�on de proc�der o� d�s le d�but on dispose de l'init et du feature est typique de simulations ferm�es.
		HashMap<StudentInit,StudentFeatures> h = new HashMap<>();
		h.put(new StudentInit("John","La douleur n'est qu'une information",Film.rambo,Film.sissi),StudentFeatures.defaultFeatures);
		h.put( new StudentInit("Yoda","La peur m�ne au c�t� obscur de la Force",Film.starwars),StudentFeatures.defaultFeatures);
		h.put( new StudentInit("Anakin","Je suis ton p�re",Film.starwars),StudentFeatures.defaultFeatures);
		h.put(new StudentInit("Luc","Non ce n'est pas vrai, ce n'est pas possible",Film.starwars),StudentFeatures.defaultFeatures);
		h.put(new StudentInit("Leia","Autant embrasser un Wookie!",Film.sissi),StudentFeatures.defaultFeatures);
		
		StudentScenarioFeatures ssf = new StudentScenarioFeatures("Simple Sc�nario", h);
		
		LogicalDateTime start = new LogicalDateTime("05/12/2019 06:00");
		LogicalDateTime end = start.add(LogicalDuration.ofMinutes(2));
		return new StudentScenario(new ScenarioId("S1")	, ssf, start, end);
	}

}
