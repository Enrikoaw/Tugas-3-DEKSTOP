import java.util.Scanner;

public class RestoranApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Menu menuRestoran = new Menu();
        Pesanan pesananSaatIni = new Pesanan();
        String menuFile = "menu_data.txt";
        String strukFile = "riwayat_struk.txt";

        
        menuRestoran.muatMenuDariFile(menuFile);

        boolean running = true;
        while (running) {
            System.out.println("\n=== SISTEM MANAJEMEN RESTORAN ===");
            System.out.println("1. Tambah Item Menu Baru");
            System.out.println("2. Tampilkan Menu");
            System.out.println("3. Buat Pesanan");
            System.out.println("4. Cetak Struk & Bayar");
            System.out.println("5. Simpan & Keluar");
            System.out.print("Pilih opsi: ");

            try {
                int pilihan = Integer.parseInt(scanner.nextLine());

                switch (pilihan) {
                    case 1:
                        System.out.print("Jenis (1.Makanan, 2.Minuman, 3.Diskon): ");
                        int jenis = Integer.parseInt(scanner.nextLine());
                        System.out.print("Nama: ");
                        String nama = scanner.nextLine();
                        
                        if (jenis == 1) {
                            System.out.print("Harga: ");
                            double harga = Double.parseDouble(scanner.nextLine());
                            System.out.print("Jenis Makanan (Cth: Pedas): ");
                            String info = scanner.nextLine();
                            menuRestoran.tambahItem(new Makanan(nama, harga, info));
                        } else if (jenis == 2) {
                            System.out.print("Harga: ");
                            double harga = Double.parseDouble(scanner.nextLine());
                            System.out.print("Jenis Minuman (Cth: Dingin): ");
                            String info = scanner.nextLine();
                            menuRestoran.tambahItem(new Minuman(nama, harga, info));
                        } else if (jenis == 3) {
                            System.out.print("Besar Potongan (Rp): ");
                            double pot = Double.parseDouble(scanner.nextLine());
                            menuRestoran.tambahItem(new Diskon(nama, pot));
                        }
                        break;

                    case 2:
                        menuRestoran.tampilkanSemuaMenu();
                        break;

                    case 3:
                        menuRestoran.tampilkanSemuaMenu();
                        System.out.print("Pilih nomor menu untuk dipesan: ");
                        int idx = Integer.parseInt(scanner.nextLine()) - 1;
                        
                        if (idx >= 0 && idx < menuRestoran.getDaftarMenu().size()) {
                            pesananSaatIni.tambahPesanan(menuRestoran.getDaftarMenu().get(idx));
                        } else {
                            throw new IndexOutOfBoundsException("Menu tidak ditemukan!");
                        }
                        break;

                    case 4:
                        pesananSaatIni.cetakStruk();
                        pesananSaatIni.simpanStrukKeFile(strukFile);
                        pesananSaatIni = new Pesanan(); 
                        break;

                    case 5:
                        menuRestoran.simpanMenuKeFile(menuFile);
                        running = false;
                        System.out.println("Terima kasih telah menggunakan aplikasi.");
                        break;

                    default:
                        System.out.println("Pilihan tidak valid.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Masukkan angka yang valid!");
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Terjadi kesalahan: " + e.getMessage());
            }
        }
        scanner.close();
    }
}