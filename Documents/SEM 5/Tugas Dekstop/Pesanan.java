import java.io.*;
import java.util.ArrayList;

public class Pesanan {
    private ArrayList<MenuItem> itemPesanan = new ArrayList<>();

    public void tambahPesanan(MenuItem item) {
        itemPesanan.add(item);
        System.out.println(item.getNama() + " ditambahkan ke pesanan.");
    }

    public double hitungTotal() {
        double total = 0;
        double totalDiskon = 0;

        for (MenuItem item : itemPesanan) {
            if (item instanceof Diskon) {
                totalDiskon += ((Diskon) item).getBesarDiskon();
            } else {
                total += item.getHarga();
            }
        }
        return Math.max(0, total - totalDiskon);
    }

    public void cetakStruk() {
        System.out.println("\n=== STRUK PESANAN ===");
        for (MenuItem item : itemPesanan) {
            item.tampilMenu();
        }
        System.out.println("---------------------");
        System.out.println("Total Bayar: Rp" + hitungTotal());
        System.out.println("=====================");
    }

    public void simpanStrukKeFile(String namaFile) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(namaFile, true))) {
            writer.write("--- Transaksi Baru ---");
            writer.newLine();
            for (MenuItem item : itemPesanan) {
                writer.write(item.getNama() + " - Rp" + item.getHarga());
                writer.newLine();
            }
            writer.write("Total: Rp" + hitungTotal());
            writer.newLine();
            writer.newLine();
            System.out.println("Struk disimpan ke " + namaFile);
        } catch (IOException e) {
            System.out.println("Gagal menyimpan struk: " + e.getMessage());
        }
    }
}