import { Product } from "./product";
import { Store } from "./store";

export class Inventory {
  id: number;
  price: number | null;
  quantity: number | null;
  store: Store;
  product: Product;
  enabled: boolean;
  createdOn: Date | null;
  updatedOn: Date | null;

  constructor(id: number, price: number, quantity: number, enabled: boolean, store: Store, product: Product,
    createdOn: Date, updatedOn: Date) {
    this.id = id;
    this.price = price;
    this.quantity = quantity;
    this.store = store;
    this.product = product;
    this.enabled = enabled;
    this.createdOn = createdOn;
    this.updatedOn = updatedOn;
  }
}
