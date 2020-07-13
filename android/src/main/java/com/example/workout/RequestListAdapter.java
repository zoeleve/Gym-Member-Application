package com.example.workout;


import android.app.VoiceInteractor;
import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ArrayAdapter;
import android.widget.TextView;


import java.util.ArrayList;




public class RequestListAdapter extends ArrayAdapter<Request> {

    private static final String TAG = "RequestListAdapter";

    private Context mContext;
    private int mResource;
    private int lastPosition = -1;

    /**
     * Holds variables in a View
     */
    private static class ViewHolder {

        TextView name;
        TextView tpname;
    }

    /**
     * Default constructor for the RequestListAdapter
     * @param context
     * @param resource
     * @param objects
     */
    public RequestListAdapter(Context context, int resource, ArrayList<Request> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //get the persons information
        String name = getItem(position).getuserName();
        String tpname = getItem(position).getTeamProgramName();


        //Create the person object with the information
        Request req = new Request(name,tpname);

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource ,parent ,false);
        TextView tvName = (TextView) convertView.findViewById(R.id.textView1);
        TextView tvTpname = (TextView) convertView.findViewById(R.id.textView2);

        tvName.setText(name);
        tvTpname.setText(tpname);

        return convertView;


    }
}