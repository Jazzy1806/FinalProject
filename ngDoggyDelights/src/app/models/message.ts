import { User } from "./user";

export class Message {

  id: number;
  message: string;
  fromUser: User;
  toUser: User;

  constructor(id: number, message: string, fromUser: User, toUser: User) {
    this.id = id;
    this.message = message;
    this.fromUser = fromUser;
    this.toUser = toUser;
  }
}
