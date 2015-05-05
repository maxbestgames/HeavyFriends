package core.gameobjects.projectiles;

import core.enums.ProjectileType;
import core.gameobjects.BouncingProjectile;
import core.visualgronk.Animation;
import core.visualgronk.Texture;

public class FireBall extends BouncingProjectile {
	
	ProjectileType type;
	
	public FireBall(int x, int y, float gravity, float velX, float velY) {
		super(x, y, gravity, velX, velY);
		type = ProjectileType.Fireball;
		
		tex = new Texture("assets/texture/fire.png", 32, 32);
		anim = new Animation(5, tex.getSprite(0, 0), tex.getSprite(1, 0), tex.getSprite(2, 0), tex.getSprite(3, 0));
		
		height = 32;
		width = 32;

	}

}
