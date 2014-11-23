package com.rsc.idonor.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.rsc.idonor.R;
import com.rsc.idonor.model.BloodType;
import com.rsc.idonor.views.TextViewBoldItalic;

import java.util.List;

/**
 * Created by darkosmoljo on 23/11/14.
 */
public class SpinnerBloodTypeAdapter extends ArrayAdapter<BloodType>{

    private List<BloodType> dataSource;
    private LayoutInflater mInflater;

    public SpinnerBloodTypeAdapter(Context context, int resource, List<BloodType> objects) {
        super(context, resource, objects);

        this.dataSource = objects;

        this.mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.custom_spinner_item, parent, false);
            holder = new ViewHolder();

            if (convertView != null) {
                holder.label = (TextViewBoldItalic) convertView.findViewById(R.id.tvBloodTypeSpinnerItem);
                convertView.setTag(holder);
            }
        } else {
            holder = (ViewHolder) convertView.getTag();
        }


        holder.label.setText(dataSource.get(position).toString());

        return convertView;
    }

    private class ViewHolder {
        TextViewBoldItalic label;
    }

}
