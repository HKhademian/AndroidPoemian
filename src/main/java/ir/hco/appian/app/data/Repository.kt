package ir.hco.appian.app.data

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import ir.hossainco.utils.App
import org.json.JSONArray
import org.json.JSONObject
import kotlin.math.max
import kotlin.math.min

object Repository {
	private const val FONT_SIZE_MUL_MAX = 2f
	private const val FONT_SIZE_MUL_MIN = 0.5f

	private val DEFAULT_SETTINGS = Settings()

	val settingsLiveData = MutableLiveData<Settings>().apply { value = DEFAULT_SETTINGS }
	val settings get() = settingsLiveData.value ?: DEFAULT_SETTINGS

	val cats = mutableListOf<Category>()
	val articles = mutableListOf<Article>()
	// val paths = mutableListOf<String>()

	fun init() {
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
				Log.e("data.cat", it.toString())
			})

		articles.addAll((0 until articlesData.length())
			.asSequence()
			.map { articlesData.getJSONObject(it) }
			.map(::Article)
			.onEach {
				Log.e("data.article", it.toString())
			})
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

}
