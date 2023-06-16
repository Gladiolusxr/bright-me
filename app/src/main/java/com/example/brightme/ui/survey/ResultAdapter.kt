package com.example.brightme.ui.survey

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.brightme.data.retrofit.ProductRecommendation
import com.example.brightme.databinding.ItemRecommendationBinding

class ResultAdapter : ListAdapter<ProductRecommendation,ProductViewHolder> (ProductComparator) {

    var productListener: ((ProductRecommendation) -> Unit)? = null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProductViewHolder {
        val binding =
            ItemRecommendationBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.render(getItem(position), productListener)
    }

    internal object ProductComparator : DiffUtil.ItemCallback<ProductRecommendation>() {
        override fun areItemsTheSame(oldItem: ProductRecommendation, newItem: ProductRecommendation): Boolean {
            return oldItem == newItem
        }
        override fun areContentsTheSame(
            oldItem: ProductRecommendation,
            newItem: ProductRecommendation
        ): Boolean {
            return oldItem == newItem
        }
    }

}

class ProductViewHolder(private val binding: ItemRecommendationBinding) :RecyclerView.ViewHolder(binding.root) {

    fun render(productRecommendation: ProductRecommendation?, listener: ((ProductRecommendation) -> Unit)?) {

        binding.tvItemReccommendation.text = productRecommendation?.name
        binding.root.setOnClickListener {
            if (productRecommendation != null) {
                listener?.invoke(productRecommendation)
            }
        }
    }
}
