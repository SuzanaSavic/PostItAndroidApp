package suzanasavic.github.com.android.postitapp.data.network

import com.google.gson.GsonBuilder
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import suzanasavic.github.com.android.postitapp.App
import suzanasavic.github.com.android.postitapp.Constants.Companion.POSTS
import suzanasavic.github.com.android.postitapp.data.entities.Post

/**
 * Created by suzana.savic on 11/17/2020.
 */
interface Api {

    @GET(POSTS)
    suspend fun getAllPosts() : Response<ArrayList<Post>>

    companion object {
        operator fun invoke(): Api {
            return Retrofit.Builder()
                .client(App.instance.client)
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(App.instance.mainUrl)
                .build()
                .create(Api::class.java)
        }
    }

}