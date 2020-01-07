package enstabretagne.travaux_diriges.TD_corrige.MouvementCollisionAvoidance.SimEntity.Robot.Representation3D;

import java.util.List;

import de.vogella.algorithms.dijkstra.model.jfxmodel.Point3DVertex;
import enstabretagne.monitor.interfaces.IMovable;
import enstabretagne.travaux_diriges.TD_corrige.MouvementCollisionAvoidance.SimEntity.Robot.RobotFeatures;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Cylinder;

public interface IRobot3D extends IMovable{
	/**
	 * Getter of the color of the robot body
	 * @return The color of the robot body
	 */
	Color getColor();

	/**
	 * Getter of the size of teh robot. The size of the robot is the height of its body (without head).
	 * @return
	 */
	double getSize();

	/**
	 * Getter of the robot type. A robot is either a Recon robot or an Assault robot.
	 * @return
	 */
	RobotFeatures.ROBOT_TYPE getType();

	/**
	 * @return true if robot is an enemy, false otherwise
	 */
	boolean isBad();

	/**
	 * @return true if the robot can see the bad robot, false otherwise.
	 */
	boolean canSeeBadRobot();

	/**
	 * Getter of the line of sight between teh robot and the enemy.
	 * @return the cylinder representing the line of sight between robot and bad robot.
	 */
	Cylinder getLineOfSightBadRobot();

	/**
	 * @return true if robot is alive, false otherwise
	 */
	boolean isAlive();

	/**
	 * Allow the simulation robot to save its 3D representation.
	 * @param rep3D 3D representation of the robot.
	 */
	void attach3DRepresentation(RobotRepresentation3D rep3D);
}
