package bzh.lerouxard.smashorpass.apiImplementation;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Page {

    @SerializedName("characters")
    @Expose
    private List<Character> characters = null;

    public List<Character> getCharacters() {
        return characters;
    }

    public void setCharacters(List<Character> characters) {
        this.characters = characters;
    }

    public Page withCharacters(List<Character> characters) {
        this.characters = characters;
        return this;
    }

}