package com.example.rss

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.NetworkCapabilities.*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.webkit.WebView
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    companion object {
        var selectedRssItem: RssItem? = null
    }

    var feedUrl: String = ""
    var rssItems: ArrayList<RssItem> = ArrayList<RssItem>()
    var aa: ArrayAdapter<RssItem>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rssURLTV: TextView = findViewById(R.id.rssURL)
        val fetchRss: Button = findViewById(R.id.fetchRss)
        val rssListView: ListView = findViewById(R.id.rssListView)
        aa = ArrayAdapter<RssItem>(this, R.layout.list_item, rssItems)
        rssListView.adapter = aa
        feedUrl = rssURLTV.text.toString()

        fetchRss.setOnClickListener {
            feedUrl = rssURLTV.text.toString()
            aa?.notifyDataSetChanged()
            refreshRssList()
        }


        rssListView.setOnItemClickListener(AdapterView.OnItemClickListener() { adapterView: AdapterView<*>, view: View, index: Int, arg3: Long ->
            val intent: Intent = Intent(this@MainActivity, WebViewActivity::class.java)
            intent.putExtra("URL", rssItems.get(index).getLink())
            startActivity(intent)
        })
    }

    //https://habr.com/ru/rss/all/all/?fl=ru
    private fun refreshRssList() {
        var task: DownloadRssTask = DownloadRssTask(feedUrl)
        var thread: Thread = Thread(task)
        thread.start()
        thread.join()
        rssItems.clear()
        rssItems.addAll(task.getItems())
        aa!!.notifyDataSetChanged()
    }
}