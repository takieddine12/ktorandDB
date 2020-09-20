package ktor.api.com

import com.fasterxml.jackson.databind.SerializationFeature
import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.features.CORS
import io.ktor.features.CallLogging
import io.ktor.features.ContentNegotiation
import io.ktor.features.DefaultHeaders
import io.ktor.jackson.jackson
import io.ktor.response.respond
import io.ktor.routing.Route
import io.ktor.routing.get
import io.ktor.routing.routing
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ktor.api.com.retrofit.UserService
import sun.util.logging.PlatformLogger
import java.util.*

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@kotlin.jvm.JvmOverloads
fun Application.module(testing : Boolean = true) {
   // val client: CoroutineClient by inject()
    val port = System.getenv("PORT")?.toInt() ?: 23567


    embeddedServer(Netty,port){
        DatabaseFactory

        install(DefaultHeaders)
        install(CallLogging){
            PlatformLogger.Level.INFO
        }
        install(CORS){
            anyHost()
        }
        install(ContentNegotiation){
            jackson {
                enable(SerializationFeature.INDENT_OUTPUT)
            }
        }
        routing {
            get("/"){
                call.respond(UserService().getAllUsers())
            }
//            get("/randomgames"){
//
//                val submodel = subModel("Active", Calendar.getInstance().time.toString())
//                val game1  = userCredentials("Los Angelos","California","https://cdn.pixabay.com/photo/2017/08/05/11/16/logo-2582748_1280.png","https://cdn.pixabay.com/photo/2017/08/05/11/16/logo-2582748_1280.png",submodel)
//                var game2: userCredentials = userCredentials("Texas","Arizona","https://cdn.pixabay.com/photo/2017/08/05/11/16/logo-2582748_1280.png","https://cdn.pixabay.com/photo/2017/08/05/11/16/logo-2582748_1280.png",submodel)
//                var game3  = userCredentials("New York","Virginia","https://cdn.pixabay.com/photo/2017/08/05/11/16/logo-2582748_1280.png","https://cdn.pixabay.com/photo/2017/08/05/11/16/logo-2582748_1280.png",submodel)
//                var gamesList = arrayListOf<userCredentials>()
//                gamesList.add(game1)
//                gamesList.add(game2)
//                gamesList.add(game3)
//
//                //call.respond(gamesList)
//
//
//            }
        }
    }.start(wait = true)

}





