package com.ricky.terrariamod.platform.services;

public interface IManaComponent {
    int getMana();
    void setMana(int mana);
    void addMana(int amount);
    void removeMana(int amount);
}
