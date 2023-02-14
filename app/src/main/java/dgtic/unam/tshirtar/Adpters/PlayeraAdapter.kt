package dgtic.unam.tshirtar.Adpters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dgtic.unam.tshirtar.Holders.PlayeraViewHolder
import dgtic.unam.tshirtar.Models.PlayeraModel
import dgtic.unam.tshirtar.R

public class PlayeraAdapter(val playeras: List<PlayeraModel>)  : RecyclerView.Adapter<PlayeraViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayeraViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return PlayeraViewHolder(layoutInflater.inflate(R.layout.playera_data, parent, false))
    }

    override fun onBindViewHolder(holder: PlayeraViewHolder, position: Int) {
        val item: PlayeraModel = playeras[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return playeras.size
    }
}