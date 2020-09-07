package com.andela.gads.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.andela.gads.R
import com.andela.gads.api.Skiller
import com.andela.gads.databinding.SkillersItemBinding

class SkillersAdapter(
        private val skillers: List<Skiller>
) : RecyclerView.Adapter<SkillersAdapter.SkillerViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            SkillerViewHolder(
                    DataBindingUtil.inflate(
                            LayoutInflater.from(parent.context),
                            R.layout.skillers_item,
                            parent,
                            false

                    )
            )

    override fun onBindViewHolder(holder: SkillerViewHolder, position: Int) {
        holder.skillersItemBinding.skiller = skillers[position]

    }

    override fun getItemCount() = skillers.size

    inner class SkillerViewHolder(
            val skillersItemBinding: SkillersItemBinding
    ) : RecyclerView.ViewHolder(skillersItemBinding.root)

}