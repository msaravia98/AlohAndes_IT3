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

-- RFC3: Indice de Ocupacion de cada Oferta
SELECT ID_PERSONA, COUNT(CAPACIDAD)
FROM(SELECT ID_PERSONA 
     FROM RESERVA RE 
     WHERE RE.ID_PROPUESTA 
     IN 
     (SELECT ID 
      FROM PROPUESTA 
      WHERE TIPO_INMUEBLE = UPPER("+tipoInmueble+")
     )
    ) 
GROUP BY ID_PROPUESTA
HAVING COUNT (ID_PERSONA) >3;


-- RCF8: Dar cliente más frecuente dado un tipo de inmueble
SELECT ID_PERSONA, COUNT(ID_PERSONA)
FROM(SELECT ID_PERSONA 
     FROM RESERVA RE 
     WHERE RE.ID_PROPUESTA 
     IN 
     (SELECT ID 
      FROM PROPUESTA 
      WHERE TIPO_INMUEBLE = UPPER("+tipoInmueble+")
     )
    ) 
GROUP BY ID_PERSONA
HAVING COUNT (ID_PERSONA) >3;

-- RCF9: OFERTAS SIN DEMANDA
SELECT *,
FROM(
        SELECT ID_PROPUESTA
        FROM PROPUESTA PRO
        WHERE PRO.ID
        NOT IN
    (
        SELECT *
        FROM RESERVA
        WHERE RESERVA.FECHA_INGRESO 
    )
)













SELECT ID_PROPUESTA, COUNT(ID_PROPUESTA) AS "Cantidad Reservas"  
FROM RESERVAS 
GROUP BY ID_PROPUESTA
ORDER BY "Cantidad Reservas" DESC;

SELECT * FROM RESERVAS WHERE ID_PROPUESTA = 15;


SELECT * FROM PROPUESTAS WHERE ID = 15;

SELECT * FROM VIVIENDAS_EXPRESS WHERE ID = 15;



