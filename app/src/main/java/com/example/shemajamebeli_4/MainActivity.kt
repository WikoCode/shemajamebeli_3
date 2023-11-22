package com.example.shemajamebeli_4

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.shemajamebeli_4.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var ticTacToeGame: TicTacToeGame


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dimensionOptions = resources.getStringArray(R.array.dimension_options)
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, dimensionOptions)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spDimensions.adapter = adapter

        binding.spDimensions.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
            }
        }

        binding.btnStartGame.setOnClickListener {
            Log.d("ClickListenerBruh" , "IS WORKING? : ${binding.btnStartGame.isPressed}")
            val selectedDimension = binding.spDimensions.selectedItem.toString().toInt()
            ticTacToeGame = TicTacToeGame(selectedDimension)
            val gameFragment: Fragment = TicTacToeFragment.newInstance(selectedDimension)

            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, gameFragment)
                .addToBackStack(null)
                .commit()
        }
    }

}
