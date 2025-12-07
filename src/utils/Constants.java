package utils;

import java.awt.event.KeyEvent;

public class Constants {
    // Configuración de Ventana y Bucle
    public static final int GAME_WIDTH = 800;
    public static final int GAME_HEIGHT = 600;
    public static final int FPS_SET = 60;
    public static final int UPS_SET = 60;
    public static final float FLOOR_Y = GAME_HEIGHT - 50; // Altura del suelo

    // Físicas
    public static final float GRAVITY = 0.4f;
    public static final float JUMP_SPEED = -10.0f;
    public static final float WALK_SPEED = 3.0f;

    // Estados del Personaje
    public enum State {
        IDLE, WALKING, JUMPING, FALLING, ATTACKING, HIT, DEAD
    }

    // Tipos de Ataque
    public enum AttackType {
        HIGH, LOW, SPECIAL
    }

    // --- DATA DE ATAQUES ---
    // Un ataque se define por: Daño, Frames Totales, Frame inicio hitbox, Frame fin hitbox, Ancho relativo, Alto relativo
    public static class AttackData {
        int damage, totalFrames, activeStart, activeEnd, width, height, offsetY;
        AttackType type;

        public AttackData(AttackType type, int damage, int totalFrames, int activeStart, int activeEnd, int width, int height, int offsetY) {
            this.type = type; this.damage = damage; this.totalFrames = totalFrames;
            this.activeStart = activeStart; this.activeEnd = activeEnd;
            this.width = width; this.height = height; this.offsetY = offsetY;
        }
    }

    // Definición de los 3 ataques básicos
    // Ataque Alto: Rápido, hitbox arriba.
    public static final AttackData ATK_HIGH = new AttackData(AttackType.HIGH, 10, 20, 5, 10, 40, 20, 10);
    // Ataque Bajo: Medio, hitbox abajo.
    public static final AttackData ATK_LOW = new AttackData(AttackType.LOW, 8, 25, 8, 15, 40, 15, 35);
    // Especial: Lento, mucho daño, hitbox grande.
    public static final AttackData ATK_SPECIAL = new AttackData(AttackType.SPECIAL, 25, 45, 15, 35, 60, 50, 0);

    // --- CONTROLES POR DEFECTO ---
    public static class Controls {
        public int UP, DOWN, LEFT, RIGHT, ATK_H, ATK_L, ATK_S;
        public Controls(int u, int d, int l, int r, int ah, int al, int as) {
            UP=u; DOWN=d; LEFT=l; RIGHT=r; ATK_H=ah; ATK_L=al; ATK_S=as;
        }
    }
    
    // Configuración Jugador 1 (WASD + JKL)
    public static final Controls P1_CONTROLS = new Controls(KeyEvent.VK_W, KeyEvent.VK_S, KeyEvent.VK_A, KeyEvent.VK_D, KeyEvent.VK_J, KeyEvent.VK_K, KeyEvent.VK_L);
    // Configuración Jugador 2 (Flechas + NumPad 123)
    public static final Controls P2_CONTROLS = new Controls(KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT, KeyEvent.VK_NUMPAD1, KeyEvent.VK_NUMPAD2, KeyEvent.VK_NUMPAD3);
}
