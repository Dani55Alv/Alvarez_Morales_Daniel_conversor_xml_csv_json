import java.io.File;
import java.io.IO;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {
/*
 * List<String> tester = new ArrayList<>();
 * List<String> testerAux = new ArrayList<>();
 * 
 * String a =
 * "C:\\Users\\daniy\\OneDrive\\Escritorio\\visualStudioClases\\gitHub\\Repositorio_Trabajo_Conversor_Ficheros\\Conversor_Ficheros\\Directorio_2\\archivo.xml";
 * Path ew = Paths.get(a);
 * try {
 * tester = Files.readAllLines(ew);
 * 
 * } catch (Exception e) {
 * System.out.println("Error");
 * e.printStackTrace();
 * }
 * for (String linea : tester) {
 * // Eliminamos las etiquetas y conservamos solo los valores
 * String[] partes = linea.split("(?=<)|(?<=>)");
 * 
 * for (String string : partes) {
 * if (string.startsWith("<") && string.endsWith(">")) {
 * // Ignoramos las etiquetas
 * continue;
 * }
 * 
 * // Imprimir solo los valores dentro de las etiquetas
 * String value = string.trim();
 * 
 * if (!value.isEmpty()) {
 * System.out.println(value); // Mostrar en consola
 * testerAux.add(value); // Guardar en lista auxiliar
 * }
 * }
 * 
 * }
 * System.out.println();
 * System.out.println("Mira");
 * 
 * for (String line : testerAux) {
 * System.out.println(line);
 * }
 * 
 * System.out.println();
 */
        
 // TODO EL PROGRAMA
        Scanner sc = new Scanner(System.in);
        boolean salir = false;
        String rutaDirectorio = "";
        String archivo = "";
        File directorioSeleccionado = null;

        String archivoSeleccionado = null;
        Gestion_Conversor gestion = new Gestion_Conversor();
        do {

            System.out.println("Menú:");
            System.out.println("1-. Seleccionar carpeta");
            System.out.println("2-. Lectura de fichero");
            System.out.println("3-. Conversión a (csv, json, xml)");
            System.out.println("4-. Salir");

            // a. Ruta dela carpeta seleccionada.
            boolean esNull;

            esNull = gestion.verRutaDirectorio(directorioSeleccionado);
            // b. Contenido de la carpeta seleccionada.
            gestion.verContenidoDirectorio(directorioSeleccionado);
            // c. Fichero seleccionado.
            gestion.verFichero(archivoSeleccionado);

            System.out.print("Seleccione una opción: ");

            int opcion = sc.nextInt();
            sc.nextLine();

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
                    // Conversión a formato

                    if (archivoSeleccionado != null) {
                        System.out.println(
                                "Introduce que formato de conversión quieres utilizar para el archivo seleccionado ");

                        System.out.println("(Archivo seleccionado: " + archivoSeleccionado + ")");

                        System.out.println("1.-xml  2.-csv  3.-json");
                        opcion = sc.nextInt();
                        sc.nextLine();
                        System.out.println("Introduce el nombre de archivo de salida");
                        String nombreArchivoSalida = sc.nextLine();
                        gestion.conversion(opcion, archivoSeleccionado, directorioSeleccionado,
                                gestion.getArchivoTextoGestion(), nombreArchivoSalida);


                                

                        // gestion.conversion
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