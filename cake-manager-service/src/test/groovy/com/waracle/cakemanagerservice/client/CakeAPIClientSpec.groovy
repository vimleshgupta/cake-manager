package com.waracle.cakemanagerservice.client

import com.waracle.cakemanagerservice.client.response.CakeResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.client.RestTemplate
import spock.lang.Specification
import spock.lang.Subject

class CakeAPIClientSpec extends Specification {

    def restTemplate = Mock(RestTemplate)

    def "getCakes should return list cakes"() {

        given:
        def expected = new ResponseEntity<CakeResponse[]>(new CakeResponse[] {new CakeResponse(title: "First", desc: "first cake", image: "url")}, HttpStatus.ACCEPTED)
        def baseUrl = "https://baseurl.com"
        restTemplate.getForEntity(baseUrl, CakeResponse[]) >> expected

        when:
        @Subject
        CakeAPIClient resource = new CakeAPIClient(restTemplate: restTemplate, url: baseUrl)
        def cakes = resource.getCakes()

        then:
        cakes.body == expected.body
    }

    def "getCakes should throw an exception when the service is down"() {

        given:
        def baseUrl = "https://baseurl.com"
        restTemplate.getForEntity(baseUrl, CakeResponse[]) >> {throw new Exception()}

        when:
        @Subject
        CakeAPIClient resource = new CakeAPIClient(restTemplate: restTemplate, url: baseUrl)
        def cakes = resource.getCakes()

        then:
        thrown(Exception)
    }
}
