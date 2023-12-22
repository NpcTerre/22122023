import java.util.Scanner;

/**
 * Clase que contiene métodos de utilidad para la lectura de datos desde la entrada estándar.
 * Esta clase proporciona métodos para leer números enteros, longs, decimales, letras, fechas y cadenas de texto,
 * asegurando que los valores ingresados estén dentro de los límites especificados.
 * Además, ofrece utilidades para imprimir mensajes y leer distintos tipos de datos desde el teclado.
 * Los métodos presentes en esta clase permiten interacciones de entrada de datos
 * que cumplen con restricciones de rango y validaciones.
 *
 * @author Daniel Terreros Encinar
 * @version     1.0
 */
public class Utilidades {

    /**
     * TODO: Solicita un número repetidamente hasta que se introduzca uno correcto (dentro de los límites)
     * @param teclado  Scanner utilizado para leer desde la entrada estándar.
     * @param mensaje  Mensaje que solicita al usuario que ingrese el número.
     * @param minimo   Límite inferior aceptable (inclusive).
     * @param maximo   Límite superior aceptable (inclusive).
     * @return El número entero válido introducido por el usuario.
     */
    public static int leerNumero(Scanner teclado, String mensaje, int minimo, int maximo) {
        int numero;
        do {
            System.out.println(mensaje);
            numero = teclado.nextInt();
        } while (numero < minimo ||numero > maximo );
        return numero;
    }

    /**
     * TODO: Solicita un número repetidamente hasta que se introduzca uno correcto (dentro de los límites)
     * @param teclado  Scanner utilizado para leer desde la entrada estándar.
     * @param mensaje  Mensaje que solicita al usuario que ingrese el número.
     * @param minimo   Límite inferior aceptable (inclusive).
     * @param maximo   Límite superior aceptable (inclusive).
     * @return El número long válido introducido por el usuario.
     */
    public static long leerNumero(Scanner teclado, String mensaje, long minimo, long maximo) {
        long numero;
        do {
            System.out.println(mensaje);
            numero = teclado.nextInt();
        } while (numero < minimo ||numero > maximo );
        return numero;
    }

    /**
     * TODO: Solicita un número repetidamente hasta que se introduzca uno correcto (dentro de los límites)
     * @param teclado  Scanner utilizado para leer desde la entrada estándar.
     * @param mensaje  Mensaje que solicita al usuario que ingrese el número.
     * @param minimo   Límite inferior aceptable (inclusive).
     * @param maximo   Límite superior aceptable (inclusive).
     * @return El número double válido introducido por el usuario.
     */
    public static double leerNumero(Scanner teclado, String mensaje, double minimo, double maximo) {
        double numero;
        do {
            System.out.println(mensaje);
            numero = teclado.nextInt();
        } while (numero < minimo ||numero > maximo );
        return numero;

    }

    /**
     * TODO: TODO: Solicita una letra repetidamente hasta que se introduzca uno correcto (dentro de los límites)
     * @param teclado  Scanner utilizado para leer desde la entrada estándar.
     * @param mensaje  Mensaje que solicita al usuario que ingrese la letra.
     * @param minimo   Límite inferior aceptable (inclusive).
     * @param maximo   Límite superior aceptable (inclusive).
     * @return La letra válida introducida por el usuario.
     */
    public static char leerLetra(Scanner teclado, String mensaje, char minimo, char maximo) {
            char letra;
            boolean entradaInvalida;
            do {
                System.out.print(mensaje);
                letra = teclado.next().charAt(0); // Lee el primer carácter ingresado
                if (letra >= minimo && letra <= maximo) {
                    entradaInvalida = false;
                } else {
                    System.out.println("Introduce una letra válida entre " + minimo + " y " + maximo);
                    entradaInvalida = true;
                }
                teclado.nextLine();
            } while (entradaInvalida);
            return letra;
    }


    /**
     * TODO: Solicita una fecha repetidamente hasta que se introduzca una correcta
     * @param teclado  Scanner utilizado para leer desde la entrada estándar.
     * @param mensaje  Mensaje que solicita al usuario que ingrese la fecha.
     * @return La fecha válida introducida por el usuario.
     */
    public static Fecha leerFecha(Scanner teclado, String mensaje) {
        int dia;
        int mes;
        int anio;
        do {
            System.out.println(mensaje);
            System.out.print("Ingrese día: ");
            dia = teclado.nextInt();
            System.out.print("Ingrese mes: ");
            mes = teclado.nextInt();
            System.out.print("Ingrese año: ");
            anio = teclado.nextInt();

            if (!Fecha.comprobarFecha(dia, mes, anio)) {
                System.out.println("\tFecha introducida incorrecta");
            }
        } while (!Fecha.comprobarFecha(dia, mes, anio));

        return new Fecha(dia, mes, anio);
    }

    /**
     * TODO: Solicita una fecha y hora repetidamente hasta que se introduzcan unas correctas
     * @param teclado  Scanner utilizado para leer desde la entrada estándar.
     * @param mensaje  Mensaje que solicita al usuario que ingrese la fecha y hora.
     * @return La fecha y hora válidas introducidas por el usuario.
     */
    public static Fecha leerFechaHora(Scanner teclado, String mensaje) {
        int dia;
        int mes;
        int anio;
        int hora;
        int minuto;
        int segundo;
        do {
            System.out.println(mensaje);
            dia = leerNumero(teclado, "Ingrese día: ", 1, Fecha.DIAS_MES);
            mes = leerNumero(teclado, "Ingrese mes: ", 1, 12);
            anio = leerNumero(teclado, "Ingrese año: ", Fecha.PRIMER_ANIO, Fecha.ULTIMO_ANIO);
            hora = leerNumero(teclado, "Ingrese segundo: ", 1, Fecha.SEGUNDOS_MINUTO);
            minuto = leerNumero(teclado, "Ingrese minuto: ", 1, Fecha.MINUTOS_HORA);
            segundo = leerNumero(teclado, "Ingrese hora: ", 1, Fecha.HORAS_DIA);
        } while (!Fecha.comprobarFecha(dia, mes, anio) || !Fecha.comprobarHora(hora, minuto, segundo));

        return new Fecha(dia, mes, anio, hora, minuto, segundo);
    }

    /**
     * TODO: Imprime por pantalla el String pasado por parámetro
     * @param teclado  Scanner utilizado para leer desde la entrada estándar.
     * @param s        Mensaje que se mostrará al usuario.
     * @return La cadena de texto ingresada por el usuario.
     */
    public static String leerCadena(Scanner teclado, String s) {
        System.out.print(s);
        return teclado.next();
    }
}
