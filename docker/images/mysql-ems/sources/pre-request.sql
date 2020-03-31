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

UPDATE comment_Comment 
SET urlProjectCommentaire
replace(urlProjectCommentaire,'https://participer.strasbourg.eu','https://ems-recette-liferay2.sully-group.fr/web/participer') 