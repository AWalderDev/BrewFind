package com.brewfind.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.NotificationCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.brewfind.BreweryDetailActivity
import com.brewfind.R
import com.brewfind.dto.Brewery
import kotlinx.android.synthetic.main.main_fragment.*
import kotlinx.android.synthetic.main.visited_fragment.*

class VisitedFragment : Fragment() {

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.visited_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        visitedBreweriesRecyclerView.layoutManager = LinearLayoutManager(context!!)
        visitedBreweriesRecyclerView.adapter = RecyclerAdapter(context!!, ArrayList(), ClickCallback())
        viewModel.getVisitedFromDB(context!!)

        viewModel.breweries.observe(this, Observer<ArrayList<Brewery>>  {it->
            (visitedBreweriesRecyclerView.adapter as RecyclerAdapter).setData(it)
            visitedBreweriesRecyclerView.adapter?.notifyDataSetChanged()
        })
    }

    companion object {
        fun newInstance() = VisitedFragment()
    }

    inner class ClickCallback : OnClickCallback {
        override fun onClick(brewery: Brewery) {
            val intent = Intent(context, BreweryDetailActivity::class.java)
            intent.putExtra(NotificationCompat.EXTRA_PEOPLE, brewery)
            startActivity(intent)
        }
    }

}
