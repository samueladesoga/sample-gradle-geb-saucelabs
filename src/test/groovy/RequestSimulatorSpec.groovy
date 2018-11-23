import PageObjects.RestClientWrapper
import geb.spock.GebReportingSpec
import groovy.json.JsonSlurper
import org.apache.commons.lang.RandomStringUtils

class RequestSimulatorSpec extends GebReportingSpec {

    def static baseUrl

    def setupSpec() {
        baseUrl = "https://reqres.in"
    }

    def "Should be able to create a new user with a job"(){
        given: "I connect to the root url"
        def root = new RestClientWrapper(baseUrl)
        root.setHeaders('application/json')

        when: "i create a new user by providing a name and a job title"
        def name = "Sam-${RandomStringUtils.randomNumeric(8)} Adam"
        def bodyString = "{\"name\": \"${name}\",\"job\": \"leader\"}"
        def jsonObj = new JsonSlurper().parseText(bodyString)
        def resp = root.post("/api/users", jsonObj)

        then: "the 201 status code is returned indicating new user successfully created"
        assert resp["response"].status == 201
        assert resp["reader"].name.contains(name)

    }

    def "A 400 error should be displayed if a user user tries to login without a password"() {
        given: "I connect to the root url"
        def root = new RestClientWrapper(baseUrl)
        root.setHeaders('application/json')

        when: "i login using the email without a password"
        def bodyString = "{\"email\": \"sam@example.com\"}"
        def jsonObj = new JsonSlurper().parseText(bodyString)
        def resp = root.post("/api/login", jsonObj)

        then: "the 201 status code is returned indicating new user successfully created"
        assert resp["response"].status == 400
        assert resp["reader"].error == "Missing password"
    }


    def "A user should not be able to register with same email twice"() {
        given: "I connect to the root url"
        def root = new RestClientWrapper(baseUrl)
        root.setHeaders('application/json')

        and: "I have already registered an account"
        def email = "Sam-${RandomStringUtils.randomNumeric(8)}@example.com"
        def bodyString = "{\"email\": \"${email}\",\"password\": \"password\"}"
        def jsonObj = new JsonSlurper().parseText(bodyString)
        root.post("/api/register", jsonObj)

        when: "i attempt to register the same account second time round"
        def resp = root.post("/api/register", jsonObj)

        then: "the 201 status code is returned indicating new user successfully created"
        assert resp["response"].status != 201
    }




}
