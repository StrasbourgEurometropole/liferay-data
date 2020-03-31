ALTER TABLE comment_Comment CHANGE comment_ text_ LONGTEXT;

ALTER TABLE place_Price CHANGE price priceDescription LONGTEXT;

DELETE FROM `VirtualHost` 
WHERE hostname!='localhost';

UPDATE Configuration_ 
SET Configuration_.dictionary=(
    SELECT * 
    FROM (
        SELECT REPLACE(dictionary, '"46.18.194.222:9300"', '"localhost:9300"') 
        FROM Configuration_ 
        WHERE configurationId = "com.liferay.portal.search.elasticsearch6.configuration.ElasticsearchConfiguration"
    ) 
    myconf1
) 
WHERE Configuration_.configurationId = "com.liferay.portal.search.elasticsearch6.configuration.ElasticsearchConfiguration" 