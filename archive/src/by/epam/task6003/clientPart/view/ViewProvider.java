package by.epam.task6003.clientPart.view;

import by.epam.task6003.clientPart.view.impl.UserActionViewImpl;

public class ViewProvider {
    private final static ViewProvider instance = new ViewProvider();

    private final UserActionView userActionView;

    private ViewProvider(){
        userActionView=new UserActionViewImpl();
    }

    public static ViewProvider getInstance(){
        return instance;
    }

    public UserActionView getUserActionView() {
        return userActionView;
    }
}
