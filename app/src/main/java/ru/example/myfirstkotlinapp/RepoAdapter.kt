package ru.example.myfirstkotlinapp.api
import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import ru.example.myfirstkotlinapp.R


class RepoAdapter(context: Context, private val values: List<GitHubRepo>) : ArrayAdapter<GitHubRepo>(context, R.layout.item_list_repo, values) {

    private class ViewHolder(row: View?) {
        var textName: TextView? = null
        var dateText: TextView? = null
        var imageView2: ImageView? = null

        init {
            this.textName = row?.findViewById(R.id.textName)
            this.imageView2 = row?.findViewById(R.id.imageView2)
            this.dateText = row?.findViewById(R.id.dateText)
        }
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val view: View?
        val viewHolder: ViewHolder
        if (convertView == null) {
            val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            view = inflater.inflate(R.layout.item_list_repo, null)

            viewHolder = ViewHolder(view)
            view.setTag(viewHolder)
            view?.tag = viewHolder

        } else {
            view = convertView
            viewHolder = view.tag as ViewHolder
        }
        val item = values[position]
        val photoUrl: List<String> =item.owner.toString().split("avatar_url=")
        val photoUrl2 =photoUrl[1].split(", gravatar_id")
        val photoUrl3 = photoUrl2[0].trim()
        val uri =  Uri.parse(photoUrl3)
        val position1 = position +1
        val owner = (item.full_name.toString()).split("/")

        Glide.with(context)
            .load(uri)
            .into(viewHolder.imageView2)

      val message = position1.toString() + ".  "+ "Repo Name: " + "\n"+  item.name  + "\n"+ "Owner: "+owner[0]+ "\n"+"Created at: " +   "\n"+ "⭐ Stargazers count: "+ item.stargazers_count

        viewHolder.textName?.text = message
        viewHolder.dateText?.text = item.created_at?.toString()

        return view as View
    }

    override fun getItem(i: Int): GitHubRepo {

        return values[i]
    }

    override fun getItemId(i: Int): Long {
        return i.toLong()
    }

    override fun getCount(): Int {
        return values.size
    }




}



