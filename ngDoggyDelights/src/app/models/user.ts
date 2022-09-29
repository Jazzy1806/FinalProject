import { Timestamp } from "rxjs";
import { Message } from "stompjs";
import { Address } from "./address";
import { Group } from "./group";
import { MessageGroup } from "./message-group";
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
  image: string = 'https://www.kindpng.com/picc/m/79-793845_meet-greet-person-and-dog-icon-hd-png.png';
  address: Address | null;
  pets: Pet[] | null;
  groups: Group[] | null;
  groupMessages: MessageGroup[] | null;
messagesSent: Message[] | null;
messagesReceived: Message[] | null;

  constructor(  id: number, firstName: string, lastName: string, email: string,
      username: string, password: string, groups: Group[], enabled: boolean, dateCreated: Date,
      dateUpdated: Date, role: string, bio: string, groupMessages: MessageGroup[], messagesSent: Message[], messagesReceived: Message[], image: string="https://www.kindpng.com/picc/m/79-793845_meet-greet-person-and-dog-icon-hd-png.png", address: Address, pets: Pet[]) {
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
    this.groups = groups;
    this.groupMessages = groupMessages;
    this.messagesSent = messagesSent;
    this.messagesReceived = messagesReceived;
  }
}
