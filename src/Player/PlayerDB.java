/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Player;

import java.util.ArrayList;

/**
 * -Class that contains an arraylist with all the characters created during a
 * game-
 *
 * @author IanGz
 */
public class PlayerDB {

    ArrayList<Player> players = new ArrayList<>();

    ArrayList<Player> selectedPlayers = new ArrayList<>();

    /**
     * getter from players
     *
     * @return a list of players
     */
    public ArrayList<Player> getPlayers() {
        return players;
    }

    /**
     * players setter
     *
     * @param players
     */
    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    /**
     * getter from selectedPlayers
     *
     * @return
     */
    public ArrayList<Player> getSelectedPlayers() {
        return selectedPlayers;
    }

    /**
     * SelectedPlayer setter
     *
     * @param selectedPlayers
     */
    public void setSelectedPlayers(ArrayList<Player> selectedPlayers) {
        this.selectedPlayers = selectedPlayers;
    }

    /**
     * Method that defines a player stats
     *
     * @param type to know the class
     * @return A new player
     */
    public int[] DefineStats(int type) {

        int[] Soldier = {15, 10, 13, 7, 10, 10};
        int[] Archer = {10, 8, 10, 7, 13, 12};
        int[] Mage = {8, 7, 8, 14, 10, 11};
        int[] Healer = {12, 9, 5, 15, 10, 11};

        switch (type) {
            case Player.SOLDIER:
                return Soldier;
            case Player.ARCHER:
                return Archer;
            case Player.MAGE:
                return Mage;
            case Player.HEALER:
                return Healer;
            default:
                return null;
        }

    }

    /**
     * Returns the character class
     *
     * @param p
     * @return a String with the name of the class
     */
    public String getPlayerClass(Player p) {

        switch (p.getType()) {
            case Player.SOLDIER:
                return "Soldier";
            case Player.ARCHER:
                return "Archer";
            case Player.MAGE:
                return "Mage";
            case Player.HEALER:
                return "Healer";
            default:
                return null;
        }

    }

}
