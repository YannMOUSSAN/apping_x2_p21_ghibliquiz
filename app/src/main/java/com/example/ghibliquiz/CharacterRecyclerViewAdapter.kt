package com.example.ghibliquiz

import android.content.Context
import android.service.autofill.OnClickAction
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.layout_characters.view.*

class CharacterRecyclerViewAdapter (
    private val context : Context,
    private val data : MutableList<Characters>)
    : RecyclerView.Adapter<CharacterRecyclerViewAdapter.ViewHolder>()
{
    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val genre : ImageView = itemView.genre
        val name : TextView = itemView.name
        val age : TextView = itemView.age

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view: View = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_characters, parent, false)
        return ViewHolder(view)
    }
    override fun getItemCount(): Int {
        return data.size
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val currentItem = data[position]
        if (currentItem.gender == "Male") {
            Glide.with(context).load("").placeholder(R.drawable.male)
        } else {
            Glide.with(context).load("").placeholder(R.drawable.female)
        }
        holder.name.text = currentItem.name
        holder.age.text = currentItem.age
    }
}