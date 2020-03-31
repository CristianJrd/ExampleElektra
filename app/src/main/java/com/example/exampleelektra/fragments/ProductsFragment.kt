package com.example.exampleelektra.fragments

import GetProductsQuery
import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.apollographql.apollo.ApolloCall
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.exception.ApolloException
import com.bumptech.glide.Glide
import com.example.exampleelektra.R
import com.example.exampleelektra.adapters.ProductsAdapter
import com.example.exampleelektra.models.Product
import com.example.exampleelektra.network.apolloClient
import kotlinx.android.synthetic.main.fragment_first.*
import kotlinx.android.synthetic.main.fragment_first.view.*
import kotlinx.android.synthetic.main.relative_products.*
import kotlinx.android.synthetic.main.toolbar_nav_drawer.*

class ProductsFragment : Fragment() {
    private var myListenerFragment : MyListener? = null
    private val listProducts = mutableListOf<Product>()
    private lateinit var recyclerView : RecyclerView
    private lateinit var adapter: ProductsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
        ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_first, container, false)
        initViews(rootView)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        context?.let {
            recyclerView = rv_list_fragment_products
            recyclerView.setHasFixedSize(true)
            adapter = ProductsAdapter(it)
            recyclerView.layoutManager = LinearLayoutManager(it, LinearLayoutManager.VERTICAL, false)
            recyclerView.adapter = adapter

            getData()

            activity?.et_search_products_toolbar?.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(char: Editable?) {
                    searchData(char.toString())
                }
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

                }
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                }
            })
        }
    }

    @SuppressLint("DefaultLocale")
    private fun searchData(searchChar: String) {
        val filteredCourseAry: ArrayList<Product> = ArrayList()
        for (product in listProducts) {
            if (product.nombre.toLowerCase().contains(searchChar.toLowerCase())) {
                filteredCourseAry.add(product)
            }
        }
        if(filteredCourseAry.isEmpty() && filteredCourseAry.size == 0){
            Toast.makeText(context, "No se encuentran coincidencias.", Toast.LENGTH_SHORT).show()
        }
        adapter.filterList(filteredCourseAry)
    }

    private fun getData() {
        val spinner: Spinner = sort_products
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter.createFromResource(
            context!!,
            R.array.options_sort_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            spinner.adapter = adapter
        }

        val variables = arrayListOf(
            "321323142",
            "321323076",
            "45025344",
            "45024957",
            "1003678",
            "321321586",
            "45025337",
            "321323141",
            "45035651",
            "45048926",
            "1007046",
            "1006062",
            "1007073",
            "321324716",
            "1006025",
            "1005116",
            "1006593",
            "1006581",
            "1006945",
            "321321538")
        val productsQuery = GetProductsQuery.builder().skus(variables).build()

        apolloClient.query(productsQuery).enqueue(object: ApolloCall.Callback<GetProductsQuery.Data>() {
            override fun onFailure(e: ApolloException) {
                Log.e("ERRORAP", "$e", e)
            }

            override fun onResponse(response: Response<GetProductsQuery.Data>) {
                val prod = response.data()?.viewer()?.products()
                prod?.forEach {
                    Log.d("DATA", "$it")
                    val product = Product(id = it.id()!!, nombre = it.name()!!, precio = it.price()!!, image = it.image()!!)
                    listProducts.add(product)
                    activity?.runOnUiThread {
                        adapter.setProductsList(listProducts)
                    }
                }
            }
        })
    }

    private fun initViews(rootView: View?) {
        rootView?.let{
            Glide.with(this)
                .load(R.drawable.img_promo_elektra)
                .placeholder(R.drawable.img_promo_elektra)//defect image
                .into(it.iv_promo_elektra_main)
        }
    }

    fun setCallback(myListener: MyListener) {
        myListenerFragment = myListener
    }

    /**
     * AQUI RECIVE LA INFORMACION DEL ACTIVITI(TIPO INTENT.EXTRA)
     */
    companion object {
        @JvmStatic
        fun newInstance() =
            ProductsFragment()
    }
}
