package bzh.lerouxard.smashorpass;

import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class HelpActivity extends AppCompatActivity {
    private static HelpActivity instance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        instance = this;
        setContentView(R.layout.activity_help);

        Button returnButton = findViewById(R.id.backBtn);
        returnButton.setOnClickListener(v -> {
            this.finish();
        });
    }
}
