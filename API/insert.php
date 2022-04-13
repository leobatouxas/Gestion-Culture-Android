<?php
include "function.php";

if(isset($_REQUEST["operation"])) {
    if($_REQUEST["operation"] == "enreg") {
        try{
            $cnx = connexionPDO();
            $donneesjson = $_REQUEST["donnees"];
            $donnees = json_decode($donneesjson);
            $codeExploitant = $donnees[0];
            foreach($donnees as $cahierCulture) {
                $idCahierCulture = $cahierCulture->idCahierCulture;
                $annee = $cahierCulture->annee;
                $codeExploitation = $cahierCulture->codeExploitation;
                $req = "INSERT INTO cahierculture (annee, codeExploitation) VALUES (:annee, :codeExploitation)";
                $stmt = $cnx->prepare($req);
                $stmt->bindParam(":annee", $annee);
                $stmt->bindParam(":codeExploitation", $codeExploitation);
                $stmt->execute();

                $req = "SELECT idCahierCulture FROM cahierculture WHERE annee = :annee AND codeExploitation = :codeExploitation";
                $stmt = $cnx->prepare($req);
                $stmt->bindParam(":annee", $annee);
                $stmt->bindParam(":codeExploitation", $codeExploitation);
                $stmt->execute();
                $idCahierCulture = $stmt->fetch()["idCahierCulture"];

                foreach($cahierCulture->parcelles as $parcelle) {
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
            echo "ok";
        }catch(PDOException $e) {
            print "Erreur :%".$e->getMessage();
            die();
        }
    }
}