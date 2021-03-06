package uk.gov.hmrc.fsetparitygateway.controllers

import play.api.http.Status
import play.api.test.FakeRequest
import uk.gov.hmrc.play.test.{ UnitSpec, WithFakeApplication }

class ApplicationControllerSpec extends UnitSpec with WithFakeApplication {

  val fakeRequest = FakeRequest("GET", "/")

  "GET /" should {
    "return 200" in {
      val result = ApplicationController.helloWorld()(fakeRequest)
      status(result) shouldBe Status.OK
    }
  }

}
