package ru.example.myfirstkotlinapp

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_chart.*
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap
import kotlin.collections.HashSet

class ChartActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chart)

        val bundle =intent.extras
        val listView =findViewById<ListView>(R.id.listar) as ListView
        val textView = findViewById<TextView>(R.id.textViewYear)



        val bundleAr = bundle!!.getStringArrayList("listStar")
        val currentYear=bundle!!.getString("current year")
        textViewYear.setText("⭐Stargazers List⭐ for "+currentYear+ " year")
        val nameAr : ArrayList<String> = ArrayList()
        val urlAr : ArrayList<String> = ArrayList()

        for(i in bundleAr!!.indices) {
            val name1 = bundleAr[i].split("login=")
            val name2 = name1[1].split(", id")
            nameAr.add(name2[0])

            val url1= bundleAr[i].split(", gravatar_id")
            val url21 =url1[0].split("avatar_url=")
            val url3 = url21[1].trim()
            urlAr.add(url3)
             }

        val chartStarsAdapter=ChartStarsAdapter(this@ChartActivity, urlAr,nameAr)
        listView!!.adapter=chartStarsAdapter
    }
    }







