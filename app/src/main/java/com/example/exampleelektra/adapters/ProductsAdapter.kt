package com.example.exampleelektra.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.exampleelektra.R
import com.example.exampleelektra.models.Product
import kotlinx.android.synthetic.main.item_product.view.*

class ProductsAdapter(private  val mContext: Context) : RecyclerView.Adapter<ProductsAdapter.ProductsHolder> () {

    private var productList : MutableList<Product> = ArrayList()

    override fun onCreateViewHolder(parentView: ViewGroup, viewType: Int): ProductsHolder {
        val layoutInflater = LayoutInflater.from(parentView.context)
        return ProductsHolder(layoutInflater.inflate(R.layout.item_product, parentView, false))
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    override fun onBindViewHolder(holder: ProductsHolder, position: Int) {
        productList.sortBy { it.nombre }
        val item = productList[position]
        holder.bind(item, mContext)
    }

    fun setProductsList(listProducts: MutableList<Product>) {
        productList.clear()
        productList.addAll(listProducts)

        notifyDataSetChanged()
    }

    class ProductsHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name = itemView.tv_name_product_item as TextView
        val price = itemView.tv_price_product_item as TextView
        val priceWithoutPromo = itemView.tv_price_without_promo_product_item as TextView
        val weekPayment = itemView.tv_week_payment_product_item as TextView
        val image = itemView.iv_image_product_item as ImageView

        fun bind(product: Product, context: Context){
            Glide.with(context).load(product.image)
                .placeholder(android.R.drawable.ic_menu_gallery)
                .into(image)
            Glide.with(context)
                .load(R.drawable.img_msi_elektra)
                .placeholder(R.drawable.img_msi_elektra)//defect image
                .into(itemView.iv_msi_elektra_item)
            Glide.with(context)
                .load(R.drawable.img_time_elektra)
                .placeholder(R.drawable.img_time_elektra)//defect image
                .into(itemView.iv_time_elektra_item)

            val priceMoreTenPercent = product.precio + (product.precio * .10)
            val priceText = "De $priceMoreTenPercent"
            val pricePerWeek = product.precio / 10
            val weekPaymentText = "Desde $$pricePerWeek semanales con CREDITO"

            name.text = product.nombre
            priceWithoutPromo.text = priceText
            price.text = product.precio.toString()
            weekPayment.text = weekPaymentText
            itemView.setOnClickListener {
                Toast.makeText(context, "Presionado el proucto: $name", Toast.LENGTH_LONG).show()
            }
        }
    }

    fun filterList(filteredCourseList: ArrayList<Product>) {
        this.productList = filteredCourseList
        notifyDataSetChanged()
    }
}