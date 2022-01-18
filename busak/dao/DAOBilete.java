package busak.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

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

    public int insert(Bilete bilete) {

        try {
            String sql = "INSERT INTO Billete (FechaInicio, DNI, CodLin, OrdenEmp, OrdenTer) VALUES (?,?,?,?,?,?)";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setTimestamp(1, Timestamp.valueOf(bilete.getHasData()));
            pst.setString(2, bilete.getNan());
            pst.setInt(3, bilete.getHasGeltoki().getLineaKode());
            pst.setInt(4, bilete.getHasGeltoki().getOrden());
            pst.setInt(5, bilete.getBukGeltoki().getOrden());
            pst.executeUpdate();
            try (ResultSet generatedKeys = pst.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    bilete.setKode(generatedKeys.getInt(1));
                } else {
                    System.out.println("No se ha podido generar el codigo");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bilete.getKode();
    }

    public void delete(int kode) {
        try {
            String sql = "DELETE FROM Billete WHERE CodBil=?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, kode);
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Bilete getByKode(int kode) {

        Bilete bilete = null;
        try {
            String sql = "SELECT * FROM Billete WHERE CodBil=?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, kode);
            ResultSet rs = pst.executeQuery();

            bilete = new Bilete();
            bilete.setKode(rs.getInt(1));
            bilete.setHasData(rs.getTimestamp(2).toLocalDateTime());
            bilete.setNan(rs.getString(3));
            int kodeLin = rs.getInt("CodLin");
            int ordenEmp = rs.getInt("OrdenEmp");
            int ordenTer = rs.getInt("OrdenTer");

            DAOGeltoki daoGeltoki = new DAOGeltoki();
            bilete.setHasGeltoki(daoGeltoki.getByKode(kodeLin, ordenEmp));
            bilete.setBukGeltoki(daoGeltoki.getByKode(kodeLin, ordenTer));

            DAOLinea daoLinea = new DAOLinea();

            bilete.setBukData(bilete.getHasData()
                    .plusMinutes(daoLinea.getByKode(kodeLin).bidaiDenbora(ordenEmp, ordenTer)));

            sql = "SELECT round(PVPU*abs(OrdenTer-OrdenEmp)) FROM Linea WHERE CodLin=? AND OrdenEmp=? AND OrdenTer=?";
            pst = conn.prepareStatement(sql);
            pst.setInt(1, kodeLin);
            pst.setInt(2, ordenEmp);
            pst.setInt(3, ordenTer);
            rs = pst.executeQuery();
            bilete.setOrdaintzekoa(rs.getFloat(1));

        } catch (SQLException e) {
            bilete = null;
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
                pst.setTimestamp(2, Timestamp.valueOf(bilete.getHasData()));
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
