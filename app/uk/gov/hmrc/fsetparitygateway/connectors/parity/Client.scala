package uk.gov.hmrc.fsetparitygateway.connectors.parity

import play.api.libs.json.JsValue
import uk.gov.hmrc.fsetparitygateway.config.{ MicroserviceAppConfig, ParityServiceApiConfig, WSHttp }
import uk.gov.hmrc.fsetparitygateway.connectors.parity.Client.ParityConnectorException
import uk.gov.hmrc.play.http.HeaderCarrier
import scala.concurrent.ExecutionContext.Implicits.global

import scala.concurrent.Future

object Client extends Client(MicroserviceAppConfig.parityApiConfig, WSHttp) {
  case class ParityConnectorException(message: String) extends Exception(message)
}

class Client(appConfig: ParityServiceApiConfig, wsHttp: WSHttp.type) {

  val parityConfig: ParityServiceApiConfig = appConfig
  val fullBasePath = parityConfig.baseUrl + parityConfig.basePath

  implicit val blankHc = new HeaderCarrier()

  def create(jsonBody: JsValue): Future[CreateResponse] = {
    wsHttp.POST(s"$fullBasePath/candidate", jsonBody, Seq("Content-Type" -> "application/json")).map { response =>
      if (!List(200, 201).contains(response.status)) {
        throw ParityConnectorException(s"Non-ok status code returned from parity -> ${response.status} with body: ${response.body}")
      } else {
        CreateResponse(response.status, response.body)
      }
    }
  }

  def update(): UpdateResponse = ???
}
