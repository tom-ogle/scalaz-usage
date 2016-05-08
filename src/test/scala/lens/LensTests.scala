package lens

import java.util.UUID

import model.{ID, Name, User, UserDetails}
import org.scalatest.{FlatSpec, Matchers}


class LensTests extends FlatSpec with Matchers {

  behavior of "userFirstNameLens#set"

  it should "create a copy of the user with the new first name" in {
    val user = createTestUser

    val newUser = userFirstNameLens.set(user, "new name")
    newUser.details.name.firstName should be("new name")
  }

  it should "leave the old user with the old first name" in {
    val user = createTestUser

    userFirstNameLens.set(user, "new name")
    user.details.name.firstName should be("John")
  }

  behavior of "userDoubleOptInLens#mod"

  it should "allow us to switch from false to true" in {
    val user = createTestUser

    val newUser = userDoubleOptInLens.mod(_ != true, user)
    newUser.details.doubleOptIn should be(true)
  }

  private def createTestUser: User = {
    User(id = ID(UUID.randomUUID()),
      username = "test user",
      details = UserDetails(
        name = Name(firstName = "John", lastName = "Smith"),
        emailAddress = "test@test.com",
        password = "pass",
        phone = "01234567890",
        doubleOptIn = false
      ))
  }
}
