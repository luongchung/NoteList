package n7.svmc.activitys;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import n7.svmc.models.Note;
import n7.svmc.notelist.R;

public class ChiTiet extends AppCompatActivity {
    TextView txtChitiet;
    Intent intent;
    TextView txtTieuDe,txtTimeBatDau,txtTimeKetThuc,txtContent,txtDiaChi;
    Button btnSua,btnXoa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet);
        init();
    }

    private void init() {
        txtChitiet= (TextView) findViewById(R.id.txtChitiet);
        txtTieuDe= (TextView) findViewById(R.id.txtTieuDe);
        txtTimeBatDau= (TextView) findViewById(R.id.txtTimeBegin);
        txtTimeKetThuc= (TextView) findViewById(R.id.txtTimeEnd);
        txtContent= (TextView) findViewById(R.id.txtND);
        txtDiaChi= (TextView) findViewById(R.id.txtDiaDiem);

        btnSua= (Button) findViewById(R.id.btnSua);
        btnXoa= (Button) findViewById(R.id.btnXoa);

        Typeface font = Typeface.createFromAsset(this.getAssets(),"fonts/fontmain.ttf");
        txtChitiet.setTypeface(font);
        txtTieuDe.setTypeface(font);
        txtTimeBatDau.setTypeface(font);
        txtTimeKetThuc.setTypeface(font);
        txtContent.setTypeface(font);
        txtDiaChi.setTypeface(font);

        intent=getIntent();
        Note note = (Note) intent.getSerializableExtra("chitiet");

        txtTieuDe.setText(note.getTitle());
        txtTimeBatDau.setText("Thời gian bắt đầu: "+note.getTimeBegin());
        txtTimeKetThuc.setText("Thời gian kết thúc: "+note.getTimeEnd());
        txtContent.setText("Nội dung: "+note.getContent());
        txtDiaChi.setText("Địa điểm: "+note.getDiaDiem());

    }
}
