package com.mobile.batch2week4.util

import com.mobile.batch2week4.model.Posts
import retrofit2.Call
import retrofit2.http.*

interface APIEndpoint {

    @GET("posts")
    fun getAllPosts(): Call<List<Posts>>

    @GET("posts/{id}")
    fun getPostsbyId(@Path("id")id: Int):Call<Posts>

    @DELETE("posts/{id}")
    fun delete(@Path("id")id : Int):Call<Void>

    @FormUrlEncoded
    @POST("posts")
    fun addPosts(
            @Field("title") title : String,
            @Field("body") body : String
    ): Call<Posts>

    @FormUrlEncoded
    @POST("posts/{id}")
    fun updatePost(
            @Path("id") id : Int,
            @Field("title") title : String,
            @Field("body") body : String
    ): Call<Posts>

}

