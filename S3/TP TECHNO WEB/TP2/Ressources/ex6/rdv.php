<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
    <table>
    <tr>
        <td>Semaine du ##</td>
        <td>Lundi</td>
        <td>Mardi</td>
        <td>Mercredi</td>
        <td>Jeudi</td>
        <td>Vendredi</td>
        <td>Samedi</td>
        <td>Dimanche</td>
    </tr>
    <?php
        $heure = 8;
        $minutes = 0;
        for( $i = 0 ; $i< 48 ; $i++ ){
            echo "<tr><td>" . $heure . ":" . $minutes . "</td>";
            // request for reserved space & colspan
            echo "</tr>";
            $minutes += 15;
            if( $minutes == 60 ){
                $minutes = 0;
                $heure += 1;
            }
        }
    ?>
    </table>
</body>
</html>