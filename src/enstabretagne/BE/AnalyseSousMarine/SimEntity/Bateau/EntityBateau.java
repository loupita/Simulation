package enstabretagne.BE.AnalyseSousMarine.SimEntity.Bateau;

import enstabretagne.BE.AnalyseSousMarine.SimEntity.Bateau.Representation3D.EntityBateau3DRepresentationInterface;
import enstabretagne.BE.AnalyseSousMarine.SimEntity.MouvementSequenceur.EntityMouvementSequenceur;
import enstabretagne.BE.AnalyseSousMarine.SimEntity.MouvementSequenceur.EntityMouvementSequenceur_Exemple;
import enstabretagne.BE.AnalyseSousMarine.SimEntity.SousMarin.EntitySousMarinFeature;
import enstabretagne.BE.AnalyseSousMarine.SimEntity.SousMarin.EntitySousMarinInit;
import enstabretagne.BE.AnalyseSousMarine.SimEntity.SousMarin.Representation3D.EntitySousMarin3DRepresentationInterface;
import enstabretagne.base.logger.Logger;
import enstabretagne.base.logger.ToRecord;
import enstabretagne.monitor.interfaces.IMovable;
import enstabretagne.simulation.components.IEntity;
import enstabretagne.simulation.components.data.SimFeatures;
import enstabretagne.simulation.components.data.SimInitParameters;
import enstabretagne.simulation.components.implementation.SimEntity;
import javafx.geometry.Point3D;
import javafx.scene.paint.Color;

@ToRecord(name="Bateau")
public class EntityBateau extends SimEntity implements IMovable,EntityBateau3DRepresentationInterface{
	
	private EntityMouvementSequenceur rmv;
	private EntityBateauInit BateauInit;
	private EntityBateauFeature BateauFeature;
	
	public EntityBateau(String name, SimFeatures features) {
		super(name, features);
		BateauFeature = (EntityBateauFeature) features;
	}

	@Override
	public void onParentSet() {
		
	}

	@Override
	protected void initializeSimEntity(SimInitParameters init) {
		BateauInit = (EntityBateauInit) getInitParameters();

		rmv = (EntityMouvementSequenceur_Exemple) createChild(EntityMouvementSequenceur_Exemple.class, "monSequenceur", ((EntityBateauFeature) getFeatures()).getSeqFeature());
		rmv.initialize(BateauInit.getMvtSeqInitial());
	
	}

	@Override
	protected void BeforeActivating(IEntity sender, boolean starting) {
		
	}

	@Override
	protected void AfterActivate(IEntity sender, boolean starting) {
		Logger.Detail(this, "AfterActivate", "Activation de Navire");
		rmv.activate();
	}
	

	@ToRecord(name="Position")
	@Override
	public Point3D getPosition() {
		return rmv.getPosition(getCurrentLogicalDate());
	}

	@ToRecord(name="Vitesse")
	@Override
	public Point3D getVitesse() {
		return rmv.getVitesse(getCurrentLogicalDate());
	}

	@ToRecord(name="Acceleration")
	@Override
	public Point3D getAcceleration() {
		return rmv.getAcceleration(getCurrentLogicalDate());
	}

	@ToRecord(name="VitesseRotation")
	@Override
	public Point3D getVitesseRotationXYZ() {
		return rmv.getVitesseRotationXYZ(getCurrentLogicalDate());
	}

	@ToRecord(name="AccelerationRotation")
	@Override
	public Point3D getAccelerationRotationXYZ() {
		return rmv.getAccelerationRotationXYZ(getCurrentLogicalDate());
	}

	@ToRecord(name="Rotation")
	@Override
	public Point3D getRotationXYZ() {
		return rmv.getRotationXYZ(getCurrentLogicalDate());
	}


	@Override
	protected void BeforeDeactivating(IEntity sender, boolean starting) {
		rmv.deactivate();
	}

	@Override
	protected void AfterDeactivated(IEntity sender, boolean starting) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void AfterTerminated(IEntity sender, boolean restart) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Color getColor() {
		return BateauFeature.getCouleur();
	}

	@Override
	public double getRayon() {
		return BateauFeature.getRayon();
	}

	@Override
	public double getLongueur() {
		return BateauFeature.getTaille();
	}

}
