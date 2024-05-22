/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Graphix;

import Mobs.Goblin;
import Player.Player;
import java.util.Random;
import javax.swing.JOptionPane;

/**
 *
 * @author IanGz
 */
public class Game extends javax.swing.JFrame {

    private int potionCount = 0;

    private Player player;

    private int score = 0;

    private Goblin goblin;

    Graphix graph;

    /**
     * potionCount getter
     *
     * @return
     */
    public int getPotionCount() {
        return potionCount;
    }

    /**
     * potionCount setter
     *
     * @param potionCount
     */
    public void setPotionCount(int potionCount) {
        this.potionCount = potionCount;
    }

    /**
     * player getter
     *
     * @return the current player
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * player setter
     *
     * @param player
     */
    private void setPlayer(Player player) {
        this.player = player;
    }

    /**
     * score getter
     *
     * @return the score
     */
    public int getScore() {
        return score;
    }

    /**
     * score setter
     *
     * @param score
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * Method that updates the potion count showed to the player
     */
    private void updatePotionCount() {

        PotionCuantityButton.setText("x " + potionCount);

    }

    /**
     * Method that enables the movement buttons
     */
    private void enableMovButtons() {

        LeftMovButton.setEnabled(true);
        RightMovButton.setEnabled(true);
        AheadMovButton.setEnabled(true);

    }

    /**
     * Method that disables the movement buttons
     */
    private void disableMovButtons() {

        LeftMovButton.setEnabled(false);
        RightMovButton.setEnabled(false);
        AheadMovButton.setEnabled(false);

    }

    /**
     * Method that enables the combat buttons
     */
    private void enableCombatButtons() {

        AttackButton.setEnabled(true);
        DefendButton.setEnabled(true);
        RunButton.setEnabled(true);

        if (potionCount > 0) {

            PotionButton.setEnabled(true);

        }

    }

    /**
     * Method that disables the combat buttons
     */
    private void disableCombatButtons() {

        AttackButton.setEnabled(false);
        DefendButton.setEnabled(false);
        RunButton.setEnabled(false);
        PotionButton.setEnabled(false);

    }

    /**
     * Method that enables LevelUpButtons
     */
    private void enableLvlUpButtons() {

        LevelUpAttackButton.setEnabled(true);
        LevelUpDefenseButton.setEnabled(true);
        LevelUpDexButton.setEnabled(true);
        LevelUpMagicButton.setEnabled(true);
        LevelUpSpeedButton.setEnabled(true);

    }

    /**
     * Method that disables LevelUpButtons
     */
    private void disableLvlUpButtons() {

        LevelUpAttackButton.setEnabled(false);
        LevelUpDefenseButton.setEnabled(false);
        LevelUpDexButton.setEnabled(false);
        LevelUpMagicButton.setEnabled(false);
        LevelUpSpeedButton.setEnabled(false);

    }

    /**
     * Method that generates a random number beetween 1 and 10 including those
     *
     * @return random number
     */
    private int generateRandomNumber() {

        Random r = new Random();

        int randomNum = r.nextInt(10) + 1;

        return randomNum;

    }

    /**
     * This method updates the character stats after upgrading
     */
    private void updateStats() {

        String charLevel = Integer.toString(player.getLevel());
        String maxHealth = Integer.toString(player.getMaxHealth());
        String curHealth = Integer.toString(player.getCurHealth());

        CharLevelLabel.setText(" Lvl: " + charLevel);
        CharAtkLabel.setText("Attack: " + Integer.toString(player.getAtk()));
        CharDefLabel.setText("Def: " + Integer.toString(player.getDef()));
        CharExpLabel.setText("Exp:   " + Integer.toString(player.getExp()));
        CharDexLabel.setText("Dex: " + Integer.toString(player.getDex()));
        CharHealthLabel.setText(maxHealth + "/" + curHealth);
        CharMagicLabel.setText("Magic: " + Integer.toString(player.getMagic()));
        CharSpeedLabel.setText("Speed: " + Integer.toString(player.getSpeed()));

    }

    /**
     * Generates a chest with random potions
     */
    private void chestRoom() {

        Random r = new Random();

        int randomNum = r.nextInt(5);

        if (randomNum == 0) {

            JOptionPane.showMessageDialog(DirectionPanel, "Wow, the chest is empty, how lucky of you :D");

        } else {

            JOptionPane.showMessageDialog(DirectionPanel, "You have received " + randomNum + " Potions");

        }

        setPotionCount(getPotionCount() + randomNum);

        updatePotionCount();
        enableMovButtons();

    }

    /**
     * Generates a random event
     */
    private void eventRoom() {

        int chance = generateRandomNumber();

        if (chance <= 3) {

            healEvent();

        } else if (chance <= 5) {

            trapEvent();

        } else {

            combatRoom(player.getLevel());

        }

    }

    /**
     * Method that triggers an event full healing the player
     */
    private void healEvent() {

        if (player.getCurHealth() < player.getMaxHealth()) {

            JOptionPane.showMessageDialog(DirectionPanel, "You've been healed");

            player.setCurHealth(player.getMaxHealth());

            updateStats();

        }

        enableMovButtons();

    }

    /**
     * Method that triggers a trap
     */
    private void trapEvent() {

        JOptionPane.showMessageDialog(DirectionPanel, "Oh no, Its a trap™");

        player.setCurHealth(player.getCurHealth() - (5 * player.getLevel()));

        updateStats();
        endGame();
        enableMovButtons();

    }

    /**
     * Generates a combat with a monster
     */
    private void combatRoom(int level) {

        enableCombatButtons();

        int maxHealth = level * 10;

        int curHealth = maxHealth;

        int defPoints = 5 + level;

        int expGiven = 10 * level;

        int atk = 10 + level;

        int speed = 5 + level;

        goblin = new Goblin(level, maxHealth, curHealth, defPoints, expGiven, atk, speed, "Goblin");

        MonNameLabel.setText(goblin.getName());

        MonLevelLabel.setText("Lvl: " + Integer.toString(goblin.getLevel()));

        combatStart();
    }

    /**
     * Method that generates a random room when you press the movement buttons
     */
    private void generateRandomRoom() {

        int chance = generateRandomNumber();

        if (chance <= 3) {

            chestRoom();

        } else if (chance <= 5) {

            eventRoom();

        } else {

            combatRoom(player.getLevel());

        }

    }

    /**
     * This method defines if the enemy attack you starting the combat
     */
    public void combatStart() {

        if (goblin.getSpeed() > player.getSpeed()) {

            player.setCurHealth(player.getCurHealth() - goblin.attack(player.getDef()));

            updateStats();

        }

        endGame();

    }

    /**
     * Creates new form Game
     *
     * @param graphix
     */
    public Game(Graphix graphix) {
        initComponents();

        graph = graphix;

        setPlayer(graphix.pdb.getSelectedPlayers().get(0));

        String charLevel = Integer.toString(player.getLevel());
        String maxHealth = Integer.toString(player.getMaxHealth());
        String curHealth = Integer.toString(player.getCurHealth());

        CharLevelLabel.setText(" Lvl: " + charLevel);
        CharNameLabel.setText(player.getName());
        CharAtkLabel.setText("Attack: " + Integer.toString(player.getAtk()));
        CharClassLabel.setText(graphix.pdb.getPlayerClass(player));
        CharDefLabel.setText("Def: " + Integer.toString(player.getDef()));
        CharExpLabel.setText("Exp:   " + Integer.toString(player.getExp()));
        CharDexLabel.setText("Dex: " + Integer.toString(player.getDex()));
        CharHealthLabel.setText(maxHealth + "/" + curHealth);
        CharMagicLabel.setText("Magic: " + Integer.toString(player.getMagic()));
        CharSpeedLabel.setText("Speed: " + Integer.toString(player.getSpeed()));

        updatePotionCount();

        jLabelCaveImage.setVisible(false);

    }

    /**
     * Ends the game
     */
    private void endGame() {

        if (player.getCurHealth() <= 0) {

            JOptionPane.showMessageDialog(DirectionPanel, "Thanks for playing, your score was: " + getScore());

            graph.setVisible(true);

            setVisible(false);

        }

    }

    /**
     * Ends the combat
     */
    private void endCombat() {

        enableMovButtons();
        disableCombatButtons();
        MonNameLabel.setText("");
        MonLevelLabel.setText("");

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ActionsPanel = new javax.swing.JPanel();
        AttackButton = new javax.swing.JButton();
        DefendButton = new javax.swing.JButton();
        PotionButton = new javax.swing.JButton();
        PotionCuantityButton = new javax.swing.JLabel();
        RunButton = new javax.swing.JButton();
        PlayerPanel = new javax.swing.JPanel();
        CharNameLabel = new javax.swing.JLabel();
        CharLevelLabel = new javax.swing.JLabel();
        CharHealthLabel = new javax.swing.JLabel();
        XDDD = new javax.swing.JLabel();
        CharExpLabel = new javax.swing.JLabel();
        MaxExpLabel = new javax.swing.JLabel();
        CharDefLabel = new javax.swing.JLabel();
        LevelUpDefenseButton = new javax.swing.JButton();
        CharAtkLabel = new javax.swing.JLabel();
        LevelUpAttackButton = new javax.swing.JButton();
        CharMagicLabel = new javax.swing.JLabel();
        LevelUpMagicButton = new javax.swing.JButton();
        CharDexLabel = new javax.swing.JLabel();
        LevelUpDexButton = new javax.swing.JButton();
        CharSpeedLabel = new javax.swing.JLabel();
        LevelUpSpeedButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        CharClassLabel = new javax.swing.JLabel();
        MonsterPanel = new javax.swing.JPanel();
        MonNameLabel = new javax.swing.JLabel();
        MonLevelLabel = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        DirectionPanel = new javax.swing.JPanel();
        LeftMovButton = new javax.swing.JButton();
        AheadMovButton = new javax.swing.JButton();
        RightMovButton = new javax.swing.JButton();
        MapPanel = new javax.swing.JPanel();
        StartButton = new javax.swing.JButton();
        jLabelCaveImage = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(700, 500));

        ActionsPanel.setMinimumSize(new java.awt.Dimension(760, 37));

        AttackButton.setText("Attack");
        AttackButton.setEnabled(false);
        AttackButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AttackButtonActionPerformed(evt);
            }
        });
        ActionsPanel.add(AttackButton);

        DefendButton.setText("Defend");
        DefendButton.setEnabled(false);
        DefendButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DefendButtonActionPerformed(evt);
            }
        });
        ActionsPanel.add(DefendButton);

        PotionButton.setText("Potion");
        PotionButton.setEnabled(false);
        PotionButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PotionButtonActionPerformed(evt);
            }
        });
        ActionsPanel.add(PotionButton);
        ActionsPanel.add(PotionCuantityButton);

        RunButton.setText("Run");
        RunButton.setEnabled(false);
        RunButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RunButtonActionPerformed(evt);
            }
        });
        ActionsPanel.add(RunButton);

        getContentPane().add(ActionsPanel, java.awt.BorderLayout.PAGE_END);

        PlayerPanel.setMinimumSize(new java.awt.Dimension(370, 360));
        PlayerPanel.setPreferredSize(new java.awt.Dimension(130, 400));
        PlayerPanel.setLayout(new java.awt.GridLayout(10, 2));

        CharNameLabel.setPreferredSize(new java.awt.Dimension(60, 20));
        PlayerPanel.add(CharNameLabel);

        CharLevelLabel.setPreferredSize(new java.awt.Dimension(50, 20));
        PlayerPanel.add(CharLevelLabel);

        CharHealthLabel.setFocusable(false);
        PlayerPanel.add(CharHealthLabel);

        XDDD.setEnabled(false);
        XDDD.setFocusable(false);
        PlayerPanel.add(XDDD);

        CharExpLabel.setFocusable(false);
        PlayerPanel.add(CharExpLabel);

        MaxExpLabel.setText("/100");
        MaxExpLabel.setFocusable(false);
        PlayerPanel.add(MaxExpLabel);

        CharDefLabel.setFocusable(false);
        PlayerPanel.add(CharDefLabel);

        LevelUpDefenseButton.setText("Up");
        LevelUpDefenseButton.setEnabled(false);
        LevelUpDefenseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LevelUpDefenseButtonActionPerformed(evt);
            }
        });
        PlayerPanel.add(LevelUpDefenseButton);

        CharAtkLabel.setFocusable(false);
        PlayerPanel.add(CharAtkLabel);

        LevelUpAttackButton.setText("Up");
        LevelUpAttackButton.setEnabled(false);
        LevelUpAttackButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LevelUpAttackButtonActionPerformed(evt);
            }
        });
        PlayerPanel.add(LevelUpAttackButton);

        CharMagicLabel.setFocusable(false);
        PlayerPanel.add(CharMagicLabel);

        LevelUpMagicButton.setText("Up");
        LevelUpMagicButton.setEnabled(false);
        LevelUpMagicButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LevelUpMagicButtonActionPerformed(evt);
            }
        });
        PlayerPanel.add(LevelUpMagicButton);

        CharDexLabel.setFocusable(false);
        PlayerPanel.add(CharDexLabel);

        LevelUpDexButton.setText("Up");
        LevelUpDexButton.setEnabled(false);
        LevelUpDexButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LevelUpDexButtonActionPerformed(evt);
            }
        });
        PlayerPanel.add(LevelUpDexButton);

        CharSpeedLabel.setFocusable(false);
        PlayerPanel.add(CharSpeedLabel);

        LevelUpSpeedButton.setText("Up");
        LevelUpSpeedButton.setEnabled(false);
        LevelUpSpeedButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LevelUpSpeedButtonActionPerformed(evt);
            }
        });
        PlayerPanel.add(LevelUpSpeedButton);

        jLabel1.setText("Class:");
        PlayerPanel.add(jLabel1);
        PlayerPanel.add(CharClassLabel);

        getContentPane().add(PlayerPanel, java.awt.BorderLayout.WEST);

        MonsterPanel.setMinimumSize(new java.awt.Dimension(130, 10));

        MonNameLabel.setPreferredSize(new java.awt.Dimension(60, 20));
        MonsterPanel.add(MonNameLabel);

        MonLevelLabel.setPreferredSize(new java.awt.Dimension(50, 20));
        MonsterPanel.add(MonLevelLabel);

        getContentPane().add(MonsterPanel, java.awt.BorderLayout.LINE_END);

        jPanel6.setLayout(new java.awt.BorderLayout());

        LeftMovButton.setText("Left");
        LeftMovButton.setEnabled(false);
        LeftMovButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LeftMovButtonActionPerformed(evt);
            }
        });
        DirectionPanel.add(LeftMovButton);

        AheadMovButton.setText("Ahead");
        AheadMovButton.setEnabled(false);
        AheadMovButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AheadMovButtonActionPerformed(evt);
            }
        });
        DirectionPanel.add(AheadMovButton);

        RightMovButton.setText("Right");
        RightMovButton.setEnabled(false);
        RightMovButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RightMovButtonActionPerformed(evt);
            }
        });
        DirectionPanel.add(RightMovButton);

        jPanel6.add(DirectionPanel, java.awt.BorderLayout.PAGE_END);

        MapPanel.setLayout(new java.awt.CardLayout());

        StartButton.setText("Start");
        StartButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                StartButtonActionPerformed(evt);
            }
        });
        MapPanel.add(StartButton, "card2");

        jLabelCaveImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Graphix/iceland-2608985_640.jpg"))); // NOI18N
        jLabelCaveImage.setText("jLabel2");
        MapPanel.add(jLabelCaveImage, "card3");

        jPanel6.add(MapPanel, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel6, java.awt.BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Uses a potion to heal the player
     *
     * @param evt
     */
    private void PotionButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PotionButtonActionPerformed

        //If you are full health it cant be used
        if (player.getCurHealth() == player.getMaxHealth()) {

            JOptionPane.showMessageDialog(DirectionPanel, "You are already full health");

        } else {

            if (getPotionCount() == 0) {

                JOptionPane.showMessageDialog(DirectionPanel, "No potions available");
                updatePotionCount();

                //If your life is less than maxhealth but cant be healed 5 hp its healed whats left to maxhp 
            } else if (player.getMaxHealth() - 5 <= player.getCurHealth()) {

                player.setCurHealth(player.getCurHealth() + (player.getMaxHealth() - player.getCurHealth()));

                updateStats();

                setPotionCount(getPotionCount() - 1);
                updatePotionCount();

                //there just heals 5hp
            } else {

                player.setCurHealth(player.getCurHealth() + 5);

                updateStats();

                setPotionCount(getPotionCount() - 1);
                updatePotionCount();

            }

            if (getPotionCount() == 0) {

                PotionButton.setEnabled(false);

            }
            updatePotionCount();
        }
    }//GEN-LAST:event_PotionButtonActionPerformed

    /**
     * Starts the game
     *
     * @param evt
     */
    private void StartButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_StartButtonActionPerformed
        StartButton.setVisible(false);
        jLabelCaveImage.setVisible(true);

        enableMovButtons();

    }//GEN-LAST:event_StartButtonActionPerformed

    /**
     * Moves the player
     *
     * @param evt
     */
    private void LeftMovButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LeftMovButtonActionPerformed

        disableMovButtons();
        generateRandomRoom();


    }//GEN-LAST:event_LeftMovButtonActionPerformed

    /**
     * Moves the player
     *
     * @param evt
     */
    private void AheadMovButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AheadMovButtonActionPerformed

        disableMovButtons();
        generateRandomRoom();

    }//GEN-LAST:event_AheadMovButtonActionPerformed

    /**
     * Moves the player
     *
     * @param evt
     */
    private void RightMovButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RightMovButtonActionPerformed

        disableMovButtons();
        generateRandomRoom();

    }//GEN-LAST:event_RightMovButtonActionPerformed

    /**
     * It upgrades the attack stat
     *
     * @param evt
     */
    private void LevelUpAttackButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LevelUpAttackButtonActionPerformed
        player.statLvlUp(1);
        updateStats();
        disableLvlUpButtons();
    }//GEN-LAST:event_LevelUpAttackButtonActionPerformed

    /**
     * It upgrades the Dex stat
     *
     * @param evt
     */
    private void LevelUpDexButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LevelUpDexButtonActionPerformed
        player.statLvlUp(3);
        updateStats();
        disableLvlUpButtons();
    }//GEN-LAST:event_LevelUpDexButtonActionPerformed

    /**
     * It upgrades the defense stat
     *
     * @param evt
     */
    private void LevelUpDefenseButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LevelUpDefenseButtonActionPerformed
        player.statLvlUp(0);
        updateStats();
        disableLvlUpButtons();
    }//GEN-LAST:event_LevelUpDefenseButtonActionPerformed

    /**
     * It upgrades the Magic stat
     *
     * @param evt
     */
    private void LevelUpMagicButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LevelUpMagicButtonActionPerformed
        player.statLvlUp(2);
        updateStats();
        disableLvlUpButtons();
    }//GEN-LAST:event_LevelUpMagicButtonActionPerformed

    /**
     * It upgrades the Speed stat
     *
     * @param evt
     */
    private void LevelUpSpeedButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LevelUpSpeedButtonActionPerformed
        player.statLvlUp(4);
        updateStats();
        disableLvlUpButtons();
    }//GEN-LAST:event_LevelUpSpeedButtonActionPerformed

    /**
     * Attacks the monster
     *
     * @param evt
     */
    private void AttackButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AttackButtonActionPerformed

        goblin.setCurhealth(goblin.getCurhealth() - player.attackDone(goblin.getDefPoints()));

        if (goblin.getCurhealth() > 0) {

            if (goblin.runAway()) {

                endCombat();

                JOptionPane.showMessageDialog(DirectionPanel, "The monster ran away");

            }

            player.setCurHealth(player.getCurHealth() - goblin.attack(player.getDef()));
            updateStats();
            endGame();

        } else {

            player.setExp(player.getExp() + (20 * goblin.getLevel()));
            setScore(getScore() + (5 * goblin.getLevel()));
            updateStats();

            if (player.getExp() >= 100) {

                player.hPLvlUp();
                player.setExp(player.getExp() - 100);
                setScore(getScore() + 10);
                updateStats();
                enableLvlUpButtons();

            }
            endCombat();

        }
    }//GEN-LAST:event_AttackButtonActionPerformed

    /**
     * Defend the monster attack It would have done something depending on the
     * character class but theres no time :p
     *
     * @param evt
     */
    private void DefendButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DefendButtonActionPerformed


    }//GEN-LAST:event_DefendButtonActionPerformed

    /**
     *  You can run from the combat or be attacked by the monster
     * 
     * @param evt
     */
    private void RunButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RunButtonActionPerformed

        if (generateRandomNumber() <= 4) {

            endCombat();

        } else {

            player.setCurHealth(player.getCurHealth() - goblin.attack(player.getDef()));
            endGame();
            updateStats();

        }

    }//GEN-LAST:event_RunButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel ActionsPanel;
    private javax.swing.JButton AheadMovButton;
    private javax.swing.JButton AttackButton;
    private javax.swing.JLabel CharAtkLabel;
    private javax.swing.JLabel CharClassLabel;
    private javax.swing.JLabel CharDefLabel;
    private javax.swing.JLabel CharDexLabel;
    private javax.swing.JLabel CharExpLabel;
    private javax.swing.JLabel CharHealthLabel;
    private javax.swing.JLabel CharLevelLabel;
    private javax.swing.JLabel CharMagicLabel;
    private javax.swing.JLabel CharNameLabel;
    private javax.swing.JLabel CharSpeedLabel;
    private javax.swing.JButton DefendButton;
    private javax.swing.JPanel DirectionPanel;
    private javax.swing.JButton LeftMovButton;
    private javax.swing.JButton LevelUpAttackButton;
    private javax.swing.JButton LevelUpDefenseButton;
    private javax.swing.JButton LevelUpDexButton;
    private javax.swing.JButton LevelUpMagicButton;
    private javax.swing.JButton LevelUpSpeedButton;
    private javax.swing.JPanel MapPanel;
    private javax.swing.JLabel MaxExpLabel;
    private javax.swing.JLabel MonLevelLabel;
    private javax.swing.JLabel MonNameLabel;
    private javax.swing.JPanel MonsterPanel;
    private javax.swing.JPanel PlayerPanel;
    private javax.swing.JButton PotionButton;
    private javax.swing.JLabel PotionCuantityButton;
    private javax.swing.JButton RightMovButton;
    private javax.swing.JButton RunButton;
    private javax.swing.JButton StartButton;
    private javax.swing.JLabel XDDD;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabelCaveImage;
    private javax.swing.JPanel jPanel6;
    // End of variables declaration//GEN-END:variables
}
