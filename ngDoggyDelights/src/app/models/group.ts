import { MessageGroup } from "./message-group";
import { User } from "./user";

export class Group {

  id: number;
  name: string;
  users: User[];
  groupMessages: MessageGroup[] | null;

  constructor(id: number, name: string, users: User[], groupMessages: MessageGroup[]) {
    this.id = id;
    this.name = name;
    this.users = users;
    this.groupMessages = groupMessages;
  }
}
