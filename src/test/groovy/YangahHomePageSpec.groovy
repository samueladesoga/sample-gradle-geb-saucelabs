import PageObjects.ProductIndexPage
import PageObjects.GraphicIndexPage
import PageObjects.TShirtDesignerPage
import PageObjects.YangahHomePage
import geb.spock.GebReportingSpec
import org.apache.commons.lang.RandomStringUtils

import static java.lang.Thread.*

class YangahHomePageSpec extends GebReportingSpec {

    def "A user should be able to design their TShirt by clicking on a Product Image"(){
        given: "A user is on the yangah home page"
        to YangahHomePage

        when: "user navigates to the Product Index Page"
        at YangahHomePage
        navigationModule.linkFor('PRODUCTS').click()

        and: "the user clicks on the first Product Image"
        at ProductIndexPage
        def toBeClicked = products.first()
        def img = toBeClicked.parent().parent().parent().parent().find('img')
        def match = (img.@src =~ /https:\/\/\w+.cloudfront.net\/product\/(\d+)\/front_image\/.+/)
        match.find()
        def productId = match.group(1)
        toBeClicked.click()

        then: "the product should be displayed in the TShirt Designer Page"
        at TShirtDesignerPage
        assert productWithId(productId).displayed
    }

    def "A user should be able to design their TShirt by click on a Graphic Image"(){
        given: "A user is on the yangah home page"
        to YangahHomePage

        when: "the user navigates on the Graphic Index Page"
        at YangahHomePage
        navigationModule.linkFor('GRAPHICS').click()

        and: "the user clicks on the first Graphic"
        at GraphicIndexPage
        def toBeClicked = graphics.first()
        def graphicid = toBeClicked.@href.tokenize( '=' )[1]
        toBeClicked.click()

        then: "the image should be loaded in the TShirt Designer"
        at TShirtDesignerPage
        assert graphicWithId(graphicid).displayed
    }

    def "A register should be able to register successfully providing the necessary parameters"(){
        given: "A user is on the yangah home page"
        to YangahHomePage

        when: "the user clicks on the 'Create an account' link"
        at YangahHomePage
        createAnAccount.click()

        and: "the user fills the registration form correctly"
        def email = "samueladesoga+${RandomStringUtils.randomNumeric(8)}@gmail.com"
        def password = "password"
        //Thread sleep added because email wasnt being typed for some strange reasons
        Thread.sleep(500)
        registrationFormModule.emailAddressField.value(email)
        registrationFormModule.passwordField.value(password)
        registrationFormModule.confirmPasswordField.value(password)

        and: "user submit the form"
        registrationFormModule.signUpButton.click()

        then: "the user is successfully registered with a message to confirm account by email"
        at YangahHomePage
        def expectedMessage = "A message with a confirmation link has been sent to your email address. Please open the link to activate your account."
        assert messageBar.text() == expectedMessage
    }
}
