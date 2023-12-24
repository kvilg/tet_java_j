public class FileConf {
    private String text;
    private String name;
    private String path;

    public FileConf() {}

    public FileConf(String text, String name, String path) {
        this.text = text;
        this.name = name;
        this.path = path;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
