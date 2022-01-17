package com.hit.fishtracker.data.adapter
import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hit.fishtracker.data.database.FishDatabase.db
import com.hit.fishtracker.data.model.Fish
import com.hit.fishtracker.databinding.FishItemBinding

class FishAdapter: RecyclerView.Adapter<FishAdapter.FishViewHolder>()
{

    class FishViewHolder(private val binding: FishItemBinding) : RecyclerView.ViewHolder(binding.root)
    {
        @SuppressLint("SetTextI18n")
        fun bind(fish: Fish)
        {
            itemView.apply {
                binding.txtFishName.text = "Name: ${fish.name}"
                binding.txtFishAge.text =   "Life Expectancy: ${fish.maxAge.toString()}"
                binding.txtFishLength.text = "Maximum Length: ${ fish.maxLength.toString() }"
                binding.txtFishWeight.text = "Maximum Weight: ${fish.maxWeight.toString()}"
                binding.txtFishEnvironment.text = "Which environment does it live in?: ${fish.environment}"
                binding.txtFishFact.text = "Fun fact: ${fish.biology}"
                binding.txtFishFact.isSelected = true
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FishViewHolder {
        val binding = FishItemBinding.inflate(LayoutInflater.from(parent.context), parent,false)
        return FishViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FishViewHolder, position: Int) {
        holder.bind(db!!.fishDao().getAllFish()[position])
    }

    override fun getItemCount(): Int {
        return db!!.fishDao().getAllFish().size
    }
}