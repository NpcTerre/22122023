import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Clase que representa una lista de naves.
 * Permite almacenar y gestionar naves espaciales con diversas funcionalidades.
 * @author Daniel Terreros Encinar
 * @version     1.0
 */
public class ListaNaves {
    private Nave[] naves;

    /**
     * TODO: Constructor de la clase para inicializar la lista a una capacidad determinada
     *
     * @param capacidad Capacidad inicial de la lista de naves.
     */
    public ListaNaves(int capacidad) {
        this.naves=new Nave[capacidad];
    }
    // TODO: Devuelve el número de naves que hay en la lista
    /**
     * Devuelve el número de naves que hay en la lista.
     *
     * @return La cantidad de naves en la lista.
     */
    public int getOcupacion() {
        int ocupacion=0;
        for(int i=0;i<naves.length;i++){
            if(naves[i]!=null){
                ocupacion++;
            }
        }
        return ocupacion;
    }
    // TODO: ¿Está llena la lista de naves?
    /**
     * ¿Está llena la lista de naves?
     *
     * @return true si la lista de naves está llena, false si aún hay espacio disponible.
     */
    public boolean estaLlena() {
        return getOcupacion()== naves.length;
    }
	// TODO: Devuelve nave dado un indice
    /**
     * Devuelve la nave en la posición especificada.
     *
     * @param posicion Índice de la nave a recuperar.
     * @return La nave en la posición especificada.
     */
    public Nave getNave(int posicion) {
        return naves[posicion];
    }

    /**
     * TODO: insertamos una nueva nave en la lista
     * @param nave La nave a insertar.
     * @return true si se añade correctamente, false si la lista está llena o hay un error.
     */

    public boolean insertarNave(Nave nave) {

        if (estaLlena()) {
            return false;
        }
        for (int i = 0; i < naves.length; i++) {
            if (naves[i] == null) {
                naves[i] = nave;
                return true;
            }
        }
        return false;
    }
    /**
     * TODO: Buscamos la nave a partir de la matricula pasada como parámetro
     * @param matricula Matrícula de la nave a buscar.
     * @return La nave encontrada o null si no existe.
     */
    public Nave buscarNave(String matricula) {
        for (Nave nave : naves) {
            if (nave != null && nave.getMatricula().equals(matricula)) {
                return nave;
            }
        }
        return null;
    }
    // TODO: Muestra por pantalla las naves de la lista con el formato indicado en el enunciado
    /**
     * Muestra por pantalla las naves de la lista con el formato especificado en el enunciado.
     */
    public void mostrarNaves() {
        for(int i=0;i<naves.length;i++){
            System.out.println(naves[i].toString());
        }
    }



    /**
     * TODO: Permite seleccionar una nave existente a partir de su matrícula, y comprueba si dispone de un alcance
     *  mayor o igual que el pasado como argumento, usando el mensaje pasado como argumento para la solicitud y
     *  siguiendo el orden y los textos mostrados en el enunciado.
     *  La función solicita repetidamente la matrícula de la nave hasta que se introduzca una con alcance suficiente
     * @param teclado Scanner para entrada de datos.
     * @param mensaje Mensaje para la solicitud de la matrícula.
     * @param alcance Alcance mínimo requerido.
     * @return La nave seleccionada que cumple con el alcance especificado.
     */
    public Nave seleccionarNave(Scanner teclado, String mensaje, double alcance) {
        System.out.println(mensaje);
        Nave nave =naves[teclado.nextInt()];
        return nave;
    }


    /**
     * TODO: Genera un fichero CSV con la lista de Naves, SOBREESCRIBIÉNDOLO
     * @param nombre Nombre del archivo CSV a generar.
     * @return true si la operación de escritura fue exitosa, false si hubo algún error.
     */
    public boolean escribirNavesCsv(String nombre) {
        PrintWriter pw = null;
        try {
            pw=new PrintWriter(new FileWriter(nombre,true));
            for(int i=0; i< naves.length;i++){
                pw.println(naves[i].toString());
            }
            return true;
        } catch (Exception e) {
            System.out.println("Exception: "+e.getMessage());
            return false;
        } finally {
            if(pw!=null){
                pw.close();
            }

        }
    }


    /**
     * TODO: Genera una lista de naves a partir del fichero CSV, usando el argumento como capacidad máxima de la lista
     * @param fichero   Nombre del archivo CSV que contiene los datos de las naves.
     * @param capacidad Capacidad máxima de la lista de naves.
     * @return Una ListaNaves con las naves leídas del archivo CSV.
     */
    public static ListaNaves leerNavesCsv(String fichero, int capacidad) {
        ListaNaves listaNaves = new ListaNaves(capacidad);

        try {
            File archivo = new File(fichero);
            Scanner scanner = new Scanner(archivo);

            while (scanner.hasNextLine()) {
                String linea = scanner.nextLine();
                String[] valores = linea.split(";");

                if (valores.length == 6) {
                    String marca = valores[0];
                    String modelo = valores[1];
                    String matricula = valores[2];
                    int filas = Integer.parseInt(valores[3]);
                    int columnas = Integer.parseInt(valores[4]);
                    double alcance = Double.parseDouble(valores[5]);

                    Nave nuevaNave = new Nave(marca, modelo, matricula, filas, columnas, alcance);
                    listaNaves.insertarNave(nuevaNave);
                } else {
                    System.out.println("Error en el formato de la línea: " + String.join(",", valores));
                }
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Archivo no encontrado: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Error en el formato de los datos numéricos: " + e.getMessage());
        }

        return listaNaves;
    }
}
