import { Store } from 'src/app/models/store';
import { NgForm } from '@angular/forms';
import { ProductReportService } from 'src/app/services/product-report.service';
import { ProductReportComponent } from './../product-report/product-report.component';
import { Component, OnInit } from '@angular/core';
import { Product } from 'src/app/models/product';
import { ProductService } from './../../services/product.service';
import { ProductReport } from 'src/app/models/product-report';
import { AuthService } from 'src/app/services/auth.service';
import { ProductCommentService } from 'src/app/services/product-comment.service';
import { ProductComment } from 'src/app/models/product-comment';
import { IngredientService } from 'src/app/services/ingredient.service';
import { Ingredient } from 'src/app/models/ingredient';
// import { Observable } from 'rxjs';

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.css'],
  providers: [ProductReportComponent]
})
export class ProductComponent implements OnInit {
  loggedInUser: any;
  showAll: boolean = true;
  storeId: number = 1;
  store: Store = {} as Store;
  products: Product[] = [];
  iid: number = 0;
  isCollapsed: boolean = true;

  hasIng: boolean = false;

  allIngredients: Ingredient[] = [];
  newProduct: Product | null = null;
  editProduct: Product | null = null;
  detailProduct: Product | null = null;
  newReport: ProductReport | null = null;
  newComment: ProductComment | null = null
  reports: ProductReport[] = [];
  comments: ProductComment[] = [];

  constructor(
    private prodService: ProductService,
    private reportService: ProductReportService,
    private reportComp: ProductReportComponent,
    private commentService: ProductCommentService,
    private ingredientService: IngredientService,
    private authService: AuthService
  ) { }

  ngOnInit(): void {
    this.reload();
    this.getLoggedInUser();
    this.getAllIngredients();
  }

  getLoggedInUser() {
    this.authService.getLoggedInUser().subscribe({
      next: (user) => {
        this.loggedInUser = user;
      },
      error: (problem) => {
        console.error(
          'ProductComponent.collectLoggedInUser(): error loading user logged in'
        );
        console.error(problem);
      },
    });
  }

  getAllIngredients() {
    this.ingredientService.index().subscribe({
      next: (data) => {
        this.allIngredients = data;
      },
      error: (err) => {
        console.error('ProductReportComponent.displayProduct(): error loading ingredients' + err);
      },
    });
  }

  displayProduct(product: Product) {
    this.reload();
    this.detailProduct = product;

    this.reportService.getReportsByProduct(product.id).subscribe({
      next: (data) => {
        if (this.detailProduct) {
          this.detailProduct.reports = data;
        }
      },
      error: (err) => {
        console.error('ProductReportComponent.getProductReports(): error loading product reports' + err);
      },
    });

    this.commentService.indexByProduct(product.id).subscribe({
      next: (comments) => {
        if (this.detailProduct) {
          this.detailProduct.comments = comments;
        }
      },
      error: (err) => {
        console.error('ProductService.displayProduct(): error loading product comments' + err);
      },
    });

    return product;
  }

  getNewProduct() {
    this.newProduct = new Product();
    this.getAllIngredients();
    return this.newProduct;
  }

  getNewComment(product: Product) {
    this.newComment = new ProductComment();
    this.newComment.product = product;
    return this.newComment;
  }

  getNewReport(product: Product) {
    this.newReport = this.reportComp.getNewReport();
    this.newReport.user = this.loggedInUser;
    this.newReport.product = product;
    return this.newReport;
  }

  // getStore(sid: number) {
  //   this.storeService.getStoreById(sid).subscribe({
  //     next: (store) => {
  //       console.log('*** From Store Service: ' + store);
  //       this.store = store;
  //     },
  //     error: (err) => {
  //       console.error('ProductComponent.getStoreById(): error retrieving store' + err);
  //     },
  //   });
  // }

  addReport(product: Product, sid: number, report: ProductReport) {
    this.reportService.create(product.id, sid, report).subscribe({
      next: (report) => {
        this.displayProduct(product);
      },
      error: (err) => {
        console.error('ProductComponent.addReport(): error adding report' + err);
      },
    });
  }

  addComment(product: Product, sid: number, comment: ProductComment) {
    this.commentService.create(product.id, sid, comment).subscribe({
      next: (data) => {
        // this.reload();
        this.displayProduct(product);
      },
      error: (err) => {
        console.error('ProductComponent.addComment(): error adding comment' + err);
      },
    });
  }

  getReports() {
    this.reports = this.reportComp.getAllReports();
    return this.reports;
  }

  reload() {
    this.prodService.index().subscribe({
      next: (products) => {
        this.products = products;
      },
      error: (err) => {
        console.error(
          'ProductComponent.reload(): error loading products: ' + err
        );
      },
    });
  }

  addProduct(newProduct: Product) {
    console.log(newProduct);

    this.prodService.create(newProduct).subscribe({
      next: (data) => {
        this.reload();
      },
      error: (err) => {
        console.error('ProductComponent.addProduct(): error adding product' + err);
      },
    });
  }

  addIngredient(id: number, product: Product) {
    console.log(id - 1);

    // if (product.ingredients.includes(this.allIngredients[iid])) {
    console.log(id - 1);
    console.log(this.allIngredients[id - 1]);

    product.ingredients.push(this.allIngredients[id - 1]);
    console.log(product);

    // }
    return product;
  }

  changeIngredient(id: number, product: Product, hasIng: boolean) {
    id--;
    // console.log(this.allIngredients[id]);
    if (!product.ingredients.includes(this.allIngredients[id])) {
      product.ingredients.push(this.allIngredients[id]);
    } else {
      let index = (ing: Ingredient) => ing.id === this.allIngredients[id].id;
      // console.log(product.ingredients.findIndex(index));
      product.ingredients.splice(product.ingredients.findIndex(index), 1);
      // console.log('after reomve');
    }
    // console.log(product);
    return product;
  }

  updateProduct(product: Product) {
    this.prodService.update(product).subscribe({
      next: (data) => {
        this.editProduct = null;
        this.reload();
      },
      error: (err) => {
        console.error('ProductComponent.updateProduct(): error updating product' + err);
      },
    });
  }

  disableProduct(product: Product) {
    product.enabled = 0;
    this.prodService.update(product).subscribe({
      next: (data) => {
        this.editProduct = null;
        this.reload();
      },
      error: (err) => {
        console.error('ProductComponent.disableProduct(): error disabling product' + err);
      },
    });
  }

  // deleteProduct(pid: number) {
  //   this.prodService.destroy(pid).subscribe({
  //     next: (data) => {
  //       // this.editTodo = null;
  //       // this.reload();
  //       // this.displayTable();
  //     },
  //     error: (err) => {
  //       console.error('ProductComponent.delete(): error deleteing product' + err);
  //     },
  //   });
  // }
}
