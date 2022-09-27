import { Component, OnInit, ViewChild } from '@angular/core';
import { MapInfoWindow, MapMarker } from '@angular/google-maps';

@Component({
  selector: 'app-map',
  templateUrl: './map.component.html',
  styleUrls: ['./map.component.css']
})
export class MapComponent implements OnInit {

  constructor() {}

    ngOnInit(): void {}

    @ViewChild(MapInfoWindow) infoWindow: MapInfoWindow | undefined;

    center: google.maps.LatLngLiteral = {
        lat: 24,
        lng: 12
    };
    zoom = 4;
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
}




