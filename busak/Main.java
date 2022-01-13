package busak;

import busak.dao.DAOErabiltzaile;
import busak.objektuak.Erabiltzaile;

public class Main {

    private static Erabiltzaile unekoErabiltzaile = null;

    public static void main(String[] args) {
        menu();
    }

    private static void menu() {
        int aukera = -1;
        do {
            System.out.println("Sartu behean dagoen aukeretako zenbaki bat");
            System.out.println();
            System.out.println("╔═══╦══════════════════╗");
            System.out.println("║ 1 ║ Logeatu          ║");
            System.out.println("╠═══╬══════════════════╣");
            System.out.println("║ 2 ║ Erregistratu     ║");
            System.out.println("╠═══╬══════════════════╣");
            System.out.println("║ 3 ║ Bilete bat erosi ║");
            System.out.println("╠═══╬══════════════════╣");
            System.out.println("║ 4 ║ Atera            ║");
            System.out.println("╚═══╩══════════════════╝");

            aukera = Utilities.eskatuInt(3);

            switch (aukera) {
                case 1:
                    logeatu();
                    break;
                case 2:
                    erregistratu();
                    break;
                case 3:
                    if (unekoErabiltzaile != null) {
                        bileteaErosi();
                    } else {
                        System.out.println("Logeatu behar zara bilete bat erosteko");
                    }
                default:
                    break;
            }

        } while (0 < aukera && aukera < 4);
    }

    private static void bileteaErosi() {
    }

    private static void erregistratu() {
        Erabiltzaile erabiltzaileBerri = new Erabiltzaile();

        System.out.println("ERABILTZAILE BERRIA ESKATU");
        System.out.print("Erabiltzailearen NAN: ");
        String nan = Utilities.eskatuNan();
        erabiltzaileBerri.setNanAiz(nan);

        System.out.print("\n" + "Erabiltzailearen Izen Abizenak: ");
        String izenabiz = Utilities.eskatuString(3);
        erabiltzaileBerri.setIzenAbizenak(izenabiz);

        System.out.print("\n" + "Pasahitza sortu: ");
        String pasahitza = Utilities.eskatuPass();
        erabiltzaileBerri.setPasahitza(pasahitza);

        System.out.println("\n" + "DATUAK");
        System.out.println("NAN: " + nan);
        System.out.println("Izen Abizenak: " + izenabiz);
        System.out.println("Pasahitza: " + "*".repeat(pasahitza.length()));

        System.out.println("Erabiltzaile hau sortu nahi duzu (b/e)?");
        boolean baiez = Utilities.eskatuBaiEz();

        if (baiez) {
            DAOErabiltzaile erabil = new DAOErabiltzaile();
            erabil.insert(erabiltzaileBerri);
        }

    }

    private static void logeatu() {
        DAOErabiltzaile daoE = new DAOErabiltzaile();
        Erabiltzaile erabiltzailea;
        boolean ok = false;
        boolean atera = false;

        do {

            System.out.println("Sartu NAN/AIZ:");
            String nanAiz = Utilities.eskatuNan();
            erabiltzailea = daoE.getByNan(nanAiz);
            if (erabiltzailea != null) {
                do {
                    System.out.println("Sartu pasahitza:");
                    String pasa = Utilities.eskatuPass();

                    erabiltzailea = daoE.getByNanPass(nanAiz, pasa);

                    if (erabiltzailea != null) {
                        unekoErabiltzaile = erabiltzailea;
                        ok = true;
                        System.out.println("Login-a ondo atera da");
                    } else {
                        System.out.println("Pasahitza okerra da");
                        System.out.println("Login-etik atera nahi zara");
                        atera = Utilities.eskatuBaiEz();
                    }
                } while (!ok && !atera);
            } else {
                System.out.println("Erabiltzailea ez da existitzen");
                System.out.println("Login-etik atera nahi zara");
                atera = Utilities.eskatuBaiEz();
            }
        } while (!ok && !atera);
        System.out.println("\nKaixo " + unekoErabiltzaile.getIzenAbizenak());
    }
}
