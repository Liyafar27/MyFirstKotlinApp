//package ru.example.myfirstkotlinapp.api
//
//import android.content.Context
//import android.content.Intent
//import android.os.Bundle
//import android.util.Log
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.ArrayAdapter
//import android.widget.ImageView
//import android.widget.TextView
//import ru.example.myfirstkotlinapp.ChartActivity
//import ru.example.myfirstkotlinapp.R
//import ru.example.myfirstkotlinapp.StargazersList2
//import java.time.ZoneId
//import java.time.format.DateTimeFormatter
//import java.util.*
//
//
//class StargazersAdapter (context: Context, private val values: List<StargazersList2>, var messageDate : String ) : ArrayAdapter<StargazersList2>(context, R.layout.item_list_repo, values) {
//    val dateList:List<String> = listOf(messageDate)
//    private class ViewHolder(row: View?) {
//
//
//        var textName: TextView? = null
//
//        var imageView2: ImageView? = null
//        var dataText :TextView? = null
//
//        init {
//
////        val imageView : ImageView = row.findViewById<View>(R.id.imageView) as ImageView
//            this.textName = row?.findViewById<TextView>(R.id.textName)
//            this.imageView2 = row?.findViewById<ImageView>(R.id.imageView2)
//            this.dataText = row?.findViewById(R.id.dateText)
//
//        }
//
//    }
//
//    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
//        val view: View?
//        val listDate: Any?
//
//        val viewHolder: ViewHolder
//        if (convertView == null) {
//            val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
//            view = inflater.inflate(R.layout.item_list_repo, null)
////            val inflater = activity?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
////            view = inflater.inflate(R.layout.user_list_row, null)
//            viewHolder = ViewHolder(view)
//            view.setTag(viewHolder)
//            view?.tag = viewHolder
//        } else {
//            view = convertView
//            viewHolder = view.tag as ViewHolder
//        }
//        var item = values[position]
//        item.starred_at.toString().filter{ it.toString().contains("2020") }
////        val starName = item.user.toString().split(", gravatar_id")
////        val starName1 =starName[0].split("avatar_url=")
////        val starName2 = starName1[1].trim()
////        val uri = Uri.parse(starName2)
//
////        val login =  item.user.toString().split(", id")
////        val login2 = login[0].split("login=")
//
//
//        val createdAt2 = item.starred_at
////        val arraconv2 = arrayconv.filter { it.toString().contains(yearStr.toString()) }
//        val user =item.user
//
////        val createdAt= (item.starred_at.toString()).split("T")
////        val createdAt2 = createdAt[0].split("-").reversed().joinToString(separator = ".")
//        Log.i("createdat", createdAt2.toString())
//
//
//        val position1 = position +1
//
//      val message = position1.toString() +
////              login2[1]+
//              "\n"+ "Starred at: "+
//              "\n"+ createdAt2
////        val message2 =   starName2
//        viewHolder.textName?.text= message
//
//         messageDate += "\""+ createdAt2 + "\"" +" "
//
//        viewHolder.dataText?.text = messageDate
////        Glide.with(context)
////           .load(uri)
////           .into(viewHolder.imageView2)
//
//
////        view!!.setOnClickListener({
////            val intent = Intent(context, ChartActivity::class.java)
////            val bundle = Bundle()
//////            bundle.putStringArrayList()
////            bundle.putString("date",messageDate)
//////            bundle.putString("date",dateList.toString())
////
////            intent.putExtras(bundle)
////            context.startActivity(intent)
////        })
//
//
//
//        return view as View
//    }
//
//    override fun getItem(i: Int): StargazersList2 {
//        return values[i]
//    }
//
//    override fun getItemId(i: Int): Long {
//        return i.toLong()
//    }
//
//    override fun getCount(): Int {
//
//        return values.size
//
//}
//}
//
//
//
//
//
//
//
//
////    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
////        var row = convertView
////
////        if (row == null) {
////            val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
////            row = inflater.inflate(R.layout.item_list_repo, parent, false)
////        }
////
////
////
////        val textName = row!!.findViewById<View>(R.id.textName) as TextView
////        val imageView : ImageView = row.findViewById<View>(R.id.imageView) as ImageView
////
////        val item = values[position]
////
////        val starName = item.user.toString().split(", gravatar_id")
////        val starName1 =starName[0].split("avatar_url=")
////
////        val message = "login: "+  starName1[1]+ "\n"+ "Starred at: "+ item.starred_at
////
////
////        textName.text = message
////      imageView.setImageURI(starName1[1].toUri())
////        Glide.with(context)
////            .load(starName1[1])
////            .into(imageView)
//
//
//
//
////        textName.setOnClickListener {
////            Toast.makeText(context, "Clicked image of " + item.name,
////                Toast.LENGTH_SHORT).show()
////
////            val intent = Intent(this@RepoAdapter,RepoInfoActivity::class.java)
////
////        }
//
//
//
////        return row
////
////
////    }
////
////
////
////}
//
