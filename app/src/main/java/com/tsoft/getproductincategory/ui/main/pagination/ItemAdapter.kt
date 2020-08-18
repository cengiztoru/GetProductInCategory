package com.tsoft.getproductincategory.ui.main.pagination

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagedListAdapter
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.tsoft.getproductincategory.R
import com.tsoft.getproductincategory.data.api.responses.ProductResponse
import com.tsoft.getproductincategory.databinding.ItemProductBinding


/**
 * Created by Cengiz TORU on 17/08/2020.
 * cengiz.toru@tsoft.com.tr
 */
//todo CHANGE TO PagingDataAdapter
class ItemAdapter :
    PagedListAdapter<ProductResponse.Data, ItemAdapter.CustomViewHolder>(
        DIFF_CALLBACK
    ) {
    class CustomViewHolder(
        val mBinding: ItemProductBinding
    ) : RecyclerView.ViewHolder(mBinding.root)


    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.mBinding.product = getItem(position)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        return CustomViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_product,
                parent,
                false
            )
        )
    }

    //DIFF UTIL
    private object DIFF_CALLBACK : DiffUtil.ItemCallback<ProductResponse.Data>() {
        override fun areItemsTheSame(
            oldItem: ProductResponse.Data,
            newItem: ProductResponse.Data
        ): Boolean {

            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: ProductResponse.Data,
            newItem: ProductResponse.Data
        ): Boolean {

            return oldItem.equals(newItem)
        }

    }

}