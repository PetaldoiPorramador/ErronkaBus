package busak.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import busak.objektuak.Bilete;

public class DAOBilete {

    Connection conn;

    /**
     * Constructor vacio
     */
    public DAOBilete() {
        super();
        conn = ConnectionManager.getConnection();
    }

    public void insert(Bilete bilete) {
        try {
            String sql = "INSERT INTO Billete (CodBil, FechaInicio, DNI, CodLin, OrdenEmp, OrdenTer) VALUES (?,?,?,?,?,?)";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, bilete.getKode());
            pst.setTime(2, Time.valueOf(bilete.getHasData()));
            pst.setString(3, bilete.getNan());
            pst.setInt(4, bilete.getHasGeltoki().getLineaKode());
            pst.setInt(5, bilete.getHasGeltoki().getOrden());
            pst.setInt(6, bilete.getBukGeltoki().getOrden());

            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int kode) {
        try {
            String sql = "DELETE FROM Billete WHERE CodBil=? AND DNI=?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, kode);
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Bilete getByKode(int kode) {
        Bilete bilete = new Bilete();
        try {
            String sql = "SELECT * FROM Billete WHERE CodBil=?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, kode);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                bilete.setKode(rs.getInt(1));
                bilete.setHasData(rs.getTime(2).toLocalTime());
                bilete.setNan(rs.getString(3));
                int kodeLin = rs.getInt("CodLin");
                int ordenEmp = rs.getInt("OrdenEmp");
                int ordenTer = rs.getInt("OrdenTer");

                DAOGeltoki daoGeltoki = new DAOGeltoki();
                bilete.setHasGeltoki(daoGeltoki.getByKode(kodeLin, ordenEmp));
                bilete.setBukGeltoki(daoGeltoki.getByKode(kodeLin, ordenTer));

                DAOLinea daoLinea = new DAOLinea();

                bilete.setBukData(rs.getTime(2).toLocalTime().plusMinutes(daoLinea.getByKode(kodeLin).bidaiDenbora(ordenEmp, ordenTer)));

                sql = "SELECT round(PVPU*abs(?-?)) FROM Linea WHERE CodLin=?";
                pst = conn.prepareStatement(sql);
                pst.setInt(1, ordenEmp);
                pst.setInt(2, ordenTer);
                pst.setInt(3, kodeLin);
                rs = pst.executeQuery();
                bilete.setOrdaintzekoa(rs.getFloat(1));

                
                
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bilete;
    }

    public void update(Bilete bilete) {
        if (this.getByKode(bilete.getKode()) == null) {
            System.out.println("Biletea ez da existitzen");
        } else {
            try {
                String sql = "UPDATE Bilete SET CodBil=?, FechaInicio=?, DNI=?, CodLin=?, OrdenEmp=?, OrdenTer=?, WHERE CodBil=?";
                PreparedStatement pst = conn.prepareStatement(sql);
                pst.setInt(1, bilete.getKode());
                pst.setTime(2, Time.valueOf(bilete.getHasData()));
                pst.setString(3, bilete.getNan());
                pst.setInt(4, bilete.getBukGeltoki().getLineaKode());
                pst.setInt(5, bilete.getHasGeltoki().getOrden());
                pst.setInt(6, bilete.getBukGeltoki().getOrden());
                pst.setInt(7, bilete.getKode());
                pst.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }

}
