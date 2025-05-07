package cine;

import java.util.Scanner;

public class GestionCine {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
      
    	int opcion;
        do {
            mostrarMenu();
            opcion = obtenerOpcionValida();
            procesarOpcion(opcion);
        } while(opcion != 5);
    }

    
    // El menu 
    private static void mostrarMenu() {
        System.out.println("\nMENÚ CINE");
        System.out.println("1. Ver películas");
        System.out.println("2. Añadir película");
        System.out.println("3. Eliminar película");
        System.out.println("4. Modificar película");
        System.out.println("5. Salir");
        System.out.print("Selecciona una opción: ");
    }

    private static int obtenerOpcionValida() {
        while (!scanner.hasNextInt()) {
        	
        	
            System.out.println(" ingresa un numero del 1 al 5");
            scanner.next();
        }
        
        
        int opcion = scanner.nextInt();
        scanner.nextLine(); 
        return opcion;
    }

    
    // las opciones 
    private static void procesarOpcion(int opcion) {
        switch (opcion) {
            case 1:
                Peliculas.mostrarCatalogoCompleto();
                break;
            case 2:
                AñadirPeli.agregarPelicula();
                break;
            case 3:
                EliminarPeli.eliminarPelicula();
                break;
            case 4:
                ModificarPeli.modificarPelicula();
                break;
            case 5:
                System.out.println("Saliendo...");
                break;
            default:
                System.out.println("selecciona otra opcion");
        }
    }
}