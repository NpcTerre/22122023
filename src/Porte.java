import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;

/**
 * Clase que representa un Porte en el sistema de PlanetExpress.
 * Un Porte es una sección dentro de una nave que puede contener múltiples envíos.
 * Permite gestionar la ocupación de los huecos dentro del Porte y realizar operaciones relacionadas con los envíos.
 * @author Daniel Terreros Encinar
 * @version     1.0
 */
public class Porte {
    private boolean[][] huecos;
    private String id;
    private Nave nave;
    private PuertoEspacial origen;
    private int muelleOrigen;
    private Fecha salida;
    private PuertoEspacial destino;
    private int muelleDestino;
    private Fecha llegada;
    private double precio;
    private ListaEnvios listaEnvios;

    /**
     * TODO: Completa el constructo de la clase
     *
     * @param id Identificador único del Porte.
     * @param nave Nave a la que pertenece el Porte.
     * @param origen Puerto espacial de origen.
     * @param muelleOrigen Número de muelle de origen.
     * @param salida Fecha de salida del Porte.
     * @param destino Puerto espacial de destino.
     * @param muelleDestino Número de muelle de destino.
     * @param llegada Fecha de llegada del Porte.
     * @param precio Precio del Porte.
     */
    public Porte(String id, Nave nave, PuertoEspacial origen, int muelleOrigen, Fecha salida, PuertoEspacial destino, int muelleDestino, Fecha llegada, double precio) {
        this.id=id;
        this.nave=nave;
        this.origen=origen;
        this.muelleOrigen=muelleOrigen;
        this.salida=salida;
        this.destino=destino;
        this.muelleDestino=muelleDestino;
        this.llegada=llegada;
        this.precio=precio;
    }
    /**
     * Devuelve el ID único del Porte.
     * @return El ID del Porte.
     */
    public String getID() {
        return id;
    }
    /**
     * Devuelve la Nave asociada al Porte.
     * @return La Nave del Porte.
     */
    public Nave getNave(){
        return nave;
    }
    /**
     * Devuelve el Puerto Espacial de origen del Porte.
     * @return El Puerto Espacial de origen.
     */
    public PuertoEspacial getOrigen() {
        return origen;
    }
    /**
     * Devuelve el número de muelle de origen del Porte.
     * @return El número de muelle de origen.
     */
    public int getMuelleOrigen() {
        return muelleOrigen;
    }
    /**
     * Devuelve la fecha y hora de salida del Porte.
     * @return La fecha y hora de salida.
     */
    public Fecha getSalida(){
        return salida;
    }
    /**
     * Devuelve el Puerto Espacial de destino del Porte.
     * @return El Puerto Espacial de destino.
     */
    public PuertoEspacial getDestino() {
        return destino;
    }
    /**
     * Devuelve el número de muelle de destino del Porte.
     * @return El número de muelle de destino.
     */
    public int getMuelleDestino() {
        return muelleDestino;
    }
    /**
     * Devuelve la fecha y hora de llegada del Porte.
     * @return La fecha y hora de llegada.
     */
    public Fecha getLlegada() {
        return llegada;
    }
    /**
     * Devuelve el precio asociado al Porte.
     * @return El precio del Porte.
     */
    public double getPrecio() {
        return precio;
    }
    // TODO: Devuelve el número de huecos libres que hay en el porte
    public int numHuecosLibres() {
        int huecos=0;
        for(int i=0; i< nave.getFilas();i++){
            for(int j=0; j<nave.getColumnas();j++){
                if(!huecoOcupado(i,j)){
                    huecos++;
                }
            }
        }
        return huecos;
    }
    // TODO: ¿Están llenos todos los huecos?
    public boolean porteLleno() {return numHuecosLibres() == 0;
    }
    // TODO: ¿Está ocupado el hueco consultado?
    /**
     * Verifica si un hueco específico del Porte está ocupado.
     *
     * @param fila Fila del hueco.
     * @param columna Columna del hueco.
     * @return true si el hueco está ocupado, false en caso contrario.
     */
    public boolean huecoOcupado(int fila, int columna) {
        return huecos[fila][columna];
    }
    /**
     * Busca un Envío por su localizador en la lista de Envíos del Porte.
     *
     * @param localizador Localizador del Envío.
     * @return Objeto Envío correspondiente al localizador, o null si no se encuentra.
     */
    public Envio buscarEnvio(String localizador) {
        return listaEnvios.buscarEnvio(localizador);
    }
    /**
     * TODO: Devuelve el objeto Envio que corresponde con una fila o columna,
     * @param fila Fila del hueco.
     * @param columna Columna del hueco.
     * @return Objeto Envío correspondiente a la posición, o null si no se encuentra o la posición es inválida.
     */
    public Envio buscarEnvio(int fila, int columna) {
        return listaEnvios.buscarEnvio(id,fila,columna);
    }
    /**
     * TODO: Método que Si está desocupado el hueco que indica el envio, lo pone ocupado y devuelve true,
     *  si no devuelve false
     * @param envio Envío a ser ubicado en el Porte.
     * @return true si el hueco estaba desocupado y se pudo ocupar, false en caso contrario.
     */
    public boolean ocuparHueco(Envio envio) {
        int fila=envio.getFila();
        int columna= envio.getColumna();
        if (!huecoOcupado(fila, columna)) {
            huecos[fila][columna] = true;
            listaEnvios.insertarEnvio(envio);
            return true;
        }
        return false;
    }
    /**
     * TODO: A través del localizador del envio, se desocupará el hueco
     * @param localizador Localizador del envío a ser desocupado.
     * @return true si se desocupó el hueco correctamente, false si el hueco ya estaba libre o el localizador no existe.
     */
    public boolean desocuparHueco(String localizador) {
        Envio envio = buscarEnvio(localizador);
        int fila=envio.getFila();
        int columna=envio.getColumna();
        if (huecoOcupado(fila, columna)) {
            huecos[fila][columna] = false;
            listaEnvios.eliminarEnvio(envio.getLocalizador());
            return true;
        }
        return false;
    }
    /**
     * TODO: Devuelve una cadena con información completa del porte
     * @return ejemplo del formato -> "Porte PM0066 de Gaia Galactic Terminal(GGT) M5 (01/01/2023 08:15:00) a
     *  Cidonia(CID) M1 (01/01/2024 11:00:05) en Planet Express One(EP-245732X) por 13424,56 SSD, huecos libres: 10"
     */
    public String toString() {
        return ("Porte " + id + " de " + origen.toStringSimple() + " " + origen.getCodigo() + " (" + salida + ") a " +
                destino.toStringSimple() + " " + destino.getCodigo() + " (" + llegada + ") en " + nave + " por " + precio + " SSD, huecos libres: " + numHuecosLibres());
    }
    /**
     * TODO: Devuelve una cadena con información abreviada del vuelo
     * @return ejemplo del formato -> "Porte PM0066 de GGT M5 (01/01/2023 08:15:00) a CID M1 (01/01/2024 11:00:05)"
     */
    public String toStringSimple() {
        return "Porte " + id + " de " + origen.obtenerIniciales() + " " + origen.getCodigo() + " (" + salida + ") a " + destino.obtenerIniciales() + " " + destino.getCodigo() + " (" + llegada + ")";
    }
    /**
     * TODO: Devuelve true si el código origen, destino y fecha son los mismos que el porte
     * @param codigoOrigen Código del origen a comparar.
     * @param codigoDestino Código del destino a comparar.
     * @param fecha Fecha de salida a comparar.
     * @return true si los códigos y la fecha coinciden con los del Porte, false en caso contrario.
     */
    public boolean coincide(String codigoOrigen, String codigoDestino, Fecha fecha) {
        return (codigoOrigen.equals(origen.getCodigo())&&codigoDestino.equals(origen.getCodigo())&&fecha.equals(salida));
    }
    /**
     * TODO: Muestra la matriz de huecos del porte, ejemplo:
     *        A  B  C
     *      1[ ][ ][ ]
     *      2[X][X][X]
     *      3[ ][ ][ ]
     *      4[ ][X][ ]
     *     10[ ][ ][ ]
     */
    public void imprimirMatrizHuecos() {
        char estadoHueco;

        // Letras superiores
        System.out.print("  ");
        for (char c = 'A'; c <= 'Z'; c++) {
            System.out.printf("%3c ", c);
        }
        System.out.println();
        // Matriz de huecos
        int fila = 1;
        for (boolean[] filaHuecos : huecos) {
            System.out.printf("%2d", fila++);

            for (boolean ocupado : filaHuecos) {
                estadoHueco = ocupado ? 'X' : ' ';
                System.out.printf("[%c] ", estadoHueco);
            }
            System.out.println();
        }
    }

    /**
     * TODO: Devuelve true si ha podido escribir en un fichero la lista de envíos del porte, siguiendo las indicaciones
     *  del enunciado
     * @param fichero Nombre del fichero donde se escribirá la lista de envíos.
     * @return true si se pudo escribir la lista de envíos en el fichero correctamente, false en caso contrario.
     */
    public boolean generarListaEnvios(String fichero) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(fichero, true))) {
            escribirCabecera(pw);
            escribirHuecosYClientes(pw);
            return true;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
    /**
     * Escribe la cabecera de la lista de envíos del Porte en un fichero.
     *
     * @param pw Instancia de PrintWriter para escribir en el fichero.
     */
    private void escribirCabecera(PrintWriter pw) {
        pw.println("--------------------------------------------------");
        pw.println("-------- Lista de envíos del porte " + id + "--------");
        pw.println("--------------------------------------------------");
        pw.println("Hueco\t\tCliente");
    }
    /**
     * Escribe los huecos ocupados y los clientes asociados a los envíos en un fichero.
     *
     * @param pw Instancia de PrintWriter para escribir en el fichero.
     */
    private void escribirHuecosYClientes(PrintWriter pw) {
        Envio envio;
        Cliente cliente;

        for (int i = 0; i < huecos.length; i++) {
            for (int k = 0; k < huecos[i].length; k++) {
                if (huecos[i][k]) {
                    envio = listaEnvios.buscarEnvio(id, i, k);
                    cliente = envio.getCliente();
                    escribirEnvioCliente(pw, envio, cliente);
                } else {
                    escribirHuecoLibre(pw, i, k);
                }
            }
        }
    }
    /**
     * Escribe un envío y su cliente asociado en un fichero.
     *
     * @param pw Instancia de PrintWriter para escribir en el fichero.
     * @param envio Envío a ser escrito.
     * @param cliente Cliente asociado al envío.
     */
    private void escribirEnvioCliente(PrintWriter pw, Envio envio, Cliente cliente) {
        pw.printf(
                "%2d%1c\t\t%s %s, %s\n",
                envio.getFila(),
                (char) (envio.getColumna() + 'A'),
                cliente.getNombre(),
                cliente.getApellidos(),
                cliente.getEmail()
        );
    }
    /**
     * Escribe un hueco libre en un fichero.
     *
     * @param pw Instancia de PrintWriter para escribir en el fichero.
     * @param fila Fila del hueco libre.
     * @param columna Columna del hueco libre.
     */
    private void escribirHuecoLibre(PrintWriter pw, int fila, int columna) {
        pw.printf("%2d%1c\n", fila + 1, (char) (columna + 'A'));
    }

    /**
     * TODO: Genera un ID de porte. Este consistirá en una cadena de 6 caracteres, de los cuales los dos primeros
     *  serán PM y los 4 siguientes serán números aleatorios.
     *  NOTA: Usar el objeto rand pasado como argumento para la parte aleatoria.
     * @param rand Objeto Random para generar los números aleatorios.
     * @return Un ID de porte generado. Ejemplo -> "PM0123"
     */
    public static String generarID(Random rand) {
        return String.format("PM%04d",rand.nextInt(10000));
    }
    /**
     * TODO: Crea y devuelve un objeto Porte de los datos que selecciona el usuario de puertos espaciales
     *  y naves y la restricción de que no puede estar repetido el identificador, siguiendo las indicaciones
     *  del enunciado.
     *  La función solicita repetidamente los parametros hasta que sean correctos
     * @param teclado Scanner para recibir datos del usuario.
     * @param rand Objeto Random para la generación de números aleatorios.
     * @param puertosEspaciales Lista de puertos espaciales disponibles.
     * @param naves Lista de naves disponibles.
     * @param portes Lista de portes existentes.
     * @return Un objeto Porte creado a partir de los datos seleccionados por el usuario.
     */
    public static Porte altaPorte(Scanner teclado, Random rand,
                                  ListaPuertosEspaciales puertosEspaciales,
                                  ListaNaves naves,
                                  ListaPortes portes) {
           boolean envioCorrecto = false;
            boolean fechaCorrecta;
            Fecha fechaLlegada;
            Fecha fechaSalida;
            //Información de los Puertos Espaciales
             PuertoEspacial origen;
        int muelleOrigen;

        do {
            origen = puertosEspaciales.seleccionarPuertoEspacial(teclado, "Ingrese código de puerto Origen: ");
            if (origen == null) {
                System.out.println("Código de puerto no encontrado.");
            }
        } while (origen == null);

        String primerMensaje = "Ingrese el muelle de Origen (1 - " + origen.getMuelles() + "): ";
        muelleOrigen = Utilidades.leerNumero(teclado, primerMensaje, 1, origen.getMuelles());

            PuertoEspacial destino = puertosEspaciales.seleccionarPuertoEspacial(teclado, "Ingrese código de puerto Destino: ");
            String segundoMensaje = "Ingrese el muelle de Destino (1 - " + destino.getMuelles() + "): ";
            int muelleDestino = Utilidades.leerNumero(teclado, segundoMensaje, 1, destino.getMuelles());
            //Información de la nave
            double distancia = origen.distancia(destino);
            Nave nave = naves.seleccionarNave(teclado, "Ingrese matrícula de la nave: ", distancia);
            //Comprobamos la fecha
            do {
                fechaSalida = Utilidades.leerFechaHora(teclado, "Introduzca la fecha de salida: ");
                fechaLlegada = Utilidades.leerFechaHora(teclado, "Introduzca la fecha de llegada: ");

                if (fechaSalida.anterior(fechaLlegada))
                    fechaCorrecta = true;
                else {
                    System.out.println("Llegada debe ser posterior a salida.");
                    fechaCorrecta = false;
                }
            } while (!fechaCorrecta);

            //Información de ID
            String porteID;
            do {
               porteID = generarID(rand);
                for (int i = 0; i < portes.getOcupacion(); i++) {
                   if (porteID.equals(portes.getPorte(i + 1).getID())) envioCorrecto = true;
                }
            } while (envioCorrecto);

            double precio;
            precio = Utilidades.leerNumero(teclado, "Ingrese precio del porte:", 0, (double) 999);
           Porte porteNuevo = new Porte(porteID, nave, origen, muelleOrigen,fechaSalida, destino, muelleDestino, fechaLlegada, precio);

            System.out.println("Porte " + porteID + " creado correctamente");
            portes.insertarPorte(porteNuevo);
            return porteNuevo;
        }
}