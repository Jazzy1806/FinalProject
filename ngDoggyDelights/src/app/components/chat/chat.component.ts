import { ChatService } from './../../services/chat.service';
import { Component, OnInit } from '@angular/core';
import { Message } from 'src/app/models/message';
import { MessageGroup } from 'src/app/models/message-group';
import { User } from 'src/app/models/user';

@Component({
  selector: 'app-chat',
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.css']
})
export class ChatComponent implements OnInit {

  personalMessages: Message[] = [];
  groupMessages: MessageGroup[] = [];
  otherUsers: User[] = [];
  groups: any[] = [];
  message: Message = {} as Message;
  groupMessage: MessageGroup = {} as MessageGroup;
  selectedUser: User = {} as User;
  selectedGroup: any = {};    //create group entity???

  constructor(private chatService: ChatService) { }

  ngOnInit(): void {
    //initialize all websocket connections in loop (for groups)  **PersonalConnect & GroupConnect from websocketServe
    //click events on chat boxes to fetch all messages
  }

  fetchMessages(fromUser: number, toUser: number) {
    this.chatService.getPersonalMessages(fromUser, toUser).subscribe({
      next: (result) => {
        this.personalMessages = result;
      },
      error: (nojoy) => {
        console.error('ChatHttpComponent.getPersonalMsgs(): error fetching msgs:' + nojoy);
      },
    });
  }

  sendPersonalMsg(toUser: number, message: Message) {
    this.chatService.sendPersonalMessage(toUser, message).subscribe({
      next: (result) => {
        this.personalMessages.push(result);
        message = {} as Message;
      },
      error: (nojoy) => {
        console.error('TodosHttpComponent.addTodo(): error creating todos:' + nojoy);
      },
    });
  }

  fetchGroupMessages(fromGroup: number) {
    this.chatService.getGroupMessages(fromGroup).subscribe({
      next: (result) => {
        this.groupMessages = result;
      },
      error: (nojoy) => {
        console.error('ChatHttpComponent.getPersonalMsgs(): error fetching msgs:' + nojoy);
      },
    });
  }

  sendGroupMsg(groupId: number, groupMessage: MessageGroup) {
    this.chatService.sendGroupMessage(groupId, groupMessage).subscribe({
      next: (result) => {
        this.groupMessages.push(result);
        groupMessage = {} as MessageGroup;
      },
      error: (nojoy) => {
        console.error('TodosHttpComponent.addTodo(): error creating todos:' + nojoy);
      },
    });
  }

  fetchUsers(userId: number) {
    this.chatService.getUsers(userId).subscribe({
      next: (result) => {
        this.otherUsers = result;
      },
      error: (nojoy) => {
        console.error('ChatHttpComponent.getPersonalMsgs(): error fetching msgs:' + nojoy);
      },
    });
  }

  fetchGroups(userId: number) {
    this.chatService.getGroups(userId).subscribe({
      next: (result) => {
        this.groups = result;
      },
      error: (nojoy) => {
        console.error('ChatHttpComponent.getPersonalMsgs(): error fetching msgs:' + nojoy);
      },
    });
  }
}
