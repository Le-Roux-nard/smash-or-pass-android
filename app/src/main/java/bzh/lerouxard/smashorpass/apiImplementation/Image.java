package bzh.lerouxard.smashorpass.apiImplementation;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Log;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import bzh.lerouxard.smashorpass.MyApplication;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Image {

    @SerializedName("large")
    @Expose
    private String large;

    private String localPath;

    public String getLarge() {
        return large;
    }

    public void setLarge(String large) {
        this.large = large;
    }

    public Image withLarge(String large) {
        this.large = large;
        return this;
    }

    void downloadImage(final String imageName) {
        Callback<ResponseBody> callback = new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    InputStream is = response.body().byteStream();
                    Bitmap bitmap = BitmapFactory.decodeStream(is);
                    saveImage(bitmap, imageName);
                } else {
                    try {
                        Log.d("DEBUG", "response error: " + response.errorBody().string().toString());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d("TAG", "Image download error: " + t.getLocalizedMessage());
            }
        };

        this.downloadImage(imageName, callback);

    }

    void downloadImage(final String imageName, Callback<ResponseBody> callback) {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

        Call<ResponseBody> imageCall = apiInterface.downloadImage(this.getLarge(), imageName);
        imageCall.enqueue(callback);

    }

    void saveImage(Bitmap imageToSave, String fileName) {
        File dir = new File(MyApplication.getAppContext().getCacheDir(), "images");
        // create this directory if not already created
        dir.mkdir();
        // create the file in which we will write the contents
        File file = new File(dir, fileName);
        try {
            FileOutputStream out = new FileOutputStream(file);
            imageToSave.compress(Bitmap.CompressFormat.JPEG, 100, out);
            out.flush();
            out.close();
            this.localPath = file.getAbsolutePath();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Bitmap getBitmap() {
        if (this.localPath == null) {
            throw new Error("Image not found");
        }
        File imgFile = new File(this.localPath);
        if (imgFile.exists()) {
            return BitmapFactory.decodeFile(imgFile.getAbsolutePath());
        } else {
            throw new Error("Image not found");
        }
    }

}