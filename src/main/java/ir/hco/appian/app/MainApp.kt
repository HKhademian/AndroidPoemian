package ir.hco.appian.app

import ir.hco.appian.app.data.Repository
import ir.hco.firebase.FirebaseLogger
import ir.hco.pushe.PusheMessagingHandler
import ir.hco.util.BaseApp

open class MainApp : BaseApp() {
	override val logger = FirebaseLogger

	override fun initRepository() {
		super.initRepository()
		Repository.init(applicationContext)
	}

	override fun initMessaging() {
		super.initMessaging()
		PusheMessagingHandler.init(applicationContext, true)
	}

	suspend fun load() {
		Repository.load(applicationContext)
	}
}
