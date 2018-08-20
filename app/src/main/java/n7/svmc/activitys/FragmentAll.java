package n7.svmc.activitys;


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

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;


import java.util.ArrayList;


import n7.svmc.adapters.AdapterToday;
import n7.svmc.adapters.adapterSpinner;
import n7.svmc.models.Loai;
import n7.svmc.models.Note;
import n7.svmc.notelist.R;



public class FragmentAll extends Fragment {

    private static final String TAG = "kiemtrakkkkk";
    ArrayList<String> arr;
    ArrayList<Loai> arrLoai=new ArrayList<>();
    int _id=0;
    AdapterToday adapterAll;
    ListView listView;
    SQLiteDatabase sqLiteDatabase;
    ArrayAdapter<String> adapter;
    private String DATABASE_NAME="n7svmc.sqlite";
    ArrayList<Note> arrNote;
    Spinner spnCategory;
    public FragmentAll() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_fragment_all, container, false);
        spnCategory=view.findViewById(R.id.sp_loai);
        listView=view.findViewById(R.id.lv_all);
        arr=new ArrayList<>();
        arrLoai=new ArrayList<>();
        arrNote = new ArrayList<>();
        new GetData_all().execute();
        adapter=new ArrayAdapter<>(this.getActivity(), android.R.layout.simple_spinner_item,arr);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spnCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                _id=getID(position);
                getall();
                adapterAll.notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spnCategory.setAdapter(adapter);
        adapterAll =new AdapterToday(getActivity(),R.layout.item_today,arrNote);
        listView.setAdapter(adapterAll);
        return view ;
    }


    private int getID(int pt){
        try {
            return arrLoai.get(pt).getId();
        }catch (Exception ex){}
        return 0;
    }
    public class GetData_all extends AsyncTask<Void, Void, Void> {
        protected void onPreExecute() {
            super.onPreExecute();

        }
        @Override
        protected Void doInBackground(Void... voids) {
            getall();
            getsp();
            return null;
        }
        @Override
        protected void onPostExecute(Void aVoid) {
            adapterAll.notifyDataSetChanged();
            adapter.notifyDataSetChanged();
        }
    }
    private void getall() {
        arrNote.clear();
        sqLiteDatabase=getActivity().openOrCreateDatabase(DATABASE_NAME, Context.MODE_PRIVATE,null);
        String sql;
        if(_id==0)
        sql="SELECT * FROM note";
        else sql="SELECT * FROM note WHERE idcategory="+_id;
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
        cursor.close();
    }
    private void getsp() {
        arr.clear();
        arr.add("Tất cả");
        arrLoai.clear();
        arrLoai.add(new Loai(0,"Tất cả"));
        sqLiteDatabase=getActivity().openOrCreateDatabase(DATABASE_NAME, Context.MODE_PRIVATE,null);
        String sql="SELECT * FROM category";
        Cursor cursor=sqLiteDatabase.rawQuery(sql,null);
        while (cursor.moveToNext())
        {
            Log.d(TAG, "0 ==: "+cursor.getString(0));
            Log.d(TAG, "1 ==: "+cursor.getString(1));
            Log.d(TAG, "=============================================");
            arr.add(cursor.getString(1));
            arrLoai.add(new Loai(Integer.parseInt(cursor.getString(0)),cursor.getString(1)));
        }
        cursor.close();

    }

}
