package ir.hco.appian.app

import ir.hco.appian.app.data.Repository
import ir.hco.util.BaseApp

open class MainApp : BaseApp() {
	override fun initRepository() {
		super.initRepository()
		Repository.init(applicationContext)
	}
}
