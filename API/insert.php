<?php
include "function.php";

if(isset($_REQUEST["operation"])) {
    if($_REQUEST["operation"] == "enreg") {
        try{
            $cnx = connexionPDO();
            $donneesjson = $_REQUEST["donnees"];
            $donneesArray = [];
            foreach(json_decode($donneesjson, true) as $donnees){
                array_push($donneesArray,$donnees);
            }
            //var_dump($donneesArray[0]);
            for($i = 0;count($donneesArray) > $i;$i++){
                $req = "INSERT INTO cahierCulture (idCahierCulture,annee,codeExploitation)";
                $req = $req . "VALUES (". $donneesArray[$i]["idCahierCulture"] .",'". $donneesArray[$i]["annee"] . "','" . $donneesArray[$i]["codeExploitation"] ."');";
                print($req);
            }
        
            //$req = "INSERT INTO exploitation (codeExploitation,codeExploitant,adresse,ville,codePostal)";
            //$req .= "VALUES (\"$codeExploitation\",\"$codeExploitant\",\"$adresse\",\"$ville\",\"$codePostal\")";
            
            //$requete = $cnx->prepare($req);
            //$requete->execute();
        }catch(PDOException $e) {
            print "Erreur :%".$e->getMessage();
            die();
        }
    }
}