

import java.util.Properties
import org.apache.kafka.clients.producer.{KafkaProducer, ProducerRecord}
import org.apache.log4j.Logger
import Model.StudentClass

object CustomProducer extends App {

  val log = Logger.getLogger(this.getClass)
  val topic = "studentList"
  val studentList = List("Kritika ", "Neelam", "Bhawna", "Ayush")
  val props = new Properties()

  props.put("bootstrap.servers", "localhost:9092")
  props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer")
  props.put("value.serializer", "CustomSerialization")

  val producer = new KafkaProducer[String,StudentClass](props)

  for (index <- 0 until studentList.size) {
    val key = index.toString
    val value = StudentClass(key, studentList(index))
    val record = new ProducerRecord[String, StudentClass](topic, key, value)
    producer.send(record)
  }
  producer.close()
  log.info("Data successfully send")

}