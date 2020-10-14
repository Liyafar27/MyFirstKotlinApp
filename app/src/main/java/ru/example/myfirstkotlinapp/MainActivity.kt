package ru.example.myfirstkotlinapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapters.Rfc3339DateJsonAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_list_repo.view.*
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import ru.example.myfirstkotlinapp.api.GitHubClient
import ru.example.myfirstkotlinapp.api.GitHubRepo
import ru.example.myfirstkotlinapp.api.RepoAdapter
import java.util.*


class MainActivity : AppCompatActivity() {

    private var listView: ListView? = null

    private var repoName :String =""
    var page:Int = 1
    var per_page : Int = 1000

    fun prevClick(view : View) {
        page -= 1
        pageNumber.setText("Page " + page.toString())
    }

    fun nextClick(view : View) {
        page += 1
        pageNumber.setText("Page"   +page.toString())
    }


    fun buttonFunc(view : View) {
        val editText = findViewById<EditText>(R.id.editText)
        repoName = editText.text.toString()

        if (view.id == R.id.buttonSearch) {
            val logging = HttpLoggingInterceptor()
            logging.setLevel(HttpLoggingInterceptor.Level.BODY)
            val client1 = OkHttpClient.Builder()
                .addInterceptor(logging)
                .build()

            val moshi = Moshi.Builder()
                .add(Date::class.java,  Rfc3339DateJsonAdapter().nullSafe())
                .build()
            val builder = Retrofit.Builder()
                .client(client1)
                .baseUrl("https://api.github.com/")
                .addConverterFactory(MoshiConverterFactory.create(moshi))


            val retrofit = builder.build()

            val client = retrofit.create<GitHubClient>(GitHubClient::class.java)
            val call = client.reposForUser(repoName,page,per_page)

            call.enqueue(object : Callback<List<GitHubRepo>> {

                override fun onResponse(call: Call<List<GitHubRepo>>, response: Response<List<GitHubRepo>>) {

                    val repos = response.body()
                    repos?.let{for (comment in it)
                        Log.i("!!!!!!!!!!", comment.toString())}



                    listView!!.adapter = RepoAdapter(this@MainActivity, repos!!)

                   listView!!.setOnItemClickListener { parent, view, position, messageIntent ->

                        Toast.makeText(this@MainActivity, "Clicked item : ${view.textName.text}",Toast.LENGTH_SHORT).show()
                        val intent =  Intent(this@MainActivity, RepoInfoActivity::class.java)
                       val bundle = Bundle()
                       bundle.putString("nameRepo", view.textName.getText().toString())
                       intent.putExtras(bundle)
//                        intent.putExtra("position", view.textName.text)
                        startActivity(intent)
                    }
                }
                override fun onFailure(call: Call<List<GitHubRepo>>, t: Throwable) {
                    Toast.makeText(this@MainActivity, "Error  ggfhdjjj:(", Toast.LENGTH_SHORT).show()                }
            })

        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listView = findViewById(R.id.pagination_list)

        val pageNumber: TextView = findViewById<TextView>(R.id.pageNumber) as TextView
        page = 1
        pageNumber.setText("Page " + page.toString())
    }

}


