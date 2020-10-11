import React from 'react';
import SignUpForm from './SignUpForm';


function MainNav() {
    return(
      <ul className="Nav">
        <li className="leftNav"><p>Distance Selector:</p><p>5 miles to 50 miles</p>
          <input type="range" id="rangeSelector" list="mileRange" min="5" max="50" step="5" value="5"/>
          <datalist id="mileRange">
            <option value="5" />
            <option value="10" />
            <option value="15" />
            <option value="20" />
            <option value="25" />
            <option value="30" />
            <option value="35" />
            <option value="40" />
            <option value="45" />
            <option value="50" />
          </datalist>
        </li>
        <li className="leftNav">
          <select id="equipmentSelector">
            <option value="wifi">Wifi</option>
            <option value="brandon">Brandon</option>
          </select>
        </li>
        <li className="leftNav">      
          <SignUpForm />
        </li>
      </ul>


    )
  }

export default MainNav;