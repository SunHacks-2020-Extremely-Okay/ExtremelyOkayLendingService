import React from 'react';
import ReactDOM from 'react-dom';
import * as serviceWorker from './serviceWorker';

// Import CSS files
import './index.css';
import './App.css';

// Import additional custom functions/classes
import Header from './Header';
import ItemBrowse from './ItemBrowse';
import MainNav from './MainNav';
import MapDisplay from './MapDisplay.jsx';

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
