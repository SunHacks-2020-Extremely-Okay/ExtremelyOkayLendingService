import React from 'react';
import Modal from 'react-modal';
import Equipment from './Equipment';
import GeoLocator from './GeoLocator';

const customModalStyles = {
    content : {
        top: '50%',
        left: '50%',
        right: 'auto',
        bottom: 'auto',
        marginRight: '-50%',
        transform: 'translate(-50%, -50%)'
    }
}

Modal.setAppElement('#root');

function Header() {
    const [modalIsOpen,setIsOpen] = React.useState(false);
    let geoEnabled = false;

    function openAddModal() {
        setIsOpen(true);
    }

    function afterOpenModal() {

    }

    function submitAddModal() {
        closeAddModal();
    }

    function closeAddModal() {
        setIsOpen(false);
    }

    return (
        <header className="Header">
        <div className="Header-brand"><p>Extremely Okay Lending service</p></div>
        <div className="Header-nav">
          <div className="tab" onClick={openAddModal}><a href="#">Add</a></div>
          <div className="tab"><a href="#">Browse</a></div> 
          <div className="tab"><a href="#">Account</a></div>
        </div>
      

      <Modal 
        isOpen={modalIsOpen}
        onAfterOpen={afterOpenModal}
        onRequestClose={closeAddModal}
        style={customModalStyles}
        contentLabel="Add Device To Database"
        >
            <form>
                
                <div className="addEquipment">
                  <GeoLocator className="addEquipment" />
                  <Equipment />
                  <label>Sku:&nbsp;
                    <input type="text" id="sku"/>
                  </label>
                
                  <br />
                  <div >
                      <button onClick={submitAddModal}>Submit</button>
                      <button onClick={closeAddModal}>Close</button>
                  </div>
                </div>
                
                
                
            </form>
        </Modal>
        </header>
    )
  }

export default Header;