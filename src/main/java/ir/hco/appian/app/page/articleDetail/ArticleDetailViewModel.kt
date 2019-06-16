package ir.hco.appian.app.page.articleDetail

import android.app.Application
import ir.hco.appian.app.data.Repository
import ir.hco.appian.app.page.BaseViewHolder

internal class ArticleDetailViewModel(app: Application) : BaseViewHolder(app) {
	var articleId: String = Repository.articles.first().id
}
