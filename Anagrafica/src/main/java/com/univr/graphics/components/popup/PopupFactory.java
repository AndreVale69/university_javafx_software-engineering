package com.univr.graphics.components.popup;

public class PopupFactory {
    public static Popup getPopup (String popupType) {
        if (popupType == null)
            return null;

        if (popupType.equalsIgnoreCase("ERROR"))
            return new ErrorPopup();
        else if (popupType.equalsIgnoreCase("WARNING"))
            return new WarningPopup();
        else if (popupType.equalsIgnoreCase("INFORMATION"))
            return new InformationPopup();

        return null;
    }
}
