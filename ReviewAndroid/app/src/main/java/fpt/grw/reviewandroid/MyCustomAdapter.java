package fpt.grw.reviewandroid;


import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

import entities.ExamDetailEntity;

public class MyCustomAdapter implements ListAdapter {
    Context context;
    List<ExamDetailEntity> myList;

    public MyCustomAdapter(Context context, List<ExamDetailEntity> myList) {
        this.context = context;
        this.myList = myList;
    }

    @Override
    public boolean areAllItemsEnabled() {
        return true;
    }

    @Override
    public boolean isEnabled(int i) {
        return true;
    }

    @Override
    public void registerDataSetObserver(DataSetObserver dataSetObserver) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {

    }

    @Override
    public int getCount() {
        return myList.size();
    }

    @Override
    public Object getItem(int i) {
        return myList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view==null){
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            view = layoutInflater.inflate(R.layout.list_item, null);
            TextView exam_detailID = view.findViewById(R.id.textView);
            TextView exam_detailName = view.findViewById(R.id.textView2);
            ImageView image = view.findViewById(R.id.imageView2);

            ExamDetailEntity entity =  myList.get(i);
            exam_detailID.setText(String.valueOf(entity.getDetail_id()) );
            exam_detailName.setText(entity.getDetail_question());
            Picasso.with(context)
                    .load(entity.getDetail_picture_url())
                    .into(image);

            //register the event user click on item
            view.setOnClickListener(view1 -> {
                Toast.makeText(context, "You clicked " + String.valueOf(entity.getDetail_id()),
                        Toast.LENGTH_SHORT).show();
            });

        }
        return view;
    }

    @Override
    public int getItemViewType(int i) {
        return 0;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }
}
