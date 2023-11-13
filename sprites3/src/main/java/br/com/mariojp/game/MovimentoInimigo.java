package br.com.mariojp.game;

public class MovimentoInimigo implements IMovimento {
    private boolean subindo = true;

    @Override
    public void move(Sprite enemy) {
        // if (enemy.x < 0) {
		// 	enemy.x = 800;
		// }
		// enemy.x -= 1;
        // System.out.println(enemy.x + " - " + enemy.y );
        // if (enemy.x < 0) {
        //     enemy.x = 800;
        //     enemy.y = 600;
        // }
        // 
        // enemy.x -= 1;
        // UP
        // enemy.y -= 1;    


         // Verifica se atingiu o limite esquerdo
         if (enemy.x < 0) {
            enemy.x = 800;
            enemy.y = 600;  // Reinicia a posição vertical ao atingir o limite esquerdo
        }

        // Move horizontalmente para a esquerda
        enemy.x -= 1;

        // Movimento vertical
        if (subindo) {
            // Se está subindo e ainda não atingiu o limite superior
            if (enemy.y > 0) {
                enemy.y -= 1;
            } else {
                // Se atingiu o limite superior, inverte a direção para descer
                subindo = false;
            }
        } else {
            // Se está descendo e ainda não atingiu o limite inferior
            if (enemy.y < 600) {
                enemy.y += 1;
            } else {
                // Se atingiu o limite inferior, inverte a direção para subir
                subindo = true;
            }
        }
    }
}
