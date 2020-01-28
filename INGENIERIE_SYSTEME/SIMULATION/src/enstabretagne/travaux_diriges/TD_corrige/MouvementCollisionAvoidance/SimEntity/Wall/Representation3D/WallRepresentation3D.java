package enstabretagne.travaux_diriges.TD_corrige.MouvementCollisionAvoidance.SimEntity.Wall.Representation3D;

import java.util.List;

import enstabretagne.monitor.Contrat3D;
import enstabretagne.monitor.ObjTo3DMappingSettings;
import enstabretagne.monitor.implementation.Representation3D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.DrawMode;
import javafx.scene.shape.Shape3D;

@Contrat3D(contrat = IWall3D.class)
public class WallRepresentation3D extends Representation3D{

	IWall3D wall3D;
	Group wall;
	
	
	public WallRepresentation3D(ObjTo3DMappingSettings settings) {
		super(settings);
	}

	@Override
	public void init(Group world, Object obj) {
		wall3D = (IWall3D) obj;
		wall = new Group();

		PhongMaterial material;
		material = new PhongMaterial(wall3D.getColor());
		
		List<Node> murs = wall3D.getWalls();
		
		for(Node b:murs) {
			if(b instanceof Shape3D)
			((Shape3D) b).setMaterial(material);
			((Shape3D) b).setDrawMode(DrawMode.FILL);
			

			wall.getChildren().add(b);
			
			
		}
		
		
//		monMur.setTranslateX(wall3D.getPosition().getX());
//		monMur.setTranslateY(wall3D.getPosition().getY());
//		monMur.setTranslateZ(wall3D.getPosition().getZ());
		
		world.getChildren().add(wall);

		
	}

	@Override
	public void update() {
		
		
	}
	
	

}
