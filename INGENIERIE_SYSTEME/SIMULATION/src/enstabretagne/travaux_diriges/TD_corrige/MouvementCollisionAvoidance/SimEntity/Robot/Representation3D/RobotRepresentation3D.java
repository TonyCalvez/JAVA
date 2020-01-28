package enstabretagne.travaux_diriges.TD_corrige.MouvementCollisionAvoidance.SimEntity.Robot.Representation3D;

import java.util.ArrayList;
import java.util.HashMap;

import de.vogella.algorithms.dijkstra.model.IVertex;
import de.vogella.algorithms.dijkstra.model.jfxmodel.Point3DVertex;
import enstabretagne.monitor.Contrat3D;
import enstabretagne.monitor.ObjTo3DMappingSettings;
import enstabretagne.monitor.implementation.Representation3D;
import enstabretagne.monitor.implementation.XYZRotator2;
import enstabretagne.travaux_diriges.TD_corrige.MouvementCollisionAvoidance.Expertise.BorderAndPathGenerator;
import enstabretagne.travaux_diriges.TD_corrige.MouvementCollisionAvoidance.SimEntity.Robot.RobotFeatures;
import javafx.collections.ObservableList;
import javafx.geometry.Bounds;
import javafx.geometry.Point3D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.*;
import javafx.scene.transform.Affine;
import javafx.scene.transform.Rotate;

@Contrat3D(contrat = IRobot3D.class)
public class RobotRepresentation3D extends Representation3D{
	private HashMap<String, Cylinder> currentLineOfSights;
	private IRobot3D robot3D;
	private Group monRobot;
	private Group myworld;
	private HashMap<Point3D, Box> knownPoints;
	private HashMap<String, Cylinder> edges;
	private PhongMaterial gridMaterial;
	private ArrayList<Cylinder> highlightedEdges;
	private ArrayList<Bounds> bounds;

	public RobotRepresentation3D(ObjTo3DMappingSettings settings) {
		super(settings);
	}

	@Override
	public void init(Group world, Object obj) {
		this.gridMaterial = new PhongMaterial(Color.LIGHTBLUE);
		this.edges = new HashMap<>();
		myworld = world;
		this.currentLineOfSights = new HashMap<>();
		this.knownPoints = new HashMap<>();
		this.highlightedEdges = new ArrayList<>();

		robot3D = (IRobot3D) obj;
		monRobot = new Group();
		PhongMaterial bodyMaterial;
		bodyMaterial = new PhongMaterial(robot3D.getColor());


		Sphere body = new Sphere(robot3D.getSize()/2);
		body.setTranslateZ(robot3D.getSize()/2);
		body.setMaterial(bodyMaterial);
		monRobot.getChildren().add(body);
		Sphere head = new Sphere(robot3D.getSize()/2.7);
		head.setTranslateZ(head.getRadius() + robot3D.getSize());
		head.setMaterial(bodyMaterial);
		monRobot.getChildren().add(head);

		PhongMaterial eyeMaterial = new PhongMaterial(Color.BLACK);

		Sphere eye = new Sphere(robot3D.getSize()/8);
		eye.setTranslateZ(head.getRadius() + robot3D.getSize());
		eye.setTranslateY(head.getRadius()/4);
		eye.setTranslateX(head.getRadius());
		eye.setMaterial(eyeMaterial);
		monRobot.getChildren().add(eye);

		if (robot3D.getType().equals(RobotFeatures.ROBOT_TYPE.ASSAULT)){
			Box weapon = new Box(robot3D.getSize()/8,robot3D.getSize()/2,robot3D.getSize()/10);
			weapon.setMaterial(new PhongMaterial(Color.BLACK));
			weapon.setTranslateX(robot3D.getSize()/1.5);
			weapon.setTranslateZ(robot3D.getSize());
			weapon.setTranslateY(robot3D.getSize()/7);
			Rotate rotate = new Rotate(90,new Point3D(0,0,1));
			weapon.getTransforms().add(rotate);
			monRobot.getChildren().add(weapon);
		}
		initBounds();
		robot3D.attach3DRepresentation(this);

		world.getChildren().add(monRobot);
	}

	public ObservableList<Node> getGraphicNodes(){
		return monRobot.getChildren();
	}

	/**
	 * Creates the bounds of this robot.
	 */
	private void initBounds() {
		this.bounds = new ArrayList<>();
		for (Node n : this.getGraphicNodes()){
			this.bounds.add(n.getBoundsInParent());
		}
	}

	@Override
	public void update() {
		if (robot3D.isAlive()) {
			Point3D p = robot3D.getPosition();

			monRobot.setTranslateX(p.getX());
			monRobot.setTranslateY(p.getY());
			monRobot.setTranslateZ(p.getZ());

			Point3D rot = robot3D.getRotationXYZ();

			Affine a = XYZRotator2.getTransformByAngle(rot);
			monRobot.getTransforms().setAll(a);
			updateLineOfSightBadRobot();

		}
		else {
			myworld.getChildren().remove(monRobot);
			myworld.getChildren().remove(currentLineOfSights.get("BadRobot"));
			for (Box square : this.knownPoints.values()){
				myworld.getChildren().remove(square);
			}
			for (Cylinder c : this.edges.values()){
				myworld.getChildren().remove(c);
			}
		}

	}

	private void updateLineOfSightBadRobot() {
		boolean canSee = robot3D.canSeeBadRobot();
		Cylinder c=robot3D.getLineOfSightBadRobot();
		if(c!=null)
			if(!myworld.getChildren().contains(c)) {
				if (currentLineOfSights.containsKey("BadRobot")) {
					myworld.getChildren().remove(currentLineOfSights.get("BadRobot"));
				}
				if (canSee){
					((PhongMaterial)c.getMaterial()).setDiffuseColor(Color.GREEN);
				}
				else {
					((PhongMaterial)c.getMaterial()).setDiffuseColor(Color.RED);
				}
				myworld.getChildren().add(c);
				this.currentLineOfSights.put("BadRobot", c);
			}
	}

	public void attachEdge(Point3DVertex point, Point3DVertex dest) {
		Cylinder c = BorderAndPathGenerator.generateCylinderBetween(point.getPoint(),dest.getPoint());
		c.setMaterial(new PhongMaterial(Color.VIOLET));
		c.setTranslateZ(2);
		this.edges.put(point.getId() + "|" + dest.getId(), c);
		this.myworld.getChildren().add(c);
	}

	public void attachGrid(Point3D point, int i) {
		Box square = new Box(i,i,0.2);
		square.setTranslateX(point.getX());
		square.setTranslateY(point.getY());
		square.setTranslateZ(0.1);
		square.setMaterial(this.gridMaterial);
		this.knownPoints.put(point,square);
		this.myworld.getChildren().add(square);
	}

	public void highlightPath(IVertex first, IVertex second) {
		Cylinder c;
		if (this.edges.containsKey(first.getId() + "|" + second.getId())){
			c = this.edges.get(first.getId() + "|" + second.getId());

		}
		else if (this.edges.containsKey(second.getId() + "|" + first.getId())){
			c = this.edges.get(second.getId() + "|" + first.getId());
		}
		else {
			c = new Cylinder();
		}
		((PhongMaterial)c.getMaterial()).setDiffuseColor(Color.NAVY);
		this.highlightedEdges.add(c);
	}

	public void clearHiglightedEdges(){
		for (Cylinder c : this.highlightedEdges){
			c.setMaterial(new PhongMaterial(Color.VIOLET));
		}
		this.highlightedEdges.clear();
	}

	public ArrayList<Bounds> getBounds() {
		return this.bounds;
	}
}