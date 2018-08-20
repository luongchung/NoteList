package n7.svmc.activitys;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.QuickContactBadge;
import android.widget.TextView;
import android.widget.Toast;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import n7.svmc.adapters.ViewPagerAdapter;
import n7.svmc.interfaces.ClickDoiTuong;
import n7.svmc.notelist.R;

public class MainActivity extends AppCompatActivity implements ClickDoiTuong {
    ListView listView;
    ViewPager viewPager;
    TextView txtNameMain;
    ViewPagerAdapter viewPagerAdapter;
    PagerTabStrip pagerTabStrip;
    SQLiteDatabase sqLiteDatabase=null;
    private String DATABASE_NAME="n7svmc.sqlite";
    private String DB_PATH="/databases/";

    TextView txt1,txt2,txt3,txt4;
    LinearLayout btn1,btn2,btn3,btn4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        events();
        setupTabPaper();
        xuLySaoChepSQLite();
    }

    private void events() {
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(MainActivity.this,AddLoai.class);
                startActivity(intent);
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    private void init() {
        listView = (ListView) findViewById(R.id.lvToday);
        txtNameMain= (TextView) findViewById(R.id.txtNameMain);

        txt1= (TextView) findViewById(R.id.txt1);
        txt2= (TextView) findViewById(R.id.txt2);
        txt3= (TextView) findViewById(R.id.txt3);
        txt4= (TextView) findViewById(R.id.txt4);

        btn1= (LinearLayout) findViewById(R.id.btn1);
        btn2= (LinearLayout) findViewById(R.id.btn2);
        btn3= (LinearLayout) findViewById(R.id.btn3);
        btn4= (LinearLayout) findViewById(R.id.btn4);

        Typeface font = Typeface.createFromAsset(this.getAssets(),"fonts/fontmain.ttf");
        txtNameMain.setTypeface(font);
        txt1.setTypeface(font);
        txt2.setTypeface(font);
        txt3.setTypeface(font);
        txt4.setTypeface(font);

    }
    private void xuLySaoChepSQLite() {
        File dbfile= getDatabasePath(DATABASE_NAME);
        if (!dbfile.exists())
        {
            try
            {
                saoChepDatabaseTuAsset();
            }
            catch (Exception ex)
            {
                Toast.makeText(this,ex.toString(), Toast.LENGTH_SHORT).show();
            }
        }
    }
    private void setupTabPaper() {
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragments(new FragmentToDay(), "HÔM NAY");
        viewPagerAdapter.addFragments(new FragmentAll(), "TẤT CẢ");
        viewPager.setAdapter(viewPagerAdapter);;
        Typeface fontTypeFace= Typeface.createFromAsset(getApplication().getAssets(),
                "fonts/fontmain.ttf");

        pagerTabStrip= (PagerTabStrip) findViewById(R.id.pagerTabStrip);
        for (int i = 0; i < pagerTabStrip.getChildCount(); ++i) {
            View nextChild = pagerTabStrip.getChildAt(i);
            if (nextChild instanceof TextView) {
                TextView textViewToConvert = (TextView) nextChild;
                textViewToConvert.setTypeface(fontTypeFace);
                textViewToConvert.setAllCaps(true);
            }
        }
    }
    private void saoChepDatabaseTuAsset() {
        try {
            // Load database từ assets
            InputStream myInput = getAssets().open(DATABASE_NAME);
            // Đường dẫn tới file database
            String outFileName = layDuongDanLuuTru();

            File f= new File(getApplicationInfo().dataDir+DB_PATH);
            if(!f.exists())
            {
                f.mkdir();//chưa có đường dẫn thì tạo đường dẫn database
            }
            // Tạo một outputstream theo kiểu file
            OutputStream myOutput = new FileOutputStream(outFileName);
            //chuyển các byte từ input sang output
            byte[] buffer = new byte[1024];
            int length;
            while ((length = myInput.read(buffer))>0){
                myOutput.write(buffer, 0, length);
            }
            //Close the streams
            myOutput.flush();
            myOutput.close();
            myInput.close();
        }
        catch (Exception ex)
        {
            Toast.makeText(MainActivity.this,"Lỗi sao chép database",Toast.LENGTH_LONG).show();
        }
    }
    private String layDuongDanLuuTru() {
        return getApplicationInfo().dataDir+DB_PATH+DATABASE_NAME;
    }
    @Override
    public void clickitem(int vitri) {
        Toast.makeText(this, vitri, Toast.LENGTH_SHORT).show();
    }
}
