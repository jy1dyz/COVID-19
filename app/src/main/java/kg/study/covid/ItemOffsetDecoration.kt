package kg.study.covid

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class ItemOffsetDecoration(private var offset: Int) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        //Добавление отступов к ненулевому элементу
        if (parent.getChildAdapterPosition(view) == 0) {
            outRect.right = offset
            outRect.left = offset
            outRect.top = offset
            outRect.bottom = offset
        }
    }
}