package ir.hco.appian.app.data

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import ir.hossainco.utils.App
import org.jetbrains.anko.defaultSharedPreferences
import org.json.JSONArray
import org.json.JSONObject
import kotlin.math.max
import kotlin.math.min

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

	fun init() {
		readBookmarks()
		// readSettings()

		val indexData = App.app.assets.open("data/index.new.json").use {
			it.bufferedReader().readText()
		}
		val index = JSONObject(indexData)
		val catsData = index.getJSONArray("cats") ?: JSONArray()
		val articlesData = index.getJSONArray("pages") ?: JSONArray()
		// val pathsData = index.getJSONArray("paths")

		cats.addAll((0 until catsData.length())
			.asSequence()
			.map { catsData.getJSONObject(it) }
			.map(::Category)
			.onEach {
				//Log.e("data.cat", it.toString())
			})

		var i = 0
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
				poems.add(it)
				// Log.e("data.poem", it.toString())
			}
			.count()
	}

	fun observeSettings(owner: Fragment, observer: (Settings) -> Unit) {
		Repository.settingsLiveData.observe(owner, Observer {
			observer(it ?: DEFAULT_SETTINGS)
		})
	}

	fun fontSizeMultiplierUp(): Boolean {
		val current = settings
		val currentVal = current.fontSizeMultiplier
		val newVal = min(FONT_SIZE_MUL_MAX, currentVal + 0.1f)

		if (currentVal == newVal)
			return false

		settingsLiveData.postValue(
			current.copy(
				fontSizeMultiplier = newVal
			)
		)
		return true
	}

	fun fontSizeMultiplierDown(): Boolean {
		val current = settings
		val currentVal = current.fontSizeMultiplier
		val newVal = max(FONT_SIZE_MUL_MIN, currentVal - 0.1f)

		if (currentVal == newVal)
			return false

		settingsLiveData.postValue(
			current.copy(
				fontSizeMultiplier = newVal
			)
		)
		return true
	}


	fun darkMode(mode: Boolean = true): Boolean {
		val current = settings
		val currentVal = current.darkMode

		if (currentVal == mode)
			return false

		settingsLiveData.postValue(
			current.copy(
				darkMode = mode
			)
		)
		return true
	}

	fun toggleDarkMode(): Boolean {
		val current = settings
		val currentVal = current.darkMode

		settingsLiveData.postValue(
			current.copy(
				darkMode = !currentVal
			)
		)
		return true
	}

	private fun readBookmarks() {
		val pref = App.app.defaultSharedPreferences
		val bookmarks = (pref.getString(PREF_BOOKMARKS, null) ?: "")
			.split("\n")
			.filter { it.isNotBlank() }
			.toSet()
		bookmarksLiveData.postValue(bookmarks)
	}

	private fun setBookmarks(bookmarks: Set<String>) {
		bookmarksLiveData.postValue(bookmarks)

		val pref = App.app.defaultSharedPreferences
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
