package ir.hco.appian.app.data

import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import ir.hossainco.utils.App
import ir.hossainco.utils.texts.toPersianLetter
import ir.hossainco.utils.tryMap
import org.jetbrains.anko.defaultSharedPreferences
import kotlin.math.max
import kotlin.math.min

object Repository {
	private const val PREF_BOOKMARKS = "bookmarks"

	private const val FONT_SIZE_MUL_MAX = 2f
	private const val FONT_SIZE_MUL_MIN = 0.5f

	private val DEFAULT_SETTINGS = Settings()
	private val DEFAULT_BOOKMARKS = emptySet<Int>()

	val settingsLiveData = MutableLiveData<Settings>().apply { value = DEFAULT_SETTINGS }
	val settings get() = settingsLiveData.value ?: DEFAULT_SETTINGS

	val bookmarksLiveData = MutableLiveData<Set<Int>>().apply { value = DEFAULT_BOOKMARKS }
	val bookmarks get() = bookmarksLiveData.value ?: DEFAULT_BOOKMARKS

	fun init() {
		readBookmarks()
//		readSettings()
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
			.split(",")
			.tryMap(String::toInt)
			.filterNotNull()
			.toSet()
		bookmarksLiveData.postValue(bookmarks)
	}

	private fun setBookmarks(bookmarks: Set<Int>) {
		bookmarksLiveData.postValue(bookmarks)

		val pref = App.app.defaultSharedPreferences
		pref.edit().putString(PREF_BOOKMARKS, bookmarks.joinToString(",")).apply()
	}

	fun setBookmark(storyId: Int, bookmark: Boolean) {
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

	fun toggleBookmark(storyId: Int) {
		val bookmarks = bookmarks
		if (bookmarks.contains(storyId)) {
			setBookmarks(
				bookmarks - storyId
			)
		} else {
			setBookmarks(
				bookmarks + storyId
			)
		}
	}

	fun isBookmark(storyId: Int): Boolean {
		val bookmarks = bookmarks
		return bookmarks.contains(storyId)
	}
}
