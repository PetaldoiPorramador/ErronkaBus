CREATE VIEW `VistaBillete` AS 
	SELECT CodBil, FechaInicio, DNI, CodLin, OrdenEmp, OrdenTer, round(PVPU*abs(OrdenTer-OrdenEmp), 2) 'PVP', FechaInicio + interval SUM(TiempoE) minute 'FechaFin'
		FROM Linea JOIN Parada USING (CodLin) JOIN Billete using (CodLin)
		WHERE Orden<IF(OrdenTer > OrdenEmp, OrdenTer, OrdenEmp) AND Orden>=IF(OrdenTer > OrdenEmp, OrdenEmp, OrdenTer)
		GROUP BY CodBil, FechaInicio, DNI, CodLin, OrdenEmp, OrdenTer, PVP;