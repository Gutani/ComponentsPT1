package com.gutani.componentspt1
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_message.view.*

data class Message(var title:String, var text:String)

class MessageAdapter(private val messages:List<Message>, private val callback: (Message) -> Unit) :RecyclerView.Adapter<MessageAdapter.VH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageAdapter.VH {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_message, parent, false)
        val vh = VH(v)
        vh.itemView.setOnClickListener {
            val message = messages[vh.adapterPosition]
            callback(message)
        }
        return vh
    }

    override fun onBindViewHolder(holder: MessageAdapter.VH, position: Int) {
        val (title, text) = messages[position]
        holder.txtTitle.text = title
        holder.txtText.text = text
    }

    override fun getItemCount(): Int = messages.size

    class VH(itemView:View):RecyclerView.ViewHolder(itemView){
        val txtTitle: TextView = itemView.txtTitleRecycler
        var txtText:TextView = itemView.txtTextRecycler
    }
}

