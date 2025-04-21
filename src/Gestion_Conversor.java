
/**
 * "Gestion_Conversor" es la clase donde se desarrollan las actividades del objeto conversor 
 * es decir, leer, escribir, transformar y demás.
 * 
 * @author Daniel Alvarez
 * @version 1.0
 * @since 2025
 * 
 */

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class Gestion_Conversor {
    /**
     * Lista que almacena el contenido del archivo de texto gestionado.
     */
    private List<String> archivoTextoGestion;

    /**
     * Constructor de la clase "Gestion_Conversor".
     * Inicializa la lista "archivoTextoGestion" vacía.
     */
    public Gestion_Conversor() {

        archivoTextoGestion = new ArrayList<>();
    }

    /**
     * Muestra por consola el nombre del archivo seleccionado.
     * Si el archivo es nulo, informa que no se ha seleccionado ningún archivo.
     * 
     * @param archivo El nombre del archivo seleccionado. Puede ser nulo si no se ha
     *                seleccionado ningún archivo.
     * 
     */

    public void verFichero(String archivo) {
        if (archivo != null) {
            System.out.println("Fichero seleccionado: " + archivo);
        } else {
            System.out.println("No se ha seleccionado ningún fichero. (Lee un archivo para seleccionar)");
        }
    }

    /**
     * Muestra por consola la ruta absoluta del directorio seleccionado.
     * Si el directorio es nulo, informa que se debe seleccionar un directorio.
     * 
     * @param directorio El directorio cuya ruta se va a mostrar. Puede ser nulo si
     *                   no se ha seleccionado un directorio.
     * @return true si no se seleccionó un directorio, false si se seleccionó un
     *         directorio válido.
     */

    public boolean verRutaDirectorio(File directorio) {
        boolean esNull = true;
        if (directorio != null) {
            // Path ruta = directorio.toPath();

            System.out.println("Carpeta seleccionada: " + directorio.getAbsolutePath());
            return esNull = false;

        } else {
            System.out.println("Selecciona primero un directorio para ver su ruta.");
            return esNull = true;
        }

    }

    /**
     * Muestra por consola el contenido del directorio seleccionado.
     * Si el directorio es nulo, informa que se debe seleccionar un directorio.
     * 
     * @param directorio El directorio cuyo contenido se va a mostrar. Puede ser
     *                   nulo si no se ha seleccionado un directorio.
     * @throws IOException Si ocurre un error al leer el contenido del directorio.
     */

    public void verContenidoDirectorio(File directorio) throws IOException {
        boolean esNull = true;
        if (directorio != null) {
            esNull = false;
            Path ruta = directorio.toPath();

            DirectoryStream<Path> flujoDatos = Files.newDirectoryStream(ruta);
            System.out.println("Conntenido del directorio seleccionado: " + directorio.getAbsolutePath());

            for (Path archivo : flujoDatos) {
                System.out.println(archivo);
            }

        }

        if (esNull) {
            System.out.println("Selecciona primero un directorio para ver su contenido.");
        }

    }

    /**
     * Intenta seleccionar un directorio a partir de la ruta proporcionada.
     * Verifica si la ruta existe y es un directorio antes de devolverlo.
     * 
     * @param rutaDirectorio La ruta del directorio a seleccionar.
     * @return El objeto `File` correspondiente al directorio seleccionado, o `null`
     *         si la ruta no existe o no es un directorio.
     */
    public File seleccionarDirectorio(String rutaDirectorio) {

        Path ruta = Paths.get(rutaDirectorio);

        if (Files.exists(ruta) && Files.isDirectory(ruta)) {
            File directorio = ruta.toFile();

            System.out.println("Carpeta seleccionada: " + directorio.getAbsolutePath());
            return directorio;

        } else {
            System.out.println("La ruta no existe o no es una carpeta.");
            return null;

        }

    }

    /**
     * Lee el contenido de un archivo dentro del directorio seleccionado y lo
     * devuelve como una lista de cadenas.
     * Además, muestra información sobre la extensión del archivo y su contenido
     * línea por línea.
     * 
     * @param nombreFichero
     *                               El nombre del archivo a leer, que debe estar
     *                               dentro del directorio seleccionado.
     * 
     * @param directorioSeleccionado El directorio donde se encuentra el archivo.
     * 
     * @return Una lista de cadenas con el contenido del archivo,
     *         o una lista vacía si no se seleccionó un directorio o el archivo está
     *         vacío.
     * 
     * @throws IOException Si el archivo no existe, no es un archivo regular, o
     *                     ocurre un error al leerlo.
     */

    public List<String> leerFichero(String nombreFichero, File directorioSeleccionado) throws IOException {
        archivoTextoGestion.clear();
        boolean exito = false;

        Path rutaDirectorio = directorioSeleccionado.toPath();

        Path rutaYFichero = rutaDirectorio.resolve(nombreFichero);

        List<String> archivoTexto = new ArrayList<>();
        List<String> textoReturn = new ArrayList<>();

        if (directorioSeleccionado != null) {

            if (Files.exists(rutaYFichero) && Files.isRegularFile(rutaYFichero)) {

                boolean archivoVacio = true;
                System.out.println("Fichero encontrado");
                File archivo = rutaYFichero.toFile();

                System.out.println(archivo.getAbsolutePath());

                String[] partes = nombreFichero.split("\\.");

                String extension = partes[partes.length - 1].toLowerCase();

                switch (extension) {
                    case "csv":
                        System.out.println("Trabajando con extension 'csv' ");
                        break;

                    case "xml":
                        System.out.println("Trabajando con extension 'xml' ");

                        break;

                    case "json":
                        System.out.println("Trabajando con extension 'json' ");

                        break;
                    case "txt":
                        System.out.println("Trabajando con extension 'txt' ");

                        break;
                    default:
                        System.out.println("Extension de archivo invalido");
                        break;
                }

                archivoTexto = Files.readAllLines(rutaYFichero);
                System.out.println("Reproduciendo por linea el texto");
                for (String linea : archivoTexto) {
                    System.out.println(linea);
                    textoReturn.add(linea);
                    archivoTextoGestion.add(linea);
                    archivoVacio = false;
                }

                if (archivoVacio) {
                    System.out.println();
                    System.out.println("(No hay nada que mostrar, el fichero esta vacio)");
                    System.out.println();
                }
                System.out.println("Fin");

                exito = true;
            } else {
                throw new IOException("El archivo o no existe o no es un archivo (es un directorio)");

            }

        }

        if (exito) {
            return textoReturn;
        } else {
            System.out.println("Primero debes seleccionar un directorio.");
            return textoReturn;

        }

    }

    /**
     * Realiza la conversión de un archivo de un formato a otro (CSV, XML, JSON)
     * según la opción seleccionada.
     * Dependiendo de la extensión del archivo original y la opción elegida, guarda
     * el archivo convertido en el directorio indicado.
     * 
     * @param opcion              La opción que determina el formato de salida (1
     *                            para CSV, 2 para JSON o para XML segun la entrada
     *                            del fichero).
     * 
     * @param archivoSeleccionado El nombre del archivo a convertir, con su
     *                            extensión original.
     * 
     * @param carpeta             El directorio donde se guardará el archivo
     *                            convertido.
     * 
     * @param texto               El contenido del archivo a convertir, representado
     *                            como una lista de cadenas.
     * 
     * @param nombreSalida        El nombre del archivo de salida, sin la extensión
     *                            final.
     * 
     * @throws IOException Si ocurre un error al crear el archivo de salida o al
     *                     escribir en él.
     */

    public void conversion(int opcion, String archivoSeleccionado, File carpeta, List<String> texto,
            String nombreSalida) throws IOException {

        Path rutaDirectorio = carpeta.toPath();
        Path rutaYSalida = null;

        String extension = detectorExtension(archivoSeleccionado);
        if (extension.equals("xml")) {
            switch (opcion) {
                case 1:
                    nombreSalida += ".csv";
                    rutaYSalida = rutaDirectorio.resolve(nombreSalida);
                    break;
                case 2:
                    nombreSalida += ".json";
                    rutaYSalida = rutaDirectorio.resolve(nombreSalida);
                    break;
                default:
                    break;
            }

        }
        if (extension.equals("csv")) {
            switch (opcion) {
                case 1:
                    nombreSalida += ".json";
                    rutaYSalida = rutaDirectorio.resolve(nombreSalida);
                    break;
                case 2:
                    nombreSalida += ".xml";
                    rutaYSalida = rutaDirectorio.resolve(nombreSalida);
                    break;
                default:
                    break;
            }
        }
        if (extension.equals("json")) {
            switch (opcion) {
                case 1:
                    nombreSalida += ".csv";
                    rutaYSalida = rutaDirectorio.resolve(nombreSalida);
                    break;
                case 2:
                    nombreSalida += ".xml";
                    rutaYSalida = rutaDirectorio.resolve(nombreSalida);
                    break;
                default:
                    break;
            }
        }

        String extensionAconvertir = detectorExtension(nombreSalida);
        if (extension.equals("csv")) {

            switch (extensionAconvertir) {

                case "xml":
                    System.out.println("Convirtiendo '" + extension + "' a  '" + extensionAconvertir + "'  ");

                    Files.createFile(rutaYSalida);
                    List<String> textoConvertido = conversionCsvAxml(texto);
                    escribirFichero(rutaYSalida, textoConvertido);

                    break;

                case "json":
                    System.out.println("Convirtiendo '" + extension + "' a  '" + extensionAconvertir + "'  ");

                    Files.createFile(rutaYSalida);
                    textoConvertido = conversionCsvAjson(texto);

                    escribirFichero(rutaYSalida, textoConvertido);

                    break;

                default:
                    System.out.println("Extension de archivo invalido");
                    break;
            }

        }

        if (extension.equals("xml")) {
            switch (extensionAconvertir) {
                case "csv":
                    System.out.println("Convirtiendo '" + extension + "' a  '" + extensionAconvertir + "'  ");
                    Files.createFile(rutaYSalida);
                    List<String> textoConvertido = conversionXmlAcsv(texto);
                    escribirFichero(rutaYSalida, textoConvertido);

                    break;

                case "json":
                    System.out.println("Convirtiendo '" + extension + "' a  '" + extensionAconvertir + "'  ");

                    Files.createFile(rutaYSalida);
                    textoConvertido = conversionXmlAjson(texto);
                    escribirFichero(rutaYSalida, textoConvertido);

                    break;

                default:
                    System.out.println("Extension de archivo invalido");
                    break;
            }

        }

        if (extension.equals("json")) {

            switch (extensionAconvertir) {
                case "csv":
                    System.out.println("Convirtiendo '" + extension + "' a  '" + extensionAconvertir + "'  ");

                    Files.createFile(rutaYSalida);
                    List<String> textoConvertido = conversionJsonAcsv(texto);
                    escribirFichero(rutaYSalida, textoConvertido);

                    break;

                case "xml":
                    System.out.println("Convirtiendo '" + extension + "' a  '" + extensionAconvertir + "'  ");
                    Files.createFile(rutaYSalida);
                    textoConvertido = conversionJsonAxml(texto);
                    escribirFichero(rutaYSalida, textoConvertido);

                    break;

                default:
                    System.out.println("Extension de archivo invalido");
                    break;
            }

        }

    }

    /**
     * Convierte un archivo en formato JSON a formato CSV. Este método extrae las
     * claves y los valores de un objeto JSON
     * y los organiza en un formato CSV con encabezado y filas correspondientes a
     * los atributos.
     * 
     * @param texto La lista de cadenas que contiene el contenido del archivo JSON a
     *              convertir.
     * @return textoWork Una lista de cadenas que representa el archivo CSV
     *         convertido.
     */
    public List<String> conversionJsonAcsv(List<String> texto) {
        List<String> textoWork = new ArrayList<>();

        int numeroAtributos = 0;
        List<String> etiquetasJson = new ArrayList<>();
        List<String> atributosJson = new ArrayList<>();
        boolean soloUnaVez = false;

        boolean finProceso = false;
        // Extrayendo atributos
        for (int i = 2; i < texto.size(); i++) {
            String linea = texto.get(i).trim(); // Asegurarse de que la línea no sea vacía
            if (linea.isEmpty()) {
                continue; // Si la línea está vacía, la ignoramos
            }

            String[] partes1 = linea.split("[:,]");

            // Verificar si el split produce el número esperado de elementos
            if (partes1.length < 2) {
                System.out.println("Línea malformada (menos de 2 elementos): " + linea);
                continue; // Ignorar líneas malformadas
            }
            for (int j = 0; j < partes1.length; j++) {

                if (i == texto.size() - 3) {
                    if (!soloUnaVez) {
                        String[] partes2 = texto.get(i).split("[:}]");
                        atributosJson.add(partes2[1].trim());
                        soloUnaVez = true;
                    }

                } else {
                    if (j == 1) {
                        if (partes1[j].trim().charAt(0) == ('\"')) {
                            String[] partes3 = partes1[j].split("\"");

                            atributosJson.add(partes3[1]);
                        } else {
                            atributosJson.add(partes1[j]);

                        }

                    }

                }
            }
        }

        // Extrayendo clave de los atributos
        for (int i = 2; i < texto.size() && finProceso == false; i++) {
            String linea = texto.get(i);
            if (linea.trim().equals("},") || linea.trim().equals("}")) {
                finProceso = true;
                continue;
            }

            String[] partes = linea.split("\"");

            // Verificar que la línea tenga suficientes elementos después de dividir
            if (partes.length < 2) {
                continue; // Si no hay suficientes elementos, ignoramos esta línea
            }

            for (int j = 0; j < partes.length && finProceso == false; j++) {

                if (j == 1) {
                    etiquetasJson.add(partes[j]);
                    numeroAtributos++;

                }
            }

        }

        // Isnrtando al csv
        String cabecera = "";

        for (int i = 0; i < etiquetasJson.size(); i++) {
            if (i == etiquetasJson.size() - 1) {
                cabecera += etiquetasJson.get(i);
            } else {
                cabecera += etiquetasJson.get(i) + ",";
            }
        }

        textoWork.add(cabecera);

        // Insertando cuerpo

        String cuerpo = "";
        int saltoPagina = 0;

        for (String string : atributosJson) {

            cuerpo += string.trim();
            saltoPagina++;

            if (saltoPagina == numeroAtributos) {

                textoWork.add(cuerpo);
                cuerpo = "";
                saltoPagina = 0;
            } else {
                cuerpo += ",";
            }

        }

        return textoWork;
    }

    /**
     * Convierte un archivo en formato JSON a formato XML. Este método extrae las
     * claves y los valores de un objeto JSON
     * y los organiza en un formato XML con etiquetas correspondientes a los
     * atributos.
     * 
     * @param texto La lista de cadenas que contiene el contenido del archivo JSON a
     *              convertir.
     * @return textoWork Una lista de cadenas que representa el archivo XML
     *         convertido.
     */
    public List<String> conversionJsonAxml(List<String> texto) {

        List<String> textoWork = new ArrayList<>();
        int numeroAtributos = 0;
        List<String> etiquetasJson = new ArrayList<>();
        List<String> atributosJson = new ArrayList<>();
        boolean soloUnaVez = false;

        boolean finProceso = false;
        // Extrayendo atributos
        for (int i = 2; i < texto.size(); i++) {
            String linea = texto.get(i).trim();
            // Evitar procesar líneas vacías o null IMPORTANTE
            if (linea.isEmpty())
                continue;

            String[] partess = linea.split("[:,]");

            for (int j = 0; j < partess.length; j++) {

                // Evitar procesar líneas con menos de dos elementos después del split
                if (partess.length < 2)
                    continue;

                if (i == texto.size() - 3) {
                    if (!soloUnaVez) {
                        String[] partes4 = texto.get(i).split("[:}]");
                        if (partes4.length > 1) { // Comprobamos que partes4 tenga al menos dos elementos
                            atributosJson.add(partes4[1].trim());
                        }
                        soloUnaVez = true;
                    }
                } else {
                    if (j == 1) {
                        if (partess[j].trim().charAt(0) == ('\"')) {
                            String[] partes3 = partess[j].split("\"");
                            if (partes3.length > 1) { // Aseguramos que se pueda acceder a partes3[1]
                                atributosJson.add(partes3[1].trim());
                            }
                        } else {
                            atributosJson.add(partess[j].trim());
                        }
                    }
                }
            }
        }

        // Extrayendo clave de los atributos
        for (int i = 2; i < texto.size() && finProceso == false; i++) {
            String linea = texto.get(i).trim();
            if (linea.trim().equals("},") || linea.trim().equals("}")) {
                finProceso = true;
                continue;
            }

            String[] partes = linea.split("\"");

            for (int j = 0; j < partes.length && finProceso == false; j++) {

                if (j == 1) {
                    etiquetasJson.add(partes[j].trim());
                    numeroAtributos++;

                }
            }

        }

        // Isnrtando al xml
        int contador = 0;

        // tenemos los strings y las etiquetas para el xml
        String padre = "<Elementos>";
        String finPadre = "</Elementos>";

        String hijo = "<Elemento>";
        String finHijo = "</Elemento>";

        textoWork.add(padre);
        for (int i = 0; i < etiquetasJson.size(); i++) {
            textoWork.add(hijo);

            for (String jsonEtiqueta : etiquetasJson) {
                String lineaXml = "";

                lineaXml+="<" + jsonEtiqueta + ">";

                // Añadir solo el dato correspondiente
                if (contador < texto.size()) {
                   lineaXml+=atributosJson.get(contador); // Aqui ira atributos json
                    contador++;
                }
                lineaXml +="</" + jsonEtiqueta + ">";
                 textoWork.add(lineaXml);

            }
            textoWork.add(finHijo);

        }
        textoWork.add(finPadre);

        return textoWork;
    }

    /**
     * Convierte un archivo en formato XML a formato JSON. Este método extrae las
     * etiquetas y valores de un archivo XML
     * y los organiza en un formato JSON con las claves correspondientes a las
     * etiquetas.
     * 
     * @param texto La lista de cadenas que contiene el contenido del archivo XML a
     *              convertir.
     * @return textoWork Una lista de cadenas que representa el archivo JSON
     *         convertido.
     */
    public List<String> conversionXmlAjson(List<String> texto) {
        List<String> textoWork = new ArrayList<>();
        int numeroAtributos = 0;

        // Reagrupando cabecera en estructura dinámica arraylist
        List<String> etiquetas = new ArrayList<>();

        // 1. Guardar la línea que marca el inicio de un objeto (ej: <usuario>)
        String lineaElemento = texto.get(1).trim();

        // 2. Reagrupar hasta el siguiente objeto
        // Empezamos desde la segunda línea (índice 1) para saltar <usuarios>
        for (int i = 1; i < texto.size(); i++) {
            String linea = texto.get(i).trim();

            // Verificar si la línea está vacía o nula
            if (linea.isEmpty()) {
                continue; // Ignorar líneas vacías
            }

            if (i != 1 && linea.equals(lineaElemento)) {
                break; // cuando encontramos el segundo <usuario>, paramos
            }

            etiquetas.add(linea);
        }

        // 3. Extraer solo las líneas de atributos
        List<String> etiquetasDef = new ArrayList<>();

        for (int i = 1; i < etiquetas.size() - 1; i++) {
            String linea = etiquetas.get(i);
            String[] partes = linea.split("[<>]");

            // Verificar que la línea contenga más de un elemento antes de acceder
            if (partes.length > 1) {
                etiquetasDef.add(partes[1]); // Esto es el nombre de la etiqueta: id, nombre...
            }
        }

        // Contando atributos para hacer saltos al insertar
        for (String etiqueta : etiquetasDef) {
            numeroAtributos++;
        }

        // Reagrupando datos en estructura estática (cuerpo del CSV)
        List<String> auxCuerpo = new ArrayList<>();
        int saltoLinea = 0;
        for (String linea : texto) {
            String[] partes = linea.split("<[^>]+>"); // Divide por cualquier etiqueta
            for (int i = 0; i < partes.length; i++) {
                if (partes[i].trim().equals("")) { // Esta línea elimina elementos vacíos o espaciados
                    continue;
                }

                saltoLinea++;
                if (saltoLinea == numeroAtributos) {
                    auxCuerpo.add(partes[i].trim());
                    saltoLinea = 0;
                } else {
                    auxCuerpo.add(partes[i].trim() + ",");
                }
            }
        }

        // Reagrupando datos en estructura estática (el cuerpo)
        textoWork.add("{");
        textoWork.add("\"Caja\": [");

        // Insertando cabecera
        String etiquetaJson = "";
        for (int i = 0; i < etiquetasDef.size(); i++) {
            if (i == etiquetasDef.size() - 1) {
                etiquetaJson += etiquetasDef.get(i);
            } else {
                etiquetaJson += etiquetasDef.get(i) + ",";
            }
        }

        // Insertando cuerpo
        String cuerpo = "";
        int saltoPagina = 0;
        for (int i = 0; i < auxCuerpo.size(); i++) {
            String string = auxCuerpo.get(i);
            String valorLimpio = string.trim().replaceAll(",$", "");

            // Verificar si es un número
            if (esNumero(valorLimpio)) {
                cuerpo += "\"" + etiquetasDef.get(saltoPagina) + "\":" + string + "\n ";
            } else {
                if (i == auxCuerpo.size() - 1 || (saltoPagina + 1) == numeroAtributos) {
                    cuerpo += "\"" + etiquetasDef.get(saltoPagina) + "\":\"" + valorLimpio + "\"" + " \n";
                } else {
                    cuerpo += "\"" + etiquetasDef.get(saltoPagina) + "\":\"" + valorLimpio + "\"," + " \n";
                }
            }

            saltoPagina++;

            if (i == auxCuerpo.size() - 1) {
                cuerpo = "{ " + cuerpo;
                cuerpo += " }";
                textoWork.add(cuerpo);
                cuerpo = "";
                saltoPagina = 0;
            } else if (saltoPagina == numeroAtributos) {
                cuerpo = "{ " + cuerpo;
                cuerpo += " },";
                textoWork.add(cuerpo);
                cuerpo = "";
                saltoPagina = 0;
            }
        }

        textoWork.add("]");
        textoWork.add("}");

        return textoWork;
    }

    /**
     * Convierte un archivo CSV a formato XML. Este método toma el contenido de un
     * archivo CSV y lo transforma
     * en una estructura XML, utilizando la primera fila como cabecera y cada fila
     * posterior como un elemento XML
     * con las etiquetas correspondientes.
     * 
     * @param texto La lista de cadenas que contiene el contenido del archivo CSV a
     *              convertir.
     * @return textoWork Una lista de cadenas que representa el archivo XML
     *         convertido.
     */

    public List<String> conversionCsvAxml(List<String> texto) {

        List<String> textoWork = new ArrayList<>();
        int contador = 0;
        String cabecera = texto.get(0);
        String[] cabeceraArray = cabecera.split(",");

        String padre = "<Elementos>";
        String finPadre = "</Elementos>";

        String hijo = "<Elemento>";
        String finHijo = "</Elemento>";

        textoWork.add(padre);
        for (int i = 1; i < texto.size(); i++) {
            String linea = texto.get(i).trim(); // Accedemos todas las líneas desde la segunda (el índice 1).

            // Verificar si la línea está vacía
            if (linea.isEmpty()) {
                continue; // Si la línea está vacía, la ignoramos
            }

            String[] datos = linea.split(",");

            // Verificar si la línea tiene el mismo número de elementos que la cabecera
            if (datos.length != cabeceraArray.length) {
                System.out.println("Línea malformada (número de columnas incorrecto): " + linea);
                continue; // Ignorar líneas malformadas
            }

            textoWork.add(hijo);
            contador = 0; // Reiniciamos el contador por cada elemento

            for (String cabeceraCsv : cabeceraArray) {
                String lineaXML = "<" + cabeceraCsv + ">";

                // Añadir solo el dato correspondiente
                if (contador < datos.length) {
                    lineaXML += datos[contador];
                    contador++;
                }
                lineaXML += "</" + cabeceraCsv + ">";

                textoWork.add(lineaXML);
            }
            textoWork.add(finHijo);
        }

        
        textoWork.add(finPadre);

        return textoWork;

    }

    /**
     * Convierte un archivo CSV a formato JSON. Este método toma el contenido de un
     * archivo CSV y lo transforma
     * en una estructura JSON, utilizando la primera fila como claves y las filas
     * posteriores como objetos
     * con los valores correspondientes.
     * 
     * @param texto La lista de cadenas que contiene el contenido del archivo CSV a
     *              convertir.
     * @return textoWork Una lista de cadenas que representa el archivo JSON
     *         convertido.
     */
    public List<String> conversionCsvAjson(List<String> texto) {

        List<String> textoWork = new ArrayList<>();
        textoWork.add("{");
        textoWork.add("  \"Caja_json\": [");

        String cabeceraCsv = texto.get(0);
        String[] cabPartes = cabeceraCsv.split(",");
        int numeroAtributos = cabPartes.length;

        List<String> cabeza = new ArrayList<>();

        for (String string : cabPartes) {
            cabeza.add(string);
        }

        List<String> cuerpo = new ArrayList<>();
        for (int i = 1; i < texto.size(); i++) {
            String linea = texto.get(i).trim();

            // IMPORTANTE Saltamos líneas vacías
            if (linea.isEmpty())
                continue;

            String[] atributosPartes = linea.split(",");

            // Saltamos líneas incompletas
            if (atributosPartes.length != numeroAtributos) {
                //System.out.println("Línea ignorada por ser incompleta: " + linea);
                continue;
            }

            for (String string : atributosPartes) {
                if (!esNumero(string)) {
                    string = "\"" + string + "\"";
                }
                cuerpo.add(string);
            }
        }
        // Es importante el codigo de arriba de tratar con casos de lineas vacias.
        // Insertando del csv al json

        String lineaJson = "";
        for (int i = 0, j = 0; i < cuerpo.size() && j < cuerpo.size(); j++) {
            if (i == 0) {
                lineaJson += "{";
            }
            if (i == numeroAtributos - 1) {
                lineaJson += " \"" + cabeza.get(i) + " \": " + cuerpo.get(j);

            } else {
                lineaJson += " \"" + cabeza.get(i) + " \": " + cuerpo.get(j) + ",";
            }

            textoWork.add(lineaJson.trim());

            lineaJson = "";

            if (i == numeroAtributos - 1 && j == cuerpo.size() - 1) {
                textoWork.add("}");

            } else {
                if (i == numeroAtributos - 1) {

                    i = 0;
                    textoWork.add("},");

                } else {
                    i++;
                }
            }
        }
        textoWork.add("  ]");
        textoWork.add("}");

        return textoWork;
    }

    /**
     * Convierte un archivo XML a formato CSV. Este método toma el contenido de un
     * archivo XML y lo transforma
     * en un archivo CSV, extrayendo las etiquetas XML como cabecera y los valores
     * dentro de esas etiquetas como cuerpo.
     * 
     * @param texto La lista de cadenas que contiene el contenido del archivo XML a
     *              convertir.
     * @return textoWork Una lista de cadenas que representa el archivo CSV
     *         convertido.
     */

    public List<String> conversionXmlAcsv(List<String> texto) {
        List<String> textoWork = new ArrayList<>();
        int numeroAtributos = 0;

        // Reagrupando cabecera en estructura dinámica arraylist
        List<String> auxCabecera = new ArrayList<>();

        // 1. Guardar la línea que marca el inicio de un objeto (ej: <usuario>)
        String lineaElemento = texto.get(1).trim();

        // 2. Reagrupar hasta el siguiente objeto
        // Empezamos desde la segunda línea (índice 1) para saltar <usuarios>
        for (int i = 1; i < texto.size(); i++) {
            String linea = texto.get(i).trim();

            // Verificar si la línea está vacía o nula
            if (linea.isEmpty()) {
                continue; // Ignorar líneas vacías
            }

            if (i != 1 && linea.equals(lineaElemento)) {
                break; // cuando encontramos el segundo <usuario>, paramos
            }

            auxCabecera.add(linea);
        }

        // 3. Extraer solo las líneas de atributos
        List<String> auxCabeceraDef = new ArrayList<>();

        for (int i = 1; i < auxCabecera.size() - 1; i++) {
            String linea = auxCabecera.get(i);
            String[] partes = linea.split("[<>]");

            // Verificar que la línea contenga más de un elemento antes de acceder
            if (partes.length > 1) {
                auxCabeceraDef.add(partes[1]); // Esto es el nombre de la etiqueta: id, nombre...
            }
        }

        // Contando atributos para hacer saltos al insertar
        for (String etiqueta : auxCabeceraDef) {
            numeroAtributos++;
        }

        // Reagrupando datos en estructura estática (cuerpo del csv)
        List<String> auxCuerpo = new ArrayList<>();
        int saltoLinea = 0;
        for (String linea : texto) {
            String[] partes = linea.split("<[^>]+>"); // Divide por cualquier etiqueta
            for (int i = 0; i < partes.length; i++) {
                // Verificar si la parte está vacía antes de agregarla
                if (partes[i].trim().equals("")) {
                    continue; // Esta línea elimina elementos vacíos o espaciados
                }

                saltoLinea++;
                if (saltoLinea == numeroAtributos) {
                    auxCuerpo.add(partes[i].trim());
                    saltoLinea = 0;
                } else {
                    auxCuerpo.add(partes[i].trim() + ",");
                }
            }
        }

        // Reagrupando datos en estructura estática (el cuerpo)

        // Insertando cabecera
        String cabecera = "";
        for (int i = 0; i < auxCabeceraDef.size(); i++) {
            if (i == auxCabeceraDef.size() - 1) {
                cabecera += auxCabeceraDef.get(i);
            } else {
                cabecera += auxCabeceraDef.get(i) + ",";
            }
        }

        textoWork.add(cabecera);

        // Insertando cuerpo
        String cuerpo = "";
        int saltoPagina = 0;

        for (String string : auxCuerpo) {
            cuerpo += string;
            saltoPagina++;

            if (saltoPagina == numeroAtributos) {
                textoWork.add(cuerpo);
                cuerpo = "";
                saltoPagina = 0;
            }
        }

        return textoWork;
    }

    /**
     * Obtiene el archivo de texto gestionado por la clase.
     * 
     * @return archivoTextoGestion La lista de cadenas que representa el archivo de
     *         texto gestionado.
     */

    public List<String> getArchivoTextoGestion() {
        return archivoTextoGestion;
    }

    /**
     * Escribe el contenido de una lista de cadenas en un archivo especificado por
     * la ruta.
     * El contenido se añade al final del archivo existente (si lo hay).
     *
     * @param ruta              La ruta del archivo en el que se escribirá el
     *                          contenido.
     * @param contenidoPorLinea La lista de cadenas que se escribirá en el archivo,
     *                          línea por línea.
     * @throws IOException Si ocurre un error al intentar escribir en el archivo.
     */

    public void escribirFichero(Path ruta, List<String> contenidoPorLinea) throws IOException {
        Files.write(ruta, contenidoPorLinea, StandardOpenOption.APPEND);
    }

    /**
     * Detecta la extensión de un archivo a partir de su nombre.
     * 
     * Este método obtiene la extensión del archivo proporcionado, considerando que
     * la extensión
     * es la parte del nombre después del último punto ("."). El valor devuelto será
     * en minúsculas.
     * 
     * @param nombreArchivo El nombre del archivo del cual se extraerá la extensión.
     * @return La extensión del archivo en minúsculas.
     */

    public String detectorExtension(String nombreArchivo) {
        String[] partes = nombreArchivo.split("\\.");

        String extension = partes[partes.length - 1].toLowerCase();

        return extension;
    }

    /**
     * Verifica si una cadena de texto representa un número.
     * 
     * Este método intenta convertir la cadena de texto a un número de tipo
     * `Double`. Si la conversión
     * es exitosa, devuelve `true`; de lo contrario, captura la excepción
     * `NumberFormatException`
     * y devuelve `false`.
     * 
     * @param texto La cadena de texto que se desea verificar.
     * @return `true` si la cadena representa un número, `false` en caso contrario.
     */

    public static boolean esNumero(String texto) {
        try {
            Double.parseDouble(texto);

            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

}