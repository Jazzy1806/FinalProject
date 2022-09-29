import { WebsocketService } from './../../services/websocket.service';
import { ChatService } from './../../services/chat.service';
import { Component, OnInit } from '@angular/core';
import { Message } from 'src/app/models/message';
import { MessageGroup } from 'src/app/models/message-group';
import { User } from 'src/app/models/user';
import { Group } from 'src/app/models/group';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-chat',
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.css']
})
export class ChatComponent implements OnInit {

  personalMessages: Message[] = [];
  groupMessages: MessageGroup[] = [];
  otherUsers: User[] = [];
  groups: Group[] = [];
  message: Message = {} as Message;
  groupMessage: MessageGroup = {} as MessageGroup;
  selectedUser: User = {} as User;
  selectedGroup: Group = {} as Group;
  loggedInUser: User = {} as User;


  constructor(private authService: AuthService, private chatService: ChatService, private socketService: WebsocketService) {
    this.loggedInUser = this.authService.loggedInUser;
   }

  ngOnInit(): void {
    this.chatService.getGroups(this.loggedInUser.id);
    this.socketService.personalConnect();
    this.socketService.groupConnect();

    //pull groupusers when group is selected
    //pull group messages when group is selected
    //save this for when user is selected - click event
    // this.chatService.getPersonalMessages(this.loggedInUser.id, this.selectedUser.id);
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
