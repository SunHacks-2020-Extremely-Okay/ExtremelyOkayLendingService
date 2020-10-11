import React, {Component} from 'react';
import {Map, InfoWindow, Marker, GoogleApiWrapper} from 'google-maps-react';

let itemsArray = require('./items.js');

export class MapDisplay extends Component {
  state = {
    showingInfoWindow: false,
    activeMarker: {},
    selectedPlace: {},
  };

  onMarkerClick = (props, marker, e) =>
    this.setState({
      selectedPlace: props,
      activeMarker: marker,
      showingInfoWindow: true
    });

  render() {
    var bounds = new this.props.google.maps.LatLngBounds();
    var points = new Array();
    for (let i = 0; i < itemsArray.length; i++) {
      points.push({ lat: itemsArray[i].location_x, lng: itemsArray[i].location_y });
      bounds.extend(points[i]);
    }

    return (
      <div className="Map-display">
        <Map
          google={this.props.google}
          bounds={bounds}
          zoom={13}
          initialCenter={{
            lat: 33.4226584,
            lng: -111.9401533
          }}>
          {/* Loop over itemsArray and plot all the points */}
          {itemsArray.map((value, index) => {
            return <Marker
              name={value.user_id}
              position={{ lat: value.location_x, lng: value.location_y }}
              onClick={this.onMarkerClick} />
          })}
          <InfoWindow
            marker={this.state.activeMarker}
            visible={this.state.showingInfoWindow}>
            <div>
              <h1>{this.state.selectedPlace.name}</h1>
            </div>
          </InfoWindow>
        </Map>
      </div>
    );
  }
}

export default GoogleApiWrapper({
  apiKey: ('AIzaSyDEMqN5k3XDbLI5RkHpwHd3AJurzsfiiw8')
})(MapDisplay)