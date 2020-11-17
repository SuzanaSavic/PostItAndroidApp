package suzanasavic.github.com.android.postitapp

import android.app.Application
import okhttp3.OkHttpClient
import suzanasavic.github.com.android.postitapp.Constants.Companion.MAIN_URL
import java.util.concurrent.TimeUnit

/**
 * Created by suzana.savic on 11/17/2020.
 */
class App : Application() {

    lateinit var mainUrl: String
    lateinit var client : OkHttpClient

    override fun onCreate() {
        super.onCreate()
        instance = this
        mainUrl = MAIN_URL
        client = OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
            .build()
    }


    companion object {
        lateinit var instance: App
            private set
    }
}