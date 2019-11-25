package com.proyecto.android_spacecraft_game;

public class GameManager {

    private final String scoreText = "SCORE: ";
    private final String healtText = "HEALT: ";

    private Meteor m1;
    private Meteor m2;
    private Meteor m3;
    private EnemyShip e1;
    private EnemyShip e2;
    private EnemyShot enemyShot;
    private EnemyShot enemyShot2;
    private SpaceShip player;
    private PlayerShot playerShot;

    public GameManager(Meteor m1, Meteor m2, Meteor m3, EnemyShip e1, EnemyShip e2, EnemyShot enemyShot, EnemyShot enemyShot2, SpaceShip player, PlayerShot playerShot) {
        this.m1 = m1;
        this.m2 = m2;
        this.m3 = m3;
        this.e1 = e1;
        this.e2 = e2;
        this.enemyShot = enemyShot;
        this.enemyShot2 = enemyShot2;
        this.player = player;
        this.playerShot = playerShot;
    }

    public void checkPlayerDmg(){
        if (enemyShot.checkPlayerColision(player) || enemyShot2.checkPlayerColision(player)){
            player.setHealt(player.getHealt() - 15);
        }
    }

    public boolean checkEndGame(){
        return m1.checkPlayerColision(player) || m2.checkPlayerColision(player) || m3.checkPlayerColision(player) || e1.checkPlayerColision(player) || e2.checkPlayerColision(player) || player.getHealt() <= 0;
    }

    public String getScoreText() {
        return scoreText + player.getScore();
    }

    public String getHealtText() {
        return healtText + player.getHealt();
    }
}
