package org.romeo.layer_presentation.core.list

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.databinding.library.baseAdapters.BR
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import org.romeo.layer_presentation.core.list.listeners.BaseClickListener
import org.romeo.layer_domain.entity.list.ListItem
import kotlin.reflect.cast


/**
 * The adapter that has to be used for the each recycler view in app
 * @param itemLayoutId - the map in which should be presented item layouts and
 * their keys
 *
 * @param listener - the item click listener
 *
 * @param bind - the code, that should be executed when the view is bound to
 * the viewHolder
 * */
class BaseAdapter<I : ListItem<I>, L : BaseClickListener>(
    private val itemLayoutId: Map<Int, Int>,
    private val listener: L? = null,
    private val bind: ((ViewDataBinding, data: I, listener: L?) -> Unit)? = null
) : ListAdapter<I, BaseAdapter<I, L>.BaseViewHolder>(BaseDiffUtilCallback<I>()) {

    inner class BaseViewHolder(
        private val binding: ViewDataBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: I, listener: L?) {
            val subClasses = data::class.sealedSubclasses
            val subClassFound = subClasses.find { it.isInstance(data) }

            try {
                val dataCasted = subClassFound!!.cast(data)
                binding.setVariable(BR.data, dataCasted)
            } catch (e: Exception) {
                try {
                    binding.setVariable(BR.data, data)
                } catch (e: Exception) {
                    Log.d(TAG, "bind: cannot inject a variable to xml")
                }
            }

            bind?.invoke(binding, data, listener)
        }
    }

    override fun getItemViewType(position: Int) =
        currentList[position].getViewType()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
/*        val view = LayoutInflater.from(parent.context)
            .inflate(itemLayoutId[viewType]!!, parent, false)*/

        val binding: ViewDataBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            itemLayoutId[viewType]!!,
            parent,
            false
        )

        return BaseViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.bind(getItem(position), listener)
    }

    companion object {
        const val DEFAULT_ITEM_LAYOUT_KEY = 0
        const val TAG = "BASE_ADAPTER"
    }
}