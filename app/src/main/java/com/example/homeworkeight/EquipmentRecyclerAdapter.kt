package com.example.homeworkeight

import android.content.ClipData
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.homeworkeight.databinding.ItemRecyclerviewBinding

class EquipmentRecyclerAdapter(
    private val equipments: MutableList<Equipment>,
    private val onDeleteClickListener: OnDeleteClickListener,
    private val onEditClickListener: OnEditClickListener) :
    RecyclerView.Adapter<EquipmentRecyclerAdapter.EquipmentViewHolder>() {


    interface OnDeleteClickListener {
        fun onDeleteClick(position: Int)
    }
    interface OnEditClickListener {
        fun onEditClick(item: Equipment)
    }


    fun updateItem(position: Int, newItem: Equipment) {
        equipments[position] = newItem
        notifyItemChanged(position)

    }
    fun addItem(item: Equipment) {
        notifyItemInserted(equipments.size - 1)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EquipmentViewHolder {
        return EquipmentViewHolder(
            ItemRecyclerviewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return equipments.size
    }

    override fun onBindViewHolder(holder: EquipmentViewHolder, position: Int) {

     holder.bind()
        val currentItem = equipments[position]
        holder.binding.btnRemove.setOnClickListener {
            onDeleteClickListener.onDeleteClick(position)
        }
        holder.binding.btnEdit.setOnClickListener {
            onEditClickListener.onEditClick(currentItem)
        }





    }

    fun deleteItem(position: Int) {
        equipments.removeAt(position)
        notifyItemRemoved(position)
    }


    inner class EquipmentViewHolder(val binding: ItemRecyclerviewBinding) :
        RecyclerView.ViewHolder(binding.root) {

            fun bind(){
                val equipment = equipments[adapterPosition]
                with(binding) {
                    tvFirstName.text = equipment.firstName
                    tvLastName.text = equipment.lastName
                    tvEmail.text = equipment.email
                }
            }

    }
}