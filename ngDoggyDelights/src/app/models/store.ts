import { ProductReport } from 'src/app/models/product-report';
import { Address } from "./address";
import { Inventory } from "./inventory";
import { Chain } from "./chain";
import { StoreComment } from "./store-comment";
import { Product } from "./product";
import { User } from './user';

export class Store {
  id: number;
  name: string = '';
  address?: Address | null;
  description: string = '';
  inventories: Inventory[] | null;
  chain?: Chain | null;
  comments: StoreComment[] | null;
  websiteUrl: string = '';
  logoUrl: string = '';
  products: Product[] | null = null;
  productReports: ProductReport[] | null;
  enabled: boolean = false;
  owner?: User;


  constructor(  id: number = 0, name: string = "", description: string ="", websiteUrl: string="", logoUrl: string="",
                productReports: ProductReport[], inventories: Inventory[] =[], comments: StoreComment[]= [], enabled : boolean = false, address?: Address, chain?: Chain, owner? : User) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.websiteUrl = websiteUrl;
    this.logoUrl = logoUrl;
    this.address = address;
    this.productReports = productReports;
    this.inventories = inventories;
    this.chain = chain;
    this.comments = comments;
    this.enabled = enabled;
    this.owner = owner;


  }
}
