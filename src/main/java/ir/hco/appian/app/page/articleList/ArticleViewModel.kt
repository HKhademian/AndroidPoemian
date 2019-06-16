package ir.hco.appian.app.page.articleList

import android.app.Application
import ir.hco.appian.app.data.Query
import ir.hco.appian.app.page.BaseViewHolder

internal class ArticleViewModel(app: Application) : BaseViewHolder(app) {
	var query: Query = Query.AllQuery
}
