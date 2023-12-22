import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.io.File;

/**
 * Clase que gestiona una lista de portes espaciales.
 * Permite almacenar y gestionar portes entre puertos espaciales con diversas funcionalidades.
 * @author Daniel Terreros Encinar
 * @version     1.0
 */
public class ListaPortes {
    private Porte[] portes;
    /**
     * TODO: Constructor de la clase para inicializar la lista a una capacidad determinada
     *
     * @param capacidad Capacidad máxima de la lista de portes.
     */
    public ListaPortes(int capacidad) {
        this.portes=new Porte[capacidad];
    }
    // TODO: Devuelve el número de portes que hay en la lista
    /**
     * Devuelve el número de portes que hay en la lista.
     *
     * @return La cantidad de portes presentes en la lista.
     */
    public int getOcupacion() {
        int ocupacion=0;
        for(int i=0;i<portes.length;i++){
            if(portes[i]!=null){
                ocupacion++;
            }
        }
        return ocupacion;
    }
    // TODO: ¿Está llena la lista?
    /**
     * Verifica si la lista de portes está llena.
     *
     * @return true si la lista está llena, false en caso contrario.
     */
    public boolean estaLlena() {
        return portes.length==getOcupacion();
    }

	//TODO: devuelve un porte dado un indice

    /**
     * Obtiene un porte de la lista según su índice.
     *
     * @param i Índice del porte en la lista.
     * @return El porte en la posición indicada.
     */
    public Porte getPorte(int i) {
        return portes[i];
    }


    /**
     * TODO: Devuelve true si puede insertar el porte
     * @param porte El porte a insertar.
     * @return true si se insertó correctamente, false si la lista está llena o se produjo un error.
     */
    public boolean insertarPorte(Porte porte) {
        if (estaLlena()) {
            return false;
        }
        for (int i = 0; i < portes.length; i++) {
            if (portes[i] == null) {
                portes[i] = porte;
                return true;
            }
        }
        return false;
    }


    /**
     * TODO: Devuelve el objeto Porte que tenga el identificador igual al parámetro id
     * @param id El identificador del porte a buscar.
     * @return El objeto Porte que coincide con el identificador dado, o null si no existe.
     */
    public Porte buscarPorte(String id) {
        for (Porte porte : portes) {
            if (porte != null && porte.getID().equals(id)) {
                return porte;
            }
        }
        return null;
    }

    /**
     * TODO: Devuelve un nuevo objeto ListaPortes conteniendo los Portes que vayan de un puerto espacial a otro
     *  en una determinada fecha
     * @param codigoOrigen  Código del puerto de origen.
     * @param codigoDestino Código del puerto de destino.
     * @param fecha         Fecha en la que se deben realizar los portes.
     * @return Una lista de portes que cumplen con los criterios dados.
     */
    public ListaPortes buscarPortes(String codigoOrigen, String codigoDestino, Fecha fecha) {
        ListaPortes listaPortes = new ListaPortes(portes.length);
        for (Porte p : portes) {
            if (p != null &&
                    p.getOrigen().getCodigo().equals(codigoOrigen) &&
                    p.getDestino().getCodigo().equals(codigoDestino) &&
                    p.getSalida().equals(fecha)) {

                listaPortes.insertarPorte(p);
            }
        }
        return listaPortes;
    }
    /**
     * TODO: Muestra por pantalla los Portes siguiendo el formato de los ejemplos del enunciado
     */
    public void listarPortes() {
        for (int i = 0; i < portes.length; i++) {
            System.out.println(portes[i].toString());
        }
    }


    /**
     * TODO: Permite seleccionar un Porte existente a partir de su ID, usando el mensaje pasado como argumento para
     *  la solicitud y siguiendo el orden y los textos mostrados en el enunciado, y usando la cadena cancelar para
     *  salir devolviendo null.
     *  La función solicita repetidamente hasta que se introduzca un ID correcto
     * @param teclado  Scanner para la entrada de datos.
     * @param mensaje  Mensaje para la solicitud del ID.
     * @param cancelar Cadena de texto para cancelar la selección.
     * @return El Porte seleccionado o null si se cancela la selección.
     */
    public Porte seleccionarPorte(Scanner teclado, String mensaje, String cancelar) {
        listarPortes();
        String id;
        do {
            id = Utilidades.leerCadena(teclado, mensaje);
        } while (!id.equals(cancelar));

        if (id.equals(cancelar)) {
            return null; // Devolver null si se ingresa la palabra clave para cancelar
        }
        return buscarPorte(id);
    }


    /**
     * TODO: Ha de escribir la lista de Portes en la ruta y nombre del fichero pasado como parámetro.
     *  Si existe el fichero, SE SOBREESCRIBE, si no existe se crea.
     * @param fichero Ruta y nombre del fichero donde se escribirán los Portes en formato CSV.
     * @return true si se escribe correctamente, false si hay algún error.
     */
    public boolean escribirPortesCsv(String fichero) {
        PrintWriter pw = null;
        try {
            pw = new PrintWriter(new FileWriter(fichero));

            if (portes != null) {
                for (int i = 0; i < portes.length; i++) {
                    if (portes[i] != null) {
                        pw.printf("%s;%s;%s;%d;%s;%s;%d;%s;%f\n",
                                portes[i].getID(),
                                portes[i].getNave().getMatricula(),
                                portes[i].getOrigen().getCodigo(),
                                portes[i].getMuelleOrigen(),
                                portes[i].getSalida().toString(),
                                portes[i].getDestino().getCodigo(),
                                portes[i].getMuelleDestino(),
                                portes[i].getLlegada().toString(),
                                portes[i].getPrecio()
                        );
                    }
                }
            }
            return true;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return false;
        } finally {
            if (pw != null){
                pw.close();
            }
        }
    }

    /**
     * TODO: Genera una lista de Portes a partir del fichero CSV, usando los límites especificados como argumentos para
     *  la capacidad de la lista
     * @param fichero           Ruta del fichero CSV que contiene los datos de los Portes.
     * @param capacidad         Capacidad máxima de la lista de Portes.
     * @param puertosEspaciales Lista de puertos espaciales para referencias.
     * @param naves             Lista de naves para referencias.
     * @return Una lista de portes generada a partir del fichero CSV.
     */
    public static ListaPortes leerPortesCsv(String fichero, int capacidad, ListaPuertosEspaciales puertosEspaciales, ListaNaves naves) {
        ListaPortes listaPortes = new ListaPortes(capacidad);
        try (Scanner scanner = new Scanner(new File(fichero))) {
            while (scanner.hasNextLine()) {
                String linea = scanner.nextLine();
                String[] campos = linea.split(";");
                if (campos.length == 9) {
                    String id = campos[0];
                    String matricula = campos[1];
                    String codOrigen = campos[2];
                    int muelleOrigen = Integer.parseInt(campos[3]);
                    String salida = campos[4];
                    String codDestino = campos[5];
                    int muelleDestino = Integer.parseInt(campos[6]);
                    String llegada = campos[7];
                    double precio = Double.parseDouble(campos[8]);

                    Porte porte = new Porte(id, naves.buscarNave(matricula), puertosEspaciales.buscarPuertoEspacial(codOrigen),
                            muelleOrigen, Fecha.fromString(salida), puertosEspaciales.buscarPuertoEspacial(codDestino),
                            muelleDestino, Fecha.fromString(llegada), precio);

                    listaPortes.insertarPorte(porte);
                } else {
                    System.out.println("Error en el formato de la línea: " + linea);
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
            return null;
        }
        return listaPortes;
    }

}
