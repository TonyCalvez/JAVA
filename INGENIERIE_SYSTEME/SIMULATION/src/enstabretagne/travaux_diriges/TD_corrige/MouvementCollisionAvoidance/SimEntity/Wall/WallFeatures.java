package enstabretagne.travaux_diriges.TD_corrige.MouvementCollisionAvoidance.SimEntity.Wall;

import enstabretagne.simulation.components.data.SimFeatures;
import javafx.scene.paint.Color;

public class WallFeatures extends SimFeatures{
	public enum WALL_TYPE{
		OUTSIDE_WALL,
		INSIDE_WALL,
		FURNITURE,
		FLOOR;
	}

	private Color color;
	private WALL_TYPE type;
	private double width;
	private double height;

	public WallFeatures(String id, WALL_TYPE type, double width, double height) {
		super(id);
		this.width = width;
		this.height = height;
		this.type = type;
		switch (this.type){
			case OUTSIDE_WALL:
				this.color = Color.DARKGREY;
				break;
			case FLOOR:
				this.color = Color.LIGHTGREY;
				break;
			case INSIDE_WALL:
				this.color = Color.GREEN;
				break;
			case FURNITURE:
				this.color = Color.ORANGE;
				break;
		}
	}

	public double getWidth() {
		return width;
	}

	public double getHeight() {
		return height;
	}

	public Color getCouleur() {
		return color;
	}
	
	public WALL_TYPE getType()
	{
		return type;
	}

	
}
