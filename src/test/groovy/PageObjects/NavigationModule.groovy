package PageObjects

import geb.Module

class NavigationModule extends Module{
    static base = {$("ul.navbar-nav")}
    static content = {
        linkFor { navLink -> $('a', text: contains(navLink)) }
    }
}
