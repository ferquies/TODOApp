package com.example.ferquies.todoapp.view.profile.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.example.ferquies.todoapp.R
import com.example.ferquies.todoapp.base.inflate
import com.example.ferquies.todoapp.domain.database.Todo
import kotlinx.android.synthetic.main.todo_item.view.todoDetail
import kotlinx.android.synthetic.main.todo_item.view.todoTitle
import javax.inject.Inject

/**
 * Created by Fernando Q. Esquitino
 * Email: ferquies@gmail.com
 * Twitter: @ferquies
 * 1/21/18
 */
class TodoListAdapter @Inject constructor() :
        RecyclerView.Adapter<TodoListAdapter.TodoViewHolder>() {

    lateinit var callback: TodoListAdapter.Callback

    var travelsList: List<Todo> = ArrayList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val inflatedView = parent.inflate(R.layout.todo_item, false)
        return TodoViewHolder(inflatedView, callback)
    }

    override fun getItemCount() = travelsList.size

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.bind(travelsList[position])
    }

    interface Callback {
        fun onItemClick(todo: Todo)
    }

    class TodoViewHolder(var view: View, val callback: TodoListAdapter.Callback) :
            RecyclerView.ViewHolder(view) {

        fun bind(todo: Todo) {
            view.todoTitle.text = todo.title
            view.todoDetail.text = todo.detail
            view.setOnClickListener {
                callback.onItemClick(todo)
            }
        }
    }
}