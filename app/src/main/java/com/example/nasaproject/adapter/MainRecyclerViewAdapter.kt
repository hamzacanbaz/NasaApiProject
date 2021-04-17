package com.example.nasaproject.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.nasaproject.NasaModel
import com.example.nasaproject.R
import com.example.nasaproject.database.CommentsEntity
import com.example.nasaproject.database.PhotosEntity
import com.example.nasaproject.util.downloadfromurl
import com.example.nasaproject.util.placeHolderProgressBar
import com.example.nasaproject.view.main.MainFragmentDirections
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_comment.view.*
import kotlinx.android.synthetic.main.main_row_item.view.*
import kotlinx.android.synthetic.main.row_item.view.*
import androidx.navigation.Navigation.findNavController as findNavController1

class MainRecyclerViewAdapter(private val listener : OnItemLongClickListen) : RecyclerView.Adapter<MainRecyclerViewAdapter.ViewHolder>() {

    var nasaList = emptyList<PhotosEntity>()



    inner class ViewHolder(itemview: View): RecyclerView.ViewHolder(itemview),View.OnLongClickListener{

        val image : ImageView = itemview.mars_image
        init {
            //itemView.setOnClickListener(this)
            itemView.setOnLongClickListener(this)
        }

        override fun onLongClick(v: View?): Boolean {
            val position = adapterPosition
            if(position!=RecyclerView.NO_POSITION){
                listener.onItemLong(position)

            }
            return true
        }


    }

    interface OnItemLongClickListen{
        fun onItemLong(position: Int)
    }

    override fun onBindViewHolder(holder: MainRecyclerViewAdapter.ViewHolder, position: Int) {
        val currentItem = nasaList[position]
        println(currentItem.img_src)
        val url = currentItem.img_src?.substring(1, currentItem.img_src!!.length-1)
        //Picasso.get().load(url).into(holder.itemView.mars_image)
        holder.itemView.mars_image.downloadfromurl(url, placeHolderProgressBar(holder.itemView.context))

        holder.itemView.setOnClickListener {
            val action = MainFragmentDirections.actionMainFragmentToCommentFragment(currentItem.imageId,position.toLong())

            Navigation.findNavController(it).navigate(action)

        }
        /*holder.itemView.setOnLongClickListener {
            val action = MainFragmentDirections.actionMainFragmentToMakeComment()
            Navigation.findNavController(it).navigate(action)
        }*/
    //holder.itemView.date.text = currentItem.earth_date
        //holder.itemView.image.text = currentItem.img_src
    }



    override fun getItemCount(): Int {
        return nasaList.size
    }

    fun setData(nasa : List<PhotosEntity>){
       // nasaList.clear()
        //nasaList.addAll(nasa)
        //this.nasaList.clear()
        this.nasaList = nasa
        notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainRecyclerViewAdapter.ViewHolder {
        val itemview =LayoutInflater.from(parent.context).inflate(R.layout.main_row_item, parent, false)
        return ViewHolder(itemview)
    }

}
