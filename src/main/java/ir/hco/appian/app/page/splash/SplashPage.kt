package ir.hco.appian.app.page.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.transaction
import androidx.lifecycle.ViewModelProviders
import ir.hco.appian.app.R
import ir.hco.appian.app.page.BasePage
import ir.hco.appian.app.page.home.HomePage
import ir.hossainco.utils.event.observeEvent
import org.jetbrains.anko.AnkoContext

class SplashPage : BasePage() {
	override val hasToolbar = false
	override val hasAds = false

	private val viewModel by lazy {
		ViewModelProviders.of(this)[SplashViewModel::class.java]
	}

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
		val context = context!!
		return SplashUI().createView(AnkoContext.create(context, this, false))
	}

	override fun onStart() {
		super.onStart()
		viewModel.loadCompleteEvent.observeEvent(viewLifecycleOwner) { result ->
			if (result) {
				fragmentManager?.transaction {
					replace(R.id.fragment, HomePage(), "home")
				}
			}
		}

		viewModel.start()
	}
}
