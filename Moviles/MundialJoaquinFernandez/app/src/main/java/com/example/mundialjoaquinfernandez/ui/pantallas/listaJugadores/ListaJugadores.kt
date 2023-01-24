package com.example.mundialjoaquinfernandez.ui.pantallas.listaJugadores

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.PorterDuff
import android.graphics.PorterDuffXfermode
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.mundialjoaquinfernandez.R
import com.example.mundialjoaquinfernandez.data.const.Constantes
import com.example.mundialjoaquinfernandez.databinding.ListaJugadoresBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class ListaJugadores : Fragment() {

    private var _binding: ListaJugadoresBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: ListaJugadoresAdapter

    //viewmodel
    private val viewModel: ListaJugadoresViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = ListaJugadoresBinding.inflate(inflater, container, false)
        val textView: TextView = binding.tvNombreEquipo
        val args: ListaJugadoresArgs by navArgs()
        val inputData = args.nombre
        textView.text = inputData
        viewModel.handleEvent(ListaJugadoresEvent.GetAll(inputData))
        adapter = ListaJugadoresAdapter()
        swipeDelete()
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            viewModel.state.observe(viewLifecycleOwner) {
                adapter.submitList(it.list)
            }

            rvJugadores.adapter = adapter


        }
    }


    private fun swipeDelete() {
        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(
            0, ItemTouchHelper.LEFT
        ) {
            private val ico = ContextCompat.getDrawable(requireContext(), R.drawable.outline_delete_24)
            private val intrinsicWidth = ico?.intrinsicWidth
            private val intrinsicHeight = ico?.intrinsicHeight
            private val clearPaint = Paint().apply { xfermode = PorterDuffXfermode(PorterDuff.Mode.CLEAR) }

            private val background = ColorDrawable()
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                val fromPosition = viewHolder.layoutPosition
                val toPosition = target.layoutPosition
                Collections.swap(adapter.currentList, fromPosition, toPosition)
                adapter.notifyItemMoved(fromPosition, toPosition)
                //add swipe left background

                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val jugador = adapter.currentList[viewHolder.layoutPosition]
                viewModel.handleEvent(ListaJugadoresEvent.DeleteJugador(jugador.nombre))
                Snackbar.make(binding.root, Constantes.JUGADOR_ELIMINADO, Snackbar.LENGTH_LONG)
                    .setAction(Constantes.DESHACER) {
                        viewModel.handleEvent(
                            ListaJugadoresEvent.InsertJugador(
                                jugador,
                                binding.tvNombreEquipo.text.toString()
                            )
                        )
                        adapter.notifyItemChanged(viewHolder.layoutPosition)
                    }.show()

            }

            override fun onChildDraw(
                c: Canvas,
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                dX: Float,
                dY: Float,
                actionState: Int,
                isCurrentlyActive: Boolean
            ) {
                val itemView = viewHolder.itemView
                val itemHeight = itemView.bottom - itemView.top
                val isCanceled = dX == 0f && !isCurrentlyActive
                val color1 = requireContext().getColor(R.color.red)


                if (isCanceled) {
                    clearCanvas(c, itemView.right + dX, itemView.top.toFloat(), itemView.right.toFloat(), itemView.bottom.toFloat())
                    super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
                    return
                }

                background.color = color1
                background.setBounds(itemView.right + dX.toInt(), itemView.top, itemView.right, itemView.bottom)
                background.draw(c)
                val deleteIconTop = itemView.top + (itemHeight - intrinsicHeight!!) / 2
                val deleteIconMargin = (itemHeight - intrinsicHeight) / 2
                val deleteIconLeft = itemView.right - deleteIconMargin - intrinsicWidth!!
                val deleteIconRight = itemView.right - deleteIconMargin
                val deleteIconBottom = deleteIconTop + intrinsicHeight

                ico?.setBounds(deleteIconLeft, deleteIconTop, deleteIconRight, deleteIconBottom)
                ico?.draw(c)


                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)

            }

            private fun clearCanvas(c: Canvas?, left: Float, top: Float, right: Float, bottom: Float) {
                c?.drawRect(left, top, right, bottom, clearPaint)
            }
        }).attachToRecyclerView(binding.rvJugadores)
    }

}