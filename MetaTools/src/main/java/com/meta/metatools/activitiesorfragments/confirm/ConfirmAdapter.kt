package com.meta.metatools.activitiesorfragments.confirm

import android.view.View
import androidx.core.content.ContextCompat
import com.meta.metacomponents.databinding.ItemConfirmBinding
import com.meta.metatools.R
import com.meta.metatools.base.BaseAdapter
import com.meta.metatools.models.ConfirmModel

class ConfirmAdapter: BaseAdapter<ConfirmModel, ItemConfirmBinding>() {

    override fun getLayout(): Int = R.layout.item_confirm

    override fun onBindViewHolder(
        holder: Companion.BaseViewHolder<ItemConfirmBinding>,
        position: Int
    ) {
        val item = itemList[position]

        if (item.image == null) {
            holder.binding.image.visibility = View.GONE
        } else {
            holder.binding.image.setImageDrawable(
                ContextCompat.getDrawable(
                    holder.binding.root.context,
                    item.image
                )
            )
        }
        if (item.title == null) {
            holder.binding.labelText.visibility = View.GONE
        } else {
            holder.binding.labelText.text = item.title
        }
    }
}