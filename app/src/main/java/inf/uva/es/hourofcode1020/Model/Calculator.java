package inf.uva.es.hourofcode1020.Model;

import java.text.DecimalFormat;

/**
 * Clase usada para el taller de Android impartido en la Escuela de Ingenieria Informatica de Valladolid, como parte del evento Hour Of Code del curso 19/20.
 *
 * @author Ivan Gonzalez Rincon.
 * @author Manuel Mendez Calvo.
 * @author David.
 */
public class Calculator {

    private double a;
    private double b;
    private double result;
    private DecimalFormat decimalFormat;
    private String format = "#.######";

    /**
     * Constructor sin parametros que inicia nuestra Calculadora.
     */
    public Calculator() {
        a = Double.NaN;
        b = 0;
        result = Double.NaN;
        decimalFormat = new DecimalFormat(format);

    }

    /**
     * Funcion para obtener el primer parametro de la Calculadora.
     *
     * @return Parametro a.
     */
    public double getA() {
        return a;
    }

    /**
     * Metodo para asignar un valor al primer parametro de la Calculadora.
     *
     * @param a Numero decimal para que haga las veces del primer parametro de la Calculadora.
     */
    public void setA(double a) {
        this.a = a;
    }

    /**
     * Funcion para obtener el segundo parametro de la Calculadora.
     *
     * @return Parametro b.
     */
    public double getB() {
        return b;
    }

    /**
     * Metodo para asignar un valor al segundo parametro de la Calculadora.
     *
     * @param b Numero decimal para que haga las veces del segundo parametro de la Calculadora.
     */
    public void setB(double b) {
        this.b = b;
    }


    /**
     * Funcion para obtener el resultado de la Calculadora.
     *
     * @return Resultado de la calculadora.
     */
    public double getResult() {
        return result;
    }

    /**
     * Metodo para asignar un valor al resultado de la Calculadora.
     *
     * @param result Numero decimal para que haga las veces del resultado de la Calculadora.
     */
    public void setResult(double result) {
        this.result = result;
    }

    /**
     * Funcion para obtener el formateador de los numeros de los que hara uso la Calculadora.
     *
     * @return El formateador por defecto de la Calculadora.
     */
    public DecimalFormat getDecimalFormat() {
        return decimalFormat;
    }

    /**
     * Metodo para modificar el formateador de los numeros de los que hara uso la Calculadora.
     *
     * @param decimalFormat El nuevo formateador del que hara uso la Calculadora.
     */
    public void setDecimalFormat(DecimalFormat decimalFormat) {
        this.decimalFormat = decimalFormat;
    }

    /**
     * Funcion para obtener el formato por defecto que usa la Calculadora.
     *
     * @return Cadena de texto asociada al formato de decimales que usa la Calculadora.
     */
    public String getFormat() {
        return format;
    }

    /**
     * Metodo para modificar el formato por defecto que usa la Calculadora.
     *
     * @param format Cadena de texto a asociar al formateador, para realizar el formato deseado de los numeros de la Calculadora
     */
    public void setFormat(String format) {
        this.format = format;
    }
}
