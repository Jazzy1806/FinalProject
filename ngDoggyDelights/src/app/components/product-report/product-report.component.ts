import { ProductReport } from './../../models/product-report';
import { ProductReportService } from './../../services/product-report.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-product-report',
  templateUrl: './product-report.component.html',
  styleUrls: ['./product-report.component.css']
})
export class ProductReportComponent implements OnInit {
  prodReports: ProductReport[] = [];

  constructor(private prService: ProductReportService) { }

  ngOnInit(): void {
    this.reload();
  }

  getAllProducts(){
    return this.prodReports;
  }

  reload() {
    this.prService.index().subscribe({
      next: (data) => {
        this.prodReports = data;
      },
      error: (err) => {
        console.error('ProductComponent.reload(): error loading products: ' + err);
      },
    });
  }

}
