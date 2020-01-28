package enstabretagne.travaux_diriges.TD_corrige.MouvementCollisionAvoidance.SimEntity.MouvementSequenceur;

import enstabretagne.base.time.LogicalDateTime;
import enstabretagne.base.time.LogicalDuration;
import enstabretagne.monitor.implementation.MovableState;
import enstabretagne.monitor.implementation.XYZRotator2;
import enstabretagne.travaux_diriges.TD_corrige.MouvementCollisionAvoidance.SimEntity.Robot.IMover;
import javafx.geometry.Point3D;
import javafx.scene.transform.Rotate;

public class SelfRotator implements IMover {

	MovableState initState;
	Point3D rotationXYZCible;
	double vRotY;
	double vRotZ;
	double timeToReach;

    public void init(LogicalDateTime d, Point3D positionInitiale, Point3D rotationXYZ, Point3D pointAViser,	double vitRot) {


		Point3D dirFinale = pointAViser.subtract(positionInitiale).normalize();
		rotationXYZCible = XYZRotator2.computeRotationXYZ(dirFinale);
		
		double dAngleY = rotationXYZCible.getY() - rotationXYZ.getY();
		double dAngleZ = rotationXYZCible.getZ() - rotationXYZ.getZ();

		if (vitRot != 0) {
			timeToReach = Math.max(Math.abs(dAngleY), Math.abs(dAngleZ)) / vitRot;
			if (timeToReach == 0) {
				vRotY = 0;
				vRotZ = 0;
			} else {
				if(dAngleY>180)
					dAngleY = dAngleY-360;//pour tourner en sens inverse
				if(dAngleZ>180)
					dAngleZ = dAngleZ-360;//pour tourner en sens inverse
				vRotY = dAngleY / timeToReach;
				vRotZ = dAngleZ / timeToReach;
			}
		}
		else
		{
			timeToReach=Double.NaN;
			vRotY = 0;
			vRotZ = 0;
		}

		Point3D vitesseRotationXYZ = new Point3D(0, vRotY, vRotZ);

		initState = new MovableState(positionInitiale, Point3D.ZERO, Point3D.ZERO, rotationXYZ, vitesseRotationXYZ,
				Point3D.ZERO);
		initState.setT(d);
	}
	

	public LogicalDuration getDurationToReach() {

		if(timeToReach!=Double.NaN)
			return LogicalDuration.ofSeconds(timeToReach);
		else
			return LogicalDuration.POSITIVE_INFINITY;
					
	}


	public Point3D getPosition(LogicalDateTime d) {
		Point3D ptemp = initState.getPosition();
		return ptemp;
	}

	public Point3D getVitesse(LogicalDateTime d) {
		Point3D dir = XYZRotator2.getTransformByAngle(getRotationXYZ(d)).transform(Rotate.X_AXIS);
		return dir;
	}

	public Point3D getAcceleration(LogicalDateTime d) {
		return initState.getAcceleration();
	}

	public Point3D getRotationXYZ(LogicalDateTime d) {
		double dt = d.soustract(initState.getT()).DoubleValue();
		Point3D vAngle = getVitesseRotationXYZ(d);
		Point3D iAngle = initState.getRotationXYZ();
		Point3D pAngle = new Point3D(iAngle.getX() + vAngle.getX() * dt, iAngle.getY() + vAngle.getY() * dt,
				iAngle.getZ() + vAngle.getZ() * dt);

		return pAngle;

	}

	public Point3D getVitesseRotationXYZ(LogicalDateTime d) {
		return initState.getRotationSpeedXYZ();

	}

	public Point3D getAccelerationRotationXYZ(LogicalDateTime d) {
		return initState.getAccelerationRotationXYZ();
	}
}
