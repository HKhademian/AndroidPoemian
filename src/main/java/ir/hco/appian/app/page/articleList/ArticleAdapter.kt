package ir.hco.appian.app.page.articleList

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ir.hco.appian.app.data.Repository
import org.jetbrains.anko.AnkoContext

internal class ArticleAdapter(
	internal val page: ArticleListPage,
	val itemIds: List<String> = Repository.articles.map { it.id }
) : RecyclerView.Adapter<ArticleItemViewHolder>() {

	override fun getItemCount() =
		itemIds.size

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
		ArticleItemViewHolder(
			itemView = ArticleItemUI().createView(AnkoContext.create(parent.context, this, false))
		) { pos ->
			page.onArticleClick(itemIds[pos])
		}

	override fun onBindViewHolder(holder: ArticleItemViewHolder, position: Int) =
		holder.bind(position, itemIds[position])

}
