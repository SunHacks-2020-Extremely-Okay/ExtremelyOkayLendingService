import React from 'react';

let itemsArray = require('./items.js');

function ItemBrowse() {
    return (
      <div className="BrowseItems">
        <ul>
          {itemsArray.map((value:any, index:any) => {
            return <li key={index}><div className="Item-location"><p>{value.user_id}<br />Item ID={value.item_id}<br />Latitude={value.location_x}<br />Latitude={value.location_y}</p></div><div className="Item-interact"><button>Reserve</button></div></li>
          })}
        </ul>
      </div>
    )
  }

export default ItemBrowse;