import java.util.Scanner;

/**
 * Clase Personaje
 * @author Ainara Fernández Herreros
 * @author Jorge López Sosa
 * Grupo 24
 */
public class Personaje {
    private final String nombre;
    private int vida;
    private final int ataque, defensa, destreza;
    private final Item[] items;

    private final double maxPesoPorPersonaje;

    /**
     * Constructor de la clase para inicializar todos los atributos
     * @param nombre
     * @param vida
     * @param ataque
     * @param defensa
     * @param destreza
     * @param maxItemsPorPersonaje
     * @param maxPesoPorPersonaje
     */
    public Personaje(String nombre, int vida, int ataque, int defensa, int destreza, int maxItemsPorPersonaje, double maxPesoPorPersonaje) {
        this.nombre = nombre;
        this.vida = vida;
        this.ataque = ataque;
        this.defensa = defensa;
        this.destreza = destreza;
        this.maxPesoPorPersonaje = maxPesoPorPersonaje;
        items = new Item[maxItemsPorPersonaje];
    }

    /**
     * Metodo crearPersonaje que administra toda la generación de personajes
     * TODO El metodo tiene que ser capaz de recoger todas las características del personaje mediante preguntas y
     *  respuestas por pantalla y se debe controlar que los valores introducidos sean validos. Una vez recabados
     *  todos los datos del personaje generar un objeto con dichas características.
     * @param teclado
     * @return
     */
    public static Personaje crearPersonaje(Scanner teclado) {
        String nombre = Utilidades.leerCadena(teclado, "¿Cómo te llamas? ");
        System.out.println("¡Hola, " + nombre + "! Tienes 250 puntos para repartir entre vida, ataque, defensa y destreza.");
        int minimo = 50;
        int maximo = 247;
        int vida = Utilidades.leerNumero(teclado, "¿Cuánta vida quieres tener? (50-247): ", 50, 247);
        int puntosRestantes = 250 - vida;
        minimo = 1;
        int ataque = Utilidades.leerNumero(teclado, "¿Cuánto ataque quieres tener? (" + minimo + "-" + (puntosRestantes - 2) + "):", 1, puntosRestantes - 2);
        puntosRestantes -= ataque;

        int defensa = Utilidades.leerNumero(teclado, "¿Cuánta defensa quieres tener? (" + minimo + "-" + (puntosRestantes - 1) + "):", 1, puntosRestantes - 1);
        puntosRestantes -= defensa;

        int destreza = Utilidades.leerNumero(teclado, "¿Cuánta destreza quieres tener? (" + minimo + "-" + puntosRestantes+ "):", 1, puntosRestantes);

        int maxPesoPorPersonaje;
        if (ataque/2 < 1){
            maxPesoPorPersonaje = 1;
        } else {
            maxPesoPorPersonaje = (int) ataque/2;
        }

        int maxItemsPorPersonaje;
        if (destreza/4 < 1){
            maxItemsPorPersonaje = 1;
        } else {
            maxItemsPorPersonaje = destreza/4;
        }

        System.out.println();

        Personaje personaje = new Personaje(nombre, vida, ataque, defensa, destreza, maxPesoPorPersonaje, maxItemsPorPersonaje);
        return personaje;

    }

    /**
     * Método getNombre
     * @return String nombre
     */
    public String getNombre() {
        return nombre;
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
     * Método getDestreza
     * @return int destreza
     */
    public int getDestreza() {
        return destreza;
    }

    /**
     * Método getItems
     * @return Item[] items
     */
    public Item[] getItems() {
        return items;
    }

    /**
     * Método getItem para devolver un Item según un índice dado
     * TODO devolver null si el índice no es válido, y el item si el índice es correcto
     * @param indice
     * @return
     */
    public Item getItem(int indice) {
        Item resultado = null;
        while (items[indice] != null){
            resultado = items[indice];
        }
            return resultado;
    }

    /**
     * Método recibirDanyo para actualizar la vida de un personaje
     * TODO Si el daño no es positivo, no hacer nada. En caso contrario reducir la vida según el daño pasado
     * @param danyo
     */
    public void recibirDanyo(int danyo) {
        if (danyo < 0){
            vida = vida - danyo;
        }
    }

    /**
     * Método anyadirItem para incluir un item en la mochila del personaje
     * TODO Comprobar si el item es valido y si el peso max del personaje no se supera para poder incluir el item,
     *  en caso negativo ddevolver false, en caso de que se pueda incluir, añadir el item a la lista de items del
     *  personaje y devolver true
     * @param item
     * @return
     */
    public boolean anyadirItem(Item item) {
        boolean resultado = false;
        if (items.length < destreza / 4 && getPesoMochila() + item.getPeso() < maxPesoPorPersonaje){
            for (int i = 0; i < items.length; i++){
                if (items[i] == null){
                    items[i] = item;
                    resultado = true;
                }
            }
        }
        return resultado;
    }

    /**
     * Método sobreescrito para devolver la información de un personaje
     * TODO Método para devolver un String con la información del personaje en el formato
     *  descrito en la memoria de la práctica P.e: "{ Edgar (V: 20, A: 5, D: 2, X: 5) }"
     * @return
     */
    @Override
    public String toString() {
        return nombre + "(V: " + vida + ", A: " + ataque + ", D: " + defensa + ", X: " + destreza;
    }

    /**
     * Método getPesoMochila para obtener el peso total que carga en la mochila el personaje
     * TODO recorrer la lista de items para obtener el peso total de todos y devolverlo
     * @return
     */
    public double getPesoMochila() {
        double resultado = 0;
        for (int i = 0; i < items.length; i++){
            double pesoI = items[i].getPeso();
            resultado += pesoI;
        }
        return resultado;
    }

    /**
     * Método getValorMochila para obtener el valor total que lleva entre todos los items el personaje
     * TODO recorrer la lista de items para obtener el valor total de todos y devolverlo
     * @return
     */
    public double getValorMochila() {
        double resultado = 0;
        for (int i = 0; i < items.length; i++){
            resultado = items[i].getValor();
        }
        return resultado;
    }

    /**
     * Método infoMochila para obtener en formato String la información de la mochila
     * TODO recorrer toda la lista de items del personaje para ir añadiendo la información de los items según el
     *  formato mostrado en la memoria. P.e. "Mochila de Edgar:
     *                                        Espada Mágica Peso: 1.5, Valor: 100
     *                                        Armadura de Gromril Peso: 4, Valor: 300
     *                                        Peso total: 5.5 Kg
     *                                        Tu mochila vale 400 monedas"
     * @return
     */
    public String infoMochila() {
        String mensaje = "Mochila de " + nombre + ": " + "\n";
        for (int i = 0; i < items.length; i++){
            if (items[i] != null){
                mensaje += items[i].toString() + "\n";
            }
        }
        return mensaje;
    }
}
