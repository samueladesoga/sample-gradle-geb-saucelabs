import PageObjects.RestClientWrapper
import geb.spock.GebReportingSpec

class TShirtDesignerSpec extends GebReportingSpec {
    def "The products should be grouped by categories in product list api"(){
        given: "I connect to the root url"
        def root = new RestClientWrapper(browser.getBaseUrl())

        when: "i request the product url api"
        def resp = root.get("live_art_configs/products.json")

        then: "the products are grouped by categories"
        assert resp.reader.productCategoriesList.size > 0
    }
}
