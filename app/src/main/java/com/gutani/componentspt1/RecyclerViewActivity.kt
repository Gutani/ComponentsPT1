package com.gutani.componentspt1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_recycler_view.*

class RecyclerViewActivity : AppCompatActivity() {
    private var messages = mutableListOf<Message>()
    private var adapter = MessageAdapter(messages, this::onMessageItemClick)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view)
        lastCustomNonConfigurationInstance.let {
            if(it is MutableList<*>){
                messages.addAll(it.filterIsInstance(Message::class.java))
            }
        }

        initRecyclerView()
        fabAdd.setOnClickListener {
            addMessage()
        }
    }

    private fun initRecyclerView() {
        rvMessages?.adapter = adapter
        val layoutManager = GridLayoutManager(this, 2)
        layoutManager.spanSizeLookup = object :GridLayoutManager.SpanSizeLookup(){
            override fun getSpanSize(position: Int): Int {
                return if(position == 0) 2 else 1
            }
        }
        rvMessages?.layoutManager = layoutManager
        initSwipeGesture()
    }

    private fun addMessage() {
        val message = Message(edtTextRecycler?.text.toString(), edtTitleRecycler?.text.toString())
        messages.add(message)
        adapter.notifyItemInserted(messages.lastIndex)
        edtTextRecycler.text?.clear()
        edtTextRecycler.text?.clear()
        edtTitleRecycler?.requestFocus()
    }

    private fun initSwipeGesture(){
        val swipe = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT){
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                messages.removeAt(position)
                adapter.notifyItemRemoved(position)
            }
        }
        val itemTouchHelper = ItemTouchHelper(swipe)
        itemTouchHelper.attachToRecyclerView(rvMessages)
    }

    private fun onMessageItemClick(message:Message) {
        val s = "${message.title}\n${message.text}"
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show()
    }
}


