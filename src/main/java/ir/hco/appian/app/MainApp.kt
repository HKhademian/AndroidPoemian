package ir.hco.appian.app

import ir.hco.appian.BaseApp
import ir.hco.appian.app.data.Repository

abstract class MainApp : BaseApp() {
	override fun initRepository() {
		super.initRepository()
		Repository.init()
	}
}
