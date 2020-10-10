import React, {Component} from 'react';
import GoogleMapReact from 'google-map-react';

class MapDisplay extends Component {
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
        <GoogleMapReact
          bootstrapURLKeys={{ key: 'AIzaSyDEMqN5k3XDbLI5RkHpwHd3AJurzsfiiw8' }}
          defaultCenter={this.props.center}
          defaultZoom={this.props.zoom}
        >
        </GoogleMapReact>
      </div>
    );
  }
}

export default MapDisplay;