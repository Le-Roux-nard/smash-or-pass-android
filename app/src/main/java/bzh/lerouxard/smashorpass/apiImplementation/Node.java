package bzh.lerouxard.smashorpass.apiImplementation;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Node {

    @SerializedName("title")
    @Expose
    private Title title;
    @SerializedName("isAdult")
    @Expose
    private boolean isAdult;

    public Title getTitle() {
        return title;
    }

    public void setTitle(Title title) {
        this.title = title;
    }

    public Node withTitle(Title title) {
        this.title = title;
        return this;
    }

    public boolean isIsAdult() {
        return isAdult;
    }

    public void setIsAdult(boolean isAdult) {
        this.isAdult = isAdult;
    }

    public Node withIsAdult(boolean isAdult) {
        this.isAdult = isAdult;
        return this;
    }

}