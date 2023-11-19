package br.com.mariojp.game;
import java.util.Random;

public class MovimentoBoss implements IMovimento {
    
    // Constantes para limites e velocidades
    private static final int LIMITE_ESQUERDO = 0;
    private static final int LIMITE_SUPERIOR = 0;
    private static final int LIMITE_INFERIOR = 600;
    private static final int VELOCIDADE_HORIZONTAL = 2;
    private static final int VELOCIDADE_VERTICAL = 3;

    // Variável para controlar se o boss está se movendo para cima ou para baixo
    private boolean subindo = true;

    // Instância de Random para gerar números aleatórios
    private Random random = new Random();

    @Override
    public void move(Sprite enemy) {
        // Verifica se atingiu o limite esquerdo
        if (enemy.x < LIMITE_ESQUERDO) {
            // Se atingiu, reposiciona em um lugar aleatório
            resetPosicao(enemy);
        }

        // Move horizontalmente para a esquerda com uma velocidade maior para o boss
        enemy.x -= VELOCIDADE_HORIZONTAL * 2;

        // Movimento vertical
        moveVertical(enemy);
    }

    // Método para reposicionar o boss em um lugar aleatório
    private void resetPosicao(Sprite enemy) {
        // Reposiciona em um lugar aleatório na vertical
        enemy.x = 800; // Reinicia na borda direita
        enemy.y = random.nextInt(LIMITE_INFERIOR - LIMITE_SUPERIOR) + LIMITE_SUPERIOR;
    }

    // Método para controlar o movimento vertical do boss
    private void moveVertical(Sprite enemy) {
        if (subindo && enemy.y > LIMITE_SUPERIOR) {
            // Se está subindo e não atingiu o limite superior, move para cima
            enemy.y -= VELOCIDADE_VERTICAL;
        } else {
            // Se atingiu o limite superior, inverte a direção para descer
            subindo = false;

            if (enemy.y < LIMITE_INFERIOR) {
                // Se está descendo e não atingiu o limite inferior, move para baixo
                enemy.y += VELOCIDADE_VERTICAL;
            } else {
                // Se atingiu o limite inferior, inverte a direção para subir
                subindo = true;
            }
        }
    }
}
