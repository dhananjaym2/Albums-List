package list.albums.view.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import list.albums.R
import list.albums.model.Album

class VerticalRecyclerViewAdapter(activityContext: Activity, val listData: List<Album>) :
    RecyclerView.Adapter<VerticalRecyclerViewAdapter.AlbumViewHolder>() {

    private val logTag: String = VerticalRecyclerViewAdapter::class.java.simpleName

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {
        val itemView: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.album_item, parent, false)
        return AlbumViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        holder.albumTitleInListItem.text = listData[position].title
    }

    class AlbumViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var albumTitleInListItem: TextView = itemView.findViewById(R.id.albumTitleInListItem)
    }

}