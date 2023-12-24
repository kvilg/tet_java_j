import java.io.*;
import java.util.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    //TODO Переделать с hash map на tree
    private static final String ROOT_PATH = "root";

    private static final File file = new File(ROOT_PATH);

    private static final ArrayList<FileConf> files = new ArrayList<>();

    private static final ArrayList<NodeFile> FILE_PATHS = new ArrayList<NodeFile>();

    public static void main(String[] args) throws IOException {

        Queue<File> hip = new LinkedList<>();

        hip.add(file);
        HashMap<String,NodeFile> outFile = new HashMap<>();

        while (!hip.isEmpty()){

            File file = Objects.requireNonNull(hip.peek());

            if(file.isDirectory()){
                hip.addAll(List.of(Objects.requireNonNull(file.listFiles())));
            }
            if(file.isFile()){
                String path = file.getPath().replace(ROOT_PATH,"");
                outFile.put(path, new NodeFile(file,path));
            }
            hip.remove();
        }







        List<NodeFile> fileConfs = outFile.values().stream().toList();


        for (NodeFile fileRead : outFile.values()) {
            List<String> fileInList = FileReaderUtil.readFile(fileRead.getFile());

            ArrayList<String> filesPaths = new ArrayList<>();

            for (int l =0;l<fileInList.size();l++){
                if(fileInList.get(l).contains("require")){
                    int indexF = fileInList.get(l).indexOf("'");
                    int indexL = fileInList.get(l).lastIndexOf("'");

                    String path = fileInList.get(l).substring(indexF+1,indexL);
                    filesPaths.add(path);
                }
            }
            ArrayList<NodeFile> filePaths = new ArrayList<>();

            for (String s : filesPaths) {
                filePaths.add(outFile.get(s));
            }

            FILE_PATHS.add(new NodeFile(fileRead.getPath().replace(ROOT_PATH,""), filePaths));
        }


        for (int l = 0; l < FILE_PATHS.size(); l++){

            ArrayList<NodeFile> filesChild = FILE_PATHS.get(l).getNodeFilesChild();

            int size = filesChild.size();
            for (int i = 0; i < size; i++) {
                NodeFile nodeFile = filesChild.get(i);
                String path = nodeFile.getPath();

                for (int j = 0; j < FILE_PATHS.size(); j++) {
                    if(FILE_PATHS.get(j).getPath().equals(path)){
                        ArrayList<NodeFile> ch = FILE_PATHS.get(j).getNodeFilesChild();
                        nodeFile.setNodeFilesChild(ch);
                        FILE_PATHS.remove(j);
                    }
                }


            }

        }



        ArrayList<FilePaths> filePaths = new ArrayList<>();

        ArrayList<NodeFile> filePathsQueue = new ArrayList<>(FILE_PATHS);


        for (NodeFile n : filePathsQueue){
            NodeFile next = n;



            Queue<NodeFile> nodeFiles = new LinkedList<>();
            nodeFiles.add(n);
            NodeFile curRef;
            while (!nodeFiles.isEmpty()) {

                ArrayList<NodeFile> refs = new ArrayList<>();

                curRef = nodeFiles.poll();

                while (true) {

                    if(refs.contains(curRef)){
                        throw new RuntimeException("asd");
                    }

                    System.out.println(curRef.getPath());

                    refs.add(curRef);
                    if (curRef.getNodeFilesChild().isEmpty()) {
                        break;
                    }

                    for (int i = 0; i < curRef.getNodeFilesChild().size() - 1; i++) {
                        nodeFiles.add(curRef.getNodeFilesChild().get(i));
                    }

                    curRef = curRef.getNodeFilesChild().get(curRef.getNodeFilesChild().size() - 1);

                }
            }



            System.out.println("\n\n\n\n");

        }

//        let cur_ref;         // текущая ссылка
//        let memory = [tree]; // память (стек)
//        // в начале память содержит ссылку на корень заданного дерева
//
//        // внешний цикл, перебирающий линии заглублений
//        // закончить цикл, если не получается извлечь ссылку из памяти (стека)
//        while ( cur_ref = memory.pop() ) {
//
//            // внутренний цикл обхода каждой линии заглубления дерева до листа
//            while ( true ) {
//                // ...обработка данных узла...
//                console.log(cur_ref.data); // просто выводим в консоль
//
//                // если это лист, выйти из цикла
//                if ( !cur_ref.refs ) break;
//
//                // помещаем ветви, ведущие налево, в память (стек)
//                for (let i = 0; i < cur_ref.refs.length - 1; i++) {
//                    memory.push( cur_ref.refs[i] );
//                }
//                // переходим по ветви, ведущей направо
//                cur_ref = cur_ref.refs[cur_ref.refs.length - 1];
//            }
//        }







        for (NodeFile fileRead : outFile.values()) {
            List<String> fileInList = FileReaderUtil.readFile(fileRead.getFile());

            ArrayList<NodeFile> nodeFiles = new ArrayList<>();

            for (int l =0;l<fileInList.size();l++){
                if(fileInList.get(l).contains("require")){
                    int indexF = fileInList.get(l).indexOf("'");
                    int indexL = fileInList.get(l).lastIndexOf("'");

                    String path = fileInList.get(l).substring(indexF+1,indexL);
                    NodeFile f = outFile.get(path);
                    nodeFiles.add(new NodeFile(f.getFile(), path));

                    List<String> strokiFile = FileReaderUtil.readFile(f.getFile());
                    fileInList.remove(l);
                    fileInList.addAll(l,strokiFile);
                }
            }

            StringBuilder sb = new StringBuilder();

            for (int i = 0; i < fileInList.size(); i++) {
                sb.append(fileInList.get(i));
                if(i != fileInList.size() -1){
                    sb.append("\n");
                }
            }
            files.add(new FileConf(sb.toString(),fileRead.getFile().getName(), fileRead.getPath().replace(ROOT_PATH,"")));
        }

        QuickSort.quickSort(files,0,files.size() -1);

        for (FileConf s : files) {
            System.out.println(s.getPath());
        }
    }

}