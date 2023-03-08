package com.example.rss

import kotlinx.coroutines.*
import org.w3c.dom.Document
import org.w3c.dom.Element
import org.w3c.dom.NodeList
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL
import java.text.SimpleDateFormat
import java.util.Date
import javax.net.ssl.HttpsURLConnection
import javax.xml.parsers.DocumentBuilder
import javax.xml.parsers.DocumentBuilderFactory

class RssItem(_title: String, _link: String) {
    private var title: String
    private var link: String

    init {
        title = _title
        link = _link
    }

    fun getTitle(): String {
        return title
    }

    fun getLink(): String {
        return link
    }

    override fun toString(): String {
        var result: String = getTitle();
        return result
    }

}



