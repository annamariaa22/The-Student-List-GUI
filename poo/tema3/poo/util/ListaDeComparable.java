package poo.util;

import java.lang.reflect.Array;

public class ListaDeComparable<T extends Comparable<T>> {
    T[] lista;
    int numarCurentDeElemente;

    public ListaDeComparable(int dim_max, Class<T> tipul_elementelor) throws NegativeArraySizeException {
        if (dim_max < 0) {
            throw new NegativeArraySizeException();
        } else {
            lista = (T[]) Array.newInstance(tipul_elementelor, dim_max);
            numarCurentDeElemente = 0;
        }
    }

    public String afisareElemente() {
        String element_afisat = lista[numarCurentDeElemente - 1].toString();
        for (int i = numarCurentDeElemente - 2; i >= 0; i--) {
            element_afisat = element_afisat + "\n" + lista[i].toString();
        }
        return element_afisat;
    }

    public void adaugareElement(T elementDeAdugat) throws ExceptieListaPlina {
        if (numarCurentDeElemente == lista.length) {
            throw new ExceptieListaPlina();
        } else {
            numarCurentDeElemente++;
            if (numarCurentDeElemente > 1) {
                for (int i = numarCurentDeElemente - 1; i > 0; i--) {
                    lista[i] = lista[i - 1];
                }
            }
            lista[0] = (T) elementDeAdugat;
            System.out.println("Am adaugat elementul " + elementDeAdugat);

        }
    }

    public void eliminareElement() throws ExceptieListaGoala {
        if (numarCurentDeElemente == 0) {
            throw new ExceptieListaGoala();
        } else {
            System.out.println("Am sters elementul " + lista[numarCurentDeElemente - 1]);
            lista[numarCurentDeElemente - 1] = null;
            numarCurentDeElemente--;
        }
    }

    public void sortareElemente() throws ExceptieListaGoala {
        if (numarCurentDeElemente == 0 || numarCurentDeElemente == 1) {
            throw new ExceptieListaGoala();
        } else {
            T interschimba;
            for (int i = 0; i < numarCurentDeElemente - 1; i++) {
                for (int j = 0; j < numarCurentDeElemente - 1 - i; j++) {
                    if (lista[j].compareTo(lista[j + 1]) == -1) {
                        interschimba = lista[j];
                        lista[j] = lista[j + 1];
                        lista[j + 1] = interschimba;
                    }
                }
            }
        }
        System.out.println("Lista a fost sortata descrescator");
    }

}
