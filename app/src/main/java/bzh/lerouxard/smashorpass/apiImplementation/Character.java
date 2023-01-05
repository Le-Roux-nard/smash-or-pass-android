package bzh.lerouxard.smashorpass.apiImplementation;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import okhttp3.ResponseBody;
import retrofit2.Callback;


public class Character {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("image")
    @Expose
    private Image image;
    @SerializedName("gender")
    @Expose
    private String gender;

    @SerializedName("media")

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Character withId(int id) {
        this.id = id;
        return this;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public Character withImage(Image image) {
        this.image = image;
        return this;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Character withGender(String gender) {
        this.gender = gender;
        return this;
    }

    public void downloadImage() {
        this.getImage().downloadImage(this.getId() + ".jpg");
    }

    public void downloadImage(Callback<ResponseBody> callback) {
        this.getImage().downloadImage(this.getId() + ".jpg", callback);
    }

    public String toString() {
        return this.getId() + " (" + this.getGender() + ")";
    }
}