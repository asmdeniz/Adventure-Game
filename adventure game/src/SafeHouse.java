public class SafeHouse extends NormalLocation {

    public SafeHouse(Player player){
        super(player, "Guvenli Ev");
    }

    @Override
    public boolean onLocation() {
        System.out.println("Guvenli evdesiniz ! ");
        System.out.println("Caniniz yenilendi ! ");
        this.getPlayer().setHealth(this.getPlayer().getOrgHealth());
        return true;
    }
}