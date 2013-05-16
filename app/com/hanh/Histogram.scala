package com.hanh

import play.api.libs.json._
import play.api.libs.functional.syntax._

case class Bar(x: Double, y: Int)
case class Histogram(name: String, bars: Array[Bar])

object Bar {
  implicit val writer = new Writes[Bar] {
    def writes(b: Bar): JsValue = {
      Json.obj(
        "x" -> b.x,    
        "y" -> b.y    
      )
    }
  }
}

object Histogram {
  implicit val writer = new Writes[Histogram] {
    def writes(h: Histogram): JsValue = {
      Json.obj(
        "name" -> h.name,
        "bars" -> h.bars    
      )
    }
  }
  
  def get(symbol: String) = {
    val sigma = 30
    val mean = 30 + symbol.hashCode() % 20
    def f(x: Double): Double = Math.exp(-(x-mean)*(x-mean)/(2*sigma*sigma)) / (sigma*Math.sqrt(2*Math.PI)) * 1000
    
    val y = for (i <- 0 until 100)
      yield Bar(i.toDouble, f(i).toInt)
    
    Histogram(symbol, y.toArray)
  }
}