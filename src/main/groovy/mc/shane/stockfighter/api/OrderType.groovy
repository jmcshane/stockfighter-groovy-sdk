package mc.shane.stockfighter.api

enum OrderType {
	LIMIT('limit'),
	MARKET('market'),
	FILL_OR_KILL('fill-or-kill'),
	IMMEDIATE_OR_CANCEL('immediate-or-cancel')
}