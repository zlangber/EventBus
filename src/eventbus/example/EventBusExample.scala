package eventbus.example

import eventbus.{BasicEventBus, EventHandler}

object EventBusExample {

  class A(message: String) {
    override def toString():String = message
  }

  class B(message: String) extends A(message)

  @EventHandler
  def handleString(e: String) {
    println(s"Handing string event: $e")
  }

  @EventHandler
  def handleA(e: A) {
    println(s"Handling A event: $e")
  }

  @EventHandler
  def handleB(e: B) {
    println(s"Handling B event: $e")
  }

  def main(args: Array[String]) {

    val eventBus = new BasicEventBus()
    eventBus.subscribe(this)
    eventBus.publish("StringEvent")
    eventBus.publish(new A("A Event"))
    eventBus.publish(new B("B Event"))
    eventBus.unsubscribe(this)
    eventBus.publish("StringEvent")
  }
}
