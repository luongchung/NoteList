package n7.svmc.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import n7.svmc.interfaces.ClickDoiTuong;
import n7.svmc.models.Loai;
import n7.svmc.notelist.R;

/**
 * Created by sev_user on 8/20/2018.
 */

public class adapterSpinner extends BaseAdapter {
    ArrayList<Loai> arr;
    Activity activity;
    public adapterSpinner(ArrayList<Loai> arr, Activity activity) {
        this.arr = arr;
        this.activity = activity;
    }

    public adapterSpinner() {
    }

    @Override
    public int getCount() {
        return arr.size();
    }

    @Override
    public Loai getItem(int position) {
        return arr.get(position);
    }

    @Override
    public long getItemId(int position) {
        return arr.get(position).getId();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater=activity.getLayoutInflater();
        View view=layoutInflater.inflate(R.layout.item_sp,parent,false);
        TextView txtMain =view.findViewById(R.id.txtTenLoai);
        txtMain.setText(arr.get(position).getTenLoai());
        return view;
    }
}
