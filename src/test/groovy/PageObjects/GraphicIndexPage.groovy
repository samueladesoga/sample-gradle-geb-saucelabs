package PageObjects

import geb.Page

class GraphicIndexPage extends Page {

    static at = { $('h2').text() == 'GRAPHICS' }

    static content = {
        graphics { $('div.graphicImage a')}
    }
}
