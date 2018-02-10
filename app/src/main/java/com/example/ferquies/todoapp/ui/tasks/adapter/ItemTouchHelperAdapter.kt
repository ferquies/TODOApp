package com.example.ferquies.todoapp.ui.tasks.adapter

/**
 * Created by Fernando Q. Esquitino
 * Email: ferquies@gmail.com
 * Twitter: @ferquies
 * 2/1/18
 */
interface ItemTouchHelperAdapter {
    fun onItemMove(fromPosition: Int, toPosition: Int)

    fun onItemDismiss(position: Int)

    fun onItemNextState(position: Int)

    fun onItemEndMove()
}