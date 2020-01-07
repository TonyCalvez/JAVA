package enstabretagne.travaux_diriges.TD_corrige.MouvementCollisionAvoidance.SimEntity.MouvementSequenceur;

import enstabretagne.simulation.components.IEntity;
import enstabretagne.simulation.components.data.SimFeatures;
import enstabretagne.simulation.core.implementation.SimEvent;
import enstabretagne.travaux_diriges.TD_corrige.MouvementCollisionAvoidance.SimEntity.Robot.Robot;
import javafx.geometry.Point3D;

public class BackToInitialPositionMouvementSequenceur extends EntityMouvementSequenceur{

	EntityMouvementSequenceurFeature emsf;
	EntityMouvementSequenceurInit emsi;
	Point3D target;
	
	public BackToInitialPositionMouvementSequenceur(String name, SimFeatures features) {
		super(name, features);
	}
	
	@Override
	protected void AfterActivate(IEntity sender, boolean starting) {
		super.AfterActivate(sender, starting);
		emsf = (EntityMouvementSequenceurFeature) getFeatures();
		emsi = (EntityMouvementSequenceurInit) getInitParameters();
		Post(new RectilinearMouvementFinished());
	}

	private void initRotation(){
		selfRotator.init(getCurrentLogicalDate(), mv.getPosition(getCurrentLogicalDate()),
				mv.getRotationXYZ(getCurrentLogicalDate()), target, emsf.getMaxSelfRotationSpeed());
		mv = selfRotator;
		Post(new SelfRotationFinished(), mv.getDurationToReach());
	}


	class SelfRotationFinished extends SimEvent {

		@Override
		public void Process() {
			if (emsf.getMaxLinearSpeed() != 0) {
				rectilinearMover.init(getCurrentLogicalDate(), mv.getPosition(getCurrentLogicalDate()),
						target, emsf.getMaxLinearSpeed());
				mv = rectilinearMover;
				Post(new RectilinearMouvementFinished(), mv.getDurationToReach());
			}

		}
	}

	class RectilinearMouvementFinished extends SimEvent {

		@Override
		public void Process() {
			BackToInitialPositionMouvementSequenceur e= (BackToInitialPositionMouvementSequenceur) Owner();
			Robot r = (Robot) e.getParent();
			if (!r.isReturning()) {
				r.initiateReturn();
			}
			target = r.getNext();
			if (target != null){
				initRotation();
			}
			else {
				staticMover.init(mv.getPosition(getCurrentLogicalDate()), mv.getRotationXYZ(getCurrentLogicalDate()));
				mv = staticMover;
				r.get3DRepresentation().clearHiglightedEdges();
			}
		}
	}
}
