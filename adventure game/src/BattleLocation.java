import java.util.Random;

public abstract class BattleLocation extends Location {
    private Obstacle obstacle;
    private Item item;
    private int maxObstacle;


    public BattleLocation(Player player, String name, Obstacle obstacle, Item item, int maxObstacle){
        super(player, name);
        this.obstacle = obstacle;
        this.setItem(item);
        this.maxObstacle = maxObstacle;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public int getMaxObstacle() {
        return maxObstacle;
    }

    public void setMaxObstacle(int maxObstacle) {
        this.maxObstacle = maxObstacle;
    }

    @Override
    boolean onLocation() {
        int obsNumber = this.randomObstacleNumber();
        System.out.println("Su an buradasiniz " + this.getName());
        System.out.println("Dikkatli ol ! Burada " + obsNumber + " tane " + this.getObstacle().getName() + " yasiyor !");
        System.out.println("<S>avas veya <K>ac ");
        String selectCase = input.nextLine();
        selectCase = selectCase.toUpperCase();
        if (selectCase.equals("S") && combat(obsNumber) && this.getName().equals("Magara")){
            System.out.println("Bu bölgedeki tum düsmanlari yendiniz !");
            System.out.println(this.getItem().getName() +  " esyasini kazandiniz !");
            this.getPlayer().getInventory().setItemList(this.getItem());
            return true;
        }else if(selectCase.equals("S") && combat(obsNumber) && this.getName().equals("Orman")){
            System.out.println("Bu bölgedeki tum düsmanlari yendiniz !");
            System.out.println(this.getItem().getName() +  " esyasini kazandiniz !");
            this.getPlayer().getInventory().setItemList(this.getItem());
            return true;
        }else if(selectCase.equals("S") && combat(obsNumber) && this.getName().equals("Nehir")){
            System.out.println("Bu bölgedeki tum düsmanlari yendiniz !");
            System.out.println(this.getItem().getName() +  " esyasini kazandiniz !");
            this.getPlayer().getInventory().setItemList(this.getItem());
            return true;
        }else if(selectCase.equals("S") && combat(obsNumber) && this.getName().equals("Maden")){
            System.out.println("Bu bölgedeki tum düsmanlari yendiniz !");
            return true;
        }else if(selectCase.equals("K")){
            return true;
        }else if(this.getPlayer().getHealth() <= 0){
            System.out.println("Öldünüz !");
            return false;
        }else if(selectCase.equals("S") && !combat(obsNumber)){
            return true;
        }else{
            System.out.println("Hatali giris yaptiniz, tekrar deneyin : ");
            return true;
        }
    }

    public boolean combat(int obsNumber){
        for (int i = 1; i<= obsNumber; i++){
            this.getObstacle().setHealth(this.getObstacle().getOrginalHealth());
            playerStats();
            obstacleStats(i);
            double a = Math.random();
            if (a>= 0.5){
                System.out.println("Ilk vurus hakki sizin !");
                while(this.getPlayer().getHealth() > 0 && this.getObstacle().getHealth() > 0){
                    System.out.println();
                    System.out.println("<V>ur veya <K>ac");
                    String selectCombat = input.nextLine().toUpperCase();
                    if (selectCombat.equals("V")){
                        System.out.println("Siz vurdunuz !");
                        this.getObstacle().setHealth(this.obstacle.getHealth() - this.getPlayer().getTotalDamage());
                        afterHit();
                        if (this.getObstacle().getHealth() > 0){
                            System.out.println();
                            System.out.println("Canavar size vurdu ! ");
                            int obstacleDamage = this.getObstacle().getDamage() - this.getPlayer().getInventory().getArmor().getBlock();
                            if (obstacleDamage < 0 ){
                                obstacleDamage = 0;
                            }
                            this.getPlayer().setHealth(this.getPlayer().getHealth() - obstacleDamage);
                            afterHit();
                        }
                    }else if(selectCombat.equals("K")){
                        return false;
                    }else{
                        System.out.println("Hatali giris yaptiniz, tekrar deneyin : ");
                    }
                }
            }else {
                System.out.println("Ilk vurus hakki canavarin !");
                while(this.getPlayer().getHealth() > 0 && this.getObstacle().getHealth() > 0){
                    System.out.println();
                    System.out.println("Canavar size vurdu ! ");
                    int obstacleDamage = this.getObstacle().getDamage() - this.getPlayer().getInventory().getArmor().getBlock();
                    if (obstacleDamage < 0 ){
                        obstacleDamage = 0;
                    }
                    this.getPlayer().setHealth(this.getPlayer().getHealth() - obstacleDamage);
                    afterHit();
                    if (this.getPlayer().getHealth() > 0){
                        boolean key = true;
                        while(key){
                            System.out.println("<V>ur veya <K>ac");
                            String selectCombat = input.nextLine().toUpperCase();
                            if (selectCombat.equals("V")){
                                System.out.println("Siz vurdunuz !");
                                this.getObstacle().setHealth(this.obstacle.getHealth() - this.getPlayer().getTotalDamage());
                                afterHit();
                                key=false;
                            }else if(selectCombat.equals("K")){
                                return false;
                            }else{
                                System.out.println("Hatali giris yaptiniz, tekrar deneyin : ");
                            }
                        }
                    }
                }
            }

            if (this.getObstacle().getHealth() == 0){
                if (this.getName().equals("Maden")){
                    System.out.println("Dusmani yendiniz !");
                    this.awardGenerator();
                }else {
                    System.out.println("Dusmani yendiniz !");
                    System.out.println(this.getObstacle().getMoney() + " para kazandiniz !");
                    this.getPlayer().setMoney(this.getPlayer().getMoney() + this.getObstacle().getMoney());
                    System.out.println("Guncel paraniz " + this.getPlayer().getMoney());
                }
            }else {
                return false;
            }
        }
        return true;
    }

    public void afterHit(){
        System.out.println("Caniniz : " + this.getPlayer().getHealth());
        System.out.println(this.getObstacle().getName() + " cani : " + this.getObstacle().getHealth());
        System.out.println();
    }

    public void playerStats(){
        System.out.println("Oyuncu degerleri");
        System.out.println("----------------------------");
        System.out.println("Saglik : " + this.getPlayer().getHealth());
        System.out.println("Silah : " + this.getPlayer().getInventory().getWeapon().getName());
        System.out.println("Zirh : " + this.getPlayer().getInventory().getArmor().getName());
        System.out.println("Bloklama : " + this.getPlayer().getInventory().getArmor().getBlock());
        System.out.println("Hasar : " + this.getPlayer().getDamage());
        System.out.println("Para : " + this.getPlayer().getMoney());
        System.out.println();
    }

    public void obstacleStats(int i){
        System.out.println(i + ". " + this.getObstacle().getName() + " degerleri");
        System.out.println("----------------------------");
        System.out.println("Saglik : " + this.getObstacle().getHealth());
        System.out.println("Hasar : " + this.getObstacle().getDamage());
        System.out.println("Ödül : " + this.getObstacle().getMoney());
        System.out.println();
    }

    public int randomObstacleNumber(){
        Random r = new Random();
        return r.nextInt(this.getMaxObstacle()) + 1;
    }

    public Obstacle getObstacle() {
        return obstacle;
    }

    public void setObstacle(Obstacle obstacle) {
        this.obstacle = obstacle;
    }

    private void awardGenerator(){
        int number = (int)(Math.random()*100);
        if (number <15){
            int number2 = (int)(Math.random()*100);
            if (number2 < 20){
                System.out.println("Tebrikler, Tufek kazandiniz !");
                this.getPlayer().getInventory().setWeapon(new Weapon("Tufek", 3, 7, 45));
                System.out.println("Yeni silahiniz " + this.getPlayer().getInventory().getWeapon());
            }else if (number2 > 20 && number2 < 50){
                System.out.println("Tebrikler, Kilic kazandiniz !");
                this.getPlayer().getInventory().setWeapon(new Weapon("Kilic", 2, 3, 35));
                System.out.println("Yeni silahiniz " + this.getPlayer().getInventory().getWeapon());
            }else {
                System.out.println("Tebrikler, Tabanca kazandiniz !");
                this.getPlayer().getInventory().setWeapon(new Weapon("Tabanca", 1, 2, 25));
                System.out.println("Yeni silahiniz " + this.getPlayer().getInventory().getWeapon());
            }
        }else if(number >15 && number <30){
            int number2 = (int)(Math.random()*100);
            if (number2 < 20){
                System.out.println("Tebrikler, Agir zirh kazandiniz !");
                this.getPlayer().getInventory().setArmor(new Armor(3, "Agir", 5, 40));
                System.out.println("Yeni zirhiniz " + this.getPlayer().getInventory().getArmor());
            }else if (number2 > 20 && number2 < 50){
                System.out.println("Tebrikler, Orta zirh kazandiniz !");
                this.getPlayer().getInventory().setArmor(new Armor(2, "Orta", 3, 25));
                System.out.println("Yeni zirhiniz " + this.getPlayer().getInventory().getArmor());
            }else {
                System.out.println("Tebrikler, Hafif zirh kazandiniz !");
                this.getPlayer().getInventory().setArmor(new Armor(1, "Hafif", 1, 15));
                System.out.println("Yeni zirhiniz " + this.getPlayer().getInventory().getArmor());
            }
        }else if(number >30 && number <55){
            int number2 = (int)(Math.random()*100);
            if (number2 < 20){
                System.out.println("Tebrikler, 10 para kazandiniz");
                this.getPlayer().setMoney(this.getPlayer().getMoney() + 10);
                System.out.println("Guncel paraniz " + this.getPlayer().getMoney());
            }else if (number2 > 20 && number2 < 50){
                System.out.println("Tebrikler, 5 para kazandiniz");
                this.getPlayer().setMoney(this.getPlayer().getMoney() + 5);
                System.out.println("Guncel paraniz " + this.getPlayer().getMoney());
            }else {
                System.out.println("Tebrikler, 1 para kazandiniz");
                this.getPlayer().setMoney(this.getPlayer().getMoney() + 1);
                System.out.println("Guncel paraniz " + this.getPlayer().getMoney());
            }
        }else {
            System.out.println("Bu canavardan esya yada para dusmedi !");
        }
    }
}