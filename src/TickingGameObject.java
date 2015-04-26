
public abstract class TickingGameObject extends GameObject{

	public TickingGameObject(int x, int y, ID id) {
		super(x, y, id);
	}

	
	public abstract void tick();
}
