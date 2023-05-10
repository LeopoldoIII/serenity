package duckduckgo;

import net.serenitybdd.core.steps.UIInteractions;

public class NavigateActions extends UIInteractions {

    public void toTheDuckDUckGOSearchPage(){
        openUrl("https://duckduckgo.com/");
    }
}