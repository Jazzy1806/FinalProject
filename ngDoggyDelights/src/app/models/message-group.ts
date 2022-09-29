import { Message } from './message';

export class MessageGroup extends Message{

  groupId: number;

  constructor(message: string, fromLogin: number, groupId: number) {
    super(message, fromLogin);
    this.groupId = groupId;
  }
}
