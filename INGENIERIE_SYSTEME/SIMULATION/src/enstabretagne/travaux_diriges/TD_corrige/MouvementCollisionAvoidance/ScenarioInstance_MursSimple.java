package enstabretagne.travaux_diriges.TD_corrige.MouvementCollisionAvoidance;

import java.util.ArrayList;
import java.util.List;

import enstabretagne.base.time.LogicalDateTime;
import enstabretagne.base.time.LogicalDuration;
import enstabretagne.simulation.components.ScenarioId;
import enstabretagne.simulation.core.IScenario;
import enstabretagne.simulation.core.IScenarioInstance;
import enstabretagne.travaux_diriges.TD_corrige.MouvementCollisionAvoidance.SimEntity.Robot.RobotFeatures;
import enstabretagne.travaux_diriges.TD_corrige.MouvementCollisionAvoidance.SimEntity.Robot.RobotInit;
import enstabretagne.travaux_diriges.TD_corrige.MouvementCollisionAvoidance.SimEntity.Wall.WallFeatures;
import enstabretagne.travaux_diriges.TD_corrige.MouvementCollisionAvoidance.SimEntity.Wall.WallInit;
import enstabretagne.travaux_diriges.TD_corrige.MouvementCollisionAvoidance.scenarios.ScenMvtCollisionAvoidance;
import enstabretagne.travaux_diriges.TD_corrige.MouvementCollisionAvoidance.scenarios.ScenMvtCollisionAvoidanceFeatures;
import javafx.geometry.Point3D;
import javafx.scene.paint.Color;

public class ScenarioInstance_MursSimple implements IScenarioInstance {

	@Override
	public IScenario getScenarioInstance(long seed) {
		ScenMvtCollisionAvoidanceFeatures mcaf = new ScenMvtCollisionAvoidanceFeatures("RbScen1");

		// Construction de l'environnement

		List<Point3D> lP;
		// Les objets qui ne sont pas des vrais murs sont de type 2 (� cot� de ORANGE)
		WallFeatures wf = new WallFeatures("RF", Color.ORANGE, 2, 0.2, 2.5);
		WallInit wi;
		lP = new ArrayList<>();
		lP.add(new Point3D(0, 0, 0));
		lP.add(new Point3D(5, 0, 0));
		lP.add(new Point3D(5, 5, 0));
		wi = new WallInit("Mur Simple", new Point3D(0, 2, 0), Point3D.ZERO, lP);
		mcaf.getWalls().put(wi, wf);

		WallFeatures wtargetF;
		WallInit wtargetI;
		List<Point3D> lT;

		// Les objets qui ne sont pas des vrais murs sont de type 1 (� cot� de RED)
		wtargetF = new WallFeatures("RF", Color.RED, 1, 1.5, 0.5);
		lT = new ArrayList<>();
		lT.add(new Point3D(0, 0, 0));
		wtargetI = new WallInit("Table", new Point3D(8, 4, 0), Point3D.ZERO, lT);
		mcaf.getWalls().put(wtargetI, wtargetF);

		// Cr�ation des robots
		// Cr�ation du robot gentil
		RobotFeatures rf = new RobotFeatures("RF", 5.0, 100.0, 0.5);
		RobotInit ri = new RobotInit("RI", Color.AQUA, new Point3D(0, 0, 1), new Point3D(0, 0, 45), false,
				new Point3D(10, 0, 0));
		mcaf.getRobots().put(ri, rf);

		// Cr�ation du robot m�chant
		RobotFeatures rfbad = new RobotFeatures("RF", 0, 0, 0.5);

		RobotInit rb = new RobotInit("BadGuy", Color.MEDIUMVIOLETRED, new Point3D(2.5, 4, 1), new Point3D(0, 0, 90),
				true, Point3D.ZERO);
		mcaf.getRobots().put(rb, rfbad);

		// Initialisation du temps
		LogicalDateTime start = new LogicalDateTime("05/12/2017 06:00");
		LogicalDateTime end = start.add(LogicalDuration.ofMinutes(2));

		ScenMvtCollisionAvoidance scen = new ScenMvtCollisionAvoidance(new ScenarioId("Scen1"), mcaf, start, end);
		return scen;
	}

}
