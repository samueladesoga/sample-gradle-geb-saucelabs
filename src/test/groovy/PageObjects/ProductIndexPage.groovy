package PageObjects

import geb.Page

class ProductIndexPage extends Page {

    static at = { $('h2').text() == 'PRODUCTS' }

    static content = {
        products { $('div.productBox a')}
    }
}
