package ir.hco.appian.app.page.poemList

import android.app.Application
import ir.hco.appian.app.data.Query
import ir.hco.appian.page.BaseViewHolder

internal class ArticleViewModel(app: Application) : BaseViewHolder(app) {
	var query: Query = Query.AllQuery
}
