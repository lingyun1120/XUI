package me.xtp.xui.jetpack

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import me.xtp.xui.R

class ViewPager2Adapter : RecyclerView.Adapter<ItemHolder>() {

    val data = arrayOf(
        "https://ae01.alicdn.com/kf/U1f7285c8591e4bc783ba76d310b31580D.jpg",
        "https://ae01.alicdn.com/kf/Uddd26ae9e5594e7593ac06ba13443c339.jpg",
        "https://ae01.alicdn.com/kf/U5f7e207c55be476e96b4d93c498de5e0N.jpg",
        "https://ae01.alicdn.com/kf/U6675009216ac45398e7ea7e75b66fe01J.jpg"
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        return ItemHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.layout_vp2_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.setData(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }
}

class ItemHolder(view: View) : RecyclerView.ViewHolder(view) {
    val ivImage: ImageView = view.findViewById(R.id.ivImage)
    fun setData(url: String) {
        Glide.with(ivImage).load(url).into(ivImage)
    }
}