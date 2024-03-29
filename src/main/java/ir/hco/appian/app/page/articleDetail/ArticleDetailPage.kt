package ir.hco.appian.app.page.articleDetail

import android.content.ClipData
import android.content.Intent.createChooser
import android.os.Bundle
import android.view.*
import android.widget.ImageView
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import ir.hco.appian.app.R
import ir.hco.appian.app.data.Article
import ir.hco.appian.app.data.Poem
import ir.hco.appian.app.data.Repository
import ir.hco.appian.app.page.BasePage
import ir.hco.util.BaseApp
import ir.hco.util.views.SimpleToolbar
import ir.hossainco.utils.packages.createShareTextIntent
import ir.hossainco.utils.packages.startNewTask
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.clipboardManager
import org.jetbrains.anko.toast

class ArticleDetailPage() : BasePage() {
	companion object {
		const val ARG_ARTICLE = "article"
		const val ARG_POEM_ID = "poemId"
	}

	constructor(poemId: String) : this() {
		arguments = bundleOf(ARG_POEM_ID to poemId)
	}

	constructor(article: Article) : this() {
		arguments = bundleOf(ARG_ARTICLE to article)
	}

	private val viewModel by lazy {
		ViewModelProviders.of(this)[ArticleDetailViewModel::class.java]
	}

	override val title: String
		get() {
			val article = viewModel.article.value ?: return "NULL"
			if (article !is Poem) return article.title
			val category = Repository.cats.firstOrNull { it.id == article.parentId } ?: Repository.cats.first()
			return "${category.title} » ${article.title}"
		}

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		val article =
			arguments?.getSerializable(ARG_ARTICLE) as? Article ?: Repository.getPoemById(
				arguments?.getString(ARG_POEM_ID) ?: return
			)

		viewModel.setArticle(article)
		setHasOptionsMenu(article is Poem)
	}

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
		val context = context!!
		return ArticleDetailUI().createView(AnkoContext.create(context, this, false)).also { view ->
			val textView = view.findViewById<TextView>(R.id.text)
			val article = viewModel.article.value ?: return@also
			textView.text = article.content
		}
	}

	override fun onStart() {
		super.onStart()
		val activity = activity ?: return
		(activity.applicationContext as? BaseApp)?.advertiser?.mayShowFull(activity)

	}

	override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
		super.onCreateOptionsMenu(menu, inflater)

		if (viewModel.article.value !is Poem) return
		inflater.inflate(R.menu.menu_article_detail, menu)
	}

	override fun onOptionsItemSelected(item: MenuItem?): Boolean {
		val context = context!!
		val poem = viewModel.article.value as? Poem ?: return super.onOptionsItemSelected(item)

		return when (item?.itemId) {
			R.id.item_share -> {
				createChooser(
					createShareTextIntent(poem.shareContent),
					context.getString(R.string.title_share_chooser)
				).startNewTask(context)
				true
			}

			R.id.item_copy -> {
				val clipboard = context.clipboardManager
				clipboard.primaryClip =
					ClipData.newPlainText(context.getString(context.applicationInfo.labelRes), poem.copyContent)

				context.toast(R.string.action_copy_done)
				true
			}

			R.id.item_bookmark -> {
				Repository.toggleBookmark(poem.id)
				true
			}

			else -> super.onOptionsItemSelected(item)
		}
	}

	override fun onOptionsMenuCreated(menu: Menu) {
		super.onOptionsMenuCreated(menu)
		val activity = activity ?: return
		val toolbar = activity.findViewById<SimpleToolbar>(R.id.toolbar) ?: return

		val poem = viewModel.article.value as? Poem ?: return

		Repository.bookmarksLiveData.observe(this, Observer {
			val bookmark = poem.bookmark

			toolbar.findViewById<ImageView>(R.id.item_bookmark)?.setImageResource(
				if (bookmark) R.drawable.ic_bookmark_on else R.drawable.ic_bookmark_off
			)
		})

	}
}
