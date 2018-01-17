package test

import java.lang._
import java.util
import java.util._

import model.{ProvidersModel, ProvidersRequest}
import org.hamcrest.CoreMatchers._
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import org.junit.runners.Parameterized.Parameters
import play.api.libs.json.{JsArray, JsValue}

/**
  * Created by home on 16.01.2018.
  */

@RunWith(value = classOf[Parameterized])
class ProvidersTest(groupName: String) {

  @Test
  def groupProvidersTests {
    val model = new ProvidersModel(ProvidersRequest(groups = groupName))
    model.fire();
    println(s"$model")

    //HTTP код состояния соответствует успеху
    assertThat(model.globResult, equalTo(model.successCode))
    //Возвращается документ в формате json
    assertThat(model.responseModel.isInstanceOf[JsValue], is(true))
    //Значение resultCode равно OK
    assertThat((model.responseModel \ "resultCode").asOpt[String].get, equalTo("OK"))
    //Все значения groupId равны параметру <GROUP_NAME>
    (model.responseModel \\ "groupId").foreach(x => {
      assertThat(x.as[String], equalTo(groupName))
    })
    //Для каждого id равного lastName, параметр name содержит подстроку Фамилия
    (model.responseModel \\ "providerFields").foreach(x => {
      x.as[JsArray].value.foreach(y => {if((y \ "id").asOpt[String].contains("lastName")){
        assertThat((y \ "name").asOpt[String].getOrElse("None").contains("Фамилия"), is(true))
      }})
    })
  }
}


object ProvidersTest {

  @Parameters
  def groups(): ArrayList[String] = {
    val list = new ArrayList[String]()
    list.add("Переводы")
    list.add("Интернет")
    list.add("Благотворительность")
    list
  }

}