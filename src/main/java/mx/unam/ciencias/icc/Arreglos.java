package mx.unam.ciencias.icc;

import java.util.Comparator;

/**
 * Clase para ordenar y buscar arreglos genéricos.
 */
public class Arreglos {

    /* Constructor privado para evitar instanciación. */
    private Arreglos() {}

    /**
     * Ordena el arreglo recibido usando SelectionSort.
     * @param <T> tipo del que puede ser el arreglo.
     * @param arreglo un arreglo cuyos elementos son comparables.
     */
    public static <T extends Comparable<T>> void
    selectionSort(T[] arreglo) {
        selectionSort(arreglo, (a, b) -> a.compareTo(b));
    }

    /**
     * Ordena el arreglo recibido usando SelectionSort.
     * @param <T> tipo del que puede ser el arreglo.
     * @param arreglo el arreglo a ordenar.
     * @param comparador el comparador para ordernar el arreglo.
     */
    public static <T> void
    selectionSort(T[] arreglo, Comparator<T> comparador) {
        int max = arreglo.length;
        for(int i = 0; i<max; i++) {
                int min = i;
                for (int j = i+1; j < max; j++)
                        if (menor (comparador, arreglo[j], arreglo[min]) )
                                min = j;
                cam(arreglo, i, min);
        }
    }

    private static <T> boolean menor (Comparator<T> c, T x, T y){
        return c.compare(x, y) < 0;
    }
    private static <T> void cam (T [] a, int m, int n){
        T s = a[m];
        a[m] = a[n];
        a[n] = s;
    }

    /**
     * Ordena el arreglo recibido usando QuickSort.
     * @param <T> tipo del que puede ser el arreglo.
     * @param arreglo un arreglo cuyos elementos son comparables.
     */
    public static <T extends Comparable<T>> void
    quickSort(T[] arreglo) {
        quickSort(arreglo, (a, b) -> a.compareTo(b));
    }

    /**
     * Ordena el arreglo recibido usando QuickSort.
     * @param <T> tipo del que puede ser el arreglo.
     * @param arreglo el arreglo a ordenar.
     * @param comparador el comparador para ordenar el arreglo.
     */
    public static <T> void
    quickSort(T[] arreglo, Comparator<T> comparador) {
        quick(arreglo, comparador);
    }
    
    private static <T> void y(T[] A, int i, int j){
            if(i == j)
                    return;
            T t = A[j];
            A[j] = A[i];
            A[i] = t;
    }
    
    private static <T> void quick(T[] A, Comparator<T> c){
            Lista<Integer> l = new Lista<Integer>();
            l.agregaFinal(0);
            l.agregaFinal(A.length-1);
            while(l.getLongitud() > 0) {
                    int x = l.eliminaUltimo();
                    int y = l.eliminaUltimo();
                    if(x - y < 1)
                            continue;
                    int i = y +1, j = x;
                    while(i < j)
                            if(c.compare(A[i], A[y]) > 0 && c.compare(A[j], A[y]) <= 0)
                                    y(A, i++, j--);
                            else if(c.compare(A[i], A[y]) <= 0)
                                    i++;
                            else
                                    j--;
                    if(c.compare(A[i],A[y]) > 0)
                            i--;
                    y(A, y, i);
                    l.agregaFinal(y);
                    l.agregaFinal(i-1);
                    l.agregaFinal(i+1);
                    l.agregaFinal(x);
            }
        }

    /**
     * Hace una búsqueda binaria del elemento en el arreglo. Regresa el índice
     * del elemento en el arreglo, o -1 si no se encuentra.
     * @param <T> tipo del que puede ser el arreglo.
     * @param arreglo un arreglo cuyos elementos son comparables.
     * @param elemento el elemento a buscar.
     * @return el índice del elemento en el arreglo, o -1 si no se encuentra.
     */
    public static <T extends Comparable<T>> int
    busquedaBinaria(T[] arreglo, T elemento) {
        return busquedaBinaria(arreglo, elemento, (a, b) -> a.compareTo(b));
    }

    /**
     * Hace una búsqueda binaria del elemento en el arreglo. Regresa el índice
     * del elemento en el arreglo, o -1 si no se encuentra.
     * @param <T> tipo del que puede ser el arreglo.
     * @param arreglo el arreglo dónde buscar.
     * @param elemento el elemento a buscar.
     * @param comparador el comparador para hacer la búsqueda.
     * @return el índice del elemento en el arreglo, o -1 si no se encuentra.
     */
    public static <T> int
    busquedaBinaria(T[] arreglo, T elemento, Comparator<T> comparador) {
        int n = 0;
        int m = arreglo.length-1;
        while(n <= m) {
                int mitad = (m+n)/2;
                if(comparador.compare(arreglo[mitad], elemento) == 0)
                        return mitad;
                else if(comparador.compare(arreglo[mitad], elemento) > 0) {
                        if(comparador.compare(arreglo[n], elemento) == 0)
                                return n;
                        m = mitad-1;
                        n = n + 1;
                }
                else{
                        if (comparador.compare(arreglo[m], elemento) == 0)
                                return m;
                        m = m-1;
                        n = n +1;
                }
        }
        return -1;
      }
    }
