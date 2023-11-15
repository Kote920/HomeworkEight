package com.example.homeworkeight

import android.app.Activity
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.homeworkeight.databinding.ActivityUsersBinding
import com.example.homeworkeight.databinding.ItemRecyclerviewBinding

class UsersActivity : AppCompatActivity(),
    EquipmentRecyclerAdapter.OnDeleteClickListener,
    EquipmentRecyclerAdapter.OnEditClickListener {


    private val equipments = mutableListOf<Equipment>()
    private lateinit var binding: ActivityUsersBinding
    private lateinit var bindingItem: ItemRecyclerviewBinding
    private lateinit var adapter: EquipmentRecyclerAdapter
    private val EDIT_REQUEST_CODE = 100
    val result = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {


        if (it.resultCode == Activity.RESULT_OK) {

            Toast.makeText(this, "fads", Toast.LENGTH_SHORT).show()
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {

                val equipment = it.data?.getParcelableExtra("edited_item", Equipment::class.java)
                Toast.makeText(this, "fdas", Toast.LENGTH_SHORT).show()


                equipment?.let {
                    val position =
                        equipments.indexOfFirst { item -> item.email == it.email }
                    if (position != -1) {
                        equipments[position] = it
                        Toast.makeText(this, "fdsa", Toast.LENGTH_SHORT).show()
                        adapter.notifyItemChanged(position)

                    }
                }
            } else {
                val equipment = it.data?.getParcelableExtra<Equipment>("edited_item")
                equipment?.let {
                    val position =
                        equipments.indexOfFirst { item -> item.email == it.email }
                    if (position != -1) {
                        equipments[position] = it
                        Toast.makeText(this, "fdsa", Toast.LENGTH_SHORT).show()
                        adapter.notifyItemChanged(position)
                    }
                }
            }
        } else {

            Toast.makeText(this, "error!", Toast.LENGTH_SHORT).show()
        }


    }

    override fun onStart() {
        super.onStart()

        
        
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUsersBinding.inflate(layoutInflater)
        bindingItem = ItemRecyclerviewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUp()
    }


    private fun setUp() {
        setEquipmentData()
        setUpEquipmentRecycler()
        binding.btnAddUser.setOnClickListener {
            val eq = Equipment("new user", "no instructions were provided for this part", "example@email.com")
            equipments.add(eq)
            adapter.addItem(eq)
        }


    }

    


    private fun setUpEquipmentRecycler() {
        adapter = EquipmentRecyclerAdapter(equipments, this, this)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

    }

    override fun onDeleteClick(position: Int) {
        equipments.removeAt(position)
        adapter.notifyItemRemoved(position)
    }

    override fun onEditClick(item: Equipment) {
        val intent = Intent(this, UserActivity::class.java)
        intent.putExtra("user", item)
        val arr : Array<Equipment> = equipments.toTypedArray()
        intent.putExtra("equipments",arr)
        startActivity(intent)
    }

    private fun setEquipmentData() {
        equipments.also {
            it.add(
                Equipment(
                    "Konstantine",
                    "Japaridze",
                    "konstantine.japaridze.1@btu.edu.ge"
                )
            )


        }
    }
}