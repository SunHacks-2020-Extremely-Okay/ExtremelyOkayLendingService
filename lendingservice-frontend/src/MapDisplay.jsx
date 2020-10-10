import React, {Component} from 'react';
import {Map, InfoWindow, Marker, GoogleApiWrapper} from 'google-maps-react';

let itemsArray = require('./items.js');

export class MapDisplay extends Component {

  render() {
    var bounds = new this.props.google.maps.LatLngBounds();
    for(let i = 0; i < itemsArray.length; i++) {
      bounds.extend({lat:itemsArray[i].location_x, lng:itemsArray[i].location_y});
    }

    return (
      <div className="Map-display">
        <Map 
          google={this.props.google} 
          zoom={13}
          initialCenter={{
            lat: 33.4226584,
            lng: -111.9401533
          }}
          bounds={bounds}>
            {/* Loop over itemsArray and plot all the points */}
            {itemsArray.map((value, index) => {
              return <Marker 
                name={value.user_id}
                position={{lat:value.location_x, lng:value.location_y}} />
            })}
        </Map>
      </div>
    );
  }
}

export default GoogleApiWrapper({
  apiKey: ('AIzaSyDEMqN5k3XDbLI5RkHpwHd3AJurzsfiiw8')
})(MapDisplay)