// package com.example.dotgame;
//
// import com.badlogic.gdx.graphics.g2d.Sprite;
// import com.badlogic.gdx.math.Vector2;
// import com.badlogic.gdx.scenes.scene2d.Actor;
//
// public class Door extends Actor {
//     public static Door instance;
//
//     private boolean locked = true;
//     private final Sprite doorClosedSprite;
//     private final PlayerMovement player;
//     private final Vector2 doorPos;
//
//     public Door(Sprite doorClosedSprite, PlayerMovement player) {
//         this.doorClosedSprite = doorClosedSprite;
//         this.player = player;
//         this.doorPos = new Vector2(1200, 270);
//         setBounds(doorPos.x, doorPos.y, doorClosedSprite.getWidth(), doorClosedSprite.getHeight());
//         instance = this;
//     }
//
//     public void nextStage() {
//         if (!locked && player != null) {
//             player.teleportTo(new Vector2(100, 270));
//         }
//     }
//
//     public void unlock() {
//         locked = false;
//     }
//
//     public void update() {
//         if (player != null && player.getX() > doorPos.x - 20 && player.getX() < doorPos.x + 100) {
//             if (!locked) {
//                 nextStage();
//             }
//         }
//     }
//
//     public void onTriggerStay(PlayerMovement other) {
//         if (other == player && !locked) {
//             nextStage();
//         }
//     }
//
//     @Override
//     public void draw(com.badlogic.gdx.graphics.g2d.Batch batch, float parentAlpha) {
//         doorClosedSprite.setPosition(doorPos.x, doorPos.y);
//         doorClosedSprite.draw(batch);
//     }
// }
