import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;


public class Main {
    //TODO Переделать с hash map на tree
    private static final String ROOT_PATH = "root";

    private static final File file = new File(ROOT_PATH);

    private static final ArrayList<NodeFile> files = new ArrayList<>();

    private static final ArrayList<NodeFile> FILE_PATHS = new ArrayList<NodeFile>();

    public static void main(String[] args) throws IOException {

        Queue<File> hip = new LinkedList<>();

        hip.add(file);
        HashMap<String, NodeFile> outFile = new HashMap<>();

        while (!hip.isEmpty()) {

            File file = Objects.requireNonNull(hip.peek());

            if (file.isDirectory()) {
                hip.addAll(List.of(Objects.requireNonNull(file.listFiles())));
            }
            if (file.isFile()) {
                String path = file.getPath().replace(ROOT_PATH, "");
                outFile.put(path, new NodeFile(file, path));
            }
            hip.remove();
        }

        for (NodeFile fileRead : outFile.values()) {
            List<String> fileInList = FileReaderUtil.readFile(fileRead.getFile());

            ArrayList<String> filesPaths = new ArrayList<>();

            for (int l = 0; l < fileInList.size(); l++) {
                if (fileInList.get(l).contains("require")) {
                    int indexF = fileInList.get(l).indexOf("'");
                    int indexL = fileInList.get(l).lastIndexOf("'");

                    String path = fileInList.get(l).substring(indexF + 1, indexL);
                    filesPaths.add(path);
                }
            }
            ArrayList<NodeFile> filePaths = new ArrayList<>();

            for (String s : filesPaths) {
                if (!filePaths.contains(outFile.get(s))) {
                    filePaths.add(outFile.get(s));
                }
            }

            FILE_PATHS.add(new NodeFile(fileRead.getFile(), fileRead.getPath().replace(ROOT_PATH, ""), filePaths));
        }

        for (int l = 0; l < FILE_PATHS.size(); l++) {

            ArrayList<NodeFile> filesChild = FILE_PATHS.get(l).getNodeFilesChild();

            int size = filesChild.size();
            for (int i = 0; i < size; i++) {
                NodeFile nodeFile = filesChild.get(i);
                String path = nodeFile.getPath();

                for (int j = 0; j < FILE_PATHS.size(); j++) {
                    if (FILE_PATHS.get(j).getPath().equals(path)) {
                        ArrayList<NodeFile> ch = FILE_PATHS.get(j).getNodeFilesChild();
                        nodeFile.setNodeFilesChild(ch);
                        FILE_PATHS.remove(j);
                    }
                }
            }

        }

        ArrayList<NodeFile> filePathsQueue = new ArrayList<>(FILE_PATHS);


        for (NodeFile n : filePathsQueue) {

            Queue<NodeFile> nodeFiles = new LinkedList<>();
            nodeFiles.add(n);
            NodeFile curRef;
            ArrayList<NodeFile> refs = new ArrayList<>();
            while (!nodeFiles.isEmpty()) {

                curRef = nodeFiles.poll();

                while (true) {
                    NodeFile finalCurRef = curRef;
                    if (refs.stream()
                            .filter(f -> f.getPath().equals(finalCurRef.getPath())).findFirst().isPresent()) {
                        throw new RuntimeException(refs.stream().map(m -> m.getPath()).collect(Collectors.toList()).toString());
                    }
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
        }

        LinkedList<NodeFile> nodesOut = new LinkedList<>();

        for (NodeFile n : filePathsQueue) {
            Queue<NodeFile> queue = new LinkedList<>();
            queue.add(n);

            while (!queue.isEmpty()) {
                NodeFile node = queue.remove();

                boolean presentNot2 = true;
                for (int j = 0; j < nodesOut.size(); j++) {
                    if (nodesOut.get(j).getPath().equals(node.getPath())) {
                        presentNot2 = false;
                        break;
                    }
                }
                if (presentNot2 || nodesOut.isEmpty()) {
                    nodesOut.add(node);
                }

                ArrayList<NodeFile> nodeFilesChild = node.getNodeFilesChild();
                for (int i = 0; i < nodeFilesChild.size(); i++) {
                    queue.add(nodeFilesChild.get(i));
                    int I = i;
                    boolean presentNot = true;
                    for (int j = 0; j < nodesOut.size(); j++) {
                        if (nodesOut.get(j).getPath().equals(nodeFilesChild.get(i).getPath())) {
                            presentNot = false;
                        }
                    }
                    if (presentNot) {
                        nodesOut.add(nodeFilesChild.get(i));
                    }
                }
            }
        }
        for (NodeFile fileRead : outFile.values()) {
            List<String> fileInList = FileReaderUtil.readFile(fileRead.getFile());

            ArrayList<NodeFile> nodeFiles = new ArrayList<>();

            for (int l = 0; l < fileInList.size(); l++) {
                if (fileInList.get(l).contains("require")) {
                    int indexF = fileInList.get(l).indexOf("'");
                    int indexL = fileInList.get(l).lastIndexOf("'");

                    String path = fileInList.get(l).substring(indexF + 1, indexL);
                    NodeFile f = outFile.get(path);
                    nodeFiles.add(new NodeFile(f.getFile(), path));

                    List<String> strokiFile = FileReaderUtil.readFile(f.getFile());
                    fileInList.remove(l);
                    fileInList.addAll(l, strokiFile);
                }
            }

            StringBuilder sb = new StringBuilder();

            for (int i = 0; i < fileInList.size(); i++) {
                sb.append(fileInList.get(i));
                if (i != fileInList.size() - 1) {
                    sb.append("\n");
                }
            }
            files.add(new NodeFile(sb.toString(), fileRead.getPath().replace(ROOT_PATH, "")));
        }


        for (NodeFile s : files) {
            System.out.println(s.getPath());
            System.out.println();
        }
    }

}