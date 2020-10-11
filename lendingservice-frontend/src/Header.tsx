import React from 'react';
import Modal from 'react-modal';

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

    function openAddModal() {
        setIsOpen(true);
    }

    function afterOpenModal() {

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
            <button onClick={closeAddModal}>Close</button>
            <form>
                <input />
            </form>
        </Modal>
        </header>
    )
  }

export default Header;