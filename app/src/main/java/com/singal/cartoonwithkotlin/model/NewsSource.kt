package com.singal.cartoonwithkotlin.model

import com.singal.cartoonwithkotlin.getHtml
import org.jsoup.Jsoup
import java.util.ArrayList

/**
 * Created by li on 2017/6/22.
 */
class NewsSource() : Source<ArrayList<NewsContainer>> {
    override fun obtain(url: String): ArrayList<NewsContainer> {
        val list = ArrayList<NewsContainer>()

        val html = getHtml(url)
        val doc = Jsoup.parse(html)

        val elements = doc.select("div.reportersBox").select("div.reportersMain")
        for (element in elements) {
            val title = element.select("div.mangeListTitle").select("span").text()
            val newsList = ArrayList<News>()

            for (ele in element.select("ul.reportersList").select("li").select("a")) {
                val title_news = ele.select("span")[0].text().replace("&amp", "\t")
                val createdTime = ele.select("span")[1].text()
                val link = "http://ishuhui.net/" + ele.attr("href")
                val news = News(title_news, createdTime, link)
                newsList.add(news)
            }

            val newsContainer = NewsContainer(title, newsList)

            list.add(newsContainer)
        }

        return list

    }

}