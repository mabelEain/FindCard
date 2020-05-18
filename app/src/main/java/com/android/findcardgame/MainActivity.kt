package com.android.findcardgame

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.findcardgame.adapter.GridItemDecoration
import com.android.findcardgame.adapter.RecyclerViewAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() , RecyclerViewAdapter.Listener{

    private lateinit var adapter: RecyclerViewAdapter
    private var randomList: List<String>? = null
    private var count: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
/*
        var randomValues = List(6) { (0..100).random() }
        randomList = randomValues.plus(randomValues)
        randomList!!.shuffled()
*/
        var myfrontValue = List(12){"?"}
        randomList = myfrontValue
        recycler_view.setHasFixedSize(true)
        recycler_view.layoutManager = GridLayoutManager(this, 3, RecyclerView.VERTICAL, false)
        adapter = RecyclerViewAdapter(randomList!!,this)
        recycler_view.adapter = adapter
        val padding = resources.getDimensionPixelSize(R.dimen.grid_spacing)
        recycler_view.addItemDecoration(GridItemDecoration(3, padding))

    }

    @SuppressLint("SetTextI18n")
    override fun onClickItem(position: Int) {
        count++
        txt_count.text = "STEPS: $count"
        Toast.makeText(this, randomList!![position] ,Toast.LENGTH_SHORT).show()
        }


}
