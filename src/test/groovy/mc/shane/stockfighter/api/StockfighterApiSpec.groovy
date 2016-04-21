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

	def sut

	def "venue heartbeat hits correct endpoint"() {
		given:
		def respMap = [test: "test1",other: "test2"]
		1*mockEndpoint.get([path: '/heartbeat']) >> [data : respMap]
		1 * mockEndpoint.setHeaders(['X-Starfighter-Authorization':'testkey'])
		1 * mockEndpoint.setContentType(ContentType.JSON)
		when:
		sut = new StockfighterApi(key,account,venue,stock,mockEndpoint)
		def data = sut.getHeartbeat()
		then:
		respMap == data
	}

	def "heartbeat has correct http parameters"() {

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