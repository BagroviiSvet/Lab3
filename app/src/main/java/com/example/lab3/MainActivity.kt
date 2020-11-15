package com.example.lab3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    companion object {
        val RSS_FEEDS = hashMapOf(
            "World news" to "https://www.cnbc.com/id/100727362/device/rss/rss.html",
            "Business" to "https://www.cnbc.com/id/10001147/device/rss/rss.html",
            "Technology" to "https://www.cnbc.com/id/19854910/device/rss/rss.html"
        )
    }

    private lateinit var recyclerView: RecyclerView
    private lateinit var channelAdapter: ChannelAdapter

    val selectedChannels = hashSetOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        channelAdapter = ChannelAdapter(this)
        channelAdapter.channelSortedList.addAll(RSS_FEEDS.keys)

        val viewManager = LinearLayoutManager(this)
        recyclerView = findViewById<RecyclerView>(R.id.recyclerView).apply {
            layoutManager = viewManager
            addItemDecoration(
                DividerItemDecoration(
                    this@MainActivity,
                    LinearLayoutManager.VERTICAL
                )
            )
            adapter = channelAdapter
        }
    }

    fun onDownloadClick(view: View?) {
        Intent(this, FeedActivity::class.java).apply {
            putExtra(FeedActivity.SELECTED_CHANNELS, selectedChannels.map { key -> RSS_FEEDS[key]!! }.toTypedArray())
        }.also {
            startActivity(it)
        }
    }
}