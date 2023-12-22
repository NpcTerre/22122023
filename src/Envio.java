import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;

/**
 * Esta clase representa un envío de un paquete.
 * Proporciona los atributos y métodos para gestionar información sobre el envío,
 * incluyendo el localizador, el cliente asociado, la ubicación en un porte, y el precio.
 * Además, permite generar facturas y obtener detalles específicos sobre el envío.
 *
 * @author Daniel Terreros Encinar
 * @version     1.0
 */
public class Envio {

    private String localizador;
    private Porte porte;
    private Cliente cliente;
    private int fila;
    private int columna;
    private double precio;

    /**
     * Constructor para la creación de un nuevo envío.
     *
     * @param localizador Identificador único del envío
     * @param porte Puerto espacial asociado al envío
     * @param cliente Cliente que envía el paquete
     * @param fila Fila de la ubicación del envío
     * @param columna Columna de la ubicación del envío
     * @param precio Precio del envío
     */
    public Envio(String localizador, Porte porte, Cliente cliente, int fila, int columna, double precio) {
        this.localizador = localizador;
        this.porte = porte;
        this.cliente = cliente;
        this.fila = fila;
        this.columna = columna;
        this.precio = precio;
    }
    /**
     * Getter para obtener el localizador del envío.
     *
     * @return El localizador del envío
     */
    public String getLocalizador() {
        return localizador;
    }
    /**
     * Getter para obtener el porte asociado al envío.
     *
     * @return El porte asociado al envío
     */
    public Porte getPorte() {
        return porte;
    }
    /**
     * Getter para obtener el cliente asociado al envío.
     *
     * @return El cliente asociado al envío
     */
    public Cliente getCliente() {
        return cliente;
    }
    /**
     * Método getter para obtener la fila del envío en el sistema de ubicación.
     *
     * @return La fila del envío
     */
    public int getFila() {
        return fila;
    }
    /**
     * Método getter para obtener la columna del envío en el sistema de ubicación.
     *
     * @return La columna del envío
     */
    public int getColumna() {
        return columna;
    }
    // TODO: Ejemplos: "1A" para el hueco con fila 1 y columna 1, "3D" para el hueco con fila 3 y columna 4
    /**
     * Método para obtener el hueco del envío, representado como una combinación de fila y columna.
     *
     * @return El hueco del envío
     */
    public String getHueco() {
        return String.valueOf(fila)+""+'A' + (columna - 1);
    }

    /**
     * Getter para obtener el precio del envío.
     *
     * @return El precio del envío
     */
    public double getPrecio() {
        return precio;
    }
    //TODO: Texto que debe generar: Envío PM1111AAAABBBBC para Porte PM0066 de GGT M5 (01/01/2023 08:15:00) a CID M1 (01/01/2024 11:00:05) en hueco 6C por 13424,56 SSD
    public String toString() {
        return "Envío "+localizador+" para Porte "+porte.getID()+" de " +porte.getOrigen().getCodigo()+" ("
                +porte.getSalida().toString()+") a "+porte.getDestino().getCodigo() +" ("+porte.getLlegada().toString()
                + ") en hueco "+getHueco()+" por "+precio+" SSD ";
    }
    // TODO: Cancela este envío, eliminándolo de la lista de envíos del porte y del cliente correspondiente
    /**
     * Método para cancelar el envío, eliminándolo de las listas del porte y del cliente correspondiente.
     *
     * @return true si se cancela correctamente, false si hay un error
     */
    public boolean cancelar() {
        return porte.desocuparHueco(localizador);
    }

    /**
     * TODO: Método para imprimir la información de este envío en un fichero que respecta el formato descrito en el
     *  enunciado
     * @param fichero
     * @return Devuelve la información con el siguiente formato como ejemplo ->
     *     -----------------------------------------------------
     *     --------- Factura del envío PM1111AAAABBBBC ---------
     *     -----------------------------------------------------
     *     Porte: PM0066
     *     Origen: Gaia Galactic Terminal (GGT) M5
     *     Destino: Cidonia (CID) M1
     *     Salida: 01/01/2023 08:15:00
     *     Llegada: 01/01/2024 11:00:05
     *     Cliente: Zapp Brannigan, zapp.brannigan@dop.gov
     *     Hueco: 6C
     *     Precio: 13424,56 SSD
     */
    /**
     * Método para generar una factura del envío y guardarla en un fichero específico.
     *
     * @param fichero Nombre del fichero donde se guardará la factura
     * @return true si se genera y guarda la factura correctamente, false si hay un error
     */
    public boolean generarFactura(String fichero) {
        PrintWriter pw = null;
        try {
            pw = new PrintWriter(new FileWriter(fichero,true));
            pw.println("-----------------------------------------------------");
            pw.println("--------- Factura del envío " + localizador + " ---------");
            pw.println("-----------------------------------------------------");
            pw.println("Porte: " + porte.getID());
            pw.println("Origen: " + porte.getOrigen().toStringSimple());
            pw.println("Destino: " + porte.getDestino().toStringSimple());
            pw.println("Salida: " + porte.getSalida().toString());
            pw.println("Llegada: " + porte.getLlegada().toString());
            pw.println("Cliente: " + cliente.toString());
            pw.println("Hueco: " + fila + columna);
            pw.println("Precio: " + precio + " SSD");
            return true;
        } catch (IOException e) {
            System.out.println("IOException: "+e.getMessage());
            return false;
        }
    }
    /**
     *	TODO: Genera un localizador de envío. Este consistirá en una cadena de 15 caracteres, de los cuales los seis
	 *   primeros será el ID del porte asociado y los 9 siguientes serán letras mayúsculas aleatorias. Ejemplo: PM0123ABCD
     *   NOTA: Usar el objeto rand pasado como argumento para la parte aleatoria.
     * @param rand    Objeto Random para generar letras aleatorias
     * @param idPorte ID del porte asociado al envío
     * @return El localizador generado para el envío
     */
    public static String generarLocalizador(Random rand, String idPorte) {
        StringBuilder localizador = new StringBuilder(idPorte);
        for (int i = 0; i < 9; i++) {
            int letrasMayusculas = rand.nextInt(26);
            localizador.append((char)letrasMayusculas+'A');
        }
        return localizador.toString();
    }


    /**
     * TODO: Método para crear un nuevo envío para un porte y cliente específico, pidiendo por teclado los datos
     *  necesarios al usuario en el orden y con los textos indicados en los ejemplos de ejecución del enunciado
     *  La función solicita repetidamente los parámetros hasta que sean correctos
     * @param teclado   Scanner para entrada por teclado
     * @param rand      Objeto Random para generar letras aleatorias del localizador
     * @param porte     Porte asociado al envío
     * @param cliente   Cliente asociado al envío
     * @return Envio para el porte y cliente especificados
     */

    public static Envio altaEnvio(Scanner teclado, Random rand, Porte porte, Cliente cliente){
        String localizador;
        int fila,columna;
        double precio;
        localizador=generarLocalizador(rand, porte.getID());
        do{
            fila= Utilidades.leerNumero(teclado,"Fila del hueco: ",1,porte.getNave().getFilas());
            columna=Utilidades.leerNumero(teclado,"Columna del hueco: ",1,porte.getNave().getColumnas());
        }while (porte.huecoOcupado(fila,columna));
        precio=Utilidades.leerNumero(teclado,"Precio del envio: ",0.001,Double.MAX_VALUE);
        return new Envio(localizador,porte,cliente,fila,columna,precio);
    }


}