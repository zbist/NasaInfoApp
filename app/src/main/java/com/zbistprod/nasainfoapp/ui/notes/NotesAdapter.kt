package com.zbistprod.nasainfoapp.ui.notes

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.zbistprod.nasainfoapp.R

class NotesAdapter :
    RecyclerView.Adapter<NotesAdapter.NoteHolder>(), ItemTouchHelperAdapter {

    var listOfNotes = mutableListOf<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteHolder {
        return NoteHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.note_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: NoteHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount() = listOfNotes.size



    override fun onItemMove(fromPosition: Int, toPosition: Int) {
        listOfNotes.removeAt(fromPosition).apply {
            listOfNotes.add(if (toPosition > fromPosition) toPosition - 1 else toPosition, this)
        }
        notifyItemMoved(fromPosition, toPosition)
    }

    override fun onItemDismiss(position: Int) {
        listOfNotes.removeAt(position)
        notifyItemRemoved(position)
    }

    inner class NoteHolder(itemView: View) : RecyclerView.ViewHolder(itemView), ItemTouchHelperViewHolder {

        private val deleteImg = itemView.findViewById<ImageView>(R.id.delete_img)
        private val text = itemView.findViewById<TextView>(R.id.text_of_note)
        private val card = itemView.findViewById<CardView>(R.id.card_item)

        fun bind(position: Int) {

            text.text = listOfNotes[position]

            deleteImg.setOnClickListener {
                listOfNotes.removeAt(adapterPosition)
                notifyItemRemoved(adapterPosition)
            }

        }

        override fun onItemSelected() {
            card.setBackgroundColor(Color.LTGRAY)
        }

        override fun onItemClear() {
            card.setBackgroundColor(Color.WHITE)
        }
    }

}

interface ItemTouchHelperAdapter {
    fun onItemMove(fromPosition: Int, toPosition: Int)

    fun onItemDismiss(position: Int)
}

interface ItemTouchHelperViewHolder {

    fun onItemSelected()

    fun onItemClear()
}