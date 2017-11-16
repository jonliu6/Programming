DELIMITER //
CREATE PROCEDURE prcFindCityById (
    IN cId VARCHAR(20),
    OUT cName VARCHAR(20),
    OUT country VARCHAR(50)
)
BEGIN
    SELECT cityName, countryName INTO cName, country FROM xCity WHERE cityId = cId;
END //
DELIMITER ;

-- CALL prcFindCityById('cabc0308', @name, @country);
-- SELECT @name, @country;