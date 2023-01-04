package bzh.lerouxard.smashorpass.apiImplementation;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import okhttp3.ResponseBody;
import retrofit2.Callback;


public class Character {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("siteUrl")
    @Expose
    private String siteUrl;
    @SerializedName("image")
    @Expose
    private Image image;
    @SerializedName("name")
    @Expose
    private Name name;
    @SerializedName("gender")
    @Expose
    private Object gender;
    @SerializedName("media")
    @Expose
    private Media media;

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

    public String getSiteUrl() {
        return siteUrl;
    }

    public void setSiteUrl(String siteUrl) {
        this.siteUrl = siteUrl;
    }

    public Character withSiteUrl(String siteUrl) {
        this.siteUrl = siteUrl;
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

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public Character withName(Name name) {
        this.name = name;
        return this;
    }

    public Object getGender() {
        return gender;
    }

    public void setGender(Object gender) {
        this.gender = gender;
    }

    public Character withGender(Object gender) {
        this.gender = gender;
        return this;
    }

    public Media getMedia() {
        return media;
    }

    public void setMedia(Media media) {
        this.media = media;
    }

    public Character withMedia(Media media) {
        this.media = media;
        return this;
    }

    public void downloadImage() {
        this.getImage().downloadImage(this.getId() + ".jpg");
    }

    public void downloadImage(Callback<ResponseBody> callback) {
        this.getImage().downloadImage(this.getId() + ".jpg", callback);
    }

    public String toString() {
        return " (" + this.getId() + ")" + this.getName().getFull();
    }
}