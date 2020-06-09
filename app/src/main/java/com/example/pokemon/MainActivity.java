package com.example.pokemon;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
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
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    List<Poke_Info> poke_infos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Retrofit retrofit=new Retrofit.Builder().baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create()).build();

        jsonPlaceHolderApi=retrofit.create(JsonPlaceHolderApi.class);
        Call<List<Poke_Info>> call=jsonPlaceHolderApi.getPoke();
        call.enqueue(new Callback<List<Poke_Info>>() {
            @Override
            public void onResponse(Call<List<Poke_Info>> call, Response<List<Poke_Info>> response) {

                if(!response.isSuccessful())
                {
                   // textView.setText("Code: "+response.code());
                    AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("Information").setMessage("Code: "+response.code()).setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    AlertDialog alertDialog=builder.create();
                    alertDialog.show();

                    return;
                }

                poke_infos=response.body();

            }

            @Override
            public void onFailure(Call<List<Poke_Info>> call, Throwable t) {
                //textView.setText(t.getMessage());
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
        mRecyclerView=findViewById((R.id.recycler_view));
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager=new LinearLayoutManager(this);
        mAdapter=new ExampleAdapter(poke_infos);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

      //  getPosts();
      //  getComments();
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
