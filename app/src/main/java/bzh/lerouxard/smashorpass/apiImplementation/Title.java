package bzh.lerouxard.smashorpass.apiImplementation;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Title {

    @SerializedName("romaji")
    @Expose
    private String romaji;

    public String getRomaji() {
        return romaji;
    }

    public void setRomaji(String romaji) {
        this.romaji = romaji;
    }

    public Title withRomaji(String romaji) {
        this.romaji = romaji;
        return this;
    }

}