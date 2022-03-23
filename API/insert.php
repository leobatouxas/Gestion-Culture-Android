<?php
include "function.php";

if(isset($_REQUEST["operation"])) {

    if($_REQUEST["operation"] == "AllExploitant") {
        try{
            $cnx = connexionPDO();
            $reqSelectExploitant = "SELECT * FROM exploitant ORDER BY codeExploitant DESC";
            $requeteSelectExploitant = $cnx->prepare($reqSelectExploitant);
            $requeteSelectExploitant->execute();
            print("AllExploitant%");
            while($ligne = $requeteSelectExploitant->fetch(PDO::FETCH_ASSOC)){
                $resultat[] = $ligne;
            }
            print(json_encode($resultat));
        }catch(PDOException $e){
            print "Erreur :%".$e->getMessage();
            die();
        }
    }

    if($_REQUEST["operation"] == "lastExploitant") {
        try{
            $cnx = connexionPDO();
            $reqSelectExploitant = "SELECT * FROM exploitant ORDER BY codeExploitant DESC LIMIT 1";
            $requeteSelectExploitant = $cnx->prepare($reqSelectExploitant);
            $requeteSelectExploitant->execute();
            print("lastExploitant%");
            if($ligne = $requeteSelectExploitant->fetch(PDO::FETCH_ASSOC)){
                print(json_encode($ligne));
            }else {
                print("null");
            }
        }catch(PDOException $e){
            print "Erreur :%".$e->getMessage();
            die();
        }
    }
    if($_REQUEST["operation"] == "enregExploitant") {
        try{
            $cnx = connexionPDO();
            $donneesjson = $_REQUEST["donnees"];
            $donnees = json_decode($donneesjson);
            $codeExploitant = $donnees[0];
            $nom = $donnees[1];
            $prenom = $donnees[2];
            $adresse = $donnees[3];
            $ville = $donnees[4];
            $codePostal = $donnees[5];
            $telephone = $donnees[6];
            $email = $donnees[7];
            print("enregExploitant%");
            
            $req = "INSERT INTO exploitant (codeExploitant,nom,prenom,adresse,ville,codePostal,telephone,email)";
            $req .= "VALUES (\"$codeExploitant\",\"$nom\",\"$prenom\",\"$adresse\",\"$ville\",\"$codePostal\",\"$telephone\",\"$email\")";
            print($req);
            $requete = $cnx->prepare($req);
            $requete->execute();
        }catch(PDOException $e) {
            print "Erreur :%".$e->getMessage();
            die();
        }
    }

    if($_REQUEST["operation"] == "lastExploitationByExploitant") {
        try{
            $cnx = connexionPDO();
            $donneesjson = $_REQUEST["donnees"];
            $donnees = json_decode($donneesjson);
            $codeExploitant = $donnees[0];
            $reqSelectExploitant = "SELECT * FROM exploitation WHERE codeExploitant = \"$codeExploitant\" ORDER BY codeExploitation DESC LIMIT 1";
            $requeteSelectExploitation = $cnx->prepare($reqSelectExploitant);
            $requeteSelectExploitation->execute();
            print("lastExploitationByExploitant%");
            if($ligne = $requeteSelectExploitation->fetch(PDO::FETCH_ASSOC)){
                print(json_encode($ligne));
            }
        }catch(PDOException $e){
            print "Erreur :%".$e->getMessage();
            die();
        }
    }
}