package dev.alraj.singlechildlayout

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import androidx.core.view.children
import androidx.core.view.isVisible
import timber.log.Timber

/**
 * A FrameLayout implementation where only one child can be visible at a time.
 * When a child's visibility is set to View.VISIBLE or View.INVISIBLE, all other child's visibility is set to View.GONE.
 * This is useful when FrameLayout is used to show single child view at a time but don't need to set View.GONE explicitly to all other child views.
 */
class SingleChildLayout(context: Context, attrs: AttributeSet?) : FrameLayout(context, attrs) {
    private var visibleChildIndex = -1
    private var protectionFlag = false

    override fun onFinishInflate() {
        super.onFinishInflate()

        // if more than one child is visible from xml, hide every child except the last one
        if (getVisibleChildCount() > 1)
            children.filter { it.hasSpace() }.toList().dropLast(1).forEach { it.beGone() }
        visibleChildIndex = singleVisibleChildIndex
    }

    override fun onDescendantInvalidated(child: View, target: View) {
        super.onDescendantInvalidated(child, target)
        Timber.d("onDescendantInvalidated, child %s, target %s", child.javaClass.simpleName, target.javaClass.simpleName)
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        Timber.d("onLayout")
        // if view was changed for the layout, don't do anything
        if (protectionFlag) return

        // if the visible child is more than one, which should not be
        val visibleChildCount = getVisibleChildCount()
        when {
            visibleChildCount > 1 -> {
                val otherVisibleChild = getOtherVisibleChild()
                getChildAt(visibleChildIndex).beGone()
                visibleChildIndex = otherVisibleChild.index

            }
            visibleChildCount == 1 -> visibleChildIndex = singleVisibleChildIndex
            visibleChildCount == 0 -> visibleChildIndex = -1
        }
    }

    /**
     * Get the number of child which is has space in the layout, whether View.VISIBLE or View.INVISIBLE
     */
    private fun getVisibleChildCount() = children.count { it.hasSpace() }

    private val singleVisibleChildIndex: Int
        get() = children.indexOfFirst { it.hasSpace() }

    /**
     * Get the list of other visible children except already visible one
     */
    private fun getOtherVisibleChild(): View {
        return children.filterIndexed { index, view ->
            view.hasSpace() && index != visibleChildIndex
        }.first()
    }

    private val View.index: Int
        get() = indexOfChild(this)

    private fun View.hasSpace(): Boolean = isVisible || isInvisible

    private val View.isInvisible: Boolean
        get() = visibility == View.INVISIBLE

    private fun View.beGone() {
        protectionFlag = true
        visibility = View.GONE
        protectionFlag = false
    }
}