package ir.hco.appian.app.data

import android.content.Context
import androidx.lifecycle.MutableLiveData
import ir.hco.util.BaseApp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import org.jetbrains.anko.defaultSharedPreferences
import org.json.JSONArray
import org.json.JSONObject

object Repository {
	private const val PREF_BOOKMARKS = "bookmarks"
	private const val FONT_SIZE_MUL_MAX = 2f
	private const val FONT_SIZE_MUL_MIN = 0.5f

	private val DEFAULT_SETTINGS = Settings()
	private val DEFAULT_BOOKMARKS = emptySet<String>()

	val settingsLiveData = MutableLiveData<Settings>().apply { value = DEFAULT_SETTINGS }
	val settings get() = settingsLiveData.value ?: DEFAULT_SETTINGS

	val bookmarksLiveData = MutableLiveData<Set<String>>().apply { value = DEFAULT_BOOKMARKS }
	val bookmarks get() = bookmarksLiveData.value ?: DEFAULT_BOOKMARKS

	val cats = mutableListOf<Category>()
	val poems = mutableListOf<Poem>()

	private val context get() = BaseApp.context

	fun init(context: Context) = runBlocking {
		readBookmarks(context)
		// readSettings(context)

	}

	suspend fun load(context: Context) = withContext(Dispatchers.IO) {
		val indexData = context.assets.open("data/index.new.json").use {
			it.bufferedReader().readText()
		}
		val index = JSONObject(indexData)
		val catsData = index.getJSONArray("cats") ?: JSONArray()
		val articlesData = index.getJSONArray("pages") ?: JSONArray()
		// val pathsData = index.getJSONArray("paths")

		cats.clear()
		cats += (0 until catsData.length())
			.asSequence()
			.map { catsData.getJSONObject(it) }
			.map(::Category)

		var i = 0
		poems.clear()
		(0 until articlesData.length())
			.asSequence()
			.map {
				articlesData.getJSONObject(it)
			}
			.map { json ->
				val poem = Poem(json)
				return@map if (poems.any { it.id == poem.id })
					Poem(poem, id = "${poem.id}-${i++}")
				else poem
			}
			.onEach {
				poems+= it
			}
			.count()
	}

	private fun readBookmarks(context: Context) {
		val pref = context.defaultSharedPreferences
		val bookmarks = (pref.getString(PREF_BOOKMARKS, null) ?: "")
			.split("\n")
			.filter { it.isNotBlank() }
			.toSet()
		bookmarksLiveData.postValue(bookmarks)
	}

	private fun setBookmarks(bookmarks: Set<String>) {
		bookmarksLiveData.postValue(bookmarks)

		val pref = context.defaultSharedPreferences
		pref.edit().putString(PREF_BOOKMARKS, bookmarks.joinToString("\n")).apply()
	}

	fun setBookmark(storyId: String, bookmark: Boolean) {
		val bookmarks = bookmarks
		if (bookmark) {
			setBookmarks(
				bookmarks + storyId
			)
		} else {
			setBookmarks(
				bookmarks - storyId
			)
		}
	}

	fun toggleBookmark(storyId: String) {
		val bookmarks = bookmarks
		if (storyId in bookmarks) {
			setBookmarks(
				bookmarks - storyId
			)
		} else {
			setBookmarks(
				bookmarks + storyId
			)
		}
	}

	fun isBookmark(storyId: String): Boolean {
		val bookmarks = bookmarks
		return (storyId in bookmarks)
	}

	fun getPoemById(poemId: String): Poem {
		return poems.first { it.id == poemId }
	}

}
