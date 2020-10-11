import React from 'react';

function Equipment() {
    return (
        <label>Type:
            <select id="description">
                <option value="wifi">Wifi</option>
                <option value="winlaptop">Windows Laptop</option>
                <option value="maclaptop">Mac Laptop</option>
                <option value="rpi">Raspberry Pi</option>
            </select>
        </label>
    )
}

export default Equipment;