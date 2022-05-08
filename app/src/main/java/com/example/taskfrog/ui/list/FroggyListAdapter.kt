package com.example.taskfrog.ui.list

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.taskfrog.R
import com.example.taskfrog.db.FroggyList


class FroggyListAdapter internal constructor(context: ListFragment) :
    RecyclerView.Adapter<FroggyListAdapter.FroggyListViewHolder>(){
        inner class FroggyListViewHolder(itemView: View) :
            RecyclerView.ViewHolder(itemView) {
                val froggyListItemView: TextView

                init{
                    froggyListItemView = itemView.findViewById(R.id.recyclerView)
                }
            }

    private val mInflater: LayoutInflater
    private var mfroggyLists: List<FroggyList>? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FroggyListViewHolder {
        val itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false)
        return FroggyListViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: FroggyListViewHolder, position: Int) {
        if(mfroggyLists != null) {
            val current = mfroggyLists!![position]
            holder.froggyListItemView.text = current.name
        } else {
            holder.froggyListItemView.text = "No Name"
        }
    }

    fun setLists(lists: List<FroggyList>?) {
        mfroggyLists = lists
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return if (mfroggyLists != null) mfroggyLists!!.size else 0
    }

    init {
        mInflater = LayoutInflater.from(this)
    }
}
