/**
 * Clase que representa una nave espacial con características específicas.
 * Se utiliza para definir las propiedades y detalles de una nave en el espacio.
 *
 * @author Daniel Terreros Encinar
 * @version     1.0
 */
public class Nave {
    private String marca;       // Marca de la nave
    private String modelo;      // Modelo de la nave
    private String matricula;   // Matrícula de identificación de la nave
    private int columnas;       // Número de columnas de contenedores en la nave
    private int filas;          // Número de filas de contenedores en la nave
    private double alcance;     // Alcance máximo de la nave en unidades astronómicas (UA)



    /**
     * Constructor of the class
     *
     * @param marca Marca de la nave
     * @param modelo Modelo de la nave
     * @param matricula Matrícula de identificación de la nave
     * @param columnas Número de columnas de contenedores en la nave
     * @param filas Número de filas de contenedores en la nave
     * @param alcance Alcance máximo de la nave en unidades astronómicas (UA)
     */
    public Nave(String marca, String modelo, String matricula, int columnas, int filas, double alcance) {
        this.marca = marca;
        this.modelo = modelo;
        this.matricula = matricula;
        this.columnas = columnas;
        this.filas = filas;
        this.alcance = alcance;
    }
    // Getters para acceder a los atributos privados de la clase
    public String getMarca() {
        return marca;
    }
    public String getModelo() {
        return modelo;
    }
    public String getMatricula() {
        return matricula;
    }
    public int getColumnas() {
        return columnas;
    }
    public int getFilas() {
        return filas;
    }
    public double getAlcance() {
        return alcance;
    }


    /**
     * TODO: Crea un String con los datos de una nave con el siguiente formato:
     * @return ejemplo del formato -> "Planet Express One (EP-245732X): 40 contenedores, hasta 1.57 UA"
     */
    public String toString() {

        return ""+marca+" "+modelo+" ("+matricula+"):"+filas*columnas+" contenedores, hasta"+ alcance+" UA";
    }


    /**
     * TODO: Crea un String con los datos de una nave con el siguiente formato:
     * @return ejemplo del formato -> "Planet Express One (EP-245732X)"
     */
    public String toStringSimple() {
        return ""+marca+" "+modelo+" ("+matricula+")";
    }
}
