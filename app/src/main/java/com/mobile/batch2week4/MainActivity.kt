package com.mobile.batch2week4

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.mobile.batch2week4.databinding.ActivityMainBinding
import com.mobile.batch2week4.adapter.MainAdapter
import com.mobile.batch2week4.model.Posts
import com.mobile.batch2week4.util.APIClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var mainAdapter : MainAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        moveToCreateActivity()
        getAllPosts()
    }

    //function untuk pindah ke CreateUpdateActivity
    fun moveToCreateActivity(){
        binding.fab.setOnClickListener {
            startActivity(Intent(this, CreateUpdateActivity::class.java))
        }
    }

    //function untuk menerima response semua posts dari API jsonplaceholder
    fun getAllPosts(){
        APIClient.APIEndpoint().getAllPosts().enqueue(object : Callback<List<Posts>>{
            override fun onFailure(call: Call<List<Posts>>, t: Throwable) {
                println(t.message)
            }

            override fun onResponse(call: Call<List<Posts>>, response: Response<List<Posts>>) {
                if (response.isSuccessful){
                    val body = response.body()
                    if (body != null){
                        showToRecycler(body)
                        Toast.makeText(applicationContext, "Data berhasil didapatkan", Toast.LENGTH_LONG).show()
                    }
                }
            }
        })
    }

    //function untuk menampilkan response yang sukses didapatkan ke dalam recyclerview
    fun showToRecycler(posts: List<Posts>){
        mainAdapter = MainAdapter(posts, object : MainAdapter.onAdapterCLick{
            override fun onCLick(posts: Posts) {
                startActivity(Intent(this@MainActivity, DetailActivity::class.java).apply {
                    putExtra("id", posts.id)
                })
            }

            override fun onUpdate(posts: Posts) {
                startActivity(Intent(this@MainActivity, CreateUpdateActivity::class.java).apply {
                    putExtra("id", posts.id)
                    putExtra("Body", posts.body)
                    putExtra("title", posts.title)
                })
            }
        })
        binding.RvPosts.apply {
            adapter = mainAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
    }
}