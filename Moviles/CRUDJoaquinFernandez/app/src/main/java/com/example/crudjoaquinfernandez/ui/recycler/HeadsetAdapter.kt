package com.example.crudjoaquinfernandez.ui.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.crudjoaquinfernandez.R
import com.example.crudjoaquinfernandez.domain.model.Headset

class HeadsetAdapter(
    private var headsetList: List<Headset>,
    private val actions: Actions,
) : RecyclerView.Adapter<HeadsetViewHolder>() {

    interface Actions {
        fun onClickDelete(id: Int)
        fun onClickDetail(id: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeadsetViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_headset, parent, false)
        return HeadsetViewHolder(view)
    }

    override fun onBindViewHolder(holder: HeadsetViewHolder, position: Int) {
        holder.render(headsetList[position], actions)

    }

    override fun getItemCount(): Int = headsetList.size

    fun cambiarLista(nuevaLista: List<Headset>) {
        headsetList = nuevaLista
        notifyDataSetChanged()
    }

}

class HeadsetViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    fun render(
        headset: Headset,
        actions: HeadsetAdapter.Actions,
    ) {
        view.findViewById<TextView>(R.id.tvNombre).text = headset.name
        view.findViewById<TextView>(R.id.tvID).text = headset.id.toString()
        view.findViewById<ImageButton>(R.id.button2).setOnClickListener {
            actions.onClickDelete(view.findViewById<TextView>(R.id.tvID).text.toString().toInt())
        }
        view.findViewById<ImageButton>(R.id.detail).setOnClickListener {
            actions.onClickDetail(view.findViewById<TextView>(R.id.tvID).text.toString().toInt())
        }
    }
}