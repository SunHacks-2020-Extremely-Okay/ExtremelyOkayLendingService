import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import * as serviceWorker from './serviceWorker';
import './App.css';
import MapDisplay from './MapDisplay';


function  Header() {
  return (
    <header className="Header">
      <div className="Header-brand"><p>Extremely Okay Lending service</p></div>
      <div className="Header-nav">
        <div className="tab"><a href="#">Add</a></div>
        <div className="tab"><a href="#">Browse</a></div> 
        <div className="tab"><a href="#">Account</a></div>
      </div>
    </header>
  )
}

function MainNav() {
  return(
    <ul className="Nav">
      <li>Nav Item 1</li>
      <li>Nav Item 2</li>
      <li>Nav Item 3</li>
    </ul>
  )
}

function ItemBrowse() {
  return (
    <div className="BrowseItems">
      <ul>
        <li>Item 1<br />Address<br />City State Zip<br /></li>
        <li>Item 2<br />Address<br />City State Zip<br /></li>
        <li>Item 3<br />Address<br />City State Zip<br /></li>
        <li>Item 4<br />Address<br />City State Zip<br /></li>
      </ul>
    </div>
  )
}

function App() {
  return (
    <div className="App">
      <Header />
      <MainNav />
      <ItemBrowse />
      <MapDisplay />
    </div>
  );
}

ReactDOM.render(
  <React.StrictMode>
    <App />
  </React.StrictMode>,
  document.getElementById('root')
);

// If you want your app to work offline and load faster, you can change
// unregister() to register() below. Note this comes with some pitfalls.
// Learn more about service workers: https://bit.ly/CRA-PWA
serviceWorker.unregister();
