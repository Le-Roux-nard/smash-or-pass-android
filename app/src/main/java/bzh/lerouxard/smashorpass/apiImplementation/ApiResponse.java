package bzh.lerouxard.smashorpass.apiImplementation;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ApiResponse {


    @SerializedName("data")
    @Expose
    private Data data;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public ApiResponse withData(Data data) {
        this.data = data;
        return this;
    }
}
