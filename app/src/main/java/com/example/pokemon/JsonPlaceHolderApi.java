package com.example.pokemon;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

public interface JsonPlaceHolderApi {
    @GET("pokemon")  //here you can also pass full url:that wil replace the base url
    Call<Poke_Info> getPoke(//@Query("userId") Integer[] userId,
                                // @Query("albumId") Integer userId
                                 // @Query("_sort") String sort,
                                  /*@Query("_order") String order*/);
   /* @GET("photos")
    Call<List<Poke_Info>> getPoke(@QueryMap Map<String,String> parameters);//drawback of this is you cannot use two userId's

    @GET("posts/{id}/comments")
    Call<List<Comment>>  getComments(@Path("id") int PostId);

    @GET
    Call<List<Comment>>  getComments(@Url String url);*/

}
