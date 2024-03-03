package com.example.booksharingv2.xml_converter

import android.util.Xml
import okhttp3.ResponseBody
import org.xmlpull.v1.XmlPullParser
import retrofit2.Converter

// Retrofit のカスタムコンバータを作成する
class XmlCustomConverter: Converter<ResponseBody, ApiBookDataList> {
    override fun convert(value: ResponseBody): ApiBookDataList {
        // PullParser のインスタンス作成
        val parser = Xml.newPullParser()
        parser.setInput(value.charStream())

        // 変数宣言
        var eventType = parser.eventType
        var currentTag: String? = null
        var currentNameSpace: String? = null
        var isDecodeIsbn = false // isbn の取得をするタイミングを管理するフラグ

        // 取得したデータ用
        var title = ""
        var author = ""
        var pubDate = ""
        var publisher = ""
        var price = ""
        var isbn = ""
        val books = mutableListOf<ApiBookData>()

        // 名前空間の URI を保持する変数（P7参考 https://www.ndl.go.jp/jp/dlib/standards/meta/2020/12/application-profile.pdf）
        val dc = "http://purl.org/dc/elements/1.1/"
        val dcndl = "http://ndl.go.jp/dcndl/terms/"
        val xsi = "http://www.w3.org/2001/XMLSchema-instance"

        // XML のイベントを処理
        while (eventType != XmlPullParser.END_DOCUMENT) {
            when (eventType) {
                XmlPullParser.START_TAG -> {
                    // 各変数の値を初期化
                    currentTag = parser.name
                    parser.getNamespace() ?: {currentNameSpace = parser.getNamespace()}
                    // isbn 取得をするか判定
                    if (currentNameSpace == dc && currentTag == "identifier" && parser.getAttributeValue(xsi, "type") == "dcndl:ISBN") {
                        isDecodeIsbn = true
                    }

                    // 要素名が item なら、変数を初期化する
                    if (currentTag == "item") {
                        title = ""
                        author = ""
                        pubDate = ""
                        publisher = ""
                        price = ""
                        isbn = ""
                    }
                }

                XmlPullParser.TEXT -> {
                    when (currentTag) { // Todo： currentTag が常に null だという警告が出るので動作を確認する
                        "title" -> title = parser.text
                        "author" -> author = parser.text
                        "pubDate" -> pubDate = parser.text
                        "publisher" -> if (currentNameSpace == dc) publisher = parser.text
                        "price" -> if (currentNameSpace == dcndl) price = parser.text
                        "identifier" -> if (isDecodeIsbn) {
                            isbn = parser.text
                            isDecodeIsbn = false
                        }
                    }
                }

                XmlPullParser.END_TAG -> {
                    isDecodeIsbn = false

                    if (parser.name == "item") {
                        books.add(ApiBookData(title, author, pubDate, publisher, price, isbn))
                    }

                    currentTag = null
                }
            }
        }
        return ApiBookDataList(books)
    }
}