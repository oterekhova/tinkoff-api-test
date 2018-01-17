package model

import play.api.libs.json.{JsValue, Json}

import scalaj.http.{Http, HttpResponse}

/**
  * Created by home on 16.01.2018.
  */
class ProvidersModel(providersRequest: ProvidersRequest) {

  var globResult: Int = 0
  var responseModel: JsValue = null

  def successCode: Int = 200

  def fire(): Unit = {
    val response = providersRequest.response
    globResult = response.code
    responseModel = Json.parse(response.body)
  }

  override def toString: String =
    s"response.code: $globResult \nresponse.body: $responseModel"
}

