package enstabretagne.BE.AnalyseSousMarine.SimEntity.Artefact.Representation3D;

import enstabretagne.monitor.interfaces.IMovable;
import javafx.scene.paint.Color;

public interface IArtefactRepresentation3D extends IMovable{
	Color getColor();
	double getSize();
	int getType();

}
