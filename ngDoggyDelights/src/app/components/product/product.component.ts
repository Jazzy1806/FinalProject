import { ProductReportComponent } from './../product-report/product-report.component';
import { Component, OnInit } from '@angular/core';
import { Product } from 'src/app/models/product';
import { ProductService } from './../../services/product.service';
import { ProductReport } from 'src/app/models/product-report';
import { AuthService } from 'src/app/services/auth.service';
// import { Observable } from 'rxjs';

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.css'],
  providers: [ProductReportComponent]
})
export class ProductComponent implements OnInit {
  products: Product[] = [];
  newProduct: Product | null = null;
  editProduct: Product | null = null;
  detailProduct: Product | null = null;
  newReport: ProductReport | null = null;
  reports: ProductReport[] = [];
  showAll: boolean = true;
  showDetail: boolean = false;
  loggedInUser: any;

  constructor(
    private prodService: ProductService,
    private reportComp: ProductReportComponent,
    private authService: AuthService
  ) { }

  ngOnInit(): void {
    this.reload();
    this.getLoggedInUser();
  }

  getLoggedInUser() {
    this.authService.getLoggedInUser().subscribe({
      next: (user) => {
        this.loggedInUser = user;
        // this.newComment.user = this.loggedInUser;
        console.log('user logged in ' + user.username);
      },
      error: (problem) => {
        console.error(
          'StoreListHttpComponent.collectLoggedInUser(): error loading user logged in'
        );
        console.error(problem);
      },
    });
  }

  displayProduct(product: Product) {
    this.reports = this.reportComp.getProductReports(product.id);
    return product;
  }

  getNewProduct() {
    this.newProduct = new Product();
    return this.newProduct;
  }

  getNewReport(product: Product) {
    this.newReport = this.reportComp.getNewReport();
    this.newReport.user = this.loggedInUser;
    this.newReport.product = product;
    return this.newReport;
  }

  addReport(report: ProductReport) {
    this.reportComp.addReport(report);
    this.newReport = this.reportComp.getNewReport();
  }

  getReports() {
    this.reports = this.reportComp.getAllReports();
  }

  getProductReports(pid: number) {
    this.reports = this.reportComp.getProductReports(pid);
    return this.reports;
  }

  reload() {
    this.prodService.index().subscribe({
      next: (data) => {
        this.products = data;
      },
      error: (err) => {
        console.error(
          'ProductComponent.reload(): error loading products: ' + err
        );
      },
    });
  }

  addProduct(newProduct: Product) {
    this.prodService.create(newProduct).subscribe({
      next: (data) => {
        // this.newProduct = new Product();
        this.reload();
      },
      error: (err) => {
        console.error('ProductComponent.addProduct(): error adding product' + err);
      },
    });
  }

  updateProduct(editProduct: Product) {
    this.prodService.update(editProduct).subscribe({
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
    console.log(product);

    this.prodService.update(product).subscribe({
      next: (data) => {
        // this.detailProduct = product;
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
