package com.infosys.feedapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.infosys.feedapplication.Model.Feed
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.nio.charset.Charset

class MainActivity : AppCompatActivity() {

    val feeds = ArrayList<Feed>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        try {
            val obj = JSONObject(loadJSONFromAsset())
            val rowList = obj.getJSONArray("rows")
            for (i in 0 until rowList.length()) {
                val feedDetail = rowList.getJSONObject(i)
                val title = feedDetail.getString("title")
                val description = feedDetail.getString("description")
                val thumb = feedDetail.getString("imageHref")

                val feed = Feed(title,description,thumb)
                feeds.add(feed)

            }
        }
        catch (e: JSONException) {
            e.printStackTrace()
        }

        rv_feed.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)
        rv_feed.adapter = FeedAdapter(this,feeds)



    }

    //TO LOAD JSON FROM ASSETS
    private fun loadJSONFromAsset(): String {
        val json: String?
        try {
            val inputStream = assets.open("data.json")
            val size = inputStream.available()
            val buffer = ByteArray(size)
            val charset: Charset = Charsets.UTF_8
            inputStream.read(buffer)
            inputStream.close()
            json = String(buffer, charset)
        }
        catch (ex: IOException) {
            ex.printStackTrace()
            return ""
        }
        return json
    }
}