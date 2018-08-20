package n7.svmc.activitys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import n7.svmc.notelist.R;

public class AddLoai extends AppCompatActivity {
    Button btnAdd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_loai);
        init();
    }

    private void init() {
        btnAdd= (Button) findViewById(R.id.btnAdd);

    }
}
