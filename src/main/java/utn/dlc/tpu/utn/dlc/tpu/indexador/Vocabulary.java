package utn.dlc.tpu.utn.dlc.tpu.indexador;

import java.util.TreeMap;

public class Vocabulary {
    private TreeMap<Palabra, Integer> palabras;

    public Vocabulary() {
        palabras = new TreeMap<>();
    }

    public void add_word(Palabra p) {
        if(p.getNombre().isEmpty() || p.getNombre().equals("a")) { //ignore a void token or the letter a
            return;
        }
        if (palabras.containsKey(p)) {
            palabras.put(p, palabras.get(p) + 1);
        }
        else {
            palabras.put(p, 1);
        }
    }

    public TreeMap<Palabra, Integer> getPalabras() {
        return palabras;
    }
}

class TestVocabulary {
    public static void main(String args []) {
        Palabra a = new Palabra("a");
        Palabra b = new Palabra("b");
        Palabra c = new Palabra("a");
        Vocabulary v = new Vocabulary();
        v.add_word(a);
        v.add_word(b);
        v.add_word(c);
    }
}
