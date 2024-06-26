import java.util.Scanner;

/**
 * Clase Sala
 * @author Ainara Fernández Herreros
 * @author Jorge López Sosa
 * Grupo 24
 */
public class Sala {
    private final String descripcion;
    private final Item[] items;
    private final Monstruo[] monstruos;
    private final Trampa[] trampas;

    private final int fila;
    private final int columna;
    private int numeroItems;
    private int numeroMonstruos;
    private int numeroTrampas;

    /**
     * Constructor de clase para inicializar los atributos de clase
     * @param descripcion
     * @param max_items
     * @param max_monstruos
     * @param maxTrampasPorSala
     * @param fila
     * @param columna
     */
    public Sala(String descripcion, int max_items, int max_monstruos, int maxTrampasPorSala, int fila, int columna) {
        this.descripcion = descripcion;
        items = new Item[max_items];
        monstruos = new Monstruo[max_monstruos];
        trampas = new Trampa[maxTrampasPorSala];
        this.fila = fila;
        this.columna = columna;
    }

    /**
     * Método agregarItem para incluir items en la sala
     * TODO comprobar si existe el objeto en la sala o si la lista de items no está ya llena en caso afirmativo
     *  devolver false. En caso de no existir incluirlo en la lista y devolver true
     * @param item
     * @return
     */
    public boolean agregarItem(Item item) {
        boolean resultado = true;
        for (int i = 0; i < numeroItems; i++) {
            if (items[i] == item) {
                resultado = false;
            }
        }
        if (resultado) {
            items[numeroItems] = item;
            numeroItems++;
        }
        return resultado;
    }

    /**
     * Método agregarMonstruo para incluir un monstruo en la sala
     * TODO comprobar si existe el monstruo en la sala o si la lista de monstruos no está ya llena en caso afirmativo
     *  devolver false. En caso de no existir incluirlo en la lista y devolver true
     * @param monstruo
     * @return
     */
    public boolean agregarMonstruo(Monstruo monstruo) {
        boolean resultado = true;
        for (int i = 0; i < numeroMonstruos; i++) {
            if (monstruos[i] == monstruo) {
                resultado = false;
            }
        }
        if (resultado) {
            monstruos[numeroMonstruos] = monstruo;
            numeroMonstruos++;
        }
        return resultado;
    }


    /**
     * Método agregarTrampa para incluir una trampa en la sala
     * TODO comprobar si existe la trampa en la sala o si la lista de trampas no está ya llena en caso afirmativo
     *  devolver false. En caso de no existir incluirlo en la lista y devolver true
     * @param trampa
     * @return
     */
    public boolean agregarTrampa(Trampa trampa) {
        boolean resultado = true;
        for (int i = 0; i < numeroTrampas; i++) {
            if (trampas[i] == trampa) {
                resultado = false;
            }
        }
        if (resultado) {
            trampas[numeroTrampas] = trampa;
            numeroTrampas++;
        }
        return resultado;
    }

    /**
     * Método getDescripcion
     * @return String descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Método hayMonstruos para comprobar si hay algún monstruo en la sala
     * TODO comprobar si hay algún monstruo en la lista
     * @return
     */
    public boolean hayMonstruos() {
        boolean resultado = false;
        for (int i = 0; i < monstruos.length && !resultado; i++){
            if (monstruos[i] != null){
                resultado = true;
            }
        }
        return resultado;
    }

    /**
     * Método seleccionarMonstruo para introducir desde pantalla el nombre de un monstruo
     * TODO Mostrar por pantalla todos los monstruos y luego solicitar que se introduzca el nombre del monstruo que se
     *  quiere seleccionar.
     * @param teclado
     * @return
     */
    public Monstruo seleccionarMonstruo(Scanner teclado) {
        /*if (hayMonstruos()){
            listarMonstruos();
            boolean encontrado = false;
            String nombre = "";
            while (!encontrado) {
                nombre = Utilidades.leerCadena(teclado, "Escribe el nombre del monstruo que quieres atacar: ");
                if (buscarMonstruo(nombre) != null) {
                    encontrado = true;
                }
            }
            return buscarMonstruo(nombre);
        }
        else return null;
        }*/
        if (hayMonstruos()){
            listarMonstruos();
            boolean encontrado = false;
            String nombre = "";
            while (!encontrado) {
                nombre = Utilidades.leerCadena(teclado, "Escribe el nombre del monstruo que quieres atacar: ");
                if (buscarMonstruo(nombre) != null) {
                    encontrado = true;
                }
            }
            return buscarMonstruo(nombre);
        }
        else return null;
    }
    private boolean contieneMonstruo(String nombreMonstruo){
        boolean resul = false;
        for (int i = 0; i < monstruos.length; i++){
            if (monstruos[i] != null){
                if (monstruos[i].getNombre().equalsIgnoreCase(nombreMonstruo)){
                    resul = true;
                }
            }
        }
        return resul;
    }
    /**
     * Método buscarMonstruo para buscar un monstruo dado el nombre del mismo
     * TODO devolver el monstruo según el nombre pasado como parámetro o devolver null si no se encuentra
     * @param nombreMonstruo
     * @return
     */
    public Monstruo buscarMonstruo(String nombreMonstruo) {
        Monstruo resultado = null;
        boolean encontrado = false;
        for (int i = 0; i < monstruos.length && !encontrado; i++){
            if (monstruos[i] != null){
                if (monstruos[i].getNombre().equalsIgnoreCase(nombreMonstruo)){
                    resultado = monstruos[i];
                    encontrado = true;
                }
            }
        }
        return resultado;
    }

    /**
     * Método listarMonstruos para mostrar por pantalla la información de los monstruos
     * TODO mostrar por pantalla la info de los monstruos utilizando los métodos implementados en la clase "monstruo"
     */
    private void listarMonstruos() {
        for (int i = 0; i < monstruos.length; i++){
            if (monstruos[i] != null){
                System.out.println(monstruos[i].toString());
            }
        }
    }

    /**
     * Método eliminarMonstruo para eliminar un monstruo de la lista segun un nombre dado
     * TODO buscar en la lista el monstruo segun el nombre pasado como parámetro y eliminarlo.
     * @param nombreMonstruo
     */
    public void eliminarMonstruo(String nombreMonstruo) {
        boolean encontrado = false;
        int contador = 0;
        int numMonstruos = monstruos.length;
        while (contador < numMonstruos && !encontrado){
            if (monstruos[contador].getNombre().equalsIgnoreCase(nombreMonstruo)){
                while (contador < numMonstruos - 1){
                    monstruos[contador] = monstruos[ contador + 1];
                    contador++;
                }
                monstruos[numMonstruos - 1] = null;
                encontrado = true;
            }
            contador++;
        }
    }

    /**
     * Método hayTrampas para saber si la sala dispone de alguna trampa
     * TODO mostrar si existe alguna trampa en la sala, false en caso contrario
     * @return
     */
    public boolean hayTrampas() {
        boolean resultado = false;
        for (int i = 0; i < trampas.length && !resultado; i++){
            if (trampas[i] != null){
                resultado = true;
            }
        }
        return resultado;
    }

    /**
     * Método getFila
     * @return int fila
     */
    public int getFila() {
        return fila;
    }

    /**
     * Método getColumna
     * @return int columna
     */
    public int getColumna() {
        return columna;
    }

    /**
     * Método hayItems para mostrar si existe algún item en la sala
     * TODO buscar si hay algún item en la lista de items, false en caso contrario
     * @return
     */
    public boolean hayItems() {
        boolean resultado = false;
        for (int i = 0; i < items.length; i++){
            if (items[i] != null){
                resultado = true;
            }
        }
        return resultado;
    }

    /**
     * Método buscarItem para obtener un item según una descripcion dada
     * TODO buscar en la lista de items un item con la descripción pasada como parámetro, devolver null si no lo
     *  encuentra
     * @param descripcion
     * @return
     */
    public Item buscarItem(String descripcion) {
        Item resultado = null;
        boolean encontrado = false;
        int i = 0;
        while (items[i] != null && i < items.length && !encontrado){
            if (items[i].getDescripcion().equalsIgnoreCase(descripcion)){
                resultado = items[i];
                encontrado = true;
            } else {
                i++;
            }
        }
        return resultado;
    }

    /**
     * Método buscarTrampa para obtener una trampa según una descripcion dada
     * TODO buscar en la lista de trampas una trampa con la descripción pasada como parámetro, devolver null si no lo
     *  encuentra
     * @param descripcion
     * @return
     */
    public Trampa buscarTrampa(String descripcion) {
        Trampa resultado = null;
        boolean encontrado = false;
        int i = 0;
        while (trampas[i] != null && i < trampas.length && !encontrado){
            if (trampas[i].getDescripcion().equalsIgnoreCase(descripcion)){
                resultado = trampas[i];
                encontrado = true;
            } else {
                i++;
            }
        }
        return resultado;
    }

    /**
     * Método getTrampas
     * @return Trampa[] trampas
     */
    public Trampa[] getTrampas() {
        return trampas;
    }

    /**
     * Método seleccionarItem para obtener un item concreto con parámetro pasados por pantalla
     * TODO Mostrar por pantalla todos los items de la sala para despues pedir que se introduzca una descripcion del
     *  item que se quiere seleccionar
     * @param teclado
     * @return
     */
    public Item seleccionarItem(Scanner teclado) {
        if (hayItems()){
            listarItems();
            boolean encontrado = false;
            String nombre = "";
            while (!encontrado) {
                nombre = Utilidades.leerCadena(teclado, "Escriba la descripcion del item que quieres coger(NINGUNO para cancelar): ");
                if (buscarItem(nombre) != null) {
                    encontrado = true;
                }
                if (nombre.equals("NINGUNO")){
                    encontrado=true;
                }
            }
            return buscarItem(nombre);
        }
        else return null;
    }
    private boolean contieneItem(String nombreItem){
        boolean resul = false;
        if (!resul){
            for (int i = 0; i < items.length; i++){
                if (items[i] != null){
                    if (items[i].getDescripcion().equalsIgnoreCase(nombreItem)){
                        resul = true;
                    }
                }
            }
        }
        return resul;
    }

    /**
     * Método listarItems para mostrar por pantalla todos los items
     * TODO utilizar las funciones de la clase Item para poder mostrar por pantalla toda la información de todos los
     *  items que hay en la sala
     */
    private void listarItems() {
        if (hayItems()){
            for (int i = 0; i < items.length; i++){
                if (items[i] != null){
                    System.out.println(items[i].toString());
                }

            }
        }
    }

    /**
     * Método eliminarItem para eliminar un item con la descripcion pasada como parámetro
     * TODO buscar el item que coincida con la descripción pasada por parámetro y eliminarlo de la lista de items
     * @param descripcion
     */
    public void eliminarItem(String descripcion) {
        boolean encontrado = false;
        int contador = 0;
        int numItems = items.length;
        while (contador < numItems && !encontrado){
            if (items[contador].getDescripcion().equalsIgnoreCase(descripcion)){
                while (contador < numItems - 1){
                    items[contador] = items[ contador + 1];
                    contador++;
                }
                items[numItems - 1] = null;
                encontrado = true;
            }
            contador++;
        }
    }
}
