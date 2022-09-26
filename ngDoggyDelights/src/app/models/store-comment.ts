import { Store } from "./store";
import { User } from "./user";

export class StoreComment {
  id: number;
  title: string = '';
  description: string = '';
  rating: number | null;
  createdOn?: Date;
  store?: Store | null;
  user?: User | null;
  // parentStoreComment: StoreComment | null;
  // replyStoreComments: StoreComment[] | null;



  constructor(id: number = 0, title: string ="", description: string ="", rating: number = 0, createdOn?: Date , store?: Store , user? : User) {
    this.id = id;
    this.title = title;
    this.description = description;
    this.rating = rating;
    this.createdOn = createdOn;
    this.store = store;
    this.user = user;
    // this.parentStoreComment = parentStoreComment;
    // this.replyStoreComments = replyStoreComments;
  }
}
