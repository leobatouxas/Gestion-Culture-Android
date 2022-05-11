<?php
include "function.php";

if (isset($_REQUEST["operation"])) {
    // enregistrement des données
    if ($_REQUEST["operation"] == "enreg") {
        try {
            // connexion à la base de données
            $cnx = connexionPDO();
            // récupération des données
            $donneesjson = $_REQUEST["donnees"];
            $donnees = json_decode($donneesjson);
            foreach ($donnees as $cahierCulture) {
                // récupération du numéro de cahier de culture, annee, codeExploitation 
                $idCahierCulture = $cahierCulture->idCahierCulture;
                $annee = $cahierCulture->annee;
                $codeExploitation = $cahierCulture->codeExploitation;

                // Requete pour verifier si le code exploitation existe bien en base de données
                $reqselect = "SELECT codeExploitation FROM exploitation WHERE codeExploitation = :codeExploitation";
                $stmt = $cnx->prepare($reqselect);
                $stmt->bindParam(":codeExploitation", $codeExploitation);
                $stmt->execute();
                $result = $stmt->fetchAll();
                
                // si le code exploitation existe déjà dans la base de données
                if ($result != null) {
                    $reqselect = "SELECT idCahierCulture FROM cahierculture WHERE annee = :annee AND codeExploitation = :codeExploitation";
                    $stmt = $cnx->prepare($reqselect);
                    $stmt->bindParam(":annee", $annee);
                    $stmt->bindParam(":codeExploitation", $codeExploitation);
                    $stmt->execute();
                    $result = $stmt->fetchAll();
                
                    // S'il n'existe pas de cahier culture pour cette exploitation et cette année on ajoute le cahier cul dans la base de données
                    if (empty($result)) {           
                        // Ajout du cahier culture dans la base de données         
                        $req = "INSERT INTO cahierculture (annee, codeExploitation) VALUES (:annee, :codeExploitation)";
                        $stmt = $cnx->prepare($req);
                        $stmt->bindParam(":annee", $annee);
                        $stmt->bindParam(":codeExploitation", $codeExploitation);
                        $stmt->execute();

                        // récupération de l'id du cahier culture créé
                        $req = "SELECT idCahierCulture FROM cahierculture WHERE annee = :annee AND codeExploitation = :codeExploitation";
                        $stmt = $cnx->prepare($req);
                        $stmt->bindParam(":annee", $annee);
                        $stmt->bindParam(":codeExploitation", $codeExploitation);
                        $stmt->execute();
                        $idCahierCulture = $stmt->fetch()["idCahierCulture"];

                        // ajout des parcelles dans la base de données
                        foreach ($cahierCulture->parcelles as $parcelle) {
                            $req = "INSERT INTO parcelle (codeEspece, idCahierCulture, surface, rendementPrevu, rendementRealise) VALUES (:codeEspece, :idCahierCulture, :surface, :rendementPrevu, :rendementRealise)";
                            $stmt = $cnx->prepare($req);
                            $stmt->bindParam(":codeEspece", $parcelle->codeEspece);
                            $stmt->bindParam(":idCahierCulture", $idCahierCulture);
                            $stmt->bindParam(":surface", $parcelle->surface);
                            $stmt->bindParam(":rendementPrevu", $parcelle->rendementPrevu);
                            $stmt->bindParam(":rendementRealise", $parcelle->rendementRealise);
                            $stmt->execute();
                        }
                    }                    
                }
            }
            print("enreg%");
        } catch (PDOException $e) {
            print "Erreur :%" . $e->getMessage();
            die();
        }
    }
}
