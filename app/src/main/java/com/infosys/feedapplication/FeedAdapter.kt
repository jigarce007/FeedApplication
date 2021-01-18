package com.infosys.feedapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.infosys.feedapplication.Model.Feed
import kotlinx.android.synthetic.main.list_item.view.*

class FeedAdapter(val ctx:Context, var feedList: ArrayList<Feed>):RecyclerView.Adapter<FeedAdapter.FeedHolder>(){
lateinit var cv : FeedHolder



    class FeedHolder(view:View):RecyclerView.ViewHolder(view){
        var tv_title : TextView =view.tv_title
        var tv_description : TextView = view.tv_description
        var iv_thumd : ImageView = view.iv_thumb

}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedHolder {

        val myView = LayoutInflater.from(ctx).inflate(R.layout.list_item,null,false)
        cv = FeedHolder(myView)
        return cv

    }

    override fun getItemCount(): Int {

        return feedList.size
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun onBindViewHolder(holder: FeedHolder, position: Int) {

       cv.tv_title.text = feedList[position].title
        cv.tv_description.text = feedList[position].description
        val thumb = feedList[position].thumb
        if (thumb !== null) {
            Glide.with(ctx)
                .load(thumb)
                .error(R.drawable.ic_launcher_background)
                .into(cv.iv_thumd)
        } else {
            cv.iv_thumd.setImageResource(R.drawable.ic_launcher_foreground)
        }
    }
}