//package enstabretagne.travaux_diriges.TD_corrige.MouvementCollisionAvoidance;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import enstabretagne.base.time.LogicalDateTime;
//import enstabretagne.base.time.LogicalDuration;
//import enstabretagne.simulation.components.ScenarioId;
//import enstabretagne.simulation.core.IScenario;
//import enstabretagne.simulation.core.IScenarioInstance;
//import enstabretagne.travaux_diriges.TD_corrige.MouvementCollisionAvoidance.SimEntity.Robot.RobotFeatures;
//import enstabretagne.travaux_diriges.TD_corrige.MouvementCollisionAvoidance.SimEntity.Robot.RobotInit;
//import enstabretagne.travaux_diriges.TD_corrige.MouvementCollisionAvoidance.SimEntity.Wall.WallFeatures;
//import enstabretagne.travaux_diriges.TD_corrige.MouvementCollisionAvoidance.SimEntity.Wall.WallInit;
//import enstabretagne.travaux_diriges.TD_corrige.MouvementCollisionAvoidance.scenarios.ScenMvtCollisionAvoidance;
//import enstabretagne.travaux_diriges.TD_corrige.MouvementCollisionAvoidance.scenarios.ScenMvtCollisionAvoidanceFeatures;
//import javafx.geometry.Point3D;
//import javafx.scene.paint.Color;
//
//public class ScenarioInstance_MursSimple implements IScenarioInstance {
//
//	@Override
//	public IScenario getScenarioInstance(long seed) {
//		ScenMvtCollisionAvoidanceFeatures mcaf = new ScenMvtCollisionAvoidanceFeatures("RbScen1");
//
//		// Construction de l'environnement
//
//		List<Point3D> lP;
//		// Les objets qui ne sont pas des vrais murs sont de type 2 (? cot? de ORANGE)
//		WallFeatures wf = new WallFeatures("RF", Color.ORANGE, 2, 0.2, 2.5);
//		WallInit wr;
//		lP = new ArrayList<>();
//		lP.add(new Point3D(0, 0, 0));
//		lP.add(new Point3D(50, 0, 0));
//		lP.add(new Point3D(50, 30, 0));
//		lP.add(new Point3D(0, 30, 0));
//		lP.add(new Point3D(0, 0, 0));
//
//		wr = new WallInit("Mur Piece", new Point3D(0, 2, 0), Point3D.ZERO, lP);
//		mcaf.getWalls().put(wr, wf);
//
////		WallFeatures wf = new WallFeatures("RF", Color.ORANGE, 2, 0.2, 2.5);
//		WallInit wi;
//		lP = new ArrayList<>();
//		lP.add(new Point3D(11, 10, 0));
//		lP.add(new Point3D(11, 15, 0));
//		lP.add(new Point3D(16, 15, 0));
//		lP.add(new Point3D(16, 20, 0));
//		lP.add(new Point3D(21, 20, 0));
//		lP.add(new Point3D(21, 25, 0));
//		wi = new WallInit("Mur Escalier", new Point3D(0, 2, 0), Point3D.ZERO, lP);
//		mcaf.getWalls().put(wi, wf);
//
//		WallInit wj;
//		lP = new ArrayList<>();
//		lP.add(new Point3D(45, 10, 0));
//		lP.add(new Point3D(35, 10, 0));
//		lP.add(new Point3D(35, 20, 0));
//		lP.add(new Point3D(45, 20, 0));
//		wj = new WallInit("Mur Mechant", new Point3D(0, 2, 0), Point3D.ZERO, lP);
//		mcaf.getWalls().put(wj, wf);
//
//
//		WallFeatures wtargetF;
//		WallInit wtargetI;
//		List<Point3D> lT;
//
//		// Les objets qui ne sont pas des vrais murs sont de type 1 (? cot? de RED)
//		// Placement Random:
//		double x_random = getRandomIntegerBetweenRange(10, 40);
//		double y_random = getRandomIntegerBetweenRange(5, 25);
//
//		wtargetF = new WallFeatures("RF", Color.YELLOWGREEN, 1, 1.5, 1);
//		lT = new ArrayList<>();
//		lT.add(new Point3D(0, 0, 0));
//		wtargetI = new WallInit("Table", new Point3D(x_random, y_random, 0), Point3D.ZERO, lT);
//		mcaf.getWalls().put(wtargetI, wtargetF);
//
//		wtargetF = new WallFeatures("RF", Color.YELLOW, 1, 1.5, 1);
//		lT = new ArrayList<>();
//		lT.add(new Point3D(0, 0, 0));
//		wtargetI = new WallInit("Chaise1", new Point3D(x_random - 2, y_random - 2, 0), Point3D.ZERO, lT);
//		mcaf.getWalls().put(wtargetI, wtargetF);
//
//		wtargetF = new WallFeatures("RF", Color.YELLOW, 1, 1.5, 1);
//		lT = new ArrayList<>();
//		lT.add(new Point3D(0, 0, 0));
//		wtargetI = new WallInit("Chaise2", new Point3D(x_random + 2, y_random + 2, 0), Point3D.ZERO, lT);
//		mcaf.getWalls().put(wtargetI, wtargetF);
//
//		// Cr?ation des robots
//		// Cr?ation du robot gentil
//		RobotFeatures rf = new RobotFeatures("RF", 5.0, 100.0, 1);
//		RobotInit ri = new RobotInit("RI", Color.RED, new Point3D(5, 5, 1), new Point3D(0, 0, 45), false,
//				new Point3D(10, 0, 0));
//		mcaf.getRobots().put(ri, rf);
//
//		// Cr?ation du robot m?chant
//		RobotFeatures rfbad = new RobotFeatures("RF", 0, 0, 1);
//
//		RobotInit rb = new RobotInit("BadGuy", Color.BLUE, new Point3D(40, 17.5, 1), new Point3D(0, 0, 90),
//				true, Point3D.ZERO);
//		mcaf.getRobots().put(rb, rfbad);
//
//		// Initialisation du temps
//		LogicalDateTime start = new LogicalDateTime("05/12/2017 06:00");
//		LogicalDateTime end = start.add(LogicalDuration.ofMinutes(2));
//
//		ScenMvtCollisionAvoidance scen = new ScenMvtCollisionAvoidance(new ScenarioId("Scen1"), mcaf, start, end);
//		return scen;
//
//	}
//
//	public static double getRandomIntegerBetweenRange(double min, double max){
//		double x = (int)(Math.random()*((max-min)+1))+min;
//		return x;
//	}
//
//}
