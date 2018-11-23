package PageObjects

import groovyx.net.http.ContentType
import groovyx.net.http.HTTPBuilder

class RestClientWrapper {
    def httpBuilder

    RestClientWrapper(url){
        httpBuilder = new HTTPBuilder(url)
        httpBuilder.handler.failure = { resp, reader ->
            [response:resp, reader:reader]
        }
        httpBuilder.handler.success = { resp, reader ->
            [response:resp, reader:reader]
        }
    }

    def setHeaders(headerString){
        httpBuilder.setHeaders(Accept: headerString)
    }

    def get(path){
        def resp = httpBuilder.get(path: path)
        resp
    }

    def post(path, body){
        def resp = httpBuilder.post(
                path: path,
                body: body,
                requestContentType: ContentType.JSON
        )
        resp
    }

}