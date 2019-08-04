 package ir.hco.appian.app.views

 import android.view.ViewManager
 import androidx.cardview.widget.CardView
 import ir.hossainco.utils.view.factory

 fun ViewManager.cardView(
 	theme: Int = 0,
 	init: CardView.() -> Unit = { }
 ) =
 	factory(::CardView, theme = theme, init = init)
