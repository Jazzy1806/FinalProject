export class Inventory {
  id: number;
  price: number | null;
  quantity: number | null;

  constructor(id: number, price: number, quantity: number) {
    this.id = id;
    this.price = price;
    this.quantity = quantity;
  }
}
