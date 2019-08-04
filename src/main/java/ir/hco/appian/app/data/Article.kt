package ir.hco.appian.app.data

import ir.hco.appian.app.R
import ir.hco.util.BaseApp
import java.io.Serializable

interface Article : Serializable {
	val title: String
	val content: String

	companion object {
		fun readAssets(path: String) =
			BaseApp.assets.open(path).use {
				it.bufferedReader().readText()
			}
	}

	object ReferencesArticle : Article {
		override val title =
			BaseApp.res.getString(R.string.item_references) ?: ""

		override val content
			get() = readAssets("(انتشار محتوا با ذکر منبع آن آزاد است)\n\nکوکا\nگود ریدز\nپارسی نو\n(dashmili-5) بلاگفا\nستاره\nآرگامگ\n")
	}
}
