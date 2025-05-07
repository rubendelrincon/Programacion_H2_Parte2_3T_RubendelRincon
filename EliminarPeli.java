package cine;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class EliminarPeli {
    private static final Scanner scanner = new Scanner(System.in);

    public static void eliminarPelicula() {
    	
    	
        // Se pide al usuario el id de la película que quiere eliminar
        System.out.print("Introduce el ID de la película que quieres eliminar: ");
        String idPelicula = scanner.nextLine();

        // Consulta para comprobar si existe una película con ese id
        String sqlComprobar = "SELECT id_pelicula FROM pelicula WHERE id_pelicula = ?";
        try (Connection conn = conexion.obtenerConexion();
        		
        		
             // Preparamos la consulta 
             PreparedStatement Comprobacion = conn.prepareStatement(sqlComprobar)) {

            // comprobamos el valor del idpelicula
            Comprobacion.setString(1, idPelicula);

            // Ejecutamos la consulta para comprobar si la película existe
            try (ResultSet rs = Comprobacion.executeQuery()) {
            	
                // Si no devuelve resultados, la película no existe
            	
                if (!rs.next()) {
                    System.out.println("La película no existe. No se puede eliminar.");
                    return; 
                }
            }

            // Consulta para eliminar la película
            String sqlDelete = "DELETE FROM pelicula WHERE id_pelicula = ?";
            try (PreparedStatement Eliminar = conn.prepareStatement(sqlDelete)) {
            	
            	
            	
                // ponemos sel ID de la película 
                Eliminar.setString(1, idPelicula);

                // Ejecutamos la sentencia de eliminación
                int filasEliminadas = Eliminar.executeUpdate();

                // Verificamos la eliminacion
                if (filasEliminadas > 0) {
                    System.out.println("Película eliminada.");
                } else {
                    System.out.println("No se pudo eliminar la película.");
                }
            }

        } catch (SQLException e) {
        	
        	
            // muestra cualquier error relacionado con la base de datos
            System.out.println("Error al eliminar la película " + e.getMessage());
        }}}
