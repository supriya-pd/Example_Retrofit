package com.example.pokemon;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    JsonPlaceHolderApi jsonPlaceHolderApi;

    //Map<String,String> parameters;
    private RecyclerView mRecyclerView;
    private ExampleAdapter
            mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private SQLiteDatabase mDatabase;
    List<PokeArray> poke_infos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // poke_infos=new ArrayList<Poke_Info>();
        PokeDBHelper dbHelper=new PokeDBHelper(this);
        mDatabase=dbHelper.getWritableDatabase();
        Retrofit retrofit=new Retrofit.Builder().baseUrl("https://pokeapi.co/api/v2/")
                .addConverterFactory(GsonConverterFactory.create()).build();

        jsonPlaceHolderApi=retrofit.create(JsonPlaceHolderApi.class);

        mRecyclerView=findViewById((R.id.recycler_view));
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager=new LinearLayoutManager(this);
        mAdapter=new ExampleAdapter(this,getAllItems());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);


        Call<Poke_Info> call=jsonPlaceHolderApi.getPoke();

        call.enqueue(new Callback<Poke_Info>() {
            @Override
            public void onResponse(Call<Poke_Info> call, Response<Poke_Info> response) {

                if (!response.isSuccessful()) {
                    // textView.setText("Code: "+response.code());
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("Information").setMessage("Code: " + response.code()).setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();

                    return;
                }


                poke_infos = response.body().getResults();
                ContentValues cv = new ContentValues();

                for (PokeArray poke_info : poke_infos) {
                    cv.put(PokeContract.PokeEntry.COLUMN_NAME, poke_info.getName());
                    cv.put(PokeContract.PokeEntry.COLUMN_IMAGE, poke_info.getUrl());


                    mDatabase.insert(PokeContract.PokeEntry.TABLE_NAME, null, cv);
                    mAdapter.swapCursor(getAllItems());

                }


            }

            @Override
            public void onFailure(Call<Poke_Info> call, Throwable t) {
                AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Information").setMessage(t.getMessage()).setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                AlertDialog alertDialog=builder.create();
                alertDialog.show();


            }

    });
    }
    private Cursor getAllItems()
    {
        return mDatabase.query(PokeContract.PokeEntry.TABLE_NAME,null,
                null, null, null, null, PokeContract.PokeEntry.COLUMN_TIMESTAMP+" DESC");
    }





  /*  private void getPosts()
    {
         parameters=new HashMap<>();
        parameters.put("userId","2");
        parameters.put("_sort","id");
        parameters.put("_order","desc");

        Call<List<Poke_Info>> call=jsonPlaceHolderApi.getPoke(parameters new Integer[]{2,3,6},"id","desc");
        // you can pass null,null,null if you don't want to create a query parameter,so you don't
        // have to create different methods with different parameters
        //but int is not nullable being primitive ,so in your interface use the Integer class

        call.enqueue(new Callback<List<Poke_Info>>() {
            @Override
            public void onResponse(Call<List<Poke_Info>> call, Response<List<Poke_Info>> response) {

                if(!response.isSuccessful())
                {
                    textView.setText("Code: "+response.code());
                    return;
                }

                List<Poke_Info> poke_infos=response.body();
                for(Poke_Info poke_info:poke_infos)
                {
                    String content="";
                    content+="ID: "+poke_info.getId()+"\n";
                    content+="User ID: "+poke_info.getUserId()+"\n";
                    content+="Title: "+poke_info.getTitle()+"\n";
                    content+="Text: "+poke_info.getText()+"\n\n";
                    textView.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<Poke_Info>> call, Throwable t) {
                textView.setText(t.getMessage());
            }
        });

    }
    private void getComments(){
     Call<List<Comment>> call=jsonPlaceHolderApi.getComments("/post3/comments");//here you can pass full url:that will replace base url
     call.enqueue(new Callback<List<Comment>>() {
         @Override
         public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {
             if(!response.isSuccessful()){
                 textView.setText("Code: "+response.code());
                 return;
             }
             List<Comment> comments=response.body();
             for(Comment comment:comments)
             {
                 String content="";
                 content+="ID: "+comment.getId()+"\n";
                 content+="Post ID: "+comment.getPostId()+"\n";
                 content+="Name: "+comment.getName()+"\n";
                 content+="Email"+comment.getEmail()+"\n";
                 content+="Text: "+comment.getText()+"\n\n";
                 textView.append(content);
             }
         }

         @Override
         public void onFailure(Call<List<Comment>> call, Throwable t) {
           textView.setText(t.getMessage());
         }
     });
    }*/
}
