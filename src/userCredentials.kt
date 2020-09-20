package ktor.api.com

import com.sun.xml.internal.ws.developer.Serialization


data class userCredentials
    (
    var HomeTeam : String ,
    var AwayTeam : String ,
    var HomeLogo : String ,
    var AwayLogo : String ,
    var subModel: subModel
)