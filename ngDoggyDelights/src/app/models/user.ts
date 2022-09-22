import { Timestamp } from "rxjs";

export class User {
  id: number;
  firstName: string | null;
  lastName: string | null;
  email: string | null;
  username: string | null;
  password: string | null;
  enabled: boolean | null;
  dateCreated: Date;
  dateUpdated: Date | null;
  role: string | null;
  bio: string | null;
  image: string | null;

  constructor(  id: number, firstName: string, lastName: string, email: string,
      username: string, password: string, enabled: boolean, dateCreated: Date,
      dateUpdated: Date, role: string, bio: string, image: string) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.username = username;
    this.password = password;
    this.enabled = enabled;
    this.dateCreated = dateCreated;
    this.dateUpdated = dateUpdated;
    this.role = role;
    this.bio = bio;
    this.image = image;
  }
}
