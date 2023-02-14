package dgtic.unam.tshirtar.Holders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import dgtic.unam.tshirtar.Models.PlayeraModel
import dgtic.unam.tshirtar.databinding.PlayeraDataBinding

class PlayeraViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private  val binding = PlayeraDataBinding.bind(view)

    fun bind(playera : PlayeraModel){
        Picasso.get().load(playera.image).into(binding.imageView)
        binding.title.text = playera.title
        binding.price.text = playera.price
        binding.description.text = playera.description
    }
}

