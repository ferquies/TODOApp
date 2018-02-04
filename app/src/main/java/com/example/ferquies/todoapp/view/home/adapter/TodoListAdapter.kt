package com.example.ferquies.todoapp.view.home.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.example.ferquies.todoapp.R
import com.example.ferquies.todoapp.base.inflate
import com.example.ferquies.todoapp.domain.database.Todo
import kotlinx.android.synthetic.main.todo_item.view.todoDetail
import kotlinx.android.synthetic.main.todo_item.view.todoTitle
import java.util.Collections
import javax.inject.Inject

/**
 * Created by Fernando Q. Esquitino
 * Email: ferquies@gmail.com
 * Twitter: @ferquies
 * 1/21/18
 */
class TodoListAdapter @Inject constructor(private val callback: TodoListAdapter.Callback) :
        RecyclerView.Adapter<TodoListAdapter.TodoViewHolder>(), ItemTouchHelperAdapter {

    private var todoList: MutableList<Todo> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val inflatedView = parent.inflate(R.layout.todo_item, false)
        return TodoViewHolder(inflatedView, callback)
    }

    override fun getItemCount() = todoList.size

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.bind(todoList[position])
    }

    override fun onItemDismiss(position: Int) {
        val todo = todoList[position]
        todoList.removeAt(position)
        notifyItemRemoved(position)
        callback.onItemDismiss(todo)
    }

    override fun onItemNextState(position: Int) {
        val todo = todoList[position]
        todoList.removeAt(position)
        notifyItemRemoved(position)
        callback.onItemNextState(todo)
    }

    override fun onItemMove(fromPosition: Int, toPosition: Int) {
        if (fromPosition < toPosition) {
            for (i in fromPosition until toPosition) {
                Collections.swap(todoList, i, i + 1)
            }
        } else {
            for (i in fromPosition downTo toPosition + 1) {
                Collections.swap(todoList, i, i - 1)
            }
        }

        notifyItemMoved(fromPosition, toPosition)
    }

    override fun onItemEndMove() {
        val tasks = ArrayList<Todo>()
        for (index in 0 until todoList.size) {
            if (todoList[index].sequence != index) {
                tasks.add(todoList[index].copy(sequence = index))
            }
        }
        callback.onItemMoved(tasks)
    }

    fun setItems(todoList: List<Todo>) {
        this.todoList = todoList.toMutableList()
        notifyDataSetChanged()
    }

    interface Callback {
        fun onItemClick(todo: Todo)

        fun onItemDismiss(todo: Todo): Boolean

        fun onItemNextState(todo: Todo): Boolean

        fun onItemMoved(tasks: List<Todo>)
    }

    class TodoViewHolder(private var view: View, private val callback: TodoListAdapter.Callback) :
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
