package n7.svmc.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.method.DateTimeKeyListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import n7.svmc.activitys.ChiTiet;
import n7.svmc.activitys.MainActivity;
import n7.svmc.models.Note;
import n7.svmc.notelist.R;

/**
 * Created by sev_user on 8/20/2018.
 */

public class AdapterToday extends ArrayAdapter<Note> {
    Activity context;
    int resource;
    ArrayList<Note> objects;
    public AdapterToday(Activity context, int resource, ArrayList<Note> objects) {
        super(context, resource, objects);
        this.context=context;
        this.resource=resource;
        this.objects=objects;
    }
    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View view =inflater.inflate(resource,parent,false);
        TextView title =view.findViewById(R.id.txtTitle);
        TextView ngay =view.findViewById(R.id.txtNgay);
        TextView diadiem =view.findViewById(R.id.txtDiaDiem);
        ImageView baothuc =view.findViewById(R.id.btnAlam);
        final Note note = objects.get(position);
        title.setText(note.getTitle());


        Typeface font = Typeface.createFromAsset(context.getAssets(),"fonts/fontmain.ttf");
        ngay.setTypeface(font);
        diadiem.setTypeface(font);
        title.setTypeface(font);

        SimpleDateFormat sm_all = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        Date date =new Date();
        Date date1 =new Date();
        try {
            date =sm_all.parse(note.getTimeBegin());
            date1 =sm_all.parse(note.getTimeEnd());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat sm_hour = new SimpleDateFormat("HH:mm");
        SimpleDateFormat sm_date = new SimpleDateFormat("dd/MM/yyyy");

        String strDate = sm_date.format(date);
        String strhour = sm_hour.format(date);

        String strDate1 = sm_date.format(date1);
        String strhour1 = sm_hour.format(date1);

        ngay.setText(strhour+" "+strDate+" --> "+strhour1+" "+strDate1);
        diadiem.setText("Địa điểm: "+note.getDiaDiem());
        if(note.isBt()){
            baothuc.setVisibility(View.VISIBLE);
        }else baothuc.setVisibility(View.INVISIBLE);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(context,ChiTiet.class);
                intent.putExtra("chitiet",note);
                context.startActivity(intent);
            }
        });
        return view;
    }
}
