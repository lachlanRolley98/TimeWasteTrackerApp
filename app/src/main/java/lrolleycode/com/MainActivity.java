package lrolleycode.com;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView totalSaved = (TextView) findViewById((R.id.bTotalSaved));
        Button workButton = (Button) findViewById(R.id.bWork);
        Button pauseButton = (Button) findViewById(R.id.bPause);
        Button playButton = (Button) findViewById(R.id.bPlay);

        workButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                totalSaved.setText("Sup dawg");
            }
        });
    }
}