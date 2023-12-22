import java.io.*;
import java.util.LinkedList;
import java.util.List;


public class FileReaderUtil {
    public static List<String> readFile(File file) throws IOException {
        List<String> list = new LinkedList<>();

        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);

        String line;
        while((line = br.readLine()) != null){
            list.add(line);
        }

        return list;
    }
}
