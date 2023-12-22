import java.io.*;
import java.util.List;
import java.util.Scanner;

/**
 * Clase que representa una lista de envíos.
 * Permite almacenar y gestionar envíos en una lista con capacidad determinada.
 *
 * @author Daniel Terreros Encinar
 * @version 1.0
 */
public class ListaEnvios {
    private Envio[] envios;
    /**
     * TODO: Constructor de la clase para inicializar la lista a una capacidad determinada
     *
     * @param capacidad Capacidad máxima de la lista de envíos
     */
    public ListaEnvios(int capacidad) {
		envios=new Envio[capacidad];
		
    }
    // TODO: Devuelve el número de envíos que hay en la lista
    /**
     * Devuelve el número de envíos que hay en la lista.
     *
     * @return El número de envíos almacenados en la lista
     */
    public int getOcupacion() {
        int ocupacion=0;
        for(int i=0;i<envios.length;i++){
            if(envios[i]!=null){
                ocupacion++;
            }
        }
        return ocupacion;
    }
    // TODO: ¿Está llena la lista de envíos?
    /**
     * ¿Está llena la lista de envíos?
     *
     * @return true si la lista está llena, false si aún hay espacio para más envíos
     */
    public boolean estaLlena() {
        return getOcupacion() == envios.length;
    }
	//TODO: Devuelve el envio dado un indice
    /**
     * Obtiene el envío en la posición dada por el índice.
     *
     * @param i Índice del envío en la lista
     * @return El envío en la posición indicada por el índice
     */
    public Envio getEnvio(int i) {
        return envios[i];
    }

    /**
     * TODO: insertamos un nuevo envío en la lista
     * @param envio El envío a insertar en la lista
     * @return true en caso de que se añada correctamente, false en caso de lista llena o error
     */
    public boolean insertarEnvio(Envio envio) {
        // Verificar si la lista está llena
        if (estaLlena()) {
            return false; // Lista llena, no se puede añadir más envíos
        }
        // Buscar el primer espacio disponible en el arreglo
        for (int i = 0; i < envios.length; i++) {
            if (envios[i] == null) {
                    envios[i] = envio; // Insertar el envío en el primer espacio disponible
                return true; // Envío añadido correctamente
            }
        }
        // Si se llega aquí, hubo un error al agregar el envío (esto debería ser inalcanzable)
        return false;
    }

    /**
     * TODO: Buscamos el envio a partir del localizador pasado como parámetro
     * @param localizador El localizador del envío a buscar
     * @return el envio que encontramos o null si no existe
     */
    public Envio buscarEnvio(String localizador) {
        for (Envio envio : envios) {
            if (envio != null && envio.getLocalizador().equals(localizador)) {
                return envio; // Se encontró el envío con el localizador dado
            }
        }
        return null; // No se encontró ningún envío con el localizador dado
    }

    /**
     * TODO: Buscamos el envio a partir del idPorte, fila y columna pasados como parámetros
     * @param idPorte    ID del porte del envío a buscar
     * @param fila       Fila del envío a buscar
     * @param columna    Columna del envío a buscar
     * @return el envio que encontramos o null si no existe
     */
    public Envio buscarEnvio(String idPorte, int fila, int columna) {
        for (Envio envio : envios) {
            if (envio != null && envio.getPorte().getID().equals(idPorte)
                    && envio.getFila() == fila && envio.getColumna() == columna) {
                return envio; // Se encontró el envío con los parámetros dados
            }
        }
        return null; // No se encontró ningún envío con los parámetros dados
    }

    /**
     * TODO: Eliminamos un envio a través del localizador pasado por parámetro
     * @param localizador El localizador del envío a eliminar
     * @return True si se ha borrado correctamente, false en cualquier otro caso
     */
    public boolean eliminarEnvio(String localizador) {
        for (int i = 0; i < envios.length; i++) {
            if (envios[i] != null && envios[i].getLocalizador().equals(localizador)) {
                envios[i] = null; // Elimina el envío de la lista
                // Reorganiza la lista si es necesario (eliminar el espacio vacío)
                reorganizarLista(i);
                return true; // Devuelve true indicando que se eliminó el envío correctamente
            }
        }
        return false; // Devuelve false si no se encontró el envío con el localizador dado
    }
    /**
     * Reorganiza la lista de envíos eliminando el espacio vacío dejado por la eliminación de un envío.
     *
     * @param posicion La posición del envío eliminado
     */
    private void reorganizarLista(int posicion) {
        for (int i = posicion; i < envios.length - 1; i++) {
            envios[i] = envios[i + 1]; // Mueve los elementos hacia la izquierda para eliminar el espacio vacío
        }
        envios[envios.length - 1] = null; // Elimina la última referencia para evitar duplicados
    }

    /**
     * TODO: Muestra por pantalla los Envios de la lista, con el formato que aparece
     * en el enunciado
     */
    public void listarEnvios() {
        for (int i = 0; i < envios.length; i++) {
            System.out.println(envios[i].toString());
        }
    }

    /**
     * TODO: Permite seleccionar un Envio existente a partir de su localizador, usando el mensaje pasado como argumento
     *  para la solicitud y siguiendo el orden y los textos mostrados en el enunciado.
     *  La función solicita repetidamente hasta que se introduzca un localizador correcto
     * @param teclado Scanner para recibir la entrada del usuario
     * @param mensaje El mensaje que se muestra para solicitar el localizador
     * @return El envío seleccionado
     */
    public Envio seleccionarEnvio(Scanner teclado, String mensaje) {
        Envio envio;
        do {
            System.out.println(mensaje);
            String localizador = teclado.nextLine();
            envio = buscarEnvio(localizador);
            if (envio == null){
                System.out.println("Localizador incorrecto");
            }
        }while (envio == null);
        return envio;
    }
/*
o public Envio seleccionarEnvio(Scanner teclado, String mensaje) {
    boolean localizadorValido = false;
    Envio envio = null;

    while (!localizadorValido) {
        try {
            System.out.println(mensaje);
            String localizador = teclado.next();

            // Buscar el envío con el localizador dado
            envio = buscarEnvio(localizador);

            if (envio != null) {
                localizadorValido = true;
            } else {
                System.out.println("Localizador incorrecto. Inténtalo de nuevo.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Entrada inválida. Introduce un localizador válido.");
            teclado.next(); // Limpiar el buffer del scanner en caso de una entrada incorrecta
        }
    }

    return envio;
}

 */


    /**
     * TODO: Añade los Envios al final de un fichero CSV, SIN SOBREESCRIBIR la información
     * @param fichero El nombre del archivo CSV
     * @return true si se ha añadido correctamente, false si hay un error
     */
    public boolean aniadirEnviosCsv(String fichero) {
        PrintWriter pw = null;
        try {
            pw = new PrintWriter(new FileWriter(fichero,true));
            for (int i = 0; i < envios.length; i++) {
                pw.println(envios[i].toString());
            }
            return true;
        } catch (Exception e) {
            System.out.println("Exception: "+e.getMessage());
            return false;
        } finally {
            if (pw != null){
                pw.close();
            }
        }
    }
/*
public boolean aniadirEnviosCsv(String fichero) {
        FileWriter fW = null;
        boolean envioAniadido = false;
        try {
             fW = new FileWriter(fichero, true);
             for (int i = 0; i < ocupacion; i ++){
                 Envio infoEnvio = envios[i];
                 fW.write(infoEnvio.getLocalizador() + ";" + infoEnvio.getPorte().getID() + ";" + infoEnvio.getCliente().getEmail() + ";" + infoEnvio.getFila() + ";" + infoEnvio.getColumna() + ";" + infoEnvio.getPrecio());
                 if (i != ocupacion) fW.write("\n");
             }
        } catch (FileNotFoundException fileNotFoundException){
            System.out.println("Fichero " + fichero + " no encontrado.");
            envioAniadido = false;
        } catch (IOException ioException) {
            System.out.println("Error de escritura en fichero " + fichero + ".");
            envioAniadido = false;
        } finally {
            if(fW != null){
                try {
                    fW.close();
                } catch (IOException ioException) {
                    //throw new RuntimeException(ioException);
                }
            }
        }
        return envioAniadido;
    }
 */
    /**
     * TODO: Lee los Envios del fichero CSV y los añade a las listas de sus respectivos Portes y Clientes
     * @param ficheroEnvios  El archivo CSV que contiene los datos de envíos
     * @param portes La lista de portes donde se agregarán los envíos
     * @param clientes La lista de clientes donde se agregarán los envíos
     *
     */
/*
    public static void leerEnviosCsv(String ficheroEnvios, ListaPortes portes, ListaClientes clientes) {
        Scanner sc = null;
        Envio infoEnvios;
        Porte porte;
        Cliente cliente;
        try {
            sc = new Scanner(new FileReader(ficheroEnvios));
            do {
                String []arrayEnvio = sc.nextLine().split(";");
                porte = portes.buscarPorte(arrayEnvio[1]);
                cliente = clientes.buscarClienteEmail(arrayEnvio[2]);
                infoEnvios = new Envio(arrayEnvio[0], porte, cliente, Integer.parseInt(arrayEnvio[3]),
                        Integer.parseInt(arrayEnvio[4]), Double.parseDouble(arrayEnvio[5]));
                //porte.getListaEnvios().insertarEnvio(infoEnvios);
                cliente.aniadirEnvio(infoEnvios);
                porte.ocuparHueco(infoEnvios);

            }while (sc.hasNext()); // Mientras tenga líneas de texto para leer
        } catch (FileNotFoundException fileNotFoundException) {
            System.out.println("Fichero Billetes no encontrado.");
            System.out.println("Fichero " + ficheroEnvios + " no encontrado.");
        }
       finally {
            if (sc != null) {
                sc.close();
            }
        }
    }
*/
     public static void leerEnviosCsv(String ficheroEnvios, ListaPortes portes, ListaClientes clientes) {
        BufferedReader entrada = null;
        //comprobar que el portes.getOcupacion() está bien ponerlo así
        ListaEnvios listaEnvios = new ListaEnvios(portes.getOcupacion());
        try {
                entrada = new BufferedReader(new FileReader(ficheroEnvios));
                String linea;
                while ((linea = entrada.readLine())!=null) {
                    String [] dato = linea.split(";");
                    Envio envio = new Envio (dato[0], portes.buscarPorte(dato[1]),
                            clientes.buscarClienteEmail(dato[2]), Integer.parseInt(dato[3]),
                            Integer.parseInt(dato[4]), Double.parseDouble(dato[5]));
                    listaEnvios.insertarEnvio(envio);
                }
        } catch (FileNotFoundException ex) {
            System.out.println("Fichero envíos no encontrado.");
        } catch (IOException ex) {
            System.out.println("Error de lectura de fichero envíos.");
        } finally {
            try {
                if (entrada != null) {
                    entrada.close();
                }
            } catch (IOException ex) {
                System.out.println("Error de cierre de fichero envíos.");
            }
        }
    }

}
