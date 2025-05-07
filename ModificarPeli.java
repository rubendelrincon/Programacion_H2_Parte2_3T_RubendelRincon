package cine;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class ModificarPeli {
    private static final Scanner scanner = new Scanner(System.in);

    public static void modificarPelicula() {
    	
    	
        // Se pide al usuario el id de la pelicula par modificarla
        System.out.print("Introduce el ID de la película a modificar: ");
        String idPelicula = scanner.nextLine();

        // Consulta para comprobar si la película existe en la base de datos
        
        String sqlComprobar = "SELECT * FROM pelicula WHERE id_pelicula = ?";
        try (Connection conn = conexion.obtenerConexion();
        		
        		
             // Preparamos la consulta de comprobación
        		
             PreparedStatement comprobacion = conn.prepareStatement(sqlComprobar)) {

            // Se establece el valor del id de la pelicula
        	
            comprobacion.setString(1, idPelicula);

            
            // Ejecutamos la consulta para verificar si la película existe
            
            try (ResultSet rs = comprobacion.executeQuery()) {
                if (!rs.next()) { 
                    System.out.println("La película no existe.");
                    return; 
                }}

            // le pedimos al usuario los nuevos valores para actualizar
            
            System.out.print("Nuevo título: ");
            String nuevoTitulo = scanner.nextLine();

            System.out.print("Nueva duración: ");
            int nuevaDuracion = scanner.nextInt();
            scanner.nextLine(); 

            // Consulta para actualizar la película en la base de datos
            
            String sqlUpdate = "UPDATE pelicula SET titulo = ?, duracion = ? WHERE id_pelicula = ?";

            // Preparamos y ejecutamos la actualización
            
            try (PreparedStatement actualizar = conn.prepareStatement(sqlUpdate)) {
            	
            	
                // Establecemos los nuevos valores en la consulta
            	
                actualizar.setString(1, nuevoTitulo);
                actualizar.setInt(2, nuevaDuracion);
                actualizar.setString(3, idPelicula);

                
                // Ejecutamos la actualización y comprobamos si se hizo correctamente
                
                int filasActualizadas = actualizar.executeUpdate();
                if (filasActualizadas > 0) {
                    System.out.println("Película modificada correctamente.");
                } else {
                    System.out.println("No se pudo modificar la película.");
                }}
            

            
            
        } catch (SQLException e) {
        	
        	
            //  muestra cualquier error que ocurra durante la operación
            System.out.println("Error al modificar película: " + e.getMessage());
}}}






