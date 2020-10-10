import React, {Component} from 'react';
import { Map, InfoWindow, Marker, GoogleApiWrapper } from 'google-maps-react';

class MapDisplay extends Component {
    render() {
      return (
            <div className="Map-display">
                <Map google={this.props.google} />
            </div>
      );
    }
  }

export default GoogleApiWrapper({
    apiKey: ('AIzaSyDEMqN5k3XDbLI5RkHpwHd3AJurzsfiiw8')
})(MapDisplay);