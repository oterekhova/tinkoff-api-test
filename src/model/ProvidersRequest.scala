package model

import play.api.libs.json.{JsValue, Json}

import scalaj.http.{Http, HttpResponse}

/**
  * Created by home on 16.01.2018.
  */
case class ProvidersRequest(groups: String)  {

  def response: HttpResponse[String] = Http("https://www.tinkoff.ru/api/v1/providers").param("groups", groups).asString


}
