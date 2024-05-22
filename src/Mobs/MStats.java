/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Mobs;

/**
 *
 * Superclass that defines monsters attributes
 *
 * @author IanGz
 */
public abstract class MStats {

    //Monster level
    protected int level;

    //Monster health points
    protected int maxhealth;

    //Monster current health
    protected int curhealth;

    //Monster defense points
    protected int defPoints;

    //Experience droped at death
    protected int exp;

    //Monster attack
    protected int atk;

    //Monster speed
    protected int speed;

    //Monster name
    protected String name;

    /**
     * level getter
     * @return
     */
    public int getLevel() {
        return level;
    }

    /**
     * level setter
     * @param level
     */
    public void setLevel(int level) {
        this.level = level;
    }

    /**
     *maxHealth getter
     * @return
     */
    public int getMaxhealth() {
        return maxhealth;
    }

    /**
     * maxHealth setter
     * @param maxhealth
     */
    public void setMaxhealth(int maxhealth) {
        this.maxhealth = maxhealth;
    }

    /**
     *current health getter
     * @return
     */
    public int getCurhealth() {
        return curhealth;
    }

    /**
     * current health setter
     * @param curhealth
     */
    public void setCurhealth(int curhealth) {
        this.curhealth = curhealth;
    }

    /**
     * def getter
     * @return
     */
    public int getDefPoints() {
        return defPoints;
    }

    /**
     * def setter
     * @param DefPoints
     */
    public void setDefPoints(int DefPoints) {
        this.defPoints = DefPoints;
    }

    /**
     * exp given getter
     * @return
     */
    public int getExp() {
        return exp;
    }

    /**
     * exp given setter
     * @param exp
     */
    public void setExp(int exp) {
        this.exp = exp;
    }

    /**
     * attack getter
     * @return
     */
    public int getAtk() {
        return atk;
    }

    /**
     * attack setter
     * @param Atk
     */
    public void setAtk(int Atk) {
        this.atk = Atk;
    }

    /**
     * speed getter
     * @return
     */
    public int getSpeed() {
        return speed;
    }

    /**
     * speed setter
     * @param Speed
     */
    public void setSpeed(int Speed) {
        this.speed = Speed;
    }

    /**
     * name getter
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * name setter
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Uses de monster attack to deal damage to players
     *
     * @param defense
     * @return damage dealt
     */
    public abstract int attack(int defense);

    /**
     * Sometimes monsters can run away when they have low health
     *
     * @return if it runs away or not
     */
    public abstract boolean runAway();

    /**
     * Constructor
     *
     * @param level
     * @param maxhealth
     * @param curhealth
     * @param defPoints
     * @param exp
     * @param atk
     * @param speed
     * @param name
     */
    public MStats(int level, int maxhealth, int curhealth, int defPoints, int exp, int atk, int speed, String name) {
        this.level = level;
        this.maxhealth = maxhealth;
        this.curhealth = curhealth;
        this.defPoints = defPoints;
        this.exp = exp;
        this.atk = atk;
        this.speed = speed;
        this.name = name;
    }

}
