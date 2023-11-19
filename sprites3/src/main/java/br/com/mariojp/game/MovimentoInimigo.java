package br.com.mariojp.game;

import br.com.mariojp.game.entities.Sprite;

public class MovimentoInimigo implements IMovimento {

    @Override
    public void move(Sprite enemy) {
        if (enemy.x < 0) {
            enemy.x = 800;
        }
        enemy.x -= 1;
    }
}
