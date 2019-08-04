package ir.hco.appian.app.page.poemList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.transaction
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ir.hco.appian.app.R
import ir.hco.appian.app.data.Query
import ir.hco.appian.app.page.BasePage
import ir.hco.appian.app.page.articleDetail.ArticleDetailPage
import org.jetbrains.anko.AnkoContext
import java.io.Serializable

class ArticleListPage() : BasePage() {
	companion object {
		const val EXTRA_QUERY = "query"
	}

	constructor(query: Query) : this() {
		arguments = bundleOf(EXTRA_QUERY to query as Serializable)
	}

	private lateinit var adapter: ArticleAdapter
	private val viewModel by lazy {
		ViewModelProviders.of(this)[ArticleViewModel::class.java]
	}

	override val title get() = viewModel.query.title

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		viewModel.query = (arguments?.getSerializable(EXTRA_QUERY) as? Query) ?: Query.AllQuery
		adapter = ArticleAdapter(this, itemIds = viewModel.query.itemIds)
	}

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
		val context = context!!
		return ArticleUI().createView(AnkoContext.create(context, this, false))
			.also { recycler ->
				recycler.adapter = adapter
				recycler.addItemDecoration(DividerItemDecoration(context, RecyclerView.VERTICAL))
				recycler.layoutManager = LinearLayoutManager(context).apply {
					orientation = RecyclerView.VERTICAL
				}
			}
	}

	fun onArticleClick(articleId: String) {
		fragmentManager?.transaction {
			replace(R.id.fragment, ArticleDetailPage(articleId))
			addToBackStack("articleDetail")
		}
	}
}
