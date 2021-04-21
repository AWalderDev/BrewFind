package com.brewfind.ui.main

import android.content.Intent
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.app.NotificationCompat.EXTRA_PEOPLE
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.brewfind.BreweryDetailActivity
import com.brewfind.R
import com.brewfind.dto.Brewery
import kotlinx.android.synthetic.main.main_fragment.*

class MainFragment : Fragment() {

    private lateinit var viewModel: MainViewModel

    private var searchType: String = ""

    private var currentPage: Int = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val searchSortSpinnerData = arrayOf("City", "State")
        spinner_SearchType.adapter = ArrayAdapter(
            context!!,
            R.layout.support_simple_spinner_dropdown_item,
            searchSortSpinnerData
        )

        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        searchRecyclerView.layoutManager = LinearLayoutManager(context!!)
        searchRecyclerView.adapter = RecyclerAdapter(context!!, ArrayList(), ClickCallback())
        viewModel.fetchBreweries(this, currentPage)

        viewModel.breweries.observe(this, Observer { it ->
            Log.println(
                Log.ERROR,
                "ss",
                "viewModel.breweries: " + viewModel.breweries.value?.size
            );
            (searchRecyclerView.adapter as RecyclerAdapter).setData(it)
            searchRecyclerView.adapter?.notifyDataSetChanged()
        })

        searchRecyclerView.setListener(object : RecyclerListener {
            override fun loadMore() {
                Toast.makeText(context!!, "loading...", Toast.LENGTH_SHORT).show()
                currentPage++
                if (searchType.equals("")) {
                    viewModel.fetchBreweries(context as LifecycleOwner, currentPage)
                } else if (searchType.equals("City")) {
                    viewModel.fetchBreweriesByCity(context as LifecycleOwner,editTextKeyWord.text.toString() ,currentPage)
                }else if (searchType.equals("State")) {
                    viewModel.fetchBreweriesByState(context as LifecycleOwner,editTextKeyWord.text.toString() ,currentPage)
                }
            }

            override fun refresh() {
                Toast.makeText(context!!, "refresh!", Toast.LENGTH_LONG).show()
            }
        })

        /**
         *  when the "search" button is clicked
         */
        btnSearch.setOnClickListener {
            //set current page to 1
            currentPage = 1;
            //get search type
            if (spinner_SearchType.selectedItem !== null && spinner_SearchType.selectedItem.toString()
                    .equals("City")
            ) {
                searchType = "City"
                viewModel.fetchBreweriesByCity(this, editTextKeyWord.text.toString(), currentPage)
            }
            if (spinner_SearchType.selectedItem !== null && spinner_SearchType.selectedItem.toString()
                    .equals("State")
            ) {
                searchType = "State"
                viewModel.fetchBreweriesByState(this, editTextKeyWord.text.toString(), currentPage)
            }
        }

    }

    inner class ClickCallback : OnClickCallback {
        override fun onClick(brewery: Brewery) {
            val intent = Intent(context, BreweryDetailActivity::class.java)
            intent.putExtra(EXTRA_PEOPLE, brewery)
            startActivity(intent)
        }
    }

    companion object {
        fun newInstance() = MainFragment()
    }


    fun RecyclerView.setListener(l: RecyclerListener) {
        setOnScrollListener(object : RecyclerView.OnScrollListener() {
            var lastVisibleItem: Int = 0
            val swipeRefreshLayout = this@setListener.parent
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val layoutManager: RecyclerView.LayoutManager = recyclerView.layoutManager!!

                if (layoutManager is LinearLayoutManager) {
                    lastVisibleItem =
                        (recyclerView.layoutManager as LinearLayoutManager).findLastVisibleItemPosition()
                } else if (layoutManager is GridLayoutManager) {
                    lastVisibleItem =
                        (recyclerView.layoutManager as GridLayoutManager).findLastVisibleItemPosition()
                } else if (layoutManager is StaggeredGridLayoutManager) {
                    val staggeredGridLayoutManager =
                        recyclerView.layoutManager as StaggeredGridLayoutManager
                    val lastPositions = IntArray(staggeredGridLayoutManager.spanCount)
                    staggeredGridLayoutManager.findLastVisibleItemPositions(lastPositions)
                    lastVisibleItem = findMax(lastPositions)
                }
            }

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItem + 1 == recyclerView.adapter?.itemCount) {
                    if (swipeRefreshLayout is SwipeRefreshLayout) {
                        if (!swipeRefreshLayout.isRefreshing) {
                            l.loadMore()
                        }
                    } else {
                        l.loadMore()
                    }
                }
            }

        })


    }

    /**
     *
     */
    fun findMax(lastPositions: IntArray): Int {
        var max = lastPositions[0]
        for (i in lastPositions.indices) {
            val value = lastPositions[i]
            if (value > max) {
                max = value
            }
        }

        return max;
    }

}
