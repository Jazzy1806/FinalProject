import { ChatService } from './chat.service';
import { AuthService } from './auth.service';
import { Injectable } from '@angular/core';
import * as Stomp from 'stompjs';
import * as SockJS from 'sockjs-client';
import { User } from '../models/user';




@Injectable({
  providedIn: 'root'
})
export class WebsocketService {

  connectedPersonal: boolean = true;
  connectedGroup: boolean = true;
  private stompClient: Stomp.Client = {} as Stomp.Client;
  private stompClient2: Stomp.Client = {} as Stomp.Client;
  user: User = {} as User;


  constructor(private authService: AuthService, private chatService: ChatService) {
   }


  personalConnect(loggedInUser: User) {
    this.user = loggedInUser;
    const socket = new SockJS('http://localhost:8090/doggiedelights');
    this.stompClient = Stomp.over(socket);

    const _this = this;
    this.stompClient.connect({}, function (frame) {
      _this.connectedPersonal = true;
      console.log('Connected: ' + frame);

      console.log(_this.user.id);

      _this.stompClient.subscribe('/topic/messages' + _this.user.id, function (waiting) {
        _this.onNewMessage(waiting);
      });
    });
  }

  onNewMessage(message: any) {
    let msg = JSON.parse(message.body);
    this.chatService.getPersonalMessages(msg.message_from, msg.message_to);
  }

  groupConnect(loggedInUser: User) {
    this.user = loggedInUser;
    const socket2 = new SockJS('http://localhost:8090/doggiedelights');
    this.stompClient2 = Stomp.over(socket2);

    const _this = this;
    this.stompClient2.connect({}, function (frame) {
      _this.connectedGroup = true;
      console.log('Connected: ' + frame);

      console.log(_this.user.id);
      _this.stompClient2.subscribe('/topic/messages/group' + _this.user.id, function (waiting) {
        _this.onNewGroupMessage(waiting);
      });
    });
  }

  onNewGroupMessage(message: any) {
    let msg = JSON.parse(message.body);
    this.chatService.getGroupMessages(msg.squad_id);
  }



}
