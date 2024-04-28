/**
 * Clase Monstruo
 */
public class Monstruo {
    private int vida;
    private final int ataque;
    private final int defensa;
    private final String nombre;

    /**
     * Constructor clase Monstruo
     * @param nombre
     * @param vida
     * @param ataque
     * @param defensa
     */
    public Monstruo(String nombre, int vida, int ataque, int defensa) {
        this.nombre = nombre;
        this.vida = vida;
        this.ataque = ataque;
        this.defensa = defensa;

    }

    /**
     * Método getVida
     * @return int vida
     */
    public int getVida() {
        return vida;
    }

    /**
     * Método getAtaque
     * @return int ataque
     */
    public int getAtaque() {
        return ataque;
    }

    /**
     * Método getDefensa
     * @return int defensa
     */
    public int getDefensa() {
        return defensa;
    }

    /**
     * Método getNombre
     * @return String nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Método recibirDanyo para calcular la vida restante
     * TODO actualizar la vida restante del monstruo despues de un ataque, siempre que el
     * valor de ataque sea positivo
     * @param ataque
     */
    public void recibirDanyo(int ataque) {
        if (ataque > 0){
            vida = vida - ataque;
        }
    }

    /**
     * Método sobreescrito para devolver la información de un monstruo
     * TODO Método para devolver un String con la información del monstruo en el formato
     *  descrito en la memoria de la práctica P.e: "[ Trasgo (V: 20, A: 5, D: 2) ]"
     * @return
     */
    @Override
    public String toString() {
        return "[ " + nombre + "(V: " + vida + ", A: " + ataque + ", D: " + defensa + ") ]";
    }

    /**
     * Método que sobreescribe el comportamiento de equals
     *  TODO Método para comparar si el objeto pasado como parámetro es igual a este,
     *      hay que comparar los parámetros internos del objeto (nombre, vida, ataque, defensa)
     * @param obj
     * @return True en caso de ser igual, false en otro caso
     */
    @Override
    public boolean equals(Object obj) {
        boolean resultado = false;
        if (obj.equals(vida) && obj.equals(nombre) && obj.equals(ataque) && obj.equals(defensa)){
            resultado = true;
        }
        return resultado;
    }
}
