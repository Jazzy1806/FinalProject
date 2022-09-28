import { Product } from "./product";
import { Store } from "./store";

export class Inventory {
  id: number;
  price: number | null;
  quantity: number | null;
  store: Store;
  product: Product;
  enabled: boolean;
  dateCreated: Date | null;
  dateUpdated: Date | null;

  constructor(id: number, price: number, quantity: number, enabled: boolean, store: Store, product: Product,
    dateCreated: Date, dateUpdated: Date) {
    this.id = id;
    this.price = price;
    this.quantity = quantity;
    this.store = store;
    this.product = product;
    this.enabled = enabled;
    this.dateCreated = dateCreated;
    this.dateUpdated = dateUpdated;
  }
}
