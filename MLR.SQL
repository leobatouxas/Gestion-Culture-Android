DROP DATABASE IF EXISTS MLR2;

CREATE DATABASE IF NOT EXISTS MLR2;
USE MLR2;
# -----------------------------------------------------------------------------
#       TABLE : espece
# -----------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS espece
 (
   codeEspece CHAR(32) NOT NULL  ,
   libelle CHAR(32) NULL  
   , PRIMARY KEY (codeEspece) 
 ) 
 comment = "";

# -----------------------------------------------------------------------------
#       TABLE : exploitant
# -----------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS exploitant
 (
   codeExploitant CHAR(32) NOT NULL  ,
   nom CHAR(32) NOT NULL  ,
   prenom CHAR(32) NOT NULL  ,
   adresse CHAR(255) NOT NULL  ,
   ville CHAR(32) NOT NULL  ,
   codePostal CHAR(32) NOT NULL  ,
   telephone CHAR(32) NOT NULL  ,
   email CHAR(255) NOT NULL  
   , PRIMARY KEY (codeExploitant) 
 ) 
 comment = "";

# -----------------------------------------------------------------------------
#       TABLE : parcelle
# -----------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS parcelle
 (
   codeParcelle CHAR(32) NOT NULL  ,
   codeEspece CHAR(32) NOT NULL  ,
   codeCahierCulture CHAR(32) NOT NULL  ,
   surface CHAR(32) NULL  ,
   rendementPrevu CHAR(32) NULL  ,
   rendementRealise CHAR(32) NULL  
   , PRIMARY KEY (codeParcelle) 
 ) 
 comment = "";

# -----------------------------------------------------------------------------
#       INDEX DE LA TABLE parcelle
# -----------------------------------------------------------------------------


CREATE  INDEX I_FK_parcelle_espece
     ON parcelle (codeEspece ASC);

CREATE  INDEX I_FK_parcelle_cahierCulture
     ON parcelle (codeCahierCulture ASC);

# -----------------------------------------------------------------------------
#       TABLE : cahierCulture
# -----------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS cahierCulture
 (
   codeCahierCulture CHAR(32) NOT NULL  ,
   codeExploitation CHAR(32) NOT NULL  ,
   annee CHAR(32) NULL  
   , PRIMARY KEY (codeCahierCulture) 
 ) 
 comment = "";

# -----------------------------------------------------------------------------
#       INDEX DE LA TABLE cahierCulture
# -----------------------------------------------------------------------------


CREATE  INDEX I_FK_cahierCulture_exploitation
     ON cahierCulture (codeExploitation ASC);

# -----------------------------------------------------------------------------
#       TABLE : exploitation
# -----------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS exploitation
 (
   codeExploitation CHAR(32) NOT NULL  ,
   codeExploitant CHAR(32) NOT NULL  ,
   adresse CHAR(255) NULL  ,
   ville CHAR(32) NULL  ,
   codePostal CHAR(255) NULL  
   , PRIMARY KEY (codeExploitation) 
 ) 
 comment = "";

# -----------------------------------------------------------------------------
#       INDEX DE LA TABLE exploitation
# -----------------------------------------------------------------------------


CREATE  INDEX I_FK_exploitation_exploitant
     ON exploitation (codeExploitant ASC);


# -----------------------------------------------------------------------------
#       CREATION DES REFERENCES DE TABLE
# -----------------------------------------------------------------------------


ALTER TABLE parcelle 
  ADD FOREIGN KEY FK_parcelle_espece (codeEspece)
      REFERENCES espece (codeEspece) ;


ALTER TABLE parcelle 
  ADD FOREIGN KEY FK_parcelle_cahierCulture (codeCahierCulture)
      REFERENCES cahierCulture (codeCahierCulture) ;


ALTER TABLE cahierCulture 
  ADD FOREIGN KEY FK_cahierCulture_exploitation (codeExploitation)
      REFERENCES exploitation (codeExploitation) ;


ALTER TABLE exploitation 
  ADD FOREIGN KEY FK_exploitation_exploitant (codeExploitant)
      REFERENCES exploitant (codeExploitant) ;

