package entities;

import utils.Constants;
import utils.Constants.State;
import java.awt.Color;
import java.awt.Graphics;

// Hereda de Entity, añade físicas, vida y estados
public abstract class Personaje extends Entity {

    protected int maxHealth = 100;
    protected int currentHealth;
    protected float airSpeed;
    protected boolean inAir = false;
    protected int direction = 1; // 1 derecha, -1 izquierda
    protected State state = State.IDLE;

    public Personaje(float x, float y, int width, int height) {
        super(x, y, width, height);
        this.currentHealth = maxHealth;
    }

    public void update() {
        updatePosition();
        updateHitboxPosition();
    }

    protected void updatePosition() {
        // Gravedad simple
        if (inAir) {
            airSpeed += Constants.GRAVITY;
            y += airSpeed;
        }
    }

    public void jump() {
        if (!inAir) {
            inAir = true;
            airSpeed = Constants.JUMP_SPEED;
            state = State.JUMPING;
        }
    }

    public void move(float speed) {
        // Solo moverse si no está atacando o siendo golpeado
        if (state != State.ATTACKING && state != State.HIT && state != State.DEAD) {
            x += speed;
            direction = (speed > 0) ? 1 : -1;
            if (!inAir) state = State.WALKING;
        }
    }
    
    public void takeDamage(int amount) {
        if (state == State.DEAD) return;
        this.currentHealth -= amount;
        System.out.println("Personaje recibió " + amount + " de daño. Vida: " + currentHealth);
        state = State.HIT;
        if (currentHealth <= 0) {
            currentHealth = 0;
            state = State.DEAD;
        }
        // Pequeño empuje hacia atrás al recibir golpe
        this.x -= (direction * 10); 
    }

    // Método abstracto para renderizar, las subclases deciden su color/sprite
    public abstract void render(Graphics g);

    // Getters y Setters necesarios para la física y el combate
    public void setX(float x) { this.x = x; }
    public void setY(float y) { this.y = y; }
    public void setAirborne(boolean airborne) { this.inAir = airborne; if(!inAir && state == State.JUMPING) state = State.IDLE;}
    public void resetAirSpeed() { this.airSpeed = 0; }
    public State getState() { return state; }
    public void setState(State s) { this.state = s; }
    public abstract combat.Ataque getCurrentAttack();
}
