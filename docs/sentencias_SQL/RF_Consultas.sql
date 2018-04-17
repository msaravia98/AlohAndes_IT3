-- Requerimientos Funcionales de Consulta
-- Iteracion 1
-- Grupo B-09

-- RFC1: Mostrar el dinero recibido por cada proveedor durante el año actual y transcurrido
SELECT PP.*, ASW.ID_PROPUESTA, asw."TOTAL GANANCIAS" FROM (

SELECT RE.ID_PROPUESTA AS "ID_PROPUESTA", SUM(RE.COSTO_TOTAL) AS "TOTAL GANANCIAS"
FROM RESERVAS RE
WHERE RE.ID_PROPUESTA IN (
    SELECT PT.ID
    FROM PROPUESTAS PT 
    WHERE PT.ID_PERSONA IN (
        SELECT PEP.ID  
        FROM PERSONAS PEP 
        WHERE ROL = 'operador'
    )
)
GROUP BY RE.ID_PROPUESTA
) ASW
INNER JOIN PROPUESTAS PU
ON PU.ID = ASW.ID_PROPUESTA

INNER JOIN PERSONAS PP
ON PP.ID = PU.ID_PERSONA

ORDER BY asw."TOTAL GANANCIAS" DESC

;


-- RFC2: Mostrar las 20 ofertas más populares
SELECT  *
FROM 
( SELECT ID_PROPUESTA, COUNT(ID_PROPUESTA) AS "Cantidad Reservas"  
FROM RESERVAS 
GROUP BY ID_PROPUESTA
ORDER BY "Cantidad Reservas" DESC)
WHERE ROWNUM <= 20;















SELECT ID_PROPUESTA, COUNT(ID_PROPUESTA) AS "Cantidad Reservas"  
FROM RESERVAS 
GROUP BY ID_PROPUESTA
ORDER BY "Cantidad Reservas" DESC;

SELECT * FROM RESERVAS WHERE ID_PROPUESTA = 15;


SELECT * FROM PROPUESTAS WHERE ID = 15;

SELECT * FROM VIVIENDAS_EXPRESS WHERE ID = 15;



