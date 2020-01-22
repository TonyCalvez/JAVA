package enstabretagne.monitor.interfaces;

import javafx.geometry.Point3D;

public interface IMovable {
		Point3D getPosition();
		Point3D getVitesse();
		Point3D getAcceleration();


		Point3D getRotationXYZ();
		Point3D getVitesseRotationXYZ();
		Point3D getAccelerationRotationXYZ();
}
