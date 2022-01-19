package busak;

import java.time.LocalDateTime;
import java.util.ArrayList;

import busak.dao.DAOBilete;
import busak.dao.DAOErabiltzaile;
import busak.dao.DAOLinea;
import busak.objektuak.Bilete;
import busak.objektuak.Erabiltzaile;
import busak.objektuak.Geltoki;
import busak.objektuak.Linea;

/**
 * Klase honek menu gisa funtzoinatzen du
 * 
 * @author Julen Unai Oier
 * @version 0.2
 * 
 */

public class Main {

    /** Testak egiteko lagungarria da */
    private static Erabiltzaile unekoErabiltzaile = null;// Erabiltzaile("78955162A", "Txanrru", "kowabunga");

    public static void main(String[] args) {
        menu();
    }

    /** Menua eta aukerak kudeatzen dituen metodoa da */
    private static void menu() {
        int aukera = -1;
        boolean atera = false;
        do {

            if (unekoErabiltzaile == null) {
                System.out.println("Sartu behean dagoen aukeretako zenbaki bat");
                System.out.println();
                System.out.println("╔═══╦══════════════════╗");
                System.out.println("║ 1 ║ Logeatu          ║");
                System.out.println("╠═══╬══════════════════╣");
                System.out.println("║ 2 ║ Erregistratu     ║");
                System.out.println("╠═══╬══════════════════╣");
                System.out.println("║ 3 ║ Atera            ║");
                System.out.println("╚═══╩══════════════════╝");

                aukera = Utilities.eskatuInt(3);

                switch (aukera) {
                    case 1:
                        logeatu();
                        break;
                    case 2:
                        erregistratu();
                        break;
                    default:
                        System.out.println("Ateratzen ... .. .");
                        atera = true;
                        break;
                }

            } else {
                System.out.println("Kaixo " + unekoErabiltzaile.getIzenAbizenak());
                System.out.println("Sartu behean dagoen aukeretako zenbaki bat");
                System.out.println();
                System.out.println("╔═══╦══════════════════╗");
                System.out.println("║ 1 ║ Bileteak ikusi   ║");
                System.out.println("╠═══╬══════════════════╣");
                System.out.println("║ 2 ║ Bilete bat erosi ║");
                System.out.println("╠═══╬══════════════════╣");
                System.out.println("║ 3 ║ Irten            ║");
                System.out.println("╚═══╩══════════════════╝");

                aukera = Utilities.eskatuInt(3);

                switch (aukera) {
                    case 1:
                        biletakIkusi();
                        break;
                    case 2:
                        bileteaErosi();
                        break;
                    default:
                        unekoErabiltzaile = null;
                        System.out.println("Ateratzen ... .. .");
                        break;
                }
            }

        } while (!atera);

    }

    /** Bileteak ikusteko metodoa */
    private static void biletakIkusi() {
        System.out.println("Hona hemen erosi dituzun bileteak:");
        DAOBilete daoBil = new DAOBilete();
        unekoErabiltzaile.setBileteak(daoBil.getAll(unekoErabiltzaile.getNanAiz()));
        unekoErabiltzaile.ikusiBileteak();
        System.out.println("\n");
    }

    /** Biletea erostearen aukera kudeatzen duen metodoa da */
    private static void bileteaErosi() {
        System.out.println("\nAukeratu hurrengo linietako bat:");
        DAOLinea daoL = new DAOLinea();
        ArrayList<Linea> lineak = daoL.getAll();
        int aukera = -1;
        int geltoHas = -1;
        int geltoBuk = -1;

        for (Linea linea : lineak) {
            System.out.println(linea);
        }
        Linea l = null;
        do {
            aukera = Utilities.eskatuInt(Integer.MAX_VALUE);
            for (Linea lin : lineak) {
                if (lin.kodeaDa(aukera)) {
                    l = lin;
                    break;
                }

            }
        } while (l == null);
        System.out.println("\nAukeratu hurrengo geltokietako bat:");
        l.printGeltoki();
        aukera = 0;
        ArrayList<Geltoki> geltokiak = l.getGeltokiak();
        int lTamaina = geltokiak.size();
        do {
            System.out.println("Sartu bidaiaren hasierako geltokiaren ordena:");
            geltoHas = Utilities.eskatuInt(lTamaina) - 1;
            System.out.println("Sartu bidaiaren bukaerako geltokiaren ordena:");
            geltoBuk = Utilities.eskatuInt(lTamaina) - 1;
            System.out.println("Akuera prozesatzen ... .. .");
            if (geltoBuk == geltoHas) {
                System.out.println("Ez duzu bileterik behar lekuan bertan geratzeko");
            }
        } while (geltoBuk == geltoHas);

        bileteaEratu(geltoHas, geltoBuk, l);
    }

    /**
     * Ordainketarako dirua eskatu eta nahikoa gehiegi edo gutxiegi den arabera
     * beharrezko kanbioa eskaera egiten du
     * 
     * @param ordaintzekoa ordaindu behareko kantitatea
     * @return boolean ordainketa gauzatu bada true beztela false
     */
    private static boolean ordaindu(double ordaintzekoa) {
        System.out.println("Sartu dirua (erabili puntoa hamartarrak banatzeko):");
        Double sartutakoDirua = Utilities.eskatuDouble();

        double kanbio = sartutakoDirua - ordaintzekoa;

        if (kanbio > 0) {
            System.out.println("Ordainketa zuzena da");
            kanbioaEman(kanbio);
            return true;

        } else if (kanbio == 0) {
            System.out.println("Ordainketa zuzena da");
            return true;
        } else {
            System.out.println("Dirua ez da nahikoa!\nSartu diru gehiago!");
            kanbio = Math.abs(kanbio);
            System.out.printf("Oraindik falta zaizu: %.2f", kanbio);
            System.out.println("\n");
            System.out.println("Diru gehiago sartu nahi duzu(b/e)");
            if (Utilities.eskatuBaiEz()) {
                return ordaindu(kanbio);
            } else {
                if (sartutakoDirua != 0) {
                    System.out.println(sartutakoDirua + "€ bueltatuko zaizkizu.");
                    return false;
                }
            }
        }
        return ordaindu(ordaintzekoa);
    }

    /**
     * Ahalik eta txanpon/bilete gutxien bueltatzen duen metodoa
     * 
     * @param zenb bueltatu beharreko diru kantitatea
     */
    private static void kanbioaEman(double zenb) {
        double[] balioak = { 200, 100, 50, 20, 10, 5, 2, 1, 0.5, 0.2, 0.1, 0.049, 0.019, 0.009 };
        String[] dirua = { "200 euro", "100 euro", "50 euro", "20 euro", "10 euro", "5 euro", "2 euro", "euro 1",
                "50 zentimo", "20 zentimo", "10 zentimo", "5 zentimo", "2 zentimo", "zentimo 1" };
        int[] zenbat = new int[14];

        int txanponkop = 0;
        int billetekop = 0;
        System.out.println("Sobratu den diru kantitea:");
        for (int i = 0; i < balioak.length; i++) {
            while (zenb >= balioak[i]) {
                if (i <= 5) {
                    billetekop++;
                } else {
                    txanponkop++;
                }
                zenb -= balioak[i];
                zenbat[i]++;
            }
        }
        System.out.println(billetekop + " billete guztira");
        for (int i = 0; i < 6; i++) {
            if (zenbat[i] != 0) {
                System.out.println("    " + dirua[i] + "ko " + zenbat[i] + " billete");
            }
        }
        System.out.println("");
        System.out.println(txanponkop + " txanpon guztira");
        for (int i = 6; i < 14; i++) {
            if (zenbat[i] != 0) {
                System.out.println("    " + dirua[i] + "ko " + zenbat[i] + " txanpon");
            }
        }
        System.out.println("");
    }

    /**
     * Behin erabiltzaileak eskatuta nondik nora joan nahi den, noiz eta zein
     * ordutan galdetzen duen metodoa
     * 
     * @param geltoHas bidaiaren hasierako geltokia
     * @param geltoBuk bidaiaren amaierako geltokia
     * @param l        bidaia egingo den linea
     */
    private static void bileteaEratu(int geltoHas, int geltoBuk, Linea l) {
        Bilete bil = new Bilete();
        LocalDateTime egunOrd;
        ArrayList<LocalDateTime> ordPosi;
        boolean ok = false;
        do {
            egunOrd = Utilities.eskatuOrdua();
            ordPosi = l.getOrduEgoki(egunOrd, geltoHas, geltoBuk > geltoHas);
            if (LocalDateTime.now().isBefore(egunOrd)) {
                if (ordPosi.size() > 0) {
                    ok = true;
                } else {
                    System.out.println("Ordu horretarako ez dago bidairik");
                }
            } else {
                System.out.println("Ezin da iraganeko egun bat aukeratu");
            }
        } while (!ok);
        int max = ordPosi.size();
        for (int i = 0; i < max; i++) {
            System.out.println(" -" + (i + 1) + "- " + ordPosi.get(i));
        }
        System.out.println("Sartu nahi duzun ordua (1-" + max + "):");
        int aukera = Utilities.eskatuInt(max) - 1;
        egunOrd = ordPosi.get(aukera);

        bil.setNan(unekoErabiltzaile.getNanAiz());
        bil.setHasGeltoki(l.getGeltoki(geltoHas));
        bil.setBukGeltoki(l.getGeltoki(geltoBuk));
        bil.setHasData(egunOrd);
        bil.setBukData(egunOrd.plusMinutes(l.bidaiDenbora(geltoHas, geltoBuk)));
        bil.setOrdaintzekoa(l.getPvpu() * Math.abs(geltoHas - geltoBuk));
        DAOBilete daoB = new DAOBilete();
        System.out.println("Hurrengo biletea erosi nahi duzu (b/e)");
        System.out.println(bil.toStringIzenak());

        if (Utilities.eskatuBaiEz()) {
            float ord = bil.getOrdaintzekoa();
            System.out.println(ord + " € ordaindu behar dituzu");
            if (ordaindu(ord)) {
                bil.setKode(daoB.insert(bil));
                System.out.println("Hona hemen biletea:\n");
                System.out.println(daoB.getByKode(bil.getKode()));
                System.out.println("\n");
            }
        } else {
            System.out.println("Erosketa bertan bera utziko da");
        }
    }

    /** Erabltzaile berrien erregistroa kudeazten duen metodoa */
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

    /** Bileteak erosteko erabiltzaileen login-a kudeatzen duen metodoa */
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
                System.out.println(nanAiz + " NAN/AIZ duen erabiltzailea ez da existitzen");
                System.out.println("Erregistratu nahi zara?");
                if (Utilities.eskatuBaiEz()) {
                    atera = true;
                    erregistratu();
                } else {
                    System.out.println("Login-etik atera nahi zara");
                    atera = Utilities.eskatuBaiEz();
                }
            }
        } while (!ok && !atera);

    }
}
