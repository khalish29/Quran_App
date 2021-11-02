package learn.idn.quranapp.network

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

class Config {

    private fun retrofit():Retrofit{
        return Retrofit.Builder().baseUrl("https://api.alquran.cloud/v1/surah/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun service(): GetServices = retrofit().create(GetServices::class.java)

}

interface GetServices {
    @GET("{nomor}")
    fun listRepos(@Path("nomor")nomor: String?): Call<AlquranModel>
}