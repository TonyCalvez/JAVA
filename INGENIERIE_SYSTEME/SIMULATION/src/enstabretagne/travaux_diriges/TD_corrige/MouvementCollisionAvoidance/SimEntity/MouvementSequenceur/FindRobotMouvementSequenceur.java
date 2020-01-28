package enstabretagne.travaux_diriges.TD_corrige.MouvementCollisionAvoidance.SimEntity.MouvementSequenceur;

import enstabretagne.simulation.components.IEntity;
import enstabretagne.simulation.components.data.SimFeatures;
import enstabretagne.simulation.core.implementation.SimEvent;
import javafx.geometry.Point3D;

public class FindRobotMouvementSequenceur extends EntityMouvementSequenceur {
    EntityMouvementSequenceurFeature emsf;
    EntityMouvementSequenceurInit emsi;
    Point3D target;

    public FindRobotMouvementSequenceur(String name, SimFeatures features) {
        super(name, features);
    }

    @Override
    protected void AfterActivate(IEntity sender, boolean starting) {
        super.AfterActivate(sender, starting);
        emsf = (EntityMouvementSequenceurFeature) getFeatures();
        emsi = (EntityMouvementSequenceurInit) getInitParameters();
        target = emsi.getTarget();
        initRotation();
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
            target = new Point3D(48,2,0);
            initRotation();
        }
    }
}
