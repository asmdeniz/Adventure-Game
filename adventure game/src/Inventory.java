import java.util.ArrayList;

public class Inventory {
    private Weapon weapon;
    private Armor armor;
    private ArrayList<Item> itemList;


    public Inventory() {
        this.weapon = new Weapon("Yumruk", 0, 0, 0);
        this.armor = new Armor(0, "Pacavra", 0, 0);
        this.itemList = new ArrayList<Item>();
    }

    public ArrayList<Item> getItemList() {
        return itemList;
    }

    public void setItemList(Item item) {
        itemList.add(item);
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public Armor getArmor() {
        return armor;
    }

    public void setArmor(Armor armor) {
        this.armor = armor;
    }
}