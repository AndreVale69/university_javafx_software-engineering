package com.univr.graphics.components.windows;

public class WindowFactory {
    public static Window getWindow (String windowType) {
        if (windowType == null)
            return null;

        if (windowType.equalsIgnoreCase("LOGIN"))
            return new LoginWindow();
        else if (windowType.equalsIgnoreCase("SELECT"))
            return new SelectWindow();
        else if (windowType.equalsIgnoreCase("INSERT"))
            return new InsertWindow();
        else if (windowType.equalsIgnoreCase("WORK"))
            return new WorkWindow();
        else if (windowType.equalsIgnoreCase("SEARCH"))
            return new SearchWindow();
        else if(windowType.equalsIgnoreCase("MODIFYWORKER"))
            return new ModifyWorkerWindow();
        else if(windowType.equalsIgnoreCase("MODIFYWORK"))
            return new ModifyWorkWindow();
        else if(windowType.equalsIgnoreCase("ADMIN"))
            return new AdminWindow();
        else if (windowType.equalsIgnoreCase("MODIFYMANAGER"))
            return new ModifyManagerWindow();

        return null;
    }
}
