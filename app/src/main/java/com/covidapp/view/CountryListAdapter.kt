package com.covidapp.view


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.covidapp.R
import com.covidapp.services.model.Country
import com.google.gson.Gson
import kotlinx.android.synthetic.main.row_load.view.*

internal class CountryListAdapter(
    private val context: Context?,
    private var arrayListCountry: List<Country>
) : RecyclerView.Adapter<CountryListAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.row_load, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val gson = Gson().toJson(arrayListCountry[position].toString())
        holder.bind(gson)

    }

    override fun getItemCount(): Int {
        return if (arrayListCountry.isNotEmpty()) arrayListCountry.size else 0
    }


    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: String) {
            with(itemView) {
                tvCountryRow.text = Gson().toJson(item)
               
            }
        }
    }
}

