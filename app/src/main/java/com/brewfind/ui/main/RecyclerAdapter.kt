package com.brewfind.ui.main

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.brewfind.R
import com.brewfind.dto.Brewery
import java.text.FieldPosition

class RecyclerAdapter(
    var context: Context,
    var list: ArrayList<Brewery>,
    onClickCallback: OnClickCallback
) :
    RecyclerView.Adapter<RecyclerAdapter.MyViewHolder>() {
    private var mOnClickCallback = onClickCallback

    override fun onBindViewHolder(p0: MyViewHolder, p1: Int) {
        p0.breweryName.text = list[p1].name
        p0.address.text = list[p1].street
        p0.city.text = list[p1].city
        p0.state.text = list[p1].state
        p0.distance.text = "Distance: " + list[p1].distance
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MyViewHolder {
        val view: View = LayoutInflater.from(context).inflate(R.layout.brewery_card, p0, false)
        val holder = MyViewHolder(view)
        view.tag = p1
        view.setOnClickListener(ClickListener())
        return holder
    }

    override fun getItemCount(): Int {
        return list?.size
    }

    fun setData(breweries: ArrayList<Brewery>) {
        list = breweries
    }

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var breweryName: TextView = view.findViewById(R.id.breweryName) as TextView
        var address: TextView = view.findViewById(R.id.address) as TextView
        var city: TextView = view.findViewById(R.id.city) as TextView
        var state: TextView = view.findViewById(R.id.state) as TextView
        var distance: TextView = view.findViewById(R.id.distance) as TextView
    }

    inner class ClickListener : View.OnClickListener {
        override fun onClick(v: View?) {
            lateinit var be: Brewery
            for (b in list) {
                if (v != null) {
                    var tv: TextView = v.findViewById(R.id.breweryName) as TextView
                    if (tv.text.equals(b.name)) {
                        be = b
                    }
                }
            }
            mOnClickCallback.onClick(be)
        }
    }


}