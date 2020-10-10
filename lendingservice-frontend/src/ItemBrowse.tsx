import React from 'react';

let itemsArray = require('./items.js');

function ItemBrowse() {

  function getItems() {
    let htmlString:string = "";

    for(let i = 0; i < itemsArray.length; i++) {
      htmlString += "<li><div className='Item-location'><p>" + itemsArray[i].user_id + "<br /></p></div>";
      htmlString += "<div className='Item-interact'><button>Reserve</button></div></li>";
    }

    // itemsArray.foreach((element:any) => {
    //   htmlString += "<li><div className='Item-location'><p>" + element.user_id + "<br /></p></div>";
    //   htmlString += "<div className='Item-interact'><button>Reserve</button></div></li>";
    //   // <li>
    //   // <div className="Item-location">
    //   //   <p>element.user_id<br />element.location_x<br />element.location_y<br /></p>
    //   // </div>
    //   // <div className="Item-interact">
    //   //   <button>Reserve</button>
    //   // </div>
    //   // </li>
    // })
    
    return htmlString;

  }
    return (
      <div className="BrowseItems">
        <ul>
          {itemsArray.map((value:any, index:any) => {
            return <li key={index}><div className="Item-location"><p>value.user_id<br />Address<br />City State Zip<br /></p></div><div className="Item-interact"><button>Reserve</button></div></li>
          })}

          <li>
            <div className="Item-location">
              <p>Item 1<br />Address<br />City State Zip<br /></p>
            </div>
            <div className="Item-interact">
              <button>Reserve</button>
            </div>
          </li>
          <li>
            <div className="Item-location">
              <p>Item 1<br />Address<br />City State Zip<br /></p>
            </div>
            <div className="Item-interact">
              <button>Reserve</button>
            </div>
          </li>
          <li>
            <div className="Item-location">
              <p>Item 1<br />Address<br />City State Zip<br /></p>
            </div>
            <div className="Item-interact">
              <button>Reserve</button>
            </div>
          </li>
          <li>
            <div className="Item-location">
              <p>Item 1<br />Address<br />City State Zip<br /></p>
            </div>
            <div className="Item-interact">
              <button>Reserve</button>
            </div>
          </li>
        </ul>
      </div>
    )
  }

export default ItemBrowse;