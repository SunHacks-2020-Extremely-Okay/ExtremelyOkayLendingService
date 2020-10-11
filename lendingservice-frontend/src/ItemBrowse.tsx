import React from 'react';

let itemsArray = require('./items.js');

function getData() {
  fetch('http://52.14.231.136:8080/items')
.then(response => response.json())
.then(data => itemsArray = data)
.then(() => console.log(itemsArray));
}

function ItemBrowse() {
    getData();

    return (
      <div className="BrowseItems">
        <ul>
          {itemsArray.map((value: any, index: any) => {
            if(value.available === true) {
              return <li key={index}>
              <div className="Item-location">
                <p>{value.description}<br />
                Item ID={value.itemId}<br />
                Latitude={value.locationX}<br />
                Latitude={value.locationY}</p>
              </div>
              <div className="Item-interact">
                <button>Reserve</button>
              </div>
            </li>
          } else {
            return null;
          }
          })}
        </ul>
      </div>
    )
  }

export default ItemBrowse;