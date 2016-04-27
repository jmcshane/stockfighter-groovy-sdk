package mc.shane.stockfighter.api

import spock.lang.Specification
import groovyx.net.http.RESTClient
import groovyx.net.http.ContentType

class StockfighterApiSpec extends Specification {

	def mockEndpoint = Mock(RESTClient)
	def account = "testaccount"
	def venue = "testvenue"
	def stock = "teststock"
	def key = "testkey"
	def respMap = [test: "test1",other: "test2"]
	def sut

	def "heartbeat hits correct endpoint"() {
		given:
		1*mockEndpoint.get([path: 'heartbeat']) >> [data : respMap]
		1 * mockEndpoint.setHeaders(['X-Starfighter-Authorization':'testkey'])
		1 * mockEndpoint.setContentType(ContentType.JSON)
		when:
		sut = new StockfighterApi(key,account,venue,stock,mockEndpoint)
		def data = sut.getHeartbeat()
		then:
		respMap == data
	}

	def "venue heartbeat has correct default http parameters"() {
		given:
		1*mockEndpoint.get([path: 'venues/testvenue/heartbeat']) >> [data : respMap]
		1 * mockEndpoint.setHeaders(['X-Starfighter-Authorization':'testkey'])
		1 * mockEndpoint.setContentType(ContentType.JSON)
		when:
		sut = new StockfighterApi(key,account,venue,stock,mockEndpoint)
		def data = sut.getVenueHeartbeat()
		then:
		respMap == data
	}

	def "venue heartbeat has correct parameters when specified value"() {
		given:
		1*mockEndpoint.get([path: 'venues/othervenue/heartbeat']) >> [data : respMap]
		1 * mockEndpoint.setHeaders(['X-Starfighter-Authorization':'testkey'])
		1 * mockEndpoint.setContentType(ContentType.JSON)
		when:
		sut = new StockfighterApi(key,account,venue,stock,mockEndpoint)
		def data = sut.getVenueHeartbeat('othervenue')
		then:
		respMap == data		
	}

	def "venue stocks builds url correctly"() {

	}

	def "stock orderbook builds url correctly"() {

	}

	def "new orderbook posts correct body and url"() {

	}

	def "quote builds url correctly"() {

	}

	def "order status for stock build url correctly"() {

	}

	def "cancel order places DELETE"() {

	}

	def "all orders build correct url"() {

	}

	def "all orders for stock builds correct url"() {

	}
}