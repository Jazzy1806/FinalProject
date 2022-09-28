import { ProductReport } from './../../models/product-report';
import { ProductReportService } from './../../services/product-report.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-product-report',
  templateUrl: './product-report.component.html',
  styleUrls: ['./product-report.component.css']
})
export class ProductReportComponent implements OnInit {
  report = {} as ProductReport;
  reports: ProductReport[] = [];
  newReport: ProductReport | null = null;

  constructor(private reportService: ProductReportService) { }

  ngOnInit(): void {
    this.reload();
  }

  getReport() {
    return this.report;
  }

  getNewReport() {
    return this.newReport;
  }

  getReports() {
    return this.reports;
  }

  reload() {
    this.reportService.index().subscribe({
      next: (data) => {
        this.reports = data;
      },
      error: (err) => {
        console.error('ProductReportComponent.reload(): error loading reports: ' + err);
      },
    });
  }

  addReport(newReport: ProductReport) {
    this.reportService.create(newReport).subscribe({
      next: (data) => {
        this.newReport = {} as ProductReport;
        this.reload();
      },
      error: (err) => {
        console.error('ProductReportComponent.addReport(): error creating product report' + err);
      },
    });
  }
}
