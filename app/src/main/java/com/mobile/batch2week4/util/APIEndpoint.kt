package com.mobile.batch2week4.util

import com.mobile.batch2week4.model.Posts
import retrofit2.Call
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Path

interface APIEndpoint {

    @GET("posts")
    fun getAllPosts(): Call<List<Posts>>

    @GET("posts/{id}")
    fun getPostsbyId(@Path("id")id: Int):Call<Posts>

    @DELETE("posts/{id}")
    fun delete(@Path("id")id : Int):Call<Void>



}

