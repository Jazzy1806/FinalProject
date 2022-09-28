// import { ProdRepPipe } from './../../pipes/prod-rep.pipe';
import { Component, OnInit, ViewChild } from '@angular/core';
import { MapGeocoder, MapInfoWindow, MapMarker } from '@angular/google-maps';
import { Product } from 'src/app/models/product';
import { ProductService } from 'src/app/services/product.service';
import { ProdRepPipe } from 'src/app/pipes/prod-rep.pipe';

@Component({
  selector: 'app-map',
  templateUrl: './map.component.html',
  styleUrls: ['./map.component.css']
})
export class MapComponent implements OnInit {

  geocodedPlaces: google.maps.LatLngLiteral[] = [];
  productsByKeyword: Product[] = [];
  mostRecentProdUpdates: any[] = [];
  keyword: string = '';

  constructor(geocoder: MapGeocoder, private productService: ProductService, private prodRepPipe: ProdRepPipe) {
    // geocoder.geocode({
    //   address: '6580 Marshall Street, Arvada, CO'
    // }).subscribe(({
    //   results}) => {
    //     console.log(results);
    //   });
    }


    ngOnInit(): void {
    }

    @ViewChild(MapInfoWindow) infoWindow: MapInfoWindow | undefined;

    zoom = 4;
    radius = 500;

    center: google.maps.LatLngLiteral = {lat: 24, lng: 12};

    circleCenter: google.maps.LatLngLiteral = {lat: 24, lng: 12};

    icon = {
      url: "https://images.squarespace-cdn.com/content/v1/5b64ccb1f2e6b1be5f20a7f9/1537931479772-E0QNNNG8A211GRLS25G2/download+paw+icon.png?format=1500w", // url
      scaledSize: new google.maps.Size(40, 40), // scaled size
      origin: new google.maps.Point(0,0), // origin
      anchor: new google.maps.Point(0, 0) // anchor
    };

    markerOptions: google.maps.MarkerOptions = {
        draggable: false,
        icon: this.icon

    };


    markerPositions: google.maps.LatLngLiteral[] = [{lat:24, lng: 12}, {lat: 32, lng: 12}, {lat: 28, lng: 21}];

    addMarker(event: google.maps.MapMouseEvent) {
      //currently pulling lat/long from map clicks... populate with stores/products based on keyword??
        if (event.latLng != null) {
          this.markerPositions.push(event.latLng.toJSON());
        }
    }

    openInfoWindow(marker: MapMarker) {
      if(this.infoWindow != undefined) {
        this.infoWindow.open(marker);
      }
    }

  getProductsByKeyword() {
    this.productService.findByKeyword(this.keyword).subscribe(
      {
        next: (results) => {
          this.productsByKeyword = results;
          this.mostRecentProdUpdates = this.prodRepPipe.transform(this.productsByKeyword);
          console.log(this.productsByKeyword);
          console.log(this.mostRecentProdUpdates);
        },
        error: (problem) => {
          console.error('MapHttpComponent.getProdsByKeyword(): error loading prods:');
          console.error(problem);
        }
      }
    );

  }

}




