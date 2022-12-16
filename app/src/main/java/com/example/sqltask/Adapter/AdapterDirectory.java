package com.example.sqltask.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sqltask.Models.Directory;
import com.example.sqltask.R;

import java.util.Base64;
import java.util.List;

public class AdapterDirectory extends BaseAdapter {

    private Context mContext;
    List<Directory> directoryList;

    public AdapterDirectory(Context mContext, List<Directory> directoryList) {
        this.mContext = mContext;
        this.directoryList = directoryList;
    }

    @Override
    public int getCount() {
        return directoryList.size();
    }

    @Override
    public Object getItem(int position) {
        return directoryList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return directoryList.get(position).getID();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        View view = View.inflate(mContext, R.layout.item_directory, null);
        TextView Title = view.findViewById(R.id.txtTitleDirectory);
        TextView Number = view.findViewById(R.id.txtNumber);
        TextView Mail = view.findViewById(R.id.txtMail);
        ImageView imageView = view.findViewById(R.id.imageView);
        
        Directory directory = directoryList.get(position);
        Title.setText("ID: " + directory.getID() + " " + directory.getTitle());
        if(directory.getNumber() == null || directory.getNumber().length() < 11){
            Number.setText("Отсутствует");
        }
        else{
            Number.setText(directory.getNumber());
        }
        if(directory.getMail() == null || directory.getMail().length() < 5){
            Mail.setText("Отсутствует");
        }
        else {
            Mail.setText(directory.getMail());
        }

        return view;
    }

}
