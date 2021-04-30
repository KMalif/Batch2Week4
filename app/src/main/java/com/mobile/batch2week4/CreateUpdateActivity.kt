package com.mobile.batch2week4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.mobile.batch2week4.databinding.ActivityCreateUpdateBinding
import com.mobile.batch2week4.model.Posts
import com.mobile.batch2week4.util.APIClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CreateUpdateActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCreateUpdateBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateUpdateBinding.inflate(layoutInflater)
        setContentView(binding.root)
        btnSaveAction()
        viewDatatoForm()
        btnUpdateAction()
    }

    //Function ketika button save diclick
    private fun btnSaveAction(){
        binding.BtnSave.setOnClickListener {
            addPost()
            finish()
        }
    }

    //Function untuk menambahkan data ke API
    private fun addPost(){
        val title = binding.ETTitle.text.toString()
        val body = binding.EtBody.text.toString()

        APIClient.APIEndpoint().addPosts(title, body).enqueue(object : Callback<Posts>{
            override fun onFailure(call: Call<Posts>, t: Throwable) {
                println(t.message)
            }

            override fun onResponse(call: Call<Posts>, response: Response<Posts>) {
                if (response.isSuccessful){
                    val body = response.body()
                    Toast.makeText(applicationContext, "Succes create data ", Toast.LENGTH_LONG).show()
                    println("Data Created $body")
                }
            }
        })
    }


    //function ketika button update diclick
    private fun btnUpdateAction(){
        binding.BtnUpdate.setOnClickListener {
            updatePost()
            finish()
        }
    }

    //function untuk merubah data yang sudah ada dalam API
    private fun updatePost(){
        val title = binding.ETTitle.text.toString()
        val body = binding.EtBody.text.toString()
        val id = intent.getIntExtra("id", 0)
        APIClient.APIEndpoint().updatePost(id, title, body).enqueue(object : Callback<Posts>{
            override fun onFailure(call: Call<Posts>, t: Throwable) {
                println(t.message)
            }

            override fun onResponse(call: Call<Posts>, response: Response<Posts>) {
                if (response.isSuccessful){
                    val body = response.body()
                    Toast.makeText(applicationContext, "Data Berhasil diupdate", Toast.LENGTH_LONG).show()
                    println("Data Baru $body")
                }
            }
        })
    }

    //function untuk get data dari intent ke form
    private fun viewDatatoForm(){
        binding.ETTitle.setText(intent.getStringExtra("title"))
        binding.EtBody.setText(intent.getStringExtra("Body"))
    }


}