package PageObjects

import geb.Module

class DesignModule extends Module {
    static base = {$(".featuredProducts")}
    static content = {
        linkFor(wait: true) { graphicName -> $('h4', text: contains(graphicName)).parent().parent()}
    }
}
