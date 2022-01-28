package com.commodityvectors.snapshotmatchers

import org.scalatest.FixtureTestSuite
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class DefaultSerializersSpec extends AnyWordSpec with Matchers with SnapshotMatcher {
  self: FixtureTestSuite =>

  case class Test(value: Int)

  "DefaultSerializers" should {

    "Serialize option" in {
      val element: Option[Test] = None
      SnapshotSerializer.serialize(Option(Test(1))) shouldEqual "Some(Test(1))"
      SnapshotSerializer.serialize(element) shouldEqual "None"
    }

    "Serializer array" in {
      SnapshotSerializer.serialize(List(Test(1))) shouldEqual "List(Test(1))"
      SnapshotSerializer.serialize(Seq(Test(1))) shouldEqual "List(Test(1))"
      SnapshotSerializer.serialize(Vector(Test(1))) shouldEqual "Vector(Test(1))"
    }

    "Serializer maps" in {
      SnapshotSerializer.serialize(Map(Test(1) -> Test(2))) shouldEqual "Map(Test(1) -> Test(2))"
      SnapshotSerializer.serialize(Map("key" -> Test(2))) shouldEqual "Map(key -> Test(2))"
      SnapshotSerializer.serialize(Map(10 -> Test(2))) shouldEqual "Map(10 -> Test(2))"
      SnapshotSerializer.serialize(Map(10.0 -> Test(2))) shouldEqual "Map(10.0 -> Test(2))"
    }

    "Serialize composed types" in {
      case class Complex(v1: Int,
                         v2: String,
                         v3: Double,
                         v4: List[Option[String]],
                         v5: Map[Option[String], Seq[Complex]])
      val child = Complex(1, "2", 3.0, List(Option("Me")), Map())
      val instance = Complex(1, "2", 3.0, List(Option("Me")), Map(Option("you") -> Seq(child)))
      SnapshotSerializer.serialize(instance) shouldEqual
        s"""|Complex(
            |  v1 = 1,
            |  v2 = "2",
            |  v3 = 3.0,
            |  v4 = List(Some(Me)),
            |  v5 = Map(Some(you) -> List(Complex(1,2,3.0,List(Some(Me)),Map())))
            |)""".stripMargin
    }

    "Allow custom serializers" in {
      implicit lazy val customSerializer: SnapshotSerializer[Test] = (in: Test) => s"CustomSerializer: ${in.value}"

      SnapshotSerializer.serialize(Test(1)) shouldEqual "CustomSerializer: 1"
    }
  }

}
