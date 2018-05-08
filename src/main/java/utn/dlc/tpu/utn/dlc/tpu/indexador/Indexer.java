package utn.dlc.tpu.utn.dlc.tpu.indexador;

import utn.dlc.util.method_timer.BadTimeUnitException;

import java.io.File;
import java.nio.file.NotDirectoryException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static utn.dlc.util.method_timer.MethodTimer.MILLISECONDS;
import static utn.dlc.util.method_timer.MethodTimer.timeThis;

/**
 * ONLY SUPPORTS .txt FILES.
 */
public class Indexer {
    private String docs_dir;
    private DocumentReader reader;
    private Vocabulary vocabulary;

    public Indexer(String path) {
        this.docs_dir = path;
        reader = DocumentReader.getInstance();
        vocabulary = new Vocabulary();
    }

    public void index_dir() throws NotDirectoryException{
        File dir = new File(this.docs_dir);
        List<File> files_to_read = read_dir(dir);
        for (File file : files_to_read) {
            parse_file(file);
        }
        int aux = 0;
        int max = 0;
        Map.Entry most_frequent_word = null;
        for (Map.Entry entry : vocabulary.getPalabras().entrySet()) {
            aux = (Integer) entry.getValue();
            if (aux > max) {
                max = aux;
                most_frequent_word = entry;
            }
        }
        System.out.println(most_frequent_word.getKey().toString());

    }

    //TODO: don't assume that there are not subdirectories.
    private List<File> read_dir(File dir) throws NotDirectoryException {
        List <File> files_to_read = new ArrayList<>();
        if (!dir.isDirectory()) {
            throw new NotDirectoryException("The provided path is not a Directory");
        }
        for (File file: dir.listFiles()) {
            files_to_read.add(file);
        }
        return files_to_read;
    }

    private void parse_file(File file) {
        List<String> words = reader.get_words(file.getPath());
        create_vocabulary(words);
    }

    private void create_vocabulary(List<String> words) {
        for (String word : words) {
            if (word.isEmpty() || word.equals("a") || word.equals("the")){
                continue;
            }
            vocabulary.add_word(new Palabra(word));
        }
    }
}

class TestIndexer {
    public static void main(String args[]) throws BadTimeUnitException {
        Indexer i = new Indexer("/Users/juani9303/Dropbox/UTN/2018/DLC/TPU-Buscador/DocumentosTP1");
        long duration = timeThis(() -> {
            try {
                i.index_dir();
            } catch (NotDirectoryException e) {
                e.printStackTrace();
            }
        }, MILLISECONDS);
        System.out.println(duration);
    }
}
