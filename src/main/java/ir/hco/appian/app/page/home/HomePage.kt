package ir.hco.appian.app.page.home

import android.os.Bundle
import android.view.*
import androidx.fragment.app.transaction
import androidx.lifecycle.ViewModelProviders
import ir.hco.appian.app.R
import ir.hco.appian.app.data.Category
import ir.hco.appian.app.data.Query
import ir.hco.appian.app.page.BasePage
import ir.hco.appian.app.page.articleDetail.ArticleDetailPage
import ir.hco.appian.app.page.articleList.ArticleListPage
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.toast

class HomePage : BasePage() {
	private val viewModel by lazy {
		ViewModelProviders.of(this)[HomeViewModel::class.java]
	}

	override val title: String?
		get() = context!!.getString(R.string.app_subtitle)

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
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
}
