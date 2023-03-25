import java.util.*;

public class Player {

    private int damage;
    private int health;
    private int money;
    private String name;
    private String charName;
    private Scanner input = new Scanner(System.in);
    private Inventory inventory;
    private int orgHealth;

    public Player(String name){
        this.name=name;
        this.inventory = new Inventory();
    }

    public int getOrgHealth() {
        return orgHealth;
    }

    public void setOrgHealth(int orgHealth) {
        this.orgHealth = orgHealth;
    }

    public void printInfo(){
        System.out.println();
        System.out.println(
                "Silahiniz : " + this.getInventory().getWeapon().getName() +
                        ", Zirh : " + this.getInventory().getArmor().getName() +
                        ", Bloklama : " + this.getInventory().getArmor().getBlock() +
                        ", Hasariniz : " + this.getTotalDamage() +
                        ", Sagliginiz : " + this.getHealth() +
                        ", Paraniz : " + this.getMoney());
    }

    public Inventory getInventory(){
        return inventory;
    }

    public void setInventory(Inventory inventory){
        this.inventory = inventory;
    }

    public int getTotalDamage(){
        return damage + this.getInventory().getWeapon().getDamage();
    }

    public int getDamage(){
        return damage;
    }

    public void setDamage(int damage){
        this.damage = damage;
    }

    public int getHealth(){
        return health;
    }

    public void setHealth(int health){
        this.health = health;
    }

    public int getMoney(){
        return money;
    }

    public void setMoney(int money){
        this.money = money;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getCharName(){
        return charName;
    }

    public void setCharName(String charName){
        this.charName = charName;
    }

    public void selectChar(){

        System.out.println("----------------------------------------------");

        GameChar[] charList = {new Samurai(), new Archer(), new Knight()};

        for (GameChar gameChar : charList) {
            System.out.println("ID : " + gameChar.getId() +
                    "\tKarakter : " + gameChar.getName() +
                    "\tHasar : " + gameChar.getDamage() +
                    "\tSaglik : " + gameChar.getHealth() +
                    "\tPara : " + gameChar.getMoney());
        }

        System.out.println("----------------------------------------------");
        System.out.println("Lutfen bir karakter seciniz :");
        int selectChar = input.nextInt();
        switch (selectChar) {
            case 1:
                initPlayer(new Samurai());
                break;

            case 2:
                initPlayer(new Archer());
                break;

            case 3:
                initPlayer(new Knight());
                break;

            default:
                initPlayer(new Samurai());
        }
        System.out.println("Karakter : " + this.getCharName() + "\tHasar : " + this.getDamage() + "\tSaglik :  " + this.getHealth() + "\tPara : " + this.getMoney());
    }

    public void initPlayer(GameChar gameChar){
        this.setCharName(gameChar.getName());
        this.setDamage(gameChar.getDamage());
        this.setHealth(gameChar.getHealth());
        this.setOrgHealth(gameChar.getHealth());
        this.setMoney(gameChar.getMoney());
    }


}