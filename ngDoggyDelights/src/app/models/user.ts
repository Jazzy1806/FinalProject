export class User {
  id: number =0;
  email: string = '';
  username: string = '';
  password: string = '';
  role: string = '';

  constructor(  id: number, email: string, username: string, password: string, role: string) {
    this.id = id;
    this.email = email;
    this.username = username;
    this.password = password;
    this.role = role;
  }
}
