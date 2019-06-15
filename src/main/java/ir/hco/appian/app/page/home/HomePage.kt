package ir.hco.appian.app.page.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import ir.hco.appian.app.page.BasePage
import org.jetbrains.anko.AnkoContext

class HomePage : BasePage() {
	private val viewModel by lazy {
		ViewModelProviders.of(this)[HomeViewModel::class.java]
	}

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
		val context = context!!
		return HomeUI().createView(AnkoContext.create(context, this, false))
	}
}
