import de.hybris.platform.ordersplitting.model.StockLevelModel

def stock = 1000

flexibleSearchService = spring.getBean 'flexibleSearchService'
modelService = spring.getBean 'modelService'

def query = "SELECT {pk} FROM {StockLevel}"
result = flexibleSearchService.search(query).getResult()
for(element in result) {
	element.setAvailable(stock)
	modelService.save(element)
	println element.productCode + '  ' + element.available
}

println 'Stock Updated Successfully !!'
