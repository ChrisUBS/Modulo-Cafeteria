package cafeteria;

// Librerias
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Funcion {

    // ArrayLists para manipular los datos
    public static ArrayList<String> contenidoCSV = new ArrayList<String>();
    public static ArrayList<Bebida> bebidas = new ArrayList<Bebida>();
   
    // Ruta del archivo CSV de bebidas (modificar segun sea necesario)
    // static String csvBebidasPath = System.getProperty("user.dir") + "\\examen2_cine\\src\\cafeteria\\bebidas.csv";
    static String csvBebidasPath = "src\\cafeteria\\bebidas.csv";

    // ** Metodos para la consola **

    // Limpiar la consola
    public static void clear() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    // Pausar el programa
    static Scanner pause = new Scanner(System.in);
    public static void pause() {
        System.out.print("\nPresiona Enter para continuar");
        pause.nextLine();
    }

    // ** Metodos para el menu **

    public static void cargarBebidas() {
        // Checar si el archivo existe
        File file = new File(csvBebidasPath);
        if(!file.exists()) {
            return;
        }

        // Cargar el archivo CSV
        contenidoCSV = cargarCSV();

        // Verificar si hay contenido en el archivo
        if (contenidoCSV.size() == 0) {
            return;
        }

        try {
            // Leer los datos del archivo CSV
            for (int i = 6; i < contenidoCSV.size(); i += 6) {
                Bebida bebida = new Bebida();
                bebida.setActivo(Boolean.parseBoolean(contenidoCSV.get(i + 1)));
                bebida.setNombre(contenidoCSV.get(i + 2));
                bebida.setPrecio(Double.parseDouble(contenidoCSV.get(i + 3)));
                bebida.setTipo(contenidoCSV.get(i + 4));
                bebida.setDimension(contenidoCSV.get(i + 5));

                bebidas.add(bebida);
            }
        } catch (Exception error) {
            System.out.println("Error al cargar las bebidas.");
        }
    }

    public static boolean altaProducto(String nombre, double precio, String tipo, String dimension) {
        try {
            Bebida bebida = new Bebida();
            bebida.setActivo(true);
            bebida.setNombre(nombre);
            bebida.setPrecio(precio);
            bebida.setTipo(tipo);
            bebida.setDimension(dimension);

            bebidas.add(bebida);
            guardarEnCSV();
            return true;

        } catch (Exception error) {
            System.out.println("Error al dar de alta el producto");
            return false;
        }
    }

    public static boolean bajaProducto(int index) {
        try {
            Bebida tempBebida = bebidas.get(index - 1);

            if(!tempBebida.isActivo()) {
                return false;
            } else {
                tempBebida.setActivo(false);
                bebidas.set(index - 1, tempBebida);
                guardarEnCSV();
                return true;
            }
    
        } catch (Exception error) {
            return false;
        }
    }

    public static boolean buscarProducto(String nombre) {
        for (Bebida bebida : bebidas) {
            if (bebida.getNombre().toLowerCase().equals(nombre) && bebida.isActivo()) {
                return true;
            }
        }

        return false;
    }

    public static boolean mostrarProductos() {
        // Verificar si hay productos para mostrar
        if(!existenProductosActivos()) {
            return false;
        }

        // Mostrar los productos
        for (Bebida bebida : bebidas) {
            if(bebida.isActivo()) {
                System.out.println("ID: " + (bebidas.indexOf(bebida) + 1));
                System.out.println("Nombre: " + bebida.getNombre());
                System.out.println("Precio: $" + bebida.getPrecio());
                System.out.println("Tipo: " + bebida.getTipo());
                System.out.println("Tamaño: " + bebida.getDimension());
                System.out.println();
            }
        }

        return true;
    }

    public static boolean existenProductosActivos() {
        for (Bebida bebida : bebidas) {
            if (bebida.isActivo()) {
                return true;
            }
        }

        return false;
    }

    // ** Metodos para el archivo CSV **

    public static ArrayList<String> cargarCSV() {
        ArrayList<String> list = new ArrayList<String>();

        try {
            File file = new File(csvBebidasPath);
            Scanner scanner = new Scanner(file);

            // Leer cada línea del archivo CSV
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(",");

                for (int i = 0; i < parts.length; i++) {
                    parts[i] = parts[i].trim();
                    list.add(parts[i]);
                }
            }
            scanner.close(); // Cerrar el scanner

        } catch (FileNotFoundException error) {
            System.out.println("No se pudo encontrar el archivo.");
        }

        return list;
    }

    public static boolean guardarEnCSV() {
        File file = new File(csvBebidasPath);
        boolean fileExists = file.exists();

        try {
            // Verificamos si el archivo ya existe si no lo creamos
            if (!fileExists)
                file.createNewFile();

            // Abre el archivo para escribir
            FileWriter writer = new FileWriter(csvBebidasPath);

            // Escribir el encabezado
            writer.append("Id,activo,nombre,precio,tipo,dimension");

            // Escribir los datos de cada bebida
            for (Bebida bebida : bebidas) {
                writer.append("\n");
                writer.append(String.valueOf(bebidas.indexOf(bebida) + 1) + ",");
                writer.append(String.valueOf(bebida.isActivo()) + ",");
                writer.append(bebida.getNombre() + ",");
                writer.append(String.valueOf(bebida.getPrecio()) + ",");
                writer.append(bebida.getTipo() + ",");
                writer.append(bebida.getDimension());
            }

            writer.flush();
            writer.close();

            return true;

        } catch (IOException error) {
            return false;
        }
    }

}