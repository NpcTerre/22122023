import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Esta clase gestiona una lista de clientes, proporcionando métodos para operar con ellos.
 * Permite almacenar, buscar y manipular objetos de tipo Cliente en una lista.
 * @author Daniel Terreros Encinar
 * @version     1.0
 */
public class ListaClientes {
    private Cliente[] clientes;

    /**
     * TODO: Constructor de la clase para inicializar la lista a una capacidad determinada
     *
     * @param capacidad La capacidad máxima de la lista de cliente
     */
    public ListaClientes(int capacidad) {
        this.clientes=new Cliente[capacidad];
    }
    // TODO: Devuelve el número de clientes que hay en la lista de clientes
    /**
     * Devuelve el número de clientes almacenados actualmente en la lista de clientes.
     *
     * @return El número de clientes presentes en la lista
     */
    public int getOcupacion() {
        int posicion;
        for (posicion = 0; clientes[posicion] != null; posicion++) {
            // No necesitamos realizar ninguna operación aquí, el bucle incrementa posicion automáticamente
        }
        return posicion;
    }

    // TODO: ¿Está llena la lista de clientes?
    /**
     * Verifica si la lista de clientes está llena.
     *
     * @return true si la lista está llena, false en caso contrario
     */
    public boolean estaLlena() {
        return getOcupacion()==clientes.length;
    }
	// TODO: Devuelve el cliente dada el indice
    /**
     * Devuelve el cliente almacenado en una posición específica de la lista.
     *
     * @param i El índice del cliente deseado
     * @return El cliente en la posición indicada
     */
    public Cliente getCliente(int i) {

        return clientes[i];
    }
    // TODO: Inserta el cliente en la lista de clientes
    /**
     * Inserta un nuevo cliente en la lista de clientes, si hay espacio disponible.
     *
     * @param cliente El cliente a insertar en la lista
     * @return true si se inserta correctamente, false si la lista está llena
     */
    public boolean insertarCliente(Cliente cliente) {
        if(!estaLlena()){
            int posicion=getOcupacion();
            clientes[posicion]=cliente;
            return true;
        }
        return false;
    }
    // TODO: Devuelve el cliente que coincida con el email, o null en caso de no encontrarlo
    /**
     * Busca y devuelve el cliente que coincida con el email proporcionado, o null si no se encuentra.
     *
     * @param email El email del cliente a buscar
     * @return El cliente que coincide con el email o null si no se encuentra
     */
    public Cliente buscarClienteEmail(String email) {
        for (Cliente cliente : clientes) {
            if (cliente != null && cliente.getEmail().equals(email)) {
                return cliente;
            }
        }
        return null; // Si no se encuentra el cliente con ese email
    }


    /**
     * TODO: Método para seleccionar un Cliente existente a partir de su email, usando el mensaje pasado como argumento
     *  para la solicitud y, siguiendo el orden y los textos mostrados en el enunciado.
     *  La función debe solicitar repetidamente hasta que se introduzca un email correcto
     * @param teclado El objeto Scanner para la entrada del usuario
     * @param mensaje  El mensaje a mostrar al usuario para solicitar el email
     * @return El cliente seleccionado a partir del email introducido
     */
    public Cliente seleccionarCliente(Scanner teclado, String mensaje) {
        Cliente cliente = null;
        String email = null;
        String[] emailCompleto = ((String)email).split("@");
        String primeraParte = emailCompleto[0];
        String segundaParte = emailCompleto[1];
        int longitud = primeraParte.length();
        boolean correcto = true;

        do {
            System.out.println(mensaje);
            emailCompleto = teclado.delimiter().split("@");
            if (!segundaParte.equals("planetexpress.com")) {
                System.out.println("Email incorrecto.");
                correcto = false;
            } else if (primeraParte.charAt(0) != '.' && primeraParte.charAt(longitud - 1) != '.') {
                for(int i = 0; i < longitud; ++i) {
                    if ((primeraParte.charAt(i) < 'A' || primeraParte.charAt(i) > 'Z') && (primeraParte.charAt(i) < 'a' || primeraParte.charAt(i) > 'z') && primeraParte.charAt(i) != '.') {
                        System.out.println("Email incorrecto");
                        correcto = false;
                    }
                }
            } else {
                System.out.println("Email incorrecto.");
                correcto = false;
            }
        } while(this.buscarClienteEmail(Arrays.toString(emailCompleto)) == null);

        return (Cliente)cliente;
    }

    /**
     * TODO: Método para guardar la lista de clientes en un fichero .csv, sobreescribiendo la información del mismo
     *  fichero
     * @param fichero La ruta del archivo .csv donde se va a guardar la información
     * @return true si se guarda correctamente, false si hay un error
     */
    public boolean escribirClientesCsv(String fichero) {
        PrintWriter pw = null;
        boolean ficheroEscrito = true;
        try {
            pw = new PrintWriter(fichero);
            for (int i = 0; i < getOcupacion(); i ++){
                Cliente cliente1 = clientes[i];
                pw.write(cliente1.getNombre() + ";" + cliente1.getApellidos() + ";" + cliente1.getEmail());
                if (i != getOcupacion() -1) pw.println();
            }
        } catch (FileNotFoundException fileNotFoundException){
            System.out.println("Fichero " + fichero + " no encontrado.");
            ficheroEscrito = false;
        } catch (IOException ioException) {
            System.out.println("Error de escritura en fichero " + fichero + ".");
            ficheroEscrito = false;
        } finally {
            if(pw != null){
                pw.close();
            }
        }
        return ficheroEscrito;
    }

    /**
     * TODO: Genera una lista de Clientes a partir del fichero CSV, usando los límites especificados como argumentos
     *  para la capacidad de la lista y el número de billetes máximo por pasajero
     * @param fichero              La ruta del archivo .csv que contiene la información de los clientes
     * @param capacidad            La capacidad máxima de la lista de clientes
     * @param maxEnviosPorCliente  El número máximo de envíos por cliente
     * @return La lista de clientes generada a partir del archivo .csv
     **/
    public static ListaClientes leerClientesCsv(String fichero, int capacidad, int maxEnviosPorCliente) {
        ListaClientes listaClientes = new ListaClientes(capacidad);
        Scanner sc = null;
        Cliente cliente;
        try {
            sc = new Scanner(new FileReader(fichero));
            do {
                String []arrayClientes = sc.nextLine().split(";");
                cliente = new Cliente(arrayClientes[0], arrayClientes[1], arrayClientes[2], maxEnviosPorCliente);
                listaClientes.insertarCliente(cliente);
            }while(sc.hasNext());
        }catch (FileNotFoundException fileNotFoundException) {
            System.out.println("Fichero Billetes no encontrado.");
            System.out.println("Fichero " + fichero + " no encontrado.");
        } finally {
            if (sc != null) {
                sc.close();
            }
        }
        return listaClientes;
    }
}