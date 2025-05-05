-- CHANGE THE CATALOG NAME IN BELOW QUERIES

-- Find sync jobs

SELECT {sync:code}, {status:code}, {result:code} FROM {CatalogVersionSyncCronJob AS sync 
JOIN CatalogVersionSyncJob AS job ON {sync:job}={job:pk} 
JOIN CronJobStatus AS status ON {sync:status}={status:pk} 
JOIN CronJobResult AS result ON {sync:result}={result:pk} } 
WHERE {job:code}='ProductCatalogSyncJob' AND {status:code}='ABORTED' ORDER BY {sync:code}


SELECT {sync:code}, {status:code}, {result:code} FROM {CatalogVersionSyncCronJob AS sync 
JOIN CatalogVersionSyncJob AS job ON {sync:job}={job:pk} 
JOIN CronJobStatus AS status ON {sync:status}={status:pk} 
JOIN CronJobResult AS result ON {sync:result}={result:pk} } 
WHERE {job:code}='ProductCatalogSyncJob' AND {status:code}='UNKNOWN' ORDER BY {sync:code}


SELECT {sync:code}, {status:code}, {result:code} 
FROM {CatalogVersionSyncCronJob AS sync 
JOIN CatalogVersionSyncJob AS job ON {sync:job}={job:pk} 
JOIN CronJobStatus AS status ON {sync:status}={status:pk} 
JOIN CronJobResult AS result ON {sync:result}={result:pk} } 
WHERE {job:code}='ProductCatalogSyncJob' AND {status:code}='UNKNOWN' AND {sync:creationtime} <= '2023-12-05 00:00:00' ORDER BY {sync:code}



-----  FIND CONTENT PAGE 

SELECT {cp:uid} FROM {ContentPage AS cp JOIN CatalogVersion AS cv ON {cp:catalogVersion}={cv:pk}
JOIN Catalog AS cat ON {cv:catalog}={cat:pk}
JOIN CmsApprovalStatus AS cas ON {cp:approvalStatus}={cas:pk}}
WHERE {cas:code}='check' AND {cat:id}='ContentCatalog' AND {cv:version}='Staged'


----- WITHOUT CATEGORY VARIANT PRODUCT

 SELECT COUNT({a.PK}) FROM {SmwVariantProduct AS a} WHERE {a.pk} NOT IN ( {{
     SELECT targetPK FROM {CategoryProductRelation AS o} WHERE targetPK={a.pk}
 }} ) AND ({a:discontinued} IS NULL OR {a:discontinued} = 0)



----- FIND SYNC TIME STAMP

SELECT COUNT({pk}) FROM {ItemSyncTimestamp} WHERE {lastSyncSourceModifiedTime} <'1990-01-01 00:00:01'

SELECT {pk} FROM {ItemSyncTimestamp} WHERE {lastSyncSourceModifiedTime} <'1990-01-01 00:00:01'


  -- FIND PRODUCT CATEGORY RELATION
  
SELECT {p:code}, {c:code} 
FROM {CategoryProductRelation AS cpr 
JOIN Product AS p ON {cpr:target}={p:pk} 
JOIN Category AS c ON {cpr:source}={c:pk}} 
WHERE {p:code} = 'p12441'



----  FIND ACTIVE SOLR CORE IN SOLR 

SELECT {sfsc:name}, {si:qualifier} FROM {SolrIndex AS si LEFT JOIN SolrFacetSearchConfig AS sfsc ON {si:facetSearchConfig}={sfsc:pk}} WHERE {si:active} ='1'

--- stock Level warehouse join
SELECT {sl:productCode}, {w:code}, {sl:available}, {iss:code} 
FROM {StockLevel AS sl 
JOIN Warehouse AS w ON {sl:warehouse}={w:pk} 
JOIN InStockStatus AS iss ON {sl:inStockStatus}={iss:pk}} 
WHERE {sl:productCode}='<product_code>'

SELECT {sl:available}, {wh:code} AS WarehouseCode, {sl:productcode}
FROM {ZipCode AS zc}, {Warehouse AS wh}, {StockLevel AS sl}
WHERE {zc:warehouse}={wh:pk}
AND {sl:warehouse}={wh:pk}
AND {zc:code}='<zip_code>'
AND {sl:productcode}='<product_code>'


--- BOOST RULES
SELECT * 
FROM {AsBoostRule AS br
JOIN AsBoostOperator AS bo ON {br:operator}={bo:pk}
JOIN AsBoostType as bt ON {br:boostType}={bt:pk}}

SELECT *
FROM {AsCategoryAwareSearchConfiguration AS casc
JOIN Category AS c ON {casc:category}={c:pk}
JOIN CatalogVersion AS cv ON {c:catalogVersion}={cv:pk}
JOIN Catalog as cat ON {cv:catalog}={cat:pk}}
WHERE {cv:version}='Online'
AND {cat:id}='<ProductCatalog>'
AND ({c:code}='<category_code>' OR {c:name[en]}='<category_en_name>')








