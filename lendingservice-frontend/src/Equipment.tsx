import React from 'react';

function Equipment() {
    return (
        <label>Type:
            <select id="equipmentSelector">
                <option value="wifi" className="addEquipment">Wifi</option>
                <option value="brandon" className="addEquipment">Brandon</option>
            </select>
        </label>
    )
}

export default Equipment;