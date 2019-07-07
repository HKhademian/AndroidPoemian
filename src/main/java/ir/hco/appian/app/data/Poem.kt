package ir.hco.appian.app.data

import ir.hco.appian.app.BuildConfig
import ir.hco.appian.app.data.Article.Companion.readAssets
import ir.hossainco.utils.App
import org.json.JSONObject

data class Poem(
	val id: String,
	val parentId: String,
	override val title: String,
	val path: String,
	val img: String,
	val spath: String
) : Article {
	val bookmark get() = Repository.isBookmark(id)

	inline val url get() = BuildConfig.APP_LINK

	override val content
		get() = readAssets("data/${path.replace("_", "-")}.txt")

	val copyContent
		get() = "$title | ${App.label}\n$url\n\n$content"

	val shareContent
		get() = "$title | ${App.label}\n$url\n\n$content"

	constructor(data: JSONObject) : this(
		id = data.optString("id") ?: "0",
		parentId = data.optString("parentId") ?: "0",
		title = data.optString("title") ?: "",
		path = data.optString("path") ?: "",
		img = data.optString("img") ?: "",
		spath = data.optString("spath") ?: ""
	)

	constructor(data: Poem, id: String) : this(
		id = id,
		parentId = data.parentId,
		title = data.title,
		path = data.path,
		img = data.img,
		spath = data.spath
	)
}
