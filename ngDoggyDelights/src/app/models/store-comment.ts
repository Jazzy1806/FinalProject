import { Store } from "./store";
import { User } from "./user";

export class StoreComment {
  id: number;
  title: string = '';
  description: string = '';
  rating: number;
  createdOn: Date;
  store: Store;
  user: User;
  // parentStoreComment: StoreComment | null;
  // replyStoreComments: StoreComment[] | null;



  constructor(id: number = 0, title: string ="", description: string ="", rating: number, createdOn: Date , store: Store , user : User) {
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
