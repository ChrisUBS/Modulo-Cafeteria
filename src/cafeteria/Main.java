/*
 * Christian Uriel Bonilla Suárez
 * 
 * Examen 2: Módulo de la cafetería
 * 
 * INFO:
 * Con este módulo se pueden agregar productos a la cafetería, 
 * dar de baja productos y mostrar los productos, dichos productos
 * se guardan en un archivo CSV.
 */

package cafeteria;

public class Main {

    public static void main(String[] args) {

        // Variable de estado
        boolean isRunning = true;

        // Inicializar las bebidas
        Funcion.cargarBebidas();

        // Loop principal
        while(isRunning) {
            Funcion.clear();
            System.out.println("*** CAFETERIA ***");
            System.out.println("1. Agregar producto");
            System.out.println("2. Dar de baja producto");
            System.out.println("3. Mostrar productos");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opcion: ");
            String opcion = Valida.readString();
            
            switch (opcion) {
                case "1":
                    // Variables para el nuevo producto
                    String nombre = "", precio = "", tipo = "", dimension = "";

                    // Obtener el nombre del producto
                    while(!Valida.isOnlyString(nombre)) {
                        Funcion.clear();
                        System.out.println("*** CAFETERIA ***");
                        System.out.println("Agregar producto");
                        System.out.print("Nombre del producto: ");
                        nombre = Valida.readString();
                    }

                    // Verificar si el producto ya existe
                    if(Funcion.buscarProducto(nombre.toLowerCase())) {
                        System.out.println("\nEl producto ya existe");
                        break;
                    }

                    // Obtener el precio del producto
                    while(!Valida.isADoubleNumber(precio) || Valida.isNegativeNumber(precio)) {
                        Funcion.clear();
                        System.out.println("*** CAFETERIA ***");
                        System.out.println("Agregar producto");
                        System.out.println("Nombre del producto: " + nombre);
                        System.out.print("Precio del producto: $");
                        precio = Valida.readString();
                    }

                    // Obtener el tipo del producto
                    while(!Valida.isAIntNumber(tipo) || Valida.isNegativeNumber(tipo) || !Valida.isInRange(tipo, 1, 3)){
                        Funcion.clear();
                        System.out.println("*** CAFETERIA ***");
                        System.out.println("Agregar producto");
                        System.out.println("Nombre del producto: " + nombre);
                        System.out.println("Precio del producto: $" + precio);
                        System.out.println("Tipo del producto" );
                        System.out.println("1. Caliente");
                        System.out.println("2. Fria");
                        System.out.println("3. Frappe");
                        System.out.print("Seleccione una opcion: ");
                        tipo = Valida.readString();
                    }
                    if (tipo.equals("1")) tipo = "Caliente";
                    if (tipo.equals("2")) tipo = "Fria";
                    if (tipo.equals("3")) tipo = "Frappe";

                    // Obtener la dimension del producto
                    while(!Valida.isAIntNumber(dimension) || Valida.isNegativeNumber(dimension) || !Valida.isInRange(dimension, 1, 2)){
                        Funcion.clear();
                        System.out.println("*** CAFETERIA ***");
                        System.out.println("Agregar producto");
                        System.out.println("Nombre del producto: " + nombre);
                        System.out.println("Precio del producto: $" + precio);
                        System.out.println("Tipo del producto: " + tipo);
                        System.out.println("Dimension del producto");
                        System.out.println("1. Chico");
                        System.out.println("2. Grande");
                        System.out.print("Seleccione una opcion: ");
                        dimension = Valida.readString();
                    }
                    if (dimension.equals("1")) dimension = "Chico";
                    if (dimension.equals("2")) dimension = "Grande";

                    // Agregar el producto
                    if(Funcion.altaProducto(nombre, Double.parseDouble(precio), tipo, dimension)) {
                        System.out.println("\nProducto agregado exitosamente");
                    } else {
                        System.out.println("\nError al agregar el producto");
                    }
                    break;
                
                case "2":
                    Funcion.clear();
                    System.out.println("*** CAFETERIA ***");
                    if(!Funcion.existenProductosActivos()) {
                        System.out.println("\nNo hay productos para dar de baja");
                    } else {
                        String index = "";
                        while(!Valida.isAIntNumber(index) || Valida.isNegativeNumber(index)){
                            System.out.print("ID del producto a dar de baja: ");
                            index = Valida.readString();
                        }
                        if(Funcion.bajaProducto(Integer.parseInt(index))) {
                            System.out.println("\nProducto dado de baja exitosamente");
                        } else {
                            System.out.println("\nError al dar de baja el producto o producto no encontrado");
                        }
                        
                    }
                    break;

                case "3":
                    Funcion.clear();
                    System.out.println("*** CAFETERIA ***");
                    System.out.println("Mostrar productos\n");
                    if(!Funcion.mostrarProductos()) {
                        System.out.println("No hay productos para mostrar");
                    }
                    break;

                case "4":
                    System.out.println("\nSaliendo del programa...");
                    isRunning = false;
                    break;
            
                default:
                    System.out.println("\nOpcion no valida");
                    break;
            }

            Funcion.pause();
        }
    }
}