-- -----------------------------------------------------
-- Schema ErronkaBus
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `ErronkaBus` ;
USE `ErronkaBus` ;

-- -----------------------------------------------------
-- Table `ErronkaBus`.`Cliente`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ErronkaBus`.`Cliente` (
  `DNI` CHAR(9) NOT NULL,
  `NomApe` VARCHAR(45) NOT NULL,
  `Pass` CHAR(32) NOT NULL,
  PRIMARY KEY (`DNI`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ErronkaBus`.`Municipio`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ErronkaBus`.`Municipio` (
  `CodMun` INT NOT NULL,
  `Nombre` VARCHAR(45) NULL,
  PRIMARY KEY (`CodMun`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ErronkaBus`.`CodigoPostal`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ErronkaBus`.`CodigoPostal` (
  `CP` INT NOT NULL,
  `CodMun` INT NOT NULL,
  PRIMARY KEY (`CP`),
  INDEX `fk_CP_Municipio_idx` (`CodMun` ASC) VISIBLE,
  CONSTRAINT `fk_CP_Municipio`
    FOREIGN KEY (`CodMun`)
    REFERENCES `ErronkaBus`.`Municipio` (`CodMun`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ErronkaBus`.`Linea`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ErronkaBus`.`Linea` (
  `CodLin` INT NOT NULL,
  `Nombre` VARCHAR(45) NOT NULL,
  `HoraInicioAsc` TIME NOT NULL,
  `HoraFinAsc` TIME NOT NULL,
  `HoraInicioDsc` TIME NOT NULL,
  `HoraFinDsc` TIME NOT NULL,
  `PVPU` FLOAT NOT NULL,
  `Frecuencia` INT NOT NULL,
  PRIMARY KEY (`CodLin`),
  UNIQUE INDEX `Nombre_UNIQUE` (`Nombre` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ErronkaBus`.`Parada`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ErronkaBus`.`Parada` (
  `CodLin` INT NOT NULL,
  `Orden` INT NOT NULL,
  `Nombre` VARCHAR(45) NOT NULL,
  `TiempoE` INT NOT NULL,
  `Calle` VARCHAR(45) NOT NULL,
  `Numero` INT NULL,
  `CP` INT NOT NULL,
  UNIQUE INDEX `Nombre_UNIQUE` (`Nombre` ASC) VISIBLE,
  PRIMARY KEY (`CodLin`, `Orden`),
  INDEX `fk_Parada_CodigoPostal1_idx` (`CP` ASC) VISIBLE,
  CONSTRAINT `fk_Parada_Linea1`
    FOREIGN KEY (`CodLin`)
    REFERENCES `ErronkaBus`.`Linea` (`CodLin`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Parada_CodigoPostal1`
    FOREIGN KEY (`CP`)
    REFERENCES `ErronkaBus`.`CodigoPostal` (`CP`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ErronkaBus`.`Billete`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ErronkaBus`.`Billete` (
  `CodBil` INT NOT NULL AUTO_INCREMENT,
  `FechaInicio` DATETIME NULL,
  `DNI` CHAR(9) NOT NULL,
  `CodLin` INT NOT NULL,
  `OrdenEmp` INT NOT NULL,
  `OrdenTer` INT NOT NULL,
  PRIMARY KEY (`CodBil`),
  INDEX `fk_Billete_Cliente1_idx` (`DNI` ASC) VISIBLE,
  INDEX `fk_Billete_Parada1_idx` (`CodLin` ASC, `OrdenEmp` ASC) VISIBLE,
  INDEX `fk_Billete_Parada2_idx` (`OrdenTer` ASC, `CodLin` ASC) VISIBLE,
  CONSTRAINT `fk_Billete_Cliente1`
    FOREIGN KEY (`DNI`)
    REFERENCES `ErronkaBus`.`Cliente` (`DNI`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Billete_Parada1`
    FOREIGN KEY (`CodLin` , `OrdenEmp`)
    REFERENCES `ErronkaBus`.`Parada` (`CodLin` , `Orden`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Billete_Parada2`
    FOREIGN KEY (`CodLin`, `OrdenTer`)
    REFERENCES `ErronkaBus`.`Parada` (`CodLin` , `Orden`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- View `ErronkaBus`.`VistaBillete`
-- -----------------------------------------------------
CREATE VIEW `VistaBillete` AS 
	SELECT CodBil, FechaInicio, DNI, CodLin, OrdenEmp, OrdenTer, round(PVPU*abs(OrdenTer-OrdenEmp), 2) 'PVP', FechaInicio + interval SUM(TiempoE) minute 'FechaFin'
		FROM Linea JOIN Parada USING (CodLin) JOIN Billete using (CodLin)
		WHERE Orden<IF(OrdenTer > OrdenEmp, OrdenTer, OrdenEmp) AND Orden>=IF(OrdenTer > OrdenEmp, OrdenEmp, OrdenTer)
		GROUP BY CodBil, FechaInicio, DNI, CodLin, OrdenEmp, OrdenTer, PVP;