import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Clase que gestiona una lista de puertos espaciales.
 * Permite almacenar y gestionar información sobre los puertos espaciales con diversas funcionalidades.
 * @author Daniel Terreros Encinar
 * @version     1.0
 */
public class ListaPuertosEspaciales {
    private PuertoEspacial[] lista;

    /**
     * TODO: Constructor de la clase para inicializar la lista a una capacidad determinada
     *
     * @param capacidad Capacidad máxima de la lista de puertos espaciales.
     */
    public ListaPuertosEspaciales(int capacidad) {
        this.lista= new PuertoEspacial[capacidad];
		
    }
    // TODO: Devuelve el número de puertos espaciales que hay en la lista
    /**
     * Devuelve el número de puertos espaciales que hay en la lista.
     *
     * @return Número de puertos espaciales almacenados en la lista.
     */
    public int getOcupacion() {
        int posicion;
        for (posicion = 0; lista[posicion] != null; posicion++) {
            // No necesitamos realizar ninguna operación aquí, el bucle incrementa posicion automáticamente
        }
        return posicion;
    }
    // TODO: ¿Está llena la lista?
    /**
     * ¿Está llena la lista de puertos espaciales?
     *
     * @return true si la lista está llena, false si hay espacio disponible.
     */
    public boolean estaLlena() {
        return lista.length==getOcupacion();
    }
	// TODO: Devuelve un puerto espacial dado un indice

    /**
     * Devuelve un puerto espacial dado un índice.
     *
     * @param i Índice del puerto espacial en la lista.
     * @return PuertoEspacial en la posición indicada por el índice.
     */
    public PuertoEspacial getPuertoEspacial(int i) {
        return lista[i];
    }

    /**
     * TODO: insertamos un Puerto espacial nuevo en la lista
     * @param puertoEspacial Puerto espacial que se quiere añadir a la lista.
     * @return true si se añade correctamente, false si la lista está llena o si hay un error.
     */

    public boolean insertarPuertoEspacial(PuertoEspacial puertoEspacial) {
        if (estaLlena()) {
            return false;
        }
        for (int i = 0; i < lista.length; i++) {
            if (lista[i] == null) {
                lista[i] = puertoEspacial;
                return true;
            }
        }
        return false;
    }

    /**
     * TODO: Buscamos un Puerto espacial a partir del codigo pasado como parámetro
     * @param codigo Código del puerto espacial que se quiere buscar.
     * @return Puerto espacial que se encuentra con el código indicado, o null si no existe.
     */
    public PuertoEspacial buscarPuertoEspacial(String codigo) {
        for (PuertoEspacial puertoEspacial : lista) {
            if (puertoEspacial != null && puertoEspacial.getCodigo().equals(codigo)) {
                return puertoEspacial;
            }
        }
        return null;
    }

    /**
     * TODO: Permite seleccionar un puerto espacial existente a partir de su código, usando el mensaje pasado como
     *  argumento para la solicitud y siguiendo el orden y los textos mostrados en el enunciado.
     *  La función solicita repetidamente el código hasta que se introduzca uno correcto
     * @param teclado Objeto Scanner para la entrada de texto.
     * @param mensaje Mensaje a mostrar solicitando el código del puerto espacial.
     * @return El puerto espacial correspondiente al código introducido.
     */
    public PuertoEspacial seleccionarPuertoEspacial(Scanner teclado, String mensaje) {
        System.out.println(mensaje);
        String respuesta= teclado.nextLine();
        PuertoEspacial puerto= null;
        for( int i =0; i<lista.length;i++){
            if( lista[i]!=null){
                if(lista[i].getCodigo().equals(respuesta)){
                   puerto= lista[i];
                }
            }
        }
        return puerto;

    }

    /**
     * TODO: Genera un fichero CSV con la lista de puertos espaciales, SOBREESCRIBIENDOLO
     * @param nombre Nombre del fichero CSV a generar.
     * @return true si se escribe correctamente, false si hay un error.
     */
    public boolean escribirPuertosEspacialesCsv(String nombre) {
        PrintWriter pw = null;
        try {
            pw = new PrintWriter(new FileWriter(nombre, false));
            for (int i = 0; i < lista.length; i++) {
                pw.println(lista[i].toString());
            }
            return true;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return false;
        } finally {
            if (pw != null) {
                pw.close();
            }
        }
    }



    /**
     * TODO: Genera una lista de PuertosEspaciales a partir del fichero CSV, usando el argumento como capacidad máxima
     *  de la lista
     * @param fichero   Ruta del fichero CSV que contiene los datos de los puertos espaciales.
     * @param capacidad Capacidad máxima de la lista de puertos espaciales.
     * @return Lista de puertos espaciales creada a partir del archivo CSV.
     */
    public static ListaPuertosEspaciales leerPuertosEspacialesCsv(String fichero, int capacidad) {
        ListaPuertosEspaciales listaPuertosEspaciales = new ListaPuertosEspaciales(capacidad);
        Scanner sc = null;

        try {
            sc = new Scanner(new FileReader(fichero));

            while (sc.hasNext()) {
                String[] valores = sc.nextLine().split(";");

                if (valores.length == 6) { // Asumiendo que hay 6 valores por línea
                    String nombre = valores[0];
                    String codigo = valores[1];
                    double radio = Double.parseDouble(valores[2]);
                    double azimutm = Double.parseDouble(valores[3]);
                    double polar = Double.parseDouble(valores[4]);
                    int numMuelles = Integer.parseInt(valores[5]);

                    listaPuertosEspaciales.insertarPuertoEspacial(new PuertoEspacial(nombre, codigo, radio, azimutm, polar, numMuelles));
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException ex) {
            System.out.println("Error de conversión o formato: " + ex.getMessage());
        } finally {
            if (sc != null) {
                sc.close();
            }
        }

        return listaPuertosEspaciales;
    }

}
