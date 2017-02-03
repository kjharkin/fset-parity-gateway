package uk.gov.hmrc.fsetparitygateway.controllers

import play.api.Logger
import uk.gov.hmrc.play.microservice.controller.BaseController
import play.api.mvc._
import uk.gov.hmrc.fsetparitygateway.connectors.parity.Client

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

object ApplicationController extends ApplicationController(Client)

class ApplicationController(parityClient: Client) extends BaseController {

  def create(): Action[AnyContent] = Action.async { implicit request =>
    request.body.asJson.map { jsonBody =>
      parityClient.create(jsonBody).flatMap { _ =>
        Future.successful(Ok("Application sent"))
      }
    }.getOrElse(Future.successful(BadRequest("Invalid Json in request body")))
  }

  def update(): Action[AnyContent] = Action.async { implicit request =>
    request.body.asJson.map { jsonBody =>
      parityClient.update(jsonBody).flatMap { _ =>
        Future.successful(Ok("Application update sent"))
      }
    }.getOrElse(Future.successful(BadRequest("Invalid Json in request body")))
  }

  def helloWorld(): Action[AnyContent] = Action.async { implicit request =>
    Future.successful(Ok("Hello world"))
  }
}
