import java.awt.*;
import java.util.Random;
import java.util.Random.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

//klass keyinput tillsätter tangentbord
public class KeyInput extends KeyAdapter{

    //variabler
    private Handler handler;
    private HUD hud;
    public KeyInput(Handler handler){
        this.handler = handler;
    }
    public static int projectileDirection = 1;
    public static int DamageCost = 5;
    public static int MaxHealthCost = 5;
    public static int MenuScroll = 0;

    //när knapp trycks
    public void keyPressed (KeyEvent e) {
        int key = e.getKeyCode();
        //letar efter spel objekt
        for(int i=0; i< handler.object.size();i++){
            GameObject tempObject = handler.object.get(i);
            if (Game.Start == false){
                if (key == KeyEvent.VK_ESCAPE){
                    Game.Start = true;
                    break;
                }
            }
            if (Game.Start == true) {
                if (Game.Pause == true) {
                    if (key == KeyEvent.VK_SPACE){
                        Game.Controls = true;
                        Game.Pause = false;
                        break;
                    }
                    if (key == KeyEvent.VK_ESCAPE) {
                        Game.Pause = false;
                        break;
                    }
                }
                if (Game.Controls==true){
                    if (key == KeyEvent.VK_SPACE){
                        Game.Pause=true;
                        Game.Controls=false;
                        break;
                    }
                    if (key == KeyEvent.VK_ESCAPE){
                        Game.Controls=false;
                        break;
                    }
                }
            }
            if (Game.Start == true) {
                if (Game.Pause == false && Game.Controls == false) {
                    if (key == KeyEvent.VK_ESCAPE) {
                        Game.Pause = true;
                        break;
                    }
                }
            }
            if (Game.Start == true && Game.Pause == false && Game.Controls==false) {
                //om spelobjekt är spelare 1
                if (tempObject.getId() == ID.Player1) {
                    if (key == KeyEvent.VK_W && Player.PlayerMovelock == false) tempObject.setVelY(-5);
                    if (key == KeyEvent.VK_S && Player.PlayerMovelock == false) tempObject.setVelY(5);
                    if (key == KeyEvent.VK_D && Player.PlayerMovelock == false) tempObject.setVelX(5);
                    if (key == KeyEvent.VK_A && Player.PlayerMovelock == false) tempObject.setVelX(-5);
                    if (key == KeyEvent.VK_LEFT) KeyInput.projectileDirection = 1;
                    if (key == KeyEvent.VK_RIGHT) KeyInput.projectileDirection = 2;
                    if (key == KeyEvent.VK_DOWN) KeyInput.projectileDirection = 3;
                    if (key == KeyEvent.VK_UP) KeyInput.projectileDirection = 4;
                    //------------------------Upgrades------------------------
                    //Damage
                    if (key == KeyEvent.VK_Q && Game.Coins >= DamageCost) {
                        Game.Coins = Game.Coins - DamageCost;
                        Game.Damage = Game.Damage + 40;
                        DamageCost = DamageCost + 1;
                    }
                    //Max Health
                    if (key == KeyEvent.VK_E && Game.Coins >= MaxHealthCost) {
                        Game.Coins = Game.Coins - MaxHealthCost;
                        Player.HP = Player.HP + 10;
                        MaxHealthCost = MaxHealthCost + 1;
                        Player.MaxHP = Player.MaxHP + 10;
                    }

                    //Hotbar slot 1
                    if (key == KeyEvent.VK_1) {

                        if (HUD.MedkitSlot == 1 && Player.MedkitAmt >= 1) {
                            if (Player.HP <= Player.MaxHP - 50) {
                                Player.HP += 50;
                            } else if (Player.HP > Player.MaxHP - 50) {
                                Player.HP = Player.MaxHP;
                            }
                            Player.MedkitAmt--;
                            HUD.slot1 = true;
                            if (Player.MedkitAmt == 0) {
                                HUD.MedkitSlot = 0;
                            }
                        } else if (HUD.BandageSlot == 1 && Player.BandageAmt >= 1) {
                            if (Player.HP <= Player.MaxHP - 20) {
                                Player.HP += 20;
                            } else if (Player.HP > Player.MaxHP - 20) {
                                Player.HP = Player.MaxHP;
                            }
                            Player.BandageAmt--;
                            HUD.slot1 = true;
                            if (Player.BandageAmt == 0) {
                                HUD.BandageSlot = 0;
                            }
                        }
                    }
                    //Hotbar slot 2
                    if (key == KeyEvent.VK_2) {

                        if (HUD.MedkitSlot == 2 && Player.MedkitAmt >= 1) {
                            if (Player.HP <= Player.MaxHP - 50) {
                                Player.HP += 50;
                            } else if (Player.HP > Player.MaxHP - 50) {
                                Player.HP = Player.MaxHP;
                            }
                            Player.MedkitAmt--;
                            HUD.slot1 = true;
                            if (Player.MedkitAmt == 0) {
                                HUD.MedkitSlot = 0;
                            }
                        } else if (HUD.BandageSlot == 2 && Player.BandageAmt >= 1) {
                            if (Player.HP <= Player.MaxHP - 20) {
                                Player.HP += 20;
                            } else if (Player.HP > Player.MaxHP - 20) {
                                Player.HP = Player.MaxHP;
                            }
                            Player.BandageAmt--;
                            HUD.slot1 = true;
                            if (Player.BandageAmt == 0) {
                                HUD.BandageSlot = 0;
                            }
                        }
                    }
                    //Hotbar slot 3
                    if (key == KeyEvent.VK_3) {

                        if (HUD.MedkitSlot == 3 && Player.MedkitAmt >= 1) {
                            if (Player.HP <= Player.MaxHP - 50) {
                                Player.HP += 50;
                            } else if (Player.HP > Player.MaxHP - 50) {
                                Player.HP = Player.MaxHP;
                            }
                            Player.MedkitAmt--;
                        } else if (HUD.BandageSlot == 3 && Player.BandageAmt >= 1) {
                            if (Player.HP <= Player.MaxHP - 20) {
                                Player.HP += 20;
                            } else if (Player.HP > Player.MaxHP - 20) {
                                Player.HP = Player.MaxHP;
                            }
                            Player.BandageAmt--;
                        }


                    }
                    //Hotbar slot 4
                    if (key == KeyEvent.VK_4) {

                        if (HUD.MedkitSlot == 4 && Player.MedkitAmt >= 1) {
                            if (Player.HP <= Player.MaxHP - 50) {
                                Player.HP += 50;
                            } else if (Player.HP > Player.MaxHP - 50) {
                                Player.HP = Player.MaxHP;
                            }
                            Player.MedkitAmt--;
                        } else if (HUD.BandageSlot == 4 && Player.BandageAmt >= 1) {
                            if (Player.HP <= Player.MaxHP - 20) {
                                Player.HP += 20;
                            } else if (Player.HP > Player.MaxHP - 20) {
                                Player.HP = Player.MaxHP;
                            }
                            Player.BandageAmt--;
                        }
                    }
                    //Hotbar slot 5
                    if (key == KeyEvent.VK_5) {

                        if (HUD.MedkitSlot == 5 && Player.MedkitAmt >= 1) {
                            if (Player.HP <= Player.MaxHP - 50) {
                                Player.HP += 50;
                            } else if (Player.HP > Player.MaxHP - 50) {
                                Player.HP = Player.MaxHP;
                            }
                            Player.MedkitAmt--;
                        } else if (HUD.BandageSlot == 5 && Player.BandageAmt >= 1) {
                            if (Player.HP <= Player.MaxHP - 20) {
                                Player.HP += 20;
                            } else if (Player.HP > Player.MaxHP - 20) {
                                Player.HP = Player.MaxHP;
                            }
                            Player.BandageAmt--;
                        }
                    }
                    //Hotbar slot 6
                    if (key == KeyEvent.VK_6) {

                        if (HUD.MedkitSlot == 6 && Player.MedkitAmt >= 1) {
                            if (Player.HP <= Player.MaxHP - 50) {
                                Player.HP += 50;
                            } else if (Player.HP > Player.MaxHP - 50) {
                                Player.HP = Player.MaxHP;
                            }
                            Player.MedkitAmt--;
                        } else if (HUD.BandageSlot == 6 && Player.BandageAmt >= 1) {
                            if (Player.HP <= Player.MaxHP - 20) {
                                Player.HP += 20;
                            } else if (Player.HP > Player.MaxHP - 20) {
                                Player.HP = Player.MaxHP;
                            }
                            Player.BandageAmt--;
                        }
                    }

                }
            }
        }
    }
    //när knapp släpps
    public void keyReleased (KeyEvent e) {
        int key = e.getKeyCode();
        //letar efter spelobjekt
        for(int i=0; i< handler.object.size();i++){
            GameObject tempObject = handler.object.get(i);
            //om spelobjekt är spelare 1
            if(tempObject.getId()== ID.Player1) {

                if (key == KeyEvent.VK_W) {
                    tempObject.setVelY(0);
                }
                if (key == KeyEvent.VK_S) {
                    tempObject.setVelY(0);
                }
                if (key == KeyEvent.VK_D) {
                    tempObject.setVelX(0);
                }
                if (key == KeyEvent.VK_A) {
                    tempObject.setVelX(0);
                }
                if (Game.Start == true && Game.Pause == false && Game.Controls == false) {
                    if (key == KeyEvent.VK_SPACE) {
                        handler.addObject(new Projectile(tempObject.x + 14, tempObject.y + 14, ID.PP, handler));
                    }
                }
            }
        }
    }
}
