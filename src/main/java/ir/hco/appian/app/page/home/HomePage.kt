@file:Suppress("UNUSED_PARAMETER")

package ir.hco.appian.app.page.home

import android.content.Intent.createChooser
import android.os.Bundle
import android.view.*
import androidx.fragment.app.transaction
import androidx.lifecycle.ViewModelProviders
import ir.hco.appian.app.R
import ir.hco.appian.app.data.Article
import ir.hco.appian.app.data.Category
import ir.hco.appian.app.data.Query
import ir.hco.appian.app.page.BasePage
import ir.hco.appian.app.page.articleDetail.ArticleDetailPage
import ir.hco.appian.app.page.poemList.ArticleListPage
import ir.hco.util.BaseApp
import ir.hossainco.utils.arch.liveDatas.mutableLiveData
import ir.hossainco.utils.packages.startNewTask
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.toast

class HomePage : BasePage() {
	companion object {
		private const val EXTRA_EXPANDED_ITEM_IDS = "expandedItemIds"
	}

	private val viewModel by lazy {
		ViewModelProviders.of(this)[HomeViewModel::class.java]
	}

	override val title: String?
		get() = context!!.getString(R.string.app_subtitle)

	val expandedItemIds = mutableLiveData(emptySet<String>())

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		if (savedInstanceState != null) {

			@Suppress("UNCHECKED_CAST")
			expandedItemIds.value =
				savedInstanceState.getSerializable(EXTRA_EXPANDED_ITEM_IDS) as? HashSet<String> ?: emptySet()
		}
		setHasOptionsMenu(true)
	}

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
		val context = context!!
		return HomeUI().createView(AnkoContext.create(context, this, false))
	}

	override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
		super.onCreateOptionsMenu(menu, inflater)
		inflater.inflate(R.menu.menu_home, menu)
	}

	override fun onOptionsItemSelected(item: MenuItem?): Boolean {
		return when (item?.itemId) {
			R.id.item_apps -> {
				onClickOtherApps(null)
				true
			}

			R.id.item_rate -> {
				onClickRate(null)
				true
			}

			else -> super.onOptionsItemSelected(item)
		}
	}

	override fun onBackKeyPressed(): Boolean {
		return if (expandedItemIds.value!!.isNotEmpty()) {
			expandedItemIds.value = emptySet()
			true
		} else false
	}

	override fun onSaveInstanceState(outState: Bundle) {
		super.onSaveInstanceState(outState)
		outState.putSerializable(EXTRA_EXPANDED_ITEM_IDS, expandedItemIds.value!!.toHashSet())
	}

	fun onItemExpand(view: View, category: Category, isExpanded: Boolean) {
		val ids = expandedItemIds.value!!.toHashSet()
		if (isExpanded) ids.add(category.id)
		else ids.remove(category.id)
		expandedItemIds.value = ids
	}


	fun onClickCategory(it: Category) {
		val query = Query.CategoryQuery(it.id)

		when {
			query.itemCount <= 0 -> context!!.toast("موردی یافت نشد")
			query.itemCount <= 1 -> {
				val articleId = query.itemIds.first()
				fragmentManager?.transaction {
					replace(R.id.fragment, ArticleDetailPage(articleId))
					addToBackStack("articleDetail")
				}
			}
			else -> fragmentManager?.transaction {
				replace(R.id.fragment, ArticleListPage(query))
				addToBackStack("articleList")
			}
		}
	}

	fun onClickOtherApps(it: View?) {
		val baseApp = context?.applicationContext as? BaseApp ?: return
		createChooser(
			baseApp.publisher.createDeveloperPageIntent(baseApp),
			baseApp.getString(R.string.title_chooser)
		).startNewTask(baseApp)
	}

	fun onClickRate(it: View?) {
		val baseApp = context?.applicationContext as? BaseApp ?: return
		createChooser(
			baseApp.publisher.createAppPageIntent(baseApp),
			baseApp.getString(R.string.title_chooser)
		).startNewTask(baseApp)
	}

	fun onClickBookmarks(it: View?) {
		fragmentManager?.transaction {
			replace(R.id.fragment, ArticleListPage(Query.BookmarkQuery))
			addToBackStack("bookmarks")
		}
	}

	fun onClickSettings(it: View?) {
		context!!.toast(R.string.alert_not_implemented)
	}

	fun onClickAbout(it: View?) {
		context!!.toast(R.string.alert_not_implemented)
	}

	fun onClickReferences(it: View?) {
		fragmentManager?.transaction {
			replace(R.id.fragment, ArticleDetailPage(Article.ReferencesArticle))
			addToBackStack("references")
		}
	}
}
