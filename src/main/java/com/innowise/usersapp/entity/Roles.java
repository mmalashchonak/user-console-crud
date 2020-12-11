package com.innowise.usersapp.entity;

public enum Roles {
    USER(1, 1),
    CUSTOMER(1, 2),
    ADMIN(2, 3),
    PROVIDER(2, 4),
    SUPER_ADMIN(3, 5);

    private final int level;
    private final int index;

    Roles(int level, int index){
        this.level = level;
        this.index = index;
    }

    public int getLevel() {
        return level;
    }

    public int getIndex() {
        return index;
    }

    public static Roles getRole(int input){
        for (Roles role : values())
            if(role.getIndex() == input){
                return role;
            }
             throw new IllegalArgumentException("Role with such index does not exist.");
    }
}
