package enstabretagne.travaux_diriges.TD_corrige.Simple3D;

import java.util.HashMap;

import com.sun.javafx.geom.transform.GeneralTransform3D;

import enstabretagne.base.math.MoreRandom;
import enstabretagne.base.time.LogicalDateTime;
import enstabretagne.base.time.LogicalDuration;
import enstabretagne.simulation.components.ScenarioId;
import enstabretagne.simulation.core.IScenario;
import enstabretagne.simulation.core.IScenarioInstance;
import enstabretagne.travaux_diriges.TD_corrige.Simple3D.Scenarios.BasicMvtScenario;
import enstabretagne.travaux_diriges.TD_corrige.Simple3D.Scenarios.BasicMvtScenarioFeatures;
import enstabretagne.travaux_diriges.TD_corrige.Simple3D.SimEntity.Bouee.BoueeFeatures;
import enstabretagne.travaux_diriges.TD_corrige.Simple3D.SimEntity.Bouee.BoueeInit;
import javafx.geometry.Point3D;
import javafx.scene.paint.Color;

public class ScenarioInstanceBasicMovement4 implements IScenarioInstance {

	@Override
	public IScenario getScenarioInstance(long seed) {
		BasicMvtScenarioFeatures bsf = new BasicMvtScenarioFeatures("BSF");
		

		//Cr�ation de bouees
		int i=0;
		MoreRandom mr = new MoreRandom(seed);
		
		int N=1;
		double size=100;

		for(i=0;i<N;i++) {
			double x= mr.nextDouble() * size;
			double y = mr.nextDouble() * size;
			
			bsf.getBouees().put(new BoueeFeatures("B"+i,5,1,3.0,1,5.2), new BoueeInit("B"+i,new Point3D(x-size/2,y-size/2,0),Color.RED));
		}

		bsf.getBouees().put(new BoueeFeatures("B"+i,5,1,3.0,0,0), new BoueeInit("B"+i,new Point3D(10,10,-5),Color.BLACK));
		
		LogicalDateTime start = new LogicalDateTime("05/12/2018 06:00");
		LogicalDateTime end = start.add(LogicalDuration.ofMinutes(15));
		BasicMvtScenario bms = new BasicMvtScenario(new ScenarioId("S1 D=5.2 N=1"), bsf, start, end);
		
		return bms;
	}

}
