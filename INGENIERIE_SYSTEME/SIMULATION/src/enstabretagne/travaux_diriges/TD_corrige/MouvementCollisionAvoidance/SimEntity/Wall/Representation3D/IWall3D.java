package enstabretagne.travaux_diriges.TD_corrige.MouvementCollisionAvoidance.SimEntity.Wall.Representation3D;

import java.util.List;

import enstabretagne.monitor.interfaces.IMovable;
import enstabretagne.travaux_diriges.TD_corrige.MouvementCollisionAvoidance.SimEntity.Wall.WallFeatures;
import javafx.geometry.Bounds;
import javafx.geometry.Point3D;
import javafx.scene.Node;
import javafx.scene.paint.Color;

public interface IWall3D extends IMovable{
	Color getColor();
	WallFeatures.WALL_TYPE getType();
	
	double getWidth();
	double getHeight();
	
	List<Node> getWalls();
	List<Node> generateMursWithDistanceOfSecurity(double distanceOfSecurity);
	
	List<Point3D> getWallShape();
	List<Point3D> getGroundBorder(double d, double detectsize);


	List<Bounds> getBounds();
	List<Bounds> getBounds(double d);
}
