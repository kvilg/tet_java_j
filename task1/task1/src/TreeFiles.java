import java.util.LinkedList;
import java.util.Queue;

public class TreeFiles {
    private NodeFile root;

    public TreeFiles() {
    }

    public TreeFiles(NodeFile root) {
        this.root = root;
    }

    public NodeFile getRoot() {
        return root;
    }

    public void setRoot(NodeFile root) {
        this.root = root;
    }

    public void pasNewRoot(NodeFile newRoot){
        NodeFile rootOld = this.root;

        this.root = newRoot;
        newRoot.addNodeFile(rootOld);
    }

    public NodeFile sarcheNodeByPath(String path){
        if(this.root.getPath().equals(path)){
            return this.root;
        }

        NodeFile node = this.root;
        Queue<NodeFile> queueNodesOut = new LinkedList<>();

        Queue<NodeFile> queueNodes = new LinkedList<>();
        queueNodes.add(node);

        while (!queueNodes.isEmpty()){
            NodeFile nodeFile = queueNodes.poll();
            queueNodesOut.add(nodeFile);
            if(!nodeFile.getNodeFilesChild().isEmpty()){
                queueNodes.addAll(nodeFile.getNodeFilesChild());
            }
            if (nodeFile.getPath().equals(path)){
                return nodeFile;
            }
        }
        return null;



    }
}
