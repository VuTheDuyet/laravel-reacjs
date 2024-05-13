import React, { createContext, useContext, useState } from 'react';

const OverlayContext = createContext();

export const OverlayProvider = ({ children }) => {
    const [overlayActive, setOverlayActive] = useState(false);

    return (
        <OverlayContext.Provider value={{ overlayActive, setOverlayActive }}>
            {children}
        </OverlayContext.Provider>
    );
};

export const useOverlay = () => useContext(OverlayContext);
