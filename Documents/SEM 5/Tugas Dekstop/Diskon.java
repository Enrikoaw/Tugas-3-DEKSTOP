public class Diskon extends MenuItem {
    private double besarDiskon;

    public Diskon(String nama, double besarDiskon) {
        super(nama, 0, "Diskon");
        this.besarDiskon = besarDiskon;
    }

    public double getBesarDiskon() { return besarDiskon; }

    @Override
    public void tampilMenu() {
        System.out.println("[Diskon] " + getNama() + " | Potongan: Rp" + besarDiskon);
    }
}