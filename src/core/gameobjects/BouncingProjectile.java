package core.gameobjects;

public abstract class BouncingProjectile extends Projectile {

	public BouncingProjectile(int x, int y, float gravity, float velX, float velY) {
		super(x, y, gravity, velX, velY);
	}

}
