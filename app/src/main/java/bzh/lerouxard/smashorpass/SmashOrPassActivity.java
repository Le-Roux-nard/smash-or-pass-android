package bzh.lerouxard.smashorpass;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.io.InputStream;

import bzh.lerouxard.smashorpass.apiImplementation.ApiClient;
import bzh.lerouxard.smashorpass.apiImplementation.ApiInterface;
import bzh.lerouxard.smashorpass.apiImplementation.ApiQuery;
import bzh.lerouxard.smashorpass.apiImplementation.ApiResponse;

import bzh.lerouxard.smashorpass.apiImplementation.Character;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SmashOrPassActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_smash_or_pass);
        ImageView imageView = findViewById(R.id.CharacterPicture);

        Callback<ResponseBody> downloadImageCallback = new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    InputStream is = response.body().byteStream();
                    Bitmap bitmap = BitmapFactory.decodeStream(is);
                    imageView.setImageBitmap(bitmap);
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

        Callback<ApiResponse> randomCharCallback = new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                Log.i("DEBUG", "getRandomChar: onResponse");
                if (response.isSuccessful()) {
                    Log.i("DEBUG", "getRandomChar: successful");
                    ApiResponse result = response.body();
                    Character character = result.getData().getPage().getCharacters().get(0);
                    character.downloadImage(downloadImageCallback);
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                Log.e("DEBUG", t.toString());
                /// error response like disconnect to  server. failed to connect or etc....
            }
        };

        getRandomChar(randomCharCallback);
    }

    private void getRandomChar(Callback<ApiResponse> arrayCallback) {
        Log.i("DEBUG", "getRandomChar: début fonction");
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<ApiResponse> call = apiInterface.getRandomChar(new ApiQuery());
        call.enqueue(arrayCallback);
        Log.i("DEBUG", "getRandomChar: fin fonction");
    }
}
