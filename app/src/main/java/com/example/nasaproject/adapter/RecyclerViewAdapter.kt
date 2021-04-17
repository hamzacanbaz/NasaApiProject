package com.example.nasaproject.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.nasaproject.R
import com.example.nasaproject.database.CommentsEntity
import kotlinx.android.synthetic.main.row_item.view.*

class RecyclerViewAdapter : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    private var commentList = emptyList<CommentsEntity>()

    class ViewHolder(itemview:View):RecyclerView.ViewHolder(itemview){

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerViewAdapter.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.row_item,parent,false))
    }


    override fun onBindViewHolder(holder: RecyclerViewAdapter.ViewHolder, position: Int) {
        val currentItem = commentList[position]
        holder.itemView.name.text = currentItem.commentUser
        holder.itemView.comment.text = currentItem.commentMessage
    }


    override fun getItemCount(): Int {
        return commentList.size
    }

    fun setData(comment : List<CommentsEntity>){
        this.commentList = comment
        notifyDataSetChanged()
    }

}