import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IO;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.lang.model.element.Element;
import javax.swing.text.Document;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.NodeList;
import org.w3c.dom.NodeList;

public class Gestion_Conversor {

    private List<String> archivoTextoGestion;

    public Gestion_Conversor() {
        archivoTextoGestion = new ArrayList<>();
    }

    public void verFichero(String archivo) {
        if (archivo != null) {
            System.out.println("Fichero seleccionado: " + archivo);
        } else {
            System.out.println("No se ha seleccionado ningún fichero. (Lee un archivo para seleccionar)");
        }
    }

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

    public void verContenidoDirectorio(File directorio) {
        boolean esNull = true;
        if (directorio != null) {
            esNull = false;
            Path ruta = directorio.toPath();

            try {
                DirectoryStream<Path> flujoDatos = Files.newDirectoryStream(ruta);
                System.out.println("Conntenido del directorio seleccionado: " + directorio.getAbsolutePath());

                for (Path archivo : flujoDatos) {
                    System.out.println(archivo);
                }

            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Error en el listado de archivos");
            }

        }

        if (esNull) {

            System.out.println("Selecciona primero un directorio para ver su contenido.");
        }

    }

    // Quizas aqui implemtar funcion que devuelva lista<string>
    public File seleccionarDirectorio(String rutaDirectorio) {

        Path ruta = Paths.get(rutaDirectorio);

        // porque no va uuu ?

        if (Files.exists(ruta) && Files.isDirectory(ruta)) {
            File directorio = ruta.toFile();

            System.out.println("Carpeta seleccionada: " + directorio.getAbsolutePath());
            return directorio;

        } else {
            System.out.println("La ruta no existe o no es una carpeta.");
            return null;

        }

    }

    /*
     * public void escribirFichero(String directorioRuta, String fichero,Set<String>
     * contenidoPorLinea) {
     * Path ruta = Paths.get(directorioRuta, fichero);
     * 
     * File archivo = ruta.toFile();
     * 
     * try {
     * if (Files.exists(ruta) && archivo.isFile()) {
     * Files.write(ruta, contenidoPorLinea, StandardOpenOption.APPEND);
     * 
     * } else {
     * // List string
     * System.out.println("No existe el fichero a escribir o no es un fichero");
     * 
     * }
     * 
     * 
     * } catch (IOException e) {
     * e.printStackTrace();
     * }
     * 
     * }
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

    public void conversion(int opcion, String archivoSeleccionado, File carpeta, List<String> texto,
            String nombreSalida) {

        Path rutaDirectorio = carpeta.toPath();

        Path rutaYFichero = rutaDirectorio.resolve(archivoSeleccionado);
        Path rutaYSalida = null;
        boolean convertirAjson = false;
        boolean convertirAxml = false;
        boolean convertirAcsv = false;

        switch (opcion) {
            case 1: // aqui podriamos añadir un boleano por si queremos convertir algo que esta ya
                    // convertido en
                // lo mismo
                System.out.println("Has elegido la conversión a formato xml");
                convertirAxml = true;

                nombreSalida += ".xml";
                rutaYSalida = rutaDirectorio.resolve(nombreSalida);

                break;

            case 2:
                System.out.println("Has elegido la conversión a formato csv");
                convertirAcsv = true;

                nombreSalida += ".csv";
                rutaYSalida = rutaDirectorio.resolve(nombreSalida);

                break;

            case 3:
                System.out.println("Has elegido la conversión a formato json");
                convertirAjson = true;

                nombreSalida += ".json";
                rutaYSalida = rutaDirectorio.resolve(nombreSalida);
                break;

            default:
                System.out.println("Opción no valida");
                break;
        }
        // cuidado con la denominacion partes que quizas de reutilizarlo puede generar
        // problemas.
        String[] partes = archivoSeleccionado.split("\\.");

        String extension = partes[partes.length - 1].toLowerCase();

        if (convertirAcsv) {
            switch (extension) {
                case "csv":
                    System.out.println("Ya esta convertido en 'csv' ");

                    break;

                case "xml":
                    System.out.println("Convirtiendo 'xml' a  'csv' ");

                    try {
                        Files.createFile(rutaYSalida);
                        String textoConvertido = conversionXmlAcsv(texto);
                        escribirFichero(rutaYSalida, textoConvertido);

                    } catch (Exception e) {
                        System.out.println("Error, no se ecuentra la ruta");
                        e.printStackTrace();

                    }
                    break;

                case "json":
                    System.out.println("Convirtiendo  'json' a 'csv' ");

                    try {
                        Files.createFile(rutaYSalida);
                        String textoConvertido = conversionJsonAcsv(texto);

                        escribirFichero(rutaYSalida, textoConvertido);

                    } catch (Exception e) {
                        System.out.println("Error, no se ecuentra la ruta");
                        e.printStackTrace();

                    }
                    break;

                default:
                    System.out.println("Extension de archivo invalido");
                    break;
            }

        }

        if (convertirAxml) {
            switch (extension) {
                case "csv":
                    System.out.println("Convirtiendo 'csv' a 'xml' ");
                    try {
                        Files.createFile(rutaYSalida);
                        String textoConvertido = conversionCsvAxml(texto);
                        escribirFichero(rutaYSalida, textoConvertido);

                    } catch (Exception e) {
                        System.out.println("Error, no se ecuentra la ruta");
                        e.printStackTrace();

                    }
                    break;

                case "xml":
                    System.out.println("Ya esta convertido en 'xml' ");
                    break;

                case "json":
                    System.out.println("Convirtiendo 'json' a 'xml' ");
                    try {

                        Files.createFile(rutaYSalida);
                        String textoConvertido = conversionJsonAxml(texto);
                        escribirFichero(rutaYSalida, textoConvertido);

                    } catch (Exception e) {
                        System.out.println("Error, no se ecuentra la ruta");
                        e.printStackTrace();

                    }

                    break;

                default:
                    System.out.println("Extension de archivo invalido");
                    break;
            }

        }

        if (convertirAjson) {
            switch (extension) {
                case "csv":
                    System.out.println("Convirtiendo 'csv' a 'json'  ");

                    try {
                        Files.createFile(rutaYSalida);

                    } catch (Exception e) {
                        System.out.println("Error, no se ecuentra la ruta");
                        e.printStackTrace();

                    }
                    break;

                case "xml":
                    System.out.println("Convirtiendo  'xml' a 'json' ");
                    try {
                        Files.createFile(rutaYSalida);
                        String textoConvertido = conversionXmlAjson(texto);
                        escribirFichero(rutaYSalida, textoConvertido);

                    } catch (Exception e) {
                        System.out.println("Error, no se ecuentra la ruta");
                        e.printStackTrace();

                    }

                    break;

                case "json":
                    System.out.println("Ya esta convertido en 'json' ");

                    break;

                default:
                    System.out.println("Extension de archivo invalido");
                    break;
            }

        }

    }

    public List<String> getArchivoTextoGestion() {
        return archivoTextoGestion;
    }

    /* */

    //1
    public String conversionCsvAxml(List<String> lineasCsv) {
        StringBuilder xml = new StringBuilder();

        if (lineasCsv.isEmpty()) {
            return "<usuarios></usuarios>";
        }

        String headerLine = lineasCsv.get(0);
        String[] headers = headerLine.split(",");

        xml.append("<usuarios>\n");
        for (int i = 1; i < lineasCsv.size(); i++) {
            String[] values = lineasCsv.get(i).split(",");
            xml.append("    <usuario>\n");
            for (int j = 0; j < headers.length; j++) {
                xml.append("        <").append(headers[j]).append(">")
                        .append(values[j].trim())
                        .append("</").append(headers[j]).append(">\n");
            }
            xml.append("    </usuario>\n");
        }
        xml.append("</usuarios>");

        return xml.toString();
    }

    //2
    public String conversionCsvAjson(List<String> lineasCsv) {
        StringBuilder json = new StringBuilder();

        // Si el CSV está vacío, devolver un array vacío
        if (lineasCsv.isEmpty()) {
            return "[]";
        }

        String headerLine = lineasCsv.get(0); // Primera línea, encabezados
        String[] headers = headerLine.split(",");

        json.append("[\n"); // Inicia el array JSON

        for (int i = 1; i < lineasCsv.size(); i++) {
            String[] values = lineasCsv.get(i).split(",");

            json.append("    {\n"); // Abre el objeto para cada usuario

            for (int j = 0; j < headers.length; j++) {
                // Agrega cada campo como propiedad del objeto
                json.append("        \"").append(headers[j].trim()).append("\": \"")
                        .append(values[j].trim()).append("\"");

                if (j < headers.length - 1) {
                    json.append(",\n"); // Si no es el último campo, agrega una coma
                } else {
                    json.append("\n"); // Último campo, solo salto de línea
                }
            }

            json.append("    }");

            if (i < lineasCsv.size() - 1) {
                json.append(",\n"); // Si no es el último usuario, agrega una coma
            } else {
                json.append("\n"); // Último usuario, solo salto de línea
            }
        }

        json.append("]"); // Cierra el array JSON

        return json.toString(); // Devuelve el JSON como string
    }
    
    //3
    public void escribirFichero(Path ruta, String contenido) {

        try {
            if (Files.exists(ruta)) {
                Files.write(ruta, contenido.getBytes());
            } else {
                System.out.println("No existe el fichero a escribir");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //4
    public String conversionXmlAcsv(List<String> lineasXml) {
        StringBuilder csv = new StringBuilder();

        // Verificamos si la lista de líneas está vacía
        if (lineasXml.isEmpty()) {
            return "";
        }

        // Unir todas las líneas en una sola cadena
        StringBuilder xmlContent = new StringBuilder();
        for (String line : lineasXml) {
            xmlContent.append(line.trim());
        }

        // Eliminar las etiquetas de apertura y cierre <usuarios> </usuarios>
        String xmlData = xmlContent.toString().replace("<usuarios>", "").replace("</usuarios>", "");

        // Buscar todas las ocurrencias de los elementos <usuario>...</usuario>
        String[] usuarios = xmlData.split("<usuario>|</usuario>");

        // Preparar encabezados
        String[] headers = { "id", "nombre", "edad" };
        csv.append(String.join(",", headers)).append("\n");

        // Procesar cada usuario
        for (String usuario : usuarios) {
            if (!usuario.trim().isEmpty()) {
                // Aquí procesamos cada campo del usuario
                String[] campos = usuario.split("<[^>]+>"); // Divide por las etiquetas XML

                // Escribir los valores de cada campo de usuario
                for (String campo : campos) {
                    if (campo.trim().length() > 0) {
                        csv.append(campo.trim()).append(",");
                    }
                }

                // Eliminar la última coma y agregar nueva línea
                if (csv.length() > 0 && csv.charAt(csv.length() - 1) == ',') {
                    csv.deleteCharAt(csv.length() - 1); // Eliminar la coma final
                }
                csv.append("\n");
            }
        }

        return csv.toString();
    }
//5
    public String conversionXmlAjson(List<String> lineasXml) {
        StringBuilder json = new StringBuilder();

        // Si el XML está vacío, devolver un JSON vacío
        if (lineasXml.isEmpty()) {
            return "[]";
        }

        // Unir todas las líneas en una sola cadena
        StringBuilder xmlContent = new StringBuilder();
        for (String line : lineasXml) {
            xmlContent.append(line.trim());
        }

        // Eliminar las etiquetas de apertura y cierre <usuarios> </usuarios>
        String xmlData = xmlContent.toString().replace("<usuarios>", "").replace("</usuarios>", "");

        // Buscar todas las ocurrencias de los elementos <usuario>...</usuario>
        String[] usuarios = xmlData.split("<usuario>|</usuario>");

        // Iniciar el array JSON
        json.append("[\n");

        // Procesar cada usuario
        for (int i = 0; i < usuarios.length; i++) {
            String usuario = usuarios[i].trim();

            // Filtrar y procesar solo los elementos válidos
            if (!usuario.isEmpty()) {
                // Usar expresiones regulares para extraer el contenido entre las etiquetas
                String id = extractTagContent(usuario, "id");
                String nombre = extractTagContent(usuario, "nombre");
                String edad = extractTagContent(usuario, "edad");

                // Empezar el objeto JSON
                json.append("    {\n");

                // Escribir los campos de cada usuario en formato JSON
                json.append("        \"id\": \"").append(id).append("\",\n");
                json.append("        \"nombre\": \"").append(nombre).append("\",\n");
                json.append("        \"edad\": \"").append(edad).append("\"\n");

                json.append("    }");

                // Agregar coma si no es el último usuario
                if (i < usuarios.length - 1) {
                    json.append(",\n");
                } else {
                    json.append("\n");
                }
            }
        }

        // Cerrar el array JSON
        json.append("]");

        return json.toString(); // Devuelve el JSON como string
    }

  

    // Función auxiliar para extraer el contenido de una etiqueta

    private String extractTagContent(String usuario, String tag) {
        String regex = "<" + tag + ">(.*?)</" + tag + ">"; // Buscar entre las etiquetas <tag></tag>
        java.util.regex.Pattern pattern = java.util.regex.Pattern.compile(regex);
        java.util.regex.Matcher matcher = pattern.matcher(usuario);

        if (matcher.find()) {
            return matcher.group(1).trim(); // Devuelve el contenido entre las etiquetas
        } else {
            return ""; // Si no encuentra la etiqueta, devolver vacío
        }
    }


    public String conversionJsonAxml(List<String> lineasJson) {
        StringBuilder xml = new StringBuilder();

        // Si el JSON está vacío, devolver un XML vacío
        if (lineasJson.isEmpty()) {
            return "<usuarios></usuarios>";
        }

        // Unimos todas las líneas del JSON en un solo string
        String jsonCompleto = String.join("", lineasJson);

        // Removemos la parte inicial del JSON que contiene el objeto raíz "usuarios"
        jsonCompleto = jsonCompleto.replace("{\"usuarios\":", "").trim();

        // Eliminamos los corchetes que rodean el array de usuarios
        jsonCompleto = jsonCompleto.substring(1, jsonCompleto.length() - 1);

        // Dividimos los usuarios por el delimitador '},' y procesamos cada uno
        String[] usuarios = jsonCompleto.split("},\\s*\\{");

        // Comenzamos el XML
        xml.append("<usuarios>\n");

        // Procesamos cada usuario dentro del array
        for (String usuario : usuarios) {
            // Limpiar la cadena de cada usuario, eliminando las llaves
            usuario = usuario.trim().replace("{", "").replace("}", "").trim();

            // Dividimos los campos de cada usuario
            String[] partes = usuario.split(",\\s*");

            String id = "", nombre = "", edad = "";

            // Procesamos cada campo
            for (String parte : partes) {
                String[] campo = parte.split(":");

                if (campo.length == 2) {
                    String key = campo[0].trim().replace("\"", "");
                    String value = campo[1].trim().replace("\"", "");

                    // Asignamos los valores de los campos
                    if (key.equals("id")) {
                        id = value;
                    } else if (key.equals("nombre")) {
                        nombre = value;
                    } else if (key.equals("edad")) {
                        // Eliminar cualquier carácter no deseado al final de la edad (como "]")
                        edad = value.replaceAll("[^0-9]", "");
                    }
                }
            }

            // Ahora creamos el XML para este usuario
            xml.append("    <usuario>\n");

            // Si la id está vacía, no incluirla
            if (!id.isEmpty()) {
                xml.append("        <id>").append(id).append("</id>\n");
            }

            xml.append("        <nombre>").append(nombre).append("</nombre>\n");
            xml.append("        <edad>").append(edad).append("</edad>\n"); // Eliminar espacios extra

            xml.append("    </usuario>\n");
        }

        // Finalizamos el XML
        xml.append("</usuarios>\n");

        return xml.toString();
    }

    public String conversionJsonAcsv(List<String> lineasJson) {
        StringBuilder csv = new StringBuilder();

        // Si el JSON está vacío, devolver un CSV vacío
        if (lineasJson.isEmpty()) {
            return "";
        }

        // Unimos todas las líneas del JSON en un solo string
        String jsonCompleto = String.join("", lineasJson);

        // Removemos la parte inicial que contiene el objeto raíz "usuarios"
        jsonCompleto = jsonCompleto.replace("{\"usuarios\":", "").trim();

        // Elimina los corchetes del array de usuarios y parte del final
        // Separamos el contenido antes y después del corchete final
        String[] partes = jsonCompleto.split("\\]");

        // Si la división es exitosa, tomamos la parte que contiene los datos
        if (partes.length > 1) {
            jsonCompleto = partes[0].substring(1).trim(); // Remueve el primer corchete y trim

            // Dividimos los usuarios por el delimitador '},' y procesamos cada uno
            String[] usuarios = jsonCompleto.split("},\\s*\\{");

            // Encabezado CSV: Definimos las columnas basándonos en los campos 'id',
            // 'nombre', 'edad'
            csv.append("id,nombre,edad\n");

            // Procesamos cada usuario dentro del array
            for (String usuario : usuarios) {
                // Limpiar la cadena de cada usuario
                usuario = usuario.trim().replace("{", "").replace("}", "").trim();

                // Dividimos los campos de cada usuario
                String[] partesUsuario = usuario.split(",\\s*");

                String id = "", nombre = "", edad = "";

                // Procesamos cada campo
                for (String parte : partesUsuario) {
                    String[] campo = parte.split(":");

                    if (campo.length == 2) {
                        String key = campo[0].trim().replace("\"", "");
                        String value = campo[1].trim().replace("\"", "");

                        // Asignamos los valores de los campos
                        if (key.equals("id")) {
                            id = value;
                        } else if (key.equals("nombre")) {
                            nombre = value;
                        } else if (key.equals("edad")) {
                            edad = value;
                        }
                    }
                }

                // Solo agregamos el usuario si tiene datos completos (no vacíos)
                if (!id.isEmpty() && !nombre.isEmpty() && !edad.isEmpty()) {
                    csv.append(id).append(",").append(nombre).append(",").append(edad).append("\n");
                }
            }
        }

        return csv.toString();
    }

}