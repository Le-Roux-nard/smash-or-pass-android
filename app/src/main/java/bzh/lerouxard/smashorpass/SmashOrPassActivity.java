package bzh.lerouxard.smashorpass;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import bzh.lerouxard.smashorpass.apiImplementation.ApiClient;
import bzh.lerouxard.smashorpass.apiImplementation.ApiInterface;
import bzh.lerouxard.smashorpass.apiImplementation.ApiQuery;
import bzh.lerouxard.smashorpass.apiImplementation.ApiResponse;
import bzh.lerouxard.smashorpass.apiImplementation.Character;
import bzh.lerouxard.smashorpass.cardstackview.CardStackAdapter;
import bzh.lerouxard.smashorpass.cardstackview.CardStackLayoutManager;
import bzh.lerouxard.smashorpass.cardstackview.CardStackListener;
import bzh.lerouxard.smashorpass.cardstackview.CardStackView;
import bzh.lerouxard.smashorpass.cardstackview.Direction;
import bzh.lerouxard.smashorpass.cardstackview.Spot;
import bzh.lerouxard.smashorpass.cardstackview.StackFrom;
import bzh.lerouxard.smashorpass.cardstackview.SwipeAnimationSetting;
import bzh.lerouxard.smashorpass.cardstackview.SwipeableMethod;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SmashOrPassActivity extends AppCompatActivity {
    private static final Integer MIN_CHARS_IN_LIST_TO_PROVIDE_CONTINUED_SERVICE = 15;
    private static CardStackView cardStackView;
    public static List<Integer> characterIdList = new ArrayList<Integer>();

    private void noConnectionAlert() {
        Toast.makeText(getApplicationContext(), "Internet Connection lost", Toast.LENGTH_SHORT).show();
    }

    private Callback<ApiResponse> randomCharCallback = new Callback<ApiResponse>() {
        @Override
        public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
            Log.i("DEBUG", "getRandomChar: onResponse");
            if (response.isSuccessful()) {
                Log.i("DEBUG", "getRandomChar: successful");
                ApiResponse result = response.body();
                List<Character> characters = result.getData().getPage().getCharacters();
                for (Character character : characters) {
                    Boolean defaultImageCheck = character.getImage().getLarge().equals("https://s4.anilist.co/file/anilistcdn/character/large/default.jpg");
                    Boolean alreadySeenCheck = characterIdList.contains(character.getId());
                    Boolean hasGenderCheck = character.getGender() != null;
                    Boolean hasGenderFilterCheck = MainActivity.filterGender != null;
                    Boolean genderCheck = !hasGenderFilterCheck || (hasGenderFilterCheck && hasGenderCheck && character.getGender().equals(MainActivity.filterGender));

                    if (!defaultImageCheck && !alreadySeenCheck && genderCheck) {
                        characterIdList.add(character.getId());
                        Spot s = new Spot("", "", character.getImage().getLarge());
                        ((CardStackAdapter) cardStackView.getAdapter()).addSpot(s);
                        if (((CardStackAdapter) cardStackView.getAdapter()).getSpots().size() < 5) {
                            ((CardStackAdapter) cardStackView.getAdapter()).notifyDataSetChanged();
                        }
                    }
                }
                if (characterIdList.size() < MIN_CHARS_IN_LIST_TO_PROVIDE_CONTINUED_SERVICE) {
                    getRandomChars(randomCharCallback);
                }
            } else {
                noConnectionAlert();
            }
        }

        @Override
        public void onFailure(Call<ApiResponse> call, Throwable t) {
            Log.e("DEBUG", t.toString());
        }
    };
    private Button helpButton, likeButton, passButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_smash_or_pass);
        getRandomChars(randomCharCallback);

        helpButton = findViewById(R.id.helpBtn);

        helpButton.setOnClickListener(v -> {
            Intent intent = new Intent(SmashOrPassActivity.this, HelpActivity.class);
            startActivity(intent);
        });

        cardStackView = findViewById(R.id.card_stack_view);

        CardStackListener listener = new CardStackListener() {
            @Override
            public void onCardDragging(Direction direction, float ratio) {

            }

            @Override
            public void onCardSwiped(Direction direction) {
                characterIdList.remove(0);
                loadNewCharacter();
            }

            @Override
            public void onCardRewound() {

            }

            @Override
            public void onCardCanceled() {

            }

            @Override
            public void onCardAppeared(View view, int position) {

            }

            @Override
            public void onCardDisappeared(View view, int position) {

            }
        };

        CardStackLayoutManager cardStackLayoutManager = new CardStackLayoutManager(MyApplication.getAppContext(), listener);
        cardStackLayoutManager.setDirections(Direction.HORIZONTAL);
        cardStackLayoutManager.setCanScrollVertical(false);
        cardStackLayoutManager.setStackFrom(StackFrom.Top);
        cardStackLayoutManager.setSwipeableMethod(SwipeableMethod.Manual);

        cardStackView.setLayoutManager(cardStackLayoutManager);


        ArrayList<Spot> spots = new ArrayList<>();
        cardStackView.setAdapter(new CardStackAdapter(spots));
    }

    private void loadNewCharacter() {
        if (characterIdList.size() < MIN_CHARS_IN_LIST_TO_PROVIDE_CONTINUED_SERVICE) {
            getRandomChars(randomCharCallback);
        }
    }

    private void getRandomChars(Callback<ApiResponse> arrayCallback) {
        Log.i("DEBUG", "getRandomChar: début fonction");
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<ApiResponse> call = apiInterface.getRandomChar(new ApiQuery());
        call.enqueue(arrayCallback);
        Log.i("DEBUG", "getRandomChar: fin fonction");
    }
}
