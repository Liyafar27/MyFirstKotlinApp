package ru.example.myfirstkotlinapp

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.listener.OnChartValueSelectedListener
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapters.Rfc3339DateJsonAdapter
import ir.farshid_roohi.linegraph.ChartEntity
import kotlinx.android.synthetic.main.activity_main.pageNumber
import kotlinx.android.synthetic.main.activity_repo_info.*
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import ru.example.myfirstkotlinapp.api.StargazersInterface3
import java.util.*
import kotlin.collections.ArrayList


class RepoInfoActivity : AppCompatActivity() {
    var yearStr= 2020
    var dateArray: ArrayList <Date> = ArrayList()
    var dateArray2: ArrayList <Date> = ArrayList()
    val convertdateArray: ArrayList<Int> = ArrayList()
    val convertdateArray2: ArrayList<Int> = ArrayList()
    var floatArray: List <Float> = ArrayList()
    var floatArray2: List <Float> = ArrayList()
    var listViewStargazers: ListView? = null
    val intList:  String = ""
    var page:Int = 1
    var per_page : Int = 1000
    var ownerStr: String? = null
    var repoNameStr3: String? = null
    var userArray: ArrayList <Any> = ArrayList()


    var chart: BarChart? = null
    var BARENTRY: ArrayList<BarEntry>? = null
    var BarEntryLabels: ArrayList<String>? = null
    var Bardataset: BarDataSet? = null
    var BARDATA: BarData? = null


    fun prevClick(view : View) {

        yearStr = yearStr.minus(1)
         pageNumber.setText("${yearStr} год ")
        convertdateArray.clear()
        convertdateArray2.clear()

        getCurrentData()

    }
    fun nextClick(view : View) {

        yearStr = yearStr.plus(1)
        pageNumber.setText("${yearStr} год ")
        convertdateArray.clear()
        convertdateArray2.clear()
        getCurrentData()


    }

    override fun onCreate(savedInstanceState: Bundle? ) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repo_info)

        yearStr=2020

        val pageNumber: TextView = findViewById<TextView>(R.id.pageNumber) as TextView



       pageNumber.setText("${yearStr} год ")

        listViewStargazers = findViewById(R.id.listViewStargazers)

        val bundle =intent.extras
        val textView =findViewById<TextView>(R.id.textView2) as TextView

        textView.setText(bundle!!.getCharSequence("nameRepo"))
        val repoNameStr: String = (textView.getText().toString())
        val repoNameStr1 = repoNameStr.split("Owner:")
        val repoNameStr2 = repoNameStr1[0].split("Name: ")
        val ownerName = repoNameStr.split("Created")
        val ownerName2 = ownerName[0].split("Owner: ")

         ownerStr = ownerName2[1].trim()
         repoNameStr3 = repoNameStr2[1].trim()

        getCurrentData ()

    }



    private fun getCurrentData () {

        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client1 = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()

        val moshi = Moshi.Builder()
            .add(Date::class.java, Rfc3339DateJsonAdapter().nullSafe())
            .build()

        val builder = Retrofit.Builder()
            .client(client1)
            .baseUrl("https://api.github.com/")
            .addConverterFactory(MoshiConverterFactory.create(moshi))

        val retrofit = builder.build()

        val client = retrofit.create<StargazersInterface3>(StargazersInterface3::class.java)


        val call = client.stargazersForRepo(ownerStr!!, repoNameStr3!!, page, per_page).also {

            it.enqueue(object : Callback<List<StargazersList2>> {

                override fun onResponse(
                    call: Call<List<StargazersList2>>,
                    response: Response<List<StargazersList2>>
                ) {

                    val repo2 = response.body()
                    repo2?.forEach() { it.starred_at?.let { it1 -> dateArray.add(it1.removeTime()) } }

                    repo2?.forEach() { it.starred_at?.let { it1 -> dateArray2.add(it1) } }
                    repo2?.forEach() { it.user?.let { it1 -> userArray.add(it1) } }

//                 val map: Map<Date, Any> =
//                     dateArray2.zip(userArray) // Gives you [("butter", 5), ("milk", 10), ("apples", 42)]
//                         .toMap()
//                 Log.i("map", map.toString())
//                 Log.i("map", map.size.toString())

                    val mapNumber = mutableMapOf<Any, Date>()
                        .apply {
                            for (i in userArray.indices) this[userArray[i]] =
                                dateArray2[i].removeTime()
                        } // create new map

                    val c1 = Calendar.getInstance()
                    c1[Calendar.DAY_OF_MONTH] = 31
                    c1[Calendar.MONTH] = 11
                    c1[Calendar.YEAR] = yearStr - 1
                    c1[Calendar.HOUR_OF_DAY] = 23 // set hour to midnight
                    c1[Calendar.MINUTE] = 59 // set minute in hour
                    c1[Calendar.SECOND] = 59 // set second in minute
                    c1[Calendar.MILLISECOND] = 0 // set millisecond in second
                    val current: Date = c1.time
                    Log.i("claendar.time c1", current.toString())

                    val c2 = Calendar.getInstance()
                    c2[Calendar.DAY_OF_MONTH] = 1
                    c2[Calendar.MONTH] = 0
                    c2[Calendar.YEAR] = yearStr + 1
                    c2[Calendar.HOUR_OF_DAY] = 0 // set hour to midnight
                    c2[Calendar.MINUTE] = 0 // set minute in hour
                    c2[Calendar.SECOND] = 0 // set second in minute
                    c2[Calendar.MILLISECOND] = 0 // set millisecond in second
                    val current2: Date = c2.time
                    Log.i("claendar.time c2", current2.toString())


//                 val arraStars = mapNumber.filter { it.toString().contains(yearStr.toString()) }
                    val arraStars2 = mapNumber.filter {
                        it.value.after(c1.time)
                    }.filter { it.value.before(c2.time) }

                    val arrayconv = dateArray.groupingBy { it }.eachCount().filter { it.value > 0 }

                    val arraconv33 = arrayconv.filter {
                        it.key.after(c1.time)
                    }.filter { it.key.before(c2.time) }

                    val arraconv2 = arrayconv.filter { it.toString().contains(yearStr.toString()) }

                    textView3.text = arraconv2.toString()

                    val month = listOf(
                        " Jan",
                        "Feb",
                        "Mar",
                        "Apr",
                        "May",
                        "Jun",
                        "Jul",
                        "Aug",
                        "Sep",
                        "Oct",
                        "Nov",
                        "Dec"                    )

                    for (i in month.indices) {
                        if (arraconv2.keys.toString().contains(month[i])) {
                            val position = arraconv2.filter { it.toString().contains(month[i]) }
                            convertdateArray.add(position.values.elementAt(0))
                        } else {
                            convertdateArray.add(0)
                        }
                    }
                    for (i in month.indices) {
                        if (arraconv33.toString().contains(month[i])) {
                            val position = arraconv33.filter { it.toString().contains(month[i]) }
                            convertdateArray2.add(position.values.elementAt(0))
                        } else {
                            convertdateArray2.add(0)
                        }
                    }

                    val values2: List<Float> = convertdateArray.map { it.toFloat() }
                    val values3: List<Float> = convertdateArray2.map { it.toFloat() }

                    floatArray2 = values2
                    floatArray = values3

//                 listViewStargazers!!.adapter =
//                     StargazersAdapter(this@RepoInfoActivity, repo2!!, intList)

                    val legendArr = listOf(
                        "January",
                        "February",
                        "March",
                        "April",
                        "May",
                        "June",
                        "July",
                        "August",
                        "September ",
                        "October",
                        "November",
                        "December"
                    )

                    var size = 12
                    if (yearStr == 2020) size = 10

                    val firstChartEntity =
                        ChartEntity(Color.WHITE, floatArray.toFloatArray().copyOf(size))

                    val list = listOf(firstChartEntity)
                    setupBarChartData()

                    lineChart.setLegend(legendArr)
                    lineChart.setList(list)
                    lineChart.setOnClickListener {

                        val intent = Intent(this@RepoInfoActivity, ChartActivity::class.java)
                        val bundle = Bundle()

                        val arrListStar2: ArrayList<String> = ArrayList()

                        arraStars2.keys.forEach { it.let { it1 -> arrListStar2.add(it1.toString()) } }


                        bundle.putStringArrayList("listStar", arrListStar2)
                        bundle.putString("current year", yearStr.toString())
                        intent.putExtras(bundle)
                        this@RepoInfoActivity.startActivity(intent)
                    }

                }

                override fun onFailure(call: Call<List<StargazersList2>>, t: Throwable) {
                    Toast.makeText(this@RepoInfoActivity, "Error :(", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }
    private fun setupBarChartData() {
        // create BarEntry for Bar Group
        val bargroup = ArrayList<BarEntry>()
        val legendArr = listOf(
            "first",
            "January",
            "February",
            "March",
            "April",
            "May",
            "June",
            "July",
            "August",
            "September ",
            "October",
            "November",
            "December"
        )
        for (i in floatArray2.indices) {
            bargroup.add(
                BarEntry(
                    (i + 1).toFloat(),
                    floatArray2.elementAt(i),
                    legendArr.elementAt(i)
                )
            )

        }


        val barDataSet = BarDataSet(bargroup, "")

        barDataSet.color = ContextCompat.getColor(this, R.color.amber)

        val data = BarData(barDataSet)

        barChart.setData(data)
        barChart.xAxis.position = XAxis.XAxisPosition.BOTTOM
        barChart.xAxis.labelCount = 11
        barChart.xAxis.valueFormatter = IndexAxisValueFormatter(legendArr)
        barChart.xAxis.enableGridDashedLine(5f, 5f, 0f)
        barChart.axisRight.enableGridDashedLine(5f, 5f, 0f)
        barChart.axisLeft.enableGridDashedLine(5f, 5f, 0f)
        barChart.description.isEnabled = false
        barChart.animateY(700)
        barChart.legend.isEnabled = false
        barChart.setPinchZoom(true)
        barChart.data.setDrawValues(false)

        barChart!!.setOnChartValueSelectedListener(object : OnChartValueSelectedListener {

            override fun onValueSelected(e: Entry, h: Highlight?) {
//
                Log.i("chose bar", e.toString())
            }

            override fun onNothingSelected() {}
        })


    }
        }

















