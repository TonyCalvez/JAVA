package enstabretagne.travaux_diriges.TD_corrige.MouvementCollisionAvoidance.SimEntity.Wall;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import enstabretagne.base.logger.Logger;
import enstabretagne.monitor.interfaces.IMovable;
import enstabretagne.simulation.components.IEntity;
import enstabretagne.simulation.components.data.SimFeatures;
import enstabretagne.simulation.components.data.SimInitParameters;
import enstabretagne.simulation.components.implementation.SimEntity;
import enstabretagne.travaux_diriges.TD_corrige.MouvementCollisionAvoidance.Expertise.BorderAndPathGenerator;
import enstabretagne.travaux_diriges.TD_corrige.MouvementCollisionAvoidance.SimEntity.Wall.Representation3D.IWall3D;
import javafx.geometry.Bounds;
import javafx.geometry.Point3D;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Box;
import javafx.scene.transform.Translate;
import org.jscience.mathematics.vector.Matrix;

public class Wall extends SimEntity implements IMovable,IWall3D{

	WallInit wIni;
	WallFeatures wFeat;
	
	public Wall(String name, SimFeatures features) {
		super(name, features);
		wFeat = (WallFeatures) features;
		murs = new ArrayList<>();
		borders = new HashMap<>();
	}

	@Override
	public String canSeeTable() {
		return null;
	}

	@Override
	public Color getColor() {
		return wFeat.getCouleur();
	}

	@Override
	public WallFeatures.WALL_TYPE getType() {
		return wFeat.getType();
	}

	@Override
	public double getWidth() {
		return wFeat.getWidth();
	}

	@Override
	public double getHeight() {
		return wFeat.getHeight();
	}

	@Override
	public List<Point3D> getWallShape() {
		return wIni.getPath();
	}

	@Override
	public Point3D getPosition() {
		return wIni.getPosInit();
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
		return wIni.getRotationXYZInit();
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

	@Override
	public void onParentSet() {
	}

	@Override
	protected void initializeSimEntity(SimInitParameters init) {
		wIni = (WallInit) getInitParameters();
		generateMurs();
	}

	@Override
	protected void BeforeActivating(IEntity sender, boolean starting) {
	}

	@Override
	protected void AfterActivate(IEntity sender, boolean starting) {
		Logger.Detail(this, "AfterActivate", "Activation du robot","test");		
		
	}

	@Override
	protected void BeforeDeactivating(IEntity sender, boolean starting) {
	}

	@Override
	protected void AfterDeactivated(IEntity sender, boolean starting) {
	}

	@Override
	protected void AfterTerminated(IEntity sender, boolean restart) {
	}

	HashMap<String,List<Point3D>> borders;
	@Override
	public List<Point3D> getGroundBorder(double d, double detectsize) {
		String id = d+"#"+detectsize;
		if(borders.containsKey(id))
			return borders.get(id);

		List<Point3D> border = BorderAndPathGenerator.getBorder(this,d,detectsize);
		borders.put(id, border);
		return border;
	}

	


	
	List<Node> murs;
	@Override
	public List<Node> getWalls() {
		return murs;
	}
	
	//Le Bounds en Javafx correspond � la boite englobante du mod�le 3D
	//C'est ce qu'utilise l'outil d'intersection de JavaFX
	@Override
	public List<Bounds> getBounds(double dSecurity){
		List<Bounds> ns = new ArrayList<>(); 

		for (Node n : generateMursWithDistanceOfSecurity(dSecurity)) {
			ns.add(n.getBoundsInParent());
			}
		return ns;
	}

	@Override
	public List<Bounds> getBounds() {
		
		return getBounds(0);
	}

	//Appel � l'algorithme de g�n�ration des murs selon une distance de s�curit� autour du mur
	//correspondant � la largeur du robot afin que le robot ne heurte pas les paroies
	@Override
	public List<Node> generateMursWithDistanceOfSecurity(double distanceOfSecurity) {
		List<Node> ns = new ArrayList<>();
		if (((WallFeatures) getFeatures()).getType().equals(WallFeatures.WALL_TYPE.FLOOR)){
			List<Point3D> path = ((WallInit) getInitParameters()).getPath();
			if (path.size() == 2){
				WallFeatures feature = (WallFeatures) getFeatures();
				Box floor = new Box(Math.abs(path.get(1).getX() - path.get(0).getX()), feature.getWidth(), feature.getHeight());
				floor.setTranslateX(floor.getWidth()/2);
				floor.setTranslateY(floor.getHeight()/2);
				ns.add(floor);
			}
		}
		else {
			((WallInit) getInitParameters()).generateMurs(getWidth(),getHeight(),distanceOfSecurity,ns);
		}
		for (Node n : ns) {
			Translate t = new Translate(getPosition().getX(), getPosition().getY(), getPosition().getZ());
			
			n.getTransforms().add(0, t);
		}
		return ns;
	}

	//G�n�ration des murs sans distance de s�curit�. C'est le mur qu'on affiche
	public void generateMurs() {
		murs.clear();
		murs.addAll(generateMursWithDistanceOfSecurity(1));
	}


}
