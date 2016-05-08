package model

import java.util.UUID

case class User(id: ID, username: String, details: UserDetails)
case class ID(id: UUID)
case class UserDetails(name: Name,
                       emailAddress: String,
                       password: String,
                       phone: String,
                       doubleOptIn: Boolean)
case class Name(firstName: String, lastName: String)
