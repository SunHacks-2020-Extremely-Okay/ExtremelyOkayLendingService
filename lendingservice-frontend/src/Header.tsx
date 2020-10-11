import React from 'react';

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

export default Header;