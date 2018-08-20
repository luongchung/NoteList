package n7.svmc.activitys;


import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;

import n7.svmc.adapters.AdapterToday;
import n7.svmc.models.Note;
import n7.svmc.notelist.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentToDay extends Fragment implements Comparator<Note> {
    private static final String TAG = "kiemtrakkkkk";
    ArrayList<Note> arrNote=new ArrayList<>();
    AdapterToday adapterToday;
    ListView listView;
    SQLiteDatabase sqLiteDatabase;
    private String DATABASE_NAME="n7svmc.sqlite";
    public FragmentToDay() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_fragment_to_day, container, false);
        listView=view.findViewById(R.id.lvToday);


        ArrayList<Note> arrNote = new ArrayList<>();
        new GetData_all().execute();
        adapterToday =new AdapterToday(this.getActivity(),R.layout.item_today,arrNote);
        listView.setAdapter(adapterToday);

        return view;
    }


    @Override
    public int compare(Note o1, Note o2) {
        return 0;
    }


    public class GetData_all extends AsyncTask<Void, Void, Void> {
        protected void onPreExecute() {
            super.onPreExecute();
           // dialog.show();
        }
        @Override
        protected Void doInBackground(Void... voids) {
            getData_today();
            return null;
        }
        @Override
        protected void onPostExecute(Void aVoid) {
            adapterToday =new AdapterToday(getActivity(),R.layout.item_today,arrNote);
            listView.setAdapter(adapterToday);
            //dialog.dismiss();
        }
    }

    private void getData_today() {
            arrNote.clear();
            sqLiteDatabase=getActivity().openOrCreateDatabase(DATABASE_NAME, Context.MODE_PRIVATE,null);
            String sql="SELECT * FROM note";
            Cursor cursor=sqLiteDatabase.rawQuery(sql,null);
            while (cursor.moveToNext())
            {
                Log.d(TAG, "0 ==: "+cursor.getString(0));
                Log.d(TAG, "1 ==: "+cursor.getString(1));
                Log.d(TAG, "2 ==: "+cursor.getString(2));
                Log.d(TAG, "3 ==: "+cursor.getString(3));
                Log.d(TAG, "4 ==: "+cursor.getString(4));
                Log.d(TAG, "5 ==: "+cursor.getString(5));
                Log.d(TAG, "=============================================");
                Note note =new Note(Integer.parseInt(cursor.getString(0)),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4), cursor.getString(7),
                        cursor.getString(5),
                        Boolean.parseBoolean(cursor.getString(6))
                );
                arrNote.add(note);

            }
            Log.d(TAG,arrNote.get(0).toString());
            cursor.close();

    }

}
