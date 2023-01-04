package bzh.lerouxard.smashorpass;

import static bzh.lerouxard.smashorpass.apiImplementation.Image.saveImage;

import android.app.NotificationChannel;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

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

    ImageView imageView;
    private static List<Character> characterList = new ArrayList<Character>();
    private static boolean firstImage = true, firstCharacter = true;
    public static List<Integer> characterIdList = new ArrayList<Integer>();
    private Callback<ApiResponse> randomCharCallback = new Callback<ApiResponse>() {
        @Override
        public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
            Log.i("DEBUG", "getRandomChar: onResponse");
            if (response.isSuccessful()) {
                Log.i("DEBUG", "getRandomChar: successful");
                ApiResponse result = response.body();
                Character character = result.getData().getPage().getCharacters().get(0);
                characterIdList.add(character.getId());
                if (character.getImage().getLarge().equals("https://s4.anilist.co/file/anilistcdn/character/large/default.jpg") || characterList.contains(character)) {
                    getRandomChar(randomCharCallback);
                    return;
                }
                characterList.add(character);
                character.downloadImage(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        Log.i("DEBUG", "getRandomChar: onResponse");
                        if (response.isSuccessful()) {
                            InputStream is = response.body().byteStream();
                            Bitmap bitmap = BitmapFactory.decodeStream(is);
                            saveImage(character.getImage(), bitmap, character.getId() + ".jpg");
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Log.e("DEBUG", t.toString());
                        /// error response like disconnect to  server. failed to connect or etc....
                    }

                    ;
                });
                if (firstCharacter) {
                    firstCharacter = false;
                    loadNewCharacter(character);
                }
            }
        }

        @Override
        public void onFailure(Call<ApiResponse> call, Throwable t) {
            Log.e("DEBUG", t.toString());
        }
    };
    private Button smashButton;
    private Button passButton;
    private View.OnClickListener defaultButtonClickListener = v -> {
        getRandomChar(randomCharCallback);
        if (firstImage) {
            firstImage = false;
            characterList.remove(0);
        }
        if (characterList.size() > 0) {
            loadNewCharacter();
        } else {
            //alert with a notification that no characters are available right now
            Toast.makeText(MyApplication.getAppContext(), "Aucun personnage n'est disponible\nVeuillez patienter quelques instants", Toast.LENGTH_SHORT).show();
            smashButton.setEnabled(false);
            passButton.setEnabled(false);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    // This method will be executed once the timer is over
                    smashButton.setEnabled(true);
                    passButton.setEnabled(true);
                }
            }, 5000);// set time as per your requirement
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_smash_or_pass);
        for (int i = 0; i < 25; i++) {
            getRandomChar(randomCharCallback);
        }

        imageView = findViewById(R.id.CharacterPicture);
        smashButton = findViewById(R.id.button_smash);
        passButton = findViewById(R.id.button_pass);

        smashButton.setOnClickListener(defaultButtonClickListener);
        passButton.setOnClickListener(defaultButtonClickListener);
    }

    private void loadNewCharacter() {
        loadNewCharacter(null);
    }

    private void loadNewCharacter(Character character) {
        getRandomChar(randomCharCallback);
        if (character == null && characterList.size() > 0) {
            character = characterList.get(0);
            characterList.remove(0);
        }
        if (character == null) {
            return;
        }
        Bitmap bitmap = character.getImage().getBitmap();
        if (bitmap == null) {
            Character finalCharacter = character;
            character.getImage().downloadImage(character.getId() + ".jpg", new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    Log.i("DEBUG", "getRandomChar: onResponse");
                    if (response.isSuccessful()) {
                        InputStream is = response.body().byteStream();
                        Bitmap bitmap = BitmapFactory.decodeStream(is);
                        saveImage(finalCharacter.getImage(), bitmap, finalCharacter.getId() + ".jpg");
                        imageView.setImageBitmap(bitmap);
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    Log.e("DEBUG", t.toString());
                    /// error response like disconnect to  server. failed to connect or etc....
                }
            });
        } else {
            imageView.setImageBitmap(bitmap);
        }
        characterIdList.add(character.getId());
    }

    private void getRandomChar(Callback<ApiResponse> arrayCallback) {
        Log.i("DEBUG", "getRandomChar: début fonction");
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<ApiResponse> call = apiInterface.getRandomChar(new ApiQuery());
        call.enqueue(arrayCallback);
        Log.i("DEBUG", "getRandomChar: fin fonction");
    }
}
