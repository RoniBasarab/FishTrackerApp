package com.hit.fishtracker.data.adapter
import android.annotation.SuppressLint
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hit.fishtracker.R
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

                binding.txtFishName.text = fish.name
                binding.btnFishAge.text =   "Life Expectancy"
                binding.btnFishLength.text = "Maximum Length"
                binding.btnFishWeight.text = "Maximum Weight"
                binding.btnFishEnvironment.text = "Which environment does it live in?"
                binding.txtFishFact.text = fish.biology
                binding.txtFishFact.isSelected = true
                setIcon(fish)
                setOnClickListeners(fish)
            }
        }

        private fun setOnClickListeners(fish: Fish)
        {

            binding.btnFishAge.setOnClickListener {
                flipAnimation(it,binding.btnFishAge, fish.maxAge.toString())
            }
            binding.btnFishLength.setOnClickListener {
                flipAnimation(it,binding.btnFishLength, fish.maxLength.toString())
            }
            binding.btnFishWeight.setOnClickListener {
                flipAnimation(it,binding.btnFishWeight, fish.maxWeight.toString())
            }
            binding.btnFishEnvironment.setOnClickListener {
                flipAnimation(it,binding.btnFishEnvironment, fish.environment)
            }

        }

        private fun setIcon(fish: Fish)
        {
            if(fish.maxWeight != null)
            {
                if(fish.maxWeight <= 10f) {
                    Glide.with(binding.root)
                        .load(R.drawable.ic_small_fish)
                        .into(binding.imgFishIcon)
                    binding.imgFishIcon.visibility = View.VISIBLE
                }

                else if(fish.maxWeight > 10f && fish.maxWeight < 15f){
                    Glide.with(binding.root)
                        .load(R.drawable.ic_medium_fish)
                        .into(binding.imgFishIcon)
                    binding.imgFishIcon.visibility = View.VISIBLE
                }

                else {
                    Glide.with(binding.root)
                        .load(R.drawable.ic_big_fish)
                        .into(binding.imgFishIcon)
                    binding.imgFishIcon.visibility = View.VISIBLE
                }
            }
        }

        private fun flipAnimation(button: View, tv: TextView, text: String){

            button.animate().apply {
                duration = 1000
                rotationYBy(360f)
            }.withEndAction {
                tv.text = text
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