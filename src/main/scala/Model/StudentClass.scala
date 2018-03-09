package Model

case class StudentClass(id: String, name: String) {
  override def toString: String = s"(id ->$id , name ->$name)\n"
}