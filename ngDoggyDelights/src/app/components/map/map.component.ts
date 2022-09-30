import { Component, OnInit, ViewChild } from '@angular/core';
import { MapGeocoder, MapInfoWindow, MapMarker } from '@angular/google-maps';
import { Product } from 'src/app/models/product';
import { ProductService } from 'src/app/services/product.service';
import { ProdRepPipe } from 'src/app/pipes/prod-rep.pipe';
import { Store } from 'src/app/models/store';
import { StoreService } from 'src/app/services/store.service';
import { Address } from 'src/app/models/address';
import { Router } from '@angular/router';


@Component({
  selector: 'app-map',
  templateUrl: './map.component.html',
  styleUrls: ['./map.component.css']
})
export class MapComponent implements OnInit {

  searchCenter: string = '';
  distance: string = '';
  displayStore: boolean = false;
  selectedStore: Store = {} as Store;

  productsByKeyword: Product[] = [];
  storesByKeyword: Store[] = [];
  mostRecentProdUpdates: any[] = [];
  keyword: string = '';

  storeMatchProducts: Store[] = [];
  addressArray: Address[] = [];
  geocodedPlaces: google.maps.LatLngLiteral[] = [];
  markers: any[] = [];
  prodDisplayInfoWindow: any[] = [];

  constructor(private geocoder: MapGeocoder, private router: Router, private productService: ProductService, private storeService: StoreService, private prodRepPipe: ProdRepPipe) {
    }

    ngOnInit(): void {
    }

    @ViewChild(MapInfoWindow) infoWindow: MapInfoWindow | undefined;

    zoom = 13;
    radius = 0;
    mapCenter: google.maps.LatLngLiteral = {lat: 39.854992, lng: -105.1317431};
    circleCenter: google.maps.LatLngLiteral = {lat: 39.854992, lng: -105.1317431};
    icon = {
      url: "https://images.squarespace-cdn.com/content/v1/5b64ccb1f2e6b1be5f20a7f9/1537931479772-E0QNNNG8A211GRLS25G2/download+paw+icon.png?format=1500w", // url
      scaledSize: new google.maps.Size(40, 40), // scaled size
      origin: new google.maps.Point(0,0), // origin
      anchor: new google.maps.Point(0, 0) // anchor
    };


      setMapOptions() {
        if (this.searchCenter !== null && this.searchCenter !== '' && this.searchCenter !== undefined) {
          this.geocoder.geocode({
            address: this.searchCenter
          }).subscribe(({
          results}) => {
            console.log(results);
            this.mapCenter = {lat: results[0].geometry.location.lat(), lng: results[0].geometry.location.lng()};
            this.circleCenter = {lat: results[0].geometry.location.lat(), lng: results[0].geometry.location.lng()};
            console.log("From setMap",this.mapCenter);
            console.log("From setMap", this.distance);
          });
        }
        switch (Number(this.distance)) {
          case 1:
            this.zoom = 14; this.radius = 1609; break;
          case 5:
            this.zoom = 11.75; this.radius = 8046; break;
          case 10:
            this.zoom = 10.75; this.radius = 16093; break;
          case 25:
            this.zoom = 9.4; this.radius = 40233; break;
          case 50:
            this.zoom = 8.25; this.radius = 80467; break;
          default:
            break;
          }
      }


      getProductsByKeyword() {
        this.markers = [];
        this.productService.findByKeyword(this.keyword).subscribe(
          {
            next: (results) => {
              this.productsByKeyword = results;
              console.log(this.productsByKeyword);
              this.storesByProdKeyword();
            },
            error: (problem) => {
              console.error('MapHttpComponent.getProdsByKeyword(): error loading prods:');
              console.error(problem);
            }
          }
        );
      }

      storesByProdKeyword() {
        this.storeService.storesByProdKeyword(this.keyword).subscribe(
          {
            next: (results) => {
              this.storesByKeyword = results;
              console.log(this.storesByKeyword);
              this.mostRecentProdUpdates = this.prodRepPipe.transform(this.productsByKeyword, this.storesByKeyword);
              console.log("stores by prod keyword-most recent PR",this.mostRecentProdUpdates);
              this.geocodeSearchResults();
            },
            error: (problem) => {
              console.error('MapHttpComponent.getProdsByKeyword(): error loading prods:');
              console.error(problem);
            }
          }
        );

      }


      geocodeSearchResults() {
        if (this.mostRecentProdUpdates.length === 0) {
          return;
        }
        else if (this.mostRecentProdUpdates.length > 0) {
          for (let r of this.mostRecentProdUpdates) {
            if (r !== null && r !== undefined) {

              console.log(r.id.storeId);

              this.storeService.getStoreById(r.id.storeId).subscribe({
                next: (stores) => {
                  let store = stores;
                  console.log("from geocode store finder: ",store);
                  this.storeMatchProducts.push(store);
                  if (store.address !== null && store.address !== undefined) {
                    this.addressArray.push(store.address);
                  }
                  console.log("geocoder:", this.addressArray);
                  console.log("geocoder:", this.addressArray[0]);
                  if (this.addressArray.length === this.storeMatchProducts.length) {
                    this.findPositions();
                  }
                },
                    error: (problem) => {
                      console.error(
                        'StoreListHttpComponent.geocode(): error loading store list'
                        );
                        console.error(problem);
                      },
                    });
                  }
                  }
                }
            }

  findPositions() {
    let counter = 0;

                  for (let a of this.addressArray) {
                    console.log("inside addresses", a.address);
                    let addr: string = a.address + ", " + a.city + ", " + a.state + " " + a.postalCode;
                    this.geocoder.geocode({
                      address: addr
                    }).subscribe(({
                      results}) => {
                        console.log("find positions",results);
                        if (counter === 0) {
                        console.log(results[0].geometry.location.lat());
                        console.log(results[0].geometry.location.lng());
                        }
                        this.geocodedPlaces.push({lat: results[0].geometry.location.lat(), lng: results[0].geometry.location.lng()});
                        console.log("find positions- geocoded places", this.geocodedPlaces);
                        if (this.searchCenter === null || this.searchCenter === '' || this.searchCenter === undefined) {
                          this.mapCenter = {lat: this.geocodedPlaces[0].lat, lng: this.geocodedPlaces[0].lng};
                        this.circleCenter = {lat: this.geocodedPlaces[0].lat, lng: this.geocodedPlaces[0].lng};
                        }
                        if (counter === this.addressArray.length - 1) {
                          console.log("entering add markers")
                          this.addMarker();
                        }
                        this.searchCenter = '';
                      });
                    }
  }


      addMarker() {
        let counter = 0;
        for (let gc of this.geocodedPlaces) {

          this.markers.push({
            position: {
              lat: gc.lat,
              lng: gc.lng,
            },
             title: "" + counter,
            options: {
              draggable: false,
              animation: google.maps.Animation.DROP,
              icon: this.icon,
            },
          });
          counter +=1;
        }
        console.log("markers: ", this.markers);
        }

        openInfoWindow(marker: MapMarker) {
          this.selectedStore = this.storeMatchProducts[Number(marker.title)];
          console.log("openInfo store",this.selectedStore);
          this.displayStore = true;
          for (let p of this.mostRecentProdUpdates) {
            console.log("openInfo PR:",p);
            if (p.id.storeId === this.selectedStore.id) {
              this.prodDisplayInfoWindow.push(p);
            }
          }
          if(this.infoWindow != undefined) {
            // this.infoWindow.open(marker);
          }
        }

  resetSearch() {
    this.zoom = 13;
    this.radius = 0;
    this.mapCenter = {lat: 39.854992, lng: -105.1317431};
    this.circleCenter = {lat: 39.854992, lng: -105.1317431};
    this.searchCenter = '';
    this.distance = '';
    this.keyword = '';
    this.geocodedPlaces = [];
    this.displayStore = false;
    this.productsByKeyword = [];
    this.storesByKeyword = [];
    this.mostRecentProdUpdates = [];
    this.storeMatchProducts = [];
    this.addressArray = [];
    this.prodDisplayInfoWindow = [];
  }

  productDetails() {
    this.router.navigateByUrl('product');
  }

}





