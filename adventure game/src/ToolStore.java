public class ToolStore extends NormalLocation {
    public ToolStore(Player player){
        super(player, "Magaza");
    }

    @Override
    public boolean onLocation(){
        System.out.println("------ Magazaya hosgeldiniz ! ------");
        boolean showMenu = true;
        while(showMenu){
            System.out.println("1 - Silahlar");
            System.out.println("2 - Zirhlar");
            System.out.println("3 - Cikis yap");
            System.out.println("Seciminiz : ");
            int selectCase = Location.input.nextInt();
            while(selectCase < 1 || selectCase > 3){
                System.out.println("Gecersiz deger girdiniz, tekrar deneyin : ");
                selectCase = input.nextInt();
            }
            switch(selectCase){
                case 1 :
                    printWeapon();
                    buyWeapon();
                    break;

                case 2 :
                    printArmor();
                    buyArmor();
                    break;

                case 3 :
                    System.out.println("Bir daha bekleriz !");
                    showMenu = false;
                    break;
            }
        }
        return true;
    }

    public void printWeapon() {
        System.out.println("----- Silahlar -----");
        System.out.println();
        for (Weapon w : Weapon.weapons()) {
            System.out.println(w.getId() + " - " + w.getName() + " <Para : " + w.getPrice() + " , Hasar : " + w.getDamage() + ">");
        }
        System.out.println("0 - Cikis yap");

    }

    public void buyWeapon() {
        System.out.println("Seciminiz : ");
        int selectedWeaponId = input.nextInt();

        while(selectedWeaponId < 0 || selectedWeaponId > Weapon.weapons().length){
            System.out.println("Gecersiz deger, tekrar deneyiniz : ");
            selectedWeaponId = input.nextInt();
        }

        if (selectedWeaponId != 0) {
            Weapon selectedWeapon = Weapon.getWeaponObjById(selectedWeaponId);

            if(selectedWeapon != null){
                if(selectedWeapon.getPrice() > this.getPlayer().getMoney()){
                    System.out.println("Yeterli paraniz bulunmamaktadir ! ");
                }else{
                    System.out.println(selectedWeapon.getName() + " silahini satin aldiniz ! ");
                    int balance = this.getPlayer().getMoney() - selectedWeapon.getPrice();
                    this.getPlayer().setMoney(balance);
                    System.out.println("Kalan paraniz : " + this.getPlayer().getMoney());
                    this.getPlayer().getInventory().setWeapon(selectedWeapon);
                }
            }
        }



    }

    public void printArmor() {
        System.out.println("----- Zirhlar -----");
        System.out.println();
        for (Armor a : Armor.armors()) {
            System.out.println(a.getId() + " - " + a.getName() + " <Para : " + a.getPrice() + " , Zirh : " + a.getBlock() + ">");
        }
        System.out.println("0 - Cikis yap");
    }

    public void buyArmor() {
        System.out.println("Seciminiz : ");
        int selectArmorId = input.nextInt();

        while(selectArmorId < 0 || selectArmorId > Armor.armors().length){
            System.out.println("Gecersiz deger, tekrar deneyiniz : ");
            selectArmorId = input.nextInt();
        }



        if (selectArmorId != 0){
            Armor selectedArmor = Armor.getArmorObjById(selectArmorId);
            if(selectedArmor != null){
                if(selectedArmor.getPrice() > this.getPlayer().getMoney()){
                    System.out.println("Yeterli paraniz bulunmamaktadir ! ");
                }else{
                    System.out.println(selectedArmor.getName() + " zirhini satin aldiniz ! ");
                    int balance = this.getPlayer().getMoney() - selectedArmor.getPrice();
                    this.getPlayer().setMoney(balance);
                    System.out.println("Kalan paraniz : " + this.getPlayer().getMoney());
                    this.getPlayer().getInventory().setArmor(selectedArmor);
                }
            }
        }
    }

}