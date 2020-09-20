package ktor.api.com.retrofit

import retrofit2.http.GET

interface ApiService {

    @GET("all_sports.php")
     fun getAllSports() : wrapModel
}