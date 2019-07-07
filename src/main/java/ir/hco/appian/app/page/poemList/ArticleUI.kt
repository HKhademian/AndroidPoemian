package ir.hco.appian.app.page.poemList

import androidx.recyclerview.widget.RecyclerView
import ir.hco.appian.app.R
import ir.hossainco.utils.view.factory
import org.jetbrains.anko.AnkoComponent
import org.jetbrains.anko.AnkoContext

internal class ArticleUI : AnkoComponent<ArticleListPage> {
	override fun createView(ui: AnkoContext<ArticleListPage>) = with(ui) {
		factory(::RecyclerView) {
			id = R.id.recycler
		}
	}
}
