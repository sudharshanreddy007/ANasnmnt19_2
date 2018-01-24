package com.sudharshan.user.anasnmnt19_2;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by IIS 5 on 24-10-2017.
 */

//created MyAdapter class which extends ArrayAdapter of Model class
public class MyAdapter extends ArrayAdapter<Model> {
    //creating variables for context,resource,model
    Context context ;
    //creating modellist object
    List<Model>modelList=new ArrayList<>();
    int resource;
    Model model;
    LayoutInflater inflater;

    // Adapter is simply used to achieve the listview concept. Not only for displaying the list of data but also the some custom view.
    //First parameter: Context .This context is used to get an inflater to inflate the views in getView method.
    //Second parameter: resource Layout for the row
    // Third parameter: ID of the TextView to which the data is written
    // Fourth parameter: The array of data
    public MyAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Model> objects) {
        super(context,resource,objects);
        this.context=context;
        this.resource=resource;
        modelList=objects;
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }
    //in Myadapter we have override the method called getview ,getcount and getItemid
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //View view = convertView;
//in this view layoutinflator we inflate listmodel
        View  view = LayoutInflater.from(context).inflate(R.layout.list_model, parent,false);
        //initialising the textviews from the .xml file
        TextView textView = view.findViewById(R.id.textView);
        TextView textView2 = view.findViewById(R.id.textView2);
        TextView textView3 = view.findViewById(R.id.textView3);
        //creating object where we get the position the model obj
        model = new Model();
        model =  modelList.get(position);
        //here it sets the values of the model obj
        textView.setText(model.getName());
        textView2.setText(model.getVotes());
        textView3.setText(model.getId());

        return view;
    }
    @Override
    public int getCount() {
        return modelList.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
}