package com.waracle.cakemanagerservice.service

import com.waracle.cakemanagerservice.client.CakeAPIClient
import com.waracle.cakemanagerservice.client.response.CakeResponse
import com.waracle.cakemanagerservice.model.Cake
import com.waracle.cakemanagerservice.repository.CakeRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import spock.lang.Specification
import spock.lang.Subject

class CakeServiceSpec extends Specification {

    def cakeRepository = Mock(CakeRepository)
    def cakeAPIClient = Mock(CakeAPIClient)

    def "initialiseData should add sample data into the database"() {

        given:
        def response = new CakeResponse(title: "First", desc: "first cake", image: "url")
        def expected = new ResponseEntity<CakeResponse[]>(new CakeResponse[] {response}, HttpStatus.ACCEPTED)

        when:
        @Subject
        CakeService cakeService = new CakeService(cakeRepository: cakeRepository, cakeAPIClient: cakeAPIClient)
        cakeService.initialiseData()

        then:
        1 * cakeAPIClient.getCakes() >> expected
        1 * cakeRepository.saveAll(_ as Set)  >> {
            arguments -> {
                assert arguments.get(0).size() == 1
                assert arguments[0].getAt(0).title == response.title
                assert arguments[0].getAt(0).description == response.desc
                assert arguments[0].getAt(0).imageUrl == response.image
            }
        }
    }

    def "initialiseData should add unique sample data only into the database"() {

        given:
        def response1 = new CakeResponse(title: "First", desc: "first cake", image: "url")
        def response2 = new CakeResponse(title: "First", desc: "first cake", image: "url")
        def response3 = new CakeResponse(title: "Second", desc: "first cake", image: "url")
        def responseEntity = new ResponseEntity<CakeResponse[]>(new CakeResponse[] {response1, response2, response3}, HttpStatus.ACCEPTED)

        when:
        @Subject
        CakeService cakeService = new CakeService(cakeRepository: cakeRepository, cakeAPIClient: cakeAPIClient)
        cakeService.initialiseData()

        then:
        1 * cakeAPIClient.getCakes() >> responseEntity
        1 * cakeRepository.saveAll(_)  >> {
            arguments -> {
                assert arguments.get(0).size() == 2
            }
        }
    }

    def "initialiseData should not sample data when client api throws an exception"() {

        when:
        @Subject
        CakeService cakeService = new CakeService(cakeRepository: cakeRepository, cakeAPIClient: cakeAPIClient)
        cakeService.initialiseData()

        then:
        1 * cakeAPIClient.getCakes() >> { throw new Exception() }
        0 * cakeRepository.saveAll(_)
    }

    def "initialiseData should not sample data when response is empty"() {

        given:
        def expected = new ResponseEntity<CakeResponse[]>(new CakeResponse[] {}, HttpStatus.ACCEPTED)

        when:
        @Subject
        CakeService cakeService = new CakeService(cakeRepository: cakeRepository, cakeAPIClient: cakeAPIClient)
        cakeService.initialiseData()

        then:
        1 * cakeAPIClient.getCakes() >> expected
        0 * cakeRepository.saveAll(_)
    }

    def "finaAllCakes should return all the cakes"() {

        given:
        def expected = [new Cake(id: 1, title: "ABC", description: "cake", imageUrl: "url")]
        cakeRepository.findAll() >> expected

        when:
        @Subject
        CakeService cakeService = new CakeService(cakeRepository: cakeRepository, cakeAPIClient: cakeAPIClient)
        List<Cake> cakes = cakeService.finaAllCakes()

        then:
        cakes == expected
    }

    def "addCake should add the cake and return in the response"() {

        given:
        def cake = new Cake(title: "ABC", description: "cake", imageUrl: "url")
        def expected = new Cake(id: 1, title: "ABC", description: "cake", imageUrl: "url")
        cakeRepository.save(cake) >> expected

        when:
        @Subject
        CakeService cakeService = new CakeService(cakeRepository: cakeRepository, cakeAPIClient: cakeAPIClient)
        Cake data = cakeService.addCake(cake)

        then:
        data == expected
    }
}
