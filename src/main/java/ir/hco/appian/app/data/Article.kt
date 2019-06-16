package ir.hco.appian.app.data

import ir.hossainco.utils.App
import org.json.JSONObject
import java.io.Serializable

data class Article(
	val id: String,
	val parentId: String,
	val title: String,
	val path: String,
	val img: String,
	val spath: String
) : Serializable {
	val text
		get() = App.assets.open("data/${path.replace("_", "-")}.txt").use {
			it.bufferedReader().readText()
		}

	constructor(data: JSONObject) : this(
		id = data.optString("id") ?: "0",
		parentId = data.optString("parentId") ?: "0",
		title = data.optString("title") ?: "",
		path = data.optString("path") ?: "",
		img = data.optString("img") ?: "",
		spath = data.optString("spath") ?: ""
	)
}
