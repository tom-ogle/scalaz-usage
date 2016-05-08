import model.{Name, User, UserDetails}

import scalaz.Lens

package object lens {
  // You need lenses all the way down the hierarchy
  val userUserDetailsLens = Lens.lensu[User, UserDetails](
    (user, userDetails) => user.copy(details = userDetails),
    _.details
  )

  val userDetailsNameLens = Lens.lensu[UserDetails, Name](
    (userDetails, name) => userDetails.copy(name = name),
    _.name
  )

  val userDetailsDoubleOptInLens = Lens.lensu[UserDetails, Boolean](
    (userDetails, doubleOptIn) => userDetails.copy(doubleOptIn = doubleOptIn),
    _.doubleOptIn
  )

  val nameFirstNameLens = Lens.lensu[Name, String](
    (name, newFirstName) => name.copy(firstName = newFirstName),
    _.firstName
  )

  // <=< is an alias for compose
  val userFirstNameLens = nameFirstNameLens <=< userDetailsNameLens <=< userUserDetailsLens
  val userDoubleOptInLens = userDetailsDoubleOptInLens <=< userUserDetailsLens
}
