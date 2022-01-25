package busak;

import busak.dao.DAOGeltoki;

public class Probie {
    

    public static void main(String[] args) {
        DAOGeltoki d = new DAOGeltoki();
        System.out.println(d.getByKode(1, 1));
    }

}
