package ir.hco.appian.app.data

import org.json.JSONObject
import java.io.Serializable

data class Category(
	val id: String,
	val parentId: String,
	val title: String
) : Serializable {
	constructor(data: JSONObject) : this(
		id = data.optString("id") ?: "0",
		parentId = data.optString("parentId") ?: "0",
		title = data.optString("title") ?: ""
	)
}
