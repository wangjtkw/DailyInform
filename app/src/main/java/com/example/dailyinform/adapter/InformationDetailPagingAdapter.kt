package com.example.dailyinform.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import com.example.dailyinform.MyApplication
import com.example.dailyinform.R
import com.example.dailyinform.bean.DetailBean
import com.example.dailyinform.databinding.ItemDetailBinding
import com.example.dailyinform.holder.BaseHolder
import com.example.dailyinform.holder.DetailHolder
import com.example.dailyinform.holder.DetailSource
import com.example.dailyinform.utils.ToastUtil

class InformationDetailPagingAdapter(private val callback: (url: String, title: String, cover: String?) -> Unit) :
    PagedListAdapter<DetailBean, BaseHolder>(diffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseHolder {
        val binding = DataBindingUtil.inflate<ItemDetailBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_detail, parent, false
        )
        return DetailHolder(binding)
    }

    override fun onBindViewHolder(holder: BaseHolder, position: Int) {
        if (holder is DetailHolder) {
            val detailBean = getItem(position) ?: return
            holder.bind(DetailSource(detailBean))
            holder.getView().setOnClickListener {
                detailBean.apply {

                    if (!url.isNullOrEmpty() && !title.isNullOrBlank()) {
                        callback(detailBean.url, detailBean.title, detailBean.cover)
                    } else {
                        ToastUtil.setToast("稍等一下，马上就好", MyApplication.getContext())
                    }
                }
            }
        }
    }

    companion object {
        val diffCallback = object : DiffUtil.ItemCallback<DetailBean>() {
            override fun areItemsTheSame(oldItem: DetailBean, newItem: DetailBean): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: DetailBean, newItem: DetailBean): Boolean {
                return oldItem.detailId == newItem.detailId
            }
        }
    }
}