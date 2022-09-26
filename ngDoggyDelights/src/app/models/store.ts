import { Address } from "./address";
import { Inventory } from "./inventory";
import { Chain } from "./chain";
import { StoreComment } from "./store-comment";
import { Product } from "./product";

export class Store {
  id: number;
  name: string = '';
  address?: Address | null;
  description: string = '';
  inventory: Inventory[] | null;
  chain?: Chain | null;
  comments: StoreComment[] | null;
  websiteUrl: string = '';
  logoUrl: string = '';
  products: Product[] | null = null;
  enabled: boolean = false;


  constructor(  id: number = 0, name: string = "", description: string ="", websiteUrl: string="", logoUrl: string="",
                address?: Address, inventory: Inventory[] =[], chain?: Chain, comments: StoreComment[]= [], enabled : boolean = false) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.websiteUrl = websiteUrl;
    this.logoUrl = logoUrl;
    this.address = address;
    this.inventory = inventory;
    this.chain = chain;
    this.comments = comments;
    this.enabled = enabled;


  }
}
