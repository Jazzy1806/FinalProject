export class Chain {
  id: number;
  name: string = '';
  description: string = '';
  websiteUrl: string = '';
  logoUrl: string = '';

  constructor(  id: number, name: string, description: string, websiteUrl: string, logoUrl: string) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.websiteUrl = websiteUrl;
    this.logoUrl = logoUrl;

  }
}
