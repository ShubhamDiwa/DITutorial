package com.vasyerp.ditutorial.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.vasyerp.ditutorial.Api.Products
import com.vasyerp.ditutorial.R

class ProducAdapter(var context: Context,private val pList: ArrayList<Products>):RecyclerView.Adapter<ProducAdapter.PViewholder>() {
    class PViewholder (itemview: View):RecyclerView.ViewHolder(itemview){
        var image=itemview.findViewById<ImageView>(R.id.imgView)
        var title=itemview.findViewById<TextView>(R.id.tvTitle)
        var description =itemview.findViewById<TextView>(R.id.description)
        var price= itemview.findViewById<TextView>(R.id.tvPrice)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PViewholder {
       val view =LayoutInflater.from(parent.context).inflate(R.layout.item_employees,parent,false)
        return PViewholder(view)
    }

    override fun getItemCount(): Int {
      return pList.size
    }

    override fun onBindViewHolder(holder: PViewholder, position: Int) {
        val item=pList[position]
        holder.title.text=item.title
        holder.description.text=item.description
        holder.price.text= item.price.toString()
        holder.image.load(item.thumbnail)


    }
}