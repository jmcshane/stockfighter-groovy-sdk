package mc.shane.stockfighter.api

import groovyx.net.http.RESTClient
import groovyx.net.http.ContentType

class StockfighterApi {

	def static final API_ENDPOINT = 'https://api.stockfighter.io/ob/api'
	def static defaultClient = new RESTClient(API_ENDPOINT)
	def defaultVenue
	def defaultStock
	private def stockfighter
	private def account

	StockfighterApi(apiKey,account,defaultVenue,defaultStock,client=defaultClient) {
		this.defaultVenue = defaultVenue
		this.defaultStock = defaultStock
		this.account = account
		stockfighter = client
		stockfighter.setHeaders(['X-Starfighter-Authorization':apiKey])
		stockfighter.setContentType(ContentType.JSON)
	}

	Map<String,Object> getHeartbeat() {
		stockfighter.get(path: '/heartbeat')?.data
	}

	Map<String,Object> getVenueHeartbeat(venue=defaultVenue) {
		stockfighter.get(path: "/venues/${venue}/heartbeat")?.data
	}

	Map<String,Object> getVenueStocks(venue=defaultVenue) {
		stockfighter.get(path: "/venues/${venue}/stocks")?.data
	}

	Map<String,Object> getStockOrderbook(venue=defaultVenue, stock=defaultStock) {
		stockfighter.get(path: "venues/${venue}/stocks/${stock}")?.data
	}

	Map<String,Object> placeNewOrder(int price,int qty,OrderDirection direction,OrderType orderType,venue=defaultVenue,stock=defaultStock) {
		stockfighter.post(path: "venues/${venue}/stocks/${stock}/orders",
			body: [	account: account,
				  	venue: venue,
				  	stock: stock,
				  	price: price,
				  	qty: qty,
				  	direction: direction,
				  	orderType: orderType
		])?.data
	}

	Map<String,Object> getStockQuote(venue=defaultVenue,stock=defaultStock) {
		stockfighter.get(path: "/venues/${venue}/stocks/${stock}/quote")?.data
	}

	Map<String,Object> getOrderStatus(id,venue=defaultVenue,stock=defaultStock) {
		stockfighter.get(path: "/venues/${venue}/stocks/${stock}/orders/${id}")?.data
	}

	Map<String,Object> cancelOrder(int order,venue=defaultVenue,stock=defaultStock) {
		stockfighter.cancel(path: "/venues/${venue}/stocks/${stock}/orders/${order}")?.data
	}

	Map<String,Object> getAllOrderStatuses(venue=defaultVenue) {
		stockfighter.get(path: "/venues/${venue}/accounts/${account}/orders")?.data
	}

	Map<String,Object> getAllStockOrderStatuses(venue=defaultVenue,stock=defaultStock) {
		stockfighter.get(path: "/venues/${venue}/accounts/${account}/stocks/${stock}/orders")?.data
	}

	void createQuotesTickerForStock(endpoint,venue=defaultVenue,stock=defaultStock) {

	}

	void createQuotesTickerForVenue(endpoint,venue=defaultVenue) {

	}

	void createExecutionsTickerForVenue(endpoint,venue=defaultVenue) {

	}

	void createExecutionsTickerForStock(endpoint,venue=defaultVenue,stock=defaultStock) {

	}
}
