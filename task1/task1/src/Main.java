import java.io.*;
import java.util.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    //TODO Переделать с hash map на tree
    private static final String ROOT_PATH = "root";

    private static final File file = new File(ROOT_PATH);

    public static void main(String[] args) throws IOException {

        Queue<File> hip = new LinkedList<>();

        hip.add(file);
        HashMap<String,File> outFile = new HashMap<>();

        while (!hip.isEmpty()){
            File file = Objects.requireNonNull(hip.peek());

            if(file.isDirectory()){
                hip.addAll(List.of(Objects.requireNonNull(file.listFiles())));
            }
            if(file.isFile()){
                outFile.put(file.getPath().replace(ROOT_PATH,""), file);
            }
            hip.remove();
        }

        for (File file1 : outFile.values()) {
            List<String> fileInList = FileReaderUtil.readFile(file1);

            for (int l =0;l<fileInList.size();l++){
                if(fileInList.get(l).contains("require")){
                    int indexF = fileInList.get(l).indexOf("'");
                    int indexL = fileInList.get(l).lastIndexOf("'");

                    String path = fileInList.get(l).substring(indexF+1,indexL);
                    File f = outFile.get(path);

                    List<String> strokiFile = FileReaderUtil.readFile(f);
                    fileInList.remove(l);
                    fileInList.addAll(l,strokiFile);

                }

            }


//            fileInList.stream()
//                    .filter(filter -> filter.contains("require"))
//                    .forEach(m ->{
//                        int indexF = m.indexOf("'");
//                        int indexL = m.indexOf("'",indexF);
//
//                        String path = m.substring(indexF,indexL);
//
//                        outFile.get(path);
//                    });
            for (String s : fileInList) {
                System.out.println(s);
            }
            System.out.println();
            System.out.println();
        }


    }

}