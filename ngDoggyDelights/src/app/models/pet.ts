import { Breed } from "./breed";
import { Diet } from "./diet";
import { User } from "./user";

export class Pet {
  id: number;
  name: string | null;
  weight: number | null;
  gender: string | null;
  image: string | null;
  birthDate: Date | null;
  enabled: boolean;
  user: User;
  breeds: Breed[];
  dietNeeds: Diet[];
  color: string;

  constructor(  id: number, name: string, weight: number, gender: string, image: string, birthDate: Date,
                enabled: boolean, user: User, breeds: Breed[], dietNeeds: Diet[], color: string) {
    this.id = id;
    this.name = name;
    this.weight = weight;
    this.gender = gender;
    this.image = image;
    this.birthDate = birthDate;
    this.enabled = enabled;
    this.user = user;
    this.breeds = breeds;
    this.dietNeeds = dietNeeds;
    this.color = color;
  }
}
