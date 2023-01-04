package bzh.lerouxard.smashorpass.apiImplementation;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Name {

    @SerializedName("native")
    @Expose
    private Object _native;
    @SerializedName("alternative")
    @Expose
    private List<Object> alternative = null;
    @SerializedName("alternativeSpoiler")
    @Expose
    private List<Object> alternativeSpoiler = null;
    @SerializedName("userPreferred")
    @Expose
    private String userPreferred;
    @SerializedName("full")
    @Expose
    private String full;

    public Object getNative() {
        return _native;
    }

    public void setNative(Object _native) {
        this._native = _native;
    }

    public Name withNative(Object _native) {
        this._native = _native;
        return this;
    }

    public List<Object> getAlternative() {
        return alternative;
    }

    public void setAlternative(List<Object> alternative) {
        this.alternative = alternative;
    }

    public Name withAlternative(List<Object> alternative) {
        this.alternative = alternative;
        return this;
    }

    public List<Object> getAlternativeSpoiler() {
        return alternativeSpoiler;
    }

    public void setAlternativeSpoiler(List<Object> alternativeSpoiler) {
        this.alternativeSpoiler = alternativeSpoiler;
    }

    public Name withAlternativeSpoiler(List<Object> alternativeSpoiler) {
        this.alternativeSpoiler = alternativeSpoiler;
        return this;
    }

    public String getUserPreferred() {
        return userPreferred;
    }

    public void setUserPreferred(String userPreferred) {
        this.userPreferred = userPreferred;
    }

    public Name withUserPreferred(String userPreferred) {
        this.userPreferred = userPreferred;
        return this;
    }

    public String getFull() {
        return full;
    }

    public void setFull(String full) {
        this.full = full;
    }

    public Name withFull(String full) {
        this.full = full;
        return this;
    }

}
