package com.mobile.batch2week4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.mobile.batch2week4.databinding.ActivityDetailBinding
import com.mobile.batch2week4.model.Posts
import com.mobile.batch2week4.util.APIClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getPostsbyId()
        deleteIconAction()
    }

    //Function untuk menerima response posts berdasarkan id
    private fun getPostsbyId(){
        APIClient.APIEndpoint().getPostsbyId(intent.getIntExtra("id", 0)).enqueue(object : Callback<Posts>{
            override fun onFailure(call: Call<Posts>, t: Throwable) {
                println(t.message)
            }

            override fun onResponse(call: Call<Posts>, response: Response<Posts>) {
                if (response.isSuccessful){
                    val body = response.body()
                    if (body != null){
                        showPostsToView(body)
                        Toast.makeText(applicationContext, "Data berhasil didapatkan", Toast.LENGTH_LONG).show()
                    }
                }
            }
        })
    }

    //function untuk menampilkan response data by Id ke dalam tampilan / view
    private fun showPostsToView(posts: Posts){
        binding.TvDetailTitle.text = posts.title
        binding.TvDetailBody.text = posts.body
    }

    //Function ketika icon delete di click
    private fun deleteIconAction(){
        binding.IconDelete.setOnClickListener {
            deletePosts()
            finish()
        }
    }

    //function untuk menghapus data/record dari API
    private fun deletePosts(){
        APIClient.APIEndpoint().delete(intent.getIntExtra("id", 0)).enqueue(object : Callback<Void>{
            override fun onFailure(call: Call<Void>, t: Throwable) {
                println(t.message)
            }

            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful){
                    Toast.makeText(applicationContext, response.code().toString(), Toast.LENGTH_LONG).show()
                }
            }
        })
    }
}