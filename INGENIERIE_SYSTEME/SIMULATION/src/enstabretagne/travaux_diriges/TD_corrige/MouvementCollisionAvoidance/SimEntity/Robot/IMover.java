package enstabretagne.travaux_diriges.TD_corrige.MouvementCollisionAvoidance.SimEntity.Robot;

import enstabretagne.base.time.LogicalDateTime;
import enstabretagne.base.time.LogicalDuration;
import javafx.geometry.Point3D;

public interface IMover {
	LogicalDuration getDurationToReach();
	Point3D getPosition(LogicalDateTime d);
	Point3D getVitesse(LogicalDateTime d);
	Point3D getAcceleration(LogicalDateTime d);


	Point3D getRotationXYZ(LogicalDateTime d);
	Point3D getVitesseRotationXYZ(LogicalDateTime d);
	Point3D getAccelerationRotationXYZ(LogicalDateTime d);

}
