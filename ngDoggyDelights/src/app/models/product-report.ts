import { Product } from "./product";
import { Store } from "./store";
import { User } from "./user";

export class ProductReport {
  id: number;
  createdOn?: Date | null;
  updatedOn: Date | null;
  userQuantity: number; //do we want this to be low-normal-high???
  price: number;
  isInStock: boolean;
  remark: string;
  user?: User;
  store?: Store | null;
  product?: Product;

  constructor(
    id: number = 0,
    createdOn: Date = new Date(),
    updatedOn: Date = new Date(),
    userQuantity: number = 0,
    price: number = 0,
    isInStock: boolean = true,
    remark: string = '',
    user: User = {} as User,
    store: Store = {} as Store,
    product: Product = new Product()
  ) {
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
