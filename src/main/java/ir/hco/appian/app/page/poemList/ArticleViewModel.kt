package ir.hco.appian.app.page.poemList

import android.app.Application
import ir.hco.appian.app.data.Query
import ir.hco.util.page.BaseViewModel

internal class ArticleViewModel(app: Application) : BaseViewModel(app) {
	var query: Query = Query.AllQuery
}
