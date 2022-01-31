package com.waracle.cakemanagerservice.resource

import com.waracle.cakemanagerservice.model.Cake
import com.waracle.cakemanagerservice.service.CakeService
import org.springframework.http.HttpStatus
import spock.lang.Specification
import spock.lang.Subject

class CakeResourceSpec extends Specification {

    def cakeService = Mock(CakeService)

    def "getCakes should return all the cakes"() {

        given:
        def expected = [new Cake(id: 1, title: "ABC", description: "cake", imageUrl: "url")]
        cakeService.finaAllCakes() >> expected

        when:
        @Subject
        CakeResource cakeResource = new CakeResource(cakeService: cakeService)
        def cakes = cakeResource.getCakes()

        then:
        cakes.body == expected
        cakes.statusCode == HttpStatus.OK
    }

    def "addCake should add and return the cake"() {

        given:
        def cake = new Cake(title: "ABC", description: "cake", imageUrl: "url")
        def expected = new Cake(id: 1, title: "ABC", description: "cake", imageUrl: "url")
        cakeService.addCake(cake) >> expected

        when:
        @Subject
        CakeResource cakeResource = new CakeResource(cakeService: cakeService)
        def cakeResponse = cakeResource.addCake(cake)

        then:
        cakeResponse.body == expected
        cakeResponse.statusCode == HttpStatus.CREATED
    }

}
