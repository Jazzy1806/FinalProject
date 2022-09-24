import { Store } from "./store";

export class StoreComment {
  id: number;
  title: string | null;
  description: string | null;
  rating: number | null;
  createdOn: Date;
  store: Store | null;
  parentStoreComment: StoreComment | null;
  replyStoreComments: StoreComment | null;

  constructor(id: number, title: string, description: string, rating: number, createdOn: Date, store : Store, parentStoreComment : StoreComment, replyStoreComments : StoreComment) {
    this.id = id;
    this.title = title;
    this.description = description;
    this.rating = rating;
    this.createdOn = createdOn;
    this.store = store;
    this.parentStoreComment = parentStoreComment;
    this.replyStoreComments = replyStoreComments;
  }
}
