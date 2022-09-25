import { Address } from "./address";
import { Inventory } from "./inventory";
import { Chain } from "./chain";
import { StoreComment } from "./store-comment";

export class Store {
  id: number;
  name: string = '';
  address: Address | null;
  description: string = '';
  inventory: Inventory[] | null;
  chain: Chain | null;
  comments: StoreComment[] | null;
  websiteUrl: string = '';
  logoUrl: string = '';

  constructor(  id: number, name: string, description: string, websiteUrl: string, logoUrl: string,
                address: Address, inventory: Inventory[], chain: Chain, comments: StoreComment[]) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.websiteUrl = websiteUrl;
    this.logoUrl = logoUrl;
    this.address = address;
    this.inventory = inventory;
    this.chain = chain;
    this.comments = comments;

  }
}
