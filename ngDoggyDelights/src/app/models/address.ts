export class Address {
  id: number;
  address: string = '';
  city: string = '';
  state: string = '';
  postalCode: string = '';
  phone: string = '';


  constructor(id: number, address: string = '', city: string= '', state: string= '', postalCode: string= '', phone: string= '') {

    this.id = id;
    this.address = address;
    this.city = city;
    this.state = state;
    this.postalCode = postalCode;
    this.phone = phone;
  }
}
