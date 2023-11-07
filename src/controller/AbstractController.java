package controller;

import layout.HomeLayout;

public abstract class AbstractController {
    protected HomeLayout homeLayout;
 
    public abstract void save();
    
    public void cancel() {
        homeLayout.showHomePage();
    }

}
