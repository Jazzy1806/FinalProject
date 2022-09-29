import { User } from "./user";

export class Group {

  id: number;
  name: string;
  users: User[];

  constructor(id: number, name: string, users: User[]) {
    this.id = id;
    this.name = name;
    this.users = users;
  }
}
