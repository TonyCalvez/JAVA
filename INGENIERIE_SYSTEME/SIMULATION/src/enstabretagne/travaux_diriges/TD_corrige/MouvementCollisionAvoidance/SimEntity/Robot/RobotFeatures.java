package enstabretagne.travaux_diriges.TD_corrige.MouvementCollisionAvoidance.SimEntity.Robot;

import enstabretagne.simulation.components.data.SimFeatures;
import enstabretagne.travaux_diriges.TD_corrige.MouvementCollisionAvoidance.SimEntity.MouvementSequenceur.EntityMouvementSequenceurFeature;

public class RobotFeatures extends SimFeatures{
	public enum ROBOT_TYPE {
		RECONNAISSANCE,
		ASSAULT;
	}

	private double vitesseMax;
	private double accelerationMax;
	private double rotationSpeedMax;
	private double taille;
	private ROBOT_TYPE type;
		
	private EntityMouvementSequenceurFeature emsf;
	public EntityMouvementSequenceurFeature getEmsf() {
		return emsf;
	}
	
	public double getVitesseMax() {
		return vitesseMax;
	}
	public double getRotationSpeedMax() {
		return rotationSpeedMax;
	}
	public double getTaille() { return taille; }
	public ROBOT_TYPE getType() { return type; }
	public RobotFeatures(String id, ROBOT_TYPE type)
	{
		super(id);
		this.taille = 2;
		this.type = type;
		switch (type){
			case RECONNAISSANCE:
				this.vitesseMax = 5;
				this.rotationSpeedMax = 100;
				break;
			case ASSAULT:
				this.vitesseMax = 2.5;
				this.rotationSpeedMax = 50;
				break;
		}
		this.emsf = new EntityMouvementSequenceurFeature(
				"Sequenceur",
				vitesseMax,
				rotationSpeedMax);
	}
	
	
	
}
