package PageObjects

import geb.Page

class TShirtDesignerPage extends Page{
    static at = { $('h2').text() == 'TSHIRT DESIGNER'}

    static content = {
        graphicWithId(wait: true) {imageId -> $('image', 'la-source-id': imageId )}
        productWithId(wait: true) {productId -> $('image', href: contains("product/${productId}/front_image/medium"))}
    }
}
