package com.example.ferquies.todoapp.view.home.adapter

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.ViewHolder
import android.support.v7.widget.helper.ItemTouchHelper
import com.example.ferquies.todoapp.R

/**
 * Created by Fernando Q. Esquitino
 * Email: ferquies@gmail.com
 * Twitter: @ferquies
 * 2/1/18
 */
class ItemTouchHelperCallback(private val adapter: ItemTouchHelperAdapter) :
        ItemTouchHelper.Callback() {

    private val paint = Paint()

    override fun isLongPressDragEnabled(): Boolean {
        return true
    }

    override fun isItemViewSwipeEnabled(): Boolean {
        return true
    }

    override fun getMovementFlags(recyclerView: RecyclerView?, viewHolder: ViewHolder?): Int {
        val dragFlags = ItemTouchHelper.UP or ItemTouchHelper.DOWN
        val swipeFlags = ItemTouchHelper.START or ItemTouchHelper.END
        return makeMovementFlags(dragFlags, swipeFlags)
    }

    override fun onMove(recyclerView: RecyclerView, viewHolder: ViewHolder,
            target: ViewHolder): Boolean {
        adapter.onItemMove(viewHolder.adapterPosition, target.adapterPosition)
        return true
    }

    override fun onSwiped(viewHolder: ViewHolder, direction: Int) {
        if (direction == ItemTouchHelper.END) {
            adapter.onItemNextState(viewHolder.adapterPosition)
        } else {
            adapter.onItemDismiss(viewHolder.adapterPosition)
        }
    }

    override fun onChildDraw(c: Canvas, recyclerView: RecyclerView?, viewHolder: ViewHolder,
            dX: Float, dY: Float, actionState: Int, isCurrentlyActive: Boolean) {
        val icon: Bitmap
        if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {

            val itemView = viewHolder.itemView
            val height = itemView.bottom.toFloat() - itemView.top.toFloat()
            val width = height / 3

            if (dX > 0) {
                paint.color = Color.parseColor("#388E3C")
                val background = RectF(itemView.left.toFloat(), itemView.top.toFloat(),
                        itemView.right.toFloat() + dX,
                        itemView.bottom.toFloat())
                c.drawRect(background, paint)
                icon = BitmapFactory.decodeResource(adapter.getResources(), R.drawable.ic_menu_send)
                val iconDest = RectF(itemView.left.toFloat() + width,
                        itemView.top.toFloat() + width,
                        itemView.left.toFloat() + 2 * width, itemView.bottom.toFloat() - width)
                c.drawBitmap(icon, null, iconDest, paint)
            } else {
                paint.color = Color.parseColor("#D32F2F")
                val background = RectF(itemView.right.toFloat() + dX, itemView.top.toFloat(),
                        itemView.right.toFloat(), itemView.bottom.toFloat())
                c.drawRect(background, paint)
                icon = BitmapFactory.decodeResource(adapter.getResources(),
                        R.drawable.ic_menu_share)
                val iconDest = RectF(itemView.right.toFloat() - 2 * width,
                        itemView.top.toFloat() + width, itemView.right.toFloat() - width,
                        itemView.bottom.toFloat() - width)
                c.drawBitmap(icon, null, iconDest, paint)
            }
        }

        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
    }
}