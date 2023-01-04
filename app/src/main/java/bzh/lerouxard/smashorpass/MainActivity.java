package bzh.lerouxard.smashorpass;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent smashOrPassIntent = new Intent(this, SmashOrPassActivity.class);

        Button startButton = findViewById(R.id.start_button);
        startButton.setOnClickListener(v -> {
            //switch to SmashOrPassActivity

            startActivity(smashOrPassIntent);
        });
    }
}