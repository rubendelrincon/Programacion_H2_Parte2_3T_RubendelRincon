package cine;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class AñadirPeli {
    private static final Scanner scanner = new Scanner(System.in);

    public static void agregarPelicula() {
    	
        System.out.println(" AÑADIR PELICULA ");

        System.out.print("ID de la película: ");
        
        
        String idPelicula = scanner.nextLine();

        // Verificar si la película ya existe
        
        
        
        String sqlCheck = "SELECT COUNT(*) FROM pelicula WHERE id_pelicula = ?";
        try (Connection conn = conexion.obtenerConexion();
             PreparedStatement sentenciaVerificarExistencia = conn.prepareStatement(sqlCheck)) {

            sentenciaVerificarExistencia.setString(1, idPelicula);
            ResultSet rs = sentenciaVerificarExistencia.executeQuery();
            rs.next();
            if (rs.getInt(1) > 0) {
            	
           System.out.println("Ya hay una pecuula con ese Id.");
           return;
            }

            // Pedir los demás datos
            
            
            System.out.print("Título: ");
            String titulo = scanner.nextLine();

            System.out.print("Duración (en minutos): ");
            int duracion = Integer.parseInt(scanner.nextLine());

            System.out.print("Id del género 1 0 2 ");
            int idGenero = Integer.parseInt(scanner.nextLine());

            System.out.print("Año de estreno: ");
            int anioEstreno = Integer.parseInt(scanner.nextLine());

            System.out.print("Puntuación (reseña) [1-10]: ");
            int puntuacion = Integer.parseInt(scanner.nextLine());

            
            
            
            // Insertar la nueva película
            
            
            
            String sqlInsert = "INSERT INTO pelicula (id_pelicula, titulo, duracion, id_genero, anio_estreno, reseña) " +
                               "VALUES (?, ?, ?, ?, ?, ?)";

            try (PreparedStatement sentenciaInsertarPelicula = conn.prepareStatement(sqlInsert)) {

                sentenciaInsertarPelicula.setString(1, idPelicula);
                sentenciaInsertarPelicula.setString(2, titulo);
                sentenciaInsertarPelicula.setInt(3, duracion);
                sentenciaInsertarPelicula.setInt(4, idGenero);
                sentenciaInsertarPelicula.setInt(5, anioEstreno);
                sentenciaInsertarPelicula.setInt(6, puntuacion);

                sentenciaInsertarPelicula.executeUpdate();
                
                System.out.println(" Película añadida .");
            }

        } catch (SQLException e) {
        	
            System.out.println(" Error " + e.getMessage());
            
            
        } catch (NumberFormatException e) {
        	
            System.out.println("Error debes ingresar valores numérico.");
       
        
        }}}
