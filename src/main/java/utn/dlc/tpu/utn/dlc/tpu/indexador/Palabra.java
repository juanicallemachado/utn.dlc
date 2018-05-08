package utn.dlc.tpu.utn.dlc.tpu.indexador;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Palabra implements Comparable{
    private String nombre;

    public Palabra(String nombre){
        this.nombre = clean(nombre.toLowerCase());
    }

    private String clean(String word){
        word = word.replaceAll("\\d", "").
                replaceAll("'s", "").
                replaceAll("'d","").
                replaceAll("'nt","").
                replaceAll("\\W","").
                replaceAll("_","").
                replaceAll("\r", "").
                replaceAll("\n", "");
        return word;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public int hashCode() {
        return this.nombre.hashCode();
    }

    @Override
    public int compareTo(Object o) {
        return this.getNombre().compareTo( ( (Palabra) o).getNombre() );
    }

    @Override
    public boolean equals(Object p) {
        return p instanceof Palabra && this.nombre.hashCode() == ((Palabra) p).getNombre().hashCode();
    }

}

class TestPalabra {
    public static void main(String args[]) {
        Palabra a = new Palabra("Juan1i's-gr0oso_a_");
        Palabra b = new Palabra("Za");
        System.out.println(a.equals(b));
        System.out.println(a.hashCode());
        System.out.println(b.hashCode());
        System.out.println(a.compareTo(b));
        List list = new ArrayList<>();
        list.add(a.getNombre());
        list.add(b.getNombre());
        Collections.sort(list);
        System.out.println(list.toString());
    }
}
