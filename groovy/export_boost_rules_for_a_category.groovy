// BOOST Rules export for category

import de.hybris.platform.adaptivesearch.model.AsBoostRuleModel;
import de.hybris.platform.adaptivesearch.model.AsPromotedItemModel;
import java.util.List;
import org.apache.commons.collections4.CollectionUtils;

def query = "SELECT {pk} FROM {AsCategoryAwareSearchConfiguration AS casc JOIN Category AS c ON {casc:category}={c:pk} JOIN CatalogVersion AS cv ON {c:catalogVersion}={cv:pk} JOIN Catalog as cat ON {cv:catalog}={cat:pk}} WHERE {cv:version}='Online' AND {cat:id}='readyrefreshProductCatalog' AND ({c:code}='ColdDrinks' OR {c:code}='StillWater' OR {c:code}='SparklingWater' OR {c:code}='Brands') "

flexibleSearchService = spring.getBean 'flexibleSearchService'

println "Unique identifier | Unique Index | Category Code | Add boost items | Item Code/ Product Code | Item Unique identifier"

flexibleSearchService.search(query).result.each { item ->   
  final List<AsPromotedItemModel> promotedItems = item.promotedItems
  if(CollectionUtils.isNotEmpty(promotedItems)) {
    promotedItems.each { promotedItem ->
      println item.uid + " | " + item.uniqueIdx + " | " + item.category.code + " | " + item.boostItemsMergeMode + " | " + promotedItem.item.code + " | " + promotedItem.uid
    }
  } else {
    println item.uid + " | " + item.uniqueIdx + " | " + item.category.code + " | " + item.boostItemsMergeMode  + " | " + "No promoted items found for this category ! "
  }
}







import de.hybris.platform.adaptivesearch.model.AsBoostRuleModel;
import de.hybris.platform.adaptivesearch.model.AsPromotedItemModel;
import java.util.List;
import org.apache.commons.collections4.CollectionUtils;

def query = "SELECT {pk} FROM {AsCategoryAwareSearchConfiguration AS casc JOIN Category AS c ON {casc:category}={c:pk} JOIN CatalogVersion AS cv ON {c:catalogVersion}={cv:pk} JOIN Catalog as cat ON {cv:catalog}={cat:pk}} WHERE {cv:version}='Online' AND {cat:id}='readyrefreshProductCatalog' AND ({c:code}='ColdDrinks' OR {c:code}='StillWater' OR {c:code}='SparklingWater' OR {c:code}='Brands') "

flexibleSearchService = spring.getBean 'flexibleSearchService'

println "Unique identifier | Unique Index | Category Code | Add boost rules | Index Property | Operator | Value | Boost Type | Boost | Rule Unique identifier"

flexibleSearchService.search(query).result.each { item -> 
  final List<AsBoostRuleModel> boostRules = item.boostRules
  if(CollectionUtils.isNotEmpty(boostRules)) {
    boostRules.each { rule ->
      println item.uid + " | " + item.uniqueIdx + " | " + item.category.code + " | " + item.boostRulesMergeMode + " | " + rule.indexProperty + " | " + rule.operator + " | " + rule.value + " | " + rule.boostType + " | " + rule.boost + " | " + rule.uid
    }
  } else {
    println item.uid + " | " + item.uniqueIdx + " | " + item.category.code + " | " + item.boostRulesMergeMode + " | " + "No boost rules found for this category ! "
  }
}

println ''

println "Unique identifier | Unique Index | Category Code | Add boost items | Item Code/ Product Code | Item Unique identifier"

flexibleSearchService.search(query).result.each { item ->   
  final List<AsPromotedItemModel> promotedItems = item.promotedItems
  if(CollectionUtils.isNotEmpty(promotedItems)) {
    promotedItems.each { promotedItem ->
      println item.uid + " | " + item.uniqueIdx + " | " + item.category.code + " | " + item.boostItemsMergeMode + " | " + promotedItem.item.code + " | " + promotedItem.uid
    }
  } else {
    println item.uid + " | " + item.uniqueIdx + " | " + item.category.code + " | " + item.boostItemsMergeMode  + " | " + "No promoted items found for this category ! "
  }
}
