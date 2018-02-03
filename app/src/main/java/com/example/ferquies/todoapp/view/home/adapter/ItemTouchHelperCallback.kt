package com.example.ferquies.todoapp.view.home.adapter

import android.content.Context
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
class ItemTouchHelperCallback(private val adapter: ItemTouchHelperAdapter,
        private val context: Context) :
        ItemTouchHelper.Callback() {

    private val paint = Paint()
    private var dragFrom = -1
    private var dragTo = -1
    private var orderChanged = false

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
        val fromPosition = viewHolder.adapterPosition
        val toPosition = target.adapterPosition

        if (dragFrom == -1) {
            dragFrom = fromPosition
        }

        dragTo = toPosition
        orderChanged = true
        adapter.onItemMove(fromPosition, toPosition)

        return true
    }

    override fun onSelectedChanged(viewHolder: ViewHolder?, actionState: Int) {
        super.onSelectedChanged(viewHolder, actionState)

        if (actionState == ItemTouchHelper.ACTION_STATE_IDLE && orderChanged) {
            if (dragFrom != -1 && dragTo != -1 && dragFrom != dragTo) {
                adapter.onItemEndMove(dragFrom, dragTo)
            }

            dragFrom = -1
            dragTo = -1
            orderChanged = false
        }
    }

    override fun onSwiped(viewHolder: ViewHolder, direction: Int) {
        if (direction == ItemTouchHelper.END) {
            adapter.onItemNextState(viewHolder.adapterPosition)
        } else {
            adapter.onItemDismiss(viewHolder.adapterPosition)
        }
    }

    override fun onChildDraw(canvas: Canvas, recyclerView: RecyclerView?, viewHolder: ViewHolder,
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
                canvas.drawRect(background, paint)
                icon = BitmapFactory.decodeResource(context.resources,
                        R.drawable.arrow_right_bold)
                val iconDest = RectF(itemView.left.toFloat() + width,
                        itemView.top.toFloat() + width,
                        itemView.left.toFloat() + 2 * width, itemView.bottom.toFloat() - width)
                canvas.drawBitmap(icon, null, iconDest, paint)
            } else {
                paint.color = Color.parseColor("#D32F2F")
                val background = RectF(itemView.right.toFloat() + dX, itemView.top.toFloat(),
                        itemView.right.toFloat(), itemView.bottom.toFloat())
                canvas.drawRect(background, paint)
                icon = BitmapFactory.decodeResource(context.resources,
                        R.drawable.delete)
                val iconDest = RectF(itemView.right.toFloat() - 2 * width,
                        itemView.top.toFloat() + width, itemView.right.toFloat() - width,
                        itemView.bottom.toFloat() - width)
                canvas.drawBitmap(icon, null, iconDest, paint)
            }
        }

        super.onChildDraw(canvas, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
    }
}