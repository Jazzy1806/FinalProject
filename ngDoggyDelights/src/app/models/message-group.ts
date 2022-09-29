import { Group } from './group';
import { Message } from './message';
import { User } from './user';

export class MessageGroup {

  message: string = '';
  created: Date;
  group: Group;
  user: User;

  constructor(group: Group, user: User, message: string, created: Date) {
    this.group = group;
    this.user = user;
    this.message = message;
    this.created = created;
  }
}
