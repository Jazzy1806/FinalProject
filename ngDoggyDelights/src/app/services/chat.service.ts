import { AuthService } from './auth.service';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/internal/Observable';
import { throwError } from 'rxjs/internal/observable/throwError';
import { catchError } from 'rxjs/internal/operators/catchError';
import { Message } from '../models/message';
import { MessageGroup } from '../models/message-group';
import { User } from '../models/user';
import { Group } from '../models/group';

@Injectable({
  providedIn: 'root'
})
export class ChatService {

  privateMessages: Message[] = [];
  groupMessages: MessageGroup[] = [];
  message: Message = {} as Message;
  loggedInUser: User = {} as User;

  url: string = 'http://localhost:8090/'


  constructor(private http: HttpClient, private authService: AuthService) {
    this.loggedInUser = this.authService.loggedInUser;
   }

  getHttpOptions() {
    let options = {
      headers: {
        Authorization: 'Basic ' + this.authService.getCredentials(),
        'X-Requested-With': 'XMLHttpRequest',
      },
    };
    return options;
  }

  getPersonalMessages(fromUser: number, toUser: number): Observable<Message[]>{
    //fetch from listmessage/{from}/{to} *no api in front
    return this.http.get<Message[]>(this.url + 'listmessage/' + fromUser + '/' + toUser, this.getHttpOptions()).pipe(
      catchError((err:any) => {
        console.log(err);
        return throwError(
          () => new Error('ChatService.getPersonalMsgs(): erorr retrieving messages: ' + err)
        );
      })
    );
  }

  sendPersonalMessage(toUser: number, message: Message) {
    //post req to app/chat/{to}
    return this.http.post<Message>(this.url + 'app/chat' + toUser, message, this.getHttpOptions()).pipe(
      catchError((err:any) => {
        console.log(err);
        return throwError(
          () => new Error('ChatService.sendPersonalMsg(): erorr sending messages: ' + err)
        );
      })
    );
  }

  getGroupMessages(fromGroup: number): Observable<MessageGroup[]> {
  //get req from listmessage/group/{squad_id} *no api in front
  return this.http.get<MessageGroup[]>(this.url + 'listmessage/group/' + fromGroup, this.getHttpOptions()).pipe(
    catchError((err:any) => {
      console.log(err);
      return throwError(
        () => new Error('ChatService.getGroupMsgs(): erorr retrieving messages: ' + err)
      );
    })
  );
  }

  sendGroupMessage(groupId: number, message: MessageGroup) {
    return this.http.post<MessageGroup>(this.url + 'app/chat/group/' + groupId, message, this.getHttpOptions()).pipe(
      catchError((err:any) => {
        console.log(err);
        return throwError(
          () => new Error('ChatService.sendGroupMsg(): erorr sending messages: ' + err)
        );
      })
    );
  }

  getGroupUsers(groupId: number, userId: number): Observable<User[]> {
//get fetchUsers/{myId} *no api in front
return this.http.get<User[]>(this.url + 'fetchGroupUsers/' + groupId + '/' + userId, this.getHttpOptions()).pipe(
  catchError((err:any) => {
    console.log(err);
    return throwError(
      () => new Error('ChatService.getUsers(): erorr retrieving users: ' + err)
    );
  })
);
  }

  getGroups(userId: number) {
    //get fetchUsers/{myId} *no api in front
    return this.http.get<Group[]>(this.url + 'fetchGroups/' + userId, this.getHttpOptions()).pipe(
      catchError((err:any) => {
        console.log(err);
        return throwError(
          () => new Error('ChatService.getGroups(): erorr retrieving groups: ' + err)
        );
      })
    );
      }


}
