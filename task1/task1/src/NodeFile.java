import java.io.File;
import java.util.ArrayList;

public class NodeFile {

    private File file;
    private String path;
    private ArrayList<NodeFile> nodeFilesChild = new ArrayList<>();

    public NodeFile() {
    }

    public NodeFile(File file, String path) {
        this.file = file;
        this.path = path;
    }

    public NodeFile(String path,ArrayList<NodeFile> nodeFiles) {
        this.path = path;
        this.nodeFilesChild = nodeFiles;
    }


    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public ArrayList<NodeFile> getNodeFilesChild() {
        return nodeFilesChild;
    }

    public void setNodeFilesChild(ArrayList<NodeFile> nodeFilesChild) {
        this.nodeFilesChild = nodeFilesChild;
    }

    public void addNodeFile(NodeFile nodeFile){
        this.nodeFilesChild.add(nodeFile);
    }

    public void addNodeFiles(ArrayList<NodeFile> nodeFiles) {
        this.nodeFilesChild.addAll(nodeFiles);
    }

}
