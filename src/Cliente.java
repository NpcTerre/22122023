import java.util.Scanner;

/**
 * Clase que representa a un Cliente en el sistema de envíos.
 * Contiene información personal del cliente y gestiona sus envíos.
 * Autor: Daniel Terreros Encinar
 * Versión: 1.0
 */
public class Cliente {
    // Atributos de la clase Cliente
    private final ListaEnvios listaEnvios;
    private final String nombre;
    private final String apellidos;
    private final String email;

    /**
     * Constructor of the class
     *
     * @param nombre Nombre del cliente
     * @param apellidos Apellidos del cliente
     * @param email Email del cliente
     * @param maxEnvios Número máximo de envíos que puede tener el cliente
     */
    public Cliente(String nombre, String apellidos, String email, int maxEnvios) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.email = email;
        this.listaEnvios = new ListaEnvios(maxEnvios);
    }
    // Métodos getters para acceder a los atributos privados de la clase
    /**
     * Getter para obtener el nombre del cliente.
     *
     * @return El nombre del cliente
     */
    public String getNombre() {
        return nombre;
    }
    /**
     * Getter para obtener los apellidos del cliente.
     *
     * @return Los apellidos del cliente
     */
    public String getApellidos() {
        return apellidos;
    }
    /**
     * Getter para obtener el email del cliente.
     *
     * @return El email del cliente
     */
    public String getEmail() {
        return email;
    }
    /**
     * Genera una representación en cadena del cliente.
     *
     * @return Representación en cadena del cliente con formato "nombre apellidos, email"
     */
    // TODO: Texto que debe generar: Zapp Brannigan, zapp.brannigan@dop.gov
    public String toString() {
        return nombre+" "+apellidos+", "+email;
    }
    // Métodos adicionales para la gestión de envíos del cliente

    // TODO: Devuelve un booleano que indica si se ha alcanzado el número máximo de envíos
    /**
     * Método que indica si se ha alcanzado el número máximo de envíos del cliente.
     *
     * @return true si se han alcanzado el número máximo de envíos, false de lo contrario
     */
    public boolean maxEnviosAlcanzado() {
        return listaEnvios.estaLlena();
    }
    // TODO: Devuelve un envío en función de su posición
    /**
     * Método para obtener un envío específico del cliente según su posición en la lista.
     *
     * @param i Posición del envío en la lista
     * @return El envío en la posición indicada
     */
    public Envio getEnvio(int i) {
        return listaEnvios.getEnvio(i);
    }
    /**
     * Método para obtener la lista completa de envíos del cliente.
     *
     * @return La lista completa de envíos del cliente
     */
    public ListaEnvios getListaEnvios() {
        return listaEnvios;
    }
    // TODO: Añade un envío al cliente
    /**
     * Método para añadir un nuevo envío al cliente.
     *
     * @param envio El envío a añadir
     * @return true si se añade correctamente, false si la operación falla
     */
    public boolean aniadirEnvio(Envio envio) {
        return listaEnvios.insertarEnvio(envio);
    }
    /**
     * Método para buscar un envío en la lista de envíos del cliente según un localizador específico.
     *
     * @param localizador El localizador del envío a buscar
     * @return El envío con el localizador indicado, si se encuentra; de lo contrario, null
     */
    public Envio buscarEnvio(String localizador) {
        return listaEnvios.buscarEnvio(localizador);
    }
    /**
     * Método para cancelar un envío en la lista de envíos del cliente según un localizador específico.
     *
     * @param localizador El localizador del envío a cancelar
     * @return true si se cancela correctamente, false si la operación falla
     */
    // TODO: Elimina el envío de la lista de envíos del pasajero
    public boolean cancelarEnvio(String localizador) {
        return listaEnvios.eliminarEnvio(localizador);
    }

    /**
     * Método para listar los envíos del cliente.
     */
    public void listarEnvios() {
        listaEnvios.listarEnvios();
    }
    // Encapsula la funcionalidad seleccionarEnvio de ListaEnvios
    /**
     * Método que encapsula la funcionalidad de seleccionar un envío en la lista de envíos del cliente.
     *
     * @param teclado  Scanner para entrada por teclado
     * @param mensaje  Mensaje para solicitar la selección del envío
     * @return El envío seleccionado
     */
    public Envio seleccionarEnvio(Scanner teclado, String mensaje) {
        return listaEnvios.seleccionarEnvio(teclado, mensaje);
    }


    /**
     * TODO: Método estático para crear un nuevo cliente "no repetido", se pide por teclado los datos necesarios
     * al usuario en el orden y con los textos indicados en los ejemplos de ejecución del enunciado
     * La función tiene que solicitar repetidamente los parámetros hasta que sean correctos
     * @param teclado Scanner para entrada por teclado
     * @param clientes Lista de clientes existentes
     * @param maxEnvios Número máximo de envíos que puede tener el cliente
     * @return Cliente recién creado
     */
    public static Cliente altaCliente(Scanner teclado, ListaClientes clientes, int maxEnvios) {
        String nombre, apellidos, email;
        do {
            nombre = Utilidades.leerCadena(teclado, "Nombre: ");
            apellidos = Utilidades.leerCadena(teclado, "Apellidos: ");
            email = Utilidades.leerCadena(teclado, "Email: ");
        } while(!nombre.matches("[A-Za-z]+") || !apellidos.matches("[A-Za-z]+")  ||!correctoEmail(email) || clientes.buscarClienteEmail(email) != null);
        return new Cliente(nombre, apellidos, email, maxEnvios);
    }


    /**
     * TODO: Metodo para comprobar que el formato de email introducido sea correcto
     * @param email Email a verificar
     * @return true si el email tiene el formato correcto, false en caso contrario
     */
    public static boolean correctoEmail(String email) {
        String[] partes = email.split("@");
        // Verificar si se dividió en dos partes y ambas tienen contenido
        if (partes.length != 2 || partes[0].isEmpty() || partes[1].isEmpty()) {
            return false;
        }
        String nombre = partes[0];
        String dominio = partes[1];
        // Verificar que el nombre tenga los caracteres deseados
        if (!nombre.matches("[A-Za-z0-9/.]+")) {
            return false;
        }
        // Verificar que el dominio sea uno de los permitidos
        return dominio.equals("planetexpress.com") || dominio.equals("upm.es");
    }

}
