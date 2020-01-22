package enstabretagne.travaux_diriges.TD_corrige.MouvementCollisionAvoidance;

import enstabretagne.base.math.MoreRandom;
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

import java.util.ArrayList;
import java.util.List;

public class ScenarioInstance_BE implements IScenarioInstance {

	private ScenMvtCollisionAvoidanceFeatures mcaf;

	@Override
	public IScenario getScenarioInstance(long seed) {
		this.mcaf = new ScenMvtCollisionAvoidanceFeatures("RbScen1");
		// Construction de l'environnement
		this.buildRoom(seed);
		// Creation du robot gentil
		this.buildNiceRobot();
		// Creation du robot mechant
		this.buildBadRobot();

		// Initialisation du temps
		LogicalDateTime start = new LogicalDateTime("05/12/2017 06:00");
		LogicalDateTime end = start.add(LogicalDuration.ofMinutes(2));

		ScenMvtCollisionAvoidance scen = new ScenMvtCollisionAvoidance(new ScenarioId("Scen1"), mcaf, start, end);
		return scen;
	}

	/**
	 * Construction du robot Recon initial.
	 */
	private void buildNiceRobot(){
		RobotFeatures rf = new RobotFeatures("RF", RobotFeatures.ROBOT_TYPE.RECONNAISSANCE);
		RobotInit ri = new RobotInit("RI", Color.GREENYELLOW, new Point3D(4, 30, 0), new Point3D(0, 0, -90), false,
				new Point3D(48, 28, 0));
		mcaf.getRobots().put(ri, rf);
	}

	/**
	 * Construction du robot ennemi.
	 */
	private void buildBadRobot(){
		RobotFeatures rfbad = new RobotFeatures("RF", RobotFeatures.ROBOT_TYPE.ASSAULT);

		RobotInit rb = new RobotInit("BadGuy", Color.RED, new Point3D(39.5, 17.5, 1), new Point3D(0, 0, 0),
				true, Point3D.ZERO);
		mcaf.getRobots().put(rb, rfbad);
	}

	/**
	 * Construction des trois meubles (table + chaises)à une position aléatoire. Il est possible que cette position aléatoire soit superposé avec un objet existant.
	 * @param seed Graine utilisé pour initialiser le randomiseur de type {@link MoreRandom}.
	 */
	private void buildFurniture(long seed){
		WallFeatures wtargetF;
		WallFeatures wtargetC;
		WallInit wtargetI;
		List<Point3D> lT;
		String[] names = new String[] {"Chaise1", "Chaise2"};
		MoreRandom randomizer = new MoreRandom(seed);
		for (int i=0;i<2;i++){
			double x = randomizer.nextUniform(5,45);
			double y = randomizer.nextUniform(5,25);

			wtargetF = new WallFeatures("RF", WallFeatures.WALL_TYPE.CHAIR, 1.5, 0.5);
			lT = new ArrayList<>();
			lT.add(new Point3D(0, 0, 0));
			wtargetI = new WallInit(names[i], new Point3D(x + 2, y + 2, 0), Point3D.ZERO, lT);
			mcaf.getWalls().put(wtargetI, wtargetF);
		}

		double x = randomizer.nextUniform(5,45);
		double y = randomizer.nextUniform(5,25);
		wtargetC = new WallFeatures("RF", WallFeatures.WALL_TYPE.TABLE, 1.5, 0.5);
		lT = new ArrayList<>();
		lT.add(new Point3D(0, 0, 0));
		wtargetI = new WallInit("Table", new Point3D(x + 2, y + 2, 0), Point3D.ZERO, lT);
		mcaf.getWalls().put(wtargetI, wtargetC);
	}

	/**
	 * Construction de la portion de mur derrière laquelle se cache le robot ennemi.
	 */
	private void buildSquareWall(){
		List<Point3D> lP;
		WallFeatures wf = new WallFeatures("RF", WallFeatures.WALL_TYPE.INSIDE_WALL, 0.2, 2.5);
		WallInit wi;
		lP = new ArrayList<>();
		lP.add(new Point3D(45, 20, 0));
		lP.add(new Point3D(35, 20, 0));
		lP.add(new Point3D(35, 10, 0));
		lP.add(new Point3D(45, 10, 0));
		wi = new WallInit("Niche", new Point3D(2, 2, 0), Point3D.ZERO, lP);
		mcaf.getWalls().put(wi, wf);
	}

	/**
	 * Construction des mur en escalier.
	 */
	private void buildZigZag(){
		//Zigzag
		List<Point3D> lPextz;
		WallFeatures wfextz = new WallFeatures("RF", WallFeatures.WALL_TYPE.INSIDE_WALL, 0.2, 2.5);
		WallInit wiextz;
		lPextz = new ArrayList<>();
		lPextz.add(new Point3D(11, 20, 0));
		lPextz.add(new Point3D(11, 15, 0));
		lPextz.add(new Point3D(16, 15, 0));
		lPextz.add(new Point3D(16, 10, 0));
		lPextz.add(new Point3D(21, 10, 0));
		wiextz = new WallInit("ZigZag", new Point3D(2, 2, 0), Point3D.ZERO, lPextz);
		mcaf.getWalls().put(wiextz, wfextz);
		//
	}

	/**
	 * Construction des murs extérieurs
	 */
	private void buildExtWall(){
		//Murs exterieurs
		List<Point3D> lPext;
		WallFeatures wfext = new WallFeatures("RF", WallFeatures.WALL_TYPE.OUTSIDE_WALL, 0.2, 2.5);
		WallInit wiext;
		lPext = new ArrayList<>();
		lPext.add(new Point3D(0, 0, 0));
		lPext.add(new Point3D(50, 0, 0));
		lPext.add(new Point3D(50, 30, 0));
		lPext.add(new Point3D(0, 30, 0));
		lPext.add(new Point3D(0, 0, 0));
		wiext = new WallInit("Outside Walls", new Point3D(2, 2, 0), Point3D.ZERO, lPext);
		mcaf.getWalls().put(wiext, wfext);
	}

	/**
	 * Construciton du sol.
	 */
	private void buildFloor() {
		List<Point3D> lPext;
		WallFeatures wfext = new WallFeatures("RF", WallFeatures.WALL_TYPE.FLOOR, 30, 0.2);
		WallInit wiext;
		lPext = new ArrayList<>();
		lPext.add(new Point3D(0, 0, 0));
		lPext.add(new Point3D(50, 30, 0));
		wiext = new WallInit("Floor", new Point3D(2, 2, 0), Point3D.ZERO, lPext);
		mcaf.getWalls().put(wiext, wfext);
	}

	/**
	 * Construit la salle.
	 * @param seed
	 */
	private void buildRoom(long seed) {
		this.buildSquareWall();
		this.buildExtWall();
		this.buildZigZag();
		this.buildFloor();
		this.buildFurniture(seed);
	}
}
