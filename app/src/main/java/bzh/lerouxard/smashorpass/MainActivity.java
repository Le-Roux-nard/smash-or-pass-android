package bzh.lerouxard.smashorpass;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button selectedButton, menButton, womenButton, mixtButton;
    static String filterGender = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent smashOrPassIntent = new Intent(this, SmashOrPassActivity.class);

        menButton = findViewById(R.id.button_select_men);
        womenButton = findViewById(R.id.button_select_women);
        mixtButton = findViewById(R.id.button_select_mixt);
        selectedButton = mixtButton;

        View.OnClickListener defaultChooseFilterListener = v -> {
            selectedButton.setBackgroundTintList(getApplicationContext().getResources().getColorStateList(R.color.button_color));
            selectedButton = (Button) v;
            selectedButton.setBackgroundTintList(getApplicationContext().getResources().getColorStateList(R.color.purple_500));

            switch (v.getId()) {
                case R.id.button_select_men: {
                    filterGender = "Male";
                    break;
                }
                case R.id.button_select_women: {
                    filterGender = "Female";
                    break;
                }
                default: {
                    filterGender = null;
                    break;
                }
            }
        };

        menButton.setOnClickListener(defaultChooseFilterListener);
        womenButton.setOnClickListener(defaultChooseFilterListener);
        mixtButton.setOnClickListener(defaultChooseFilterListener);


        Button startButton = findViewById(R.id.start_button);
        startButton.setOnClickListener(v ->

        {
            //switch to SmashOrPassActivity

            startActivity(smashOrPassIntent);
        });
    }
}