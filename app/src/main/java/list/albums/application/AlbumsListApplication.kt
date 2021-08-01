package list.albums.application

import android.app.Application

class AlbumsListApplication : Application() {

    companion object {
        private lateinit var instance: AlbumsListApplication

        fun getInstance(): AlbumsListApplication {
            return instance
        }
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}