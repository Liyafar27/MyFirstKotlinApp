package ru.example.myfirstkotlinapp

import android.content.Context
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide

class ChartStarsAdapter (context: Context,urlAr: ArrayList<String>, nameAr: ArrayList<String> ) : BaseAdapter() {
    var context:Context= context
    var urlar = urlAr
    var namearr = nameAr
    private class ViewHolder(row: View?) {

        var textName: TextView? = null
        var imageView2: ImageView? = null

        init {
            this.textName = row?.findViewById<TextView>(R.id.textName)
            this.imageView2 = row?.findViewById<ImageView>(R.id.imageView2)
               }
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view: View?
        val viewHolder: ViewHolder
        if (convertView == null) {
            val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            view = inflater.inflate(R.layout.item_list_repo, null)
//            val inflater = activity?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
//            view = inflater.inflate(R.layout.user_list_row, null)
            viewHolder = ViewHolder(view)
            view.setTag(viewHolder)
            view?.tag = viewHolder
        } else {
            view = convertView
            viewHolder = view.tag as ViewHolder
        }
        viewHolder.textName!!.setText(namearr!![position])
        val uri = Uri.parse(urlar!![position])
        Glide.with(context)
           .load(uri)
           .into(viewHolder.imageView2)

        return view as View
    }

    override fun getItem(i: Int): Any{
        return urlar[i]
    }

    override fun getItemId(i: Int): Long {
        return i.toLong()
    }

    override fun getCount(): Int {

        return urlar!!.size

    }
}



