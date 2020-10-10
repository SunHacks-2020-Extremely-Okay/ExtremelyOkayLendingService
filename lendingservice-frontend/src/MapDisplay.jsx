import React, {Component} from 'react';
import {Map, InfoWindow, Marker, GoogleApiWrapper} from 'google-maps-react';

let itemsArray = require('./items.js');

export class MapDisplay extends Component {

  static defaultProps = {
    center: {
      lat: 33.4226584,
      lng: -111.9401533
    },
    zoom: 13
  }

  render() {
    return (
      <div className="Map-display">
        <Map 
          google={this.props.google} 
          zoom={13}
          initialCenter={{
            lat: 33.4226584,
            lng: -111.9401533
          }}>
          {/* Loop over itemsArray and plot all the points */}
          {itemsArray.map((value, index) => {
            return 
          })}
        </Map>
      </div>
    );
  }
}

export default GoogleApiWrapper({
  apiKey: ('AIzaSyDEMqN5k3XDbLI5RkHpwHd3AJurzsfiiw8')
})(MapDisplay)