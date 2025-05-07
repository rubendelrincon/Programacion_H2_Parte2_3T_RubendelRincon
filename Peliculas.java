package cine;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Peliculas {
    public static void mostrarCatalogoCompleto() {
        String sql = "SELECT p.id_pelicula AS id, p.titulo, p.duracion, "
                   + "g.nombre AS genero, p.anio_estreno AS anio, p.reseña AS puntuacion "
                   + "FROM pelicula p INNER JOIN genero g ON p.id_genero = g.id_genero";

        try (Connection conn = conexion.obtenerConexion();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            
            System.out.println(" CATÁLOGO COMPLETO ");
            System.out.printf("%-8s %-25s %-10s %-15s %-6s %s%n", 
                "ID", "TÍTULO", "DURACIÓN", "GÉNERO", "AÑO", "PUNTUACIÓN");
            
            while (rs.next()) {
                System.out.printf("%-8s %-25s %-10d %-15s %-6d %d/10%n",
                    rs.getString("id"),
                    rs.getString("titulo"),
                    rs.getInt("duracion"),
                    rs.getString("genero"),
                    rs.getInt("anio"),
                    rs.getInt("puntuacion"));
            }
            
        } catch (SQLException e) {
            System.out.println("Error al cargar películas: " + e.getMessage());
        }
    }
}
