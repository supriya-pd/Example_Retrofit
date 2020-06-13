package com.example.pokemon;

import java.lang.String;
import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import static java.security.AccessController.getContext;

public class ExampleAdapter extends RecyclerView.Adapter<ExampleAdapter.ExampleViewHolder> {
    private Context mContext;
    private Cursor mCursor;
    int i;

    public ExampleAdapter(Context context,Cursor cursor) {

        //this.info=new ArrayList<Poke_Info>();
       mContext=context;
       mCursor=cursor;
       i=0;

    }

    public static class ExampleViewHolder extends RecyclerView.ViewHolder {
        public ImageView mImageView;
        public TextView mTextView;

        public ExampleViewHolder(@NonNull View itemView) {

            super(itemView);
            mImageView = itemView.findViewById(R.id.imageView);
            mTextView = itemView.findViewById(R.id.textView);

        }
    }



    @NonNull
    @Override
    public ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       LayoutInflater inflator=LayoutInflater.from(mContext);
       View view=inflator.inflate(R.layout.example_item,parent,false);
       return new ExampleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExampleViewHolder holder, int position) {
        if(!mCursor.moveToPosition(position))
            return;


        String name=mCursor.getString(mCursor.getColumnIndex(PokeContract.PokeEntry.COLUMN_NAME));
        String image=mCursor.getString(mCursor.getColumnIndex(PokeContract.PokeEntry.COLUMN_IMAGE));


         holder.mTextView.setText(name);

       Picasso.get().load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/"+
              Integer.toString(i++)+".png").into(holder.mImageView);
      // holder.mImageView.setImageResource(R.drawable.ic_launcher_background);

    }

    @Override
    public int getItemCount() {
        try {
            return mCursor.getCount();
        } catch (NullPointerException e) {

        }


        return 0;
    }

    public void swapCursor(Cursor newCursor)
    {
        if(mCursor!=null)
            mCursor.close();
        mCursor=newCursor;
        if(newCursor!=null)
            notifyDataSetChanged();
    }
}