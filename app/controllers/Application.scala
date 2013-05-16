package controllers

import play.api._
import play.api.mvc._
import play.api.libs.json
import play.api.libs.json.Json
import com.hanh.Histogram

object Application extends Controller {
  val symbols = List("HSBC", "CHINA")
  def index = Action {
    Redirect(routes.Application.graph())
  }
  
  def graph = Action {
    Ok(views.html.index(symbols))
  }

  def graphJSON = Action { implicit request =>
    val symbol = request.queryString("symbol").head
    Ok(Json.toJson(Histogram.get(symbol)))
  }
}