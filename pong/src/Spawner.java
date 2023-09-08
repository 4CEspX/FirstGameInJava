import java.util.Random;

public class Spawner {
    private Handler handler;
        private HUD hud;
        private Random r = new Random();

        public Spawner(Handler handler, HUD hud) {
            this.handler = handler;
            this.hud = hud;
        }

        public static int EnemyCount = 0;
            int timer=0;
            public static int level = 1;
            public static int wave = 1;
            public static int EnemyAmt = 0;
        public void tick(){
            timer++;
            //levels
            if (wave == 10 || wave == 20){
                level++;
                wave = 0;
            }
            //Level 1
            if (timer > 200 & EnemyCount == 0 & level == 1){
                Enemy.EnemyMoveLock = true;
                timer=0;
                Random r1 = new Random();
                Random r2 = new Random();

                for (int i = 0; i< wave;i++) {
                    int SpawnLocationX = r1.nextInt(600);
                    int SpawnLocationY = r2.nextInt(600);
                    if(SpawnLocationX > 100 && SpawnLocationX < 400){
                        SpawnLocationX = r1.nextInt(600);
                    }
                    if(SpawnLocationY > 100 && SpawnLocationY < 300){
                        SpawnLocationY = r1.nextInt(600);
                    }
                    handler.addObject(new Enemy(SpawnLocationX,SpawnLocationY,ID.Enemy1,handler));
                    EnemyCount++;


                }
                wave++;
                new Thread(()->{
                    try {
                        Thread.sleep(800);
                        Enemy.EnemyMoveLock = false;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }).start();
            }
            //Level 2
            if (timer > 200 & EnemyCount == 0 & level == 2){
                Enemy.EnemyMoveLock = true;
                timer=0;

                Random r1 = new Random();
                Random r2 = new Random();
                Random r3 = new Random();
                Random r4 = new Random();

                for (int i = 0; i< wave;i++) {

                    int SpawnLocationX1 = r1.nextInt(600);
                    int SpawnLocationY1 = r2.nextInt(600);
                    if(SpawnLocationX1 > 100 && SpawnLocationX1 < 400){
                        SpawnLocationX1 = r1.nextInt(600);
                    }
                    if(SpawnLocationY1 > 100 && SpawnLocationY1 < 300){
                        SpawnLocationY1 = r1.nextInt(600);
                    }
                    int SpawnLocationX2 = r1.nextInt(600);
                    int SpawnLocationY2 = r2.nextInt(600);
                    if(SpawnLocationX2 > 100 && SpawnLocationX2 < 400){
                        SpawnLocationX2 = r1.nextInt(600);
                    }
                    if(SpawnLocationY2 > 100 && SpawnLocationY2 < 300){
                        SpawnLocationY2 = r1.nextInt(600);
                    }

                    int RandomEnemy1 = r3.nextInt(2);
                    int RandomEnemy2 = r4.nextInt(2);

                    if (RandomEnemy1 > 0){
                        RandomEnemy1--;
                        handler.addObject(new Enemy(SpawnLocationX1,SpawnLocationY1,ID.Enemy1,handler));
                        EnemyCount++;
                    }
                    if (RandomEnemy2 > 0){
                        RandomEnemy2--;
                        handler.addObject(new Enemy(SpawnLocationX2,SpawnLocationY2,ID.Enemy2,handler));
                        EnemyCount++;
                    }

                    if (RandomEnemy1 == 0 && RandomEnemy2 == 0){
                        RandomEnemy1 = r3.nextInt(2);
                        RandomEnemy2 = r4.nextInt(2);
                    }

                }
                wave++;
                new Thread(()->{
                    try {
                        Thread.sleep(800);
                        Enemy.EnemyMoveLock = false;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }).start();

            }

            //level 3
            if (timer > 200 & EnemyCount == 0 & level == 3){
                Enemy.EnemyMoveLock = true;
                timer=0;

                Random r1 = new Random();
                Random r2 = new Random();
                Random r3 = new Random();
                Random r4 = new Random();
                Random r5 = new Random();

                for (int i = 0; i< wave;i++) {

                    int SpawnLocationX1 = r1.nextInt(600);
                    int SpawnLocationY1 = r2.nextInt(600);
                    if(SpawnLocationX1 > 100 && SpawnLocationX1 < 400){
                        SpawnLocationX1 = r1.nextInt(600);
                    }
                    if(SpawnLocationY1 > 100 && SpawnLocationY1 < 300){
                        SpawnLocationY1 = r1.nextInt(600);
                    }
                    int SpawnLocationX2 = r1.nextInt(600);
                    int SpawnLocationY2 = r2.nextInt(600);
                    if(SpawnLocationX2 > 100 && SpawnLocationX2 < 400){
                        SpawnLocationX2 = r1.nextInt(600);
                    }
                    if(SpawnLocationY2 > 100 && SpawnLocationY2 < 300){
                        SpawnLocationY2 = r1.nextInt(600);
                    }
                    int SpawnLocationX3 = r1.nextInt(600);
                    int SpawnLocationY3 = r2.nextInt(600);
                    if(SpawnLocationX3 > 100 && SpawnLocationX3 < 400){
                        SpawnLocationX3 = r1.nextInt(600);
                    }
                    if(SpawnLocationY3 > 100 && SpawnLocationY3 < 300){
                        SpawnLocationY3 = r1.nextInt(600);
                    }

                    int RandomEnemy1 = r3.nextInt(2);
                    int RandomEnemy2 = r4.nextInt(2);
                    int RandomEnemy3 = r5.nextInt(2);

                    if (RandomEnemy1 > 0){
                        RandomEnemy1--;
                        handler.addObject(new Enemy(SpawnLocationX1,SpawnLocationY1,ID.Enemy1,handler));
                        EnemyCount++;
                    }
                    if (RandomEnemy2 > 0){
                        RandomEnemy2--;
                        handler.addObject(new Enemy(SpawnLocationX2,SpawnLocationY2,ID.Enemy2,handler));
                        EnemyCount++;
                    }
                    if (RandomEnemy3 > 0){
                        RandomEnemy3--;
                        handler.addObject(new Enemy(SpawnLocationX3,SpawnLocationY3,ID.Enemy3,handler));
                        EnemyCount++;
                    }
                    if (RandomEnemy1 == 0 && RandomEnemy2 == 0 && RandomEnemy3 == 0){
                        RandomEnemy1 = r3.nextInt(2);
                        RandomEnemy2 = r4.nextInt(2);
                        RandomEnemy3 = r5.nextInt(2);
                    }

                }
                wave++;
                new Thread(()->{
                    try {
                        Thread.sleep(800);
                        Enemy.EnemyMoveLock = false;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }).start();

            }
        }
    }

