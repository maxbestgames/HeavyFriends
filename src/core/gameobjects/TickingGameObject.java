package core.gameobjects;

import core.enums.EntityID;
import core.enums.ObjectAction;
import core.enums.ObjectState;
import core.handlers.BoundsHandler;
import core.handlers.MovementHandler;

public abstract class TickingGameObject extends GameObject{

	protected boolean rightStop, leftStop, botStop, topStop;
	protected boolean ledgeEndLeft, ledgeEndRight;
	protected ObjectAction currentAction;
	protected ObjectState currentState;
	protected MovementHandler mov;
	
	protected BoundsHandler objBoundBox;
	
	public TickingGameObject(int x, int y, EntityID id) {
		super(x, y, id);
		objBoundBox = new BoundsHandler(this);
	}

	
	public abstract void tick();
	
	public BoundsHandler getObjBoundBox() {
		return objBoundBox;
	}

	public void setRightStop(boolean rightStop) {
		this.rightStop = rightStop;
	}

	public void setLeftStop(boolean leftStop) {
		this.leftStop = leftStop;
	}

	public void setBotStop(boolean botStop) {
		this.botStop = botStop;
	}
	
	public boolean getBotStop() {
		return botStop;
	}

	public void setTopStop(boolean topStop) {
		this.topStop = topStop;
	}

	public ObjectAction getAction() {
		return currentAction;
	}
	
	public ObjectState getState() {
		return currentState;
	}
	
	public void setAction(ObjectAction currentAction) {
		this.currentAction = currentAction;
	}
	
	public void setState(ObjectState currentState) {
		this.currentState = currentState;
	}


	public boolean isLedgeEndLeft() {
		return ledgeEndLeft;
	}


	public void setLedgeEndLeft(boolean ledgeEndLeft) {
		this.ledgeEndLeft = ledgeEndLeft;
	}


	public boolean isLedgeEndRight() {
		return ledgeEndRight;
	}


	public void setLedgeEndRight(boolean ledgeEndRight) {
		this.ledgeEndRight = ledgeEndRight;
	}
}
