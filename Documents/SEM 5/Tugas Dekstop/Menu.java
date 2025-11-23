import java.io.*;
import java.util.ArrayList;

public class Menu {
    private ArrayList<MenuItem> daftarMenu = new ArrayList<>();

    public void tambahItem(MenuItem item) {
        daftarMenu.add(item);
    }

    public ArrayList<MenuItem> getDaftarMenu() {
        return daftarMenu;
    }

    public void tampilkanSemuaMenu() {
        System.out.println("\n=== DAFTAR MENU RESTORAN ===");
        if (daftarMenu.isEmpty()) {
            System.out.println("Menu belum tersedia.");
        } else {
            for (int i = 0; i < daftarMenu.size(); i++) {
                System.out.print((i + 1) + ". ");
                daftarMenu.get(i).tampilMenu();
            }
        }
    }

    public void simpanMenuKeFile(String namaFile) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(namaFile))) {
            for (MenuItem item : daftarMenu) {
                String line = "";
                if (item instanceof Makanan) {
                    line = "Makanan," + item.getNama() + "," + item.getHarga() + "," + ((Makanan) item).getJenisMakanan();
                } else if (item instanceof Minuman) {
                    line = "Minuman," + item.getNama() + "," + item.getHarga() + "," + ((Minuman) item).getJenisMinuman();
                } else if (item instanceof Diskon) {
                    line = "Diskon," + item.getNama() + "," + ((Diskon) item).getBesarDiskon();
                }
                writer.write(line);
                writer.newLine();
            }
            System.out.println("Menu berhasil disimpan ke " + namaFile);
        } catch (IOException e) {
            System.out.println("Gagal menyimpan menu: " + e.getMessage());
        }
    }

    public void muatMenuDariFile(String namaFile) {
        daftarMenu.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader(namaFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts[0].equals("Makanan")) {
                    tambahItem(new Makanan(parts[1], Double.parseDouble(parts[2]), parts[3]));
                } else if (parts[0].equals("Minuman")) {
                    tambahItem(new Minuman(parts[1], Double.parseDouble(parts[2]), parts[3]));
                } else if (parts[0].equals("Diskon")) {
                    tambahItem(new Diskon(parts[1], Double.parseDouble(parts[2])));
                }
            }
            System.out.println("Menu berhasil dimuat dari " + namaFile);
        } catch (IOException | NumberFormatException e) {
            System.out.println("Gagal memuat menu (File mungkin belum ada): " + e.getMessage());
        }
    }
}