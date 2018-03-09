

import java.io.{ByteArrayOutputStream, ObjectOutputStream, ObjectStreamException}
import java.util

import Model.StudentClass
import org.apache.kafka.common.serialization.Serializer

class CustomSerialization extends Serializer[StudentClass] {

  override def configure(map: util.Map[String, _], b: Boolean): Unit = {

  }

  override def close(): Unit = {

  }

  override def serialize(topic: String, data: StudentClass): Array[Byte] = {
    try {
      val byteArrayOutputStream = new ByteArrayOutputStream()
      val outputStream = new ObjectOutputStream(byteArrayOutputStream)
      outputStream.writeObject(data)
      outputStream.close()
      byteArrayOutputStream.close()
      byteArrayOutputStream.toByteArray
    }
    catch {
      case exception: ObjectStreamException => throw new Exception(exception)
    }
  }

}