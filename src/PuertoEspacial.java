/**
 * Esta clase representa un Puerto Espacial con sus respectivas coordenadas y atributos.
 * Se utiliza para definir la posición y características de un puerto en el espacio.
 *
 * @author Daniel Terreros Encinar
 * @version 1.0
 */
public class PuertoEspacial {


    private String nombre;      // Nombre del puerto espacial
    private String codigo;      // Código del puerto espacial
    private double radio;       // Distancia al Sol en unidades astronómicas (UA)
    private double azimut;      // Ángulo desde el eje positivo x hasta la proyección del punto en el plano xy
    private double polar;       // Ángulo desde el eje positivo z hasta el puerto
    private int numMuelles;     // Número de muelles en el puerto

    /**
     * Constructor de la clase PuertoEspacial.
     *
     * @param nombre Nombre del puerto espacial
     * @param codigo Código del puerto espacial
     * @param radio Distancia al Sol en unidades astronómicas (UA)
     * @param azimut Ángulo desde el eje positivo x hasta la proyección del punto en el plano xy
     * @param polar Ángulo desde el eje positivo z hasta el puerto
     * @param numMuelles Número de muelles en el puerto
     */
    public PuertoEspacial(String nombre, String codigo, double radio, double azimut, double polar, int numMuelles) {
        this.nombre = nombre;
        this.codigo = codigo;
        this.radio = radio;
        this.azimut = azimut;
        this.polar = polar;
        this.numMuelles = numMuelles;
    }
    // Getters para acceder a los atributos privados de la clase
    public String getNombre() {
        return nombre;
    }
    public String getCodigo() {
        return codigo;
    }
    public double getRadio() {
        return radio;
    }
    public double getAzimut() {
        return azimut;
    }

    public double getPolar() {
        return polar;
    }
    public int getMuelles() {
        return numMuelles;
    }

    /**
     * TODO: Método para calcular la distancia entre el puerto espacial que recibe el mensaje y el puerto
     *  espacial "destino" siguiendo las ecuaciones del enunciado (Las formulas se encuentran en el enunciado)
     * Calcula la distancia entre este puerto espacial y otro puerto espacial "destino"
     * utilizando las coordenadas esféricas de ambos puertos y las ecuaciones dadas.
     *
     *  @param destino Puerto espacial al que se calcula la distancia
     *  @return La distancia euclidiana entre ambos puertos espaciales
     */
    public double distancia(PuertoEspacial destino) {
        // TODO: Para calcular la distancia entre dos Puertos Espaciales, se transforman sus coordenadas esféricas a cartesianas
        double x1 = radio * Math.sin(polar) * Math.cos(azimut);
        double y1 = radio * Math.sin(polar) * Math.sin(azimut);
        double z1 = radio * Math.cos(polar);
        double x2 = destino.radio * Math.sin(destino.polar) * Math.cos(destino.azimut);
        double y2 = destino.radio * Math.sin(destino.polar) * Math.sin(destino.azimut);
        double z2 = destino.radio * Math.cos(destino.polar);
        // TODO: Una vez se tienen las coordenadas cartesianas, basta con calcular la distancia euclídea entre ellas:
        double distancia= Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2) + Math.pow(z2 - z1, 2));
        return distancia;
    }

    /**
     * TODO: Método que crea un String con los datos de un puerto espacial con el siguiente formato:
     * @return ejemplo -> "Gaia Galactic Terminal(GGT), en (1.0 90.0 0.0), con 8 muelles" (Radio, Azimut, Polar)
     */
    public String toString() {

        return ""+nombre+" ("+codigo+"), en ("+radio+" "+azimut+" "+polar+"), con "+numMuelles+" muelles";
    }

    /**
     * TODO: Método que crea un String con los datos de un aeropuerto con el siguiente formato:
     * @return ejemplo -> "Gaia Galactic Terminal (GGT)"
     */
    public String toStringSimple() {

        return ""+nombre+" ("+codigo+")";
    }
    public String obtenerIniciales() {

        if (nombre == null || nombre.isEmpty()) {
            return "";
        }
        StringBuilder iniciales = new StringBuilder();
        String[] palabras = nombre.split("\\s+");
        for (String palabra : palabras) {
            if (!palabra.isEmpty()) {
                iniciales.append(palabra.charAt(0));
            }
        }
        return iniciales.toString().toUpperCase();
    }
}
