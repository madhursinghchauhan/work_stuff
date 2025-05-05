import de.hybris.platform.core.model.user.CustomerModel;

def customerid = 'bottledeposit22@gmail.com'
def query = "SELECT {pk} FROM {Customer} where {uid}='$customerid'"

flexibleSearchService = spring.getBean 'flexibleSearchService'

CustomerModel customer = flexibleSearchService.search(query).result.get(0)
println customer.uid + " - " + customer.pk
customer.groups.each{
  t -> println " " + t.uid + " - " + t.pk
}
println ""
println "all groups"
customer.allGroups.each{
  t -> println " " + t.uid + " - " + t.pk
}

