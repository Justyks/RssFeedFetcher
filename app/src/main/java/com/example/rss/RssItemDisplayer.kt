package com.example.rss

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle
import android.widget.*;
import java.util.*;
import java.text.*;
import java.net.*;
import java.io.*;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import android.view.*;

class RssItemDisplayer: Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.rss_item_displayer)

        val selectedRssItem: RssItem? = MainActivity.selectedRssItem
        val titleTv: TextView = findViewById(R.id.titleTextView)
        val contentTv: TextView = findViewById(R.id.contentTextView)

        var title: String = ""
        var content: String = ""
        val sdf: SimpleDateFormat = SimpleDateFormat("MM/dd - hh:mm:ss")
        if (selectedRssItem != null) {
            title = "\n" + selectedRssItem.getTitle() + "\n" + selectedRssItem.getLink()
        }

        titleTv.text = title
        contentTv.text = content

    }
}