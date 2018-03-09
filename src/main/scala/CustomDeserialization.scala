

import java.io.{ByteArrayInputStream, ObjectInputStream}
import java.util

import Model.StudentClass
import org.apache.kafka.common.serialization.Deserializer

class CustomDeserialization extends Deserializer[StudentClass] {

  override def configure(map: util.Map[String, _], b: Boolean): Unit = {

  }

  override def close(): Unit = {

  }

  override def deserialize(topic: String, bytes: Array[Byte]) = {
    val byteArrayInputStream = new ByteArrayInputStream(bytes)
    val objectInputStream = new ObjectInputStream(byteArrayInputStream)
    val studentData = objectInputStream.readObject().asInstanceOf[StudentClass]
    byteArrayInputStream.close()
    objectInputStream.close()
    studentData
  }

}