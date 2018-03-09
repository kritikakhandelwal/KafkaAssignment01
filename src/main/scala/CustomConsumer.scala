import java.util.Properties

import Model.StudentClass
import org.apache.kafka.clients.consumer.KafkaConsumer
import org.apache.log4j.Logger

import scala.collection.JavaConverters._
import Model.StudentClass

object CustomConsumer extends App {

  val log = Logger.getLogger(this.getClass)
  val topic = "studentList"
  val props = new Properties()
  props.put("bootstrap.servers", "localhost:9092")
  props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")
  props.put("value.deserializer", "CustomDeserialization")
  props.put("group.id", "studentList")
  props.put("enable.auto.commit", "false")

  val consumer = new KafkaConsumer[String, StudentClass](props)

  consumer.subscribe(java.util.Collections.singletonList(topic))
  while (true) {
    val records = consumer.poll(5000)
    for (record <- records.asScala)
      log.info(s"Student Information:${record.value()}")
  }

}