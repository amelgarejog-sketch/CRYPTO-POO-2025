package utils;

import entities.Personaje;
import java.awt.geom.Rectangle2D;

public class CollisionManager {

    // Evita que el personaje caiga del suelo
    public static void checkFloorCollision(Personaje p) {
        if (p.getHitbox().y + p.getHitbox().height >= Constants.FLOOR_Y) {
            p.setY(Constants.FLOOR_Y - p.getHitbox().height);
            p.setAirborne(false);
            p.resetAirSpeed();
        } else {
            p.setAirborne(true);
        }
    }

    // Mantiene al personaje dentro de la pantalla
    public static void checkWallCollision(Personaje p) {
        if (p.getHitbox().x < 0) p.setX(0);
        if (p.getHitbox().x + p.getHitbox().width > Constants.GAME_WIDTH)
            p.setX(Constants.GAME_WIDTH - p.getHitbox().width);
    }

    // Evita que los luchadores se atraviesen (empujándose mutuamente)
    public static void checkPlayerPushCollision(Personaje p1, Personaje p2) {
        if (p1.getHitbox().intersects(p2.getHitbox())) {
            float overlapX = (p1.getHitbox().x < p2.getHitbox().x) ?
                 (p1.getHitbox().x + p1.getHitbox().width) - p2.getHitbox().x :
                 (p2.getHitbox().x + p2.getHitbox().width) - p1.getHitbox().x;

            // Empujar a ambos en direcciones opuestas la mitad del solapamiento
            if (p1.getHitbox().x < p2.getHitbox().x) {
                p1.setX(p1.getX() - overlapX / 2);
                p2.setX(p2.getX() + overlapX / 2);
            } else {
                p1.setX(p1.getX() + overlapX / 2);
                p2.setX(p2.getX() - overlapX / 2);
            }
        }
    }

    // Verifica si el ataque de p1 golpeó el cuerpo de p2
    public static boolean checkAttackHit(Personaje attacker, Personaje victim) {
        if (attacker.getCurrentAttack().isActiveFrame()) {
             Rectangle2D.Float attackBox = attacker.getCurrentAttack().getHitbox();
             Rectangle2D.Float victimBody = victim.getHitbox();
             return attackBox.intersects(victimBody);
        }
        return false;
    }
}
