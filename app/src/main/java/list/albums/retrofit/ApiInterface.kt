package list.albums.retrofit

import list.albums.model.Album
import retrofit2.Call
import retrofit2.http.GET

/**
 * REST API end points
 */
interface ApiInterface {

    /**
     * Get albums list
     */
    @GET("albums")
    fun getAlbumsList(): Call<List<Album>>

}