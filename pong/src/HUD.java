import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;

public class HUD {

    //variabler
    private int score = 0;
    public static float speedup = 1;
    public static int time = 1;
    public static int p1score;
    public static int p2score;
    public static boolean slot1 = true;
    public static boolean slot2 = true;
    public static boolean slot3 = true;
    public static boolean slot4 = true;
    public static boolean slot5 = true;
    public static boolean slot6 = true;
    public static int BandageSlot = 0;
    public static int MedkitSlot = 0;
    public static boolean HudToggle = false;


    //tick metod uppdaterar classen
    public void tick() {
        if (Game.Pause == false && Game.Start == true && Game.Controls == false) {
            HudToggle = true;
        }else if (Game.Pause == true || Game.Start == false || Game.Controls == true){
            HudToggle = false;
        }else{
            HudToggle=false;
        }

    }
    //timer skapar en timer som räknar sekunder
    public static void timer(){
        //thread är en tråd(typ en loop som går att sätta tid på)
        new Thread(() -> {
            while (true) {
                try {
                    //ger en delay i millisekunder
                    Thread.sleep(1000);
                    time += 1;

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
    //uppdaterar klassens utseende
    public void render(Graphics g) {
        Font StorText = new Font("Arial", Font.BOLD,60);
        Font LitenText = new Font("Arial", Font.PLAIN,12);
        Font MellanText = new Font("Arial", Font.BOLD,30);
        Font MellanLitenochMellanText = new Font("Arial", Font.PLAIN,20);
        if (Game.Start == false){
            g.setColor(Color.WHITE);
            g.setFont(StorText);
            g.drawString("Press [ESC] to start",40,150);
            g.setFont(LitenText);
        }
        if (Game.Pause==true){
            g.setColor(Color.WHITE);
            g.setFont(StorText);
            g.drawString("Paused",210,200);
            g.setFont(LitenText);
            g.drawString("(Press [ESC] to Pause and unpause)",220,240);
            g.drawString("(Press [SPACE] to see controls)",220,260);

        }
        if (Game.Controls){
            g.setFont(MellanText);
            g.setColor(Color.WHITE);
            g.drawString("[W][A][S][D] to Move",190,150);
            g.drawString("[SPACE] to Shoot",190,180);
            g.drawString("Arrow Keys to Aim",190,210);
            g.setFont(LitenText);
            g.drawString("(Press [SPACE] to go back to pause screen)",220,260);
            g.drawString("(Press [ESC] to Continiue playing)",220,280);
//Upgrade Keys
            g.drawString("Press Q to upgrade Damage.", 450, 40);
            g.drawString("Or press E to upgrade Max HP.", 430, 80);
        }
        if (Spawner.level == 4 && Spawner.wave >= 0 && Spawner.EnemyCount == 0){
            g.setColor(Color.YELLOW);
            g.setFont(StorText);
            g.drawString("You Won",200,200);
            g.setFont(LitenText);
            g.setColor(Color.WHITE);
        }
        if (HudToggle == true) {
            g.setColor(Color.white);
            g.drawRect(15, 15, Player.MaxHP + 9, 30);
            g.setColor(Color.red);
            g.fillRect(20, 20, Player.HP, 21);
            g.setFont(MellanLitenochMellanText);
            g.setColor(Color.YELLOW);
            g.drawString("Coins: " + Game.Coins, 25, 200);
            g.setColor(Color.white);
            g.drawString("Weve: " + (Spawner.wave-1), 20, 560);
            g.drawString("Level: " + Spawner.level, 20, 590);
            g.setFont(LitenText);
            g.drawString("Max HP Upgrade Cost: " + KeyInput.MaxHealthCost, 450, 100);
            g.drawString("Damage Upgrade Cost: " + KeyInput.DamageCost   , 450, 60);
            g.drawRoundRect(150, 520, 50, 50, 5, 5);
            g.drawRoundRect(205, 520, 50, 50, 5, 5);
            g.drawRoundRect(260, 520, 50, 50, 5, 5);
            g.drawRoundRect(315, 520, 50, 50, 5, 5);
            g.drawRoundRect(370, 520, 50, 50, 5, 5);
            g.drawRoundRect(425, 520, 50, 50, 5, 5);
        }
        if (Player.MedkitAmt > 0) {
            if (slot1 == true && MedkitSlot == 0 && BandageSlot != 1) {MedkitSlot =1;}
            else if (slot2 == true && MedkitSlot == 0 && BandageSlot != 2) MedkitSlot =2;
            else if (slot3 == true && MedkitSlot == 0 && BandageSlot != 3) MedkitSlot =3;
            else if (slot4 == true && MedkitSlot == 0 && BandageSlot != 4) MedkitSlot =4;
            else if (slot5 == true && MedkitSlot == 0 && BandageSlot != 5) MedkitSlot =5;
            else if (slot6 == true && MedkitSlot == 0 && BandageSlot != 6) MedkitSlot =6;

            if (MedkitSlot == 1 && slot1 == true && BandageSlot != 1) {
                g.setColor(Color.GREEN);
                g.fillRoundRect(160, 535, 30, 15, 3, 3);
                if (Player.MedkitAmt > 1) {
                    g.setColor(Color.WHITE);
                    g.drawString(Player.MedkitAmt + "x", 185, 565);
                }
            }
            else if (MedkitSlot == 2 && slot2 == true && BandageSlot != 2){
                g.setColor(Color.GREEN);
                g.fillRoundRect(160+55, 535, 30, 15, 3, 3);
                if (Player.MedkitAmt > 1) {
                    g.setColor(Color.WHITE);
                    g.drawString(Player.MedkitAmt + "x", 185+55, 565);
                }
            }
            else if (MedkitSlot == 3 && slot3 == true && BandageSlot != 3) {
                g.setColor(Color.GREEN);
                g.fillRoundRect(160+110, 535, 30, 15, 3, 3);
                if (Player.MedkitAmt > 1) {
                    g.setColor(Color.WHITE);
                    g.drawString(Player.MedkitAmt + "x", 185+110, 565);
                }
            }
            else if (MedkitSlot == 4 && slot4 == true && BandageSlot != 4) {
                g.setColor(Color.GREEN);
                g.fillRoundRect(160+165, 535, 30, 15, 3, 3);
                if (Player.MedkitAmt > 1) {
                    g.setColor(Color.WHITE);
                    g.drawString(Player.MedkitAmt + "x", 185+165, 565);
                }
            }
            else if (MedkitSlot == 5 && slot5 == true && BandageSlot != 5) {
                g.setColor(Color.GREEN);
                g.fillRoundRect(160+220, 535, 30, 15, 3, 3);
                if (Player.MedkitAmt > 1) {
                    g.setColor(Color.WHITE);
                    g.drawString(Player.MedkitAmt + "x", 185+220, 565);
                }
            }
            else if (MedkitSlot == 6 && slot6 == true && BandageSlot != 6) {
                g.setColor(Color.GREEN);
                g.fillRoundRect(160+275, 535, 30, 15, 3, 3);
                if (Player.MedkitAmt > 1) {
                    g.setColor(Color.WHITE);
                    g.drawString(Player.MedkitAmt + "x", 185+275, 565);
                }
            }
        }

        if (Player.BandageAmt > 0) {
            if (slot1 == true && BandageSlot == 0) {BandageSlot =1;}
            else if (slot2 == true && BandageSlot == 0) BandageSlot =2;
            else if (slot3 == true && BandageSlot == 0) BandageSlot =3;
            else if (slot4 == true && BandageSlot == 0) BandageSlot =4;
            else if (slot5 == true && BandageSlot == 0) BandageSlot =5;
            else if (slot6 == true && BandageSlot == 0) BandageSlot =6;
            if (BandageSlot == MedkitSlot && BandageSlot > 0){
                BandageSlot++;
            }
            if (BandageSlot == 1 && slot1 == true && MedkitSlot != 1) {
                g.setColor(Color.WHITE);
                g.fillRoundRect(220-55, 530, 15, 30, 3, 3);
                if (Player.BandageAmt > 1) {
                    g.drawString(Player.BandageAmt + "x", 240-55, 565);
                }
            }
            else if (BandageSlot == 2 && slot2 == true && MedkitSlot != 2) {
                g.setColor(Color.WHITE);
                g.fillRoundRect(220, 530, 15, 30, 3, 3);
                if (Player.BandageAmt > 1) {
                    g.drawString(Player.BandageAmt + "x", 240, 565);
                }
            }
            else if (BandageSlot == 3 && slot3 == true && MedkitSlot != 3) {
                g.setColor(Color.WHITE);
                g.fillRoundRect(220+55, 530, 15, 30, 3, 3);
                if (Player.BandageAmt > 1) {
                    g.drawString(Player.BandageAmt + "x", 240+55, 565);
                }
            }
            else if (BandageSlot == 4 && slot4 == true && MedkitSlot != 4) {
                g.setColor(Color.WHITE);
                g.fillRoundRect(220+110, 530, 15, 30, 3, 3);
                if (Player.BandageAmt > 1) {
                    g.drawString(Player.BandageAmt + "x", 240+110, 565);
                }
            }
            else if (BandageSlot == 5 && slot5 == true && MedkitSlot != 5) {
                g.setColor(Color.WHITE);
                g.fillRoundRect(220+165, 530, 15, 30, 3, 3);
                if (Player.BandageAmt > 1) {
                    g.drawString(Player.BandageAmt + "x", 240+165, 565);
                }
            }
            else if (BandageSlot == 6 && slot6 == true && MedkitSlot != 6) {
                g.setColor(Color.WHITE);
                g.fillRoundRect(220+220, 530, 15, 30, 3, 3);
                if (Player.BandageAmt > 1) {
                    g.drawString(Player.BandageAmt + "x", 240+220, 565);
                }
            }
        }
    }
    public void score(int score){
        this.score = score;
    }
    public int getScore(){
        return score;
    }

}