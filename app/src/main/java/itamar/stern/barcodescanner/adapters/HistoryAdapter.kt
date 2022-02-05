package itamar.stern.barcodescanner.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import itamar.stern.barcodescanner.databinding.HistoryItemBinding
import itamar.stern.barcodescanner.models.HistoryItem

class HistoryAdapter ( private val history: List<HistoryItem>) : RecyclerView.Adapter<HistoryAdapter.VH>() {
    class VH(val binding: HistoryItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH =
        VH(
            HistoryItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.binding.textViewUrl.text = history[position].url
    }

    override fun getItemCount() = history.size

}