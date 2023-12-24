import java.util.ArrayList;

public class FilePaths {

    private String path;
    private ArrayList<FilePaths> pathChilde = new ArrayList<>();


    public FilePaths() {
    }

    public FilePaths(String path, ArrayList<FilePaths> pathChilde) {
        this.path = path;
        this.pathChilde = pathChilde;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public ArrayList<FilePaths> getPathChilde() {
        return pathChilde;
    }

    public void setPathChilde(ArrayList<FilePaths> pathChilde) {
        this.pathChilde = pathChilde;
    }
}
