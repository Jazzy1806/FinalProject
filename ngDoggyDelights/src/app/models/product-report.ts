import { Product } from "./product";
import { Store } from "./store";
import { User } from "./user";

export class ProductReport {
  id: number;
  createdOn: Date;
  updatedOn: Date | null;
  userQuantity: number | null; //do we want this to be low-normal-high???
  price: number | null;
  isInStock: boolean | null;
  remark: string | null;
  user: User | null;
  store: Store | null;
  product: Product | null;

  constructor(id: number, createdOn: Date, updatedOn: Date, userQuantity: number, price: number,
              isInStock: boolean, remark: string, user: User, store: Store, product: Product) {
    this.id = id;
    this.createdOn = createdOn;
    this.updatedOn = updatedOn;
    this.userQuantity = userQuantity;
    this.price = price;
    this.isInStock = isInStock;
    this.remark = remark;
    this.user = user;
    this.store = store;
    this.product = product;
  }
}
