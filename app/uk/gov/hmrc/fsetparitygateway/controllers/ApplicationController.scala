package uk.gov.hmrc.fsetparitygateway.controllers

import uk.gov.hmrc.play.microservice.controller.BaseController
import play.api.mvc._
import scala.concurrent.Future

object ApplicationController extends ApplicationController

trait ApplicationController extends BaseController {

  def create(): Action[AnyContent] = Action.async { implicit request =>
    Future.successful(Ok("Hello world"))
  }

  def update(): Action[AnyContent] = Action.async { implicit request =>
    Future.successful(Ok("Hello world"))
  }

  def helloWorld(): Action[AnyContent] = Action.async { implicit request =>
    Future.successful(Ok("Hello world"))
  }
}
