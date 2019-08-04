package ir.hco.appian.app.page.articleDetail

import android.app.Application
import androidx.lifecycle.MutableLiveData
import ir.hco.appian.app.data.Article
import ir.hco.util.page.BaseViewModel
import ir.hossainco.utils.arch.liveDatas.hiddenMutableLiveData

internal class ArticleDetailViewModel(app: Application) : BaseViewModel(app) {
	var article = hiddenMutableLiveData<Article>()

	fun setArticle(article: Article) {
		(this.article as MutableLiveData).value = article
	}
}
