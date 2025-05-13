package com.example.dotgame;

/**
 * Box2D category bitmask constants for collision filtering.
 */
public class Constants {
    public static final short CATEGORY_BULLET = 0x0001; // 탄환
    public static final short CATEGORY_WALL   = 0x0002; // 벽
    public static final short CATEGORY_MIRROR = 0x0004; // 거울
    public static final short CATEGORY_PRISM  = 0x0008; // 프리즘
}
