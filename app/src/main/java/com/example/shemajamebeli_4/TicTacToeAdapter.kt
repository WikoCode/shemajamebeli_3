package com.example.shemajamebeli_4

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.shemajamebeli_4.databinding.ItemTicTacToeCellBinding

class TicTacToeAdapter(
    private val dimension: Int,
    private val ticTacToeGame: TicTacToeGame,
    private val onCellClickListener: (row: Int, col: Int) -> Unit
) : RecyclerView.Adapter<TicTacToeAdapter.CellViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CellViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemTicTacToeCellBinding.inflate(inflater, parent, false)
        return CellViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CellViewHolder, position: Int) {
        val row = position / dimension
        val col = position % dimension

        holder.bind(row, col)
    }

    fun updateData(newData: List<List<TicTacToeGame.Player>>) {
        val diffResult = DiffUtil.calculateDiff(object : DiffUtil.Callback() {
            override fun getOldListSize(): Int = ticTacToeGame.getDimension() * ticTacToeGame.getDimension()

            override fun getNewListSize(): Int = newData.size * newData[0].size

            override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                return oldItemPosition == newItemPosition
            }

            override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                val oldRow = oldItemPosition / dimension
                val oldCol = oldItemPosition % dimension

                val newRow = newItemPosition / dimension
                val newCol = newItemPosition % dimension

                return ticTacToeGame.getPlayerState(oldRow, oldCol) == newData[newRow][newCol]
            }
        })

        diffResult.dispatchUpdatesTo(this)
    }

    override fun getItemCount(): Int = dimension * dimension

    inner class CellViewHolder(private val binding: ItemTicTacToeCellBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(row: Int, col: Int) {
            binding.root.setOnClickListener {
                if (ticTacToeGame.getPlayerState(row, col) == TicTacToeGame.Player.NONE) {
                    ticTacToeGame.makeMove(row, col)
                    onCellClickListener.invoke(row, col)
                }
            }

            val playerState = ticTacToeGame.getPlayerState(row, col)
            setDrawable(playerState, binding.ivCell)
        }

        private fun setDrawable(player: TicTacToeGame.Player, imageView: ImageView) {
            when (player) {
                TicTacToeGame.Player.NONE -> {
                    imageView.setImageResource(android.R.color.transparent)
                }
                TicTacToeGame.Player.X -> {
                    imageView.setImageResource(R.drawable.ic_x)
                }
                TicTacToeGame.Player.O -> {
                    imageView.setImageResource(R.drawable.ic_o)
                }
            }
        }
    }
}