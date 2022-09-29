export class Message {

  message: string;
  fromLogin: number;

  constructor(message: string, fromLogin: number) {
    this.message = message;
    this.fromLogin = fromLogin;
  }
}
