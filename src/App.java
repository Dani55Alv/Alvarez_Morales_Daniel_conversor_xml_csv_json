
/**
 * Es la clase principal donde se desarrolla el programa Gestion_conversor.
 * 
 * @author Daniel Alvarez Morales
 * @version 1.0
 * @since 2025
 * 
 */

import java.io.File;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        boolean salir = false;
        File directorioSeleccionado = null;

        String archivoSeleccionado = null;
        Gestion_Conversor gestion = new Gestion_Conversor();
        do {

            System.out.println("Menú:");
            System.out.println("1-. Seleccionar carpeta");
            System.out.println("2-. Lectura de fichero");
            System.out.println("3-. Conversión a (csv, json, xml)");
            System.out.println("4-. Salir");

            // Ruta dela carpeta seleccionada.
            boolean esNull;

            esNull = gestion.verRutaDirectorio(directorioSeleccionado);
            // Contenido de la carpeta seleccionada.

            try {
                gestion.verContenidoDirectorio(directorioSeleccionado);

            } catch (IOException e) {

                e.printStackTrace();
                System.out.println("Error en el listado de archivos");
            }

            // Fichero seleccionado.
            gestion.verFichero(archivoSeleccionado);

            // Inicializamos el valor de opcion con cualquier valor (Es redundante pero se
            // deber hacer para inicializarlo).
            int opcion = 9;

            boolean volverIntroducir = false;
            do {
                try {
                    System.out.print("Seleccione una opción: ");

                    opcion = sc.nextInt();
                    sc.nextLine();

                    volverIntroducir = true;

                } catch (InputMismatchException e) {
                    System.out.println("Error, entrada incorrecta, asegurate de introducir un numero del menú.");
                    sc.nextLine();
                    e.printStackTrace();
                }
            } while (!volverIntroducir);

            switch (opcion) {
                case 1:
                    // Seleccionar carpeta
                    System.out.print("Introduce la ruta del directorio: ");
                    String rutaCarpeta = sc.nextLine();

                    directorioSeleccionado = gestion.seleccionarDirectorio(rutaCarpeta);

                    break;

                case 2:

                    // Lectura de fichero
                    if (!esNull) {

                        System.out.print("Introduce el nombre del fichero de dentro de la carpeta actual: ");
                        String nombreFichero = sc.nextLine();

                        try {
                            List<String> lectura = gestion.leerFichero(nombreFichero, directorioSeleccionado);
                            archivoSeleccionado = nombreFichero;

                        } catch (IOException e) {
                            System.out.println("Error");
                            e.printStackTrace();
                            archivoSeleccionado = null;
                        }
                    } else {
                        System.out.println(
                                "Selecciona primero un directorio para proceder a realizar la lectura de un fichero de dicha carpeta");
                    }
                    break;

                case 3:
                    if (archivoSeleccionado != null) {
                        System.out.println(
                                "Introduce que formato de conversión quieres utilizar para el archivo seleccionado ");

                        System.out.println("(Archivo seleccionado: " + archivoSeleccionado + ")");

                        String extension = gestion.detectorExtension(archivoSeleccionado);

                        if (extension.equals("xml")) {
                            System.out.println("1.-csv  2.-json");

                        }
                        if (extension.equals("csv")) {
                            System.out.println("1.-json  2.-xml");

                        }
                        if (extension.equals("json")) {
                            System.out.println("1.-csv  2.-xml");

                        }

                        volverIntroducir = false;
                        do {

                            try {
                                opcion = sc.nextInt();
                                sc.nextLine();
                                volverIntroducir = true;
                            } catch (InputMismatchException e) {
                                System.out
                                        .println(
                                                "Error, entrada incorrecta, asegurate de introducir un numero de la consola.");
                                e.printStackTrace();
                                sc.nextLine();

                            }
                        } while (!volverIntroducir);

                        System.out.println("Introduce el nombre de archivo de salida (Sin formato)");

                        String nombreArchivoSalida = sc.nextLine();
                        try {

                            gestion.conversion(opcion, archivoSeleccionado, directorioSeleccionado,
                                    gestion.getArchivoTextoGestion(), nombreArchivoSalida);

                        } catch (IOException e) {
                            System.out.println("Error a la hora de convertir fichero o al escribir fichero");
                            e.printStackTrace();
                        }

                    } else {
                        System.out.println("Tiene que seleccionar primero un archivo");

                    }
                    break;

                case 4:

                    System.out.println("Saliendo del programa...");
                    salir = true;
                    break;

                default:
                    System.out.println("Opción no válida.");
                    break;
            }

        } while (!salir);

    }
}