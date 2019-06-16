package ir.hco.appian.app.page.articleDetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProviders
import ir.hco.appian.app.R
import ir.hco.appian.app.data.Repository
import ir.hco.appian.app.page.BasePage
import org.jetbrains.anko.AnkoContext

class ArticleDetailPage() : BasePage() {
	companion object {
		const val ARG_ARTICLE_ID = "articleId"
	}

	constructor(articleId: String) : this() {
		arguments = bundleOf(ARG_ARTICLE_ID to articleId)
	}

	private val viewModel by lazy {
		ViewModelProviders.of(this)[ArticleDetailViewModel::class.java]
	}

	override val title: String
		get() {
			val article = Repository.articles.firstOrNull { it.id == viewModel.articleId } ?: Repository.articles.first()
			val category = Repository.cats.firstOrNull { it.id == article.parentId } ?: Repository.cats.first()
			return "${category.title} » ${article.title}"
		}

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		viewModel.articleId = arguments?.getString(ARG_ARTICLE_ID) ?: Repository.articles.first().id
	}

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
		val context = context!!
		return ArticleDetailUI().createView(AnkoContext.create(context, this, false)).also { view ->
//			val titleView = view.findViewById<TextView>(R.id.title)
//			val subtitleView = view.findViewById<TextView>(R.id.subtitle)
			val textView = view.findViewById<TextView>(R.id.text)

			val article = Repository.articles.firstOrNull { it.id == viewModel.articleId } ?: Repository.articles.first()
			val category = Repository.cats.firstOrNull { it.id == article.parentId } ?: Repository.cats.first()

//			titleView.text = article.title
//			subtitleView.text = category.title
			textView.text = article.text
		}
	}
}
