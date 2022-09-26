import { ProductReportService } from 'src/app/services/product-report.service';
import { Component, OnInit } from '@angular/core';
import { Product } from 'src/app/models/product';
import { ProductService } from './../../services/product.service';
import { ProductReport } from 'src/app/models/product-report';

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.css'],
})
export class ProductComponent implements OnInit {
  products: Product[] = [];
  newProduct: Product | null = null;
  editProduct: Product | null = null;
  detailProduct: Product | null = null;
  reports: ProductReport[] = [];

  showAll: boolean = true;

  constructor(
    private prodService: ProductService,
    private reportService: ProductReportService
  ) {}

  ngOnInit(): void {
    this.reload();
  }

  displayProduct(product: Product) {
    return product;
  }

  getReports(pid: number) {
    this.reportService.getReports(pid).subscribe({
      next: (data) => {
        this.reports = data;
        console.log(this.reports);

      },
      error: (err) => {
        console.error(
          'ProductComponent.reload(): error loading products: ' + err
        );
      },
    });
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
        this.newProduct = new Product();
        this.reload();
      },
      error: (err) => {
        console.error('SongComponent.addSong(): error adding song' + err);
      },
    });
  }
}
