import { Timestamp } from "rxjs";
import { Address } from "./address";
import { Pet } from "./pet";


export class User {
  id: number;
  firstName: string = '';
  lastName: string = '';
  email: string = '';
  username: string = '';
  password: string = '';
  enabled: boolean | null;
  dateCreated: Date | null;
  dateUpdated: Date | null;
  role: string = '';
  bio: string = '';
  image: string = '';
  address: Address | null;
  pets: Pet[] | null;


  constructor(  id: number, firstName: string, lastName: string, email: string,
      username: string, password: string, enabled: boolean, dateCreated: Date,
      dateUpdated: Date, role: string, bio: string, image: string="https://www.kindpng.com/picc/m/79-793845_meet-greet-person-and-dog-icon-hd-png.png", address: Address, pets: Pet[]) {
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
    this.address = address;
    this.pets = pets;
  }
}
