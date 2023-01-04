package bzh.lerouxard.smashorpass.apiImplementation;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Media {

    @SerializedName("nodes")
    @Expose
    private List<Node> nodes = null;

    public List<Node> getNodes() {
        return nodes;
    }

    public void setNodes(List<Node> nodes) {
        this.nodes = nodes;
    }

    public Media withNodes(List<Node> nodes) {
        this.nodes = nodes;
        return this;
    }

}