package com.example.rss

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import org.w3c.dom.Document
import org.w3c.dom.Element
import org.w3c.dom.NodeList
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL
import java.util.*
import javax.net.ssl.HttpsURLConnection
import javax.xml.parsers.DocumentBuilder
import javax.xml.parsers.DocumentBuilderFactory
import kotlin.collections.ArrayList

class DownloadRssTask(_url: String): Runnable {
    private var rssItems: ArrayList<RssItem> = ArrayList<RssItem>()
    private var url: String
    init {
        url = _url
    }
    fun getItems(): ArrayList<RssItem>{
        return  rssItems
    }
    override fun run() {
        try {
            val url: URL = URL(url)
            val connection: HttpURLConnection =
                url.openConnection() as HttpsURLConnection
            connection.connect()

            if (connection.responseCode == HttpURLConnection.HTTP_OK) {
                val inputStream: InputStream = connection.inputStream
                val dbf: DocumentBuilderFactory = DocumentBuilderFactory.newInstance()
                val db: DocumentBuilder = dbf.newDocumentBuilder()
                val document: Document = db.parse(inputStream)
                val element: Element = document.documentElement
                val nodeList: NodeList = element.getElementsByTagName("item")

                if (nodeList.length > 0) {
                    for (i in 0..nodeList.length) {
                        if (i != nodeList.length) {
                            var entry: Element = nodeList.item(i) as Element
                            var _titleE =
                                entry.getElementsByTagName("title").item(0) as Element
                            var _linkE =
                                entry.getElementsByTagName("link").item(0) as Element

                            var _title: String = _titleE.firstChild.nodeValue
                            var _link: String = _linkE.firstChild.nodeValue

                            var rssItem: RssItem =
                                RssItem(_title, _link)
                            rssItems.add(rssItem)
                        }
                    }
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
}
}