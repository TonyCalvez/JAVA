package enstabretagne.travaux_diriges.TD_corrige.MouvementCollisionAvoidance.SimEntity.Robot;

import java.util.ArrayList;
import java.util.List;

import enstabretagne.base.logger.Logger;
import enstabretagne.base.time.LogicalDuration;
import enstabretagne.monitor.interfaces.IMovable;
import enstabretagne.simulation.components.IEntity;
import enstabretagne.simulation.components.data.SimFeatures;
import enstabretagne.simulation.components.data.SimInitParameters;
import enstabretagne.simulation.components.implementation.SimEntity;
import enstabretagne.simulation.core.implementation.SimEvent;
import enstabretagne.travaux_diriges.TD_corrige.MouvementCollisionAvoidance.Expertise.BorderAndPathGenerator;
import enstabretagne.travaux_diriges.TD_corrige.MouvementCollisionAvoidance.SimEntity.MouvementSequenceur.EntityMouvementSequenceur;
import enstabretagne.travaux_diriges.TD_corrige.MouvementCollisionAvoidance.SimEntity.MouvementSequenceur.BackToInitialPositionMouvementSequenceur;
import enstabretagne.travaux_diriges.TD_corrige.MouvementCollisionAvoidance.SimEntity.MouvementSequenceur.EntityMouvementSequenceurInit;
import enstabretagne.travaux_diriges.TD_corrige.MouvementCollisionAvoidance.SimEntity.MouvementSequenceur.FindRobotMouvementSequenceur;
import enstabretagne.travaux_diriges.TD_corrige.MouvementCollisionAvoidance.SimEntity.Robot.Representation3D.IRobot3D;
import enstabretagne.travaux_diriges.TD_corrige.MouvementCollisionAvoidance.SimEntity.Robot.Representation3D.RobotRepresentation3D;
import enstabretagne.travaux_diriges.TD_corrige.MouvementCollisionAvoidance.SimEntity.Wall.Wall;
import enstabretagne.travaux_diriges.TD_corrige.MouvementCollisionAvoidance.SimEntity.Wall.WallFeatures;
import javafx.geometry.Bounds;
import javafx.geometry.Point3D;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Cylinder;

public class Robot extends SimEntity implements IMovable, IRobot3D {
	private RobotInit rIni;
	private RobotFeatures rFeat;
	private EntityMouvementSequenceur rmv;
	private Cylinder lineOfSightBadRobot;
	private double healthPoint;
	private DiscoveredMap map;
	private RobotRepresentation3D rep3D;
	private boolean isReturning;
	private List<Bounds> obstacleBounds;

	// IMovable methods
	@Override
	public Point3D getPosition() {
		return rmv.getPosition(getCurrentLogicalDate());
	}

	@Override
	public Point3D getVitesse() {
		return null;
	}

	public Point3D getSpeed() {
		return rmv.getVitesse(getCurrentLogicalDate());
	}

	@Override
	public Point3D getAcceleration() {
		return rmv.getAcceleration(getCurrentLogicalDate());
	}

	@Override
	public Point3D getRotationXYZ() {
		return rmv.getRotationXYZ(getCurrentLogicalDate());
	}

	@Override
	public Point3D getVitesseRotationXYZ() {
		return null;
	}

	public Point3D getRotationSpeedXYZ() {
		return rmv.getVitesseRotationXYZ(getCurrentLogicalDate());
	}

	@Override
	public Point3D getAccelerationRotationXYZ() {
		return rmv.getAccelerationRotationXYZ(getCurrentLogicalDate());
	}

	//IRobot3D methods
	@Override
	public boolean isAlive(){
		return this.getStatus().isALIVE();
	}

	@Override
	public Color getColor() {
		return rIni.getCouleur();
	}

	@Override
	public double getSize() {
		return rFeat.getTaille();
	}

	@Override
	public RobotFeatures.ROBOT_TYPE getType() {
		return ((RobotFeatures)getFeatures()).getType();
	}

	@Override
	public boolean isBad() {
		return rIni.isBad();
	}


	public Robot(String name, SimFeatures features) {
		super(name, features);
		rFeat = (RobotFeatures) features;
	}

	@Override
	public String canSeeTable() {
		return null;
	}  // need to be effective

	@Override
	protected void initializeSimEntity(SimInitParameters init) {
		rIni = (RobotInit) getInitParameters();
		this.setInitialState();
		this.initFirstMouvementSequenceur();
		Logger.Information(this,"initializeSimEntity", String.format("The Robot %s has been initialized with the %s type. Bad= %b", getName(),getType().toString(),isBad()));
	}

	private void initFirstMouvementSequenceur() {
		if (rIni.isBad()){
			rmv = (EntityMouvementSequenceur) createChild(EntityMouvementSequenceur.class, "Mvt", rFeat.getEmsf());
		}
		else{
			rmv = (EntityMouvementSequenceur) createChild(FindRobotMouvementSequenceur.class, "Mvt", rFeat.getEmsf());
		}
		rmv.initialize(rIni.getMvtSeqIni());
	}

	private void setInitialState() {
		this.healthPoint = 100;
		this.map = new DiscoveredMap(this, 2);
		this.isReturning = false;
	}

	@Override
	protected void AfterActivate(IEntity sender, boolean starting) {
		rmv.activate();
		if (!isBad())Post(new DetectEvent(this), LogicalDuration.ofMillis(100));
		this.obstacleBounds = this.generateBoundsForObstacles();
	}

	@Override
	public boolean canSeeBadRobot() {
		List<Wall> walls = (List<Wall>) (List<?>) getEngine()
				.requestSimObject(simo -> (simo instanceof Wall) && (((Wall) simo).getType().equals(WallFeatures.WALL_TYPE.INSIDE_WALL) || ((Wall) simo).getType().equals(WallFeatures.WALL_TYPE.OUTSIDE_WALL)));
		List<Bounds> bounds = new ArrayList();
		for (Wall w : walls) {
			bounds.addAll(w.getBounds());
		}

		Robot badRobot = null;
		List<Robot> badRobots = (List<Robot>) (List<?>) getEngine().requestSimObject(simo -> (simo instanceof Robot && ((Robot) simo).isBad()) && simo.getSimObjID() != this.getSimObjID());
		if (badRobots.size() == 1) {
			badRobot = badRobots.get(0);

			lineOfSightBadRobot = BorderAndPathGenerator.generateCylinderBetween(badRobot.getPosition(), getPosition());
			lineOfSightBadRobot.setMaterial(new PhongMaterial(Color.AQUA));
			lineOfSightBadRobot.setTranslateZ(this.getSize()/2);

			boolean isVisible = BorderAndPathGenerator.intervisibilityBetween(badRobot.getPosition(), getPosition(),
					bounds);
			return isVisible;
		} else
			return false;
	}

	public void resolveSimpleHit(){
		boolean before = isDamaged();
		switch (this.getType()){
			case RECONNAISSANCE:
				this.healthPoint -= 50;
				break;
			case ASSAULT:
				this.healthPoint -= 12.5;
				break;
		}
		this.checkIfDamaged(before);
		this.checkIfDead();
	}

	private void checkIfDamaged(boolean before) {
		if (!before && isDamaged()){
			Post(new JustDamagedEvent());
		}
	}

	public void resolveCriticalHit(){
		boolean before = isDamaged();
		switch (this.getType()){
			case RECONNAISSANCE:
				this.healthPoint -= 80;
				break;
			case ASSAULT:
				this.healthPoint -= 20;
				break;
		}
		this.checkIfDamaged(before);
		this.checkIfDead();
	}


	private void checkIfDead() {
		if (this.healthPoint <= 0){
			this.deactivate();
		}
	}

	@Override
	public Cylinder getLineOfSightBadRobot() {
		return lineOfSightBadRobot;
	}

	@Override
	protected void BeforeDeactivating(IEntity sender, boolean starting) {
		rmv.deactivate();
	}

	@Override
	protected void BeforeActivating(IEntity sender, boolean starting) {

	}

	@Override
	protected void AfterDeactivated(IEntity sender, boolean starting) {

	}

	@Override
	protected void AfterTerminated(IEntity sender, boolean restart) {

	}

	@Override
	public void onParentSet() {

	}

	public double getRange() {
		switch (getType()){
			case RECONNAISSANCE:
				return 15;
			case ASSAULT:
				return 7.5;
		}
			return 0;
	}

	public Point3D getInitialPosition() {
		return ((RobotInit)this.getInitParameters()).getPosInit();
	}

	public RobotRepresentation3D get3DRepresentation() {
		return this.rep3D;
	}

	public void initiateReturn() {
		this.isReturning = true;
		this.map.generatePath();
	}

	public void stopReturn() {
		this.isReturning = false;
	}

	public Point3D getNext() {
		return this.map.getNext();
	}

	public boolean isReturning() {
		return this.isReturning;
	}

	public List<Bounds> getObstacleBounds() {
		return this.obstacleBounds;
	}

	private class DetectEvent extends SimEvent {
		Robot robot;

		public DetectEvent(Robot robot) {
			this.robot = robot;
		}

		@Override
		public void Process() {
			this.robot.getMap().discover();
			if (isBadRobotIdentified()){
				Point3D currentPosition = getPosition();
				Point3D currentRotation = getRotationXYZ();
				rmv.deactivate();
				rmv = (EntityMouvementSequenceur) createChild(BackToInitialPositionMouvementSequenceur.class, "Mvt1",
						rFeat.getEmsf());
				rmv.initialize(new EntityMouvementSequenceurInit("Flee",
						new IMovable() {
							@Override
							public Point3D getPosition() {
								return currentPosition;
							}

							@Override
							public Point3D getVitesse() {
								return null;
							}


							public Point3D getSpeed() {
								return Point3D.ZERO;
							}

							@Override
							public Point3D getAcceleration() {
								return Point3D.ZERO;
							}

							@Override
							public Point3D getRotationXYZ() {
								return currentRotation;
							}

							@Override
							public Point3D getVitesseRotationXYZ() {
								return null;
							}

							public Point3D getRotationSpeedXYZ() {
								return Point3D.ZERO;
							}

							@Override
							public Point3D getAccelerationRotationXYZ() {
								return Point3D.ZERO;
							}
						},
						null));
				rmv.activate();
			}
			Post(new DetectEvent(this.robot), LogicalDuration.ofSeconds(1));
		}

		private boolean isBadRobotIdentified() {
			return !getEngine().requestSimObject(simo -> (simo instanceof Robot && ((Robot) simo).isBad() &&((Robot) simo).getPosition().distance(robot.getPosition()) < robot.getIdentificationRange())).isEmpty();

		}
	}

	private double getIdentificationRange() {
		switch (getType()){
			case RECONNAISSANCE:
				return 10;
			case ASSAULT:
				return 5;
		}
		return 0;
	}

	private DiscoveredMap getMap() {
		return this.map;
	}

	public List<Bounds> generateBoundsForObstacles(){
		List<Wall> walls = (List<Wall>) (List<?>) getEngine()
				.requestSimObject(simo -> (simo instanceof Wall) && (((Wall) simo).getType().equals(WallFeatures.WALL_TYPE.INSIDE_WALL) || ((Wall) simo).getType().equals(WallFeatures.WALL_TYPE.OUTSIDE_WALL) || ((Wall) simo).getType().equals(WallFeatures.WALL_TYPE.CHAIR)));
		List<Bounds> bounds = new ArrayList();
		for (Wall w : walls) {
			bounds.addAll(w.getBounds());
		}
		List<Robot> robots = (List<Robot>) (List<?>) getEngine().requestSimObject(simo -> (simo instanceof Robot && simo != this));
		for (Robot r : robots){
			bounds.addAll(r.getBounds());
		}
		return bounds;
	}

	public ArrayList<Bounds> getBounds() {
		return this.rep3D.getBounds();
	}

	@Override
	public void attach3DRepresentation(RobotRepresentation3D rep) {
		this.rep3D = rep;
	}

	public double getNominalSpeed(){
		switch (getType()) {
			case RECONNAISSANCE:
				if (isDamaged()) {
					return 2.5;
				} else {
					return 5;
				}
			case ASSAULT:
				if (isDamaged()) {
					return 2.5;
				}
				else {
					return 1.25;
				}
		}
		return 0;
	}

	public double getNominalRotationSpeed(){
		switch (getType()) {
			case RECONNAISSANCE:
				if (isDamaged()) {
					return 50;
				} else {
					return 100;
				}
			case ASSAULT:
				if (isDamaged()) {
					return 25;
				}
				else {
					return 50;
				}
		}
		return 0;
	}

	private boolean isDamaged() {
		return (healthPoint<50);
	}

	private class JustDamagedEvent extends SimEvent {
		@Override
		public void Process() {
			rmv.changeSpeed();
		}
	}
}
