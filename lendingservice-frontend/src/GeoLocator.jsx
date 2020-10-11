import React from "react";
import { geolocated } from "react-geolocated";
 
class GeoLocator extends React.Component {
    render() {
        return !this.props.isGeolocationAvailable ? (
            <div>Your browser does not support Geolocation</div>
        ) : !this.props.isGeolocationEnabled ? (
            <div>Geolocation is not enabled</div>
        ) : this.props.coords ? (
            <div>
                <label>Latitude:&nbsp;
                    <input type="text" value={this.props.coords.latitude} />
                </label><br />
                <label>Longitude:&nbsp;
                    <input type="text" value={this.props.coords.longitude} />
                </label>
            </div>
        ) : (
            <div>Getting the location data&hellip; </div>
        );
    }
}

export default geolocated({
    positionOptions: {
        enableHighAccuracy: false,
    },
    userDecisionTimeout: 5000,
})(GeoLocator);