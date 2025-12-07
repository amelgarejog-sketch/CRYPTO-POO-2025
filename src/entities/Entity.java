package entities;

import java.awt.geom.Rectangle2D;

public abstract class Entity {
    protected float x, y;
    protected int width, height;
    protected Rectangle2D.Float hitbox;

    public Entity(float x, float y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        initHitbox();
    }

    protected void initHitbox() {
        // El hitbox podría ser más pequeño que el sprite visual
        hitbox = new Rectangle2D.Float(x, y, width, height);
    }

    protected void updateHitboxPosition() {
        hitbox.x = x;
        hitbox.y = y;
    }

    public Rectangle2D.Float getHitbox() { return hitbox; }
    public float getX() { return x; }
    public float getY() { return y; }
}
