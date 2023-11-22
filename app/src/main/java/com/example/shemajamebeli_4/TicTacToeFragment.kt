package com.example.shemajamebeli_4
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.shemajamebeli_4.databinding.FragmentTicTacToeBinding

class TicTacToeFragment : Fragment() {

    private var _binding: FragmentTicTacToeBinding? = null
    private val binding get() = _binding!!

    private lateinit var recyclerViewAdapter: TicTacToeAdapter

    private lateinit var ticTacToeGame: TicTacToeGame

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTicTacToeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("FragmentTag", "TicTacToeFragment onViewCreated")

        val dimension = arguments?.getInt(DIMENSION_KEY, 3) ?: 3

        ticTacToeGame = TicTacToeGame(dimension)

        binding.rvTicTacToe.layoutManager = GridLayoutManager(requireContext(), dimension)

        recyclerViewAdapter = TicTacToeAdapter(dimension, ticTacToeGame) { row, col ->
            ticTacToeGame.makeMove(row, col)
            recyclerViewAdapter.updateData(ticTacToeGame.getBoard())
        }

        binding.rvTicTacToe.adapter = recyclerViewAdapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val DIMENSION_KEY = "DIMENSION_KEY"

        fun newInstance(selectedDimension: Int): TicTacToeFragment {
            val fragment = TicTacToeFragment()
            val args = Bundle()
            args.putInt(DIMENSION_KEY, selectedDimension)
            fragment.arguments = args
            return fragment

        }

    }

}