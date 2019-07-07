package ir.hco.appian.app.data

import ir.hco.appian.app.R
import ir.hossainco.utils.App
import java.io.Serializable

interface Article : Serializable {
	val title: String
	val content: String

	companion object {
		fun readAssets(path: String) =
			App.assets.open(path).use {
				it.bufferedReader().readText()
			}
	}

	object ReferencesArticle : Article {
		override val title =
			App.res.getString(R.string.item_references) ?: ""

		override val content
			get() = readAssets("data/references.txt")
	}
}
